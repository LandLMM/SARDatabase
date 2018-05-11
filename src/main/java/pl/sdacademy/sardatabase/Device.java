package pl.sdacademy.sardatabase;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class Device {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotNull(message = "The field cannot be left empty")
    private String manufacturerName;
    @NotNull(message = "The field cannot be left empty")
    private String modelName;
    @NotNull(message = "The field cannot be left empty")
    private int modelNumber;
    @NotNull(message = "The field cannot be left empty")
    private int sarHeadValue;
    @NotNull(message = "The field cannot be left empty")
    private int sarBodyWornValue;


    public Device() {
    }

    public Device(int id, String manufacturerName, String modelName, int modelNumber, int sarHeadValue, int sarBodyWornValue) {
        this.id=id;
        this.manufacturerName = manufacturerName;
        this.modelName = modelName;
        this.modelNumber = modelNumber;
        this.sarHeadValue = sarHeadValue;
        this.sarBodyWornValue = sarBodyWornValue;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getManufacturerName() {
        return manufacturerName;
    }

    public void setManufacturerName(String manufacturerName) {
        this.manufacturerName = manufacturerName;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public int getModelNumber() {
        return modelNumber;
    }

    public void setModelNumber(int modelNumber) {
        this.modelNumber = modelNumber;
    }

    public int getSarHeadValue() {
        return sarHeadValue;
    }

    public void setSarHeadValue(int sarHeadValue) {
        this.sarHeadValue = sarHeadValue;
    }

    public int getSarBodyWornValue() {
        return sarBodyWornValue;
    }

    public void setSarBodyWornValue(int sarBodyWornValue) {
        this.sarBodyWornValue = sarBodyWornValue;
    }
}