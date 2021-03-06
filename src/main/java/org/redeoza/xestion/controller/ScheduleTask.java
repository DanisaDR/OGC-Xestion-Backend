package org.redeoza.xestion.controller;

import org.redeoza.xestion.utils.GeoAPI;
import org.redeoza.xestion.utils.GeoAPIEntities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class ScheduleTask {

    private final Logger log = LoggerFactory.getLogger(ScheduleTask.class);

    private static final DateTimeFormatter realTime = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");


    @Scheduled(cron = "0 0 0 1 1/1 ?")
    void scheduleTaskGeoAPI() {
        log.info("Cron Task :: Execution Time - {}", realTime.format(LocalDateTime.now()));
        WebClient clientGeoAPI = WebClient.create("https://apiv1.geoapi.es/");
        Mono<GeoAPI> response = clientGeoAPI.get().uri("municipios?CPRO=15&type=JSON&key=3732a0b22150bde3e59459a07995d034f2559bef776d5c7dac812f9fe7ff3af0&sandbox=0").retrieve().bodyToMono(GeoAPI.class);
        response.map(GeoAPI::getEntitiesList);
        List<GeoAPIEntities> lst = new ArrayList<>(Objects.requireNonNull(response.map(GeoAPI::getEntitiesList).block()));
        lst.forEach(c -> log.info(c.getCmum()));
    }
}
