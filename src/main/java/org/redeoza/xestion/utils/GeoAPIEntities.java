package org.redeoza.xestion.utils;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GeoAPIEntities {
    // Comunidad
    @JsonProperty("CCOM")
    private String ccom;

    @JsonProperty("COM")
    private String com;

    @JsonProperty("DMUN50")
    private String dmun50;

    // Elementos de Poboación
    @JsonProperty("CPRO")
    private String cpro;

    @JsonProperty("CMUM")
    private String cmum;

    @JsonProperty("CUN")
    private String cun;

    @JsonProperty("NENTSI50")
    private String nentsi50;

    // Núcleo de Poboación
    @JsonProperty("NNUCLEO50")
    private String nnucleo50;

    // Codigo Postales
    @JsonProperty("CPOS")
    private String cpos;

    // Tipo calle o demás
    @JsonProperty("CVIA")
    private String cvia;

    @JsonProperty("NVIAC")
    private String nviac;

    @JsonProperty("TVIA")
    private String tvia;

    public String getCpro() {
        return cpro;
    }

    public void setCpro(String cpro) {
        this.cpro = cpro;
    }

    public String getCmum() {
        return cmum;
    }

    public void setCmum(String cmun) {
        this.cmum = cmun;
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

    public String getNnucleo50() {
        return nnucleo50;
    }

    public void setNnucleo50(String nnucleo50) {
        this.nnucleo50 = nnucleo50;
    }

    public String getCpos() {
        return cpos;
    }

    public void setCpos(String cpos) {
        this.cpos = cpos;
    }

    public String getCvia() {
        return cvia;
    }

    public void setCvia(String cvia) {
        this.cvia = cvia;
    }

    public String getNviac() {
        return nviac;
    }

    public void setNviac(String nviac) {
        this.nviac = nviac;
    }

    public String getTvia() {
        return tvia;
    }

    public void setTvia(String tvia) {
        this.tvia = tvia;
    }

    public String getCcom() {
        return ccom;
    }

    public void setCcom(String ccom) {
        this.ccom = ccom;
    }

    public String getCom() {
        return com;
    }

    public void setCom(String com) {
        this.com = com;
    }

    public String getDmun50() {
        return dmun50;
    }

    public void setDmun50(String dmun50) {
        this.dmun50 = dmun50;
    }
}
