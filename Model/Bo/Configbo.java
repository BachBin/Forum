package Bo;

import Bean.Config;
import Dao.Configdao;

public class Configbo {
	Configdao configdao = new Configdao();
	
	public Config getConfig() throws Exception {
		return configdao.getConfig();
	}	
	public boolean updateConfig(boolean co, int type) throws Exception {
		return configdao.updateConfig(co, type);
	}
}
