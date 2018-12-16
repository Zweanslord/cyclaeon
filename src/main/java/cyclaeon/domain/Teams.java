package cyclaeon.domain;

import java.io.Serializable;

import javax.persistence.Embeddable;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

@Embeddable
public class Teams implements Serializable {

	private static final long serialVersionUID = 1L;

	private final int illuminators;
	private final int obscurers;
	private final int creators;
	private final int destroyers;
	private final int guardians;

	// Hibernate constructor
	private Teams() {
		this.illuminators = 0;
		this.obscurers = 0;
		this.creators = 0;
		this.destroyers = 0;
		this.guardians = 0;
	}

	private Teams(int illuminators, int obscurers, int creators, int destroyers, int guardians) {
		super();
		if (illuminators < 0) {
			throw new IllegalArgumentException("Illuminators cannot be lower than 0.");
		}
		if (obscurers < 0) {
			throw new IllegalArgumentException("Obscurers cannot be lower than 0.");
		}
		if (creators < 0) {
			throw new IllegalArgumentException("Creators cannot be lower than 0.");
		}
		if (destroyers < 0) {
			throw new IllegalArgumentException("Destroyers cannot be lower than 0.");
		}
		if (guardians < 0) {
			throw new IllegalArgumentException("Guardians cannot be lower than 0.");
		}

		this.illuminators = illuminators;
		this.obscurers = obscurers;
		this.creators = creators;
		this.destroyers = destroyers;
		this.guardians = guardians;
	}

	static Teams zero() {
		return new Teams(0, 0, 0, 0, 0);
	}

	static Teams assemble(TeamsAssemblyInput teamsAssemblyInput) {
		if (teamsAssemblyInput.illuminators < 1) {
			throw new IllegalArgumentException("A minimum of 1 Illuminator must be assembled.");
		}
		if (teamsAssemblyInput.obscurers < 1) {
			throw new IllegalArgumentException("Obscurers cannot be lower than 1.");
		}
		if (teamsAssemblyInput.creators < 1) {
			throw new IllegalArgumentException("Creators cannot be lower than 1.");
		}
		if (teamsAssemblyInput.destroyers < 1) {
			throw new IllegalArgumentException("Destroyers cannot be lower than 1.");
		}
		return new Teams(
				teamsAssemblyInput.illuminators,
				teamsAssemblyInput.obscurers,
				teamsAssemblyInput.creators,
				teamsAssemblyInput.destroyers,
				0);
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

	public int getIlluminators() {
		return illuminators;
	}

	public int getObscurers() {
		return obscurers;
	}

	public int getCreators() {
		return creators;
	}

	public int getDestroyers() {
		return destroyers;
	}

	public int getGuardians() {
		return guardians;
	}

}