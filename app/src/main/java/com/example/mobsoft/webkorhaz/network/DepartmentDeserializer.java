package com.example.mobsoft.webkorhaz.network;

import com.example.mobsoft.webkorhaz.model.ConsultationHourType;
import com.example.mobsoft.webkorhaz.model.Department;
import com.example.mobsoft.webkorhaz.utils.GsonHelper;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Apati on 2017.04.30..
 */

public class DepartmentDeserializer implements JsonDeserializer<Department> {

    @Override
    public Department deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        Department department = new Department();
        department.setDepartmentId(json.getAsJsonObject().get("departmentId").getAsLong());
        department.setDepartmentName(json.getAsJsonObject().get("departmentName").getAsString());

        JsonArray consultationHourTypeArray = json.getAsJsonObject().get("consultationHourType").getAsJsonArray();
        List<ConsultationHourType> consultationHourTypes = new ArrayList<>();

        for (int i = 0 ; i < consultationHourTypeArray.size() ; i++ ) {
            JsonElement jsonElement = consultationHourTypeArray.get(i);
            ConsultationHourType consultationHourType = GsonHelper.getGson().fromJson(jsonElement, ConsultationHourType.class);

            consultationHourType.setDepartment(department);
            consultationHourTypes.add(consultationHourType);
        }

        department.setConsultationHourTypeList(consultationHourTypes);
        return department;
    }
}
