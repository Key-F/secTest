package com.keyf.sec;

public class Threat {
    public String name;
    public String category;
    public double damage;
    public double probabilityOfAppearance;
    public int index;

    public Threat(){}

    public Threat(String name, double damage, double probabilityOfAppearance) {
        this.name = name;
        this.damage = damage;
        this.probabilityOfAppearance = probabilityOfAppearance;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getDamage() {
        return damage;
    }

    public void setDamage(double damage) {
        this.damage = damage;
    }

    public double getProbabilityOfAppearance() {
        return probabilityOfAppearance;
    }

    public void setProbabilityOfAppearance(double probabilityOfAppearance) {
        this.probabilityOfAppearance = probabilityOfAppearance;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}

