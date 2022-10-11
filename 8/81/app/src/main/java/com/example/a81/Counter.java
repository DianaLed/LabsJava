package com.example.a81;

public class Counter {
    private long count;


    public Counter() {
        count = 0;
    }

    public Counter(Counter c) {
        count = c.count;
    }

    public Counter(long startValue) {
        count = startValue;
    }

    public void increase() {
        ++count;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public void reset() {
        count = 0;
    }

}
