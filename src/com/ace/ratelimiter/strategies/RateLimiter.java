package com.ace.ratelimiter.strategies;

public interface RateLimiter {
    boolean processRequest(String clientId);
}
/**
 *
 * token bucket
 * leaky bucket
 * sliding window log
 * sliding window counter
 * fixed window
 */