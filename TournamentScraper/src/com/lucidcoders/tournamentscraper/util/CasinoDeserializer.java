package com.lucidcoders.tournamentscraper.util;

import java.lang.reflect.Type;

import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.lucidcoders.tournamentscraper.rest.response.AtlasCasinoResponse.Result;

public class CasinoDeserializer implements JsonDeserializer<Result> {
    
    private static final String ADDRESS = "address_link/_text";
    private static final String AGE = "age";

    @Override
    public Result deserialize(JsonElement json, Type TypeOfT, JsonDeserializationContext context) throws JsonParseException {
	
	JsonElement jsonAddress = json.getAsJsonObject().get(ADDRESS);
	String address = jsonAddress.getAsString().replace("(Directions)", "").trim();
	
	json.getAsJsonObject().remove(ADDRESS);
	json.getAsJsonObject().addProperty(ADDRESS, address);
	
	JsonElement jsonAge = json.getAsJsonObject().get(AGE);
	String age = jsonAge.getAsString() + "+";
	
	json.getAsJsonObject().remove(AGE);
	json.getAsJsonObject().addProperty(AGE, age);
	
	
	return new Gson().fromJson(json, Result.class);
    }

}
