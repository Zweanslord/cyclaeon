package cyclaeon.service;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.assertj.core.util.Arrays;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import cyclaeon.CyclaeonApplication;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {CyclaeonApplication.class})
@Transactional
public class CycleServiceIT {
	
	@Autowired
	private CycleService sut;
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Test
	public void create() {
		sut.create(new CycleDto("cycle"));
		
		entityManager.flush();
	}
	
	@Test
	public void findAll() {
		createCycle("cycle");
		
		var cycles = sut.findAll();
		
		List<CycleDto> expected = new ArrayList<>();
		expected.add(new CycleDto("cycle"));
		assertEquals(expected, cycles);
	}

	private void createCycle(String string) {
		sut.create(new CycleDto("cycle"));
		entityManager.flush();
		
	}

}
