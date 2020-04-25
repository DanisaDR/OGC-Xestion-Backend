package org.redeoza.xestion.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * <b>MissingFieldException.java<b>
 * 
 * @author Daniel Isasi
 * @since 13 ene. 2020
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class MissingFieldException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public MissingFieldException(String exception) {
		super(exception);
	}
}
