package com.fys.samples.chapter2;

import com.fys.samples.Apple;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

public class FilteringApples {

  public static void main(String[] args) {
    List<Apple> inventory = Arrays.asList(new Apple(80,"green"),
        new Apple(155, "green"),
        new Apple(120, "red"));
    //Print filter Apples
    List<Apple> redApples = filterApples(inventory, (Apple apple) -> "red".equals(apple.getColor()));
    //System.out.println(redApples);
    redApples.forEach(System.out::println);
    //Sort Apps
    inventory.sort((Apple a1, Apple a2) -> a1.getWeight().compareTo(a2.getWeight()));
    inventory.forEach(System.out::println);
  }

  public static List<Apple> filterApples(List<Apple> inventory, Predicate<Apple> p) {
    List<Apple> result = new ArrayList<>();
    for(Apple apple: inventory) {
      if(p.test(apple)) {
        result.add(apple);
      }
    }
    return result;
  }
}
