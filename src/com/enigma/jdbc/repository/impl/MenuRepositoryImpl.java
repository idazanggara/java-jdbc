package com.enigma.jdbc.repository.impl;

import com.enigma.jdbc.config.DBConnector;
import com.enigma.jdbc.entity.Menu;
import com.enigma.jdbc.repository.MenuRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MenuRepositoryImpl implements MenuRepository {
	@Override
	public int save(String name) {
		try (Connection connection = DBConnector.getConnection()) {
			String sql = "INSERT INTO m_menu (menu_name) VALUES (?)";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, name);
			int result = statement.executeUpdate();
			statement.close();
			return result;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public Menu findById(Integer id) {
		try (Connection connection = DBConnector.getConnection()) {
			String sql = "SELECT id, menu_name as name FROM m_menu WHERE id = ?";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, id);
			ResultSet resultSet = statement.executeQuery();

			if (resultSet.next()) {
				Menu menu = new Menu(
								resultSet.getInt("id"),
								resultSet.getString("name")
				);
				resultSet.close();
				statement.close();
				return menu;
			} else {
				resultSet.close();
				statement.close();
				return null;
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
