package com.example.demo.entities;

import lombok.Data;

import java.io.Serializable;

@Data
public class EducationCount implements Serializable {

    private static final long serialVersionUID = -2906085131425427859L;

    private String _id;
    private Double count;

    public EducationCount(String _id, Double count) {
        this._id = _id;
        this.count = count;
    }
}
