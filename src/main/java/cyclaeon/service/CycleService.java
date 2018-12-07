package cyclaeon.service;

import java.util.List;

public interface CycleService {
	
	List<CycleDto> findAll();
	
	void create(CycleDto cycle);

}