package se.consys.silverkissen.dataaccess.cats;

import java.util.List;

import se.consys.silverkissen.entities.Cat;
import se.consys.silverkissen.entities.Log;

public interface iCatDao {
	void create(Cat cat);
	void update(Cat cat);
	void delete(Cat cat);
	Cat findById(int id);
	List<Cat> findAll();
	void saveLog(Log log);
}
