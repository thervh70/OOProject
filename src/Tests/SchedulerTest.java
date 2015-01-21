package Tests;


import static org.junit.Assert.*;

import org.junit.Test;

import Model.Competition;
import Model.Scheduler;

public class SchedulerTest {

	@Test
	public void testGenerate() {
		System.out.println(Math.random());
		Competition comp = Scheduler.generate();
		assertTrue(comp != null);
	}

}
