package com.API.springboot.restapi.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserDetailsCommandLineRunner implements CommandLineRunner {

    private Logger logger = LoggerFactory.getLogger(getClass());

    private UserDetailsRepository userDao;

    public UserDetailsCommandLineRunner(UserDetailsRepository userDao) {
        this.userDao = userDao;
    }

    @Override
    public void run(String... args) throws Exception {
        userDao.save(new UserDetails("Rodrigo","Admin"));
        userDao.save(new UserDetails("Marquez","Admin"));
        userDao.save(new UserDetails("David","Admin"));

        List<UserDetails> users = userDao.findAll();

        users.forEach(user -> logger.info(user.toString()));



    }
}
