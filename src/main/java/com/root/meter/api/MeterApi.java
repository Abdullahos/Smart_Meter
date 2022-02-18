package com.root.meter.api;

import com.root.meter.model.Meter;
import com.root.meter.service.MeterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/meter")
public class MeterApi {
    @Autowired
    public MeterService meterService;

    @PostMapping("/save")
    public ResponseEntity<Long> save(@Valid @RequestBody Meter meter){
        Long meterID = meterService.save(meter);
        if( meterID!= null ){
            return new ResponseEntity<Long>(meterID, HttpStatus.CREATED);
        }
        //TODO: implement the rest errors
        else{
            return ResponseEntity.internalServerError().build();
        }
    }
    @GetMapping("/get")
    public ResponseEntity<Meter> get(@RequestParam Long meterId){
        Meter meter = meterService.findById(meterId);
        return ResponseEntity.ok(meter);
    }
}
