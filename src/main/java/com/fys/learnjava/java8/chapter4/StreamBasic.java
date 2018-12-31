package com.fys.samples.chapter4;

import java.util.Comparator;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;

public class StreamBasic {

  public static void main(String[] args) {
   /* Dish.menu.stream()
        .filter(d -> d.getCalories() < 400)
        .sorted(Comparator.comparing(Dish::getCalories))
        .map(Dish::getName)
        .collect(toList())
        .forEach(System.out:: println);*/

    Map<Dish.Type,List<Dish>> dishedsByType
        = Dish.menu.stream().collect(groupingBy(Dish::getType));

  }
}
