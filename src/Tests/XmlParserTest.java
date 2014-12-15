package Tests;

import static org.junit.Assert.*;

import org.junit.Test;

import Database.*;

public class XmlParserTest {

	DBmain d = new DBmain();
	
	@Test
	public void test() {
		assertTrue(d.getSize() == 0);
		d = XmlParser.parseDB();
		assertFalse(d.getSize() == 0);
	}
}
