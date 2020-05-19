package org.redeoza.xestion.model.geoapi;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Entity
@Table(name = "municipio",
        uniqueConstraints =
        @UniqueConstraint(columnNames = {"dmun50", "cmum"})
)
public class Municipio implements Serializable {

    @Id
    @Column(name = "cmum")
    private String cmum;

    @NotEmpty
    @Column(name = "dmun50")
    private String dmun50;

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
}
