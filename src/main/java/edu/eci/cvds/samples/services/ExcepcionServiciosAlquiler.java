package edu.eci.cvds.samples.services;

public class ExcepcionServiciosAlquiler extends Exception {
	
	public ExcepcionServiciosAlquiler() {
		
	}
	
	public ExcepcionServiciosAlquiler(String msg) {
		super(msg);
	}
	
	public ExcepcionServiciosAlquiler(String msg,Exception e) {
		super(msg);
	}

}
