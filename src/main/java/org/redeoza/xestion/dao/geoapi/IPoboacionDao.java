package org.redeoza.xestion.dao.geoapi;

import org.redeoza.xestion.model.geoapi.Poboacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPoboacionDao extends JpaRepository<Poboacion, Integer> {

}
