package com.revature.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

// import com.revature.exceptions.MoonFailException;
import com.revature.models.Moon;
import com.revature.utilities.ConnectionUtil;

public class MoonDao {
    
    public List<Moon> getAllMoons() {
		try(Connection connection = ConnectionUtil.createConnection()) {
			List<Moon> moons = new ArrayList<>();

            // String sql = "SELECT moons.id, moons.name, moons.myPlanetId FROM moons JOIN planets ON moons.myPlanetId = planets.id WHERE planets.ownerID = ?";
			String sql = "SELECT * FROM moons";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
			// preparedStatement.setInt(1, currentUserId);
            ResultSet rs = preparedStatement.executeQuery();
			
            while(rs.next()){
				Moon moon = new Moon();
				moon.setId(rs.getInt("id"));
				moon.setName(rs.getString("name"));
				moon.setMyPlanetId(rs.getInt("myPlanetId"));
				moons.add(moon);
            }
			return moons;
        }catch(SQLException e){
            e.printStackTrace();
			return new ArrayList<>();
        }
	}

	public Moon getMoonByName(int ownerId, String moonName) {
		Moon moon = new Moon();
		try(Connection connection = ConnectionUtil.createConnection()){
            String sql = "SELECT moons.id, moons.name, moons.myPlanetId FROM moons JOIN planets ON moons.myPlanetId = planets.id WHERE moons.name = ? AND planets.ownerID = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

			preparedStatement.setString(1, moonName);
			preparedStatement.setInt(2, ownerId);

            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
				moon.setId(rs.getInt("id"));
				moon.setName(rs.getString("name"));
				moon.setMyPlanetId(rs.getInt("myPlanetId"));	
            }
			return moon;
        }catch(SQLException e){
            e.printStackTrace();
			return moon;
        }
	}

	public Moon getMoonById(int ownerId, int moonId) {
		Moon moon = new Moon();

		try(Connection connection = ConnectionUtil.createConnection()){
            String sql = "SELECT moons.id, moons.name, moons.myPlanetId FROM moons JOIN planets ON moons.myPlanetId = planets.id WHERE moons.id = ? AND planets.ownerID = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

			preparedStatement.setInt(1, moonId);
			preparedStatement.setInt(2, ownerId);

            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
				moon.setId(rs.getInt("id"));
				moon.setName(rs.getString("name"));
				moon.setMyPlanetId(rs.getInt("myPlanetId"));
				return moon;
            }
        }catch(SQLException e){
            e.printStackTrace();
			return moon;
        }
		return moon;
	}

	public Moon createMoon(Moon m) {
		Moon createdMoon = new Moon();
        try (Connection connection = ConnectionUtil.createConnection()){
            String sql = "INSERT INTO moons (name, myPlanetId) VALUES (?, ?)";
            //Tells database to return the generated ID
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, m.getName());
            preparedStatement.setInt(2, m.getMyPlanetId());
            
            preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            while(rs.next()){
                createdMoon.setName(m.getName());
                createdMoon.setMyPlanetId(m.getMyPlanetId());
                createdMoon.setId(rs.getInt(1));
            }

            return createdMoon;

        }catch(SQLException e){
            e.printStackTrace();
			return createdMoon;
        }
	}

	public boolean deleteMoonById(int moonId) {
		try(Connection connection = ConnectionUtil.createConnection()){
			String sql = "DELETE FROM moons WHERE id = ?";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, moonId);
			ps.executeUpdate();
			return true;
		}catch(SQLException e){
			e.printStackTrace();
			return false;
		}
	}

	public List<Moon> getMoonsFromPlanet(int planetId) {
	// 	try(Connection connection = ConnectionUtil.createConnection()){
    //         List<Moon> moons = new ArrayList<>();
    //         String sql = "SELECT moons.id, moons.name, moons.myPlanetId FROM moons JOIN planets ON moons.myPlanetId = planets.id WHERE moons.myPlanetId = ? AND planets.ownerID = ?";
    //         PreparedStatement preparedStatement = connection.prepareStatement(sql);

	// 		preparedStatement.setInt(1, planetId);
	// 		preparedStatement.setInt(2, userId);

    //         ResultSet rs = preparedStatement.executeQuery();
    //         while(rs.next()){
	// 			Moon moon = new Moon();
	// 			moon.setId(rs.getInt("id"));
	// 			moon.setName(rs.getString("name"));
	// 			moon.setMyPlanetId(rs.getInt("myPlanetId"));
	// 			moons.add(moon);
    //         }
	// 		return moons;
    //     }catch(SQLException e){
    //         System.out.println(e.getMessage());
    //     }
	// 	return new ArrayList<>();
	// }
	try(Connection connection = ConnectionUtil.createConnection()){
		List<Moon> moons = new ArrayList<>();
		String sql = "SELECT moons.id, moons.name, moons.myPlanetId FROM moons JOIN planets ON moons.myPlanetId = planets.id WHERE moons.myPlanetId = ?";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);

		preparedStatement.setInt(1, planetId);

		ResultSet rs = preparedStatement.executeQuery();
		while(rs.next()){
			Moon moon = new Moon();
			moon.setId(rs.getInt("id"));
			moon.setName(rs.getString("name"));
			moon.setMyPlanetId(rs.getInt("myPlanetId"));
			moons.add(moon);
		}
		return moons;
	}catch(SQLException e){
		System.out.println(e.getMessage());
	}
	return new ArrayList<>();
}


	public Moon getAnyMoonByName(String moonName) {
		Moon moon = new Moon();
		try(Connection connection = ConnectionUtil.createConnection()){
            String sql = "SELECT * FROM moons WHERE name = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

			preparedStatement.setString(1, moonName);

            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
				moon.setId(rs.getInt("id"));
				moon.setName(rs.getString("name"));
				moon.setMyPlanetId(rs.getInt("myPlanetId"));	
            }
			return moon;
        }catch(SQLException e){
            e.printStackTrace();
			return moon;
        }
	}



	public Moon getMoonByIdNoCheck(int moonId) {
		Moon moon = new Moon();

		try(Connection connection = ConnectionUtil.createConnection()){
            String sql = "SELECT * FROM moons WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

			preparedStatement.setInt(1, moonId);

            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
				moon.setId(rs.getInt("id"));
				moon.setName(rs.getString("name"));
				moon.setMyPlanetId(rs.getInt("myPlanetId"));
				return moon;
            }
        }catch(SQLException e){
            e.printStackTrace();
			return moon;
        }

		return moon;
	}
}
