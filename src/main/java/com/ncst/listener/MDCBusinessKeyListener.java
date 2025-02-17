package com.ncst.listener;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.ExecutionListener;
import org.slf4j.MDC;

/**
 * MDC work in thread-local
 * This class will create a bussiness key log automatically.
 */
public class MDCBusinessKeyListener implements ExecutionListener {

  @Override
  public void notify(DelegateExecution delegateExecution) throws Exception {
    String businessKey = delegateExecution.getProcessBusinessKey();
    MDC.put("businessKey", businessKey); // Store Business Key in MDC
  }
}
