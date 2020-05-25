package org.redeoza.xestion.dao.geoapi;

import org.redeoza.xestion.model.geoapi.Municipio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IMunicipioDao extends JpaRepository<Municipio, String> {

    @Query("select m from Municipio m where m.cmum = :cmum")
    Municipio findByStringIdMun(@Param("cmum") String cmum);
}
