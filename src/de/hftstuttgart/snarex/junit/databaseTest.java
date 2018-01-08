package de.hftstuttgart.snarex.junit;

import static org.junit.Assert.*;

import org.junit.Test;

import de.hftstuttgart.snarex.datapoint.main.DatapointManager;
import junit.framework.Assert;

public class databaseTest {

	@Test
	public void testConnection() {
		assertEquals("Connection is up", DatapointManager.getConnection());
	}

}
