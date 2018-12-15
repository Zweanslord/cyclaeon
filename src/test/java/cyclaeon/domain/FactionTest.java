package cyclaeon.domain;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import cyclaeon.domain.Faction;
import cyclaeon.domain.FactionId;
import cyclaeon.domain.StringId;

public class FactionTest {

	@Test
	public void create() {
		Faction faction = Faction.create("Id", StringId.of("Cycle-Id"), "Name");

		assertEquals(FactionId.of("Id", StringId.of("Cycle-Id")), faction.getId());
		assertEquals("Name", faction.getName());
		assertEquals("", faction.getDescription());
	}

	@Test(expected = IllegalArgumentException.class)
	public void createWithBlankName() {
		Faction.create("IRRELEVANT", StringId.of("IRRELEVANT"), "");
	}

	@Test
	public void updateNameAndDescription() {
		Faction faction = Faction.create("IRRELEVANT", StringId.of("IRRELEVANT"), "NAME");

		faction.updateNameAndDescription("Another Name", "Updated Description.");

		assertEquals("Another Name", faction.getName());
		assertEquals("Updated Description.", faction.getDescription());
	}

	@Test(expected = IllegalArgumentException.class)
	public void updateNameAndDescriptionWithBlankName() {
		Faction faction = Faction.create("IRRELVANT", StringId.of("IRRELEVANT"), "NAME");

		faction.updateNameAndDescription("", "Updated Description.");
	}

}