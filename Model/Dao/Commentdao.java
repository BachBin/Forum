package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

import Bean.CommentVMbean;
import Bean.Commentbean;
import Connection.ConnecDataBase;

public class Commentdao {
	String sql;
	Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;

	public Commentbean getCommentByIdCmt(Long cmtid) throws Exception {
		Commentbean cmt = new Commentbean();
		sql = "select * from Post_Comment where id = ?";

		con = new ConnecDataBase().getConnection();
		ps = con.prepareStatement(sql);
		ps.setLong(1, cmtid);
		rs = ps.executeQuery();
		if (rs.next()) {
			cmt.setId(rs.getLong(1));
			cmt.setPostId(rs.getLong(2));
			cmt.setParentId(rs.getLong(3));
			cmt.setAuthorId(rs.getLong(4));
			cmt.setCreatedAt(rs.getTimestamp(5));
			cmt.setUpdateAt(rs.getTimestamp(6));
			cmt.setContent(rs.getString(7));
		}
		con.close();
		ps.close();
		rs.close();

		return cmt;
	}
	
	public ArrayList<CommentVMbean> getComment(Long postid) throws Exception {
		ArrayList<CommentVMbean> ds = new ArrayList<CommentVMbean>();
		sql = "select c.*,u.firstName, u.middleName, u.lastName, u.image, u.uniqueId, \r\n"
				+ "(select count(*) from CommentLike where commentId = c.id and isLike =  2) as 'like',\r\n"
				+ "(select count(*) from CommentLike where commentId = c.id and isLike =  1) as 'dislike'\r\n"
				+ "from Post_Comment as c JOIN Users as u on c.authorId = u.id\r\n"
				+ "where postId = ? order by createdAt asc";

		con = new ConnecDataBase().getConnection();
		ps = con.prepareStatement(sql);
		ps.setLong(1, postid);
		rs = ps.executeQuery();
		while (rs.next()) {
			// long id, long postId, long parentId, long authorid,Timestamp createdAt,
			// Timestamp updateAt, String content
			// 1 2 3 4 5 6 7
			// String firstName String middleName String lastName String image
			// 8 9 10 11
			CommentVMbean cmt = new CommentVMbean();
			cmt.setId(rs.getLong(1));
			cmt.setPostId(rs.getLong(2));
			cmt.setParentId(rs.getLong(3));
			cmt.setAuthorId(rs.getLong(4));
			cmt.setCreatedAt(rs.getTimestamp(5));
			cmt.setUpdateAt(rs.getTimestamp(6));
			cmt.setContent(rs.getString(7));			
			cmt.setFirstName(rs.getString(8));
			cmt.setMiddleName(rs.getString(9));
			cmt.setLastName(rs.getString(10));
			cmt.setImage(rs.getString(11));
			cmt.setUniqueId(rs.getLong(12));
			cmt.setLike(rs.getLong(13));
			cmt.setDislike(rs.getLong(14));
			ds.add(cmt);
		}
		con.close();
		ps.close();
		rs.close();

		return ds;
	}	
	
	public boolean createCmt(Long postId, Long authorId, String content) throws Exception {
		boolean res = false;

		Date date = new Date();
		Timestamp createdAt = new Timestamp(date.getTime());
		sql = "insert into Post_Comment(postId, authorId, createdAt, content) values (?,?,?,?)";

		con = new ConnecDataBase().getConnection();
		ps = con.prepareStatement(sql);
		ps.setLong(1, postId);
		ps.setLong(2, authorId);
		ps.setTimestamp(3, createdAt);
		ps.setString(4, content);

		res = ps.executeUpdate() > 0;

		con.close();
		ps.close();

		return res;
	}
	
	public boolean updateCmt(Long id, String content) throws Exception {
		boolean res = false;
		
		Date date = new Date();
		Timestamp updateAt = new Timestamp(date.getTime());
		
		sql = "update Post_Comment set content = ?, updateAt = ? where id = ?";
		con = new ConnecDataBase().getConnection();
		ps = con.prepareStatement(sql);
		ps.setString(1, content);
		ps.setTimestamp(2, updateAt);
		ps.setLong(3, id);

		res = ps.executeUpdate() > 0;

		con.close();
		ps.close();

		return res;
	}
	
	public boolean deleteCmt(Long id) throws Exception {
		boolean res = false;
		//Xoa like cua comment
		res = deleteAllLikeCmt(id);
		//Xoa comment
		sql = "delete from Post_Comment where id = ?";
		con = new ConnecDataBase().getConnection();
		ps = con.prepareStatement(sql);
		ps.setLong(1, id);

		res = ps.executeUpdate() > 0;

		con.close();
		ps.close();

		return res;
	}
	
