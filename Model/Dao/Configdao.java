package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import Bean.Config;
import Connection.ConnecDataBase;

public class Configdao {
	String sql;
	Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	
	public Config getConfig() throws Exception {
		Config config = new Config();
		
		sql = "select top 1 * from Config";

		con = new ConnecDataBase().getConnection();
		ps = con.prepareStatement(sql);
		rs = ps.executeQuery();
		if (rs.next()) {			
			config = new Config(rs.getBoolean(1), rs.getBoolean(2), rs.getBoolean(3), rs.getBoolean(4), rs.getBoolean(5));
		}
		con.close();
		ps.close();
		rs.close();

		return config;
	}
	
	public boolean updateConfig(boolean co, int type) throws Exception {
		boolean res = false;
		con = new ConnecDataBase().getConnection();
		if(type == 0) {
			sql = "update Config set allowLogin = ?";
			ps = con.prepareStatement(sql);
			ps.setBoolean(1, co);
		}				
		else if(type == 1) {
			sql = "update Config set allowRegistry = ?";
			ps = con.prepareStatement(sql);
			ps.setBoolean(1, co);
		}
		else if(type == 2) {
			sql = "update Config set allowPost = ?";
			ps = con.prepareStatement(sql);
			ps.setBoolean(1, co);
		}
		else if(type == 3) {
			sql = "update Config set allowChat = ?";
			ps = con.prepareStatement(sql);
			ps.setBoolean(1, co);
		}			
		else  {
			sql = "update Config set Forum = ?";
			ps = con.prepareStatement(sql);
			ps.setBoolean(1, co);
		}			

		res = ps.executeUpdate() > 0;

		con.close();
		ps.close();

		return res;		
	}
}
