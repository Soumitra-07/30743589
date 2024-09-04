package com.cts.customerloyalty.dao;

import com.cts.customerloyalty.model.Purchase;
import java.util.List;

public interface PurchaseDAO {
    void addPurchase(Purchase purchase);
    Purchase getPurchaseById(int purchaseId);
    void updatePurchase(Purchase purchase);
    void deletePurchase(int purchaseId);
    List<Purchase> getAllPurchases();
}
