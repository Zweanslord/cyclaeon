package cyclaeon.service;

import static org.junit.Assert.assertEquals;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import cyclaeon.entity.Cycle;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class CycleQueryServiceIT {

	@Autowired
	private CycleQueryService sut;

	@PersistenceContext
	private EntityManager entityManager;

	@Test
	public void findAll() {
		createCycle("cycle");

		var cycles = sut.findAll();

		List<CycleDto> expected = List.of(new CycleDto("cycle", "cycle", ""));
		assertEquals(expected, cycles);
	}

	private void createCycle(String name) {
		entityManager.persist(Cycle.create(name));
		entityManager.flush();
	}

}
