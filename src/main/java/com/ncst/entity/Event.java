package com.ncst.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import java.sql.Timestamp;
import java.time.OffsetDateTime;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Event {
  @Id
  private UUID id;

  @Enumerated(EnumType.STRING)
  private Status status;

  private OffsetDateTime creationDate;
}
