package com.example.demo.entities;

import lombok.Data;

import java.io.Serializable;

@Data
public class SecondTypeCount implements Serializable {

    private static final long serialVersionUID = -2301123884366358087L;

    private String _id;
    private Double count;

    public SecondTypeCount(String _id, Double count) {
        this._id = _id;
        this.count = count;
    }
}
