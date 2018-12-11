package cyclaeon.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

@Entity
public class Cycle {

	@Id
	private final String name;

	private String description;

	// Hibernate constructor
	@SuppressWarnings("unused")
	private Cycle() {
		this.name = null;
		this.description = "";
	}

	private Cycle(String name) {
		if (StringUtils.isBlank(name)) {
			throw new IllegalArgumentException("Invalid cycle name. Name is blank.");
		}

		this.name = name;
		this.description = "";
	}

	public static Cycle create(String name) {
		return new Cycle(name);
	}

	public void updateDescription(String description) {
		this.description = description;
	}

	@Override
	public boolean equals(Object other) {
		return EqualsBuilder.reflectionEquals(this, other);
	}

	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

}