package de.hftstuttgart.snarex.datapoint.main;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DatapointManager {
	static SessionFactory factory;

	public static void main(String[] args) {
		try {
			// open database connection
			factory = new Configuration().configure().buildSessionFactory();
			System.out.println("sessionFactory object created successfully. Connection to database is up.");
		} catch (Throwable ex) {
			System.err.println("Failed to create sessionFactory object." + ex);
			throw new ExceptionInInitializerError(ex);
		}

	}

}
