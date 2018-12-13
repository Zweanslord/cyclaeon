package cyclaeon.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cyclaeon.entity.Cycle;
import cyclaeon.entity.StringId;
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
	public void create(String id) {
		var cycle = Cycle.create(id);
		cycleRepository.save(cycle);
	}

	@Override
	@Transactional
	public void updateDescriptionAndName(String id, String name, String description) {
		var cycle = findCycleById(id);
		cycle.updateNameAndDescription(name, description);
	}

	private Cycle findCycleById(String id) {
		return cycleRepository.findById(StringId.of(id))
				.orElseThrow(() -> new IllegalStateException(String.format("Could not find cycle by id '%s'.", id)));
	}

}