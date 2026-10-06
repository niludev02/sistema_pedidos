package br.edu.fatecpg.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

public class ProdutoCarrinho {
    private int id;
    private String title;
    private double price;
    private int quantity;
    private double total;
    private double discountPercentage;
    private double discountedTotal;

    @JsonIgnoreProperties(ignoreUnknown = true)
    public ProdutoCarrinho() {

    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getTotal() {
        return total;
    }

    public double getDiscountPercentage() {
        return discountPercentage;
    }

    public double getDiscountedTotal() {
        return discountedTotal;
    }

    @Override
    public String toString() {
        return "ProdutoCarrinho{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                ", total=" + total +
                ", discountPercentage=" + discountPercentage +
                '}';
    }

}
