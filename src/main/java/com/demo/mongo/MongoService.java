package com.demo.mongo;

import com.codahale.metrics.Meter;
import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.Slf4jReporter;
import com.demo.mongo.Model.TestGlobalStat;
import com.demo.mongo.Model.TextOrder;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.util.CloseableIterator;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;

/**
 * @author: yyb
 * @date: 17-9-27
 */
@Component
public class MongoService implements CommandLineRunner {

    private static Logger logger = LoggerFactory.getLogger(MongoService.class);

    @Autowired
    MongoTemplate mongoTemplate;

    private volatile int num = 100;

    private volatile MetricRegistry metricRegistry = new MetricRegistry();

    private Slf4jReporter slf4jReporter = Slf4jReporter.forRegistry(metricRegistry).build();

    private volatile Phaser phaser = new Phaser();

    private volatile LinkedBlockingQueue<String> idsQueue = new LinkedBlockingQueue<>(1000);
    private volatile boolean isEnd = false;

    public void newSaveThread(int i) {
        for (int k = 0; k < i; k++) {
            new Thread(() -> {
                phaser.register();
                int curNum = new Integer(num);
                while (curNum > 0) {
                    TextOrder textOrder = generateOutlayOrder();
                    mongoTemplate.save(textOrder);
                    metricRegistry.meter("save").mark(1L);
                    curNum--;
                }
                phaser.arriveAndDeregister();
            }, "save-" + k).start();
        }
    }

    public void newInsertThread(int i) {
        for (int k = 0; k < i; k++) {
            phaser.register();
            new Thread(() -> {
                try {
                    int curNum = new Integer(num);
                    while (curNum > 0) {
                        TextOrder textOrder = generateOutlayOrder();
                        mongoTemplate.insert(textOrder);
                        metricRegistry.meter("insert").mark(1L);
                        curNum--;
                    }
                } finally {
                    phaser.arriveAndDeregister();
                }
            }, "insert-" + i).start();
        }
    }

