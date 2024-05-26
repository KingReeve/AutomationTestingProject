package com.revature.service;

import java.util.List;

import com.revature.models.Moon;
// import com.revature.models.User;
import com.revature.repository.MoonDao;

public class MoonService {

	private MoonDao dao;

	public MoonService(MoonDao dao) {
		this.dao = dao;
	}

	public List<Moon> getAllMoons() {
		// return dao.getAllMoons(userId);
		return dao.getAllMoons();
	}

	public Moon getMoonByName(int userId, String moonName) {
		return dao.getMoonByName(userId, moonName);
	}

	public Moon getMoonById(int userId, int moonId) {
		return dao.getMoonById(userId, moonId);
	}

	public Moon createMoon(Moon moon) {
		if(moon.getName().length()>30){
			System.out.println("Name too long. Less than 30 required.");
			moon.setName(null);
			return moon;
		}
		if(dao.getAnyMoonByName(moon.getName()).getName() != null){   //&& dao.getAnyMoonByName(moon.getName()).getName().equals(moon.getName())
			System.out.println("Cannot create a moon with a duplicate name.");
			moon.setName(null);
			return moon;
		}else{
			return dao.createMoon(moon);
		}
	}

	public boolean deleteMoonById(int moonId) {
		Moon moon = dao.getMoonByIdNoCheck(moonId);
		if(moon.getName()==null){
			System.out.println("No moon to delete");
			return false;		
		}else{
			System.out.println(moon.toString());
			return dao.deleteMoonById(moonId);
		}
	}

	public List<Moon> getMoonsFromPlanet(int myPlanetId) {
		//Find a way to get userId in here
		//return dao.getMoonsFromPlanet(currentUser.getId(), myPlanetId);
		return dao.getMoonsFromPlanet(myPlanetId);
	}
}
