package br.edu.fatecpg.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

public class RespostaCarrinhos {
    private List<Carrinho> carts;
    private int total;
    private int skip;
    private int limit;

    @JsonIgnoreProperties(ignoreUnknown = true)
    public RespostaCarrinhos() {

    }

    public List<Carrinho> getCarts() {
        return carts;
    }

    public int getTotal() {
        return total;
    }

    public int getSkip() {
        return skip;
    }

    public int getLimit() {
        return limit;
    }

    @Override
    public String toString() {
        return "RespostaCarrinhos{" +
                "carts=" + carts +
                ", total=" + total +
                ", skip=" + skip +
                ", limit=" + limit +
                '}';
    }
}
