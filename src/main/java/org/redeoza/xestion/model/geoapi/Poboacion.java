package org.redeoza.xestion.model.geoapi;

import javax.persistence.*;

@Entity
@Table(name = "poboacion",
        uniqueConstraints =
        @UniqueConstraint(columnNames = {"nentsi50", "cun"})
)
public class Poboacion {

    @Id
    @Column(name = "cun")
    private String cun;

    @Column(name = "nentsi50")
    private String nentsi50;

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
}
