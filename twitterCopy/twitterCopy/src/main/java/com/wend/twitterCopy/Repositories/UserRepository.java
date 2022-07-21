package com.wend.twitterCopy.Repositories;

import com.wend.twitterCopy.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

}
