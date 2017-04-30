package com.example.mobsoft.webkorhaz.utils;

import com.example.mobsoft.webkorhaz.model.ConsultationHourType;
import com.example.mobsoft.webkorhaz.model.Department;
import com.example.mobsoft.webkorhaz.network.ConsultationHourTypeDeserializer;
import com.example.mobsoft.webkorhaz.network.ConsultationHourTypeSerializer;
import com.example.mobsoft.webkorhaz.network.DepartmentDeserializer;
import com.example.mobsoft.webkorhaz.network.DepartmentSerializer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GsonHelper {
	public static final String DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";

	private static Gson gson;

	static {
		gson = new GsonBuilder()
				.registerTypeAdapter(ConsultationHourType.class, new ConsultationHourTypeDeserializer())
				.registerTypeAdapter(ConsultationHourType.class, new ConsultationHourTypeSerializer())
				.registerTypeAdapter(Department.class, new DepartmentSerializer())
				.registerTypeAdapter(Department.class, new DepartmentDeserializer())
				.setDateFormat(DATE_FORMAT).create();
	}

	public static Gson getGson() {
		return gson;
	}
}