	public long getSLCmt(Long id) throws Exception {
		long socmt = 0;
		sql = "select count (*) from Post_Comment where postId = ?";

		con = new ConnecDataBase().getConnection();
		ps = con.prepareStatement(sql);
		ps.setLong(1, id);
		rs = ps.executeQuery();
		if (rs.next())
			socmt = (long) rs.getInt(1);
		con.close();
		ps.close();
		rs.close();

		return socmt;
	}
	public long getSLLikeAllCmt(Long idpost) throws Exception {
		long sol = 0;
		sql = "select count(*)\r\n"
				+ "from Post as p\r\n"
				+ "JOIN Post_Comment as pm on p.id = pm.postId\r\n"
				+ "JOIN CommentLike as cl on pm.id = cl.commentId\r\n"
				+ "where p.id = ? and cl.isLike = 2";

		con = new ConnecDataBase().getConnection();
		ps = con.prepareStatement(sql);
		ps.setLong(1, idpost);
		rs = ps.executeQuery();
		if (rs.next())
			sol = (long) rs.getInt(1);
		con.close();
		ps.close();
		rs.close();

		return sol;
	}
	
	
	//Xy ly like comment
	
	public long soLikeCmt(Long commentId) throws Exception {
		long solike = 0;
		sql = "select count (*) from CommentLike where commentId = ? and isLike = 2";

		con = new ConnecDataBase().getConnection();
		ps = con.prepareStatement(sql);
		ps.setLong(1, commentId);
		rs = ps.executeQuery();
		if (rs.next())
			solike = (long) rs.getInt(1);
		con.close();
		ps.close();
		rs.close();

		return solike;
	}

	public long soDislikeCmt(Long commentId) throws Exception {
		long sodislike = 0;
		sql = "select count (*) from CommentLike where commentId = ? and isLike = 1";

		con = new ConnecDataBase().getConnection();
		ps = con.prepareStatement(sql);
		ps.setLong(1, commentId);
		rs = ps.executeQuery();
		if (rs.next())
			sodislike = (long) rs.getInt(1);
		con.close();
		ps.close();
		rs.close();

		return sodislike;
	}

	public boolean isLikeCmt(Long authorId, Long commentId, int islike) throws Exception {
		boolean res = false;		
		sql = "insert into CommentLike(authorId, commentId, isLike) values (?, ?, ?)";

		con = new ConnecDataBase().getConnection();
		ps = con.prepareStatement(sql);
		ps.setLong(1, authorId);
		ps.setLong(2, commentId);
		ps.setInt(3, islike);

		res = ps.executeUpdate() > 0;

		con.close();
		ps.close();

		return res;
	}
	public long getIdMaxLikeCmt() throws Exception {
		long id = 0;
		sql = "select max(id) from CommentLike";

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
		long id = getIdMaxLikeCmt();
		sql = "DBCC CHECKIDENT (CommentLike, RESEED, ?)";
		con = new ConnecDataBase().getConnection();
		ps = con.prepareStatement(sql);		
		ps.setLong(1, id);
		
		res = ps.executeUpdate() > 0;
		con.close();
		ps.close();
		
		return res;
	}
	
	public boolean deleteisLike(Long authorId, Long commentId) throws Exception {
		boolean res = false;		
		sql = "delete from CommentLike where authorId = ? and commentId = ?";
		con = new ConnecDataBase().getConnection();
		ps = con.prepareStatement(sql);
		ps.setLong(1, authorId);
		ps.setLong(2, commentId);

		res = ps.executeUpdate() > 0;

		con.close();
		ps.close();

		return res;
	}
	
	public int getIdLike(Long authorId, Long commentId) throws Exception {
		int id = 0;
		sql = "select isLike from CommentLike where authorId = ? and commentId = ? ";

		con = new ConnecDataBase().getConnection();
		ps = con.prepareStatement(sql);
		ps.setLong(1, authorId);
		ps.setLong(2, commentId);
		rs = ps.executeQuery();
		if (rs.next())
			id = rs.getInt(1);
		con.close();
		ps.close();
		rs.close();

		return id;
	}

	public ArrayList<Long> getIdLikeList(Long commentId) throws Exception {
		ArrayList<Long> res = new ArrayList<Long>();
		sql = "select authorId from CommentLike where commentId = ? and isLike = 2";

		con = new ConnecDataBase().getConnection();
		ps = con.prepareStatement(sql);
		ps.setLong(1, commentId);
		rs = ps.executeQuery();
		if (rs.next())
			res.add((long) rs.getInt(1));

		con.close();
		ps.close();
		rs.close();

		return res;
	}

	public ArrayList<Long> getIdDislikeList(Long commentId) throws Exception {
		ArrayList<Long> res = new ArrayList<Long>();
		sql = "select authorId from CommentLike where commentId = ? and isLike = 1";

		con = new ConnecDataBase().getConnection();
		ps = con.prepareStatement(sql);
		ps.setLong(1, commentId);
		rs = ps.executeQuery();
		if (rs.next())
			res.add((long) rs.getInt(1));

		con.close();
		ps.close();
		rs.close();

		return res;
	}	
	
	public boolean deleteAllLikeCmt(Long id) throws Exception {
		boolean res = false;
		sql = "delete from CommentLike where commentId = ?";
		con = new ConnecDataBase().getConnection();
		ps = con.prepareStatement(sql);
		ps.setLong(1, id);

		res = ps.executeUpdate() > 0;

		con.close();
		ps.close();

		return res;
	}

}
