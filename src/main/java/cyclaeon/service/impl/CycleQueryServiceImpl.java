package cyclaeon.service.impl;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cyclaeon.entity.Cycle;
import cyclaeon.repository.CycleRepository;
import cyclaeon.service.CycleDto;
import cyclaeon.service.CycleQueryService;

@Service
public class CycleQueryServiceImpl implements CycleQueryService {

	private final CycleRepository cycleRepository;

	@Autowired
	public CycleQueryServiceImpl(CycleRepository cycleRepository) {
		this.cycleRepository = cycleRepository;
	}

	@Override
	@Transactional(readOnly = true)
	public List<CycleDto> findAll() {
		return toDtos(cycleRepository.findAll());
	}

	private static List<CycleDto> toDtos(Iterable<Cycle> cycles) {
		return StreamSupport.stream(cycles.spliterator(), false)
				.map(CycleQueryServiceImpl::toDto)
				.collect(Collectors.toList());
	}

	private static CycleDto toDto(Cycle cycle) {
		return new CycleDto(
				cycle.getName(),
				cycle.getDescription());
	}

	@Override
	public CycleDto findCycle(String name) {
		return cycleRepository.findById(name)
				.map(CycleQueryServiceImpl::toDto)
				.orElseThrow(() -> new IllegalArgumentException(String.format("Did not find Cycle '%s'.", name)));
	}

}