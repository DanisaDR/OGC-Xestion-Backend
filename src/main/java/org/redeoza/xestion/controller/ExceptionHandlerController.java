package org.redeoza.xestion.controller;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.ConnectException;
import java.sql.SQLNonTransientException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import org.hibernate.exception.JDBCConnectionException;
import org.redeoza.xestion.exception.GenericException;
import org.redeoza.xestion.exception.MissingFieldException;
import org.redeoza.xestion.exception.NotFoundException;
import org.redeoza.xestion.utils.UtilConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException.Unauthorized;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * <b>RestExceptionHandler.java<b>
 * 
 * @author Daniel Isasi
 * @since 12 ene. 2020
 */
@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice
public class ExceptionHandlerController extends ResponseEntityExceptionHandler {

	private final Logger log = LoggerFactory.getLogger(ExceptionHandlerController.class);

	private static final DateTimeFormatter realTime = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

	Map<String, Object> response = new HashMap<>();

	@ExceptionHandler({ AccessDeniedException.class })
	public ResponseEntity<Map<String, Object>> accessDenied(AccessDeniedException ex, WebRequest request) {
		response.put(UtilConstant.ERROR, ex.getLocalizedMessage());
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.FORBIDDEN);
	}

	@ExceptionHandler({ Unauthorized.class })
	public ResponseEntity<Map<String, Object>> unAuthorized(Unauthorized ex, WebRequest request) {
		response.put(UtilConstant.ERROR, ex.getLocalizedMessage());
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.UNAUTHORIZED);
	}

	@ExceptionHandler({ NotFoundException.class })
	public ResponseEntity<Map<String, Object>> notFound(NotFoundException ex, WebRequest request) {
		response.put(UtilConstant.ERROR, ex.getLocalizedMessage());
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler({ MissingFieldException.class })
	public ResponseEntity<Map<String, Object>> missingFields(MissingFieldException ex, WebRequest request) {
		response.put(UtilConstant.ERROR, ex.getLocalizedMessage());
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler({ GenericException.class, Exception.class, SQLNonTransientException.class, ConnectException.class,
			JDBCConnectionException.class })
	public ResponseEntity<Map<String, Object>> genericException(GenericException ex, WebRequest request) {
		response.put(UtilConstant.ERROR, UtilConstant.GENERIC_ERROR
				.concat(UtilConstant.REALTIME_HAPPEN.concat(realTime.format(LocalDateTime.now()))));

		final StringWriter errors = new StringWriter();
		ex.printStackTrace(new PrintWriter(errors));

		log.error(UtilConstant.MESSAGE_LOGGER, ex.getMessage(), UtilConstant.MESSAGE_CAUSE_LOGGER, ex.getCause(),
				errors.toString());

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
