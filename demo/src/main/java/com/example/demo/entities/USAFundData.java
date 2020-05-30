package com.example.demo.entities;

import lombok.Data;
import org.bson.types.ObjectId;

import java.io.Serializable;

@Data
//@Document(collection = "USA_fund_data")
public class USAFundData implements Serializable {
    private static final long serialVersionUID = -5057815530281678883L;

    private ObjectId _id;
    private String date;
    private String code;
    private String fundName;
    private String company;
    private Double closingPrice;
    private Double growthRate;
    private Double previousClose;
    private Double change;
    private Double OneYearChange;
    private Double turnover;
    private Double ROE;
    private Double ROA;
    private Double MorningstarRating;
    private Double RiskRating;
    private Double TTMYield;
    private Double YTDFundReturn;
    private Double ThreeMonthFundReturn;
    private Double OneYearFundReturn;
    private Double ThreeYearFundReturn;
    private Double FiveYearFundReturn;
    private String TotalAssets;
    private String totalMarketCapitalization;

    public USAFundData(ObjectId _id, String date, String code, String fundName, String company, Double closingPrice, Double growthRate, Double previousClose, Double change, Double OneYearChange, Double turnover, Double ROE, Double ROA, Double MorningstarRating, Double RiskRating, Double TTMYield, Double YTDFundReturn, Double ThreeMonthFundReturn, Double OneYearFundReturn, Double ThreeYearFundReturn, Double FiveYearFundReturn, String TotalAssets, String totalMarketCapitalization) {
        this._id = _id;
        this.date = date;
        this.code = code;
        this.fundName = fundName;
        this.company = company;
        this.closingPrice = closingPrice;
        this.growthRate = growthRate;
        this.previousClose = previousClose;
        this.change = change;
        this.OneYearChange = OneYearChange;
        this.turnover = turnover;
        this.ROE = ROE;
        this.ROA = ROA;
        this.MorningstarRating = MorningstarRating;
        this.RiskRating = RiskRating;
        this.TTMYield = TTMYield;
        this.YTDFundReturn = YTDFundReturn;
        this.ThreeMonthFundReturn = ThreeMonthFundReturn;
        this.OneYearFundReturn = OneYearFundReturn;
        this.ThreeYearFundReturn = ThreeYearFundReturn;
        this.FiveYearFundReturn = FiveYearFundReturn;
        this.TotalAssets = TotalAssets;
        this.totalMarketCapitalization = totalMarketCapitalization;
    }
}
