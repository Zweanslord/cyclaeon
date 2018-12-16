package cyclaeon.domain;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public final class TeamsAssemblyInput {

	public final int illuminators;
	public final int obscurers;
	public final int creators;
	public final int destroyers;

	public TeamsAssemblyInput(int illuminators, int obscurers, int creators, int destroyers) {
		super();
		this.illuminators = illuminators;
		this.obscurers = obscurers;
		this.creators = creators;
		this.destroyers = destroyers;
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

}