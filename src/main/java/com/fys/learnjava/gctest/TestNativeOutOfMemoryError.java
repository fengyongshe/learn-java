package com.fys.learnjava.gctest;

public class TestNativeOutOfMemoryError {

  public static void main(String[] args) {
    for(int i = 0;;i++) {
      System.out.print("i = " + i);
      new Thread(new HoldThread()).start();
    }
  }
}
