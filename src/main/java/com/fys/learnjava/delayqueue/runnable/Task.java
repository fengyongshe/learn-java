package com.fys.learnjava.delayqueue.runnable;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

public class Task<T extends Runnable>  implements Delayed {

  private final long time;
  private final T task;
  private static final AtomicLong atomic = new AtomicLong(0);
  private final long n;

  public Task(long timeout, T t) {
    this.time = System.nanoTime() + timeout;
    this.task = t;
    this.n = atomic.getAndIncrement();
  }

  @Override
  public long getDelay(TimeUnit unit) {
    return unit.convert(this.time - System.nanoTime(), TimeUnit.NANOSECONDS);
  }

  @Override
  public int compareTo(Delayed other) {
    if(other == this) return 0;
    if (other instanceof  Task) {
      Task x = (Task) other;
      long diff = time - x.time;
      if (diff < 0) return -1;
      else if (diff > 0) return 1;
      else if (n < x.n) return -1;
      else return 1;
    }
    long d = (getDelay(TimeUnit.NANOSECONDS )- other.getDelay(TimeUnit.NANOSECONDS));
    return (d == 0) ? 0: ((d<0) ? -1: 1);
  }

  public T getTask() {
    return this.task;
  }

}
