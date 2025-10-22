package com.parcial.dos.parcialdos.account.dto;

public class AccountResponseDTO {

    private Long id;
    private String numeroCuenta;
    private String dueno;
    private Double balanceActual;
    private Boolean active;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public void setNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

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

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}
