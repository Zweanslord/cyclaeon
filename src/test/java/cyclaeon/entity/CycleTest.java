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
	public void updateNameAndDescription() {
		Cycle cycle = createCycle();

		cycle.updateNameAndDescription("NAME", "DESCRIPTION");

		assertEquals("NAME", cycle.getName());
		assertEquals("DESCRIPTION", cycle.getDescription());
	}

	@Test(expected = IllegalArgumentException.class)
	public void updateNameAndDescriptionWithBlankName() {
		Cycle cycle = createCycle();

		cycle.updateNameAndDescription("", "IRRELEVANT_DESCRIPTION");
	}

	private static Cycle createCycle() {
		return Cycle.create("IRRELEVANT_NAME");
	}

}