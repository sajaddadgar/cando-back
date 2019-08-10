package com.rahnema.domain;

public interface IDomain<T> {
    boolean isValid();

    T generate();

}
