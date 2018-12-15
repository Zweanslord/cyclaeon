package cyclaeon.domain;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import cyclaeon.domain.FactionId;
import cyclaeon.domain.StringId;

public class FactionIdTest {

	@Test
	public void of() {
		FactionId factionId = FactionId.of("ID", StringId.of("CYCLE-ID"));

		assertEquals("ID", factionId.getId());
		assertEquals("CYCLE-ID", factionId.getCycleId());
	}

}
