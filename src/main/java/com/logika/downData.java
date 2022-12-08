package com.logika;

public class downData {
    private final clearBaris clearBaris;
    private final tampilData tampilData;
    public downData(clearBaris clearBaris, tampilData tampilData){
        super();
        this.clearBaris = clearBaris;
        this.tampilData = tampilData;
    }

    public clearBaris getClearBaris() {
        return clearBaris;
    }

    public tampilData getTampilData() {
        return tampilData;
    }
}
