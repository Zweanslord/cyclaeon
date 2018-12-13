package cyclaeon.entity;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class FactionIdTest {

	@Test
	public void of() {
		FactionId factionId = FactionId.of("ID", StringId.of("CYCLE-ID"));

		assertEquals("ID", factionId.getId());
		assertEquals("CYCLE-ID", factionId.getCycleId());
	}

}
