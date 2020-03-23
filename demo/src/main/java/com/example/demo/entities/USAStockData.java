package com.example.demo.entities;

import lombok.Data;
import org.bson.types.ObjectId;

import java.io.Serializable;

@Data
//@Document(collection = "USA_stock_data")
public class USAStockData implements Serializable {
    private static final long serialVersionUID = -1084645073757625821L;

    private ObjectId _id;
    private String date;
    private String code;
    private String name;
    private Double closingPrice;
    private Double openingPrice;
    private Double maxPrice;
    private Double minPrice;
    private Double volume;
    private Double previousClose;
    private Double quoteChange;
    private Double change;
    private Double turnover;
    private Double amplitude;
    private Double turnoverRate;
    private Double PER;
    private Double QRR;
    private Double PBR;
    private Double totalMarketCapitalization;
    private Double TTM;
    private Double EPS;
    private Double NAVPS;

    public USAStockData(ObjectId _id, String date, String code, String name, Double closingPrice, Double openingPrice, Double maxPrice, Double minPrice, Double volume, Double previousClose, Double quoteChange, Double change, Double turnover, Double amplitude, Double turnoverRate, Double PER, Double QRR, Double PBR, Double totalMarketCapitalization, Double TTM, Double EPS, Double NAVPS) {
        this._id = _id;
        this.date = date;
        this.code = code;
        this.name = name;
        this.closingPrice = closingPrice;
        this.openingPrice = openingPrice;
        this.maxPrice = maxPrice;
        this.minPrice = minPrice;
        this.volume = volume;
        this.previousClose = previousClose;
        this.quoteChange = quoteChange;
        this.change = change;
        this.turnover = turnover;
        this.amplitude = amplitude;
        this.turnoverRate = turnoverRate;
        this.PER = PER;
        this.QRR = QRR;
        this.PBR = PBR;
        this.totalMarketCapitalization = totalMarketCapitalization;
        this.TTM = TTM;
        this.EPS = EPS;
        this.NAVPS = NAVPS;
    }
}
