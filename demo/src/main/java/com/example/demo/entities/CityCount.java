package com.example.demo.entities;

import lombok.Data;

import java.io.Serializable;

@Data
public class CityCount implements Serializable {

    private static final long serialVersionUID = 6878076402112756339L;

    private String _id;
    private Double count;

    public CityCount(String _id, Double count) {
        this._id = _id;
        this.count = count;
    }
}
