package com.ncst.command;

import com.ncst.process.EventProcessor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * Why: Use command line runner to make sure - a thread working on this Responsibility: Creating
 * thread to check in DB the new event to proceed our logic.
 */
@Component
public class CamundaCommandLineRunner implements CommandLineRunner {

  private final EventProcessor eventProcessor;

  public CamundaCommandLineRunner(EventProcessor eventProcessor) {
    this.eventProcessor = eventProcessor;
  }

  @Override
  public void run(String... args) throws Exception {
    eventProcessor.startProcessing(); // Start processing tasks programmatically
  }
}
