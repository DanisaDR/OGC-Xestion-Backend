package org.redeoza.xestion.model.geoapi;

import java.io.Serializable;

public class CodigoPostalPK implements Serializable {

    private static final long serialVersionUID = 1L;

    private String cun;
    private String cmum;
    private String cpos;

    public CodigoPostalPK() {

    }

    public CodigoPostalPK(String cun, String cmum, String cpos) {
        super();
        this.cun = cun;
        this.cmum = cmum;
        this.cpos = cpos;
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

    public String getCpos() {
        return cpos;
    }

    public void setCpos(String cpos) {
        this.cpos = cpos;
    }
}
