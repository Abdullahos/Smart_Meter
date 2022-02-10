package com.root.meter.repo;

import com.root.meter.model.User;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.Optional;
@Transactional
public interface UserRepo extends CrudRepository<User,Long> {
    Optional<User> findByMeterId(Long meterId);
}
