package com.fys.learnjava.delayqueue.runnable;

import org.junit.Test;

public class TestTaskRunner {

  @Test
  public void testTask() {
    TaskRunner runner = TaskRunner.getInstance();
    runner.put(6, new Runnable() {
      @Override
      public void run() {
        System.out.println("Runnable started , After 6 Seconds");
      }
    });
    runner.put(1, new Runnable() {
      @Override
      public void run() {
        System.out.println("Runnable started , After 1 Seconds");
      }
    });
    runner.init();
  }

}
