package com.revature.service;

import com.revature.models.User;
import com.revature.models.UsernamePasswordAuthentication;
import com.revature.repository.UserDao;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

public class UserServiceTest {

    private static UsernamePasswordAuthentication batmanCredentials;
    private static User batman;
    private static User newUserCredentials;
    private static UsernamePasswordAuthentication newUserCredentialsForDao;
    private static User newUserMockData;

    private static User usernameTooLongCreds;
    private static User passwordTooLongCreds;
    private static User usernamePasswordTooLongCreds;

    private static User usernameTakenCreds;

    private UserDao dao;

    private UserService service;

    @BeforeClass
    public static void initializeTestData(){
        batmanCredentials = new UsernamePasswordAuthentication();
        batmanCredentials.setUsername("Batman");
        batmanCredentials.setPassword("I am the night");

        batman = new User();
        batman.setId(1);
        batman.setUsername("Batman");
        batman.setPassword("I am the night");

        newUserCredentials = new User();
        newUserCredentials.setUsername("validUsername");
        newUserCredentials.setPassword("validPassword");

        newUserCredentialsForDao = new UsernamePasswordAuthentication();
        newUserCredentialsForDao.setUsername(newUserCredentials.getUsername());
        newUserCredentialsForDao.setPassword(newUserCredentials.getPassword());

        newUserMockData = new User();
        newUserMockData.setId(1);
        newUserMockData.setUsername(newUserCredentialsForDao.getUsername());
        newUserMockData.setPassword(newUserCredentialsForDao.getPassword());

        usernameTooLongCreds = new User();
        usernameTooLongCreds.setUsername("thisshouldbethirtycharacters+12");
        usernameTooLongCreds.setPassword("validPassword");

        passwordTooLongCreds = new User();
        passwordTooLongCreds.setUsername("validUsername");
        passwordTooLongCreds.setPassword("thisshouldbethirtycharacters+12");

        usernamePasswordTooLongCreds = new User();
        usernamePasswordTooLongCreds.setUsername("thisshouldbethirtycharacters+12");
        usernamePasswordTooLongCreds.setPassword("thisshouldbethirtycharacters+12");

        usernameTakenCreds = new User();
        usernameTakenCreds.setUsername("Batman");
        usernameTakenCreds.setPassword("whatever under 30");
    }

    @Before
    public void setup(){
        dao = Mockito.mock(UserDao.class);
        service = new UserService(dao);
    }

    /*
        NOTE: Due to the authenticate method currently only passing data from the API to the Repository
        Layer there is not much we can "test" with the authenticate method. If you want to add an integration
        test just to make sure that data is actually being passed into the repository layer you can make
        a simple test to verify this
     */
    @Test
    public void authenticatePositive(){
        Mockito.when(dao.getUserByUsername(batmanCredentials.getUsername())).thenReturn(batman);
        User resultUser = service.authenticate(batmanCredentials);
        Assert.assertSame(batman,resultUser);
    }

    @Test
    public void registerPositive(){
        // since these are meant to be valid credentials we want the get user by username method
        // to not return anything. We simulate this by returning null from our mocked method call
        Mockito.when(dao.getUserByUsername(newUserCredentials.getUsername())).thenReturn(new User());
        // we have to tell Mockito what to do with the createUser method when the input is given: in our
        // case we want to return a newly initialized User from the database.
        Mockito.when(dao.createUser(newUserCredentialsForDao)).thenReturn(newUserMockData);
        User returnedUser = service.register(newUserCredentials);
        Assert.assertSame(newUserMockData, returnedUser);

    }

    @Test
    public void registerNegativeUsernameTooLong(){
        // if you need Mockito to throw an exception because a method call should NOT have been reached then
        // you can use Mockito.any() as the argument instead of providing a specific value
        Mockito.when(dao.getUserByUsername(Mockito.any()))
                .thenThrow(new AssertionError("getUserByUsername should not have been called: username was too long but was not detected"));
        User returnedUser = service.register(usernameTooLongCreds);
        Assert.assertNull(returnedUser);
    }

    @Test
    public void registerNegativePasswordTooLong(){
        Mockito.when(dao.getUserByUsername(Mockito.any()))
                .thenThrow(new AssertionError("getUserByUsername should not have been called: password was too long but was not detected"));
        User returnedUser = service.register(passwordTooLongCreds);
        Assert.assertNull(returnedUser);
    }

    @Test
    public void registerNegativeUsernameAndPasswordTooLong(){
        Mockito.when(dao.getUserByUsername(Mockito.any()))
                .thenThrow(new AssertionError("getUserByUsername should not have been called: password and Username was too long but was not detected"));
        User returnedUser = service.register(usernamePasswordTooLongCreds);
        Assert.assertNull(returnedUser);
    }

    @Test
    public void registerNegativeUsernameAlreadyTaken(){
        /*
            To check and make sure our service method is preventing duplicate usernames from being
            entered into our database we need to pass an object into the getUserByUsername method
            that shares a username with one that "already exists" and return the User object
            that has the already taken Username. Since I have been using batman to represent
            data that already exists in the database I am reusing it for this test
         */
        Mockito.when(dao.getUserByUsername(usernameTakenCreds.getUsername())).thenReturn(batman);
        Mockito.when(dao.createUser(Mockito.any())).thenThrow(new AssertionError("createUser should not have been called"));
        User returnedUser = service.register(usernameTakenCreds);
        Assert.assertNull(returnedUser);
    }

}
