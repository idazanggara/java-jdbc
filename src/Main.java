import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {
	public static void main(String[] args) {
		// download dulu jdbc jarnya untuk postgres
		// https://jdbc.postgresql.org/download/
		// pertama tambahkan library dulu di project structure
		// jdbc yg kita download ini isinya code-code miliknya postgres
		// untuk mengkoneksikan database kita dengan aplikasi java
		// inilah yg di sebuh library

		// JDBC (Java Database Connectivity) itu adalah sebuah API
		// ini gimana cara java kita bisa connect dengan java database kita
		// API adalah jembatan untuk melakukan komunikasi antar aplikasi
		// Driver manager, ini gunakanya untuk memilih apa provider database yg mau kita gunakan
		/**
		 * 		 contoh code yg berantakan dulu
		 */
		// jadi mulai jdbc ini kalian ngodingnya sudah perfile lah contoh untuk koneksi db satu file sendiri representasi dari table itu satu file sendiri, nanti kita akan bahas design pattern lah pelan"

		// ini kita menambahkan library untuk postgres secara manual, kalau mau pakai mysql juga bisa manual. ini kita belum belajar MAVEN ya, library/Package manager jadi masih manual
		// sebenernya yg masuk build automation tools sebenernya lebih luas dari package manager. tapi kalian saat ini pahaminya mirip dengan package manajer aja biar enggak bingung.
		// Library adalah sekumpulan code milih orang lain yg nanti akan kita gunakan sesuai kegunaannya

		// template JDBC url
		// template: "jdbc:<provider>://localhost:<port-provider>/<nama-database>
		// https://www.tutorialspoint.com/jdbc/jdbc-db-connections.htm
		String url = "jdbc:postgresql://localhost:5432/DIYPlatform";
		String username = "postgres";
		String password = "postgres";
		try {
			Connection connection = DriverManager.getConnection(url, username, password);
			connection.close(); // jagan lupa di close karana koneksi itu berat
		} catch (SQLException e){
			System.out.println("Connection Failed");
			System.out.println(e);
		}
		System.out.println("Connected");




	}
}
