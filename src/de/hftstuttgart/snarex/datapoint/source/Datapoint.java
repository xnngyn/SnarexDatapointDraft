package de.hftstuttgart.snarex.datapoint.source;

import java.time.LocalDateTime;

public class Datapoint {
	private int id;
	private double temperature;
	private double pressure;
	private double revolutions;
	LocalDateTime date;

	public Datapoint() {
	};

	public Datapoint(LocalDateTime date, double temperature, double pressure, double revolutions) {
		this.date = LocalDateTime.now();
		this.temperature = temperature;
		this.pressure = pressure;
		this.revolutions = revolutions;
	}
	
	@Override
	public String toString() {

		return String.format("%10d %10s %10f %10f %10f \n", id, date, temperature, pressure, revolutions );
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	public double getPressure() {
		return pressure;
	}

	public void setPressure(double pressure) {
		this.pressure = pressure;
	}

	public double getRevolutions() {
		return revolutions;
	}

	public void setRevolutions(double revolutions) {
		this.revolutions = revolutions;
	}

	public double getTemperature() {

		return temperature;
	}

	public void setTemperature(double temperature) {
		this.temperature = temperature;
	}
}
