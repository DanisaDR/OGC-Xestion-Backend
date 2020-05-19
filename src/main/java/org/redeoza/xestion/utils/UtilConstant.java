package org.redeoza.xestion.utils;

import javafx.beans.binding.Bindings;
import org.springframework.web.util.UriBuilder;

import java.net.URI;
import java.util.function.Function;

public class UtilConstant {

	// Mensaxes LOGGER de excepcións.
	public static final String ERROR = "errors";
	public static final String GENERIC_ERROR = "Neste intre temos algún tipo de problema. Avise os/as administradores/as para que o revisen. ";
	public static final String REALTIME_HAPPEN = "Rexistrase o erró na DATA e HORA: ";
	public static final String MESSAGE = "message";
	public static final String MESSAGE_LOGGER = "Mensaxe do erró: {}.";
	public static final String MESSAGE_CAUSE_LOGGER = " Foi causado por mor de: {}.";

	// Parametros
	public static final String HAS_ACCESS = "hasAccess";
	public static final int POOL_SIZE = 10;
	public static final String POOL_TASK = "POOL-TASK";

	// Mensaxes de accións xerais:
	public static final String CREATED = " queda creado e rexistrado na BBDD";

	// Mensaxes USUARI@
	public static final String NEW_USER = "O/A novo/a usuario/a co Alias: ";
	public static final String UPDATED_USER = "O/A usuario/a queda actualizado e o alias xenerado para os posteriores LOGINs: ";
	public static final String NOT_DELETE_MYSELF = "Non te borrar da aplicación a ti mesmo. Acción reixatada.";
	public static final String NOT_DELETE_NECESARY_USERS = "Este usuario pertence os ADMIN_TOTAL. Cancelase o borrado.";
	public static final String DELETE_USER = "O/A usuario/a queda borrado físicamente do aplicativo. Non se pode volver a usar.";
	public static final String NOT_FOUND_USER = "O/A usuario/a que intentas atopar non o temos rexistrado. Revise o ID.";
	public static final String ONE_PHONE_FILL = "Polo menos temos ter rexistrado algún teléfono.";
	public static final String NOT_ROLE_DETECT = "Non hai ningún rol seleccionado. O/A usuario/a debe ter un a lo menos.";

	// Mensaxes SOCI@
	public static final String NEW_SOCIO = "O/A novo/a socio/a co ID: OGC - ";
	public static final String UPDATED_SOCIO = "O/A socio/a queda actualizado co ID: OGC - ";
	public static final String COMPLETE_NAME_SOCIO = " co nome completo: ";
	public static final String NOT_FOUND_SOCIO = "O/A usuario/a que intentas atopar non o temos rexistrado. Revise o ID sen poñer por exemplo: [OGC-10]";
	public static final String ALMOST_ONE_PHONE = "Temos que ter un teléfono rexistrado.";
	public static final String LEAVING = " dado/a de baixa";
	public static final String NOT_NOME_COMPLETO = "O nome completo do/a socio/a ven baleiro. Ten cubrirse.";
	public static final String FAIL_LEAVING_IS_BEFORE = "A data de baixa é menor que a de alta e iso non pode ocurrir.";

	// Mensaxes ROLES
	public static final String NOT_FOUND_ROL = "O rol que intentas atopar non esta rexistrado.";
	public static final String UPDATED_ROL_USERS = "Actualización dos/as usuarios/as e/ou ";
	public static final String UPDATED_ROL_PERMISSIONS = "actualización dos permisos para o Rol: ";

	// Mensaxes ACTIVIDADES
	public static final String NEW_ACTIVIDADE = "A nova actividade queda creada co nome: ";
	public static final String NOT_FOUND_ACTIVIDADE = "A actividade tentas atopar non está rexistrada.";
	public static final String UPDATED_ACTIVIDADE = "A nova actividade queda actualizada co nome: ";
	public static final String DELETE_ACTIVIDADE = "A actividade queda borrada físicamente do aplicativo. Non se pode volver a usar.";
	public static final String NOT_FOUND_MONITOR = "Non ven cuberto o/a monitora/a";

	// Implementación GeoAPI
	public static final String URL_BASE_GEOAPI = "https://apiv1.geoapi.es/";
	public static final String URL_KEY_API_JSON_GEOAPI = "&type=JSON&key=3732a0b22150bde3e59459a07995d034f2559bef776d5c7dac812f9fe7ff3af0&sandbox=0";
	public static final String URL_PROVINCIA_CORUNHA_GEOAPI = "municipios?CPRO=15";
	public static final String URL_ALL_MUNICIPIO_CORUNHA_GEOAPI = "poblaciones?CPRO=15&CMUM=";

	// Varias
	public static final String COMA = ", ";

	// CLAVES RSA DE ACCESO DE ENDPOINTS
	public static final String RSA_PUBLIC = "AAAAB3NzaC1yc2EAAAADAQABAAACAQDhPbkGRagJhhE9RTxDkj1+piIQWJ9phmLQ/"
			+ "RiA10HDSq4s714cZvBtT0NpznifURQKAtiCAkR+c1EdpHSpMrS3mhrYwZpKCG4ZlNhje+KfKVH0OTA/x1fM0aF4ZVEXsHhST1"
			+ "wyy3MNZylF1sWM+fV9CCrqqsttLhZ/Blzp4qYCfsBvSd+bozSHb7fagXsyr2dIOlKMc4RpHOKTAwQ2vHo5JWsrc1oMhVaELzr"
			+ "NRGaK1iNWPVNti2KPqIInXD7ZqMvhycOd5QMNVNNa4AmTETejL/Za5s/KA0MfnrQKuG99kQb/xv57d2Co5dImHgJNI0gB1AsDIr/"
			+ "jXA4w835bXIIdz3RXGv60YAGOBi/BZtN7RpZ1HDcVAng2lgk9bHzz0bUZKCqTafMYSZqqmPFe/FdySaTmO1DKtuV+gVzcLtk2IdlY"
			+ "/ttGP8J9dK1dwzqJGPgoNoS+mSnHuhz0e/Vi1ukHxGsEilJX5Wrb8FlUhRGr9oUZ8ssLq0JoTLdZp7M9aiuop2UuODJjeGbmN"
			+ "EbM80GUkDHn+CZdDTEu7Nv1hmUksdn97ZrZEsPqgvOJyOYIf8ICn0Jie3QitPjXo4se6n5TxccmLN+TfcecPQqeV51T5KHlMgP"
			+ "zCuXF4ho5DsR3TtpEqx1w+NA2fv5OkBvR4IJMjQAj3cvpLLYttkyrFeK1HQ== ";

	public static final String RSA_PRIVATE = "-----BEGIN RSA PRIVATE KEY-----\r\n"
			+ "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\r\n" +
			"-----END RSA PRIVATE KEY-----\r\n";
}
