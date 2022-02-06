package com.root.meter.service;

import com.root.meter.DTO.ReadingDTO;
import com.root.meter.model.Reading;
import com.root.meter.repo.MeterRepo;
import com.root.meter.repo.ReadingRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ReadingService {
    @Autowired
    private ReadingRepo readingRepo;
    @Autowired
    private MeterRepo meterRepo;

    public Long save(ReadingDTO readingDTO){
        return readingRepo.save(dtoToReading(readingDTO)).getId();
    }

    public List<ReadingDTO> getReadingsByMeterId(Long meterId){
        Optional<List<Reading>> optReadingList = readingRepo.findAllByMeterIdId(meterId);
        if(optReadingList.isPresent()){
            List<ReadingDTO>readingDTOS = new ArrayList<>();
            List<Reading> readings = optReadingList.get();
            //TODO: search more on lambda expression
            readings.forEach(reading -> readingDTOS.add(readingToDto(reading)));
            return readingDTOS;
        }
        else return new ArrayList<>();
    }

    public Reading dtoToReading(ReadingDTO readingDTO){
        Reading reading = new Reading();
        BeanUtils.copyProperties(readingDTO, reading);
        reading.setMeterId(meterRepo.findById(readingDTO.getMeterId()).get());
        return reading;
    }

    public ReadingDTO readingToDto (Reading reading){
        ReadingDTO readingDTO = new ReadingDTO();
        BeanUtils.copyProperties(reading,readingDTO);
        //set meterId in readingDto
        readingDTO.setMeterId(reading.getMeterId().getId());
        return readingDTO;
    }

}
