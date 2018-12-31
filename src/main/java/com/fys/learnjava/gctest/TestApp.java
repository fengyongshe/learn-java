package com.fys.learnjava.gctest;

import io.dropwizard.Application;
import io.dropwizard.Configuration;
import io.dropwizard.setup.Environment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class TestApp extends Application<Configuration> {

  private static final Logger log = LoggerFactory.getLogger(TestApp.class);

  private static int threadIndex = 0;

  public static void main(String[] args) throws Exception {
    new TestApp().run(args);
  }

  @Override
  public void run(Configuration config, Environment env) throws Exception {
    final ScheduledExecutorService scheduler =
        env.lifecycle().scheduledExecutorService("mem-gc-test").build();

    scheduler.scheduleAtFixedRate(thrasher(), 1, 100, TimeUnit.SECONDS);
  }

  private Runnable thrasher() {
    return new Runnable() {
      private int count = 0;

      @Override
      public void run() {
        log.info("Running thrasher..");
        log.info("Thread index: "+ threadIndex);
        threadIndex++;
        final List<String> messages = new ArrayList<>();

        //400 000 000  => 64G
        //800 000 000 => 128G
        for (int i = 0; i < 800_000_000; i++) {
          messages.add("yoz_" + i);
        }

        log.info("Created {} messages", messages.size());

        if (++count == 18) {
          log.info("18 runs complete - exiting");

          System.exit(0);
        }
      }
    };
  }
}