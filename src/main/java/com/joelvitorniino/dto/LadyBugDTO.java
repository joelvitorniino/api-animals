package com.joelvitorniino.dto;

import com.joelvitorniino.models.LadyBug;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

public class LadyBugDTO implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String url;

    public LadyBugDTO() {
    }

    public LadyBugDTO(LadyBug obj) {
        this.id = obj.getId();
        this.url = obj.getURL();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
