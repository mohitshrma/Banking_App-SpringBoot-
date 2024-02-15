package com.bankapplication.thejavaacademybank.respository;

import com.bankapplication.thejavaacademybank.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {

    Boolean existsByEmail(String email);

}
