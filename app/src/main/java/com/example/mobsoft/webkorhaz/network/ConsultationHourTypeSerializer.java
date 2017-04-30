package com.example.mobsoft.webkorhaz.network;

import com.example.mobsoft.webkorhaz.model.ConsultationHourType;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;

/**
 * Created by Apati on 2017.05.01..
 */

public class ConsultationHourTypeSerializer implements JsonSerializer<ConsultationHourType>{
    @Override
    public JsonElement serialize(ConsultationHourType src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject result = new JsonObject();
        result.add("consultationHourTypeId",  new JsonPrimitive(src.getConsultationHourTypeId()));
        result.add("name", new JsonPrimitive(src.getType()));
        return result;
    }
}
