package org.redeoza.xestion.model.geoapi;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "municipio",
        uniqueConstraints =
        @UniqueConstraint(columnNames = {"cmum"})
)
public class Municipio implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "municipio_id")
    private int munID;

    @Column(name = "cpro")
    private String cpro;

    @Column(name = "cmum")
    private String cmum;

    @Column(name = "cun")
    private String cun;

    @Column(name = "dmun50")
    private String dmun50;

    public int getMunID() {
        return munID;
    }

    public void setMunID(int munID) {
        this.munID = munID;
    }

    public String getCmum() {
        return cmum;
    }

    public void setCmum(String cmum) {
        this.cmum = cmum;
    }

    public String getCpro() {
        return cpro;
    }

    public void setCpro(String cpro) {
        this.cpro = cpro;
    }

    public String getCun() {
        return cun;
    }

    public void setCun(String cun) {
        this.cun = cun;
    }

    public String getDmun50() {
        return dmun50;
    }

    public void setDmun50(String dmun50) {
        this.dmun50 = dmun50;
    }
}
