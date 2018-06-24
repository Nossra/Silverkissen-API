package se.consys.silverkissen.dataaccess.litters;

import java.util.List;

import se.consys.silverkissen.entities.Litter;
import se.consys.silverkissen.entities.Log;

public interface iLitterDao {
	public void create(Litter litter);
	public void update(Litter litter);
	public void delete(Litter litter);
	public Litter findById(int id);
	public List<Litter> findAll();
	public void saveLog(Log log);
}
