package net.xcip.tarbitrary.demo.entity;

/**
 * <p></p>
 *
 * @author tarbitrary
 * @version V1.0
 * @created_time 2019-12-11 16:59
 * @module 现货延期模块
 **/
public class Vote {
    private int userId;



    /**
     *
     * 是否为请求
     * true 表示请求
     * false 表示响应
     */
    private boolean isRequest;

    /**
     * 投票or查询
     * true 表示投票
     * false 表示查询
     */
    private boolean voteFlag;
    private int counts;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public boolean isRequest() {
        return isRequest;
    }

    public void setRequest(boolean request) {
        isRequest = request;
    }

    public int getCounts() {
        return counts;
    }

    public void setCounts(int counts) {
        this.counts = counts;
    }

    public boolean isVoteFlag() {
        return voteFlag;
    }

    public void setVoteFlag(boolean voteFlag) {
        this.voteFlag = voteFlag;
    }

    @Override
    public String toString() {
        return "Vote{" +
                "userId=" + userId +
                ", isRequest=" + isRequest +
                ", voteFlag=" + voteFlag +
                ", counts=" + counts +
                '}';
    }
}
