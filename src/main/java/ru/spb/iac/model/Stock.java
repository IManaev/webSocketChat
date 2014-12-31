package ru.spb.iac.model;

import lombok.*;

import java.io.*;
import java.text.*;
import java.util.*;

/**
 * Created by manaev on 24.12.14.
 */
public class Stock implements Serializable {

    private static final long serialVersionUID = 1L;
    @Getter @Setter
    private String code = "";
    @Getter @Setter
    private double price = 0.0;
    @Getter @Setter
    private Date time = new Date();

    public Stock() {

    }

    public Stock(String code, double price) {
        this.code = code;
        this.price = price;
    }

    private DateFormat df = new SimpleDateFormat("dd MMM yyyy, HH:mm:ss");

    public String getTimeStr() {
        return df.format(time);
    }

  /* standard getters & setters */

}
