package com.ace.ratelimiter;

/**
 * 1. Generate traffic
 * 2. Give it to rate limiter
 * 3. Write response to a log file
 */
public class Orchestrator {
    public static void main(String[] args) {
        int i = 0;
        long end = System.currentTimeMillis() + 1000;
        while (System.currentTimeMillis() < end) {
            i++;
            // generate load based  i = 200 and then reset the counter after one sec
        }
        System.out.println(i);
    }
}

// 168449976, 179243930, 179033346, 179321523, 181882042