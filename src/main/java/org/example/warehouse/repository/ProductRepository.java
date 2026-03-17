package org.example.warehouse.repository;

import org.example.warehouse.entity.Product;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ProductRepository extends CrudRepository<Product, String> {

    Iterable<Product> findByWarehouseWarehouseID(String warehouseID);

    Optional<Product> findByProductIDAndWarehouseWarehouseID(String productID, String warehouseID);

}