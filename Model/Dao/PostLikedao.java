package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import Connection.ConnecDataBase;

public class PostLikedao {
	String sql;
	Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;

	public boolean isLike(Long authorId, Long postId, int islike) throws Exception {
		boolean res = false;
		sql = "insert into PostLike (authorId, postId, isLike) values (?,?,?)";
		con = new ConnecDataBase().getConnection();
		ps = con.prepareStatement(sql);
		ps.setLong(1, authorId);
		ps.setLong(2, postId);
		ps.setInt(3, islike);

		res = ps.executeUpdate() > 0;

		con.close();
		ps.close();

		return res;
	}
	public long getIdMax() throws Exception {
		long id = 0;
		sql = "select max(id) from PostLike";

		con = new ConnecDataBase().getConnection();
		ps = con.prepareStatement(sql);		
		rs = ps.executeQuery();
		if (rs.next())
			id = (long) rs.getInt(1);
		con.close();
		ps.close();
		rs.close();

		return id;
	}
	public boolean updateIdentity() throws Exception {
		boolean res = false;
		long id = getIdMax();
		sql = "DBCC CHECKIDENT (PostLike, RESEED, ?)";
		con = new ConnecDataBase().getConnection();
		ps = con.prepareStatement(sql);		
		ps.setLong(1, id);
		res = ps.executeUpdate() > 0;

		con.close();
		ps.close();

		return res;
	}
	public boolean deleteisLike(Long authorId, Long postId) throws Exception {
		boolean res = false;		
		sql = "delete from PostLike where authorId = ? and postId = ?";
		con = new ConnecDataBase().getConnection();
		ps = con.prepareStatement(sql);
		ps.setLong(1, authorId);
		ps.setLong(2, postId);

		res = ps.executeUpdate() > 0;

		con.close();
		ps.close();

		return res;
	}

	public long soLikePost(Long postId) throws Exception {
		long solike = 0;
		sql = "select count (*) from PostLike where postId = ? and isLike = 2";

		con = new ConnecDataBase().getConnection();
		ps = con.prepareStatement(sql);
		ps.setLong(1, postId);
		rs = ps.executeQuery();
		if (rs.next())
			solike = (long) rs.getInt(1);
		con.close();
		ps.close();
		rs.close();

		return solike;
	}

	public long soDislikePost(Long postId) throws Exception {
		long sodislike = 0;
		sql = "select count (*) from PostLike where postId = ? and isLike = 1";

		con = new ConnecDataBase().getConnection();
		ps = con.prepareStatement(sql);
		ps.setLong(1, postId);
		rs = ps.executeQuery();
		if (rs.next())
			sodislike = (long) rs.getInt(1);
		con.close();
		ps.close();
		rs.close();

		return sodislike;
	}

	public int getIdLike(Long postId, Long authorId) throws Exception {
		int id = 0;
		sql = "select isLike from PostLike where postId = ? and authorId = ?";

		con = new ConnecDataBase().getConnection();
		ps = con.prepareStatement(sql);
		ps.setLong(1, postId);
		ps.setLong(2, authorId);
		rs = ps.executeQuery();
		if (rs.next())
			id = rs.getInt(1);
		con.close();
		ps.close();
		rs.close();

		return id;
	}

	public ArrayList<Long> getIdLikeList(Long postId) throws Exception {
		ArrayList<Long> res = new ArrayList<Long>();
		sql = "select authorId from PostLike where postId = ? and isLike = 2";

		con = new ConnecDataBase().getConnection();
		ps = con.prepareStatement(sql);
		ps.setLong(1, postId);
		rs = ps.executeQuery();
		if (rs.next())
			res.add((long) rs.getInt(1));

		con.close();
		ps.close();
		rs.close();

		return res;
	}

	public ArrayList<Long> getIdDislikeList(Long postId) throws Exception {
		ArrayList<Long> res = new ArrayList<Long>();
		sql = "select authorId from PostLike where postId = ? and isLike = 1";

		con = new ConnecDataBase().getConnection();
		ps = con.prepareStatement(sql);
		ps.setLong(1, postId);
		rs = ps.executeQuery();
		if (rs.next())
			res.add((long) rs.getInt(1));

		con.close();
		ps.close();
		rs.close();

		return res;
	}

	public boolean deleteAllLikebyPostId(Long postId) throws Exception {
		boolean res = false;
		sql = "delete from PostLike where postId = ?";
		con = new ConnecDataBase().getConnection();
		ps = con.prepareStatement(sql);
		ps.setLong(1, postId);

		res = ps.executeUpdate() > 0;

		con.close();
		ps.close();

		return res;
	}
}
