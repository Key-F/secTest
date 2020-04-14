package com.keyf.sec;

import java.util.List;

public class Application {
    public String name;
    public String apk;
    public int size;
    public int cost;
    public List<Threat> threatProtectionList;
    public List<ExtPermission> permissionList;
    public List<String> category;
    public int index; // Индекс в своей категории
    public List<Double> probabilityOfThreatProtection;

    public Application() {
        // Получаем полный размер списка угроз, вероятность устанавливаем том, где знаем.
    }

    public Application(String name, int cost, int size, List<Double> probabilityOfThreatProtection) {
        this.name = name;
        this.cost = cost;
        this.size = size;
        this.probabilityOfThreatProtection = probabilityOfThreatProtection;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getApk() {
        return apk;
    }

    public void setApk(String apk) {
        this.apk = apk;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public List<Threat> getThreatProtectionList() {
        return threatProtectionList;
    }

    public void setThreatProtectionList(List<Threat> threatProtectionList) {
        this.threatProtectionList = threatProtectionList;
    }

    public List<ExtPermission> getPermissionList() {
        return permissionList;
    }

    public void setPermissionList(List<ExtPermission> permissionList) {
        this.permissionList = permissionList;
    }

    public List<String> getCategory() {
        return category;
    }

    public void setCategory(List<String> category) {
        this.category = category;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public List<Double> getProbabilityOfThreatProtection() {
        return probabilityOfThreatProtection;
    }

    public Double getProbabilityOfThreatProtection(int index) {
        // Получаем вероятность защиты от конкретной угрозы
        return probabilityOfThreatProtection.get(index);
    }

    public void setProbabilityOfThreatProtection(List<Double> probabilityOfThreatProtection) {
        this.probabilityOfThreatProtection = probabilityOfThreatProtection;
    }
}


