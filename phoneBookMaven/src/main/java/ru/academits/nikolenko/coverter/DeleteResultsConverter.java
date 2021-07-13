package ru.academits.nikolenko.coverter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.academits.nikolenko.service.DeleteResults;

public class DeleteResultsConverter {
    private Gson gson = new GsonBuilder().create();

    public String convertToJson(DeleteResults deleteResults) {
        return gson.toJson(deleteResults);
    }
}
