package com.root.meter.service;

import com.root.meter.DTO.DailyReadingDTO;
import com.root.meter.model.DailyReading;
import com.root.meter.model.User;
import com.root.meter.repo.ReadingRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Transactional
@Service
public class ReadingService {
    @Autowired
    private ReadingRepo readingRepo;
    @Autowired
    private MeterService meterService;
    @Autowired
    private UserService userService;
    @Autowired
    JdbcTemplate jdbcTemplate;

    /**
     * method to save the reading dto
     * @param dailyReadingDTO
     * @return reading id
     */
    public DailyReading save(DailyReadingDTO dailyReadingDTO) {
        //get the state price of the user of that meter
        User userByMeterId = userService.findUserByMeterId(dailyReadingDTO.getMeterId());
        //TODO:should i check of null for useByMeterID although if not exist the user?
        // service should throw not found exception??
        String userState = userByMeterId.getState();
        //get the state KWH price in cents
        Double statePricePerKWH = jdbcTemplate.queryForObject(
                "select price from KWStatesPrices where state = ?",
                Double.class,
                userState
        );
        //calculate the total price in (cents) of this reading
        double amount = dailyReadingDTO.getEnergy() * statePricePerKWH;
        double energy = dailyReadingDTO.getEnergy();
        DailyReading dailyReading = dtoToReading(dailyReadingDTO);
        //check if this reading is the first reading in the day
        Optional<DailyReading> optionalReading = readingRepo.findAllByMeterIdAndDate(dailyReadingDTO.getMeterId(), dailyReadingDTO.getDate());
        //check if this reading isn't the first reading today
        if (optionalReading.isPresent()) {
            DailyReading accumulativeTodayReading = optionalReading.get();
            //add the old reading to the new
            amount += accumulativeTodayReading.getAmount();
            energy += accumulativeTodayReading.getEnergy();
            dailyReading.setId(accumulativeTodayReading.getId());
        }
        //set the new energy and amount
        dailyReading.setAmount(amount);
        dailyReading.setEnergy(energy);
        //save/update the reading
        return readingRepo.save(dailyReading);
    }

    public List<DailyReadingDTO> getReadingsByMeterId(Long meterId){
        Optional<List<DailyReading>> optReadingList = readingRepo.findAllByMeterId(meterId);
        if(optReadingList.isPresent()){
            List<DailyReadingDTO> dailyReadingDTOS = new ArrayList<>();
            List<DailyReading> dailyReadings = optReadingList.get();
            //TODO: search more on lambda expression
            dailyReadings.forEach(dailyReading -> dailyReadingDTOS.add(readingToDto(dailyReading)));
            return dailyReadingDTOS;
        }
        else return new ArrayList<>();
    }

    public DailyReading dtoToReading(DailyReadingDTO dailyReadingDTO){
        DailyReading dailyReading = new DailyReading();
        BeanUtils.copyProperties(dailyReadingDTO, dailyReading);
        dailyReading.setMeter(meterService.findById(dailyReadingDTO.getMeterId()));
        return dailyReading;
    }

    public DailyReadingDTO readingToDto (DailyReading dailyReading){
        DailyReadingDTO dailyReadingDTO = new DailyReadingDTO();
        BeanUtils.copyProperties(dailyReading, dailyReadingDTO);
        //set meterId in readingDto
        dailyReadingDTO.setMeterId(dailyReading.getMeter().getId());
        return dailyReadingDTO;
    }

    public List<DailyReadingDTO> getReadingsBetween2TimeStamps(LocalDate start, LocalDate end) {
        Optional<List<DailyReading>> optReadingList = readingRepo.findAllByDateBetween(start, end);
        if(optReadingList.isPresent()){
            List<DailyReadingDTO> dailyReadingDTOS = new ArrayList<>();
            List<DailyReading> dailyReadings = optReadingList.get();
            //TODO: search more on lambda expression
            dailyReadings.forEach(dailyReading -> dailyReadingDTOS.add(readingToDto(dailyReading)));
            return dailyReadingDTOS;
        }
        else return new ArrayList<>();
    }
}
