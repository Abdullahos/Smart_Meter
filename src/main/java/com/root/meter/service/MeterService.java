package com.root.meter.service;

import com.root.meter.model.Meter;
import com.root.meter.repo.MeterRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MeterService {
    @Autowired
    private MeterRepo meterRepo;

    public Long save(Meter meter){
        return meterRepo.save(meter).getId();
    }

    public Meter getByMeterId(Long id){
        Optional<Meter> optionalMeterById = meterRepo.findById(id);
        if(optionalMeterById.isPresent())   return optionalMeterById.get();
        else return null;
    }
}
