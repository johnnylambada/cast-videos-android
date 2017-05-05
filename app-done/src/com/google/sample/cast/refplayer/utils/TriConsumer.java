package com.google.sample.cast.refplayer.utils;

public interface TriConsumer<T,U,V> {
    void accept(T v1, U v2, V v3);
}
