package com.lucidcoders.tournamentscraper.util;

import java.lang.reflect.Type;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.lucidcoders.tournamentscraper.rest.response.HardRockHollywoodResponse.Result;

public class HardRockHollywoodDeserializer implements JsonDeserializer<Result> {
    
    private static final String CURRENCY = "tournament_value_prices/_currency";
    private static final String PRICES = "tournament_value_prices";
    private static final String SOURCE = "tournament_value_prices/_source";

    @Override
    public Result deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
	    throws JsonParseException {

	JsonElement currency = json.getAsJsonObject().get(CURRENCY);
	JsonElement price = json.getAsJsonObject().get(PRICES);
	JsonElement source = json.getAsJsonObject().get(SOURCE);

	if (currency instanceof JsonArray && price instanceof JsonArray && source instanceof JsonArray) {
	    return new Gson().fromJson(json, Result.class);
	}

	if (currency != null && !(currency instanceof JsonArray)) {
	    JsonArray newCurrency = new JsonArray();
	    newCurrency.add(currency);
	    
	    json.getAsJsonObject().remove(CURRENCY);
	    json.getAsJsonObject().add(CURRENCY, newCurrency);
	}
	
	if (price != null && !(price instanceof JsonArray)) {
	    JsonArray newPrice = new JsonArray();
	    newPrice.add(price);
	    
	    json.getAsJsonObject().remove(PRICES);
	    json.getAsJsonObject().add(PRICES, newPrice);
	}
	
	if (source != null && !(source instanceof JsonArray)) {
	    JsonArray newSource = new JsonArray();
	    newSource.add(source);
	    
	    json.getAsJsonObject().remove(SOURCE);
	    json.getAsJsonObject().add(SOURCE, newSource);
	}
	
	System.out.println("Result: " + json.toString());

	return new Gson().fromJson(json, Result.class);
    }
}
