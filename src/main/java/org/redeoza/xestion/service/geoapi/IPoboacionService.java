package org.redeoza.xestion.service.geoapi;

import org.redeoza.xestion.model.geoapi.Poboacion;
import org.redeoza.xestion.utils.GeoAPIEntities;

import java.util.Set;

public interface IPoboacionService {

    Set<Poboacion> getAllPobs();

    Poboacion findPobByCmumAndCun(String cmum, String cun);

    void savePob(Set<Poboacion> poboacions);
    
    void checkPobsWithGeoAPI(Set<GeoAPIEntities> lstGeoAPI);
}
