package com.example.victor.currencyconverter.ui.model.response;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;

/**
 * Created by victor on 24.05.17.
 */


public class CurrencyModelXml {

    @Attribute(name = "ID")
    private String id;

    @Element(name = "Name")
    private String name;

    @Element(name = "Value")
    private String value;

    @Element(name = "Nominal")
    private String nominal;

    @Element(name = "CharCode")
    private String charCode;

    @Element(name = "NumCode")
    private String numCode;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNominal() {
        return nominal;
    }

    public void setNominal(String nominal) {
        this.nominal = nominal;
    }

    public String getCharCode() {
        return charCode;
    }

    public void setCharCode(String charCode) {
        this.charCode = charCode;
    }

    public String getNumCode() {
        return numCode;
    }

    public void setNumCode(String numCode) {
        this.numCode = numCode;
    }

    public double getDoubleValue() {
        return Double.valueOf(value.replace(",", "."));
    }
}