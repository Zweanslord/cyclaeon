package cyclaeon.domain;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TeamsTest {

	@Test
	public void zero() {
		Teams teams = Teams.zero();

		assertEquals(0, teams.getIlluminators());
		assertEquals(0, teams.getObscurers());
		assertEquals(0, teams.getCreators());
		assertEquals(0, teams.getDestroyers());
		assertEquals(0, teams.getGuardians());
	}

	@Test
	public void assemble() {
		Teams teams = Teams.assemble(new TeamsAssemblyInput(1, 1, 1, 1));

		assertEquals(1, teams.getIlluminators());
		assertEquals(1, teams.getObscurers());
		assertEquals(1, teams.getCreators());
		assertEquals(1, teams.getDestroyers());
		assertEquals(0, teams.getGuardians());
	}

	@Test(expected = IllegalArgumentException.class)
	public void assembleZeroIlluminators() {
		Teams.assemble(new TeamsAssemblyInput(0, 1, 1, 1));
	}

	@Test(expected = IllegalArgumentException.class)
	public void assembleZeroObscurers() {
		Teams.assemble(new TeamsAssemblyInput(1, 0, 1, 1));
	}

	@Test(expected = IllegalArgumentException.class)
	public void assembleZeroCreators() {
		Teams.assemble(new TeamsAssemblyInput(1, 1, 0, 1));
	}

	@Test(expected = IllegalArgumentException.class)
	public void assembleZeroDestroyers() {
		Teams.assemble(new TeamsAssemblyInput(1, 1, 1, 0));
	}

}