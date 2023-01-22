package com.ace.ratelimiter.loadgenerator;

public interface LoadGenerator {
    void generateSteadyLoad(int tps);
    void generateSharpTrafficSpikeLoad(int lowTps, int highTps);
    void generateSteadilyIncreasingLoad(int startTps, int incRate);
    void customLoadPattern(int startLoad);
}
