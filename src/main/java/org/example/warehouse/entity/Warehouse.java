package org.example.warehouse.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
public class Warehouse {

    @Setter
    @Getter
    @Id
    private String warehouseID;

    @Setter
    @Getter
    private String warehouseName;
    @Setter
    @Getter
    private String warehouseAddress;
    private String warehousePostalCode;
    @Setter
    @Getter
    private String warehouseCity;
    private String warehouseCountry;

    @OneToMany(mappedBy = "warehouse")
    private List<Product> products;

    public Warehouse() {}

    public Warehouse(String warehouseID, String warehouseName, String warehouseAddress, String warehousePostalCode, String warehouseCity, String warehouseCountry) {
        this.warehouseID = warehouseID;
        this.warehouseName = warehouseName;
        this.warehouseAddress = warehouseAddress;
        this.warehousePostalCode = warehousePostalCode;
        this.warehouseCity = warehouseCity;
        this.warehouseCountry = warehouseCountry;
    }
}
