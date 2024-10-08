package com.automation.pages;

import com.automation.pojos.Products;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProductPage {
    private Connection connection;


    public ProductPage(Connection connection) {
        this.connection = connection;
    }


    public List<Products> getProductsById(String productId) throws SQLException {
        String query = "SELECT product_id, product_name, supplier_id, category_id, quantity_per_unit, unit_price, " +
                "units_in_stock, units_on_order, reorder_level, discontinued " +
                "FROM public.products WHERE product_id = " + productId;

        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);

        List<Products> products = new ArrayList<>();
        while (resultSet.next()) {
            Products product = new Products();
            product.setProductId(resultSet.getInt("product_id"));
            product.setProductName(resultSet.getString("product_name"));
            product.setSupplierId(resultSet.getInt("supplier_id"));
            product.setCategoryId(resultSet.getInt("category_id"));
            product.setQuantityPerUnit(resultSet.getString("quantity_per_unit"));
            product.setUnitPrice(resultSet.getFloat("unit_price"));
            product.setUnitsInStock(resultSet.getInt("units_in_stock"));
            product.setUnitsOnOrder(resultSet.getInt("units_on_order"));
            product.setReorderLevel(resultSet.getInt("reorder_level"));
            product.setDiscontinued(resultSet.getInt("discontinued") == 1);

           products.add(product);
        }

        return products;
    }
}
