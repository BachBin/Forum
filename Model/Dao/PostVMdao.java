package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import Bean.PostVMbean;
import Connection.ConnecDataBase;

public class PostVMdao {
	String sql;
	Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;

	public long getSLRecentQuestionbySearch(String search) throws Exception {
		long res = 0;
		sql = "select count(*) from Post as p where p.type = 0 and (title like ? or summary like ? or content like ?)";

		con = new ConnecDataBase().getConnection();
		ps = con.prepareStatement(sql);
		ps.setString(1, "%" + search + "%");
		ps.setString(2, "%" + search + "%");
		ps.setString(3, "%" + search + "%");
		rs = ps.executeQuery();
		if (rs.next()) {
			res = rs.getLong(1);
		}
		con.close();
		ps.close();
		rs.close();

		return res;
	}

	public ArrayList<PostVMbean> getRecentQuestionPagebySearch(long index, long sobai, String search) throws Exception {
		ArrayList<PostVMbean> ds = new ArrayList<PostVMbean>();
		sql = "select p.*, u.image, u.type, u.uniqueId, ((select count(*) from Post_Comment where postId = p.id)) as answer\r\n"
				+ "from Post as p JOIN Users as u on p.authorId = u.id\r\n"
				+ "where p.type = 0 and (title like ? or summary like ? or content like ?)\r\n"
				+ "order by p.createdAt desc\r\n" + "OFFSET ? rows fetch next ? rows ONLY";

		con = new ConnecDataBase().getConnection();
		ps = con.prepareStatement(sql);
		ps.setString(1, "%" + search + "%");
		ps.setString(2, "%" + search + "%");
		ps.setString(3, "%" + search + "%");
		ps.setLong(4, (index - 1) * sobai);
		ps.setLong(5, sobai);
		rs = ps.executeQuery();
		while (rs.next()) {
			// 1 2 3 4 5 6 7
			// long id, long authorId, long parentId, String title, String slug, String
			// summary, boolean solved,
			// 8 9 10 11 12 13 14
			// Timestamp createdAt, Timestamp updatedAt, String content, Long view, boolean
			// type, String image, Long answer
			PostVMbean pvm = new PostVMbean();
			pvm.setId(rs.getLong(1));
			pvm.setAuthorId(rs.getLong(2));
			pvm.setParentId(rs.getLong(3));
			pvm.setTitle(rs.getString(4));
			pvm.setSlug(rs.getString(5));
			pvm.setSummary(rs.getString(6));
			pvm.setSolved(rs.getBoolean(7));
			pvm.setCreatedAt(rs.getTimestamp(8));
			pvm.setUpdatedAt(rs.getTimestamp(9));
			pvm.setContent(rs.getString(10));
			pvm.setView(rs.getLong(11));
			pvm.setType(rs.getBoolean(12));
			pvm.setShow(rs.getBoolean(13));
			pvm.setImage(rs.getString(14));
			pvm.setTypeUser(rs.getInt(15));
			pvm.setUniqueId(rs.getLong(16));
			pvm.setAnswer(rs.getLong(17));
			ds.add(pvm);
		}
		con.close();
		ps.close();
		rs.close();

		return ds;
	}

	public long getSLRecentQuestion() throws Exception {
		long res = 0;
		sql = "select count(*) from Post as p where p.type = 0";

		con = new ConnecDataBase().getConnection();
		ps = con.prepareStatement(sql);
		rs = ps.executeQuery();
		if (rs.next()) {
			res = rs.getLong(1);
		}
		con.close();
		ps.close();
		rs.close();

		return res;
	}

	public ArrayList<PostVMbean> getRecentQuestionPage(long index, long sobai) throws Exception {
		ArrayList<PostVMbean> ds = new ArrayList<PostVMbean>();
		sql = "select p.*, u.image, u.type, u.uniqueId, ((select count(*) from Post_Comment where postId = p.id)) as answer\r\n"
				+ "from Post as p JOIN Users as u on p.authorId = u.id\r\n" + "where p.type = 0\r\n"
				+ "order by p.createdAt desc\r\n" + "OFFSET ? rows fetch next ? rows ONLY";

		con = new ConnecDataBase().getConnection();
		ps = con.prepareStatement(sql);
		ps.setLong(1, (index - 1) * sobai);
		ps.setLong(2, sobai);
		rs = ps.executeQuery();
		while (rs.next()) {
			
			PostVMbean pvm = new PostVMbean();
			pvm.setId(rs.getLong(1));
			pvm.setAuthorId(rs.getLong(2));
			pvm.setParentId(rs.getLong(3));
			pvm.setTitle(rs.getString(4));
			pvm.setSlug(rs.getString(5));
			pvm.setSummary(rs.getString(6));
			pvm.setSolved(rs.getBoolean(7));
			pvm.setCreatedAt(rs.getTimestamp(8));
			pvm.setUpdatedAt(rs.getTimestamp(9));
			pvm.setContent(rs.getString(10));
			pvm.setView(rs.getLong(11));
			pvm.setType(rs.getBoolean(12));
			pvm.setShow(rs.getBoolean(13));
			pvm.setImage(rs.getString(14));
			pvm.setTypeUser(rs.getInt(15));
			pvm.setUniqueId(rs.getLong(16));
			pvm.setAnswer(rs.getLong(17));
			ds.add(pvm);
		}
		con.close();
		ps.close();
		rs.close();

		return ds;
	}

	public long getSLRecentQuestionbyCate(String slugcate) throws Exception {
		long res = 0;
		sql = "select count(*)\r\n" + "from Users as u\r\n" + "join Post as p on u.id = p.authorId\r\n"
				+ "join Post_Category as pc on p.id = pc.postId\r\n" + "join Category as c on pc.categoryId = c.id\r\n"
				+ "where c.slug = ? and p.type = 0";

		con = new ConnecDataBase().getConnection();
		ps = con.prepareStatement(sql);
		ps.setString(1, slugcate);
		rs = ps.executeQuery();
		if (rs.next()) {
			res = rs.getLong(1);
		}
		con.close();
		ps.close();
		rs.close();

		return res;
	}

