package com.example.luismauricio.weatherappedlio.data;

public interface Mapper<T, S> {
    S map(T t);
}
