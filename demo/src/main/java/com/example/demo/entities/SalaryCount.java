package com.example.demo.entities;

import lombok.Data;

import java.io.Serializable;

@Data
public class SalaryCount implements Serializable {

    private static final long serialVersionUID = -6490013732659624378L;

    private String _id;
    private Double count;

    public SalaryCount(String _id, Double count) {
        this._id = _id;
        this.count = count;
    }
}
