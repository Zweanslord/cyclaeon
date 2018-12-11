package cyclaeon.service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class CycleApplicationServiceIT {

	@Autowired
	private CycleApplicationService sut;

	@PersistenceContext
	private EntityManager entityManager;

	@Test
	public void create() {
		sut.create("cycle");

		entityManager.flush();
	}

	@Test
	public void createDuplicate() {
		sut.create("cycle");
		sut.create("cycle");

		entityManager.flush();
	}

}