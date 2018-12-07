package cyclaeon.repository;

import org.springframework.data.repository.CrudRepository;

import cyclaeon.entity.Cycle;

public interface CycleRepository extends CrudRepository<Cycle, String>{
	
}