package com.root.meter.service;

import com.root.meter.DTO.UserDTO;
import com.root.meter.Exception.ObjectNotFoundException;
import com.root.meter.model.Meter;
import com.root.meter.model.User;
import com.root.meter.repo.MeterRepo;
import com.root.meter.repo.UserRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private MeterRepo meterRepo;

    public User save(UserDTO userDTO){
        //TODO: add filter
        return userRepo.save(dtoToUser(userDTO));
    }

    public User findUserByMeterId(Long meterId){
        Optional<User> optionalUser = userRepo.findByMeterId(meterId);
        if(optionalUser.isPresent()){
            return optionalUser.get();
        }
        else {
            throw new ObjectNotFoundException("no user linked to this meter with id: "+meterId);
        }
    }
    public UserDTO userToDTO(User user){
        UserDTO dto = new UserDTO();
        BeanUtils.copyProperties(user, dto);
        //TODO: user && || meter may be null (check)
        dto.setMeterId(user.getMeter().getId());
        return dto;
    }
    public User dtoToUser(UserDTO dto){
        User user = new User();
        BeanUtils.copyProperties(dto, user);
        //TODO: dto may be null (check)
        Optional<Meter> optionalMeterById = meterRepo.findById(dto.getMeterId());
        if(optionalMeterById.isPresent()){
            user.setMeter(optionalMeterById.get());
            return user;
        }
        else {
            throw new ObjectNotFoundException("no such meter");
        }
    }
}
