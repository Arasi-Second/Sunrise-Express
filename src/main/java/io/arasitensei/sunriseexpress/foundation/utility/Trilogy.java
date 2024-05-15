package io.arasitensei.sunriseexpress.foundation.utility;

public class Trilogy<F, S, T> {

    F first;
    S second;
    T third;

    protected Trilogy(F first, S second, T third) {
        this.first = first;
        this.second = second;
        this.third = third;
    }

    public F getFirst() {
        return first;
    }

    public S getSecond() {
        return second;
    }

    public T getThird() {
        return third;
    }
}
