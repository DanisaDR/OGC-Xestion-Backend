package org.redeoza.xestion.controller;

import java.util.HashMap;
import java.util.Map;

import org.redeoza.xestion.service.ICotaService;
import org.redeoza.xestion.service.ISocioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
public class InicioController {

	@Autowired
	private ICotaService cotaSrv;

	@Autowired
	private ISocioService socSrv;

	@GetMapping("/")
	public ResponseEntity<Map<String, Object>> inicio() {
		final Map<String, Object> response = new HashMap<>();

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}

	@Secured({ "ROLE_ADMIN", "ROLE_DIRECTIVA" })
	@PreAuthorize("hasPermission('hasAccess', 'TODOS_PERMISOS')")
	@GetMapping("cotas-totais/{cotaAnual}")
	public ResponseEntity<Map<String, Object>> sumTotalCotas(@PathVariable int cotaAnual) {
		final Map<String, Object> response = new HashMap<>();

		response.put("total", cotaSrv.sumCotasByYear(cotaAnual));

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}

	@Secured({ "ROLE_ADMIN", "ROLE_DIRECTIVA" })
	@PreAuthorize("hasPermission('hasAccess', 'TODOS_PERMISOS')")
	@GetMapping("socios-activos-inactivos/{socAct}")
	public ResponseEntity<Map<String, Object>> socActs(@PathVariable boolean socAct) {
		final Map<String, Object> response = new HashMap<>();

		response.put("soc-acts", socSrv.socActNoActs(socAct));

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
}
