package org.example.warehouse;

import org.example.warehouse.entity.Product;
import org.example.warehouse.entity.Purchase;
import org.example.warehouse.entity.Warehouse;
import org.example.warehouse.repository.ProductRepository;
import org.example.warehouse.repository.PurchaseRepository;
import org.example.warehouse.repository.WarehouseRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
public class DataInitializer implements CommandLineRunner {

    // Das sind die Verbindungen zu deinen Repositories
    private final WarehouseRepository warehouseRepository;
    private final ProductRepository productRepository;
    private final PurchaseRepository purchaseRepository;

    public DataInitializer(WarehouseRepository warehouseRepository,
                           ProductRepository productRepository,
                           PurchaseRepository purchaseRepository) {
        this.warehouseRepository = warehouseRepository;
        this.productRepository = productRepository;
        this.purchaseRepository = purchaseRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        // --- 1. WAREHOUSES ANLEGEN ---
        Warehouse w1 = new Warehouse("001", "Linz Bahnhof", "Bahnhofsstrasse 27/9", "4020", "Linz", "Austria");
        Warehouse w2 = new Warehouse("002", "Vienna Central", "Hauptplatz 1", "1010", "Vienna", "Austria");

        warehouseRepository.save(w1);
        warehouseRepository.save(w2);

        // --- 2. 10 PRODUKTE ANLEGEN ---
        String[] names = {"Orangensaft", "Apfelsaft", "Mineralwasser", "Brot", "Milch", "Käse", "Eier", "Nudeln", "Reis", "Kaffee"};
        List<Product> products = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            Product p = new Product();
            p.setProductID("PROD-" + (100 + i));
            p.setProductName(names[i]);
            p.setProductCategory(i < 3 ? "Getränk" : "Lebensmittel");
            p.setProductQuantity(1000 + (i * 100));
            p.setProductUnit("Stück");
            // Die ersten 5 kommen nach Linz, der Rest nach Wien
            p.setWarehouse(i < 5 ? w1 : w2);
            products.add(productRepository.save(p));
        }

        // --- 3. 300 KÄUFE GENERIEREN (VERTIEFUNG) ---
        Random random = new Random();
        for (int i = 0; i < 300; i++) {
            Purchase purchase = new Purchase();
            // Wähle ein zufälliges Produkt aus der Liste oben
            Product randomProduct = products.get(random.nextInt(products.size()));

            purchase.setProduct(randomProduct);
            purchase.setAmount(random.nextInt(10) + 1); // Menge zwischen 1 und 10

            // Zufälliges Datum in den letzten 30 Tagen erstellen
            purchase.setTimestamp(LocalDateTime.now()
                    .minusDays(random.nextInt(30))
                    .minusHours(random.nextInt(24)));

            purchaseRepository.save(purchase);
        }

        System.out.println("--------------------------------------");
        System.out.println("✅ DATEN ERFOLGREICH IMPORTIERT!");
        System.out.println("-> 2 Warehouses, 10 Produkte, 300 Käufe");
        System.out.println("--------------------------------------");
    }
}