package com.root.meter.repo;

import com.root.meter.model.Reading;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface ReadingRepo extends CrudRepository<Reading, Long> {
    Optional<List<Reading>> findAllByMeterIdId(Long meterId);

    Optional<List<Reading>> findAllByTimeStampBetween(LocalDateTime start, LocalDateTime end);
}
