package cyclaeon.service.impl;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cyclaeon.entity.Cycle;
import cyclaeon.repository.CycleRepository;
import cyclaeon.service.CycleDto;
import cyclaeon.service.CycleService;

@Service
public class CycleServiceImpl implements CycleService {
	
	private final CycleRepository cycleRepository;
	
	public CycleServiceImpl(CycleRepository cycleRepository) {
		this.cycleRepository = cycleRepository;
	}

	@Override
	@Transactional(readOnly = true)
	public List<CycleDto> findAll() {
		return toDtos(cycleRepository.findAll());
	}
	
	private static List<CycleDto> toDtos(Iterable<Cycle> cycles) {
		return StreamSupport.stream(cycles.spliterator(), false)
				.map(cycle -> new CycleDto(cycle.getName()))
				.collect(Collectors.toList());
	}

	@Override
	@Transactional
	public void create(CycleDto cycleDto) {
		var cycle = Cycle.create(cycleDto.name);
		cycleRepository.save(cycle);
	}

}