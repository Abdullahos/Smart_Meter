package com.root.meter.repo;

import com.root.meter.model.Users;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.Optional;
@Transactional
public interface UserRepo extends CrudRepository<Users,Long> {
    Optional<Users> findByMeterId(Long meterId);
}
