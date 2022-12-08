package com.logika;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class skor {

    private IntegerProperty skor = new SimpleIntegerProperty(0);

    public IntegerProperty skorProperty() {
        return skor ;
    }

    public void add(int i) {
        skor.setValue(skor.getValue() + i);
    }
}
