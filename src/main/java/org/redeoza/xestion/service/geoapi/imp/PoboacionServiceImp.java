package org.redeoza.xestion.service.geoapi.imp;

import com.google.common.collect.Sets;
import org.redeoza.xestion.dao.geoapi.IPoboacionDao;
import org.redeoza.xestion.model.geoapi.Municipio;
import org.redeoza.xestion.model.geoapi.Poboacion;
import org.redeoza.xestion.model.geoapi.PoboacionPK;
import org.redeoza.xestion.service.geoapi.IMunicipioService;
import org.redeoza.xestion.service.geoapi.IPoboacionService;
import org.redeoza.xestion.utils.GeoAPIEntities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class PoboacionServiceImp implements IPoboacionService {

    private final Logger log = LoggerFactory.getLogger(PoboacionServiceImp.class);

    @Autowired
    IPoboacionDao pobDao;

    @Autowired
    IMunicipioService munSrv;

    @Override
    public Set<Poboacion> getAllPobs() {
        return new HashSet<>(pobDao.findAll(Sort.by(Sort.Direction.DESC, "nentsi50")));
    }

    @Override
    public Poboacion findPobByCmumAndCun(String cmum, String cun) {
        return pobDao.findPobByCmumAndCun(cmum, cun);
    }

    @Override
    public void savePob(Set<Poboacion> pobs) {
        pobDao.saveAll(pobs);
    }

    @Override
    public void checkPobsWithGeoAPI(Set<GeoAPIEntities> lstGeoAPI) {

        Set<Poboacion> setGeoApiPob = new HashSet<>();
        AtomicReference<Municipio> munDDBB = new AtomicReference<>(new Municipio());

        for(GeoAPIEntities geoAPI : lstGeoAPI) {
            PoboacionPK pk = new PoboacionPK();
            pk.setCmum(geoAPI.getCmum());
            pk.setCun(geoAPI.getCun());
            Poboacion pobBBDD = new Poboacion();
            pobBBDD.setPoboacionPK(pk);
            pobBBDD.setNentsi50(geoAPI.getNentsi50());
            pobBBDD.setMunicipio(munSrv.findByMun(geoAPI.getCmum()));
            setGeoApiPob.add(pobBBDD);
        }

        Optional<GeoAPIEntities> opt = lstGeoAPI.stream().findFirst();

        opt.ifPresent(geoAPIEntities -> munDDBB.set(munSrv.findByMun(geoAPIEntities.getCmum())));

        if (lstGeoAPI.size() > munDDBB.get().getPoboacions().size()) {
            Sets.SetView<Poboacion> dev = Sets.difference(setGeoApiPob, munDDBB.get().getPoboacions());
            for (Poboacion rst : dev) {
                rst.isNew();
                pobDao.save(rst);
                log.debug("Insertamos a POB: {} en el Municipio: {} ", rst.getNentsi50(), rst.getMunicipio().getDmun50());
            }
        }
    }
}
