package com.root.meter.api;

import com.root.meter.model.Reading;
import com.root.meter.service.ReadingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reading")
public class ReadingApi {
    @Autowired
    private ReadingService readingService;
    @PostMapping("/post")
    public ResponseEntity<Reading> save(@RequestBody Reading reading){
        if(readingService.save(reading) != null ){
            return ResponseEntity.ok().build();
        }
        else {
            return ResponseEntity.internalServerError().build();
        }
    }
    @GetMapping("/get")
    public List<Reading> getReadingById(Long meterId){
        return readingService.getReadingsByMeterId(meterId);
    }
    /*
    @GetMapping("/get")

    public Reading getReadingByMonth(){

    }
    @GetMapping("/get")
    public Reading getReadingByYear(){

    }

     */
}
