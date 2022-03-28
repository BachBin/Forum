package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

import Bean.Userbean;
import Connection.ConnecDataBase;

public class Userdao {
	String sql;
	Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;

	public Long getSLUser(int type) throws Exception {
		Long res = (long) 0;
		con = new ConnecDataBase().getConnection();
		sql = "";
		if(type == -1) {
			sql = "select count(*) from Users";
			
			ps = con.prepareStatement(sql);			
			rs = ps.executeQuery();
		}
		else {
			sql = "select count(*) from Users where type = ?";
			
			ps = con.prepareStatement(sql);
			ps.setInt(1, type);
			rs = ps.executeQuery();
		}
		if (rs.next())
			res = (long) rs.getInt(1);
		con.close();
		ps.close();
		rs.close();

		return res;
	}

	public ArrayList<Userbean> getUserbyPage(long index, long sobai, int type) throws Exception {
		ArrayList<Userbean> ds = new ArrayList<Userbean>();
		sql = "";
		if(type == -1) {
			sql = "select * from Users order by id desc OFFSET ? rows fetch next ? rows ONLY";
			con = new ConnecDataBase().getConnection();
			ps = con.prepareStatement(sql);
			ps.setLong(1, (index - 1) * sobai);
			ps.setLong(2, sobai);
			rs = ps.executeQuery();
		}
		else {
			sql = "select * from Users where type = ? order by id desc OFFSET ? rows fetch next ? rows ONLY";
			con = new ConnecDataBase().getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(1, type);
			ps.setLong(2, (index - 1) * sobai);
			ps.setLong(3, sobai);
			rs = ps.executeQuery();
		}
		
		while (rs.next()) {
			ds.add(new Userbean(
					rs.getLong(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
					rs.getString(6), rs.getString(7), rs.getTimestamp(8), rs.getTimestamp(9), rs.getString(10),
					rs.getString(11), rs.getString(12), rs.getInt(13), rs.getLong(14), rs.getString(15))
					);
		}
		con.close();
		ps.close();
		rs.close();

		return ds;
	}

	public ArrayList<Userbean> getUsers() throws Exception {
		ArrayList<Userbean> ds = new ArrayList<Userbean>();
		sql = "select * from Users";

		con = new ConnecDataBase().getConnection();
		ps = con.prepareStatement(sql);
		rs = ps.executeQuery();
		while (rs.next()) {
			// 1 2 3 4 5 6
			// long id, String firstName, String middleName, String lastName, String mobile,
			// String email,
			// 7 8 9 10 11 12
			// String passwordHash, Timestamp registeredAt, Timestamp lastLogin, String
			// intro, String profile, int type
			ds.add(new Userbean(
					rs.getLong(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
					rs.getString(6), rs.getString(7), rs.getTimestamp(8), rs.getTimestamp(9), rs.getString(10),
					rs.getString(11), rs.getString(12), rs.getInt(13), rs.getLong(14), rs.getString(15))
					);
		}
		con.close();
		ps.close();
		rs.close();

		return ds;
	}

	public Userbean getUser(String email, String password) throws Exception {
		Userbean res = null;
		sql = "select * from Users where email = ? and passwordHash = ?";

		con = new ConnecDataBase().getConnection();
		ps = con.prepareStatement(sql);
		ps.setString(1, email);
		ps.setString(2, password);
		rs = ps.executeQuery();
		if (rs.next()) {
			res = new Userbean(rs.getLong(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
					rs.getString(6), rs.getString(7), rs.getTimestamp(8), rs.getTimestamp(9), rs.getString(10),
					rs.getString(11), rs.getString(12), rs.getInt(13), rs.getLong(14), rs.getString(15));			
		}
		con.close();
		ps.close();
		rs.close();

		return res;
	}

	public Userbean getUserbyId(Long id) throws Exception {
		Userbean res = null;
		sql = "select * from Users where id = ?";

		con = new ConnecDataBase().getConnection();
		ps = con.prepareStatement(sql);
		ps.setLong(1, id);
		rs = ps.executeQuery();
		if (rs.next()) {
			res = new Userbean(rs.getLong(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
					rs.getString(6), rs.getString(7), rs.getTimestamp(8), rs.getTimestamp(9), rs.getString(10),
					rs.getString(11), rs.getString(12), rs.getInt(13), rs.getLong(14), rs.getString(15));			
		}
		con.close();
		ps.close();
		rs.close();

		return res;
	}
	
	public Userbean getUserbyUniqueId(Long id) throws Exception {
		Userbean res = null;
		sql = "select * from Users where uniqueId = ?";

		con = new ConnecDataBase().getConnection();
		ps = con.prepareStatement(sql);
		ps.setLong(1, id);
		rs = ps.executeQuery();
		if (rs.next()) {
			res = new Userbean(rs.getLong(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
					rs.getString(6), rs.getString(7), rs.getTimestamp(8), rs.getTimestamp(9), rs.getString(10),
					rs.getString(11), rs.getString(12), rs.getInt(13), rs.getLong(14), rs.getString(15));			
		}
		con.close();
		ps.close();
		rs.close();

		return res;
	}

	public long getSLUserbySearch(String search) throws Exception {
		long res = 0;
		sql = "select count(*) from Users where (\r\n" + "	firstName like ? \r\n" + "	or middleName like ? \r\n"
				+ "	or lastName like ? \r\n" + "	or mobile like ?\r\n" + "	or email like ?\r\n"
				+ "	or profile like ?	\r\n" + ")";

		con = new ConnecDataBase().getConnection();
		ps = con.prepareStatement(sql);
		ps.setString(1, "%" + search + "%");
		ps.setString(2, "%" + search + "%");
		ps.setString(3, "%" + search + "%");
		ps.setString(4, "%" + search + "%");
		ps.setString(5, "%" + search + "%");
		ps.setString(6, "%" + search + "%");
		rs = ps.executeQuery();
		if (rs.next()) {
			res = rs.getLong(1);
		}
		con.close();
		ps.close();
		rs.close();

		return res;
	}

	public ArrayList<Userbean> getUserbyPageSearch(long index, long sobai, String search, int type) throws Exception {
		ArrayList<Userbean> ds = new ArrayList<Userbean>();
		sql = "";
		if(type == -1) {
			sql = "select *\r\n" + "from Users\r\n" + "where (\r\n" + "	firstName like ? \r\n"
					+ "	or middleName like ? \r\n" + "	or lastName like ? \r\n" + "	or mobile like ?\r\n"
					+ "	or email like ?\r\n" + "	or profile like ?	\r\n" + ")\r\n" + "order by registeredAt desc\r\n"
					+ "OFFSET ? rows fetch next ? rows ONLY\r\n" + "";
			con = new ConnecDataBase().getConnection();
			ps = con.prepareStatement(sql);
			ps = con.prepareStatement(sql);
			ps.setString(1, "%" + search + "%");
			ps.setString(2, "%" + search + "%");
			ps.setString(3, "%" + search + "%");
			ps.setString(4, "%" + search + "%");
			ps.setString(5, "%" + search + "%");
			ps.setString(6, "%" + search + "%");
			ps.setLong(7, (index - 1) * sobai);
			ps.setLong(8, sobai);
			rs = ps.executeQuery();
		}
		else {
			sql = "select * from Users \r\n"
					+ "where (\r\n"
					+ "firstName like ?\r\n"
					+ "or middleName like ?\r\n"
					+ "or lastName like ?\r\n"
					+ "or mobile like ?\r\n"
					+ "or email like ?\r\n"
					+ "or profile like ?\r\n"
					+ ") and type = ?\r\n"
					+ "order by registeredAt desc\r\n"
					+ "OFFSET ? rows fetch next ? rows ONLY";
			con = new ConnecDataBase().getConnection();
			ps = con.prepareStatement(sql);
			ps = con.prepareStatement(sql);
			ps.setString(1, "%" + search + "%");
			ps.setString(2, "%" + search + "%");
			ps.setString(3, "%" + search + "%");
			ps.setString(4, "%" + search + "%");
			ps.setString(5, "%" + search + "%");
			ps.setString(6, "%" + search + "%");
			ps.setInt(7, type);
			ps.setLong(8, (index - 1) * sobai);
			ps.setLong(9, sobai);
			rs = ps.executeQuery();
		}	
		
		while (rs.next()) {
			// 1 2 3 4 5 6
			// long id, String firstName, String middleName, String lastName, String mobile,
			// String email,
			// 7 8 9 10 11 12
			// String passwordHash, Timestamp registeredAt, Timestamp lastLogin, String
			// intro, String profile, int type
			ds.add(new Userbean(rs.getLong(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
					rs.getString(6), rs.getString(7), rs.getTimestamp(8), rs.getTimestamp(9), rs.getString(10),
					rs.getString(11), rs.getString(12), rs.getInt(13), rs.getLong(14), rs.getString(15)));
		}
		con.close();
		ps.close();
		rs.close();

		return ds;
	}
	
	public ArrayList<Userbean> searchUser(String search) throws Exception {
		ArrayList<Userbean> ds = new ArrayList<Userbean>();		
		
			sql = "select *\r\n"
					+ "from Users\r\n"
					+ "where (firstName like ?\r\n"
					+ "or middleName like ? or lastName like ? or mobile like ?\r\n"
					+ "or email like ? or profile like ?)\r\n"
					+ "order by registeredAt desc";
			con = new ConnecDataBase().getConnection();
			ps = con.prepareStatement(sql);
			ps = con.prepareStatement(sql);
			ps.setString(1, "%" + search + "%");
			ps.setString(2, "%" + search + "%");
			ps.setString(3, "%" + search + "%");
			ps.setString(4, "%" + search + "%");
			ps.setString(5, "%" + search + "%");
			ps.setString(6, "%" + search + "%");
			
			rs = ps.executeQuery();
				
		
		while (rs.next()) {			
			ds.add(new Userbean(rs.getLong(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
					rs.getString(6), rs.getString(7), rs.getTimestamp(8), rs.getTimestamp(9), rs.getString(10),
					rs.getString(11), rs.getString(12), rs.getInt(13), rs.getLong(14), rs.getString(15)));
		}
		con.close();
		ps.close();
		rs.close();

		return ds;
	}

	public boolean createUser(String email, String password, int type) throws Exception {
		boolean res = false;
		sql = "insert into Users(email,passwordHash,registeredAt,[type], uniqueId, status) values(?,?,?,?,?,?)";

		Date date = new Date();
		Random random = new Random();
		
		LocalDateTime localdatetime = LocalDateTime.now();
		int randomId = random.nextInt(1000000 + 1 - localdatetime.toLocalTime().toSecondOfDay()) + localdatetime.toLocalTime().toSecondOfDay();

		con = new ConnecDataBase().getConnection();
		ps = con.prepareStatement(sql);
		ps.setString(1, email);
		ps.setString(2, password);
		ps.setTimestamp(3, new Timestamp(date.getTime()));
		ps.setInt(4, type);
		ps.setLong(5, randomId);
		ps.setString(6, "Active now");

		res = ps.executeUpdate() > 0;
		con.close();
		ps.close();
		return res;
	}

	public boolean updateUser(Userbean user) throws Exception {
		boolean res = false;
		sql = "update Users set firstName = ?, middleName = ?, lastName = ?, mobile = ?, email = ?, passwordHash = ?, registeredAt = ?, lastLogin = ?, intro = ?, profile = ?, image = ?, type = ? where id = ?";

		con = new ConnecDataBase().getConnection();
		ps = con.prepareStatement(sql);
		ps.setString(1, user.getFirstName());
		ps.setString(2, user.getMiddleName());
		ps.setString(3, user.getLastName());
		ps.setString(4, user.getMobile());
		ps.setString(5, user.getEmail());
		ps.setString(6, user.getPasswordHash());
		ps.setTimestamp(7, user.getRegisteredAt());
		ps.setTimestamp(8, user.getLastLogin());
		ps.setString(9, user.getIntro());
		ps.setString(10, user.getProfile());
		ps.setString(11, user.getImage());
		ps.setInt(12, user.getType());
		ps.setLong(13, user.getId());

		res = ps.executeUpdate() > 0;
		con.close();
		ps.close();
		return res;
	}

	public boolean updateLastLogin(Timestamp time, Long id) throws Exception {
		boolean res = false;
		sql = "update Users set lastLogin = ? where id = ?";

		con = new ConnecDataBase().getConnection();
		ps = con.prepareStatement(sql);
		ps.setTimestamp(1, time);
		ps.setLong(2, id);

		res = ps.executeUpdate() > 0;
		con.close();
		ps.close();
		return res;
	}

	public boolean updateImage(String img, Long id) throws Exception {
		boolean res = false;
		sql = "update Users set image = ? where id = ?";

		con = new ConnecDataBase().getConnection();
		ps = con.prepareStatement(sql);
		ps.setString(1, img);
		ps.setLong(2, id);

		res = ps.executeUpdate() > 0;
		con.close();
		ps.close();
		return res;
	}

	public boolean updatePassword(String email, String password) throws Exception {
		boolean res = false;
		sql = "UPDATE Users SET passwordHash = ? WHERE email = ?";

		con = new ConnecDataBase().getConnection();
		ps = con.prepareStatement(sql);
		ps.setString(1, password);
		ps.setString(2, email);

		res = ps.executeUpdate() > 0;
		con.close();
		ps.close();
		return res;
	}
	
	public boolean updateStatus(long id, String status) throws Exception {
		boolean res = false;
		sql = "update Users set status = ? where id = ?";

		con = new ConnecDataBase().getConnection();
		ps = con.prepareStatement(sql);
		ps.setString(1, status);
		ps.setLong(2, id);	

		res = ps.executeUpdate() > 0;
		con.close();
		ps.close();
		return res;
	}

	public boolean deleteUser(Long id) throws Exception {
		boolean res = false;
		sql = "delete from Users where id = ?";

		con = new ConnecDataBase().getConnection();
		ps = con.prepareStatement(sql);
		ps.setLong(1, id);

		res = ps.executeUpdate() > 0;
		con.close();
		ps.close();
		return res;
	}

	public boolean deleteImage(Long id) throws Exception {
		boolean res = false;
		sql = "update Users set image = NULL where id = ?";

		con = new ConnecDataBase().getConnection();
		ps = con.prepareStatement(sql);
		ps.setLong(1, id);

		res = ps.executeUpdate() > 0;
		con.close();
		ps.close();
		return res;
	}

	public boolean checkUser(String email) throws Exception {
		int res = 0;
		sql = "SELECT * FROM Users WHERE email = ?";

		con = new ConnecDataBase().getConnection();
		ps = con.prepareStatement(sql);
		ps.setString(1, email);
		rs = ps.executeQuery();
		if (rs.next()) {
			res = 1;
		}
		con.close();
		ps.close();

		return res > 0;
	}
}
