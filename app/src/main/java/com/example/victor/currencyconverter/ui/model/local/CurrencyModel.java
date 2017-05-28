package com.example.victor.currencyconverter.ui.model.local;

/**
 * Created by victor on 27.05.17.
 */

public class CurrencyModel {

    private int id;
    private String name;
    private String shortName;
    private int nominal;
    private double value;

    public CurrencyModel(int id, String name, String shortName, int nominal, double value) {
        this.id = id;
        this.name = name;
        this.shortName = shortName;
        this.nominal = nominal;
        this.value = value;
    }

    public CurrencyModel(CurrencyModel model) {
        this.id = model.getId();
        this.name = model.getName();
        this.shortName = model.getShortName();
        this.nominal = model.getNominal();
        this.value = model.getValue();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public int getNominal() {
        return nominal;
    }

    public void setNominal(int nominal) {
        this.nominal = nominal;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
