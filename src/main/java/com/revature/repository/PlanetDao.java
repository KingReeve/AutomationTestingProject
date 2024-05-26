package com.revature.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.models.Planet;
import com.revature.utilities.ConnectionUtil;

public class PlanetDao {
    
    public List<Planet> getAllPlanets() {
		try(Connection connection = ConnectionUtil.createConnection()) {
			List<Planet> planets = new ArrayList<>();

            String sql = "SELECT * FROM planets";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
			// preparedStatement.setInt(1, ownerId);
            ResultSet rs = preparedStatement.executeQuery();

            while(rs.next()){
				Planet planet = new Planet();
				planet.setId(rs.getInt("id"));
				planet.setName(rs.getString("name"));
				planet.setOwnerId(rs.getInt("ownerId"));
				planets.add(planet);
            }
			return planets;
        }catch(SQLException e){
            e.printStackTrace();
			return new ArrayList<>();
        }
	}

	public Planet getPlanetByName(int ownerId, String planetName) {
		Planet planet = new Planet();
		try(Connection connection = ConnectionUtil.createConnection()){
			String sql = "SELECT * FROM planets WHERE name = ? AND ownerId = ?";
			PreparedStatement ps = connection.prepareStatement(sql);
			
			ps.setString(1, planetName);
			ps.setInt(2, ownerId);
			ResultSet rs = ps.executeQuery();

            while(rs.next()){
				planet.setId(rs.getInt("id"));
				planet.setName(rs.getString("name"));
				planet.setOwnerId(rs.getInt("ownerId"));
            }
			return planet;
		}catch(SQLException e){
			e.printStackTrace();
			return planet;
		}			
	}

	public Planet getPlanetById(int ownerId, int planetId) {
		try(Connection connection = ConnectionUtil.createConnection()){
			String sql = "SELECT * FROM planets WHERE id = ? AND ownerId = ?";
			PreparedStatement ps = connection.prepareStatement(sql);
			
			ps.setInt(1, planetId);
			ps.setInt(2, ownerId);
			ResultSet rs = ps.executeQuery();

			Planet planet = new Planet();
			while(rs.next()){
				planet.setId(rs.getInt("id"));
				planet.setName(rs.getString("name"));
				planet.setOwnerId(rs.getInt("ownerId"));
			}
			return planet;
		}catch(SQLException e){
			e.printStackTrace();
			return new Planet();
		}	
	}

	public Planet createPlanet(Planet p) {
		Planet createdPlanet = new Planet();
        try (Connection connection = ConnectionUtil.createConnection()){
            String sql = "INSERT INTO planets (name, ownerId) VALUES (?, ?)";
            //Tells database to return the generated ID
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, p.getName());
            preparedStatement.setInt(2, p.getOwnerId());
            
            preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            while(rs.next()){
                createdPlanet.setName(p.getName());
                createdPlanet.setOwnerId(p.getOwnerId());
                createdPlanet.setId(rs.getInt(1));
            }
            return createdPlanet;

        }catch(SQLException e){
            e.printStackTrace();
			return createdPlanet;
        }
	}

	public boolean deletePlanetById(int planetId) {
		try(Connection connection = ConnectionUtil.createConnection()){
			String sql = "DELETE FROM planets WHERE id = ?";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, planetId);
			ps.executeUpdate();
			return true;
		}catch(SQLException e){
			e.printStackTrace();
			return false;
		}
	}




	public Planet getAnyPlanetByName(String planetName) {
		try(Connection connection = ConnectionUtil.createConnection()){
			String sql = "SELECT * FROM planets WHERE name = ?";
			PreparedStatement ps = connection.prepareStatement(sql);
			
			ps.setString(1, planetName);
			ResultSet rs = ps.executeQuery();

			Planet planet = new Planet();
			while(rs.next()){
				planet.setId(rs.getInt("id"));
				planet.setName(rs.getString("name"));
				planet.setOwnerId(rs.getInt("ownerId"));
			}
			return planet;
		}catch(SQLException e){
			e.printStackTrace();
			return null;
		}	
	}
}