package com.lingavin.flickrviewer.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.lingavin.flickrviewer.model.Photo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by gavin on 15/5/14.
 */
public class FlickrJsonParser {

    public static List<Photo> parsePhotos(String response) {
        Gson gson = (new GsonBuilder()).create();
        JsonParser parser = new JsonParser();
        JsonObject jsonObject = (JsonObject) parser.parse(response);
        List<Photo> photos = Arrays.asList(gson.fromJson(jsonObject.getAsJsonArray("items"), Photo[].class));
        return photos;
    }
}
