package com.revature.service;

import com.revature.models.Moon;
import com.revature.models.Planet;
import com.revature.repository.MoonDao;
import io.cucumber.java.bs.A;
import io.cucumber.java.ja.但し;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

public class MoonServiceTest {

    private MoonDao dao;
    private MoonService service;
    //need to make a list of user and planet objects to test on
    private static Planet testPlanet; //used for moon ownership tests
    private static Moon uniqueMoon; //used for positive unique, get by name, and id
    private static Moon mockUniqueMoon; //used to mock a returned object
    private static Moon zeroCharMoon;// used for positive moon creation
    private static Moon mockZeroCharMoon; //mock return object
    private static Moon thirtyCharMoon; //used for positive moon creation
    private static Moon mockThirtyCharMoon; //used to mock returned object

    private static List<Moon> moonList; //used for get all moons test



    private static Moon nonUniqueMoon; //used for negative unique test, id, and name test
    private static Moon longMoonName; //used for negative moon creation char limit test

    //both used for moon deletion
    private static Moon moonToDelete;
    private static Moon mockMoonToDelete;
    private static Moon nonExistentMoon;


    @BeforeClass
    public static void initTests()
    {
        //setting up planet testing object
        testPlanet = new Planet();
        testPlanet.setId(1);
        testPlanet.setName("testPlanetForMoons");
        testPlanet.setOwnerId(2);

        //setting up moon test objects
        uniqueMoon = new Moon();
        uniqueMoon.setId(1);
        uniqueMoon.setName("uniqueMoon");
        uniqueMoon.setMyPlanetId(1);

        //creating the mock return object
        mockUniqueMoon = new Moon();
        mockUniqueMoon.setId(uniqueMoon.getId());
        mockUniqueMoon.setName(uniqueMoon.getName());
        mockUniqueMoon.setMyPlanetId(uniqueMoon.getMyPlanetId());

        zeroCharMoon = new Moon();
        zeroCharMoon.setId(2);
        zeroCharMoon.setName("");
        zeroCharMoon.setMyPlanetId(1);

        mockZeroCharMoon = new Moon();
        mockZeroCharMoon.setId(zeroCharMoon.getId());
        mockZeroCharMoon.setName(zeroCharMoon.getName());
        mockZeroCharMoon.setMyPlanetId(zeroCharMoon.getMyPlanetId());

        thirtyCharMoon = new Moon();
        thirtyCharMoon.setId(3);
        thirtyCharMoon.setName("CARTMANCARTMANCARTMANCARTMANCA");
        thirtyCharMoon.setMyPlanetId(1);

        mockThirtyCharMoon = new Moon();
        mockThirtyCharMoon.setId(thirtyCharMoon.getId());
        mockThirtyCharMoon.setName(thirtyCharMoon.getName());
        mockThirtyCharMoon.setMyPlanetId(thirtyCharMoon.getMyPlanetId());

        // creating moon list test object
        moonList = new ArrayList<>();
        //adding to moon list
        moonList.add(uniqueMoon);
        moonList.add(zeroCharMoon);
        moonList.add(thirtyCharMoon);

        //creating negative test objects
        nonUniqueMoon = new Moon();
        nonUniqueMoon.setName("notUniqueName");
        nonUniqueMoon.setMyPlanetId(1);

        longMoonName = new Moon();
        longMoonName.setName("CARTMANCARTMANCARTMANCARTMANCAgdddd");
        longMoonName.setMyPlanetId(1);

        moonToDelete= new Moon();
        moonToDelete.setName("delete");
        moonToDelete.setId(6);
        moonToDelete.setMyPlanetId(1);

        mockMoonToDelete = new Moon();
        mockMoonToDelete.setId(moonToDelete.getId());
        mockMoonToDelete.setName(moonToDelete.getName());
        mockMoonToDelete.setMyPlanetId(moonToDelete.getMyPlanetId());

        nonExistentMoon = new Moon();
        nonExistentMoon.setName("NotExist");
        nonExistentMoon.setId(7);
        nonExistentMoon.setMyPlanetId(1);


    }

    @Before
    public void setUp()
    {
        dao = Mockito.mock(MoonDao.class);
        service = new MoonService(dao);
    }

    @Test
    public void getAllMoonsTest()
    {
        Mockito.when(dao.getAllMoons()).thenReturn(moonList);
        List<Moon> temp = service.getAllMoons();
        Assert.assertTrue(temp.size()!=0);
    }
//********************************** Postive/Negative Get Moon By Name tests ************************************************************
    //should I use getAnyMoonByName or getMoonByName?
    // returns true using an existing moon
    @Test
    public void getMoonByNamePositiveTest()
    {
        Mockito.when(dao.getMoonByName(testPlanet.getOwnerId(),uniqueMoon.getName())).thenReturn(mockUniqueMoon);
        Moon temp = service.getMoonByName(testPlanet.getOwnerId(), uniqueMoon.getName());
        int unique_id = mockUniqueMoon.getId();
        String unique_name = mockUniqueMoon.getName();
        int temp_id = temp.getId();
        String temp_name = temp.getName();

        Assert.assertTrue(unique_name.equals(temp_name) && unique_id == temp_id);

    }

