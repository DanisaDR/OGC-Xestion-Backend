package org.redeoza.xestion.model.geoapi;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.domain.Persistable;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "codigo_postal")
public class CodigoPostal implements Serializable, Persistable<CodigoPostalPK> {

    private static final long serialVersionUID = 1L;

    @EmbeddedId
    CodigoPostalPK codigoPostalPK;

    @Transient
    private boolean isNew;

    public CodigoPostal() {

    }

    public CodigoPostal(CodigoPostalPK codigoPostalPK, boolean isNew) {
        this.codigoPostalPK = codigoPostalPK;
        this.isNew = isNew;
    }

    @MapsId("poboacion")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
        @JoinColumn(name = "cmum", insertable=false, updatable=false),
        @JoinColumn(name = "cun", insertable=false, updatable=false)
    })
    @JsonIgnoreProperties(value = { "codigoPostals", "hibernateLazyInitializer", "handler" }, allowSetters = true)
    private Poboacion poboacion;

    @Version
    @Column(name = "version")
    private long version;

    public Poboacion getPoboacion() {
        return poboacion;
    }

    public void setPoboacion(Poboacion poboacion) {
        this.poboacion = poboacion;
    }

    @Override
    public CodigoPostalPK getId() {
        return codigoPostalPK;
    }

    @Override
    public boolean isNew() {
        return true;
    }

    public CodigoPostalPK getCodigoPostalPK() {
        return codigoPostalPK;
    }

    public void setCodigoPostalPK(CodigoPostalPK codigoPostalPK) {
        this.codigoPostalPK = codigoPostalPK;
    }
}