	public ArrayList<PostVMbean> getRecentQuestionbyCatePage(String slugcate, long index, long sobai) throws Exception {
		ArrayList<PostVMbean> ds = new ArrayList<PostVMbean>();
		sql = "select p.* , u.image, u.type, u.uniqueId, ((select count(*) from Post_Comment where postId = p.id)) as answer\r\n"
				+ "from Users as u\r\n" + "join Post as p on u.id = p.authorId\r\n"
				+ "join Post_Category as pc on p.id = pc.postId\r\n" + "join Category as c on pc.categoryId = c.id\r\n"
				+ "where c.slug = ? and p.type = 0\r\n" + "order by p.createdAt desc\r\n"
				+ "OFFSET ? rows fetch next ? rows ONLY";

		con = new ConnecDataBase().getConnection();
		ps = con.prepareStatement(sql);
		ps.setString(1, slugcate);
		ps.setLong(2, (index - 1) * sobai);
		ps.setLong(3, sobai);
		rs = ps.executeQuery();
		while (rs.next()) {
			
			PostVMbean pvm = new PostVMbean();
			pvm.setId(rs.getLong(1));
			pvm.setAuthorId(rs.getLong(2));
			pvm.setParentId(rs.getLong(3));
			pvm.setTitle(rs.getString(4));
			pvm.setSlug(rs.getString(5));
			pvm.setSummary(rs.getString(6));
			pvm.setSolved(rs.getBoolean(7));
			pvm.setCreatedAt(rs.getTimestamp(8));
			pvm.setUpdatedAt(rs.getTimestamp(9));
			pvm.setContent(rs.getString(10));
			pvm.setView(rs.getLong(11));
			pvm.setType(rs.getBoolean(12));
			pvm.setShow(rs.getBoolean(13));
			pvm.setImage(rs.getString(14));
			pvm.setTypeUser(rs.getInt(15));
			pvm.setUniqueId(rs.getLong(16));
			pvm.setAnswer(rs.getLong(17));
			ds.add(pvm);
		}
		con.close();
		ps.close();
		rs.close();

		return ds;
	}

	public long getSLRecentQuestionbyTags(String slugtags) throws Exception {
		long res = 0;
		sql = "select count(*)\r\n" + "from Users as u\r\n" + "join Post as p on u.id = p.authorId\r\n"
				+ "join Post_Tag as pt on p.id = pt.postId\r\n" + "join Tag as t on pt.tagId = t.id\r\n"
				+ "where t.slug = ? and p.type = 0";

		con = new ConnecDataBase().getConnection();
		ps = con.prepareStatement(sql);
		ps.setString(1, slugtags);
		rs = ps.executeQuery();
		if (rs.next()) {
			res = rs.getLong(1);
		}
		con.close();
		ps.close();
		rs.close();

		return res;
	}

	public ArrayList<PostVMbean> getRecentQuestionbyTagsPage(String slugtags, long index, long sobai) throws Exception {
		ArrayList<PostVMbean> ds = new ArrayList<PostVMbean>();
		sql = "select p.* , u.image, u.type, u.uniqueId, ((select count(*) from Post_Comment where postId = p.id)) as answer\r\n"
				+ "from Users as u\r\n" + "join Post as p on u.id = p.authorId\r\n"
				+ "join Post_Tag as pt on p.id = pt.postId\r\n" + "join Tag as t on pt.tagId = t.id\r\n"
				+ "where t.slug = ? and p.type = 0\r\n" + "order by p.createdAt desc\r\n"
				+ "OFFSET ? rows fetch next ? rows ONLY";

		con = new ConnecDataBase().getConnection();
		ps = con.prepareStatement(sql);
		ps.setString(1, slugtags);
		ps.setLong(2, (index - 1) * sobai);
		ps.setLong(3, sobai);
		rs = ps.executeQuery();
		while (rs.next()) {
			
			PostVMbean pvm = new PostVMbean();
			pvm.setId(rs.getLong(1));
			pvm.setAuthorId(rs.getLong(2));
			pvm.setParentId(rs.getLong(3));
			pvm.setTitle(rs.getString(4));
			pvm.setSlug(rs.getString(5));
			pvm.setSummary(rs.getString(6));
			pvm.setSolved(rs.getBoolean(7));
			pvm.setCreatedAt(rs.getTimestamp(8));
			pvm.setUpdatedAt(rs.getTimestamp(9));
			pvm.setContent(rs.getString(10));
			pvm.setView(rs.getLong(11));
			pvm.setType(rs.getBoolean(12));
			pvm.setShow(rs.getBoolean(13));
			pvm.setImage(rs.getString(14));
			pvm.setTypeUser(rs.getInt(15));
			pvm.setUniqueId(rs.getLong(16));
			pvm.setAnswer(rs.getLong(17));
			ds.add(pvm);
		}
		con.close();
		ps.close();
		rs.close();

		return ds;
	}

	public long getSLRecentAnswerbySearch(String search) throws Exception {
		long res = 0;
		sql = "select count(*) from Post as p where p.type = 1 and (title like ? or summary like ? or content like ?)";

		con = new ConnecDataBase().getConnection();
		ps = con.prepareStatement(sql);
		ps.setString(1, "%" + search + "%");
		ps.setString(2, "%" + search + "%");
		ps.setString(3, "%" + search + "%");
		rs = ps.executeQuery();
		if (rs.next()) {
			res = rs.getLong(1);
		}
		con.close();
		ps.close();
		rs.close();

		return res;
	}

	public ArrayList<PostVMbean> getRecentAnswerPagebySearch(long index, long sobai, String search) throws Exception {
		ArrayList<PostVMbean> ds = new ArrayList<PostVMbean>();
		sql = "select p.*, u.image, u.type, u.uniqueId, ((select count(*) from Post_Comment where postId = p.id)) as answer\r\n"
				+ "from Post as p JOIN Users as u on p.authorId = u.id\r\n"
				+ "where p.type = 1 and (title like ? or summary like ? or content like ?)\r\n"
				+ "order by p.createdAt desc\r\n" + "OFFSET ? rows fetch next ? rows ONLY";

		con = new ConnecDataBase().getConnection();
		ps = con.prepareStatement(sql);
		ps.setString(1, "%" + search + "%");
		ps.setString(2, "%" + search + "%");
		ps.setString(3, "%" + search + "%");
		ps.setLong(4, (index - 1) * sobai);
		ps.setLong(5, sobai);
		rs = ps.executeQuery();
		while (rs.next()) {
			
			PostVMbean pvm = new PostVMbean();
			pvm.setId(rs.getLong(1));
			pvm.setAuthorId(rs.getLong(2));
			pvm.setParentId(rs.getLong(3));
			pvm.setTitle(rs.getString(4));
			pvm.setSlug(rs.getString(5));
			pvm.setSummary(rs.getString(6));
			pvm.setSolved(rs.getBoolean(7));
			pvm.setCreatedAt(rs.getTimestamp(8));
			pvm.setUpdatedAt(rs.getTimestamp(9));
			pvm.setContent(rs.getString(10));
			pvm.setView(rs.getLong(11));
			pvm.setType(rs.getBoolean(12));
			pvm.setShow(rs.getBoolean(13));
			pvm.setImage(rs.getString(14));
			pvm.setTypeUser(rs.getInt(15));
			pvm.setUniqueId(rs.getLong(16));
			pvm.setAnswer(rs.getLong(17));
			ds.add(pvm);
		}
		con.close();
		ps.close();
		rs.close();

		return ds;
	}

	public long getSLRecentAnswer() throws Exception {
		long res = 0;
		sql = "select count(*) from Post as p where p.type = 1";

		con = new ConnecDataBase().getConnection();
		ps = con.prepareStatement(sql);
		rs = ps.executeQuery();
		if (rs.next()) {
			res = rs.getLong(1);
		}
		con.close();
		ps.close();
		rs.close();

		return res;
	}

