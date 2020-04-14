package com.keyf.sec;

import android.Manifest;

public class ExtPermission {
    public Manifest.permission name;
    public double damage;
    public int index;

    ExtPermission(){}

    public Manifest.permission getName() {
        return name;
    }

    public void setName(Manifest.permission name) {
        this.name = name;
    }

    public double getDamage() {
        return damage;
    }

    public void setDamage(double damage) {
        this.damage = damage;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
