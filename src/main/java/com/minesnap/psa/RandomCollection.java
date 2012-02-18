package com.minesnap.psa;

// Lifted from http://stackoverflow.com/questions/6409652/random-weighted-selection-java-framework/6409791#6409791

import java.util.NavigableMap;
import java.util.TreeMap;
import java.util.Random;

public class RandomCollection<E> {
    private final NavigableMap<Double, E> map = new TreeMap<Double, E>();
    private final Random random;
    private double total = 0;

    public RandomCollection() {
        this(new Random());
    }

    public RandomCollection(Random random) {
        this.random = random;
    }

    public void add(double weight, E result) {
        if (weight <= 0) return;
        total += weight;
        map.put(total, result);
    }

    public E next() {
        double value = random.nextDouble() * total;
        return map.ceilingEntry(value).getValue();
    }
}
