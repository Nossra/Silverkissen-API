package se.consys.silverkissen.dataaccess.user;

import se.consys.silverkissen.entities.Log;
import se.consys.silverkissen.entities.User;

public interface iUserDao {
	public boolean find(String email, String password);
	void saveLog(Log log);
}
