package org.redeoza.xestion.service.geoapi.imp;

import com.google.common.collect.Sets;
import org.redeoza.xestion.dao.geoapi.IMunicipioDao;
import org.redeoza.xestion.dao.geoapi.IPoboacionDao;
import org.redeoza.xestion.exception.GenericException;
import org.redeoza.xestion.model.geoapi.Municipio;
import org.redeoza.xestion.model.geoapi.Poboacion;
import org.redeoza.xestion.service.geoapi.IMunicipioService;
import org.redeoza.xestion.utils.GeoAPIEntities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Service
public class MunicipioServiceImp implements IMunicipioService {

    private final Logger log = LoggerFactory.getLogger(MunicipioServiceImp.class);

    @Autowired
    IMunicipioDao munDao;

    @Override
    public Set<Municipio> getAllMunicipios() {
        return new HashSet<>(munDao.findAll(Sort.by(Sort.Direction.DESC, "dmun50")));
    }

    @Override
    public void saveMun(Municipio municipio) {
        munDao.save(municipio);
    }

    @Override
    public Municipio findByMun(String cmum) {
        return munDao.findByStringIdMun(cmum);
    }

    @Override
    public void checkMunsWithGeoAPI(Set<GeoAPIEntities> lstGeoAPI) {

        Set<Municipio> setGeoApiMun = new HashSet<>();

        for(GeoAPIEntities geoAPI : lstGeoAPI) {
            Municipio munBBDD = new Municipio();
            munBBDD.setCmum(geoAPI.getCmum());
            munBBDD.setDmun50(geoAPI.getDmun50());
            setGeoApiMun.add(munBBDD);
        }

        try {
            if (setGeoApiMun.size() > getAllMunicipios().size()) {
                Sets.SetView<Municipio> dev = Sets.difference(setGeoApiMun, getAllMunicipios());
                for(Municipio rst : dev) {
                    this.saveMun(rst);
                    log.info("Faise a insercción do seguinte Mun {}", rst);
                }
            }
        } catch(Exception ex) {
            throw new GenericException(ex.getMessage());
        }
    }
}
