package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

import Bean.Categorybean;
import Bean.Postbean;
import Connection.ConnecDataBase;

public class Postdao {
	String sql;
	Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;

	public ArrayList<Postbean> getPost() throws Exception {
		ArrayList<Postbean> ds = new ArrayList<Postbean>();
		sql = "select * from Post order by createdAt desc";

		con = new ConnecDataBase().getConnection();
		ps = con.prepareStatement(sql);
		rs = ps.executeQuery();
		while (rs.next()) {
			// 1 2 3 4 5 6 7
			// long id, long authorId, long parentId, String title, String slug, String
			// summary, boolean solved,
			// 8 9 10 11
			// Timestamp createdAt, Timestamp updatedAt, Timestamp publishedAt, String
			// content
			ds.add(new Postbean(rs.getLong(1), rs.getLong(2), rs.getLong(3), rs.getString(4), rs.getString(5),
					rs.getString(6), rs.getBoolean(7), rs.getTimestamp(8), rs.getTimestamp(9), rs.getString(10),
					rs.getLong(11), rs.getBoolean(12), rs.getBoolean(13)));
		}
		con.close();
		ps.close();
		rs.close();

		return ds;
	}

	public ArrayList<Postbean> getRecentPost() throws Exception {
		ArrayList<Postbean> ds = new ArrayList<Postbean>();
		sql = "select top 3 * from Post as p order by p.createdAt desc";

		con = new ConnecDataBase().getConnection();
		ps = con.prepareStatement(sql);
		rs = ps.executeQuery();
		while (rs.next()) {
			// 1 2 3 4 5 6 7
			// long id, long authorId, long parentId, String title, String slug, String
			// summary, boolean solved,
			// 8 9 10 11
			// Timestamp createdAt, Timestamp updatedAt, Timestamp publishedAt, String
			// content
			ds.add(new Postbean(rs.getLong(1), rs.getLong(2), rs.getLong(3), rs.getString(4), rs.getString(5),
					rs.getString(6), rs.getBoolean(7), rs.getTimestamp(8), rs.getTimestamp(9), rs.getString(10),
					rs.getLong(11), rs.getBoolean(12), rs.getBoolean(13)));
		}
		con.close();
		ps.close();
		rs.close();

		return ds;
	}

	public Long countQuestion() throws Exception {
		Long res = (long) 0;
		sql = "select count(*) as total from Post where type = 0 and show = 1";

		con = new ConnecDataBase().getConnection();
		ps = con.prepareStatement(sql);
		rs = ps.executeQuery();
		if (rs.next())
			res = (long) rs.getInt(1);
		con.close();
		ps.close();
		rs.close();

		return res;
	}

	public Long countAnswer() throws Exception {
		sql = "select count(*) as total from Post where type = 1 and show = 1";
		Long res = (long) 0;

		con = new ConnecDataBase().getConnection();
		ps = con.prepareStatement(sql);
		rs = ps.executeQuery();
		if (rs.next())
			res = (long) rs.getInt(1);
		con.close();
		ps.close();
		rs.close();

		return res;
	}

	public Long countView(long id) throws Exception {
		sql = "select slView from Post where id = ?";
		Long res = (long) 0;

		con = new ConnecDataBase().getConnection();
		ps = con.prepareStatement(sql);
		ps.setLong(1, id);
		rs = ps.executeQuery();
		if (rs.next())
			res = (long) rs.getInt(1);
		con.close();
		ps.close();
		rs.close();

		return res;
	}

	public boolean subView(long id) throws Exception {
		boolean res = false;
		Long slview = countView(id);
		sql = "update Post set slView = ? where id = ?";

		con = new ConnecDataBase().getConnection();
		ps = con.prepareStatement(sql);
		ps.setLong(1, slview + 1);
		ps.setLong(2, id);
		res = ps.executeUpdate() > 0;
		con.close();
		ps.close();

		return res;
	}

	public boolean updateSolved(long id, boolean solved) throws Exception {
		boolean res = false;
		sql = "update Post set solved = ? where id = ?";

		con = new ConnecDataBase().getConnection();
		ps = con.prepareStatement(sql);
		ps.setBoolean(1, solved);
		ps.setLong(2, id);
		res = ps.executeUpdate() > 0;
		con.close();
		ps.close();

		return res;
	}

