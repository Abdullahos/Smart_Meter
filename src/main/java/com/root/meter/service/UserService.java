package com.root.meter.service;

import com.root.meter.DTO.UserDTO;
import com.root.meter.Exception.ObjectNotFoundException;
import com.root.meter.model.Meter;
import com.root.meter.model.User;
import com.root.meter.repo.UserRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;
@Transactional
@Service
public class UserService {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private MeterService meterService;

    /**
     * create user and meter and assign them to each other
     * @param userDTO
     * @return the saved user
     */
    public User save(UserDTO userDTO){
        User user = dtoToUser(userDTO);
        //assign user to meter
        Meter meter = new Meter(user);
        //assign meter to user
        user.setMeter(meter);
        //save user
        meterService.save(meter);
        //save and return user
        return userRepo.save(user);
    }

    /**
     * find user by meter id
     * @param meterId
     * @return the user of that meter id or throw Object Not Found
     */
    public User findUserByMeterId(Long meterId){
        Optional<User> optionalUser = userRepo.findByMeterId(meterId);
        if(optionalUser.isPresent()){
            return optionalUser.get();
        }
        else {
            throw new ObjectNotFoundException("no user linked to this meter with id: "+meterId);
        }
    }
    //convert user to userDto
    public UserDTO userToDTO(User user){
        UserDTO dto = new UserDTO();
        BeanUtils.copyProperties(user, dto);
        //TODO: user && || meter may be null (check)
        dto.setMeterId(user.getMeter().getId());
        return dto;
    }
    public User dtoToUser(UserDTO dto){
        //TODO: dto may be null (check)
        User user = new User();
        BeanUtils.copyProperties(dto, user);
        return user;
    }
}
