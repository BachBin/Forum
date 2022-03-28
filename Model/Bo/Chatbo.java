package Bo;

import java.util.ArrayList;

import Bean.Chat;
import Dao.Chatdao;

public class Chatbo {
	Chatdao chatdao = new Chatdao();
	
	public boolean insertChat(Long fromId, Long toId, String message) throws Exception {
		return chatdao.insertChat(fromId, toId, message);
	}
	public ArrayList<Chat> getChat(Long fromId, Long toId) throws Exception {
		return chatdao.getChat(fromId, toId);
	}
	public Chat getOneChat(Long toId, Long fromId) throws Exception {
		return chatdao.getOneChat(toId, fromId);
	}
}