    public void newIdProductionThread() {
        isEnd = false;
        phaser.register();
        new Thread(() -> {
            CloseableIterator<TextOrder> stream = mongoTemplate.stream(new Query(), TextOrder.class);
            while (stream.hasNext()) {
                TextOrder textOrder = stream.next();
                try {
                    idsQueue.put(textOrder.getId());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            phaser.arriveAndDeregister();
            isEnd = true;
        }, "id-production").start();
    }

    public void newUpdateThread(int i) {
        this.newIdProductionThread();

        for (int k = 0; k < i; k++) {
            phaser.register();
            new Thread(() -> {
                while (!idsQueue.isEmpty() || !isEnd) {
                    try {
                        String id = idsQueue.poll(5, TimeUnit.SECONDS);
                        if (StringUtils.isEmpty(id)) {
                            continue;
                        }
                        mongoTemplate.updateFirst(
                                Query.query(Criteria.where("id").is(id)),
                                Update.update("status", "1"),
                                TextOrder.class);
                        metricRegistry.meter("updateFirst").mark();
                    } catch (InterruptedException e) {
                        logger.error("--updateFirst->>", e);
                    }
                }
                phaser.arriveAndDeregister();
            }, "update-" + i).start();
        }
    }

    public void newDeleteThread(int i) {
        this.newIdProductionThread();
        for (int k = 0; k < i; k++) {
            phaser.register();
            new Thread(() -> {
                while (!idsQueue.isEmpty() || !isEnd) {
                    try {
                        String id = idsQueue.poll(5, TimeUnit.SECONDS);
                        if (StringUtils.isEmpty(id)) {
                            continue;
                        }
                        mongoTemplate.remove(Query.query(Criteria.where("id").is(id)), TextOrder.class);
                        metricRegistry.meter("delete").mark();
                    } catch (InterruptedException e) {
                        logger.error("--delete->>", e);
                    }
                }
                phaser.arriveAndDeregister();
            }, "delete-" + i).start();
        }
    }

    public void newIncThread(String date, int i) {

        mongoTemplate.findAndRemove(Query.query(Criteria.where("date").is(date)), TestGlobalStat.class);
        TestGlobalStat testGlobalStat = new TestGlobalStat();
        testGlobalStat.setDate(date);
        mongoTemplate.save(testGlobalStat);

        for (int k = 0; k < i; k++) {
            new Thread(() -> {
                for (int j = 0; j < num; j++) {
                    mongoTemplate.updateFirst(Query.query(Criteria.where("date").is(date)),
                            new Update().inc("rechargeMoney", 11), TestGlobalStat.class);
                    metricRegistry.meter("inc").mark();
                }
            }, "inc-1-" + k).start();
        }

        for (int k = 0; k < i; k++) {
            new Thread(() -> {
                for (int j = 0; j < num; j++) {
                    mongoTemplate.updateFirst(Query.query(Criteria.where("date").is(date)),
                            new Update().inc("wxMoney", 12), TestGlobalStat.class);
                    metricRegistry.meter("inc").mark();
                }
            }, "inc-2-" + k).start();
        }

        for (int k = 0; k < i; k++) {
            new Thread(() -> {
                for (int j = 0; j < num; j++) {
                    mongoTemplate.updateFirst(Query.query(Criteria.where("date").is(date)),
                            new Update().inc("wxOrder", 1), TestGlobalStat.class);
                    metricRegistry.meter("inc").mark();
                    metricRegistry.meter("wxOrder").mark();
                }
            }, "inc-3-" + k).start();
        }
    }

    public TextOrder generateOutlayOrder() {
        TextOrder textOrder = new TextOrder();
        textOrder.setUserId(generateId(24));
        textOrder.setNickname("nickname");

        textOrder.setBusinessId(generateId(24));
        textOrder.setBusinessName("business");
        textOrder.setChildId(generateId(24));
        textOrder.setChildName("child");
        textOrder.setSelfId(generateId(24));
        textOrder.setSelfName("self");

        textOrder.setSex(RandomUtils.nextInt(2));
        textOrder.setDesc("订阅章节");
        textOrder.setType("outlay");
        textOrder.setReadMoney(35);
        textOrder.setGiftMoney(0);
        textOrder.setCurGiftMoney(10);
        textOrder.setCurReadMoney(40);
        textOrder.setTime(System.currentTimeMillis());
        textOrder.setDate(LocalDate.now().toString());
        textOrder.setHour(LocalDateTime.now().getHour() + "");
        textOrder.setStatus(0);
        textOrder.setPayId(null);
        textOrder.setRechargeMoney(0);
        textOrder.setCartoonId(generateId(24));
        textOrder.setCartoonName("cartoon");
        textOrder.setChapterNum(12);
        textOrder.setChapterId(generateId(24));
        textOrder.setLinkId(generateId(24));
        textOrder.setSourceCartoonId(generateId(24));
        textOrder.setSourceCartoonMoney(39);

        return textOrder;
    }

    public TextOrder generateIncomeOrder() {
        TextOrder textOrder = generateOutlayOrder();
        textOrder.setDesc("微信充值-30");
        textOrder.setType("income");
        textOrder.setReadMoney(3000);
        textOrder.setGiftMoney(338);
        textOrder.setPayId(generateId(26));
        textOrder.setRechargeMoney(30);
        return textOrder;
    }

    public String generateId(int num) {
        return RandomStringUtils.randomAlphanumeric(num);
    }


    @Override
    public void run(String... args) throws Exception {
        if (args.length == 0) return;
        phaser.register();
        logger.info("--->> start run");
        slf4jReporter.start(1, TimeUnit.SECONDS);

        String type = args[0];
        String thread = args[1];
        Integer threadNum = Integer.valueOf(thread);

        switch (type.toUpperCase()) {
            case "INSERT":
                this.num = Integer.valueOf(args[2]);
                newInsertThread(threadNum);
                break;
            case "SAVE":
                this.num = Integer.valueOf(args[2]);
                newSaveThread(threadNum);
                break;
            case "DELETE":
                newDeleteThread(threadNum);
                break;
            case "UPDATE":
                newUpdateThread(threadNum);
                break;
            case "INC":
                this.num = Integer.valueOf(args[2]);
                String date = args[3];
                newIncThread(date, threadNum);
                break;
        }

        phaser.arriveAndDeregister();

        Thread.sleep(3000);
    }

    private void logMetricInfo() {
        for (String key : metricRegistry.getMeters().keySet()) {
            Meter meter = metricRegistry.meter(key);
            logger.info("{}--> count:{}, meanRate:{}, oneRate:{}, fiveRate:{}, fifteenRate:{}",
                    key,
                    meter.getCount(),
                    ((Double) meter.getMeanRate()).longValue(),
                    ((Double) meter.getOneMinuteRate()).longValue(),
                    ((Double) meter.getFiveMinuteRate()).longValue(),
                    ((Double) meter.getFifteenMinuteRate()).longValue());
        }
    }

}
