package ru.academits.nikolenko.coverter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.academits.nikolenko.model.Filter;

public class FilterConverter {
    private Gson gson = new GsonBuilder().create();

    public Filter convertFormJson(String queryStringJson) {
        return gson.fromJson(queryStringJson, Filter.class);
    }
}
