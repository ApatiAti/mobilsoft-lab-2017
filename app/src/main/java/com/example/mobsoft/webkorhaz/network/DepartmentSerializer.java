package com.example.mobsoft.webkorhaz.network;

import com.example.mobsoft.webkorhaz.model.ConsultationHourType;
import com.example.mobsoft.webkorhaz.model.Department;
import com.example.mobsoft.webkorhaz.utils.GsonHelper;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;

/**
 * Created by Apati on 2017.05.01..
 */

public class DepartmentSerializer implements JsonSerializer<Department> {
    @Override
    public JsonElement serialize(Department src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject result = new JsonObject();
        result.add("departmentId",  new JsonPrimitive(src.getDepartmentId()));
        result.add("departmentName", new JsonPrimitive(src.getDepartmentName()));

        JsonArray chTypeList = new JsonArray();
        for (ConsultationHourType chType: src.getConsultationHourTypeList()) {
            JsonElement jsonElement = GsonHelper.getGson().toJsonTree(chType, ConsultationHourType.class);
            chTypeList.add(jsonElement);
        }
        result.add("consultationHourType", chTypeList);
        return result;
    }

}
