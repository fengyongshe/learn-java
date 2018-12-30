package com.fys.learnjava.delayqueue.delaytask;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class DelayTask implements Delayed {

  public String name;
  public Long delayTime;
  public TimeUnit delayTimeUnit;
  public Long executeTime;

  public DelayTask(String name, long delayTime, TimeUnit delayTimeUnit ) {
    this.name = name;
    this.delayTime = delayTime;
    this.delayTimeUnit = delayTimeUnit;
    this.executeTime = System.currentTimeMillis() + delayTimeUnit.toMillis(delayTime);
  }

  @Override
  public long getDelay(TimeUnit unit) {
    return unit.convert(executeTime - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
  }

  @Override
  public int compareTo(Delayed o) {
    if(this.getDelay(TimeUnit.MILLISECONDS) > o.getDelay(TimeUnit.MILLISECONDS)) {
      return 1;
    }else if(this.getDelay(TimeUnit.MILLISECONDS) < o.getDelay(TimeUnit.MILLISECONDS)) {
      return -1;
    }
    return 0;
  }
}
