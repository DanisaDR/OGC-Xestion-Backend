package org.redeoza.xestion.service.geoapi.imp;

import com.google.common.collect.Sets;
import org.redeoza.xestion.dao.geoapi.IPoboacionDao;
import org.redeoza.xestion.model.geoapi.Poboacion;
import org.redeoza.xestion.service.geoapi.IMunicipioService;
import org.redeoza.xestion.service.geoapi.IPoboacionService;
import org.redeoza.xestion.utils.GeoAPIEntities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class PoboacionServiceImp implements IPoboacionService {

    private final Logger log = LoggerFactory.getLogger(PoboacionServiceImp.class);

    @Autowired
    IPoboacionDao pobDao;

    @Autowired
    IMunicipioService munSrv;

    @Override
    public Set<Poboacion> getAllPobs() {
        return new HashSet<>(pobDao.findAll());
    }

    @Override
    public void savePob(Poboacion poboacion) {
        pobDao.save(poboacion);
    }

    @Override
    public Set<Poboacion> findListPobByMun(String cmum) {
        return munSrv.findByMun(cmum).getPoboacions();
    }

    @Override
    public void checkPobsWithGeoAPI(Set<GeoAPIEntities> lstGeoAPI) {
        Set<Poboacion> setGeoApiPob = new HashSet<>();

        for(GeoAPIEntities geoAPI : lstGeoAPI) {
            Poboacion pobBBDD = new Poboacion();
            pobBBDD.setCun(geoAPI.getCun());
            pobBBDD.setNentsi50(geoAPI.getNentsi50());
            pobBBDD.setMunicipio(munSrv.findByMun(geoAPI.getCmum()));
            setGeoApiPob.add(pobBBDD);
        }

        Optional<GeoAPIEntities> opt = lstGeoAPI.stream().findFirst();

        if(opt.isPresent()) {
            Set<Poboacion> getPobsByMun = this.findListPobByMun(opt.get().getCmum());

            if (setGeoApiPob.size() > getPobsByMun.size()) {
                Sets.SetView<Poboacion> dev = Sets.difference(setGeoApiPob, getPobsByMun);
                for(Poboacion rst : dev) {
                    this.savePob(rst);
                    log.info("Faise a insercción do seguinte Pob: {}", rst);
                }
            }
        }
    }
}
