package com.fys.samples.chapter6;

import com.fys.samples.chapter4.Dish;

import static java.util.stream.Collectors.*;

public class Grouping {

  public static void main(String[] args) {

    System.out.println("Grouping by Type:" + Dish.menu.stream().collect(
        groupingBy(Dish::getType)
    ));

    System.out.println("Dish names grouped by type::" + Dish.menu.stream().collect(
        groupingBy(Dish::getType, mapping(Dish::getName, toList()))
    ));

    System.out.println("Dishes partitioned by type::" + Dish.menu.stream().collect(
        partitioningBy(Dish::isVegetarian)
    ));

    System.out.println("Total calories in menu: " + Dish.menu.stream().collect(
        reducing(0, Dish::getCalories, Integer::sum)
    ));

    System.out.println("Dish SummaryStatistics " + Dish.menu.stream().collect(
        summarizingInt(Dish::getCalories)
    ));
  }
}