	public boolean createPost(Long authorId, String title, String summary, String content, boolean type, Long categoryId, boolean show) throws Exception {
		boolean res = false;
		Date date = new Date();
		Timestamp createdAt = new Timestamp(date.getTime());
		String slug = VNCharacterUtils.removeAccent(title).toLowerCase().replace(' ', '-');
		
		sql = "insert into Post(authorId, title, slug, summary,createdAt,content,type, show) values (?,?,?,?,?,?,?,?)";

		con = new ConnecDataBase().getConnection();
		ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		ps.setLong(1, authorId);
		ps.setString(2, title);
		ps.setString(3, slug);
		ps.setString(4, summary);
		ps.setTimestamp(5, createdAt);
		ps.setString(6, content);
		ps.setBoolean(7, type);
		ps.setBoolean(8, show);

		int idRow = ps.executeUpdate();

		if (idRow != 0) {
			try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
				if (generatedKeys.next()) {
					long postid = generatedKeys.getLong(1);
					Categorydao catedao = new Categorydao();
					if (catedao.createPostCategory(postid, categoryId))
						res = true;
					else
						res = false;

				}
			}
		}
		con.close();
		ps.close();

		return res;
	}

	public boolean updatePostCategory(Long id, Long categoryId) throws Exception {
		boolean res = false;
		sql = "update Post_Category set categoryId = ? where postId = ?";

		con = new ConnecDataBase().getConnection();
		ps = con.prepareStatement(sql);
		ps.setLong(1, categoryId);
		ps.setLong(2, id);

		res = ps.executeUpdate() > 0;

		con.close();
		ps.close();

		return res;
	}

	public boolean updatePost(Long id, String title, String summary, String content, boolean type, Long categoryId)
			throws Exception {
		boolean res = false;
		Date date = new Date();
		Timestamp updatedAt = new Timestamp(date.getTime());
		String slug = title.toLowerCase().replace(' ', '-');
		String sql = "update Post set title = ?, slug = ?, summary = ?, updatedAt = ?, [content] = ?, [type] = ? where id = ?";

		updatePostCategory(id, categoryId);
		con = new ConnecDataBase().getConnection();
		ps = con.prepareStatement(sql);
		ps.setString(1, title);
		ps.setString(2, slug);
		ps.setString(3, summary);
		ps.setTimestamp(4, updatedAt);
		ps.setString(5, content);
		ps.setBoolean(6, type);
		ps.setLong(7, id);

		res = ps.executeUpdate() > 0;

		con.close();
		ps.close();

		return res;
	}

	public boolean deletePostCategory(long idp) throws Exception {
		boolean res = false;
		sql = "delete from Post_Category where postId = ?";

		con = new ConnecDataBase().getConnection();
		ps = con.prepareStatement(sql);
		ps.setLong(1, idp);

		res = ps.executeUpdate() > 0;

		con.close();
		ps.close();

		return res;
	}

	public boolean deletePostCmt(long idp) throws Exception {
		boolean res = false;
		sql = "delete from Post_Comment where postId = ?";

		con = new ConnecDataBase().getConnection();
		ps = con.prepareStatement(sql);
		ps.setLong(1, idp);

		res = ps.executeUpdate() > 0;

		con.close();
		ps.close();

		return res;
	}

	public boolean deletePost(String post) throws Exception {
		boolean res = false;
		Reportdao rpdao = new Reportdao();
		rpdao.deleteReport(getPostbySlug(post).getId());
		PostLikedao plikedao = new PostLikedao();
		plikedao.deleteAllLikebyPostId(getPostbySlug(post).getId());
		deletePostCategory(getPostbySlug(post).getId());
		deletePostCmt(getPostbySlug(post).getId());

		sql = "delete from Post where slug = ?";

		con = new ConnecDataBase().getConnection();
		ps = con.prepareStatement(sql);
		ps.setString(1, post);

		res = ps.executeUpdate() > 0;

		con.close();
		ps.close();

		return res;
	}

	public Postbean getPostbySlug(String slug) throws Exception {
		Postbean post = new Postbean();
		sql = "SELECT * FROM Post WHERE slug = ?";

		con = new ConnecDataBase().getConnection();
		ps = con.prepareStatement(sql);
		ps.setString(1, slug);
		rs = ps.executeQuery();
		if (rs.next()) {
			// long id, long parentId, String title, String slug, String content
			// 1 2 3 4 5
			post = new Postbean(rs.getLong(1), rs.getLong(2), rs.getLong(3), rs.getString(4), rs.getString(5),
					rs.getString(6), rs.getBoolean(7), rs.getTimestamp(8), rs.getTimestamp(9), rs.getString(10),
					rs.getLong(11), rs.getBoolean(12), rs.getBoolean(13));			
		}
		con.close();
		ps.close();
		rs.close();

		return post;
	}

	public Categorybean getCatebySlug(String slug) throws Exception {
		Categorybean cate = new Categorybean();
		sql = "select c.*\r\n" + "from Post as p \r\n" + "JOIN Post_Category as pc on p.id = pc.postId \r\n"
				+ "JOIN Category as c on pc.categoryId = c.id\r\n" + "where p.slug like ?";

		con = new ConnecDataBase().getConnection();
		ps = con.prepareStatement(sql);
		ps.setString(1, slug);
		rs = ps.executeQuery();
		if (rs.next()) {
			// long id, long parentId, String title, String slug, String content
			// 1 2 3 4 5
			cate.setId(rs.getLong(1));
			cate.setParentId(rs.getLong(2));
			cate.setTitle(rs.getString(3));
			cate.setSlug(rs.getString(4));
			cate.setContent(rs.getString(5));
		}
		con.close();
		ps.close();
		rs.close();

		return cate;
	}

	public boolean admitPost(String post) throws Exception {
		boolean res = false;
		sql = "UPDATE Post SET show = 1 WHERE slug = ?";

		con = new ConnecDataBase().getConnection();
		ps = con.prepareStatement(sql);
		ps.setString(1, post);

		res = ps.executeUpdate() > 0;

		con.close();
		ps.close();

		return res;
	}
	public Long getIdCateByPost(String slug) throws Exception{
		sql = "select c.id from Post as p\r\n"
				+ "JOIN Post_Category as pc on p.id = pc.postId\r\n"
				+ "JOIN Category as c on pc.categoryId = c.id\r\n"
				+ "where p.slug = ?";
		
		Long res = (long) 0;

		con = new ConnecDataBase().getConnection();
		ps = con.prepareStatement(sql);
		ps.setString(1, slug);
		rs = ps.executeQuery();
		if (rs.next())
			res = (long) rs.getInt(1);
		con.close();
		ps.close();
		rs.close();

		return res;
	}
	public ArrayList<Postbean> getInvolvePost(String slug) throws Exception {
		Long idcate = getIdCateByPost(slug);
		ArrayList<Postbean> ds = new ArrayList<Postbean>();
		sql = "select top 5 * from Post as p\r\n"
				+ "JOIN Post_Category as pc on p.id = pc.postId\r\n"
				+ "JOIN Category as c on pc.categoryId = c.id\r\n"
				+ "where c.id = ? and p.slug not like ?\r\n"
				+ "order by p.createdAt desc";

		con = new ConnecDataBase().getConnection();
		ps = con.prepareStatement(sql);
		ps.setLong(1, idcate);
		ps.setString(2, slug);
		rs = ps.executeQuery();
		while (rs.next()) {		
			
			ds.add(new Postbean(rs.getLong(1), rs.getLong(2), rs.getLong(3), rs.getString(4), rs.getString(5),
					rs.getString(6), rs.getBoolean(7), rs.getTimestamp(8), rs.getTimestamp(9), rs.getString(10),
					rs.getLong(11), rs.getBoolean(12), rs.getBoolean(13)));
		}
		con.close();
		ps.close();
		rs.close();

		return ds;
	}
	
	public ArrayList<Postbean> getAllPostByCategory(long idcategory) throws Exception {
		ArrayList<Postbean> ds = new ArrayList<Postbean>();
		sql = "select p.* from Post as p\r\n"
				+ "join Post_Category as pc on p.id = pc.postId\r\n"
				+ "where pc.categoryId = ? order by createdAt desc";

		con = new ConnecDataBase().getConnection();
		ps = con.prepareStatement(sql);
		ps.setLong(1, idcategory);		
		rs = ps.executeQuery();
		while (rs.next()) {		
			
			ds.add(new Postbean(rs.getLong(1), rs.getLong(2), rs.getLong(3), rs.getString(4), rs.getString(5),
					rs.getString(6), rs.getBoolean(7), rs.getTimestamp(8), rs.getTimestamp(9), rs.getString(10),
					rs.getLong(11), rs.getBoolean(12), rs.getBoolean(13)));
		}
		con.close();
		ps.close();
		rs.close();

		return ds;
	}

}
