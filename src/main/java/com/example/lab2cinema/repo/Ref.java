package com.example.lab2cinema.repo;

public final class Ref<T>  {

    public T Value;

    public Ref(T value) {
        Value = value;
    }

    public Ref() {
        Value = null;
    }
}
