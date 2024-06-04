package com.revature.repository;

import com.revature.models.User;
import com.revature.models.UsernamePasswordAuthentication;
import com.revature.utilities.ConnectionUtil;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.SQLException;

public class UserDaoTest {

    private static final String validUsername = "Batman";
    private static final String invalidUsername = "Robin";

    // Due to the class limitation of not having a full args constructor I need to initialize
    // this object when the testing starts. I only need to do this once so it is a good candidate for
    // a "BeforeClass" method
    private static UsernamePasswordAuthentication createUserPositiveData;
    private static UsernamePasswordAuthentication createUserNegativeData;

    private UserDao dao;

    @BeforeClass
    public static void initializeTestData(){
        ConnectionUtil.resetTestDatabase();

        createUserPositiveData = new UsernamePasswordAuthentication();
        createUserPositiveData.setUsername("thisisaverage12");
        createUserPositiveData.setPassword("thisisaverage12");

        createUserNegativeData = new UsernamePasswordAuthentication();
        createUserNegativeData.setUsername("thisshouldbethirtycharacters+12");
        createUserNegativeData.setPassword("thisshouldbethirtycharacters+12");
    }

    @Before
    public void setup(){
        dao = new UserDao();
    }

    @Test
    public void getUserByUsernamePositiveTest(){
        User user = dao.getUserByUsername(validUsername);
        Assert.assertEquals(validUsername, user.getUsername());
    }

    @Test
    public void getUserByUsernameNegativeTest(){
        User nullUser = dao.getUserByUsername(invalidUsername);
        Assert.assertNull(nullUser);
    }

    @Test
    public void createUserPositive(){
        User newUser = dao.createUser(createUserPositiveData);
        Assert.assertNotEquals(0, newUser.getId());
    }

    /*
        NOTE: database does not currently prevent usernames that are too long from being added to the table,
              so I am going to leave this test in place as a reminder that, for the sake of redundant security,
              it would be good to go back into the database at a later time to add a constraint on the table
              so that if something goes wrong with development of the Service layer there is still a check
              being made so that usernames that are invalid are not allowed in the database

        NOTE AGAIN: the User table appears to have no constraints on the username column. This should be rectified
                    to having a unique and length check constraint added
     */
    @Test
    public void createUserNegative(){
        User emptyUser = dao.createUser(createUserNegativeData);
        Assert.assertEquals(0, emptyUser.getId());
    }

}
