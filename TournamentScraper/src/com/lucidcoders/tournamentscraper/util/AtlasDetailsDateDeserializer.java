package com.lucidcoders.tournamentscraper.util;

import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

public class AtlasDetailsDateDeserializer implements JsonDeserializer<Date> {
    private final DateFormat dateFormat;

    public AtlasDetailsDateDeserializer() {
      dateFormat = new SimpleDateFormat("EEEE, MMM d, yyyy", Locale.US);
      dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
    }
    
    @Override
    public Date deserialize(JsonElement json, Type TypeOfT, JsonDeserializationContext context) throws JsonParseException {
	try {
	    String dateString = json.getAsString();
	    Date date = dateFormat.parse(dateString);
	    return date;
	} catch (ParseException e) {
	    throw new JsonParseException(e);
	}
    }

}
