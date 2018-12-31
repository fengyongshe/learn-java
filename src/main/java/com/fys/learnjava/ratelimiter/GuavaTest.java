package com.fys.learnjava.ratelimiter;

import com.google.common.util.concurrent.RateLimiter;

public class GuavaTest {

  public static void main(String[] args) {
    RateLimiter rateLimiter = RateLimiter.create(2);
    System.out.println("Start Time:" + System.currentTimeMillis());
    System.out.println(rateLimiter.acquire(5));
    System.out.println("Start Time:" + System.currentTimeMillis());
    System.out.println(rateLimiter.acquire(2));
    System.out.println("Start Time:" + System.currentTimeMillis());
    System.out.println(rateLimiter.acquire(1));
    System.out.println("Start Time:" + System.currentTimeMillis());
  }

}
