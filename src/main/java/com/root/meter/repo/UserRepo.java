package com.root.meter.repo;

import com.root.meter.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepo extends CrudRepository<User,Long> {
    Optional<User> findByMeterId(Long meterId);
}
