package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import Bean.Categorybean;
import Connection.ConnecDataBase;

public class Categorydao {
	String sql;
	Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;

	public Long getSLCategories() throws Exception {
		sql = "select count(*) from Category";
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

	public ArrayList<Categorybean> getCategoriesbyPage(long index, long sobai) throws Exception {
		ArrayList<Categorybean> ds = new ArrayList<Categorybean>();
		sql = "select * from category\r\n" + "order by id desc\r\n" + "OFFSET ? rows fetch next ? rows ONLY";

		con = new ConnecDataBase().getConnection();
		ps = con.prepareStatement(sql);
		ps.setLong(1, (index - 1) * sobai);
		ps.setLong(2, sobai);
		rs = ps.executeQuery();
		while (rs.next()) {
			// long id, long parentId, String title, String slug, String content
			// 1 2 3 4 5
			ds.add(new Categorybean(rs.getLong(1), rs.getLong(2), rs.getString(3), rs.getString(4), rs.getString(5)));
		}
		con.close();
		ps.close();
		rs.close();

		return ds;
	}

	public ArrayList<Categorybean> getCategories() throws Exception {
		ArrayList<Categorybean> ds = new ArrayList<Categorybean>();
		sql = "select * from Category";

		con = new ConnecDataBase().getConnection();
		ps = con.prepareStatement(sql);
		rs = ps.executeQuery();
		while (rs.next()) {
			// long id, long parentId, String title, String slug, String content
			// 1 2 3 4 5
			ds.add(new Categorybean(rs.getLong(1), rs.getLong(2), rs.getString(3), rs.getString(4), rs.getString(5)));
		}
		con.close();
		ps.close();
		rs.close();

		return ds;
	}

	public long getSLCategoriesbySearch(String search) throws Exception {
		long res = 0;
		sql = "select count(*) from Category where (title like ? or slug like ? or content like ?)";

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

	public ArrayList<Categorybean> getCategoriesbyPageSearch(long index, long sobai, String search) throws Exception {
		ArrayList<Categorybean> ds = new ArrayList<Categorybean>();
		sql = "select *\r\n" + "from Category\r\n" + "where (title like ? or slug like ? or content like ?)\r\n"
				+ "order by id desc\r\n" + "OFFSET ? rows fetch next ? rows ONLY";

		con = new ConnecDataBase().getConnection();
		ps = con.prepareStatement(sql);
		ps.setString(1, "%" + search + "%");
		ps.setString(2, "%" + search + "%");
		ps.setString(3, "%" + search + "%");
		ps.setLong(4, (index - 1) * sobai);
		ps.setLong(5, sobai);
		rs = ps.executeQuery();
		while (rs.next()) {
			// long id, long parentId, String title, String slug, String content
			// 1 2 3 4 5
			ds.add(new Categorybean(rs.getLong(1), rs.getLong(2), rs.getString(3), rs.getString(4), rs.getString(5)));
		}
		con.close();
		ps.close();
		rs.close();

		return ds;
	}

	public boolean createCategory(String title, String content) throws Exception {
		boolean res = false;
		String slug = title.toLowerCase().replace(' ', '-');
		sql = "insert into Category(title, slug, content) values (?,?,?)";

		con = new ConnecDataBase().getConnection();
		ps = con.prepareStatement(sql);
		ps.setString(1, title);
		ps.setString(2, slug);
		ps.setString(3, content);

		res = ps.executeUpdate() > 0;

		con.close();
		ps.close();

		return res;
	}

	public boolean updateCategory(Long id, String title, String content) throws Exception {
		boolean res = false;
		String slug = title.toLowerCase().replace(' ', '-');
		sql = "update Category set title = ?, slug = ?, content = ? where id = ?";

		con = new ConnecDataBase().getConnection();
		ps = con.prepareStatement(sql);
		ps.setString(1, title);
		ps.setString(2, slug);
		ps.setString(3, content);
		ps.setLong(4, id);

		res = ps.executeUpdate() > 0;

		con.close();
		ps.close();

		return res;
	}

	public boolean deleteCategory(Long id) throws Exception {
		boolean res = false;
		sql = "delete from Category where id = ?";
		con = new ConnecDataBase().getConnection();
		ps = con.prepareStatement(sql);
		ps.setLong(1, id);

		res = ps.executeUpdate() > 0;

		con.close();
		ps.close();

		return res;
	}

	public boolean createPostCategory(Long postId, Long categoryId) throws Exception {
		boolean res = false;

		sql = "insert into Post_Category(postId, categoryId) values (?,?)";

		con = new ConnecDataBase().getConnection();
		ps = con.prepareStatement(sql);
		ps.setLong(1, postId);
		ps.setLong(2, categoryId);

		res = ps.executeUpdate() > 0;

		con.close();
		ps.close();

		return res;
	}

}
