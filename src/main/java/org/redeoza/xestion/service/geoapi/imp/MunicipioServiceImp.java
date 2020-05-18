package org.redeoza.xestion.service.geoapi.imp;

import com.google.common.collect.Sets;
import org.redeoza.xestion.dao.geoapi.IMunicipioDao;
import org.redeoza.xestion.model.geoapi.Municipio;
import org.redeoza.xestion.service.geoapi.IMunicipioService;
import org.redeoza.xestion.utils.GeoAPIEntities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class MunicipioServiceImp implements IMunicipioService {

    @Autowired
    IMunicipioDao munDao;

    @Override
    public Set<Municipio> getAllMunicipios() {
        return new HashSet<>(munDao.findAll());
    }

    @Override
    public void saveMun(Municipio municipio) {
        munDao.save(municipio);
    }

    @Override
    public void checkAllMunWithGeoAPI(Set<GeoAPIEntities> lstGeoAPI) {

        Set<Municipio> setGeoApiMun = new HashSet<>();

        for(GeoAPIEntities geoAPI : lstGeoAPI) {
            Municipio munBBDD = new Municipio();
            munBBDD.setCmum(geoAPI.getCmum());
            munBBDD.setCpro(geoAPI.getCpro());
            munBBDD.setCun(geoAPI.getCun());
            munBBDD.setDmun50(geoAPI.getDmun50());
            setGeoApiMun.add(munBBDD);
        }

        if (setGeoApiMun.size() > getAllMunicipios().size()) {
            Sets.SetView<Municipio> dev = Sets.difference(setGeoApiMun, getAllMunicipios());
            for(Municipio rst : dev) {
                this.saveMun(rst);
            }
        }
    }
}