	public ArrayList<PostVMbean> getRecentAnswerPage(long index, long sobai) throws Exception {
		ArrayList<PostVMbean> ds = new ArrayList<PostVMbean>();
		sql = "select p.*, u.image, u.type, u.uniqueId, ((select count(*) from Post_Comment where postId = p.id)) as answer\r\n"
				+ "from Post as p JOIN Users as u on p.authorId = u.id\r\n" + "where p.type = 1\r\n"
				+ "order by p.createdAt desc\r\n" + "OFFSET ? rows fetch next ? rows ONLY";

		con = new ConnecDataBase().getConnection();
		ps = con.prepareStatement(sql);
		ps.setLong(1, (index - 1) * sobai);
		ps.setLong(2, sobai);
		rs = ps.executeQuery();
		while (rs.next()) {
			
			PostVMbean pvm = new PostVMbean();
			pvm.setId(rs.getLong(1));
			pvm.setAuthorId(rs.getLong(2));
			pvm.setParentId(rs.getLong(3));
			pvm.setTitle(rs.getString(4));
			pvm.setSlug(rs.getString(5));
			pvm.setSummary(rs.getString(6));
			pvm.setSolved(rs.getBoolean(7));
			pvm.setCreatedAt(rs.getTimestamp(8));
			pvm.setUpdatedAt(rs.getTimestamp(9));
			pvm.setContent(rs.getString(10));
			pvm.setView(rs.getLong(11));
			pvm.setType(rs.getBoolean(12));
			pvm.setShow(rs.getBoolean(13));
			pvm.setImage(rs.getString(14));
			pvm.setTypeUser(rs.getInt(15));
			pvm.setUniqueId(rs.getLong(16));
			pvm.setAnswer(rs.getLong(17));
			ds.add(pvm);
		}
		con.close();
		ps.close();
		rs.close();

		return ds;
	}

	public ArrayList<PostVMbean> getRecentAnswer() throws Exception {
		ArrayList<PostVMbean> ds = new ArrayList<PostVMbean>();
		sql = "select p.*, u.image, u.type, u.uniqueId, ((select count(*) from Post_Comment where postId = p.id)) as answer\r\n"
				+ "from Post as p JOIN Users as u on p.authorId = u.id\r\n" + "where p.type = 1\r\n"
				+ "order by p.createdAt desc";

		con = new ConnecDataBase().getConnection();
		ps = con.prepareStatement(sql);
		rs = ps.executeQuery();
		while (rs.next()) {
			
			PostVMbean pvm = new PostVMbean();
			pvm.setId(rs.getLong(1));
			pvm.setAuthorId(rs.getLong(2));
			pvm.setParentId(rs.getLong(3));
			pvm.setTitle(rs.getString(4));
			pvm.setSlug(rs.getString(5));
			pvm.setSummary(rs.getString(6));
			pvm.setSolved(rs.getBoolean(7));
			pvm.setCreatedAt(rs.getTimestamp(8));
			pvm.setUpdatedAt(rs.getTimestamp(9));
			pvm.setContent(rs.getString(10));
			pvm.setView(rs.getLong(11));
			pvm.setType(rs.getBoolean(12));
			pvm.setShow(rs.getBoolean(13));
			pvm.setImage(rs.getString(14));
			pvm.setTypeUser(rs.getInt(15));
			pvm.setUniqueId(rs.getLong(16));
			pvm.setAnswer(rs.getLong(17));
			ds.add(pvm);
		}
		con.close();
		ps.close();
		rs.close();

		return ds;
	}

	public long getSLRecentAnswerbyCate(String slugcate) throws Exception {
		long res = 0;
		sql = "select count(*)\r\n" + "from Users as u\r\n" + "join Post as p on u.id = p.authorId\r\n"
				+ "join Post_Category as pc on p.id = pc.postId\r\n" + "join Category as c on pc.categoryId = c.id\r\n"
				+ "where c.slug = ? and p.type = 1";

		con = new ConnecDataBase().getConnection();
		ps = con.prepareStatement(sql);
		ps.setString(1, slugcate);
		rs = ps.executeQuery();
		if (rs.next()) {
			return rs.getLong(1);
		}
		con.close();
		ps.close();
		rs.close();

		return res;
	}

	public ArrayList<PostVMbean> getRecentAnswerbyCatePage(String slugcate, long index, long sobai) throws Exception {
		ArrayList<PostVMbean> ds = new ArrayList<PostVMbean>();
		sql = "select p.* , u.image, u.type, u.uniqueId, ((select count(*) from Post_Comment where postId = p.id)) as answer\r\n"
				+ "from Users as u\r\n" + "join Post as p on u.id = p.authorId\r\n"
				+ "join Post_Category as pc on p.id = pc.postId\r\n" + "join Category as c on pc.categoryId = c.id\r\n"
				+ "where c.slug = ? and p.type = 1\r\n" + "order by p.createdAt desc\r\n"
				+ "OFFSET ? rows fetch next ? rows ONLY";

		con = new ConnecDataBase().getConnection();
		ps = con.prepareStatement(sql);
		ps.setString(1, slugcate);
		ps.setLong(2, (index - 1) * sobai);
		ps.setLong(3, sobai);
		rs = ps.executeQuery();
		while (rs.next()) {
			
			PostVMbean pvm = new PostVMbean();
			pvm.setId(rs.getLong(1));
			pvm.setAuthorId(rs.getLong(2));
			pvm.setParentId(rs.getLong(3));
			pvm.setTitle(rs.getString(4));
			pvm.setSlug(rs.getString(5));
			pvm.setSummary(rs.getString(6));
			pvm.setSolved(rs.getBoolean(7));
			pvm.setCreatedAt(rs.getTimestamp(8));
			pvm.setUpdatedAt(rs.getTimestamp(9));
			pvm.setContent(rs.getString(10));
			pvm.setView(rs.getLong(11));
			pvm.setType(rs.getBoolean(12));
			pvm.setShow(rs.getBoolean(13));
			pvm.setImage(rs.getString(14));
			pvm.setTypeUser(rs.getInt(15));
			pvm.setUniqueId(rs.getLong(16));
			pvm.setAnswer(rs.getLong(17));
			ds.add(pvm);
		}
		con.close();
		ps.close();
		rs.close();

		return ds;
	}

	public long getSLRecentAnswerbyTags(String slugtags) throws Exception {
		long res = 0;
		sql = "select count(*)\r\n" + "from Users as u\r\n" + "join Post as p on u.id = p.authorId\r\n"
				+ "join Post_Tag as pt on p.id = pt.postId\r\n" + "join Tag as t on pt.tagId = t.id\r\n"
				+ "where t.slug = ? and p.type = 1";

		con = new ConnecDataBase().getConnection();
		ps = con.prepareStatement(sql);
		ps.setString(1, slugtags);
		rs = ps.executeQuery();
		if (rs.next()) {
			res = rs.getLong(1);
		}
		con.close();
		ps.close();
		rs.close();

		return res;
	}

