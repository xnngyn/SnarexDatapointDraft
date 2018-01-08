package de.hftstuttgart.snarex.datapoint.main;

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
