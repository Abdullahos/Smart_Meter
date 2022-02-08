package com.root.meter.repo;

import com.root.meter.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepo extends CrudRepository<User,Long> {
}
