package io.arasitensei.sunriseexpress.foundation.utility;

import org.jetbrains.annotations.NotNull;

import java.util.Iterator;

public class Triple<T> extends Trilogy<T, T, T> implements Iterable<T> {

    protected Triple(T first, T second, T third) {
        super(first, second, third);
    }

    public T get(int index) {
        return switch (index) {
            case 0 -> getFirst();
            case 1 -> getSecond();
            case 2 -> getThird();
            default -> throw new IllegalStateException("Index (" + index + ") is illegal!");
        };
    }

    public static <T> Triple<T> create(T first, T second, T third) {
        return new Triple<>(first, second, third);
    }

    @NotNull
    @Override
    public Iterator<T> iterator() {
        return new Triplerator<>(this);
    }

    private static class Triplerator<T> implements Iterator<T> {

        int state;
        private final Triple<T> triple;

        public Triplerator(Triple<T> triple) {
            this.triple = triple;
            state = 0;
        }

        @Override
        public boolean hasNext() {
            return state != 3;
        }

        @Override
        public T next() {
            state++;
            return switch (state) {
                case 1 -> triple.first;
                case 2 -> triple.second;
                case 3 -> triple.third;
                default -> throw new IllegalStateException("State (" + state + ") is illegal!");
            };
        }
    }
}
