package de.hftstuttgart.snarex.datapoint.source;

import java.time.LocalDateTime;

public class Datapoint {
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
