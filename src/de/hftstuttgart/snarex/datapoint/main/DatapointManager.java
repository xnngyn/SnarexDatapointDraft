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
		try {
		
			getConnection();
		
		/* CREATE, DELETE and READ values */
		
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

			/* CREATE new object and SAVE the object */
			/*
			 * Datapoint dtp= new Datapoint(LocalDateTime.now(),30.2,2.1,8000.1); 
			 * save(dtp);
			 */
			
			// deleteByID(1101);
			
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
			
		
		
		}finally {
			/* CLOSE database connection */
			factory.close();
		}
	}

	/* try CONNECT to database */
	public static boolean getConnection() {
		boolean res = false;
		try {
			factory = new Configuration().configure().buildSessionFactory();
			System.out.println("sessionFactory object created successfully. Connection to database is up.");
			return res = true;
		} catch (Exception e) {
			System.err.println("Failed to create sessionFactory object." + e);
			return res;
		}
	}
	
	/* SAVE object in database/table */
	public static boolean save(Datapoint datapoint) {
		boolean res = false;
		try{
		Session session = factory.openSession();
		session.beginTransaction();
		session.save(datapoint);
		session.getTransaction().commit();
		session.close();
		return res = true;
		}catch (Exception e) {
			e.printStackTrace();
			return res;
		}
	}

	/* DELETE object by id from database/table by id */
	public static boolean deleteByID(int id) {
		boolean res = false;
		try {
		Session session = factory.openSession();
		session.beginTransaction();
		Datapoint dtp = new Datapoint();
		dtp.setId(id);
		session.delete(dtp);
		session.getTransaction().commit();
		session.close();
		return res = true;
		}catch (Exception e) {
			e.printStackTrace();
			return res;
		}
	}
	
	/* DELETE object by object from database/table by id */
	public static boolean deleteByObject(Datapoint name) {
		boolean res = false;
		try {
		Session session = factory.openSession();
		session.beginTransaction();
		session.delete(name);
		session.getTransaction().commit();
		session.close();
		return res = true;
		}catch (Exception e) {
			e.printStackTrace();
			return res;
		}
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
