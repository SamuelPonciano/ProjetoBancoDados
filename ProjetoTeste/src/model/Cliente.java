package model;

import java.math.BigDecimal;

public class Client {
    private boolean isPremium;
    private BigDecimal cashBack;

    public Client(){
        this.isPremium = false;
        this.cashBack = BigDecimal.ZERO;
    }
}