package com.fys.samples.chapter7;

import java.util.concurrent.ForkJoinPool;

public class ParallelStreamsTest {

  public static final ForkJoinPool FORK_JOIN_POOL = new ForkJoinPool();

  public static void main(String[] args) {
    System.out.println("Result:" + ForkJoinSumCalculator.forkJoinSum(10_000_000));
  }
}
