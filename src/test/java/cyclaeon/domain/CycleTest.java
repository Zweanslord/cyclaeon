package cyclaeon.domain;

import static org.junit.Assert.assertEquals;

import java.util.Set;

import org.junit.Test;

public class CycleTest {

	@Test
	public void create() {
		Cycle cycle = Cycle.create("Id");

		assertEquals("Id", cycle.getId());
		assertEquals("Id", cycle.getName());
		assertEquals("", cycle.getDescription());
		assertEquals(Set.of(), cycle.getFactions());
	}

	@Test(expected = IllegalArgumentException.class)
	public void createWithBlankName() {
		Cycle.create("");
	}

	@Test
	public void updateNameAndDescription() {
		Cycle cycle = Cycle.create("IRRELEVANT_ID");

		cycle.updateNameAndDescription("Updated name", "Updated description.");

		assertEquals("Updated name", cycle.getName());
		assertEquals("Updated description.", cycle.getDescription());
	}

	@Test(expected = IllegalArgumentException.class)
	public void updateNameAndDescriptionWithBlankName() {
		Cycle cycle = Cycle.create("IRRELEVANT_ID");

		cycle.updateNameAndDescription("", "IRRELEVANT_DESCRIPTION");
	}

	@Test
	public void createFaction() {
		Cycle cycle = Cycle.create("Id");

		cycle.createFaction("factionId", "Name");

		Set<Faction> expected = Set.of(
				Faction.create("factionId", StringId.of("Id"), "Name"));
		assertEquals(expected, cycle.getFactions());
	}

	@Test(expected = UnsupportedOperationException.class)
	public void getFactionsModifyCollection() {
		Cycle cycle = Cycle.create("Id");
		cycle.createFaction("factionId", "Name");

		cycle.getFactions().add(Faction.create("anotherFactionId", StringId.of("Id"), "Another Name"));
	}

	@Test
	public void assembleTeams() {
		Cycle cycle = Cycle.create("IRRELEVANT_ID");
		cycle.createFaction("factionId", "Name");

		cycle.assembleTeams("factionId", new TeamsAssemblyInput(1, 1, 1, 1));

		Faction faction = cycle.getFactions().iterator().next();
		assertEquals(1, faction.getIlluminators());
		assertEquals(1, faction.getObscurers());
		assertEquals(1, faction.getCreators());
		assertEquals(1, faction.getDestroyers());
		assertEquals(0, faction.getGuardians());
	}

	@Test(expected = IllegalArgumentException.class)
	public void assembleTeamsNegativeInput() {
		Cycle cycle = Cycle.create("IRRELEVANT_ID");
		cycle.createFaction("factionId", "Name");

		cycle.assembleTeams("factionId", new TeamsAssemblyInput(-1, -1, -1, -1));
	}

	@Test(expected = IllegalArgumentException.class)
	public void assembleTeamsNonExistentFaction() {
		Cycle cycle = Cycle.create("IRRELEVANT_ID");

		cycle.assembleTeams("factionId", new TeamsAssemblyInput(-1, -1, -1, -1));
	}

}