	public ArrayList<PostVMbean> getRecentAnswerbyTagsPage(String slugtags, long index, long sobai) throws Exception {
		ArrayList<PostVMbean> ds = new ArrayList<PostVMbean>();
		sql = "select p.* , u.image, u.type, u.uniqueId, ((select count(*) from Post_Comment where postId = p.id)) as answer\r\n"
				+ "from Users as u\r\n" + "join Post as p on u.id = p.authorId\r\n"
				+ "join Post_Tag as pt on p.id = pt.postId\r\n" + "join Tag as t on pt.tagId = t.id\r\n"
				+ "where t.slug = ? and p.type = 1\r\n" + "order by p.createdAt desc\r\n"
				+ "OFFSET ? rows fetch next ? rows ONLY";

		con = new ConnecDataBase().getConnection();
		ps = con.prepareStatement(sql);
		ps.setString(1, slugtags);
		ps.setLong(2, (index - 1) * sobai);
		ps.setLong(3, sobai);
		rs = ps.executeQuery();
		while (rs.next()) {
			
			PostVMbean pvm = new PostVMbean();
			pvm.setId(rs.getLong(1));
			pvm.setAuthorId(rs.getLong(2));
			pvm.setParentId(rs.getLong(3));
			pvm.setTitle(rs.getString(4));
			pvm.setSlug(rs.getString(5));
			pvm.setSummary(rs.getString(6));
			pvm.setSolved(rs.getBoolean(7));
			pvm.setCreatedAt(rs.getTimestamp(8));
			pvm.setUpdatedAt(rs.getTimestamp(9));
			pvm.setContent(rs.getString(10));
			pvm.setView(rs.getLong(11));
			pvm.setType(rs.getBoolean(12));
			pvm.setShow(rs.getBoolean(13));
			pvm.setImage(rs.getString(14));
			pvm.setTypeUser(rs.getInt(15));
			pvm.setUniqueId(rs.getLong(16));
			pvm.setAnswer(rs.getLong(17));
			ds.add(pvm);
		}
		con.close();
		ps.close();
		rs.close();

		return ds;
	}

	public long getSLNoAnswerbySearch(String search) throws Exception {
		long res = 0;
		String sql = "select count(*) \r\n" + "from Post as p \r\n"
				+ "where ((select count(*) from Post_Comment where postId = p.id)) = 0 and (title like ? or summary like ? or content like ?)";

		con = new ConnecDataBase().getConnection();
		ps = con.prepareStatement(sql);
		ps.setString(1, "%" + search + "%");
		ps.setString(2, "%" + search + "%");
		ps.setString(3, "%" + search + "%");
		rs = ps.executeQuery();
		if (rs.next()) {
			res = rs.getLong(1);
		}
		con.close();
		ps.close();
		rs.close();

		return res;
	}

	public ArrayList<PostVMbean> getNoAnswerPagebySearch(long index, long sobai, String search) throws Exception {
		ArrayList<PostVMbean> ds = new ArrayList<PostVMbean>();
		sql = "select p.*, u.image, u.type, u.uniqueId, ((select count(*) from Post_Comment where postId = p.id)) as answer\r\n"
				+ "from Post as p JOIN Users as u on p.authorId = u.id\r\n"
				+ "where ((select count(*) from Post_Comment where postId = p.id)) = 0 and (title like ? or summary like ? or content like ?)\r\n"
				+ "order by p.createdAt desc\r\n" + "OFFSET ? rows fetch next ? rows ONLY";

		con = new ConnecDataBase().getConnection();
		ps = con.prepareStatement(sql);
		ps.setString(1, "%" + search + "%");
		ps.setString(2, "%" + search + "%");
		ps.setString(3, "%" + search + "%");
		ps.setLong(4, (index - 1) * sobai);
		ps.setLong(5, sobai);
		rs = ps.executeQuery();
		while (rs.next()) {			
			PostVMbean pvm = new PostVMbean();
			pvm.setId(rs.getLong(1));
			pvm.setAuthorId(rs.getLong(2));
			pvm.setParentId(rs.getLong(3));
			pvm.setTitle(rs.getString(4));
			pvm.setSlug(rs.getString(5));
			pvm.setSummary(rs.getString(6));
			pvm.setSolved(rs.getBoolean(7));
			pvm.setCreatedAt(rs.getTimestamp(8));
			pvm.setUpdatedAt(rs.getTimestamp(9));
			pvm.setContent(rs.getString(10));
			pvm.setView(rs.getLong(11));
			pvm.setType(rs.getBoolean(12));
			pvm.setShow(rs.getBoolean(13));
			pvm.setImage(rs.getString(14));
			pvm.setTypeUser(rs.getInt(15));
			pvm.setUniqueId(rs.getLong(16));
			pvm.setAnswer(rs.getLong(17));
			ds.add(pvm);
		}
		con.close();
		ps.close();
		rs.close();

		return ds;
	}

	public long getSLNoAnswer() throws Exception {
		long res = 0;
		sql = "select count(*)\r\n" + "from Post as p\r\n"
				+ "where ((select count(*) from Post_Comment where postId = p.id)) = 0";

		con = new ConnecDataBase().getConnection();
		ps = con.prepareStatement(sql);
		rs = ps.executeQuery();
		if (rs.next()) {
			res = rs.getLong(1);
		}
		con.close();
		ps.close();
		rs.close();

		return res;
	}

	public ArrayList<PostVMbean> getNoAnswerPage(long index, long sobai) throws Exception {
		ArrayList<PostVMbean> ds = new ArrayList<PostVMbean>();
		sql = "select p.*, u.image, u.type, u.uniqueId, ((select count(*) from Post_Comment where postId = p.id)) as answer\r\n"
				+ "from Post as p JOIN Users as u on p.authorId = u.id\r\n"
				+ "where ((select count(*) from Post_Comment where postId = p.id)) = 0\r\n"
				+ "order by p.createdAt desc\r\n" + "OFFSET ? rows fetch next ? rows ONLY";

		con = new ConnecDataBase().getConnection();
		ps = con.prepareStatement(sql);
		ps.setLong(1, (index - 1) * sobai);
		ps.setLong(2, sobai);
		rs = ps.executeQuery();
		while (rs.next()) {			
			
			PostVMbean pvm = new PostVMbean();
			pvm.setId(rs.getLong(1));
			pvm.setAuthorId(rs.getLong(2));
			pvm.setParentId(rs.getLong(3));
			pvm.setTitle(rs.getString(4));
			pvm.setSlug(rs.getString(5));
			pvm.setSummary(rs.getString(6));
			pvm.setSolved(rs.getBoolean(7));
			pvm.setCreatedAt(rs.getTimestamp(8));
			pvm.setUpdatedAt(rs.getTimestamp(9));
			pvm.setContent(rs.getString(10));
			pvm.setView(rs.getLong(11));
			pvm.setType(rs.getBoolean(12));
			pvm.setShow(rs.getBoolean(13));
			pvm.setImage(rs.getString(14));
			pvm.setTypeUser(rs.getInt(15));
			pvm.setUniqueId(rs.getLong(16));
			pvm.setAnswer(rs.getLong(17));
			ds.add(pvm);
		}
		con.close();
		ps.close();
		rs.close();

		return ds;
	}

