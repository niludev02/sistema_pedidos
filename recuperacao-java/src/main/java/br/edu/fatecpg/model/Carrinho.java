package br.edu.fatecpg.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;
import java.util.List;

public class Carrinho {
    private int id;
    private int userId;
    private double total;
    private double discountedTotal;
    private int totalProducts;
    private int totalQuantity;
    private List<ProdutoCarrinho> products;

    @JsonIgnoreProperties(ignoreUnknown = true)
    public Carrinho() {
    }

    public List<ProdutoCarrinho> getProducts() {
        return products;
    }

    public int getTotalQuantity() {
        return totalQuantity;
    }

    public int getTotalProducts() {
        return totalProducts;
    }

    public double getDiscountedTotal() {
        return discountedTotal;
    }

    public double getTotal() {
        return total;
    }

    public int getUserId() {
        return userId;
    }

    public int getId() {
        return id;
    }

    public double getEconomia() {
        return total - discountedTotal;
    }

    @Override
    public String toString() {
        return "Carrinho{" +
                "id=" + id +
                ", userId=" + userId +
                ", total=" + total +
                ", discountedTotal=" + discountedTotal +
                ", totalProducts=" + totalProducts +
                ", totalQuantity=" + totalQuantity +
                ", products=" + products +
                '}';
    }
}
