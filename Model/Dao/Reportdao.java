package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import Connection.ConnecDataBase;

public class Reportdao {
	String sql;
	Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;

	public Long getSLReport(long postId) throws Exception {
		sql = "select count(*) from Post_Report where postId = ?";
		Long res = (long) 0;

		con = new ConnecDataBase().getConnection();
		ps = con.prepareStatement(sql);
		ps.setLong(1, postId);
		rs = ps.executeQuery();
		if (rs.next())
			res = (long) rs.getInt(1);
		con.close();
		ps.close();
		rs.close();

		return res;
	}

	public boolean reportPost(long postId, String content) throws Exception {
		sql = "insert into Post_Report(postId, content) values (?,?)";
		boolean res = false;

		con = new ConnecDataBase().getConnection();
		ps = con.prepareStatement(sql);
		ps.setLong(1, postId);
		ps.setString(2, content);

		res = ps.executeUpdate() > 0;

		con.close();
		ps.close();

		return res;
	}

	public boolean deleteReport(long postId) throws Exception {
		boolean res = false;
		sql = "delete from Post_Report where postId = ?";

		con = new ConnecDataBase().getConnection();
		ps = con.prepareStatement(sql);
		ps.setLong(1, postId);

		res = ps.executeUpdate() > 0;

		con.close();
		ps.close();

		return res;
	}

	public ArrayList<String> getReportContent(String slug) throws Exception {
		ArrayList<String> listRP = new ArrayList<String>();
		sql = "SELECT content FROM Post_Report WHERE postId = ?";

		con = new ConnecDataBase().getConnection();
		ps = con.prepareStatement(sql);
		ps.setLong(1, new Postdao().getPostbySlug(slug).getId());
		rs = ps.executeQuery();
		if (rs.next())
			listRP.add(rs.getString(1));
		
		con.close();
		ps.close();
		rs.close();

		return listRP;
	}
}