	public long getSLNoAnswerbyCate(String slugcate) throws Exception {
		long res = 0;
		sql = "select count(*)\r\n" + "from Users as u\r\n" + "join Post as p on u.id = p.authorId\r\n"
				+ "join Post_Category as pc on p.id = pc.postId\r\n" + "join Category as c on pc.categoryId = c.id\r\n"
				+ "where ((select count(*) from Post_Comment where postId = p.id)) = 0 and c.slug = ?";

		con = new ConnecDataBase().getConnection();
		ps = con.prepareStatement(sql);
		ps.setString(1, slugcate);
		rs = ps.executeQuery();
		if (rs.next()) {
			res = rs.getLong(1);
		}
		con.close();
		ps.close();
		rs.close();

		return res;
	}

	public ArrayList<PostVMbean> getNoAnswerbyCatePage(String slugcate, long index, long sobai) throws Exception {
		ArrayList<PostVMbean> ds = new ArrayList<PostVMbean>();
		sql = "select p.* , u.image, u.type, u.uniqueId, ((select count(*) from Post_Comment where postId = p.id)) as answer\r\n"
				+ "from Users as u\r\n" + "join Post as p on u.id = p.authorId\r\n"
				+ "join Post_Category as pc on p.id = pc.postId\r\n" + "join Category as c on pc.categoryId = c.id\r\n"
				+ "where ((select count(*) from Post_Comment where postId = p.id)) = 0 and c.slug = ?\r\n"
				+ "order by p.createdAt desc\r\n" + "OFFSET ? rows fetch next ? rows ONLY";

		con = new ConnecDataBase().getConnection();
		ps = con.prepareStatement(sql);
		ps.setString(1, slugcate);
		ps.setLong(2, (index - 1) * sobai);
		ps.setLong(3, sobai);
		rs = ps.executeQuery();
		while (rs.next()) {			
			PostVMbean pvm = new PostVMbean();
			pvm.setId(rs.getLong(1));
			pvm.setAuthorId(rs.getLong(2));
			pvm.setParentId(rs.getLong(3));
			pvm.setTitle(rs.getString(4));
			pvm.setSlug(rs.getString(5));
			pvm.setSummary(rs.getString(6));
			pvm.setSolved(rs.getBoolean(7));
			pvm.setCreatedAt(rs.getTimestamp(8));
			pvm.setUpdatedAt(rs.getTimestamp(9));
			pvm.setContent(rs.getString(10));
			pvm.setView(rs.getLong(11));
			pvm.setType(rs.getBoolean(12));
			pvm.setShow(rs.getBoolean(13));
			pvm.setImage(rs.getString(14));
			pvm.setTypeUser(rs.getInt(15));
			pvm.setUniqueId(rs.getLong(16));
			pvm.setAnswer(rs.getLong(17));
			ds.add(pvm);
		}
		con.close();
		ps.close();
		rs.close();

		return ds;
	}

	//
	public long getSLNoAnswerbyTags(String slugtags) throws Exception {
		long res = 0;
		sql = "select count(*)\r\n" + "from Users as u\r\n" + "join Post as p on u.id = p.authorId\r\n"
				+ "join Post_Tag as pt on p.id = pt.postId\r\n" + "join Tag as t on pt.tagId = t.id\r\n"
				+ "where ((select count(*) from Post_Comment where postId = p.id)) = 0 and t.slug = ?";

		con = new ConnecDataBase().getConnection();
		ps = con.prepareStatement(sql);
		ps.setString(1, slugtags);
		rs = ps.executeQuery();
		if (rs.next()) {
			res = rs.getLong(1);
		}
		con.close();
		ps.close();
		rs.close();

		return res;
	}

	public ArrayList<PostVMbean> getNoAnswerbyTagsPage(String slugtags, long index, long sobai) throws Exception {
		ArrayList<PostVMbean> ds = new ArrayList<PostVMbean>();
		sql = "select p.* , u.image, u.type, u.uniqueId, ((select count(*) from Post_Comment where postId = p.id)) as answer\r\n"
				+ "from Users as u\r\n" + "join Post as p on u.id = p.authorId\r\n"
				+ "join Post_Tag as pt on p.id = pt.postId\r\n" + "join Tag as t on pt.tagId = t.id\r\n"
				+ "where ((select count(*) from Post_Comment where postId = p.id)) = 0 and t.slug = ?\r\n"
				+ "order by p.createdAt desc\r\n" + "OFFSET ? rows fetch next ? rows ONLY";

		con = new ConnecDataBase().getConnection();
		ps = con.prepareStatement(sql);
		ps.setString(1, slugtags);
		ps.setLong(2, (index - 1) * sobai);
		ps.setLong(3, sobai);
		rs = ps.executeQuery();
		while (rs.next()) {			
			PostVMbean pvm = new PostVMbean();
			pvm.setId(rs.getLong(1));
			pvm.setAuthorId(rs.getLong(2));
			pvm.setParentId(rs.getLong(3));
			pvm.setTitle(rs.getString(4));
			pvm.setSlug(rs.getString(5));
			pvm.setSummary(rs.getString(6));
			pvm.setSolved(rs.getBoolean(7));
			pvm.setCreatedAt(rs.getTimestamp(8));
			pvm.setUpdatedAt(rs.getTimestamp(9));
			pvm.setContent(rs.getString(10));
			pvm.setView(rs.getLong(11));
			pvm.setType(rs.getBoolean(12));
			pvm.setShow(rs.getBoolean(13));
			pvm.setImage(rs.getString(14));
			pvm.setTypeUser(rs.getInt(15));
			pvm.setUniqueId(rs.getLong(16));
			pvm.setAnswer(rs.getLong(17));
			ds.add(pvm);
		}
		con.close();
		ps.close();
		rs.close();

		return ds;
	}

	public long getSLRecentPostAllbySearch(String search, int type) throws Exception {
		long res = 0;
		sql = "";
		con = new ConnecDataBase().getConnection();
		if(type == -1) {
			sql = "select count(*) from Post where (title like ? or summary like ? or content like ?)";
			ps = con.prepareStatement(sql);
			ps.setString(1, "%" + search + "%");
			ps.setString(2, "%" + search + "%");
			ps.setString(3, "%" + search + "%");
			rs = ps.executeQuery();
		}
		else {
			sql = "select count(*) from Post where (title like ? or summary like ? or content like ?) and type = ?";
			ps = con.prepareStatement(sql);
			ps.setString(1, "%" + search + "%");
			ps.setString(2, "%" + search + "%");
			ps.setString(3, "%" + search + "%");
			ps.setInt(4, type);
			rs = ps.executeQuery();
		}	
		if (rs.next()) {
			res = rs.getLong(1);
		}
		con.close();
		ps.close();
		rs.close();

		return res;
	}

