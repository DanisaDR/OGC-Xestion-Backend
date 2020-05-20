package org.redeoza.xestion.model.geoapi;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "poboacion")
public class Poboacion {

    @Id
    @Column(name = "cun")
    private String cun;

    @Column(name = "nentsi50")
    private String nentsi50;

    @ManyToOne
    @JoinColumn(name="cmum")
    @JsonIgnoreProperties(value = { "municipio", "hibernateLazyInitializer", "handler" }, allowSetters = true)
    private Municipio municipio;

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
}
