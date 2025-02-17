package com.ncst.repositories;

import com.ncst.entity.Event;
import com.ncst.entity.Status;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends JpaRepository<Event, UUID> {
  Event findFirstByStatusOrderByCreationDateAsc(Status status);
}