	public ArrayList<PostVMbean> getRecentPostAllPagebySearch(long index, long sobai, String search, int type) throws Exception {
		ArrayList<PostVMbean> ds = new ArrayList<PostVMbean>();
		sql = "";
		con = new ConnecDataBase().getConnection();
		if(type == -1) {
			sql = "select p.*, u.image, u.type, u.uniqueId, ((select count(*) from Post_Comment where postId = p.id)) as answer\r\n"
					+ "				from Post as p JOIN Users as u on p.authorId = u.id\r\n"
					+ "				where (title like ? or summary like ? or content like ?)\r\n"
					+ "				order by p.createdAt desc\r\n" + "				OFFSET ? rows fetch next ? rows ONLY";
			ps = con.prepareStatement(sql);
			ps.setString(1, "%" + search + "%");
			ps.setString(2, "%" + search + "%");
			ps.setString(3, "%" + search + "%");
			ps.setLong(4, (index - 1) * sobai);
			ps.setLong(5, sobai);
			rs = ps.executeQuery();
		
		}
		else {
			sql = "select p.*, u.image, u.type, u.uniqueId, ((select count(*) from Post_Comment where postId = p.id)) as answer\r\n"
					+ "				from Post as p JOIN Users as u on p.authorId = u.id\r\n"
					+ "				where (title like ? or summary like ? or content like ?) and p.type = ?\r\n"
					+ "				order by p.createdAt desc\r\n" + "				OFFSET ? rows fetch next ? rows ONLY";
			ps = con.prepareStatement(sql);
			ps.setString(1, "%" + search + "%");
			ps.setString(2, "%" + search + "%");
			ps.setString(3, "%" + search + "%");
			ps.setInt(4, type);
			ps.setLong(5, (index - 1) * sobai);
			ps.setLong(6, sobai);
			rs = ps.executeQuery();
		}		
		
		while (rs.next()) {			
			PostVMbean pvm = new PostVMbean();
			pvm.setId(rs.getLong(1));
			pvm.setAuthorId(rs.getLong(2));
			pvm.setParentId(rs.getLong(3));
			pvm.setTitle(rs.getString(4));
			pvm.setSlug(rs.getString(5));
			pvm.setSummary(rs.getString(6));
			pvm.setSolved(rs.getBoolean(7));
			pvm.setCreatedAt(rs.getTimestamp(8));
			pvm.setUpdatedAt(rs.getTimestamp(9));
			pvm.setContent(rs.getString(10));
			pvm.setView(rs.getLong(11));
			pvm.setType(rs.getBoolean(12));
			pvm.setShow(rs.getBoolean(13));
			pvm.setImage(rs.getString(14));
			pvm.setTypeUser(rs.getInt(15));
			pvm.setUniqueId(rs.getLong(16));
			pvm.setAnswer(rs.getLong(17));
			ds.add(pvm);
		}
		con.close();
		ps.close();
		rs.close();

		return ds;
	}

	public long getSLRecentPostAll(int type) throws Exception {
		long res = 0;
		sql = "";
		con = new ConnecDataBase().getConnection();
		if(type == -1) {
			sql = "select count(*) from Post as p JOIN Users as u on p.authorId = u.id";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
		}
		else {
			sql = "select count(*) from Post as p JOIN Users as u on p.authorId = u.id where p.type = ?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, type);
			rs = ps.executeQuery();
		}
		
		if (rs.next()) {
			res = rs.getLong(1);
		}
		con.close();
		ps.close();
		rs.close();

		return res;
	}

	public ArrayList<PostVMbean> getRecentPostAllPage(long index, long sobai, int type) throws Exception {
		ArrayList<PostVMbean> ds = new ArrayList<PostVMbean>();
		sql = "";
		con = new ConnecDataBase().getConnection();
		if(type == -1) {
			sql = "select p.*, u.image, u.type, u.uniqueId, ((select count(*) from Post_Comment where postId = p.id)) as answer\r\n"
					+ "from Post as p JOIN Users as u on p.authorId = u.id\r\n" + "order by p.createdAt desc\r\n"
					+ "OFFSET ? rows fetch next ? rows ONLY";
			ps = con.prepareStatement(sql);
			ps.setLong(1, (index - 1) * sobai);
			ps.setLong(2, sobai);
			rs = ps.executeQuery();
		}
		else {
			sql = "select p.*, u.image, u.type, u.uniqueId, ((select count(*) from Post_Comment where postId = p.id)) as answer\r\n"
					+ "from Post as p JOIN Users as u on p.authorId = u.id\r\n"
					+ "where p.type = ?\r\n"
					+ "order by p.createdAt desc\r\n"
					+ "OFFSET ? rows fetch next ? rows ONLY";
			ps = con.prepareStatement(sql);
			ps.setInt(1, type);
			ps.setLong(2, (index - 1) * sobai);
			ps.setLong(3, sobai);
			rs = ps.executeQuery();			
		}
		
		while (rs.next()) {			
			PostVMbean pvm = new PostVMbean();
			pvm.setId(rs.getLong(1));
			pvm.setAuthorId(rs.getLong(2));
			pvm.setParentId(rs.getLong(3));
			pvm.setTitle(rs.getString(4));
			pvm.setSlug(rs.getString(5));
			pvm.setSummary(rs.getString(6));
			pvm.setSolved(rs.getBoolean(7));
			pvm.setCreatedAt(rs.getTimestamp(8));
			pvm.setUpdatedAt(rs.getTimestamp(9));
			pvm.setContent(rs.getString(10));
			pvm.setView(rs.getLong(11));
			pvm.setType(rs.getBoolean(12));
			pvm.setShow(rs.getBoolean(13));
			pvm.setImage(rs.getString(14));
			pvm.setTypeUser(rs.getInt(15));
			pvm.setUniqueId(rs.getLong(16));
			pvm.setAnswer(rs.getLong(17));
			ds.add(pvm);
		}
		con.close();
		ps.close();
		rs.close();

		return ds;
	}

	public ArrayList<PostVMbean> getRecentPostAll() throws Exception {
		ArrayList<PostVMbean> ds = new ArrayList<PostVMbean>();
		sql = "select p.*, u.image, u.type, u.uniqueId, ((select count(*) from Post_Comment where postId = p.id)) as answer\r\n"
				+ "from Post as p JOIN Users as u on p.authorId = u.id\r\n" + "order by p.createdAt desc";

		con = new ConnecDataBase().getConnection();
		ps = con.prepareStatement(sql);
		rs = ps.executeQuery();
		while (rs.next()) {			
			PostVMbean pvm = new PostVMbean();
			pvm.setId(rs.getLong(1));
			pvm.setAuthorId(rs.getLong(2));
			pvm.setParentId(rs.getLong(3));
			pvm.setTitle(rs.getString(4));
			pvm.setSlug(rs.getString(5));
			pvm.setSummary(rs.getString(6));
			pvm.setSolved(rs.getBoolean(7));
			pvm.setCreatedAt(rs.getTimestamp(8));
			pvm.setUpdatedAt(rs.getTimestamp(9));
			pvm.setContent(rs.getString(10));
			pvm.setView(rs.getLong(11));
			pvm.setType(rs.getBoolean(12));
			pvm.setShow(rs.getBoolean(13));
			pvm.setImage(rs.getString(14));
			pvm.setTypeUser(rs.getInt(15));
			pvm.setUniqueId(rs.getLong(16));
			pvm.setAnswer(rs.getLong(17));
			ds.add(pvm);
		}
		con.close();
		ps.close();
		rs.close();

		return ds;
	}

