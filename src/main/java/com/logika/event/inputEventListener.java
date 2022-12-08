package com.logika.event;

import com.logika.downData;
import com.logika.tampilData;

public interface inputEventListener {

    downData onDownEvent(evenMove event);
    tampilData onLeftEvent();
    tampilData onRightEvent();
    tampilData onRotateEvent();
}
