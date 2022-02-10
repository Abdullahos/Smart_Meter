package com.root.meter.api;

import com.root.meter.DTO.DailyReadingDTO;
import com.root.meter.model.DailyReading;
import com.root.meter.service.ReadingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
@Transactional
@RestController
@RequestMapping("/reading")
public class ReadingApi {
    @Autowired
    private ReadingService readingService;
    @PostMapping("/post")
    public ResponseEntity<DailyReading> save(@RequestBody DailyReadingDTO dailyReadingDTO){
        DailyReading reading = readingService.save(dailyReadingDTO);
        if(reading != null){
            return new ResponseEntity<DailyReading>(reading, HttpStatus.CREATED);
        }
        else {
            return ResponseEntity.internalServerError().build();
        }
    }

    /**
     * return all readings by meter id
     * @param meterId
     * @return
     */
    @GetMapping("/get")
    public List<DailyReadingDTO> getReadingById(@RequestParam Long meterId){
        return readingService.getReadingsByMeterId(meterId);
    }

    /**
     * retrieve all readings of given hour
     * @return
     */
    @GetMapping("/get/period")
    public List<DailyReadingDTO> getReadingBetweenTwoTimeStamps(
            @RequestParam("start") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate start,
            @RequestParam("end") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate end){


        return  readingService.getReadingsBetween2TimeStamps(start, end);
    }
    //TODO: implement the rest of GET methods
    /*

    @GetMapping("/get")
    public Reading getReadingByYear(){

    }

     */
}
