package org.redeoza.xestion.dao.geoapi;

import org.redeoza.xestion.model.geoapi.Municipio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IMunicipioDao extends JpaRepository<Municipio, Integer> {
}
