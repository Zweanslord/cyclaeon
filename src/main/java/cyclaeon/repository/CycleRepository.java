package cyclaeon.repository;

import org.springframework.data.repository.CrudRepository;

import cyclaeon.domain.Cycle;
import cyclaeon.domain.StringId;

public interface CycleRepository extends CrudRepository<Cycle, StringId> {

}