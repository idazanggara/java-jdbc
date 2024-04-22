package com.enigma.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Main1DDL_DML {
	public static void main(String[] args) {
		// download dulu jdbc jarnya untuk postgres
		// https://jdbc.postgresql.org/download/
		// pertama tambahkan library dulu di project structure
		// jdbc yg kita download ini isinya code-code miliknya postgres
		// untuk mengkoneksikan database kita dengan aplikasi java
		// inilah yg di sebuh library

		// JDBC (Java Database Connectivity) itu adalah standart API untuk terhubung dengan Database
		// ini gimana cara java kita bisa connect dengan database kita
		// API : Application Programming Interface,
		// API adalah jembatan untuk melakukan komunikasi antar aplikasi, jadi dengan API ini apkikasi bisa komukasi dengan aplikasi lain, Operation sitem kalian bisa menyalakan layar dll itu dengan API
		// kemaren kan kita simpen data masih di file, nah sekarang kita sudah simpan di db,
		// tapi kita download dulu drivernya  untuk postgres
		// driver itu termasuk libarary, library adalah sekumpulan code milih orang lain yg nanti akan kita gunakan sesuai kegunaannya
		// Driver manager, ini gunakanya untuk memilih apa provider database yg mau kita gunakan

		/** pertma lihat slide dahulu */

		/**
		 * 		 contoh code yg berantakan dulu
		 */
		// jadi mulai jdbc ini kalian ngodingnya sudah perfile lah contoh untuk koneksi db satu file sendiri representasi dari table itu satu file sendiri, nanti kita akan bahas design pattern lah pelan"

		// ini kita menambahkan library untuk postgres secara manual, kalau mau pakai mysql juga bisa manual. ini kita belum belajar MAVEN ya, library/Package manager jadi masih manual
		// sebenernya yg masuk build automation tools sebenernya lebih luas dari package manager. tapi kalian saat ini pahaminya mirip dengan package manajer aja biar enggak bingung.
		// Library adalah sekumpulan code milih orang lain yg nanti akan kita gunakan sesuai kegunaannya

		// template JDBC url
		// template: "jdbc:<provider/db>://<localhost>:<port-provider>/<nama-database>
		// cuman di beberapa provider ada seikit tambahan, tapi cari aja di SOFlow ada kok.
		// https://www.tutorialspoint.com/jdbc/jdbc-db-connections.htm
		String url = "jdbc:postgresql://localhost:5432/tokonyadia";
		String username = "postgres";
		String password = "postgres"; // ini bahaya kalau langsung push di git, karena password ini bisa dilihat oleh orang lain

		String dbName = "tokonyadia";
		String dbHost = "localhost";
		String dbPort = "5432";
		String newUrl = String.format("jdbc:postgresql://%s:%s/%s",dbHost, dbPort, dbName);
		System.out.println(newUrl);

    // kita coba koneksi dulu
		// pertama kita coba tanpa try  catch
//		 Connection connection = DriverManager.getConnection(url, username, password);
//		try {
//			Connection connection = DriverManager.getConnection(url, username, password);
//			connection.close(); // jagan lupa di close karena koneksi itu berat dan nanti kalu kalian menggunakan database server yg berbayar, setiap kalian buka koneksi akan ada biaya.
//			System.out.println("Connected");
//		} catch (SQLException e){
//			System.out.println("Connection Failed");
////			System.out.println(e);
////			e.printStackTrace(); // kalau lagi males
//			System.out.println(e.getMessage());
////			throw new RuntimeException(e);
//		}

		// opsi tambahan kalau drivernya error
		try {
			Class.forName("org.postgresql.Driver"); // ini nanti kalian akan paham, ini adalah library yg kita download tadi
			// ini buat ngecheck apakah drivernya ada atau belum
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
		// langsung pakai try cath with value
		try (Connection connection = DriverManager.getConnection(url, username, password)){ // ini nanti agak delai, karena ini mengkoneskikan ke database kita, jadi pemanggilan method yg cukup berat
      System.out.println("Connected"); // untuk memastikan dia sudah konek atau belum
			// ini udah materi lanjutan, buat ddl di java
			// buat table dengan java / ddl
			// cara lama
//			String sql =  "CREATE TABLE IF NOT EXISTS products("+ " " + " " +
			// cara stelah java 17 - string literal
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
								INSERT INTO products (name_product, price, description, created_at)
								VALUES ('Shampoo', 25000, 'Shampoo anti ketombe', NOW())       
								""";

			String namePrd = "Shampoo";
			Integer price = 25000;
			String desc = "Shampoo anti ketombe";
			String sqlInsertFormatted = String.format("INSERT INTO products ( name_product, price, description, created_at) VALUES ( '%s', %d, '%s', NOW())",  namePrd, price, desc);


//			String newsqlInsert = " INSERT INTO products ( name_product, price, description, created_at) VALUES ( " '"+namePrd+"', "+price+", '"+desc+"', NOW()) ";
				// bagaimana cara eksekusi query di java
			// nah statment itu adalah interfacenya, kita bisa pakai interface ini untuk mengeksekusi query
			Statement statement =  connection.createStatement();
			// execute() -> hasilnya boolean (bisa ddl dan dml)
			// exekuteUpdate() -> hasilnya int (bisa dml  dan ddl)
//			statement.execute(sql); // ini staementnya mengeksekusi query

			int result = statement.executeUpdate(sqlInsert); // ini statementnya mengeksekusi query
			if (result > 0){
				System.out.println("Berhasil Masuk Datanya!!");
			}

			statement.close(); // jangan lupa di tutup

			// dql beda lagi caranya



    } catch (SQLException e){
      System.out.println("Connection Failed");
      System.out.println(e.getMessage());
//			throw new RuntimeException(e); // eenggak papa kita throw yg panggil main akan menangkapnya
    }

		// Gimana buat DDL di java
	}
}
