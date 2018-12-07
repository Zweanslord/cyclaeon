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
	
	// Hibernate constructor
	@SuppressWarnings("unused")
	private Cycle() {
		this.name = null;
	}
	
	private Cycle(String name) {
		if (StringUtils.isBlank(name)) {
			throw new IllegalArgumentException("Invalid cycle name. Name is blank.");
		}	
		
		this.name = name;
	}
	
	public static Cycle create(String name) {
		return new Cycle(name);
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

}
