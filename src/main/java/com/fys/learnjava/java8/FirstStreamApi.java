package com.fys.samples;

import java.util.ArrayList;
import java.util.List;

public class FirstStreamApi {

  public static void main(String[] args) {
    List<Integer> testLists = new ArrayList<Integer>();
    testLists.add(4);
    testLists.add(10);
    testLists.add(1);
    testLists.stream().filter( (Integer a) -> a > 3).forEach(System.out::println);
  }
}