	public long getSLRecentPostAllbyCate(String slugcate) throws Exception {
		long res = 0;
		sql = "select count(*)\r\n" + "from Users as u\r\n" + "join Post as p on u.id = p.authorId\r\n"
				+ "join Post_Category as pc on p.id = pc.postId\r\n" + "join Category as c on pc.categoryId = c.id\r\n"
				+ "where c.slug = ?";

		con = new ConnecDataBase().getConnection();
		ps = con.prepareStatement(sql);
		ps.setString(1, slugcate);
		rs = ps.executeQuery();
		if (rs.next()) {
			res = rs.getLong(1);
		}
		con.close();
		ps.close();
		rs.close();

		return res;
	}

	public ArrayList<PostVMbean> getRecentPostAllbyCatePage(String slugcate, long index, long sobai) throws Exception {
		ArrayList<PostVMbean> ds = new ArrayList<PostVMbean>();
		sql = "select p.* , u.image, u.type, u.uniqueId, ((select count(*) from Post_Comment where postId = p.id)) as answer\r\n"
				+ "from Users as u\r\n" + "join Post as p on u.id = p.authorId\r\n"
				+ "join Post_Category as pc on p.id = pc.postId\r\n" + "join Category as c on pc.categoryId = c.id\r\n"
				+ "where c.slug = ?\r\n" + "order by p.createdAt desc\r\n" + "OFFSET ? rows fetch next ? rows ONLY";

		con = new ConnecDataBase().getConnection();
		ps = con.prepareStatement(sql);
		ps.setString(1, slugcate);
		ps.setLong(2, (index - 1) * sobai);
		ps.setLong(3, sobai);
		rs = ps.executeQuery();
		while (rs.next()) {		
			PostVMbean pvm = new PostVMbean();
			pvm.setId(rs.getLong(1));
			pvm.setAuthorId(rs.getLong(2));
			pvm.setParentId(rs.getLong(3));
			pvm.setTitle(rs.getString(4));
			pvm.setSlug(rs.getString(5));
			pvm.setSummary(rs.getString(6));
			pvm.setSolved(rs.getBoolean(7));
			pvm.setCreatedAt(rs.getTimestamp(8));
			pvm.setUpdatedAt(rs.getTimestamp(9));
			pvm.setContent(rs.getString(10));
			pvm.setView(rs.getLong(11));
			pvm.setType(rs.getBoolean(12));
			pvm.setShow(rs.getBoolean(13));
			pvm.setImage(rs.getString(14));
			pvm.setTypeUser(rs.getInt(15));
			pvm.setUniqueId(rs.getLong(16));
			pvm.setAnswer(rs.getLong(17));
			ds.add(pvm);
		}
		con.close();
		ps.close();
		rs.close();

		return ds;
	}

	//
	public long getSLRecentPostAllbyTags(String slugtags) throws Exception {
		long res = 0;
		sql = "select count(*)\r\n" + "from Users as u\r\n" + "join Post as p on u.id = p.authorId\r\n"
				+ "join Post_Tag as pt on p.id = pt.postId\r\n" + "join Tag as t on pt.tagId = t.id\r\n"
				+ "where t.slug = ?";

		con = new ConnecDataBase().getConnection();
		ps = con.prepareStatement(sql);
		ps.setString(1, slugtags);
		rs = ps.executeQuery();
		if (rs.next()) {
			res = rs.getLong(1);
		}
		con.close();
		ps.close();
		rs.close();

		return res;
	}

	public ArrayList<PostVMbean> getRecentPostAllbyTagsPage(String slugtags, long index, long sobai) throws Exception {
		ArrayList<PostVMbean> ds = new ArrayList<PostVMbean>();
		sql = "select p.* , u.image, u.type, u.uniqueId, ((select count(*) from Post_Comment where postId = p.id)) as answer\r\n"
				+ "from Users as u\r\n" + "join Post as p on u.id = p.authorId\r\n"
				+ "join Post_Tag as pt on p.id = pt.postId\r\n" + "join Tag as t on pt.tagId = t.id\r\n"
				+ "where t.slug = ?\r\n" + "order by p.createdAt desc\r\n" + "OFFSET ? rows fetch next ? rows ONLY";

		con = new ConnecDataBase().getConnection();
		ps = con.prepareStatement(sql);
		ps.setString(1, slugtags);
		ps.setLong(2, (index - 1) * sobai);
		ps.setLong(3, sobai);
		rs = ps.executeQuery();
		while (rs.next()) {
			// 1 2 3 4 5 6 7
			// long id, long authorId, long parentId, String title, String slug, String
			// summary, boolean solved,
			// 8 9 10 11 12 13 14
			// Timestamp createdAt, Timestamp updatedAt, String content, Long view, boolean
			// type, String image, Long answer
			PostVMbean pvm = new PostVMbean();
			pvm.setId(rs.getLong(1));
			pvm.setAuthorId(rs.getLong(2));
			pvm.setParentId(rs.getLong(3));
			pvm.setTitle(rs.getString(4));
			pvm.setSlug(rs.getString(5));
			pvm.setSummary(rs.getString(6));
			pvm.setSolved(rs.getBoolean(7));
			pvm.setCreatedAt(rs.getTimestamp(8));
			pvm.setUpdatedAt(rs.getTimestamp(9));
			pvm.setContent(rs.getString(10));
			pvm.setView(rs.getLong(11));
			pvm.setType(rs.getBoolean(12));
			pvm.setShow(rs.getBoolean(13));
			pvm.setImage(rs.getString(14));
			pvm.setTypeUser(rs.getInt(15));
			pvm.setUniqueId(rs.getLong(16));
			pvm.setAnswer(rs.getLong(17));
			ds.add(pvm);
		}
		con.close();
		ps.close();
		rs.close();

		return ds;
	}

