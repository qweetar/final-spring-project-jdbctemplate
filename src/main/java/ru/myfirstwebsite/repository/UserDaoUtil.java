package ru.myfirstwebsite.repository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.myfirstwebsite.domain.to.User;

import java.util.Arrays;

import static ru.myfirstwebsite.domain.enums.Sex.FEMALE;

@Component
@Transactional(rollbackFor = DataIntegrityViolationException.class)
public class UserDaoUtil {


    @Autowired
    @Qualifier("userDaoImpl")
    private UserDao userDao;

    public void testOperations() {
        userDao.save(
                new User(100L, "test", "test", "test", "test", FEMALE,
                         "qweqwer", "qweqwe", true, "qweqwe")
        );

        User second = userDao.findById(2L);
        second.setSurname("testTransaction");
        User third = userDao.findById(3L);
        third.setSurname("12345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890");

        userDao.batchUpdate(Arrays.asList(second, third));
    }
}
