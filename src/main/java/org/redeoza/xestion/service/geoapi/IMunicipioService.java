package org.redeoza.xestion.service.geoapi;

import org.redeoza.xestion.model.geoapi.Municipio;
import org.redeoza.xestion.utils.GeoAPIEntities;

import java.util.Set;

public interface IMunicipioService {

    Municipio findByMun(String cmun);

    Set<Municipio> getAllMunicipios();

    void saveMun(Municipio municipio);

    void checkMunsWithGeoAPI(Set<GeoAPIEntities> lstGeoAPI);
}
