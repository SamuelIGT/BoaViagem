package br.ufc.quixada.boaviagem;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import br.ufc.quixada.boaviagem.dao.Cost;
import br.ufc.quixada.boaviagem.dao.Viagem;

/**
 * Created by samuel on 03/10/17.
 */

public class StorageController {
    private StorageService storage;
    private Gson jsonParser;

    public StorageController() {
        storage = new StorageService();
        jsonParser = new GsonBuilder().create();
    }

    public List<Viagem> getTravels(String key, Context context) {
        String serializedList;
        serializedList = storage.getDefaults(key, context);

        if (serializedList != null) {
            Type viagemListType = new TypeToken<List<Viagem>>() {
            }.getType();
            return jsonParser.fromJson(serializedList, viagemListType);
        }
        return null;
    }

    public void saveTravel(String key, Context context, Viagem travel) {
        List<Viagem> travels = getTravels(key, context);
        String serializedList;

        if (travels != null) {
            long id = 1;
            if (travels.size() > 0) {
                id = travels.get(travels.size() - 1).getId() + 1;
            }
            travel.setId(id);
        } else {
            travels = new ArrayList<>();
        }

        travels.add(travel);
        serializedList = jsonParser.toJson(travels);
        storage.setDefaults(key, serializedList, context);
    }

    public void editTravel(String key, Context context, Viagem travelEdited, long id) {
        List<Viagem> travels = getTravels(key, context);
        String serializedList;

        for (Viagem travel : travels) {
            if (travel.getId() == id) {
                travel.setDestination(travelEdited.getDestination());
                travel.setBudget(travelEdited.getBudget());
                travel.setBusiness(travelEdited.isBusiness());
                travel.setArrivalDate(travelEdited.getArrivalDate());
                travel.setDepartureDate(travelEdited.getDepartureDate());
                travel.setNumberOfPeople(travelEdited.getNumberOfPeople());
            }
        }

        serializedList = jsonParser.toJson(travels);
        storage.setDefaults(key, serializedList, context);
    }

    public List<CharSequence> getTravelsUniqueTitle(String key, Context context) {
        List<CharSequence> uniqueTiles = new ArrayList<>();
        List<Viagem> travels = getTravels(key, context);
        if (travels != null) {
            for (Viagem travel : travels) {
                uniqueTiles.add(travel.getDestination() + " - " + travel.getDepartureDateString());
            }
        }
        return uniqueTiles;
    }

    public void saveCostByTravelUniqueTitle(String key, Context context, Cost cost, String travelUniqueTile) {
        List<Viagem> travels = getTravels(key, context);
        if (travels != null) {
            for (Viagem travel : travels) {
                String uniqueTitle = travel.getDestination() + " - " + travel.getDepartureDateString();
                if (uniqueTitle.equals(travelUniqueTile)) {
                    travel.addCost(cost);
                }
            }
            String serializedList = jsonParser.toJson(travels);
            storage.setDefaults(key, serializedList, context);
        }
    }

    public Viagem getTravelById(String key, Context context, long id) {
        List<Viagem> travels = getTravels(key, context);
        if (travels != null) {
            for (Viagem travel : travels) {
                if (travel.getId() == id) {
                    return travel;
                }
            }
        }
        return null;
    }

    public void removeTravelById(String key, Context context, long id) {
        List<Viagem> travels = getTravels(key, context);
        if (travels != null) {
            for (int i = 0; i < travels.size(); i++) {
                if (travels.get(i).getId() == id) {
                    travels.remove(i);
                }
            }
        }
        updateTravels(key, context, travels);
    }

    public void updateTravels(String key, Context context, List<Viagem> travels) {
        String serializedList;
        if (travels != null) {
            serializedList = jsonParser.toJson(travels);
            storage.setDefaults(key, serializedList, context);
        }
    }
}
