package de.hftstuttgart.snarex.datapoint.main;

import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import de.hftstuttgart.snarex.datapoint.source.Datapoint;

public class DatapointManager {
	static SessionFactory factory;

	// save object in database/table
	public static void save(Datapoint datapoint) {
		Session session = factory.openSession();
		session.beginTransaction();
		session.save(datapoint);
		session.getTransaction().commit();
		session.close();
	}

	// delete object from database/table by id
	public static void delete(int id) {
		Session session = factory.openSession();
		session.beginTransaction();
		Datapoint dtp = new Datapoint();
		dtp.setId(id);
		session.delete(dtp);
		session.getTransaction().commit();
		session.close();
	}

	// get/read values from database/table
	public static List<Datapoint> getData() {
		List<Datapoint> datapoints; // created list named employees
		Session session = factory.openSession(); // open Session to create SQL argument
		datapoints = session.createQuery("FROM Datapoint").list();
		session.close();
		return datapoints;
	}

	public static void main(String[] args) {
		
		// open database connection
		try {
			factory = new Configuration().configure().buildSessionFactory();
			System.out.println("sessionFactory object created successfully. Connection to database is up.");
		} catch (Throwable ex) {
			System.err.println("Failed to create sessionFactory object." + ex);
			throw new ExceptionInInitializerError(ex);
		}
		
		Datapoint dtp = new Datapoint();
		dtp.setDate(LocalDateTime.now());
		dtp.setTemperature(36.2);
		dtp.setPressure(2.1);
		dtp.setRevolutions(8000.2);
		
		//close database connection
		factory.close();
	}

}
