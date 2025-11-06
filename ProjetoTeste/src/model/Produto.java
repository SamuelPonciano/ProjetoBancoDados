package model;

import java.math.BigDecimal;

public class Produto {
    private BigDecimal valor;


    
    private int vendedorId;

    public Produto(BigDecimal valor, int vendedorId){
        this.valor = valor;
        this.vendedorId = vendedorId;
    }
}