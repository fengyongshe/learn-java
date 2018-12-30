package com.fys.learnjava.delayqueue.delaytask;

import org.junit.Test;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.TimeUnit;

public class DelayQueueTest {

  @Test
  public void testDelayTask() {
    DelayQueue<DelayTask> queue = new DelayQueue<>();
    queue.add(new DelayTask("Task-1", 3000L, TimeUnit.MILLISECONDS));
    queue.add(new DelayTask("Task-2", 1000L, TimeUnit.MILLISECONDS));
    queue.add(new DelayTask("Task-3", 2000L, TimeUnit.MILLISECONDS));

    System.out.println("Queue Task put Done");

    while(!queue.isEmpty()) {
      try {
        DelayTask task = queue.take();
        if(task != null) {
          System.out.println(task.name + ":" + System.currentTimeMillis());
        }
      } catch (InterruptedException ex) {
        ex.printStackTrace();
      }
    }
  }

}
