package cyclaeon.entity;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

@Embeddable
public class FactionId implements Serializable {

	private static final long serialVersionUID = 1L;

	@Embedded
	private final StringId id;

	@Embedded
	private final StringId cycleId;

	private FactionId(String id, StringId cycleId) {
		this.id = StringId.of(id);
		this.cycleId = cycleId;
	}

	static FactionId of(String id, StringId cycleId) {
		return new FactionId(id, cycleId);
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

	public String getId() {
		return id.id;
	}

	public String getCycleId() {
		return cycleId.id;
	}

}