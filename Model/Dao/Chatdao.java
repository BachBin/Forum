package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import Bean.Categorybean;
import Bean.Chat;
import Connection.ConnecDataBase;

public class Chatdao {
	String sql;
	Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	
	public boolean insertChat(Long fromId, Long toId, String message) throws Exception {
		boolean res = false;
		
		sql = "insert into Chat(fromId, toId, message) values(?,?,?)";

		con = new ConnecDataBase().getConnection();
		ps = con.prepareStatement(sql);
		ps.setLong(1, fromId);
		ps.setLong(2, toId);
		ps.setString(3, message);

		res = ps.executeUpdate() > 0;

		con.close();
		ps.close();

		return res;
	}
	public ArrayList<Chat> getChat(Long fromId, Long toId) throws Exception {
		ArrayList<Chat> ds = new ArrayList<Chat>();
		sql = "select * from Chat where (fromId = ? and toId = ?) or (fromId = ? and toId = ?) order by id";

		con = new ConnecDataBase().getConnection();
		ps = con.prepareStatement(sql);
		ps.setLong(1, fromId);
		ps.setLong(2, toId);
		ps.setLong(3, toId);
		ps.setLong(4, fromId);
		rs = ps.executeQuery();
		while (rs.next()) {			
			ds.add(new Chat(rs.getLong(1), rs.getLong(2), rs.getLong(3), rs.getString(4)));
		}
		con.close();
		ps.close();
		rs.close();

		return ds;
	}	
	public Chat getOneChat(Long fromId, Long toId) throws Exception {
		Chat chat = new Chat();
		sql = "select Top 1* from Chat where (fromId = ? and toId = ?) or (fromId = ? and toId = ?) order by id desc ";

		con = new ConnecDataBase().getConnection();
		ps = con.prepareStatement(sql);
		ps.setLong(1, fromId);
		ps.setLong(2, toId);
		ps.setLong(3, toId);
		ps.setLong(4, fromId);
		
		rs = ps.executeQuery();
		if (rs.next()) {			
			chat = new Chat(rs.getLong(1), rs.getLong(2), rs.getLong(3), rs.getString(4));
		}
		con.close();
		ps.close();
		rs.close();

		return chat;
	}
}
