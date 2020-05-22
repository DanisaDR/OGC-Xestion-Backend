package org.redeoza.xestion.model.geoapi;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "municipio",
        uniqueConstraints =
        @UniqueConstraint(columnNames = {"dmun50", "cmum"})
)
public class Municipio implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "cmum")
    private String cmum;

    @NotEmpty
    @Column(name = "dmun50")
    private String dmun50;

    @OneToMany(mappedBy = "municipio", fetch = FetchType.EAGER)
    @JsonIgnoreProperties(value = { "poboacions", "hibernateLazyInitializer", "handler" }, allowSetters = true)
    private Set<Poboacion> poboacions;

    @Version
    @Column(name = "version")
    private long version;

    public String getCmum() {
        return cmum;
    }

    public void setCmum(String cmum) {
        this.cmum = cmum;
    }

    public String getDmun50() {
        return dmun50;
    }

    public void setDmun50(String dmun50) {
        this.dmun50 = dmun50;
    }

    public Set<Poboacion> getPoboacions() {
        return poboacions;
    }

    public void setPoboacions(Set<Poboacion> poboacions) {
        this.poboacions = poboacions;
    }
}
