package com.ace.ratelimiter.strategies.model;

import java.time.Instant;

public class TokenBucket {
    private Instant lastRequestTime;
    private Integer bucketTokens;

    public TokenBucket(Instant lastRequestTime, Integer bucket) {
        this.lastRequestTime = lastRequestTime;
        this.bucketTokens = bucket;
    }

    public Instant getLastRequestTime() {
        return lastRequestTime;
    }

    public void setLastRequestTime(Instant lastRequestTime) {
        this.lastRequestTime = lastRequestTime;
    }

    public Integer getBucketTokens() {
        return bucketTokens;
    }

    public void setBucketTokens(Integer bucketTokens) {
        this.bucketTokens = bucketTokens;
    }

    @Override
    public String toString() {
        return "TokenBucket{" +
                "lastRequestTime=" + lastRequestTime +
                ", bucketTokens=" + bucketTokens +
                '}';
    }
}
