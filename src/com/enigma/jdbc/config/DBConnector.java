package com.enigma.jdbc.config;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnector {
	public static Connection getConnection() {
		try {
			// bisa pakai enviroment variable
			//  echo %JAVA_HOME% , kalau kalian mungkin pakai $, nanti keluarnya pathnya
			// DB_NAME, DB_PORT, DB_HOST, DB_USERNAME, DB_PASSWORD
			// windows pakai SET, mac dan linux pakai export
			//  export DB_NAME=tokonyadia
			//  export DB_PORT=5432
      //  export DB_HOST=localhost
      //  export DB_PASSWORD=postgres
			// DB_HOST=localhost;DB_PORT=5432;DB_NAME=tokonyadia;DB_USERNAME=postgres;DB_PASSWORD=postgres // atau bisa tambahkan di edit konfigurasi
			String dbHost = System.getenv("DB_HOST"); // String dbHost = "localhost";
			String dbPort = System.getenv("DB_PORT"); // String dbPort = "5432";
      String dbName = System.getenv("DB_NAME"); // String dbName = "tokonyadia";
			String dbUrl = "jdbc:postgresql://" + dbHost + ":" + dbPort + "/" + dbName;
//			String dbUrl = String.format("jdbc:postgresql://%s:%s/%s", dbHost, dbPort, dbName);

			System.out.println(dbUrl);
			String dbUser = System.getenv("DB_USERNAME"); // String dbUser = "postgres";
			String dbPass = System.getenv("DB_PASSWORD"); // String dbPass = "postgres";
      return DriverManager.getConnection(dbUrl, dbUser, dbPass);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
