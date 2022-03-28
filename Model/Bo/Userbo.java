package Bo;

import java.sql.Timestamp;
import java.util.ArrayList;

import Bean.Userbean;
import Dao.Userdao;

public class Userbo {
	Userdao userdao = new Userdao();
	
	public Long getSLUser(int type) throws Exception {
		return userdao.getSLUser(type);
	}
	public ArrayList<Userbean> getUserbyPage(long index, long sobai, int type) throws Exception {
		return userdao.getUserbyPage(index, sobai, type);
	}
	public ArrayList<Userbean> getUsers() throws Exception {
		return userdao.getUsers();
	}	
	public Userbean getUserbyId(Long id) throws Exception {	
		return userdao.getUserbyId(id);
	}
	public Userbean getUserbyUniqueId(Long id) throws Exception {
		return userdao.getUserbyUniqueId(id);
	}
	public ArrayList<Userbean> getAdministrators() throws Exception {
		ArrayList<Userbean> ds1 = new ArrayList<Userbean>();	
		ArrayList<Userbean> ds2 = new ArrayList<Userbean>();
		ArrayList<Userbean> ds = new ArrayList<Userbean>();
		for(Userbean e:getUsers()) {
			if(e.getType()==1)
				ds1.add(e);
			if(e.getType()==2)
				ds2.add(e);
		}
		ds.addAll(ds2);
		ds.addAll(ds1);
		return ds;
	}	
	public ArrayList<Userbean> searchUser(String search) throws Exception {
		return userdao.searchUser(search);
	}
	public Userbean getUser(String email, String password) throws Exception {	
		return userdao.getUser(email, password);
	}
	public long getSLUserbySearch(String search) throws Exception {
		return userdao.getSLUserbySearch(search);
	}
	public ArrayList<Userbean> getUserbyPageSearch(long index, long sobai,String search, int type) throws Exception {
		return userdao.getUserbyPageSearch(index, sobai, search, type);
	}
	public boolean createUser(String email, String password, int type) throws Exception {
		return userdao.createUser(email, password, type);
	}
	public boolean updateUser(Userbean user) throws Exception {
		return userdao.updateUser(user);
	}
	public boolean updateLastLogin(Timestamp time, Long id) throws Exception {
		return userdao.updateLastLogin(time, id);
	}
	public boolean updateImage(String img, Long id) throws Exception {
		return userdao.updateImage(img, id);
	}
	public boolean updatePassword(String email, String password) throws Exception {
		return userdao.updatePassword(email, password);
	}
	public boolean updateStatus(long id, String status) throws Exception {
		return userdao.updateStatus(id, status);
	}
	public boolean deleteUser(Long id) throws Exception{
		return userdao.deleteUser(id);
	}
	public boolean deleteImage(Long id) throws Exception {
		return userdao.deleteImage(id);
	}
	public boolean checkUser(String email) throws Exception {
		return userdao.checkUser(email);
	}
}
