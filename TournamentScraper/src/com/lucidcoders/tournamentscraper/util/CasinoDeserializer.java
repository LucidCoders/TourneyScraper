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
	
	//Replace address
	JsonElement jsonAddress = json.getAsJsonObject().get(ADDRESS);
	String address = "";
	
	if (jsonAddress != null && jsonAddress.getAsString() != null) {
	    address = jsonAddress.getAsString().replace("(Directions)", "").trim();
	}
	
	json.getAsJsonObject().remove(ADDRESS);
	json.getAsJsonObject().addProperty(ADDRESS, address);
	
	// Replace age
	JsonElement jsonAge = json.getAsJsonObject().get(AGE);
	String age = "";
	
	if (jsonAge != null && jsonAge.getAsString() != null) {
	    age = jsonAge.getAsString() + "+";
	}
	
	json.getAsJsonObject().remove(AGE);
	json.getAsJsonObject().addProperty(AGE, age);
	//todo Expected BEGIN_ARRAY but was STRING error
	return new Gson().fromJson(json, Result.class);
    }

}
