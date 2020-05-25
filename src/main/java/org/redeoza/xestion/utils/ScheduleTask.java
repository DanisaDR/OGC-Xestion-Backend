package org.redeoza.xestion.utils;

import org.redeoza.xestion.model.geoapi.Municipio;
import org.redeoza.xestion.model.geoapi.Poboacion;
import org.redeoza.xestion.service.geoapi.ICodigoPostalService;
import org.redeoza.xestion.service.geoapi.IMunicipioService;
import org.redeoza.xestion.service.geoapi.IPoboacionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static org.apache.maven.shared.utils.StringUtils.concatenate;

@Component
public class ScheduleTask {

    private final Logger log = LoggerFactory.getLogger(ScheduleTask.class);

    private static final DateTimeFormatter realTime = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

    @Autowired
    IMunicipioService munSrv;

    @Autowired
    IPoboacionService pobSrv;

    @Autowired
    ICodigoPostalService cpSrv;

    @Autowired
    JdbcTemplate template;

    @Scheduled(cron = "0 * * * * ?")
    void scheduleMunGeoAPI() {

        if(log.isInfoEnabled()) {
            log.info("Tarefa GeoApi Municipio :: Tempo Comezo: {}", realTime.format(LocalDateTime.now()));
        }

        WebClient clientGeoAPI = WebClient.create(UtilConstant.URL_BASE_GEOAPI);

        Mono<GeoAPI> response = clientGeoAPI.get().uri(
                UtilConstant.URL_PROVINCIA_CORUNHA_GEOAPI +
                   UtilConstant.URL_KEY_API_JSON_GEOAPI).retrieve().bodyToMono(GeoAPI.class);

        Set<GeoAPIEntities> lstGeoAPI = new HashSet<>(Objects.requireNonNull(response.map(GeoAPI::getEntitiesList).block()));

        munSrv.checkMunsWithGeoAPI(lstGeoAPI);

        if(log.isInfoEnabled()) {
            log.info("Tarefa GeoApi Municipio :: Tempo Remate: {}", realTime.format(LocalDateTime.now()));
        }
    }

    // 0 0 0 1 1/1 *
    @Scheduled(cron = "1 * * * * ?")
    void schedulePobGeoAPI() {

        if(log.isInfoEnabled()) {
            log.info("Tarefa GeoApi Poboacións :: Tempo Comezo: {}", realTime.format(LocalDateTime.now()));
        }

        WebClient clientGeoAPI = WebClient.create(UtilConstant.URL_BASE_GEOAPI);

        for(Municipio mun : munSrv.getAllMunicipios()) {

            Mono<GeoAPI> response = clientGeoAPI.get().uri(
                    UtilConstant.URL_ALL_MUNICIPIO_CORUNHA_GEOAPI + mun.getCmum()
                       + UtilConstant.URL_KEY_API_JSON_GEOAPI
            ).retrieve().bodyToMono(GeoAPI.class);
            Set<GeoAPIEntities> lstGeoAPI = new HashSet<>(Objects.requireNonNull(response.map(GeoAPI::getEntitiesList).block()));

            pobSrv.checkPobsWithGeoAPI(lstGeoAPI);

            lstGeoAPI.clear();
        }

        if(log.isInfoEnabled()) {
            log.info("Tarefa GeoApi Poboacións :: Tempo Remate: {} ", realTime.format(LocalDateTime.now()));
        }
    }

    @Scheduled(cron = "0 * * * * ?")
    void scheduleCPGeoAPI() {

        if(log.isInfoEnabled()) {
            log.info("Tarefa GeoApi Códigos Postais :: Tempo Comezo: {}", realTime.format(LocalDateTime.now()));
        }

        WebClient clientGeoAPI = WebClient.create(UtilConstant.URL_BASE_GEOAPI);
        for(Poboacion pob : pobSrv.getAllPobs()) {

            Mono<GeoAPI> response = clientGeoAPI.get().uri(
                    UtilConstant.URL_MUNICIPIO_CORUNHA_GEOAPI + pob.getPoboacionPK().getCmum()
                            + UtilConstant.URL_POBOACION_MUN_GEOAPI + pob.getPoboacionPK().getCun()
                            + UtilConstant.URL_KEY_API_JSON_GEOAPI
            ).retrieve().bodyToMono(GeoAPI.class);
            Set<GeoAPIEntities> lstGeoAPI = new HashSet<>(Objects.requireNonNull(response.map(GeoAPI::getEntitiesList).block()));

            cpSrv.checkCPsWithGeoAPI(lstGeoAPI);
        }

        if(log.isInfoEnabled()) {
            log.info("Tarefa GeoApi Códigos Postais :: Tempo Remate: {}", realTime.format(LocalDateTime.now()));
        }
    }
}