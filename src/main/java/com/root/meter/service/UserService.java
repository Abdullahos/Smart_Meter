package com.root.meter.service;

import com.root.meter.DTO.UserDTO;
import com.root.meter.Exception.ObjectNotFoundException;
import com.root.meter.model.Meter;
import com.root.meter.model.Users;
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
    public Users save(UserDTO userDTO){
        Users user = dtoToUser(userDTO);
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
    public Users findUserByMeterId(Long meterId){
        Optional<Users> optionalUser = userRepo.findByMeterId(meterId);
        if(optionalUser.isPresent()){
            return optionalUser.get();
        }
        else {
            throw new ObjectNotFoundException("no user linked to this meter with id: "+meterId);
        }
    }
    //convert user to userDto
    public UserDTO userToDTO(Users user){
        UserDTO dto = new UserDTO();
        BeanUtils.copyProperties(user, dto);
        //TODO: user && || meter may be null (check)
        dto.setMeterId(user.getMeter().getId());
        return dto;
    }
    public Users dtoToUser(UserDTO dto){
        //TODO: dto may be null (check)
        Users user = new Users();
        BeanUtils.copyProperties(dto, user);
        return user;
    }

    public Users findById(Long id) {
        return userRepo.findById(id).get();
    }
}
