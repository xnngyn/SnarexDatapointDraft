package de.hftstuttgart.snarex.junit;

import static org.junit.Assert.*;

import java.time.LocalDateTime;

import org.junit.Test;

import de.hftstuttgart.snarex.datapoint.main.DatapointManager;
import de.hftstuttgart.snarex.datapoint.source.Datapoint;

public class databaseTest {

	Datapoint dtp = new Datapoint();

	@Test
	public void testConnection() {
		assertEquals(true, DatapointManager.getConnection());
	}

	@Test
	public void testSaving() {
		dtp.setDate(LocalDateTime.now());
		dtp.setTemperature(30.2);
		dtp.setPressure(1.8);
		dtp.setRevolutions(9000.3);
		assertEquals(true, DatapointManager.save(dtp));

	}

	@Test
	public void testDeleting() {
		dtp.setDate(LocalDateTime.now());
		dtp.setTemperature(30.2);
		dtp.setPressure(1.8);
		dtp.setRevolutions(9000.3);
		assertEquals(true, DatapointManager.deleteByObject(dtp));
	}

	@Test
	public void testReading() {
		assertNotNull(DatapointManager.getData());
	}
}
