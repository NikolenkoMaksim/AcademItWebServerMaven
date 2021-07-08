package ru.academits.nikolenko.coverter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class ContactsIdsConverter {
    private Gson gson = new GsonBuilder().create();

    public int[] convertFormJson(String contactsIdsJson) {
        return gson.fromJson(contactsIdsJson, int[].class);
    }
}
