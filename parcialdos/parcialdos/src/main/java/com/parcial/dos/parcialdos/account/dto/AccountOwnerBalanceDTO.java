package com.parcial.dos.parcialdos.account.dto;

public class AccountOwnerBalanceDTO {

    private String dueno;
    private Double balanceActual;

    public String getDueno() {
        return dueno;
    }

    public void setDueno(String dueno) {
        this.dueno = dueno;
    }

    public Double getBalanceActual() {
        return balanceActual;
    }

    public void setBalanceActual(Double balanceActual) {
        this.balanceActual = balanceActual;
    }
}
