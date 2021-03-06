package cyclaeon.domain;

import javax.persistence.Embedded;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

@Entity
public class Faction {

	@EmbeddedId
	private final FactionId id;

	private String name;
	private String description;

	@Embedded
	private Teams teams;

	private Faction() {
		super();
		id = null;
		name = null;
		description = null;
		teams = null;
	}

	private Faction(String id, StringId cycleId, String name, String description) {
		super();

		var factionId = FactionId.of(id, cycleId);
		validateName(name);

		this.id = factionId;
		this.name = name;
		this.description = description;
		teams = Teams.zero();
	}

	private static void validateName(String name) {
		if (StringUtils.isBlank(name)) {
			throw new IllegalArgumentException("Faction name cannot be blank.");
		}
	}

	static Faction create(String id, StringId cycleId, String name) {
		var description = "";
		return new Faction(id, cycleId, name, description);
	}

	void updateNameAndDescription(String name, String description) {
		validateName(name);

		this.name = name;
		this.description = description;
	}

	void assembleTeams(TeamsAssemblyInput teamsAssemblyInput) {
		this.teams = Teams.assemble(teamsAssemblyInput);
	}

	boolean hasFactionId(String factionId) {
		return id.getId().equals(factionId);
	}

	@Override
	public boolean equals(Object object) {
		if (object == null) {
			return false;
		}
		if (object == this) {
			return true;
		}
		if (object.getClass() != getClass()) {
			return false;
		}

		Faction other = (Faction) object;
		return new EqualsBuilder()
				.append(id, other.id)
				.build();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder()
				.append(id)
				.toHashCode();
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	public FactionId getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public int getIlluminators() {
		return teams.getIlluminators();
	}

	public int getObscurers() {
		return teams.getObscurers();
	}

	public int getCreators() {
		return teams.getCreators();
	}

	public int getDestroyers() {
		return teams.getDestroyers();
	}

	public int getGuardians() {
		return teams.getGuardians();
	}

}