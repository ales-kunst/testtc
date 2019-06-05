package org.testtc.config;

public interface Configuration<K extends Enum> {

    <T> T get(K key);

    <T> void set(K key, T value);

    <T> void add(K key, T value);

    <T> T[] getAll(K key);
}
