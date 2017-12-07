package com.tistory.pgmkkh.bunkerapp;

/**
 * Created by Kyungho on 2017-12-06.
 */

public class Product {
    private int id;
    private String bName; // 벙커네임
    private String loc; // 위치
    private double lati;  // 위 경도
    private double longi;
    private int capa; // 수용인원
    private String phone;  // 전화번호
    private String aName;  // 관리처?

    public Product(int id, String bName, String loc, double lati, double longi, int capa, String phone, String aName) {
        this.id = id;
        this.bName = bName;
        this.loc = loc;
        this.lati = lati;
        this.longi = longi;
        this.capa = capa;
        this.phone = phone;
        this.aName = aName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getbName() {
        return bName;
    }

    public void setbName(String bName) {
        this.bName = bName;
    }

    public String getLoc() {
        return loc;
    }

    public void setLoc(String loc) {
        this.loc = loc;
    }

    public double getLati() {
        return lati;
    }

    public void setLati(double lati) {
        this.lati = lati;
    }

    public double getLongi() {
        return longi;
    }

    public void setLongi(double longi) {
        this.longi = longi;
    }

    public int getCapa() {
        return capa;
    }

    public void setCapa(int capa) {
        this.capa = capa;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getaName() {
        return aName;
    }

    public void setaName(String aName) {
        this.aName = aName;
    }
}

