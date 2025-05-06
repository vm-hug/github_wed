package com.demo.account.repository;

import com.demo.account.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User , Long> {
        public User findByUsername(String username);
}
