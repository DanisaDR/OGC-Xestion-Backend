package org.redeoza.xestion.service.geoapi;

import org.redeoza.xestion.model.geoapi.CodigoPostal;
import org.redeoza.xestion.utils.GeoAPIEntities;

import java.util.Set;

public interface ICodigoPostalService {

    Set<CodigoPostal> getAllCP();

    Set<CodigoPostal> getCPSByPob(String cmum, String cun);

    void saveCP(CodigoPostal codigoPostal);

    void checkCPsWithGeoAPI(Set<GeoAPIEntities> lstGeoAPI);
}
