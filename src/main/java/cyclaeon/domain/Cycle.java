package cyclaeon.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

@Entity
public class Cycle {

	@EmbeddedId
	private final StringId id;

	private String name;
	private String description;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "cycleId", insertable = false, updatable = false)
	private Set<Faction> factions;

	private Cycle() {
		super();
		id = null;
		name = null;
		description = "";
		factions = new HashSet<>();
	}

	private Cycle(String id, String name, String description) {
		super();

		var stringId = StringId.of(id);
		validateName(name);

		this.id = stringId;
		this.name = name;
		this.description = description;
		this.factions = new HashSet<>();
	}

	private static void validateName(String name) {
		if (StringUtils.isBlank(name)) {
			throw new IllegalArgumentException("Cycle name cannot be blank.");
		}
	}

	public static Cycle create(String id) {
		return new Cycle(id, id, "");
	}

	public void updateNameAndDescription(String name, String description) {
		validateName(name);

		this.name = name;
		this.description = description;
	}

	public void createFaction(String factionId, String name) {
		var faction = Faction.create(factionId, id, name);
		factions.add(faction);
	}

	public void assembleTeams(String factionId, TeamsAssemblyInput teamsAssemblyInput) {
		var faction = findFaction(factionId);

		CycleStartRules.validateTeamsAssembly(teamsAssemblyInput);

		faction.assembleTeams(teamsAssemblyInput);
	}

	private Faction findFaction(String factionId) {
		return factions.stream()
				.filter(faction -> faction.hasFactionId(factionId))
				.findAny()
				.orElseThrow(() -> new IllegalArgumentException(String.format("Faction with id '%s' not in the cycle.", factionId)));
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

		Cycle other = (Cycle) object;
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

	public String getId() {
		return id.id;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public Set<Faction> getFactions() {
		return Set.copyOf(factions);
	}

}