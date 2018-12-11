package cyclaeon.entity;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class CycleTest {

	@Test
	public void create() {
		Cycle cycle = Cycle.create("NAME");

		assertEquals("NAME", cycle.getName());
		assertEquals("", cycle.getDescription());
	}

	@Test(expected = IllegalArgumentException.class)
	public void createWithBlankName() {
		Cycle.create("");
	}

	@Test
	public void updateDescription() {
		Cycle cycle = createCycle();

		cycle.updateDescription("DESCRIPTION");

		assertEquals("DESCRIPTION", cycle.getDescription());
	}

	private static Cycle createCycle() {
		return Cycle.create("IRRELEVANT_NAME");
	}

}