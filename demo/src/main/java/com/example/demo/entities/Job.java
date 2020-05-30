package com.example.demo.entities;

import lombok.Data;
import org.bson.types.ObjectId;

import java.io.Serializable;
import java.sql.Array;

@Data
//@Document(collection = "jobs")
public class Job implements Serializable {
    private static final long serialVersionUID = 7943809661963985208L;

    private ObjectId _id;
    private String positionId;
    private String positionName;
    private String firstType;
    private String secondType;
    private String thirdType;
    private String skillLables;
    private String salary;
    private String city;
    private String workYear;
    private String education;
    private String positionAdvantage;
    private String companyFullName;
    private String companyShortName;
    private String companySize;
    private String industryField;
    private String financeStage;

    public Job(ObjectId _id, String positionId, String positionName, String firstType, String secondType, String thirdType, String skillLables, String salary, String city, String workYear, String education, String positionAdvantage, String companyFullName, String companyShortName, String companySize, String industryField, String financeStage) {
        this._id = _id;
        this.positionId = positionId;
        this.positionName = positionName;
        this.firstType = firstType;
        this.secondType = secondType;
        this.thirdType = thirdType;
        this.skillLables = skillLables;
        this.salary = salary;
        this.city = city;
        this.workYear = workYear;
        this.education = education;
        this.positionAdvantage = positionAdvantage;
        this.companyFullName = companyFullName;
        this.companyShortName = companyShortName;
        this.companySize = companySize;
        this.industryField = industryField;
        this.financeStage = financeStage;
    }
}
