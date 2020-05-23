package org.redeoza.xestion.model.geoapi;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "codigo_postal")
@IdClass(value = CodigoPostalPK.class)
public class CodigoPostal implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "cun")
    private String cun;

    @Id
    @Column(name = "cmum")
    private String cmum;

    @Id
    @Column(name = "cpos")
    private String cpos;

    @MapsId
    @ManyToOne
    @JoinColumn(name = "cun", referencedColumnName = "cun")
    @JoinColumn(name = "cmum", referencedColumnName = "cmum")
    @JsonIgnoreProperties(value = { "codigoPostals", "hibernateLazyInitializer", "handler" }, allowSetters = true)
    private Poboacion poboacion;

    @Version
    @Column(name = "version")
    private long version;

    public String getCun() {
        return cun;
    }

    public void setCun(String cun) {
        this.cun = cun;
    }

    public String getCmum() {
        return cmum;
    }

    public void setCmum(String cmum) {
        this.cmum = cmum;
    }

    public String getCpos() {
        return cpos;
    }

    public void setCpos(String cpos) {
        this.cpos = cpos;
    }

    public Poboacion getPoboacion() {
        return poboacion;
    }

    public void setPoboacion(Poboacion poboacion) {
        this.poboacion = poboacion;
    }
}
