package com.automation.pojos;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class Product {

    int product_id;
    String product_name;
    int units_in_stock;
    int unit_price;
}
