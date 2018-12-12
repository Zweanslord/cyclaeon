package cyclaeon.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cyclaeon.entity.Cycle;
import cyclaeon.repository.CycleRepository;
import cyclaeon.service.CycleApplicationService;

@Service
public class CycleApplicationServiceImpl implements CycleApplicationService {

	private final CycleRepository cycleRepository;

	@Autowired
	public CycleApplicationServiceImpl(CycleRepository cycleRepository) {
		this.cycleRepository = cycleRepository;
	}

	@Override
	@Transactional
	public void create(String cycleName) {
		var cycle = Cycle.create(cycleName);
		cycleRepository.save(cycle);
	}

	@Override
	@Transactional
	public void updateDescription(String cycleName, String description) {
		var cycle = findCycleByName(cycleName);
		cycle.updateDescription(description);
	}

	private Cycle findCycleByName(String name) {
		return cycleRepository.findById(name)
				.orElseThrow(() -> new IllegalStateException(String.format("Could not find cycle '%s'.", name)));
	}

}