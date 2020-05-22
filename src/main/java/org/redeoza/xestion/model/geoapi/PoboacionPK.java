package org.redeoza.xestion.model.geoapi;

import java.io.Serializable;

public class PoboacionPK implements Serializable {

    private static final long serialVersionUID = 1L;

    private String cun;
    private String cmum;

    public PoboacionPK(String cun, String cmum) {
        super();
        this.cun = cun;
        this.cmum = cmum;
    }

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
}
