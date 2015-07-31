package com.lucidcoders.tournamentscraper.util;

import java.lang.reflect.Type;

import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.lucidcoders.tournamentscraper.rest.response.AtlasPokerRoomsResponse.Result;

public class PokerRoomDeserializer implements JsonDeserializer<Result> {
    
    private static final String CASINO_SOURCE = "casinos/_source";

    @Override
    public Result deserialize(JsonElement json, Type TypeOfT, JsonDeserializationContext context) throws JsonParseException {
	
	JsonElement casinoSource = json.getAsJsonObject().get(CASINO_SOURCE);
	
	String casinoId = casinoSource.getAsString().replace("/poker-room/", "");
	
	json.getAsJsonObject().remove(CASINO_SOURCE);
	json.getAsJsonObject().addProperty(CASINO_SOURCE, casinoId);
	
	return new Gson().fromJson(json, Result.class);
    }

}
