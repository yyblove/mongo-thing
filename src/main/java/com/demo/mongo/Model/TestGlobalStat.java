package com.demo.mongo.Model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Map;
@Document
public class TestGlobalStat {

    @Id
    private String id;

    @Indexed
    private String date;

    /**
     * 充值金额
     */
    private int rechargeMoney;

    /**
     * 充值钱
     */
    private int wxMoney;

    /**
     * 订单数
     */
    private int wxOrder;

    /**
     * 充值去重用户数
     */
    private int rechargeUser;

    private int rechargeOpenNum;

    private int rechargeClickNum;

    private int rechargeSuccessNum;

    /**
     * 充值订单数
     */
    private int rechargeOrder;

    /**
     * 订阅用户
     */
    private int subscribeUser;

    private int readMoney;

    private int giftMoney;

    /**
     * 总用户
     */
    private int allUser;

    /**
     * 日活用户
     */
    private int activeUser;

    private int chapterNum;

    /**
     * 新增用户
     */
    private int addUser;

    /**
     * 系统男性
     */
    private int maleNum;

    /**
     * 系统女性
     */
    private int femaleNum;

    /**
     * 充值系统男性
     */
    private int rechargeMaleNum;

    /**
     * 充值系统女性
     */
    private int rechargeFemaleNum;

    public int getGiftMoney() {
        return giftMoney;
    }

    public void setGiftMoney(int giftMoney) {
        this.giftMoney = giftMoney;
    }

    private Map<String, String> userMap;

    public int getReadMoney() {
        return readMoney;
    }

    public void setReadMoney(int readMoney) {
        this.readMoney = readMoney;
    }

    public int getChapterNum() {
        return chapterNum;
    }

    public void setChapterNum(int chapterNum) {
        this.chapterNum = chapterNum;
    }

    public int getSubscribeUser() {
        return subscribeUser;
    }

    public void setSubscribeUser(int subscribeUser) {
        this.subscribeUser = subscribeUser;
    }

    public int getAllUser() {
        return allUser;
    }

    public void setAllUser(int allUser) {
        this.allUser = allUser;
    }

    public int getActiveUser() {
        return activeUser;
    }

    public void setActiveUser(int activeUser) {
        this.activeUser = activeUser;
    }

    public int getAddUser() {
        return addUser;
    }

    public void setAddUser(int addUser) {
        this.addUser = addUser;
    }

    public Map<String, String> getUserMap() {
        return userMap;
    }

    public void setUserMap(Map<String, String> userMap) {
        this.userMap = userMap;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getRechargeMoney() {
        return rechargeMoney;
    }

    public void setRechargeMoney(int rechargeMoney) {
        this.rechargeMoney = rechargeMoney;
    }

    public int getRechargeUser() {
        return rechargeUser;
    }

    public void setRechargeUser(int rechargeUser) {
        this.rechargeUser = rechargeUser;
    }

    public int getRechargeOrder() {
        return rechargeOrder;
    }

    public void setRechargeOrder(int rechargeOrder) {
        this.rechargeOrder = rechargeOrder;
    }

    public int getRechargeOpenNum() {
        return rechargeOpenNum;
    }

    public void setRechargeOpenNum(int rechargeOpenNum) {
        this.rechargeOpenNum = rechargeOpenNum;
    }

    public int getRechargeClickNum() {
        return rechargeClickNum;
    }

    public void setRechargeClickNum(int rechargeClickNum) {
        this.rechargeClickNum = rechargeClickNum;
    }

    public int getRechargeSuccessNum() {
        return rechargeSuccessNum;
    }

    public void setRechargeSuccessNum(int rechargeSuccessNum) {
        this.rechargeSuccessNum = rechargeSuccessNum;
    }

    public int getMaleNum() {
        return maleNum;
    }

    public void setMaleNum(int maleNum) {
        this.maleNum = maleNum;
    }

    public int getFemaleNum() {
        return femaleNum;
    }

    public void setFemaleNum(int femaleNum) {
        this.femaleNum = femaleNum;
    }

    public int getRechargeMaleNum() {
        return rechargeMaleNum;
    }

    public void setRechargeMaleNum(int rechargeMaleNum) {
        this.rechargeMaleNum = rechargeMaleNum;
    }

    public int getRechargeFemaleNum() {
        return rechargeFemaleNum;
    }

    public void setRechargeFemaleNum(int rechargeFemaleNum) {
        this.rechargeFemaleNum = rechargeFemaleNum;
    }

    public int getWxMoney() {
        return wxMoney;
    }

    public void setWxMoney(int wxMoney) {
        this.wxMoney = wxMoney;
    }

    public int getWxOrder() {
        return wxOrder;
    }

    public void setWxOrder(int wxOrder) {
        this.wxOrder = wxOrder;
    }
}
