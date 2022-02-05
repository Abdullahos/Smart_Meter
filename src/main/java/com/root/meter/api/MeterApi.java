package com.root.meter.api;

import com.root.meter.model.Meter;
import com.root.meter.service.MeterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/meter")
public class MeterApi {
    @Autowired
    public MeterService meterService;
    @PostMapping("/save")
    public ResponseEntity<Meter> save(@RequestBody Meter meter){
        Long meterID = meterService.save(meter);
        if( meterID!= null ){
            return ResponseEntity.status(201).build();
        }
        //TODO: implement the rest errors
        else{
            return ResponseEntity.internalServerError().build();
        }
    }
    @GetMapping("/get")
    public ResponseEntity<String> get(@RequestParam Long meterId){
        Meter meter = meterService.getByMeterId(meterId);
        return ResponseEntity.ok("id "+meter.getId());
    }
}
