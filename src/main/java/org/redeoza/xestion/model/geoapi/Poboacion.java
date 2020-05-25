package org.redeoza.xestion.model.geoapi;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.domain.Persistable;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "poboacion")
public class Poboacion implements Serializable, Persistable<PoboacionPK> {

    private static final long serialVersionUID = 1L;

    @EmbeddedId
    PoboacionPK poboacionPK;

    @Column(name = "nentsi50")
    private String nentsi50;

    @Transient
    private boolean isNew = true;

    @MapsId("municipio")
    @ManyToOne
    @JoinColumn(name = "cmum", insertable=false, updatable=false)
    @JsonIgnoreProperties(value = { "poboacions", "hibernateLazyInitializer", "handler" }, allowSetters = true)
    private Municipio municipio;

    @OneToMany(mappedBy = "poboacion", fetch = FetchType.EAGER)
    @JsonIgnoreProperties(value = { "poboacion", "hibernateLazyInitializer", "handler" }, allowSetters = true)
    private Set<CodigoPostal> codigoPostals;

    @Version
    @Column(name = "version")
    private long version;

    public Poboacion(PoboacionPK poboacionPK, boolean isNew) {
        this.poboacionPK = poboacionPK;
        this.isNew = isNew;
    }

    public Poboacion() {

    }

    public PoboacionPK getPoboacionPK() {
        return poboacionPK;
    }

    public void setPoboacionPK(PoboacionPK poboacionPK) {
        this.poboacionPK = poboacionPK;
    }

    public String getNentsi50() {
        return nentsi50;
    }

    public void setNentsi50(String nentsi50) {
        this.nentsi50 = nentsi50;
    }

    public Municipio getMunicipio() {
        return municipio;
    }

    public void setMunicipio(Municipio municipio) {
        this.municipio = municipio;
    }

    public Set<CodigoPostal> getCodigoPostals() {
        return codigoPostals;
    }

    public void setCodigoPostals(Set<CodigoPostal> codigoPostals) {
        this.codigoPostals = codigoPostals;
    }

    @Override
    public PoboacionPK getId() {
        return poboacionPK;
    }

    @Override
    public boolean isNew() {
        return true;
    }
}
