package se.consys.silverkissen.dataaccess.images;

import java.util.List;

import se.consys.silverkissen.entities.Image;
import se.consys.silverkissen.entities.Log;

public interface iImageDao {
	void create(Image image);
	void update(Image image);
	void delete(Image image);
	List<Image> findAll();
	Image findById(int id);
	void saveLog(Log log);
}
