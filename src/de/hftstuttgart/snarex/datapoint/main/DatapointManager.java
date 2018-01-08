package de.hftstuttgart.snarex.datapoint.main;

import java.time.format.DateTimeFormatter;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import de.hftstuttgart.snarex.datapoint.source.Datapoint;

public class DatapointManager {
	/* initialize session factory */
	static SessionFactory factory;

	/* date formatter */
	static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

	public static void main(String[] args) {

		/* OPEN database connection */
		try {
			factory = new Configuration().configure().buildSessionFactory();
			System.out.println("sessionFactory object created successfully. Connection to database is up.");
		} catch (Throwable ex) {
			System.err.println("Failed to create sessionFactory object." + ex);
			throw new ExceptionInInitializerError(ex);
		}
		/* CREATE, DELETE and READ values */
		try {
			/* generate random values */
			/*
			 * for(int i= 0; i<250;i++) {
			 * Random r = new Random(); 
			 * double rTemperature = r.nextDouble()*100; 
			 * double rPressure = r.nextDouble()*10;
			 * double rRevolutions= r.nextDouble()*10000;
			 * Datapoint dtp1 = new Datapoint(LocalDateTime.now(),rTemperature,rPressure,rRevolutions);
			 * save(dtp1); 
			 * }
			 */

			/*
			 * Datapoint dtp= new Datapoint(LocalDateTime.now(),30.2,2.1,8000.1); 
			 * save(dtp);
			 */
			
			// delete(1201);
			
			/* PRINT values from database/table */
			List<Datapoint> datapoints = getData();
			System.out.println("---------------------------------------------------------------------------");
			System.out.print(
					String.format("%10s %5s %30s %10s %15s\n", "ID", "Date", "Temperature", "Pressure", "Revolutions"));
			System.out.println("---------------------------------------------------------------------------");
			for (Datapoint myDtp : datapoints) {
				System.out.println(String.format("%10d %20s %9.2f %12.2f %15.2f \n", myDtp.getId(),
						myDtp.getDate().format(formatter), myDtp.getTemperature(), myDtp.getPressure(),
						myDtp.getRevolutions()));
			}
			System.out.println("---------------------------------------------------------------------------");
		} finally {
			/* CLOSE database connection */
			factory.close();
		}
	}

	/* SAVE object in database/table */
	public static void save(Datapoint datapoint) {
		Session session = factory.openSession();
		session.beginTransaction();
		session.save(datapoint);
		session.getTransaction().commit();
		session.close();
	}

	/* DELETE object from database/table by id */
	public static void delete(int id) {
		Session session = factory.openSession();
		session.beginTransaction();
		Datapoint dtp = new Datapoint();
		dtp.setId(id);
		session.delete(dtp);
		session.getTransaction().commit();
		session.close();
	}

	/* GET/READ values from database/table */
	public static List<Datapoint> getData() {
		List<Datapoint> datapoints; // created list named employees
		Session session = factory.openSession(); // open Session to create SQL argument
		datapoints = session.createQuery("FROM Datapoint").list();
		session.close();
		return datapoints;
	}

}
