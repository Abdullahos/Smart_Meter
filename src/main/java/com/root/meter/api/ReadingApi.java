package com.root.meter.api;

import com.root.meter.DTO.ReadingDTO;
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
    public ResponseEntity<Reading> save(@RequestBody ReadingDTO readingDTO){
        if(readingService.save(readingDTO) != null ){
            return ResponseEntity.ok().build();
        }
        else {
            return ResponseEntity.internalServerError().build();
        }
    }
    @GetMapping("/get")
    public List<ReadingDTO> getReadingById(Long meterId){
        return readingService.getReadingsByMeterId(meterId);
    }
    //TODO: implement the rest of GET methods
    /*
    @GetMapping("/get")

    public Reading getReadingByMonth(){

    }
    @GetMapping("/get")
    public Reading getReadingByYear(){

    }

     */
}
