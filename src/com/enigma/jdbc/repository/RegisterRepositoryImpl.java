package com.enigma.jdbc.repository;

import com.enigma.jdbc.config.DBConnector;
import com.enigma.jdbc.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class RegisterRepositoryImpl implements RegisterRepository {
	@Override
	public int save(String name) {
		try (Connection connection = DBConnector.getConnection())  {
			String sql = "INSERT INTO users (email, password) VALUES (?,?)";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, name);
			ps.setString(2,"pasword");
			int result = ps.executeUpdate();
			ps.close();
			return result;
		} catch (Exception e) {
      throw new RuntimeException(e);
    }
	}

	@Override
	public User findById(Integer id) {
		try (Connection connection = DBConnector.getConnection())  {
			String sql = "SELECT id, email, password FROM users WHERE id = ?";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet resultSet = ps.executeQuery();
			User user = null;
      if (resultSet.next()) {
        user = new User();
        user.setId(resultSet.getInt(1));
        user.setEmail(resultSet.getString(2));
        user.setPassword(resultSet.getString(3));
      }
			resultSet.close();
      ps.close();
      return user;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
