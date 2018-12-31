package com.fys.samples.chapter3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class FunctionTest {

  public static void main(String[] args) {
    Converter<String,Integer> converter =
                    (from) -> Integer.parseInt(from);
    Integer integer = converter.convert("231");

    //TEST Consumer Interface
    happy(100, (m) -> System.out.println("Consume:" + m + " $$"));

    //Test Supplier
    List<Integer> numList = getNumList(3, () -> (int)(Math.random() * 10));
    numList.forEach(System.out::println);

    //Test Function
    String subStr = strHandler("Hello Function", (str) -> str.substring(2,5));
    System.out.println(subStr);

    //Test Predicate
    List<String> list = Arrays.asList("Hello","Lambda", "Funny");
    List<String> result = filterStr(list, (s) -> s.length() > 5);
    result.forEach(System.out::println);
  }

  public static void happy(double money, Consumer<Double> con) {
    con.accept(money);
  }

  public static List<Integer> getNumList(int num, Supplier<Integer> sup) {
    List<Integer> list = new ArrayList<>();
    for(int i = 0 ;i < num;  i++){
      Integer n = sup.get();
      list.add(n);
    }
    return list;
  }

  public static String strHandler(String str, Function<String,String> fun) {
    return fun.apply(str);
  }

  public static List<String> filterStr(List<String> list, Predicate<String> pre) {
    List<String> result = new ArrayList<>();
    for(String str : list) {
      if(pre.test(str)) {
        result.add(str);
      }
    }
    return result;
  }
}
