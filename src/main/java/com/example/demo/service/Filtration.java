package com.example.demo.service;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Filtration {



    public static <T> List<String> getListString(List<T> obj) {
        if (obj.isEmpty()) return new ArrayList<>();
        Class<?> clazz = obj.get(0).getClass();
        return obj.stream()
                .map(item -> {
                    try {
                        Field field = clazz.getDeclaredField("id");
                        field.setAccessible(true);
                        Object fieldValue = field.get(item);
                        return fieldValue != null ? fieldValue.toString() : "null";
                    } catch (NoSuchFieldException | IllegalAccessException e) {
                        e.printStackTrace();
                        return "Error: " + e.getMessage();
                    }
                })
                .collect(Collectors.toList());
    }


}
