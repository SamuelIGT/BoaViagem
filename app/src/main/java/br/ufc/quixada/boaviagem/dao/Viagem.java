package br.ufc.quixada.boaviagem.dao;

import java.io.Serializable;
import java.util.List;

/**
 * Created by samue on 25/09/2017.
 */

public class Viagem implements Serializable {
    private int id;
    private String destination;
    private String duration;
    private double totalSpend;
    private List<Cost> expenses;
    private boolean isBusiness;

    public Viagem(String destination, String duration, double totalSpend, boolean isBusiness) {
        this.destination = destination;
        this.duration = duration;
        this.totalSpend = totalSpend;
        this.isBusiness = isBusiness;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public double getTotalSpend() {
        return totalSpend;
    }

    public void setTotalSpend(double totalSpend) {
        this.totalSpend = totalSpend;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void addCost(Cost cost){
        expenses.add(cost);
    }
    public boolean removeCost(int id){
        for(Cost cost: expenses){
            if(cost.getId() == id){
                expenses.remove(cost);
                return true;
            }
        }
        return false;
    }

    public boolean isBusiness() {
        return isBusiness;
    }

    public void setBusiness(boolean business) {
        isBusiness = business;
    }
}
