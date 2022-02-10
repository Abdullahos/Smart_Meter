package com.root.meter.repo;

import com.root.meter.model.DailyReading;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
@Transactional
@Repository
public interface ReadingRepo extends CrudRepository<DailyReading, Long> {
    Optional<List<DailyReading>> findAllByMeterId(Long meterId);

    Optional<List<DailyReading>> findAllByDateBetween(LocalDate start, LocalDate end);

    Optional<DailyReading> findAllByMeterIdAndDate(Long meterId, LocalDate date);
}
