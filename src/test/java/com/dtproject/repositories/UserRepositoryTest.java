package com.dtproject.repositories;

import com.dtproject.configuration.AppConfig;
import com.dtproject.models.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppConfig.class)
@WebAppConfiguration
public class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @Test
    public void findByUsername() throws Exception {

        User user = new User("admin", "admin");
        user = userRepository.save(user);

        User user2 = new User("lol", "lol");
        user2 = userRepository.save(user2);

        User result = userRepository.findByUsername("admin");
        Assert.assertEquals(user.getUsername(), result.getUsername());


    }

}