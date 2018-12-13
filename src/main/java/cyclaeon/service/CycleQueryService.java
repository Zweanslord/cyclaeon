package cyclaeon.service;

import java.util.List;
import java.util.Optional;

public interface CycleQueryService {

	List<CycleDto> findAll();

	Optional<CycleDto> findCycle(String cycleId);

}