package com.example.warehouse.controller;

import com.example.warehouse.entity.Product;
import com.example.warehouse.entity.Purchase;
import com.example.warehouse.entity.Warehouse;
import com.example.warehouse.repository.ProductRepository;
import com.example.warehouse.repository.PurchaseRepository;
import com.example.warehouse.repository.WarehouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/warehouse")
public class WarehouseController {

    @Autowired
    private PurchaseRepository purchaseRepository;

    @Autowired
    private WarehouseRepository warehouseRepository;

    @Autowired
    private ProductRepository productRepository;

    @PostMapping("/addWarehouse")
    public Warehouse addWarehouse(@RequestBody Warehouse warehouse) {
        return warehouseRepository.save(warehouse);
    }

    @PostMapping("/addProduct")
    public Product addProduct(@RequestBody Product product) {
        return productRepository.save(product);
    }

    @GetMapping("/warehouses")
    public Iterable<Warehouse> getWarehouses() {
        return warehouseRepository.findAll();
    }

    @GetMapping("/products")
    public Iterable<Product> getProducts() {
        return productRepository.findAll();
    }

    // EK
    @GetMapping("/{warehouseID}/products")
    public Iterable<Product> getProductsByWarehouse(@PathVariable String warehouseID) {
        return productRepository.findByWarehouseWarehouseID(warehouseID);
    }

    @GetMapping("/{warehouseID}/products/{productID}")
    public Product getSingleProduct(@PathVariable String warehouseID,
                                    @PathVariable String productID) {

        return productRepository
                .findByProductIDAndWarehouseWarehouseID(productID, warehouseID)
                .orElse(null);
    }

    @PutMapping("/{warehouseID}")
    public Warehouse updateWarehouse(@PathVariable String warehouseID,
                                     @RequestBody Warehouse updatedWarehouse) {

        Warehouse warehouse = warehouseRepository
                .findById(warehouseID)
                .orElse(null);

        if (warehouse != null) {

            warehouse.setWarehouseName(updatedWarehouse.getWarehouseName());
            warehouse.setWarehouseAddress(updatedWarehouse.getWarehouseAddress());
            warehouse.setWarehouseCity(updatedWarehouse.getWarehouseCity());

            return warehouseRepository.save(warehouse);
        }

        return null;
    }

    @PostMapping("/purchase")
    public Purchase addPurchase(@RequestBody Purchase purchase) {
        return purchaseRepository.save(purchase);
    }

    @GetMapping("/purchase")
    public Iterable<Purchase> getPurchases() {
        return purchaseRepository.findAll();
    }


}