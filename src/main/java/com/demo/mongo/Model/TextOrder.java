package com.demo.mongo.Model;

import org.springframework.data.annotation.Id;

/**
 * @author: yyb
 * @date: 17-9-27
 */
public class TextOrder {

    @Id
    private String id;

    private String userId;

    private String nickname;

    private String businessId;

    private String businessName;

    private String childId;

    private String childName;

    private String selfId;

    private String selfName;

    private Integer sex;

    private String desc;

    private String type;

    private Integer readMoney;

    private Integer giftMoney;

    private Integer curReadMoney;

    private Integer curGiftMoney;

    private Long time;

    private String date;

    private String hour;

    private Integer status;

    private String payId;

    private Integer rechargeMoney;

    private String cartoonId;

    private String cartoonName;

    private Integer chapterNum;

    private String chapterId;

    private String linkId;

    private String sourceCartoonId;

    private int sourceCartoonMoney;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getBusinessId() {
        return businessId;
    }

    public void setBusinessId(String businessId) {
        this.businessId = businessId;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public String getChildId() {
        return childId;
    }

    public void setChildId(String childId) {
        this.childId = childId;
    }

    public String getChildName() {
        return childName;
    }

    public void setChildName(String childName) {
        this.childName = childName;
    }

    public String getSelfId() {
        return selfId;
    }

    public void setSelfId(String selfId) {
        this.selfId = selfId;
    }

    public String getSelfName() {
        return selfName;
    }

    public void setSelfName(String selfName) {
        this.selfName = selfName;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getReadMoney() {
        return readMoney;
    }

    public void setReadMoney(Integer readMoney) {
        this.readMoney = readMoney;
    }

    public Integer getGiftMoney() {
        return giftMoney;
    }

    public void setGiftMoney(Integer giftMoney) {
        this.giftMoney = giftMoney;
    }

    public Integer getCurReadMoney() {
        return curReadMoney;
    }

    public void setCurReadMoney(Integer curReadMoney) {
        this.curReadMoney = curReadMoney;
    }

    public Integer getCurGiftMoney() {
        return curGiftMoney;
    }

    public void setCurGiftMoney(Integer curGiftMoney) {
        this.curGiftMoney = curGiftMoney;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getPayId() {
        return payId;
    }

    public void setPayId(String payId) {
        this.payId = payId;
    }

    public Integer getRechargeMoney() {
        return rechargeMoney;
    }

    public void setRechargeMoney(Integer reachargeMoney) {
        this.rechargeMoney = reachargeMoney;
    }

    public String getCartoonId() {
        return cartoonId;
    }

    public void setCartoonId(String cartoonId) {
        this.cartoonId = cartoonId;
    }

    public String getCartoonName() {
        return cartoonName;
    }

    public void setCartoonName(String cartoonName) {
        this.cartoonName = cartoonName;
    }

    public Integer getChapterNum() {
        return chapterNum;
    }

    public void setChapterNum(Integer chapterNum) {
        this.chapterNum = chapterNum;
    }

    public String getChapterId() {
        return chapterId;
    }

    public void setChapterId(String chapterId) {
        this.chapterId = chapterId;
    }

    public String getLinkId() {
        return linkId;
    }

    public void setLinkId(String linkId) {
        this.linkId = linkId;
    }

    public String getSourceCartoonId() {
        return sourceCartoonId;
    }

    public void setSourceCartoonId(String sourceCartoonId) {
        this.sourceCartoonId = sourceCartoonId;
    }

    public int getSourceCartoonMoney() {
        return sourceCartoonMoney;
    }

    public void setSourceCartoonMoney(int sourceCartoonMoney) {
        this.sourceCartoonMoney = sourceCartoonMoney;
    }
}
