package org.redeoza.xestion.service.geoapi.imp;

import com.google.common.collect.Sets;
import org.redeoza.xestion.dao.geoapi.IPoboacionDao;
import org.redeoza.xestion.model.geoapi.Municipio;
import org.redeoza.xestion.model.geoapi.Poboacion;
import org.redeoza.xestion.service.geoapi.IPoboacionService;
import org.redeoza.xestion.utils.GeoAPIEntities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class PoboacionServiceImp implements IPoboacionService {

    private final Logger log = LoggerFactory.getLogger(PoboacionServiceImp.class);

    @Autowired
    IPoboacionDao pobDao;

    @Override
    public Set<Poboacion> getAllPobs() {
        return new HashSet<>(pobDao.findAll());
    }

    @Override
    public void savePob(Poboacion poboacion) {
        pobDao.save(poboacion);
    }

    @Override
    public void checkPobsWithGeoAPI(Set<GeoAPIEntities> lstGeoAPI) {
        Set<Poboacion> setGeoApiPob = new HashSet<>();

        for(GeoAPIEntities geoAPI : lstGeoAPI) {
            Poboacion pobBBDD = new Poboacion();
            pobBBDD.setCun(geoAPI.getCun());
            pobBBDD.setNentsi50(geoAPI.getNentsi50());
            setGeoApiPob.add(pobBBDD);
        }

        if (setGeoApiPob.size() > getAllPobs().size()) {
            Sets.SetView<Poboacion> dev = Sets.difference(setGeoApiPob, this.getAllPobs());
            for(Poboacion rst : dev) {
                this.savePob(rst);
                log.info("Faise a insercción do seguinte Pob: {}", rst);
            }
        }
    }
}
