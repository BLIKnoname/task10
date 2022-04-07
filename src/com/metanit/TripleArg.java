package com.metanit;

import java.util.List;

public class TripleArg {

    private final List<Apartment> list;
    private final int n;
    private final double minS;

    public TripleArg( List<Apartment> list,int n,double minS){
        this.list = list;
        this.n = n;
        this.minS = minS;
    }

    public List<Apartment> getList() {
        return list;
    }

    public int getN() {
        return n;
    }

    public double getMinS() {
        return minS;
    }
}