package cyclaeon.entity;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class StringIdTest {

	@Test
	public void testOf() {
		StringId stringId = StringId.of("STRING_ID-Test1");

		assertEquals("STRING_ID-Test1", stringId.id);
	}

	@Test
	public void testOfAlphabet() {
		StringId stringId = StringId.of("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmonpqrstuvwxyz");

		assertEquals("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmonpqrstuvwxyz", stringId.id);
	}

	@Test
	public void testOfNumeric() {
		StringId stringId = StringId.of("0123456789");

		assertEquals("0123456789", stringId.id);
	}

	@Test
	public void testOfDashUnderscore() {
		StringId stringId = StringId.of("-_");

		assertEquals("-_", stringId.id);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testOfOtherCharacters() {
		StringId.of("StringId!@#$%^&*()");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testOfBlank() {
		StringId.of("");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testOfNull() {
		StringId.of(null);
	}

}