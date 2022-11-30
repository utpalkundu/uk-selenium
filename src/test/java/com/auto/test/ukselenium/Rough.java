package com.auto.test.ukselenium;

import com.auto.test.ukselenium.restUtilities.RestAssuredUtils;
import com.google.common.collect.Iterables;

import java.sql.SQLOutput;
import java.time.Instant;
import java.util.List;
import java.util.OptionalInt;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.stream.IntStream;

public class Rough {

    public static void main(String[] args) {

        System.out.println(System.getProperty("user.dir"));

        System.out.println(Instant.now().getEpochSecond());


    List<String> list = List.of("foo", "bar", "baz");

        OptionalInt index = IntStream.range(0, list.size())
                .filter(i -> list.get(i).equals("bar")).findFirst();

        System.out.println(list.get(index.getAsInt()));

        System.out.println(Iterables.indexOf(list, u -> u.equals("bar")));








    }
}
