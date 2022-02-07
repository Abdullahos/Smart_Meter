package com.root.meter.api;

import com.root.meter.DTO.ReadingDTO;
import com.root.meter.model.Reading;
import com.root.meter.service.ReadingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("/reading")
public class ReadingApi {
    @Autowired
    private ReadingService readingService;
    @PostMapping("/post")
    public ResponseEntity<Reading> save(@RequestBody ReadingDTO readingDTO){
        if(readingService.save(readingDTO) != null ){
            return ResponseEntity.ok().build();
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
    public List<ReadingDTO> getReadingById(@RequestParam Long meterId){
        return readingService.getReadingsByMeterId(meterId);
    }

    /**
     * retrieve all readings of given hour
     * @return
     */
    @GetMapping("/get/period")
    public List<ReadingDTO> getReadingBetweenTwoTimeStamps(@RequestParam String start, @RequestParam String end){
        DateTimeFormatter dateTimeFormat =
                DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSX");

        //Next parse the date from the @RequestParam, specifying the TO type as a TemporalQuery:
        LocalDateTime startDate = dateTimeFormat.parse(start, LocalDateTime::from);
        LocalDateTime endDate = dateTimeFormat.parse(end, LocalDateTime::from);
        return  readingService.getReadingsByHour(startDate, endDate);
    }
    //TODO: implement the rest of GET methods
    /*

    @GetMapping("/get")
    public Reading getReadingByYear(){

    }

     */
}
