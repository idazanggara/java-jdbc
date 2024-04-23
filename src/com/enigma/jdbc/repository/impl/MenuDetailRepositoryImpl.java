package com.enigma.jdbc.repository.impl;

import com.enigma.jdbc.config.DBConnector;
import com.enigma.jdbc.dto.MenuDetailRequest;
import com.enigma.jdbc.dto.MenuDetailResponse;
import com.enigma.jdbc.repository.MenuDetailRepository;

import java.sql.*;

public class MenuDetailRepositoryImpl implements MenuDetailRepository {
	@Override
	public int save(MenuDetailRequest request) {
		try (Connection conn = DBConnector.getConnection()) {
			// memulai transaksi secara ACID
			// COMMIT untu menyimpan perubahan pada transaksi ya
			// nah sebenernya di JDBC udah auto menggunakan transaksi di awal tapi dia auto commit

			conn.setAutoCommit(false); // auto commit kita false dulu diawal. biarkan dia melakukan transaksi
			String sqlInsertMenul = "INSERT INTO m_menu (menu_name) VALUES (?)";
			PreparedStatement psInsertMenu = conn.prepareStatement(sqlInsertMenul, Statement.RETURN_GENERATED_KEYS); // IDnya di returning saat kalian melakukan insert

			psInsertMenu.setString(1, request.getName()); // kalau pakai parameter lebih baik prepare statment
      int resul = 0;
			resul += psInsertMenu.executeUpdate();

			// ini khusu sequence atau autoincrement ya guys
			// dia seperti : SELECT id FROM LAST INSERT
			ResultSet gkMenu = psInsertMenu.getGeneratedKeys();// kita ambil id
			int menuId = 0;
			if (gkMenu.next()){
				menuId = gkMenu.getInt("id");
			} else {
				conn.rollback(); // kalau gagal bisa kita rollback/batal
			}

			// kita ambil id dari menu yang baru saja di insert
			String sqlInsertMenuPrice = "INSERT INTO m_menu_price (menu_id, price) VALUES (?, ?)";
			PreparedStatement psInsertMenuPrice = conn.prepareStatement(sqlInsertMenuPrice);
			psInsertMenuPrice.setInt(1, menuId);
      psInsertMenuPrice.setFloat(2, request.getPrice());
			resul += psInsertMenuPrice.executeUpdate();

			gkMenu.close();
			psInsertMenu.close();
			psInsertMenuPrice.close();

			// acid kemaren kita ngapain?
      conn.commit(); // commit transaksi
      return resul;

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public MenuDetailResponse findByMenuId(Integer menuId) {
		// challlenge ke traine, gampang enggak perlu pakai tranksaksi kalau select
		try (Connection conn = DBConnector.getConnection()){
      String sql = """
            SELECT
      			m.id as menu_id,
      			mp.id as menu_price_id,
      			m.menu_name as name,
         		mp.price
         		FROM m_menu m
         		JOIN m_menu_price mp
         		ON m.id = mp.menu_id
         		WHERE m.id = ?""";

      PreparedStatement ps = conn.prepareStatement(sql);
      ps.setInt(1, menuId);
      ResultSet rs = ps.executeQuery();
      MenuDetailResponse response = new MenuDetailResponse();
      if (rs.next()){
        response.setMenuId(rs.getInt("menu_id"));
				response.setMenuPriceId(rs.getInt("menu_price_id"));
        response.setName(rs.getString("name"));
        response.setPrice(rs.getFloat("price"));
      }
			rs.close();
			ps.close();
      return response;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
