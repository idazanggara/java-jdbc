package com.enigma.jdbc;

import com.enigma.jdbc.config.DBConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Arrays;

public class Main4UseDBConnector {
	// jadi koneksi itu di buat hanya 1 kali
	// kita udah belajar OOP, jadi manfaatkan OOP.
	// kita buat package config
	public static void main(String[] args) {
		String[] emails = {"idaz@example.com", "anggara@example.com", "didaz@example.com"};
		try (Connection connection = DBConnector.getConnection()) {
			// batch proses, proses yg dikumpulkan dulu
			// pertama di tambahin user credential unique constraint
			// PrepareStament
			String sql = "insert into users (email, password) values (?, ?)";
			PreparedStatement statement = connection.prepareStatement(sql);

//			Statement statement1 = connection.createStatement();
//			for (String email : emails) {
//				String sql2 = String.format("insert into users (email, password) values ('%s', 'password')",email);
//				statement1.executeUpdate(sql2);
//			}

			for (String email : emails){
				statement.setString(1, email);
				statement.setString(2, "password");
        statement.addBatch();
				statement.clearParameters(); // biar kereset lagi
			}

//			 setelah bacth terkumpul atau ditambahin, kita eksekusi diluar
			int[] result = statement.executeBatch();
			System.out.println(Arrays.toString(result));

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