    //negative test, should return false since moon doesnt exist
    @Test
    public void getMoonByNameNegativeTest()
    {
        Mockito.when(dao.getMoonByName(testPlanet.getOwnerId(), nonUniqueMoon.getName())).thenReturn(null);
        Moon temp = service.getMoonByName(testPlanet.getOwnerId(), nonUniqueMoon.getName());
        Assert.assertNull(temp);
    }

    //****************** Positive/Negative Get Moon By ID tests *******************************************************
    @Test
    public void getMoonByIdPositiveTest()
    {
        Mockito.when(dao.getMoonById(testPlanet.getOwnerId(), uniqueMoon.getId())).thenReturn(mockUniqueMoon);
        Moon temp = service.getMoonById(testPlanet.getOwnerId(), uniqueMoon.getId());
        int unique_id = mockUniqueMoon.getId();
        String unique_name = mockUniqueMoon.getName();
        int temp_id = temp.getId();
        String temp_name = temp.getName();

        Assert.assertTrue(unique_name.equals(temp_name) && unique_id == temp_id);

    }

    @Test
    public void getMoonByIdNegativeTest()
    {
        Mockito.when(dao.getMoonById(testPlanet.getOwnerId(), nonUniqueMoon.getId())).thenReturn(null);
        Moon temp = service.getMoonById(testPlanet.getOwnerId(), nonUniqueMoon.getId());
        Assert.assertNull(temp);
    }


    //*********** Positive Moon Creation **********************************************************************************

    @Test
    public void createMoonPositiveTestWithUniqueName()
    {
        Mockito.when(dao.getAnyMoonByName(uniqueMoon.getName())).thenReturn(new Moon());
        Mockito.when(dao.createMoon(uniqueMoon)).thenReturn(mockUniqueMoon);
        Moon temp = service.createMoon(uniqueMoon);
        Assert.assertSame(mockUniqueMoon, temp);

    }
    @Test
    public void createMoonPositiveTestWithZeroChar()
    {
        Mockito.when(dao.getAnyMoonByName(zeroCharMoon.getName())).thenReturn(new Moon());
        Mockito.when(dao.createMoon(zeroCharMoon)).thenReturn(mockZeroCharMoon);
        Moon temp = service.createMoon(zeroCharMoon);
        Assert.assertSame(mockZeroCharMoon,temp);

    }

    @Test
    public void createMoonPositiveTestWithThirtyChar()
    {
        Mockito.when(dao.getAnyMoonByName(thirtyCharMoon.getName())).thenReturn(new Moon());
        Mockito.when(dao.createMoon(thirtyCharMoon)).thenReturn(mockThirtyCharMoon);
        Moon temp = service.createMoon(thirtyCharMoon);
        Assert.assertSame(mockThirtyCharMoon, temp);
    }


    //*************** Moon Creation Negative Tests **************************************************************************************

    @Test
    public void createMoonNegativeTestWithExistingMoonName()
    {
        Mockito.when(dao.getAnyMoonByName(nonUniqueMoon.getName())).thenReturn(nonUniqueMoon);
        Mockito.when(dao.createMoon(nonUniqueMoon)).thenThrow(
                new AssertionError("Create planet should not have been called since moon already exists")
        );
        Moon temp = service.createMoon(nonUniqueMoon);
        Assert.assertEquals(0, temp.getId());
    }

    @Test
    public void createMoonNegativeTestWithLongMoonName()
    {
        Mockito.when(dao.getAnyMoonByName(longMoonName.getName())).thenThrow(
                new AssertionError("Get Any Moon name should not have been called since name is longer than 30 chars")
        );
        Moon temp = service.createMoon(longMoonName);
        Assert.assertSame(temp, longMoonName);
    }

    //************ Positive/Negative Moon Deletion Tests *************************

    @Test
    public void deleteMoonByIdPositiveTest()
    {
        Mockito.when(dao.getMoonByIdNoCheck(moonToDelete.getId())).thenReturn(moonToDelete);
        Mockito.when(dao.deleteMoonById(moonToDelete.getId())).thenReturn(true);
        boolean deleted = service.deleteMoonById(moonToDelete.getId());
        Assert.assertTrue(deleted);


    }

    @Test
    public void deleteMoonByIdNegativeTest()
    {
        Mockito.when(dao.getMoonByIdNoCheck(nonExistentMoon.getId())).thenReturn(null);
        Mockito.when(dao.deleteMoonById(nonExistentMoon.getId())).thenThrow(
                new AssertionError("Delete moon should not have been called since moon does not exist")
        );
        boolean notDelete = service.deleteMoonById(nonExistentMoon.getId());
        Assert.assertFalse(notDelete);
    }



    @Test
    public void getMoonsFromPlanetPositiveTest()
    {
        Mockito.when(dao.getMoonsFromPlanet(testPlanet.getId())).thenReturn(moonList);
        List<Moon> temp = service.getMoonsFromPlanet(testPlanet.getId());
        Assert.assertSame(moonList, temp);
    }

    @Test
    public void getMoonsFromPlanetNegativeTest()
    {
        Mockito.when(dao.getMoonsFromPlanet(2)).thenReturn(new ArrayList<>());
        List<Moon> temp = service.getMoonsFromPlanet(2);
        Assert.assertEquals(temp.size(), 0);
    }




}
