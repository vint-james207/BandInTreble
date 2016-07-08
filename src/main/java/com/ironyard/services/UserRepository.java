package com.ironyard.services;

import com.ironyard.entities.User;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by jeffryporter on 7/7/16.
 */
public interface UserRepository extends CrudRepository<User, Integer>
{
    public User findFirstByName(String name);
}
