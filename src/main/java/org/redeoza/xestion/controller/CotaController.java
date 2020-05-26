package org.redeoza.xestion.controller;

import org.redeoza.xestion.exception.GenericException;
import org.redeoza.xestion.exception.MissingFieldException;
import org.redeoza.xestion.exception.NotFoundException;
import org.redeoza.xestion.model.Cota;
import org.redeoza.xestion.service.ICotaService;
import org.redeoza.xestion.utils.UtilConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("cotas")
public class CotaController {

    @Autowired
    ICotaService cotaSrv;

    @Secured({ "ROLE_ADMIN", "ROLE_DIRECTIVA" })
    @PreAuthorize("hasPermission('hasAccess', 'TODOS_PERMISOS')")
    @GetMapping(value = "ver/{socID}")
    public ResponseEntity<Set<Cota>> showSoc(@PathVariable int socID) {
        Set<Cota> showCotasBySoc;

        try {
            showCotasBySoc = cotaSrv.allCotasBySoc(socID);

            if (showCotasBySoc == null) {
                throw new NotFoundException(UtilConstant.NOT_FOUND_SOCIO);
            }

            return new ResponseEntity<Set<Cota>>(showCotasBySoc, HttpStatus.OK);
        } catch (final Exception ex) {
            throw new GenericException(ex.getMessage());
        }
    }

    @Secured({ "ROLE_ADMIN", "ROLE_DIRECTIVA" })
    @PreAuthorize("hasPermission('hasAccess', 'TODOS_PERMISOS')")
    @PutMapping(value = "modificar/{cotaID}")
    public ResponseEntity<Map<String, Object>> updateCotaBySoc(@Valid @RequestBody Set<Cota> cotas, BindingResult result,
                                                          @PathVariable int socID) {
        final Map<String, Object> response = new HashMap<>();

        if (result.hasErrors()) {
            final List<String> errors = result.getFieldErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.toList());

            throw new MissingFieldException(errors.toString());
        }

        try {
            Set<Cota> showCotasBySoc = cotaSrv.allCotasBySoc(socID);

            if (showCotasBySoc == null) {
                throw new NotFoundException(UtilConstant.NOT_FOUND_SOCIO);
            }

            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
        } catch (final Exception ex) {
            throw new GenericException(ex.getMessage());
        }
    }
}
