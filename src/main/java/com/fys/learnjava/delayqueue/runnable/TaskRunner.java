package com.fys.learnjava.delayqueue.runnable;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class TaskRunner {

  Executor executor = Executors.newFixedThreadPool(20);
  private Thread taskThread ;
  private DelayQueue<Task> queue = new DelayQueue<>();

  public void init() {
    taskThread = new Thread(() -> execute());
    taskThread.setDaemon(true);
    taskThread.setName("Task Queue Daemon Thread");
    taskThread.start();
  }

  public void execute() {
    System.out.println("Start: " + System.currentTimeMillis());
    while(true) {
      try {
        Task t1 = queue.take();
        Runnable task = t1.getTask();
        if(t1 == null) {
          continue;
        }
        executor.execute(task);
        System.out.println("[at task:" + task + "]   [Time:" + System.currentTimeMillis() + "]");
      } catch (Exception ex) {
        System.out.println(ex.getCause());
      }
    }
  }

  public void put(long time, Runnable task) {
    long nanoTime = TimeUnit.NANOSECONDS.convert(time, TimeUnit.MILLISECONDS);
    System.out.println("Put Task at "+ time);
    Task k = new Task(nanoTime, task);
    queue.put(k);
  }

  public boolean endTask(Task<Runnable> task) {
    return queue.remove(task);
  }

  private TaskRunner() { }

  private static class LazyHolder {
    private static TaskRunner taskRunner = new TaskRunner();
  }

  public static TaskRunner getInstance() {
    return LazyHolder.taskRunner;
  }

}
