package com.example.mypc.danhba;

public class Contact {
    private String name, number;
    private boolean isMale;

    public Contact() {
    }

    public Contact(String name, String number, boolean isMale) {
        this.name = name;
        this.number = number;
        this.isMale = isMale;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public boolean isMale() {
        return isMale;
    }

    public void setMale(boolean male) {
        isMale = male;
    }
}
