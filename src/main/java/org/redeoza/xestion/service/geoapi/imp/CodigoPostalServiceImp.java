package org.redeoza.xestion.service.geoapi.imp;

import com.google.common.collect.Sets;
import org.redeoza.xestion.dao.geoapi.ICodigoPostalDao;
import org.redeoza.xestion.model.geoapi.CodigoPostal;
import org.redeoza.xestion.model.geoapi.CodigoPostalPK;
import org.redeoza.xestion.model.geoapi.Poboacion;
import org.redeoza.xestion.service.geoapi.ICodigoPostalService;
import org.redeoza.xestion.service.geoapi.IPoboacionService;
import org.redeoza.xestion.utils.GeoAPIEntities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class CodigoPostalServiceImp implements ICodigoPostalService {

    private final Logger log = LoggerFactory.getLogger(CodigoPostalServiceImp.class);

    @Autowired
    ICodigoPostalDao cpDao;

    @Autowired
    IPoboacionService pobSrv;

    @Override
    public Set<CodigoPostal> getAllCP() {
        return new HashSet<>(cpDao.findAll(Sort.by(Sort.Direction.DESC, "cun")));
    }

    @Override
    public Set<CodigoPostal> getCPSByPob(String cmum, String cun) {
        return cpDao.findCPsByCmumAndCun(cmum, cun);
    }

    @Override
    public void saveCP(CodigoPostal codigoPostal) {
        cpDao.save(codigoPostal);
    }

    @Override
    public void checkCPsWithGeoAPI(Set<GeoAPIEntities> lstGeoAPI) {
        Set<CodigoPostal> setGeoApiPob = new HashSet<>();
        AtomicReference<Poboacion> pobDDBB = new AtomicReference<>(new Poboacion());

        for(GeoAPIEntities geoAPI : lstGeoAPI) {
            CodigoPostal cpBBDD = new CodigoPostal();
            CodigoPostalPK pk = new CodigoPostalPK();
            pk.setCmum(geoAPI.getCmum());
            pk.setCun(geoAPI.getCun());
            pk.setCpos(geoAPI.getCpos());
            cpBBDD.setCodigoPostalPK(pk);
            cpBBDD.setPoboacion(pobSrv.findPobByCmumAndCun(geoAPI.getCmum(), geoAPI.getCun()));
            setGeoApiPob.add(cpBBDD);
        }

        Optional<GeoAPIEntities> opt = lstGeoAPI.stream().findFirst();

        opt.ifPresent(geoAPIEntities -> pobDDBB.set(pobSrv.findPobByCmumAndCun(geoAPIEntities.getCmum(), geoAPIEntities.getCun())));

        if (lstGeoAPI.size() > pobDDBB.get().getCodigoPostals().size()) {
            Sets.SetView<CodigoPostal> dev = Sets.difference(setGeoApiPob, pobDDBB.get().getCodigoPostals());
            for (CodigoPostal rst : dev) {
                rst.isNew();
                saveCP(rst);
                log.debug("Insertamos a CP: {} en la POB: {} ", rst.getPoboacion().getNentsi50(), rst.getPoboacion().getMunicipio().getDmun50());
            }
        }
    }
}
