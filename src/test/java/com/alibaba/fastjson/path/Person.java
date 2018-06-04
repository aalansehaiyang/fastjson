package com.alibaba.fastjson.path;

import lombok.Data;

@Data
public class Person {

    Store store;

    @Data
    static class Store {

        Bicycle bicycle;
    }

    @Data
    static class Bicycle {

        String color;
        double price;

    }

    @Data
    static class Book {

        String author;
        double price;
        String category;
        String title;
    }
}
