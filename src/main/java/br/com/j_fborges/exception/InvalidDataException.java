package br.com.j_fborges.exception;

public class InvalidDataException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6320736614374876296L;

	public InvalidDataException(String message) {
        super(message);
    }
}
