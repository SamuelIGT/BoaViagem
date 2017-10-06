package br.ufc.quixada.boaviagem.dao;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by samue on 25/09/2017.
 */

public class Viagem implements Serializable {
    private long id;
    private String destination;
    private double budget;
    private int numberOfPeople;
    private List<Cost> expenses;
    private boolean isBusiness;
    private Date arrivalDate;
    private Date departureDate;

    public Viagem(String destination, double budget, boolean isBusiness, Date arrivalDate, Date departureDate, int numberOfPeople) {
        this.destination = destination;
        this.budget = budget;
        this.isBusiness = isBusiness;
        this.arrivalDate = arrivalDate;
        this.departureDate = departureDate;
        this.numberOfPeople = numberOfPeople;
        expenses = new ArrayList<>();
    }

    public List<Cost> getExpenses() {
        return expenses;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }


    public double getBudget() {
        return budget;
    }

    public void setBudget(double budget) {
        this.budget = budget;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void addCost(Cost cost) {
        long id = 1;
        if (expenses.size() > 0) {
            id = expenses.get(expenses.size()-1).getId() + 1;
        }
        cost.setId(id);

        expenses.add(cost);
    }

    public boolean removeCost(int id) {
        for (Cost cost : expenses) {
            if (cost.getId() == id) {
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

    public Date getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(Date arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public Date getDepartureDate() {
        return departureDate;
    }

    public String getDepartureDateString() {
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        return df.format(getDepartureDate());
    }
    public String getArrivalDateString() {
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        return df.format(getArrivalDate());
    }

    public void setDepartureDate(Date departureDate) {
        this.departureDate = departureDate;
    }

    public int getNumberOfPeople() {
        return numberOfPeople;
    }

    public void setNumberOfPeople(int numberOfPeople) {
        this.numberOfPeople = numberOfPeople;
    }

    public double getTotalSpend(){
        double totalSpend = 0;
        for(Cost cost: expenses){
            totalSpend += cost.getAmount();
        }
        return totalSpend;
    }

    public void update(Viagem travel){
        if(travel != null) {
            setDestination(travel.getDestination());
            setArrivalDate(travel.getArrivalDate());
            setBudget(travel.getBudget());
            setBusiness(travel.isBusiness());
            setDepartureDate(travel.getDepartureDate());
            setNumberOfPeople(travel.getNumberOfPeople());
        }
    }

}
