package cyclaeon.service;

import java.util.List;

public interface CycleQueryService {

	List<CycleDto> findAll();

	CycleDto findCycle(String name);

}