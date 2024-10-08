package com.automation.pojos;
import lombok.Data;

@Data
public class Products {
    private int productId;
    private String productName;
    private int supplierId;
    private int categoryId;
    private String quantityPerUnit;
    private float unitPrice;
    private int unitsInStock;
    private int unitsOnOrder;
    private int reorderLevel;
    private boolean discontinued;
}
