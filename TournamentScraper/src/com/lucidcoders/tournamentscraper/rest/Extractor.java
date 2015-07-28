package com.lucidcoders.tournamentscraper.rest;

public enum Extractor {
    
    MONTHLY_SERIES("8d6b3383-774c-43be-b75c-fc530cf600fb"),
    ATLAS_AREAS("6311bac8-4cc9-42e3-a412-ffb8d7b579b5"),
    ATLAS_UPCOMING("01335d95-4414-4553-ba02-9b542e008947"),
    ATLAS_UPCOMING_SCROLL("b3fa3514-e871-4f68-bb3b-b340f531a5de"),
    ATLAS_DETAILS_5_FIELDS("a8d38ab3-af6e-4d63-9a42-0ccbb79c3c29"),
    ATLAS_DETAILS_6_FIELDS("b8fc1f0f-c031-4511-be9b-0f3375b01942");
    
    private final String id;
    
    private Extractor(String id) {
	this.id = id;
    }
    
    public String getId() {
	return id;
    }
}
