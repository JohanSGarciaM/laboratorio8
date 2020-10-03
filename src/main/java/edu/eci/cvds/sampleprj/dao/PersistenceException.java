package edu.eci.cvds.sampleprj.dao;

public class PersistenceException extends Exception{
	
	public PersistenceException() {
		
	}
	
	public PersistenceException(String msg) {
		super(msg);
	}
	
	public PersistenceException(String msg,Exception e) {
		super(msg);
	}
	

}
