package org.redeoza.xestion.model.geoapi;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "poboacion")
@IdClass(value = PoboacionPK.class)
public class Poboacion implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "cun")
    private String cun;

    @Id
    @Column(name = "cmum")
    private String cmum;

    @Column(name = "nentsi50")
    private String nentsi50;

    @MapsId
    @ManyToOne
    @JoinColumn(name = "cmum")
    @JsonIgnoreProperties(value = { "poboacions", "hibernateLazyInitializer", "handler" }, allowSetters = true)
    private Municipio municipio;

    @OneToMany(mappedBy = "codigo_postal", fetch = FetchType.EAGER)
    @JsonIgnoreProperties(value = { "poboacion", "hibernateLazyInitializer", "handler" }, allowSetters = true)
    private Set<CodigoPostal> codigoPostals;

    @Version
    @Column(name = "version")
    private long version;

    public String getCmum() {
        return cmum;
    }

    public void setCmum(String cmum) {
        this.cmum = cmum;
    }

    public String getCun() {
        return cun;
    }

    public void setCun(String cun) {
        this.cun = cun;
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
}
