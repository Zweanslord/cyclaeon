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
				.map(Cycle::getName)
				.map(CycleDto::new)
				.collect(Collectors.toList());
	}

}