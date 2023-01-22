package com.ace.ratelimiter.strategies;

import com.ace.ratelimiter.strategies.model.TokenBucket;

import java.time.Duration;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Algorithm
 * each client has a bucket of token when a request comes in client request is assigned a token and then
 * processed if there are no tokens in the bucket then request is throttled. Token are refilled in bucket at predefined
 * rate.
 */
public class TokenBucketRateLimiterImpl implements RateLimiter {

    int TOKEN_REFILL_RATE = 20; // 20 tokens per seconds
    int BUCKET_LIMIT = 50;
    Map<String, TokenBucket> tokenBucket = new HashMap<>();

    @Override
    public boolean processRequest(String clientId) {
        if(!tokenBucket.containsKey(clientId))
            tokenBucket.put(clientId, new TokenBucket(Instant.ofEpochSecond(0), BUCKET_LIMIT));
        TokenBucket bucket = tokenBucket.get(clientId);
        if(Duration.between(bucket.getLastRequestTime(), Instant.now()).toMillis() > 1000)
            bucket.setBucketTokens(Math.min(bucket.getBucketTokens() + TOKEN_REFILL_RATE, BUCKET_LIMIT));
        if(bucket.getBucketTokens() > 0) {
            bucket.setBucketTokens(bucket.getBucketTokens() - 1);
            bucket.setLastRequestTime(Instant.now());
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        Instant startTime = Instant.now();
        Instant requestTime = Instant.now();
        Instant requestEndTime = Instant.now();
        String clientId = UUID.randomUUID().toString();
        System.out.println("Generating load...");
        TokenBucketRateLimiterImpl tokenBucketRateLimiter = new TokenBucketRateLimiterImpl();
        // generating 15 request per second then generate 67 request per ms
        long processedRequestCount = 0, throttledRequestCount = 0;
        long i = 0, j = 0, sum = 0;
        while (true) {
            j++;
            if(Duration.between(requestTime, Instant.now()).toMillis() >= 10) {
                //System.out.println(Duration.between(requestTime, Instant.now()).toMillis());
                i++;
                requestTime = Instant.now();
                if(tokenBucketRateLimiter.processRequest(clientId))
                    processedRequestCount++;
                else
                    throttledRequestCount++;
                requestEndTime = Instant.now();
                sum += Duration.between(requestTime, requestEndTime).toMillis();
            }
            if(Duration.between(startTime, Instant.now()).toMillis() >= 1000)
                break;
        }
        System.out.println(Duration.between(startTime, requestTime).toMillis());
        System.out.println("Total Request - " + i + " " + j + " " + sum);
        System.out.println("Processed Request - " + processedRequestCount);
        System.out.println("Throttled Request - " + throttledRequestCount);
    }
}