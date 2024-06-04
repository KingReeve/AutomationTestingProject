package com.revature.service;

import com.revature.models.Planet;
import com.revature.models.User;
import com.revature.repository.PlanetDao;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

public class PlanetServiceTest {

    private PlanetDao dao;
    //private static User batman;
    //private static User robin;
    // will set id of 1 and 2 manually when mocking
    private static Planet uniquePlanet1;
    private static Planet positivePlanet;
    private static Planet longPlanetname;
    private static Planet planetNameTooLong;
    private static Planet validPlanet2; // for deletion of planet not owned
    private static List<Planet> planetList;
    private static Planet noNamePlanet;
    private static Planet nonUniquePlanet1;
    private static Planet mockPlanet1;
    private PlanetService service;


    @BeforeClass
    public static void initializeTest(){
        /*
    batman = new User();
    batman.setId(1);
    batman.setUsername("Batman");
    batman.setPassword("I am the night");

    robin = new User();
    robin.setId(2);
    robin.setUsername("Robin");
    robin.setPassword("I am robin");
        */
    noNamePlanet = new Planet();
    noNamePlanet.setId(5);
    noNamePlanet.setName("");
    noNamePlanet.setOwnerId(2);

    mockPlanet1 = new Planet();
    mockPlanet1.setId(1);
    mockPlanet1.setName("validPlanetName");
    mockPlanet1.setOwnerId(1);
    uniquePlanet1 = new Planet();
    uniquePlanet1.setId(1);
    uniquePlanet1.setName("validPlanetName");
    uniquePlanet1.setOwnerId(1);
    nonUniquePlanet1 = new Planet();
    nonUniquePlanet1.setId(6);
    nonUniquePlanet1.setName("validPlanetName");
    nonUniquePlanet1.setOwnerId(1);
    positivePlanet = new Planet();
    positivePlanet.setId(4);
    positivePlanet.setName("validPlanetName");
    positivePlanet.setOwnerId(4);


    longPlanetname = new Planet();
    longPlanetname.setId(6);
    longPlanetname.setName("123456789111111111111111111111");
    longPlanetname.setOwnerId(1);

    planetNameTooLong = new Planet();
    planetNameTooLong.setId(2);
    planetNameTooLong.setName("1234567891111111111111111111131");
    planetNameTooLong.setOwnerId(1);

    validPlanet2 = new Planet();
    validPlanet2.setId(3);
    validPlanet2.setName("validPlanetName2");
    validPlanet2.setOwnerId(2);

    planetList = new ArrayList<>();
    planetList.add(uniquePlanet1);
    planetList.add(validPlanet2);
    planetList.add(longPlanetname);
    }
    @Before
    public void setup(){
        dao = Mockito.mock(PlanetDao.class);
        service = new PlanetService(dao);
    }
    @Test
    public void getAllPlanets(){
        Mockito.when(dao.getAllPlanets()).thenReturn(planetList);
        List<Planet> tempList = service.getAllPlanets();
        Assert.assertTrue(tempList.size()!=0);
    }

    @Test
    public void getPlanetByNamePositive(){
        Mockito.when(dao.getPlanetByName(uniquePlanet1.getOwnerId(),uniquePlanet1.getName())).thenReturn(uniquePlanet1);
        Planet tempPlanet = service.getPlanetByName(uniquePlanet1.getOwnerId(), uniquePlanet1.getName());
        Assert.assertSame(uniquePlanet1,tempPlanet);
    }

    @Test
    public void getPlanetByNameNegative(){
        Mockito.when(dao.getPlanetByName(uniquePlanet1.getOwnerId(),uniquePlanet1.getName())).thenReturn(null);
        Planet tempPlanet = service.getPlanetByName(uniquePlanet1.getOwnerId(), uniquePlanet1.getName());
        Assert.assertNull(tempPlanet);
    }

    @Test
    public void getPlanetByIdPositive(){
        Mockito.when(dao.getPlanetById(uniquePlanet1.getOwnerId(), uniquePlanet1.getId())).thenReturn(uniquePlanet1);
        Planet tempPlanet = service.getPlanetById(uniquePlanet1.getOwnerId(), uniquePlanet1.getId());
        Assert.assertSame(uniquePlanet1,tempPlanet);
    }
    @Test
    public void getPlanetByIdNegative(){
        Mockito.when(dao.getPlanetById(uniquePlanet1.getOwnerId(), uniquePlanet1.getId())).thenReturn(null);
        Planet tempPlanet = service.getPlanetById(uniquePlanet1.getOwnerId(), uniquePlanet1.getId());
        Assert.assertNull(tempPlanet);
    }
    @Test
    public void createPlanetWithUniquePositive(){
        Mockito.when(dao.getAnyPlanetByName(uniquePlanet1.getName())).thenReturn(null);
        Mockito.when(dao.createPlanet(uniquePlanet1)).thenReturn(mockPlanet1);
        Planet tempPlanet = service.createPlanet(uniquePlanet1.getOwnerId(), uniquePlanet1);
        Assert.assertTrue(tempPlanet.getId()>0);
    }
    @Test
    public void createPlanetWithNoNamePositive(){
        Mockito.when(dao.getAnyPlanetByName(noNamePlanet.getName())).thenReturn(null);
        Mockito.when(dao.createPlanet(noNamePlanet)).thenReturn(mockPlanet1);
        Planet tempPlanet = service.createPlanet(noNamePlanet.getOwnerId(), noNamePlanet);
        Assert.assertTrue(tempPlanet.getId()>0);
    }
    @Test
    public void createPlanetWithLongName(){
        Mockito.when(dao.getAnyPlanetByName(longPlanetname.getName())).thenReturn(null);
        Mockito.when(dao.createPlanet(longPlanetname)).thenReturn(mockPlanet1);
        Planet tempPlanet = service.createPlanet(longPlanetname.getOwnerId(), longPlanetname);
        Assert.assertTrue(tempPlanet.getId()>0);
    }
    @Test
    public void createPlanetWithExistingName(){
        Mockito.when(dao.getAnyPlanetByName(mockPlanet1.getName())).thenReturn(mockPlanet1);
        Mockito.when(dao.createPlanet(mockPlanet1)).thenThrow( new AssertionError("Create planet should not have been called since" +
                "Planet already exists " ));
        Planet tempPlanet = service.createPlanet(mockPlanet1.getOwnerId(), uniquePlanet1);
        //Successful of creationg of planet
        Assert.assertNull(tempPlanet);
    }
    

}

