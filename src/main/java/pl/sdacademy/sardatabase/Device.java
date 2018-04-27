package pl.sdacademy.sardatabase;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Device {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String manufacturerName;
    private String modelName;
    private int modelNumber;
    private int sarHeadValue;
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
}