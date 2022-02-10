package com.root.meter.repo;

import com.root.meter.model.Meter;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Transactional
@Repository
public interface MeterRepo extends CrudRepository<Meter, Long > {
}
