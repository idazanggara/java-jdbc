package com.enigma.jdbc;

import java.sql.*;

public class Main2DQL {
	public static void main(String[] args) {
		String dbName = "tokonyadia";
		String dbHost = "localhost";
		String dbPort = "5432";
		String newUrl = String.format("jdbc:postgresql://%s:%s/%s",dbHost, dbPort, dbName);

		String username = "postgres";
		String password = "postgres";

		try (Connection connection = DriverManager.getConnection(newUrl, username, password)){
			// sekarang kita mau coba query dql, beda dengan dml dan ddl. kalau dql kita bisa mengambil data
			// inget jadi kalau select itukan kita mengambil data dan database kita sebenernya membuat table baru sementara
			// inget jangan menggunakan * saat query di aplikasi, jadi best practicenya focus ke coloumn apa aja yg akan kalian butuhkan. karan kalau binta akan berat, karena di real project table enggak cuman ada 5 coloumn tapi bisa ada 50'an lebih coloum dan ada ribuat row atau data.
			String sql = "SELECT * FROM products ORDER BY product_id DESC";
			//			connection.createStatement().var // biar cepet
			Statement statement = connection.createStatement();
      // executeQuery() -> hasilnya resultSet (di PPT ada)
      // execute() -> hasilnya boolean (bisa ddl dan dml)
      // exekuteUpdate() -> hasilnya int (bisa dml  dan ddl)
			ResultSet resultSet = statement.executeQuery(sql);

			System.out.println("=".repeat(75));
			System.out.printf("%-5s %-10s %-10s %-20s %-10s\n", "id", "name", "price", "desc", "createdAt");
			System.out.println("=".repeat(75));
			// resyktSet sama seperti itterable jadi pakai next
			resultSet.next();
//			while (resultSet.next()){
				// itu bisa pakai column label atau bisa pakai nomer urutan columnnya
        Integer id = resultSet.getInt("product_id");
        String name = resultSet.getString("name_product");
        Integer price = resultSet.getInt("price");
        String desc = resultSet.getString("description");
        Timestamp createdAt = resultSet.getTimestamp("created_at");

				System.out.printf("%-5s %-10s %-10s %-20s %-10s\n", id, name, price, desc, createdAt);
//				System.out.println(id + " " + name + " " + price + " " + desc + " " + createdAt);
//      }

			resultSet.close(); // yg kita close si risultSet dulu ya buat statementnya
			statement.close();
		} catch (SQLException e){
			System.out.println("Connection Failed");
			System.out.println(e.getMessage());
//			throw new RuntimeException(e); // eenggak papa kita throw yg panggil main akan menangkapnya
		}
	}

	private void exampleDDLDML() {
		String url = "jdbc:postgresql://localhost:5432/tokonyadia";
		String username = "postgres";
		String password = "postgres"; // ini bahaya kalau langsung push di git, karena password ini bisa dilihat oleh orang lain

		try (Connection connection = DriverManager.getConnection(url, username, password)){
			System.out.println("Connected");
			// buat table dengan java / ddl
			String sql = """
          CREATE TABLE IF NOT EXISTS products(
            product_id SERIAL PRIMARY KEY,
						name_product VARCHAR(255),
						price INTEGER,
						description VARCHAR(255),
						created_at TIMESTAMP
          )
          """;
			// ini yg kedua kita insert data / dml
			String sqlInsert = """
								INSERT INTO products (product_id, name_product, price, description, created_at)
								VALUES (1, 'Shampoo', 25000, 'Shampoo anti ketombe', NOW())       
								""";

			String namePrd = "Shampoo";
			Integer price = 25000;
			String desc = "Shampoo anti ketombe";
			Integer id = 3;
			String sqlInsertFormatted = String.format("INSERT INTO products (product_id, name_product, price, description, created_at) VALUES (%d, '%s', %d, '%s', NOW())", id, namePrd, price, desc);


//			String newsqlInsert = " INSERT INTO products (product_id, name_product, price, description, created_at) VALUES ( " + id + ", '"+namePrd+"', "+price+", '"+desc+"', NOW()) ";
			// bagaimana cara eksekusi query di java
			// nah statment itu adalah interfacenya, kita bisa pakai interface ini untuk mengeksekusi query
			Statement statement =  connection.createStatement(); // nanti kita bisa dilarang menggunakan ini
			// execute() -> hasilnya boolean (bisa ddl dan dml)
			// exekuteUpdate() -> hasilnya int (bisa dml  dan ddl)
//			statement.execute(sql); // ini staementnya mengeksekusi query

			int result = statement.executeUpdate(sqlInsert); // ini statementnya mengeksekusi query
			if (result > 0){
				System.out.println("Berhasil Masuk Datanya!!");
			}

			statement.close(); // jangan lupa di tutup

		} catch (SQLException e){
			System.out.println("Connection Failed");
			System.out.println(e.getMessage());
		}
	}
}
