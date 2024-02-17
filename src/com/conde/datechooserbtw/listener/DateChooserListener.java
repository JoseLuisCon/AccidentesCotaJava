package com.conde.datechooserbtw.listener;

import com.conde.datechooserbtw.DateBetween;

import java.util.Date;

public interface DateChooserListener {
    public void dateChanged(Date date, DateChooserAction action);

    public void dateBetweenChanged(DateBetween date, DateChooserAction action);
}
