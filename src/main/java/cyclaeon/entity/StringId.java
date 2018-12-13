package cyclaeon.entity;

import java.io.Serializable;

import javax.persistence.Embeddable;

import org.apache.commons.lang3.builder.EqualsBuilder;

@Embeddable
public class StringId implements Serializable {

	private static final long serialVersionUID = 1L;

	private static final String REGULAR_EXPRESSION_ALPHANUMERIC_UNDERSCORE_DASH = "[a-zA-Z0-9_-]+";

	public final String id;

	// Hibernate Constructor
	private StringId() {
		id = null;
	}

	private StringId(String stringId) {
		if (stringId == null || !stringId.matches(REGULAR_EXPRESSION_ALPHANUMERIC_UNDERSCORE_DASH)) {
			throw new IllegalArgumentException(
					String.format("StringId '%s' is empty or contains other characters than alphanumeric, underscore or dash.", stringId));
		}

		this.id = stringId;
	}

	public static StringId of(String id) {
		return new StringId(id);
	}

	@Override
	public boolean equals(Object other) {
		return EqualsBuilder.reflectionEquals(this, other);
	}

	@Override
	public int hashCode() {
		return id.hashCode();
	}

	@Override
	public String toString() {
		return id;
	}

}