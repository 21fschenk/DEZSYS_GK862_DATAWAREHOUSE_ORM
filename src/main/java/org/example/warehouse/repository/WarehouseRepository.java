package org.example.warehouse.repository;

import org.example.warehouse.entity.Warehouse;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface WarehouseRepository extends CrudRepository<Warehouse, String> {

    Optional<Warehouse> findByWarehouseID(String warehouseID);

}