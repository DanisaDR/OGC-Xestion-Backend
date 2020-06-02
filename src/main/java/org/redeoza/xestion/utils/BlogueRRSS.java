package org.redeoza.xestion.utils;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BlogueRRSS {

    @JsonProperty("body")
    private String body;
    @JsonProperty("created")
    private String created;
    @JsonProperty("field_imaxe")
    private String imaxe;
    @JsonProperty("title")
    private String title;
    @JsonProperty("nid")
    private String nid;

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getImaxe() {
        return imaxe;
    }

    public void setImaxe(String imaxe) {
        this.imaxe = imaxe;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNid() {
        return nid;
    }

    public void setNid(String nid) {
        this.nid = nid;
    }
}
