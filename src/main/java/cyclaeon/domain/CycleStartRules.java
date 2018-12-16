package cyclaeon.domain;

/**
 * Instead of a class offering static functionality, this could be an embedded persisted class for {@link Cycle} with
 * the current constants being updated. Currently hard-coded for speed of delivery.
 */
final class CycleStartRules {

	private CycleStartRules() {
	}

	private static final int STARTING_TEAMS_PER_FACTION = 20;
	private static final int MAXIMUM_STARTING_TEAMS_PER_TYPE = 10;

	static void validateTeamsAssembly(TeamsAssemblyInput teamsAssemblyInput) {
		validateStartingTeams(teamsAssemblyInput, STARTING_TEAMS_PER_FACTION);
		validateMaximumTeams(teamsAssemblyInput, MAXIMUM_STARTING_TEAMS_PER_TYPE);
	}

	private static void validateStartingTeams(TeamsAssemblyInput teamsAssemblyInput, int startingTeamsPerFaction) {
		int totalTeams = totalTeams(teamsAssemblyInput);
		if (totalTeams > startingTeamsPerFaction) {
			throw new IllegalArgumentException(
					String.format("Total assembled teams '%s' exceeds starting teams '%s'.", totalTeams, startingTeamsPerFaction));
		}
	}

	private static int totalTeams(TeamsAssemblyInput teamsAssemblyInput) {
		return teamsAssemblyInput.illuminators
				+ teamsAssemblyInput.obscurers
				+ teamsAssemblyInput.creators
				+ teamsAssemblyInput.destroyers;
	}

	private static void validateMaximumTeams(TeamsAssemblyInput teamsAssemblyInput, int maximumStartingTeamsPerType) {
		validateMaximumTeams(teamsAssemblyInput.illuminators, maximumStartingTeamsPerType, "illuminators");
		validateMaximumTeams(teamsAssemblyInput.obscurers, maximumStartingTeamsPerType, "obscurers");
		validateMaximumTeams(teamsAssemblyInput.creators, maximumStartingTeamsPerType, "creators");
		validateMaximumTeams(teamsAssemblyInput.destroyers, maximumStartingTeamsPerType, "destroyers");
	}

	private static void validateMaximumTeams(int teams, int maximum, String type) {
		if (teams > maximum) {
			throw new IllegalArgumentException(
					String.format("Amount of %s '%s' exceeds maximum of '%'.", type, teams, maximum));
		}
	}

}