package com.revature.service;

import java.util.List;

import com.revature.models.Planet;
import com.revature.repository.PlanetDao;

public class PlanetService {

	private PlanetDao dao;

	public PlanetService(PlanetDao dao){
		this.dao = dao;
	}

	public List<Planet> getAllPlanets() {
		return dao.getAllPlanets();
	}

	public Planet getPlanetByName(int ownerId, String planetName) {
		return dao.getPlanetByName(ownerId, planetName);
	}

	public Planet getPlanetById(int ownerId, int planetId) {
		return dao.getPlanetById(ownerId, planetId);
	}

	public Planet createPlanet(int ownerId, Planet planet) {
		if(planet.getName().length()>30){
			System.out.println("Name too long");
			return new Planet();
		}
		if(dao.getAnyPlanetByName(planet.getName()).getName() != null){
			System.out.println("Cannot create a planet with a duplicate name.");
			return new Planet();
		}else{
			System.out.println("Successful creation of planet.");
			Planet createdPlanet = new Planet();
			createdPlanet.setName(planet.getName());
			createdPlanet.setOwnerId(ownerId);
			return dao.createPlanet(createdPlanet);
		}
	}

	public boolean deletePlanetById(int ownerId, int planetId) {
		Planet planet = getPlanetById(ownerId, planetId);
		if(dao.getPlanetById(ownerId, planetId).getName() != null){
			if(dao.deletePlanetById(planetId)){
				System.out.println(planet);
				System.out.println("Deletion of planet successful.");
				return true;
			}else{
				System.out.println("Deletion of planet unsuccessful.");
				return false;
			}
		}else{
			System.out.println("You do not have access, or planet does not exist.");
			return false;
		}
	}
}
