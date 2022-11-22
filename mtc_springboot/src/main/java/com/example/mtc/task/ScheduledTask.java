package com.example.mtc.task;

import com.example.mtc.service.HealthMedicationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@SuppressWarnings("ALL")
public class ScheduledTask {
  @Autowired
  private HealthMedicationService medicationService;

  @Scheduled(cron = "0 0 0 ? * *")
  public void medicationFlush() {
    medicationService.flushAll();
    log.info("medication flush");
  }
}
