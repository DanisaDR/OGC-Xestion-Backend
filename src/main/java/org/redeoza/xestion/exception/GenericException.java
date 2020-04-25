package org.redeoza.xestion.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * <b>GenericException.java<b>
 * 
 * @author Daniel Isasi
 * @since 13 ene. 2020
 */
@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class GenericException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public GenericException(String exception) {
		super(exception);
	}
}
