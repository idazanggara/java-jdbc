package com.enigma.jdbc;

import java.sql.*;

public class Main3_DMLBanyak {
	public static void main(String[] args) {
		String dbName = "tokonyadia";
		String dbHost = "localhost";
		String dbPort = "5432";
		String newUrl = String.format("jdbc:postgresql://%s:%s/%s",dbHost, dbPort, dbName);
		String username = "postgres";
		String password = "postgres";


		try (Connection connection = DriverManager.getConnection(newUrl, username, password)){
			Statement statement = connection.createStatement();
			// Sekarang kita kalau mau insert intonya banyak gimana? kalau datanya dari array
			// enggak mungkin kan kalu insert satu satu.
			// masih ingat enggak kemaren ACID
			// bagian dari konsistensi, konsistensi di ACID itu apa? strukturnya harus sesuai dan datanya harus sesuai.
			// ceritanya : ada harga kita settipe data interger tapi kita input ada uh double. yg di double nolak enggak?

			// biasanya yg kalian lakukan seperti ini kan
			Object[] prices = {20000, 30000.00, "safda", 50000}; // jadi enggak ACID kan, enggak konsisten
			for (Object price : prices){
				// tapi kalau kayak gini jikai data 3 error yg lain tetep masuk enggak? tetep masukkan ? berarti konsisten enggak?

				String sql = String.format("INSERT INTO products ( price) VALUES (%s)", price);
        statement.executeUpdate(sql); // kalau untuk eksekusi banyak, kalian enggak disarankan melakukan 2 langkan ini, sql dan statement ini.
      }
			// nah cara handle enggak konsistenan ini pakai apa?
			// Kalian bida gunakan prepate statement, tapi sebelum kita pelajarin prepareStatement kita bahas dulu kenapa enggak bole pakai createStatement();

			// nah kita lanjut ke Main3CreateStamenet, ini membahas kenapa kita enggak boleh pakai create statement



      statement.close();


		} catch (SQLException e){
			System.out.println("Connection Failed");
			System.out.println(e.getMessage());
		}
	}

	private void exampleDQL() {
		String dbName = "tokonyadia";
		String dbHost = "localhost";
		String dbPort = "5432";
		String newUrl = String.format("jdbc:postgresql://%s:%s/%s",dbHost, dbPort, dbName);

		String username = "postgres";
		String password = "postgres";

		try (Connection connection = DriverManager.getConnection(newUrl, username, password)){
			String sql = "SELECT * FROM products ORDER BY product_id DESC";
			Statement statement = connection.createStatement();
			// executeQuery() -> hasilnya resultSet (di PPT ada)
			// execute() -> hasilnya boolean (bisa ddl dan dml)
			// exekuteUpdate() -> hasilnya int (bisa dml  dan ddl)
			ResultSet resultSet = statement.executeQuery(sql);

			System.out.println("=".repeat(75));
			System.out.printf("%-5s %-10s %-10s %-20s %-10s\n", "id", "name", "price", "desc", "createdAt");
			System.out.println("=".repeat(75));
			while (resultSet.next()){
			Integer id = resultSet.getInt("product_id");
			String name = resultSet.getString("name_product");
			Integer price = resultSet.getInt("price");
			String desc = resultSet.getString("description");
			Timestamp createdAt = resultSet.getTimestamp("created_at");

			System.out.printf("%-5s %-10s %-10s %-20s %-10s\n", id, name, price, desc, createdAt);
//				System.out.println(id + " " + name + " " + price + " " + desc + " " + createdAt);
      }

			resultSet.close(); // yg kita close si risultSet dulu ya buat statementnya
			statement.close();
		} catch (SQLException e){
			System.out.println("Connection Failed");
			System.out.println(e.getMessage());
//			throw new RuntimeException(e); // eenggak papa kita throw yg panggil main akan menangkapnya
		}
	}
}
