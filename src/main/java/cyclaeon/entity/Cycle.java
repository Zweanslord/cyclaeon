package cyclaeon.entity;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

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

	private Cycle() {
		this.id = null;
		this.name = null;
		this.description = "";
	}

	private Cycle(String id, String name, String description) {
		var stringId = StringId.of(id);
		validateName(name);

		this.id = stringId;
		this.name = name;
		this.description = description;
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
				.appendSuper(super.equals(object))
				.append(name, other.name)
				.build();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder()
				.append(name)
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

}