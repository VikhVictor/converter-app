package com.example.victor.currencyconverter.ui.model.response;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

/**
 * Created by victor on 24.05.17.
 */

@Root(name = "ValCurs", strict = false)
public class CurrencyCourseXml {

    @Attribute(name = "Date")
    private String date;

    @Attribute(name = "name")
    private String provider;

    @ElementList(entry = "Valute", inline = true, required = true)
    private List<CurrencyModelXml> valutes;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public List<CurrencyModelXml> getValutes() {
        return valutes;
    }

    public void setValutes(List<CurrencyModelXml> valutes) {
        this.valutes = valutes;
    }


}
