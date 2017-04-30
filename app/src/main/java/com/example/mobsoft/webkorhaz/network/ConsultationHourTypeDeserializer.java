package com.example.mobsoft.webkorhaz.network;

import com.example.mobsoft.webkorhaz.model.ConsultationHourType;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;


import java.lang.reflect.Type;


/**
 * Created by Apati on 2017.04.30..
 */

public class ConsultationHourTypeDeserializer implements JsonDeserializer<ConsultationHourType> {

    @Override
    public ConsultationHourType deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        ConsultationHourType chType = new ConsultationHourType();


//        JsonElement consultationTypeid = json.getAsJsonObject().get("id");
//        Long chTypeId = consultationTypeid != null ? consultationTypeid.getAsLong() : null;
//        chType.setConsultationHourTypeId(chTypeId));

        JsonElement consultationTypeid = json.getAsJsonObject().get("consultationTypeid");
        Long chTypeId = consultationTypeid != null ? consultationTypeid.getAsLong() : null;
        chType.setConsultationHourTypeId(chTypeId);

        chType.setType(json.getAsJsonObject().get("name").getAsString());

        return chType;
    }
}
