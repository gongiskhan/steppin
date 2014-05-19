package com.toptal.steppin.core.service;

import com.toptal.steppin.core.AbstractUserTest;
import com.toptal.steppin.core.model.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNull;

/**
 * Created by ggomes on 11/05/14.
 */
public class UserServiceTest extends AbstractUserTest {

    @Autowired
    private UserService userService;

    @Test
    @Rollback(true)
    public void testPersist() throws Exception{
        try{
            userService.delete(1L);
            userService.delete(2L);
        }catch (Exception ex){}

        userService.persist(user);

        User userFromDb = userService.fetchByEmail("bolinhos@gmail.com");
        assertEquals(user.getEmail(), userFromDb.getEmail());
        assertEquals(null, userFromDb.getPassword());
        assertEquals(user.getLogEntries().get(0).getDate(), userFromDb.getLogEntries().get(0).getDate());
    }

    @Test
    @Rollback(true)
    public void testDelete() throws Exception{
        userService.persist(user);
        userService.delete(userService.fetchByEmail("bolinhos@gmail.com").getId());
        assertNull(userService.fetch(1L));
        assertNull(userService.fetchByEmail("bolinhos@gmail.com"));
    }
}
