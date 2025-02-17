package com.ncst.delegates;

import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.ExternalTaskService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

@Slf4j
public abstract class HelloWorld implements ExternalTaskService {
  public static final String VARIABLE_NUMBER = "number";

  @Override
  public void execute(DelegateExecution delegateExecution) throws Exception {
    DelegateExecution supperexe = delegateExecution.getSuperExecution();
    if(supperexe != null) {
      log.info("Supper Process ID" + supperexe.getProcessInstanceId());
    }
    log.info("definitionId: " + delegateExecution.getProcessDefinitionId());
    Thread.sleep(2000); //process of this job
    Object object = delegateExecution.getVariable(VARIABLE_NUMBER);
    if (object != null) {
      Integer number = (Integer) delegateExecution.getVariable(VARIABLE_NUMBER);
      log.info("Greeting: " + number);
      delegateExecution.setVariable(VARIABLE_NUMBER, number + 1);
    } else {
      log.info(delegateExecution.getProcessBusinessKey());
      log.info("Greeting: " + object + " and parent id = "+delegateExecution.getParentId() + " and parent activity instance :" + delegateExecution.getParentActivityInstanceId());
    }
  }
}
