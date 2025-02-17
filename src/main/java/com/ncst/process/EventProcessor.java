package com.ncst.process;

import com.ncst.delegates.HelloWorld;
import com.ncst.entity.Event;
import com.ncst.entity.Status;
import com.ncst.repositories.EventRepository;
import java.util.UUID;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.ProcessEngines;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.runtime.ProcessInstantiationBuilder;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class EventProcessor {

  private final EventRepository eventRepository;

  public EventProcessor(EventRepository eventRepository) {
    this.eventRepository = eventRepository;
  }

  public void startProcessing() {
    Executors.newSingleThreadScheduledExecutor()
        .scheduleAtFixedRate(this::processEvent, 0, 2, TimeUnit.SECONDS);
  }

  private void processEvent() {
    try {
      Event event = eventRepository.findFirstByStatusOrderByCreationDateAsc(Status.INIT);
      if (event != null) {
        ProcessEngine engine = ProcessEngines.getDefaultProcessEngine();
        ProcessInstantiationBuilder instance = engine.getRuntimeService()
            .createProcessInstanceByKey("camunda-app-process");
        instance.setVariable(HelloWorld.VARIABLE_NUMBER, 1);
        instance.businessKey("ORDER-CODE-111"); // business key is code that like traceID, multiple process and jobs (tracing bw db and camunda and external system)
        ProcessInstance processInstance = instance.execute();
        event.setStatus(Status.IN_PROGRESS);
        eventRepository.save(event);
        log.info("Process instance started for event: {} and process instance id: {} and business key {}", event.getId(), processInstance.getId(), processInstance.getBusinessKey());
      }
    } catch (Exception e) {
      // Handle error appropriately (log it, retry, etc.)
      log.error(e.getMessage(), e);
    }
  }
}

