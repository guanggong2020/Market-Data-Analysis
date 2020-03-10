package com.example.demo.entities;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Data
//@Document(collection = "gupiao_data")
public class GuPiaoData implements Serializable {

    private static final long serialVersionUID = -1085990317548389872L;

    private ObjectId _id;
    private String date;
    private String code;
    private String name;
    private Double closingPrice;
    private Double maxPrice;
    private Double minPrice;
    private Double openingPrice;
    private Double previousClose;
    private Double change;
    private Double quoteChange;
    private Double turnoverRate;
    private Double volume;
    private Double turnover;
    private Double totalMarketCapitalization;
    private Double marketCapitalization;

    public GuPiaoData(ObjectId _id, String date, String code, String name, Double closingPrice, Double maxPrice, Double minPrice, Double openingPrice, Double previousClose, Double change, Double quoteChange, Double turnoverRate, Double volume, Double turnover, Double totalMarketCapitalization, Double marketCapitalization) {
        this._id = _id;
        this.date = date;
        this.code = code;
        this.name = name;
        this.closingPrice = closingPrice;
        this.maxPrice = maxPrice;
        this.minPrice = minPrice;
        this.openingPrice = openingPrice;
        this.previousClose = previousClose;
        this.change = change;
        this.quoteChange = quoteChange;
        this.turnoverRate = turnoverRate;
        this.volume = volume;
        this.turnover = turnover;
        this.totalMarketCapitalization = totalMarketCapitalization;
        this.marketCapitalization = marketCapitalization;
    }
}
