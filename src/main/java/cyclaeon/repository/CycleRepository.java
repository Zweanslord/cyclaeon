package cyclaeon.repository;

import org.springframework.data.repository.CrudRepository;

import cyclaeon.entity.Cycle;
import cyclaeon.entity.StringId;

public interface CycleRepository extends CrudRepository<Cycle, StringId> {

}