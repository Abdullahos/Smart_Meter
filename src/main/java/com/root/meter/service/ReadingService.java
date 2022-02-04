package com.root.meter.service;

import com.root.meter.model.Reading;
import com.root.meter.repo.ReadingRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ReadingService {
    @Autowired
    private ReadingRepo readingRepo;
    public List<Reading> getReadingsByMeterId(Long meterId){
        Optional<List<Reading>> optReadingList = readingRepo.findAllByMeterId(meterId);
        if(optReadingList.isPresent()){
            return optReadingList.get();
        }
        else return new ArrayList<>();
    }
    public Long save(Reading reading){
        return readingRepo.save(reading).getId();
    }
}
