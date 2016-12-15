package com.example.managerproducts;

import com.example.managerproducts.model.Product;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by dprimenko on 15/12/16.
 */
public class ProductsRepository {

    private ArrayList<Product> productList;
    private static final int PRICE_COMPARISION = 0;
    private static final int STOCK_COMPARISION = 1;

    private static ProductsRepository ourInstance = new ProductsRepository();

    public static ProductsRepository getInstance() {
        return ourInstance;
    }

    private ProductsRepository() {

        productList = new ArrayList<Product>();
        addProduct(new Product("Ibuprofeno", "Para la resaca", "Bayer", "600", 2.50, 20, R.drawable.ibuprofeno));
        addProduct(new Product("Paracetamol", "Hola caracola", "Teva", "650", 5.50, 31, R.drawable.paracetamol));
        addProduct(new Product("Voltaren", "Voltaren bueno y barato", "Novartis", "75", 1.50, 15, R.drawable.voltaren));
        addProduct(new Product("StopCold", "Para parar el resfriado", "Vedim", "120", 4.50, 5, R.drawable.stop));
        addProduct(new Product("Apiretal", "Para los niños", "ERN", "100", 10.00, 60, R.drawable.apiretal));
        addProduct(new Product("Aspirina", "Mítica aspirina", "Bayer", "500", 7.00, 9, R.drawable.aspirina));
        addProduct(new Product("Valium", "Duerme", "Roche", "10", 5.00, 45, R.drawable.valium));
        addProduct(new Product("MDMA", "Para pasarlo bien", "The Streets", "20", 20.00, 18, R.drawable.mdma));

    }

    public void addProduct(Product product) {
        productList.add(product);
    }

    public List<Product> getListProducts() {
        return productList;
    }
}
