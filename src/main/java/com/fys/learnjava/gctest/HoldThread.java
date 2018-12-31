package com.fys.learnjava.gctest;

import java.util.concurrent.CountDownLatch;

public class HoldThread extends Thread {

  CountDownLatch cd = new CountDownLatch(1);
  public HoldThread() {
    this.setDaemon(true);
  }
  public void run() {
    try {
      cd.await();
    } catch(InterruptedException e) {

    }
  }
}
