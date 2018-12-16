package cyclaeon.domain;

import org.junit.Test;

public class CycleStartRulesTest {

	@Test
	public void validateTeamsAssemblyAllOnes() {
		TeamsAssemblyInput input = new TeamsAssemblyInput(1, 1, 1, 1);

		CycleStartRules.validateTeamsAssembly(input);
	}

	@Test
	public void validateTeamsAssemblyTenIlluminators() {
		TeamsAssemblyInput input = new TeamsAssemblyInput(10, 1, 1, 1);

		CycleStartRules.validateTeamsAssembly(input);
	}

	@Test
	public void validateTeamsAssemblyTenObscurers() {
		TeamsAssemblyInput input = new TeamsAssemblyInput(1, 10, 1, 1);

		CycleStartRules.validateTeamsAssembly(input);
	}

	@Test
	public void validateTeamsAssemblyTenCreators() {
		TeamsAssemblyInput input = new TeamsAssemblyInput(1, 1, 10, 1);

		CycleStartRules.validateTeamsAssembly(input);
	}

	@Test
	public void validateTeamsAssemblyTenDestroyers() {
		TeamsAssemblyInput input = new TeamsAssemblyInput(1, 1, 1, 10);

		CycleStartRules.validateTeamsAssembly(input);
	}

	@Test(expected = IllegalArgumentException.class)
	public void validateTeamsAssemblyElevenIlluminators() {
		TeamsAssemblyInput input = new TeamsAssemblyInput(11, 1, 1, 1);

		CycleStartRules.validateTeamsAssembly(input);
	}

	@Test(expected = IllegalArgumentException.class)
	public void validateTeamsAssemblyElevenObscurers() {
		TeamsAssemblyInput input = new TeamsAssemblyInput(1, 11, 1, 1);

		CycleStartRules.validateTeamsAssembly(input);
	}

	@Test(expected = IllegalArgumentException.class)
	public void validateTeamsAssemblyElevenCreators() {
		TeamsAssemblyInput input = new TeamsAssemblyInput(1, 1, 11, 1);

		CycleStartRules.validateTeamsAssembly(input);
	}

	@Test(expected = IllegalArgumentException.class)
	public void validateTeamsAssemblyElevenDestroyers() {
		TeamsAssemblyInput input = new TeamsAssemblyInput(1, 1, 1, 11);

		CycleStartRules.validateTeamsAssembly(input);
	}

	@Test(expected = IllegalArgumentException.class)
	public void validateTeamsAssemblyTwentyOneTeams() {
		TeamsAssemblyInput input = new TeamsAssemblyInput(6, 5, 5, 5);

		CycleStartRules.validateTeamsAssembly(input);
	}

}
