package com.enigma.jdbc;

import java.sql.*;
import java.util.Scanner;

public class Main3ACreateToprepareStatment {
	public static void main(String[] args) {
		String url = "jdbc:postgresql://localhost:5432/tokonyadia";
		String username = "postgres";
		String password = "postgres";

		try (Connection connection = DriverManager.getConnection(url, username, password)){
		// disini kita akan simulasi login
			// kalian tau simulasi login seperti apa?
			// 	Cek apakah email dan password itu sesuai?
			Scanner scanner = new Scanner(System.in);
			String email = scanner.nextLine();
			String passwordUser = scanner.nextLine();
//			String sql = "SELECT * FROM users WHERE email = '" + email + "' AND password = '"+ passwordUser +"'";
//			System.out.println(sql);
//			Statement statement = connection.createStatement();
//			ResultSet resultSet = statement.executeQuery(sql);
			// kita coba ngehack
			// ini sql injection, nanti di coba untuk inputan
				// hacker
				//' OR 1=1 --
			// SELECT email, password FROM users WHERE email = 'hacker' AND password = '' OR 1=1 --'
			// dimana email sama dengan hacker, password sama dengan kosong, atau 1=1, dan --'

			// ini disebut dengan sql injection, bagaimana cara orang yg tidak bertanggung jawab terhadap suatu aplikasi itu melakukan injecksi ke suatu query SQL, jadi sebenernya ini salah satu alasan kita enggak boleh gunakan createstatement, karena kita tidak bisa mengontrol query yang kita buat, karena kita tidak bisa mengontrol inputan user, jadi kita harus menggunakan PreparedStatement
			// penjelasan diata
			// untuk createStament direcomendasikan hanya untuk memasukkan data
			// kalau query select lebih baik gunakan preparesatement

			// ganti jadi preparesatement
			// kita akan menggunakan preparedstatement
			String sql = "SELECT email, password FROM users WHERE email = ? AND password = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql); // sekarang Sqlnya di taroh disini bukan di executequery lagi, tujuan buat apa? biar bisa tambah parameter.
			// column, 1 itu mengarah ke tanda tanya pertama, dimulai dari 1
      preparedStatement.setString(1, email); // cara memasukkan data ke preparedstatement
			preparedStatement.setString(2, passwordUser);
			System.out.println(preparedStatement.toString()); // cara mendapatkan query yang akan di eksekusi
			 ResultSet resultSet = preparedStatement.executeQuery();

			// selain SQL injection dengan prepareSatement, dia juga Memudahkan batch query
			// apa itu batch query?

			if (resultSet.next()){
				System.out.println("Login Sukses");
				System.out.println("Selamat Datang " + resultSet.getString("email"));
			} else {
				System.out.println("Login Gagal");
			}

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}