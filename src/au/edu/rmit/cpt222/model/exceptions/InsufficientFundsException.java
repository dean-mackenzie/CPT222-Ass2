package au.edu.rmit.cpt222.model.exceptions;

import java.io.Serializable;

/**
 * Custom Exception class to indicate that the player has insufficient funds
 * 
 * @author Mikhail Perepletchikov
 * 
 */
@SuppressWarnings("serial")
public class InsufficientFundsException extends Exception implements Serializable {
	/**
	 * default constructor
	 */
	public InsufficientFundsException() {
		super("Insufficient Funds Exception");
	}

	/**
	 * @param message
	 *            exception message
	 */
	public InsufficientFundsException(String message) {
		super(message);
	}
}