	public long getSLMyPost(long idu, int type, String search) throws Exception {		
		long res = 0;
		con = new ConnecDataBase().getConnection();
		sql = "";
		if(type == -1 && search == "") {
			sql = "select count(*) from Post as p JOIN Users as u on p.authorId = u.id where u.id = ?";
			
			ps = con.prepareStatement(sql);
			ps.setLong(1, idu);
			rs = ps.executeQuery();			
		}
		else if(type != -1 && search == "") {
			sql = "select count(*) from Post as p JOIN Users as u on p.authorId = u.id where u.id = ? and p.type = ?";
			
			ps = con.prepareStatement(sql);
			ps.setLong(1, idu);
			ps.setInt(2, type);
			rs = ps.executeQuery();			
		}	
		else if(type == -1 && search != ""){
			sql = "select count(*) \r\n"
					+ "from Post as p JOIN Users as u on p.authorId = u.id \r\n"
					+ "where u.id = ? and (p.title like ? or p.summary like ? or p.content like ?)";
			ps = con.prepareStatement(sql);
			ps.setLong(1, idu);
			ps.setString(2, "%" + search + "%");
			ps.setString(3, "%" + search + "%");
			ps.setString(4, "%" + search + "%");
			rs = ps.executeQuery();
		}
		else if(type != -1 && search != "") {
			sql = "select count(*) \r\n"
					+ "from Post as p JOIN Users as u on p.authorId = u.id \r\n"
					+ "where u.id = ? and p.type = ? and (p.title like ? or p.summary like ? or p.content like ?)";
			ps = con.prepareStatement(sql);
			ps.setLong(1, idu);
			ps.setInt(2, type);
			ps.setString(3, "%" + search + "%");
			ps.setString(4, "%" + search + "%");
			ps.setString(5, "%" + search + "%");
			rs = ps.executeQuery();
		}
		if (rs.next()) {
			res = rs.getLong(1);
		}
		con.close();
		ps.close();
		rs.close();

		return res;
	}

	public ArrayList<PostVMbean> getMyPost(long index, long sobai, long idu, int type, String search) throws Exception {
		ArrayList<PostVMbean> ds = new ArrayList<PostVMbean>();
		sql = "";
		if(type == -1 && search == "") {
			sql = "select p.*, u.image, u.type, u.uniqueId, ((select count(*) from Post_Comment where postId = p.id)) as answer\r\n"
					+ "from Post as p JOIN Users as u on p.authorId = u.id\r\n"
					+ "where u.id = ?\r\n"
					+ "order by p.createdAt desc  OFFSET ? rows fetch next ? rows ONLY";
			con = new ConnecDataBase().getConnection();
			ps = con.prepareStatement(sql);
			ps.setLong(1, idu);			
			ps.setLong(2, (index - 1) * sobai);
			ps.setLong(3, sobai);
			rs = ps.executeQuery();
		}
		else if(type != -1 && search == "") {
			sql = "select p.*, u.image, u.type, u.uniqueId, ((select count(*) from Post_Comment where postId = p.id)) as answer\r\n"
					+ "from Post as p JOIN Users as u on p.authorId = u.id\r\n"
					+ "where u.id = ? and p.type = ?\r\n"
					+ "order by p.createdAt desc  OFFSET ? rows fetch next ? rows ONLY";
			con = new ConnecDataBase().getConnection();
			ps = con.prepareStatement(sql);
			ps.setLong(1, idu);
			ps.setInt(2, type);
			ps.setLong(3, (index - 1) * sobai);
			ps.setLong(4, sobai);
			rs = ps.executeQuery();
		}
		else if(type == -1 && search != ""){
			sql = "select p.*, u.image, u.type, u.uniqueId, ((select count(*) from Post_Comment where postId = p.id)) as answer\r\n"
					+ "from Post as p JOIN Users as u on p.authorId = u.id\r\n"
					+ "where u.id = ? and (p.title like ? or p.summary like ? or p.content like ?)\r\n"
					+ "order by p.createdAt desc  OFFSET ? rows fetch next ? rows ONLY";
			con = new ConnecDataBase().getConnection();
			ps = con.prepareStatement(sql);
			ps.setLong(1, idu);			
			ps.setString(2, "%" + search + "%");
			ps.setString(3, "%" + search + "%");
			ps.setString(4, "%" + search + "%");
			ps.setLong(5, (index - 1) * sobai);
			ps.setLong(6, sobai);
			rs = ps.executeQuery();
		}
		else if(type != -1 && search != "") {
			sql = "select p.*, u.image, u.type, u.uniqueId, ((select count(*) from Post_Comment where postId = p.id)) as answer\r\n"
					+ "from Post as p JOIN Users as u on p.authorId = u.id\r\n"
					+ "where u.id = ? and p.type = ? and (p.title like ? or p.summary like ? or p.content like ?)\r\n"
					+ "order by p.createdAt desc  OFFSET ? rows fetch next ? rows ONLY";
			con = new ConnecDataBase().getConnection();
			ps = con.prepareStatement(sql);
			ps.setLong(1, idu);	
			ps.setInt(2, type);
			ps.setString(3, "%" + search + "%");
			ps.setString(4, "%" + search + "%");
			ps.setString(5, "%" + search + "%");
			ps.setLong(6, (index - 1) * sobai);
			ps.setLong(7, sobai);
			rs = ps.executeQuery();
		}		
		
		while (rs.next()) {			
			PostVMbean pvm = new PostVMbean();
			pvm.setId(rs.getLong(1));
			pvm.setAuthorId(rs.getLong(2));
			pvm.setParentId(rs.getLong(3));
			pvm.setTitle(rs.getString(4));
			pvm.setSlug(rs.getString(5));
			pvm.setSummary(rs.getString(6));
			pvm.setSolved(rs.getBoolean(7));
			pvm.setCreatedAt(rs.getTimestamp(8));
			pvm.setUpdatedAt(rs.getTimestamp(9));
			pvm.setContent(rs.getString(10));
			pvm.setView(rs.getLong(11));
			pvm.setType(rs.getBoolean(12));
			pvm.setShow(rs.getBoolean(13));
			pvm.setImage(rs.getString(14));
			pvm.setTypeUser(rs.getInt(15));
			pvm.setUniqueId(rs.getLong(16));
			pvm.setAnswer(rs.getLong(17));
			ds.add(pvm);
		}
		con.close();
		ps.close();
		rs.close();

		return ds;
	}

	public PostVMbean getPostBySlug(String slug) throws Exception {
		PostVMbean pvm = null;
		String sql = "select p.*, u.image, u.type, u.uniqueId, ((select count(*) from Post_Comment where postId = p.id)) as answer\r\n"
				+ "from Post as p JOIN Users as u on p.authorId = u.id\r\n" + "where slug = ?";

		con = new ConnecDataBase().getConnection();
		ps = con.prepareStatement(sql);
		ps.setString(1, slug);
		rs = ps.executeQuery();
		if (rs.next()) {
			pvm = new PostVMbean();
			pvm.setId(rs.getLong(1));
			pvm.setAuthorId(rs.getLong(2));
			pvm.setParentId(rs.getLong(3));
			pvm.setTitle(rs.getString(4));
			pvm.setSlug(rs.getString(5));
			pvm.setSummary(rs.getString(6));
			pvm.setSolved(rs.getBoolean(7));
			pvm.setCreatedAt(rs.getTimestamp(8));
			pvm.setUpdatedAt(rs.getTimestamp(9));
			pvm.setContent(rs.getString(10));
			pvm.setView(rs.getLong(11));
			pvm.setType(rs.getBoolean(12));
			pvm.setShow(rs.getBoolean(13));
			pvm.setImage(rs.getString(14));
			pvm.setTypeUser(rs.getInt(15));
			pvm.setUniqueId(rs.getLong(16));
			pvm.setAnswer(rs.getLong(17));		
		}
		con.close();
		ps.close();
		rs.close();

		return pvm;
	}
}
