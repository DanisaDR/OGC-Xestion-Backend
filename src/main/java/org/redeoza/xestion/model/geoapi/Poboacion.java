package org.redeoza.xestion.model.geoapi;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.domain.Persistable;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "poboacion")
public class Poboacion implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "cun")
    private String cun;

    @Column(name = "nentsi50")
    private String nentsi50;

    @MapsId
    @ManyToOne
    @JoinColumn(name = "cmum")
    @JsonIgnoreProperties(value = { "municipio", "hibernateLazyInitializer", "handler" }, allowSetters = true)
    private Municipio municipio;

    @Version
    @Column(name = "version")
    private long version;

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
