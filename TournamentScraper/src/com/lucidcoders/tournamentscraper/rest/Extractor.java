package com.lucidcoders.tournamentscraper.rest;

public enum Extractor {
    
    MONTHLY_SERIES("8d6b3383-774c-43be-b75c-fc530cf600fb"),
    ATLAS_AREAS("6311bac8-4cc9-42e3-a412-ffb8d7b579b5"),
    ATLAS_UPCOMING("01335d95-4414-4553-ba02-9b542e008947"),
    ATLAS_UPCOMING_SCROLL("b3fa3514-e871-4f68-bb3b-b340f531a5de"),
    ATLAS_DETAILS_5_FIELDS("a8d38ab3-af6e-4d63-9a42-0ccbb79c3c29"),
    ATLAS_DETAILS_6_FIELDS("b8fc1f0f-c031-4511-be9b-0f3375b01942"),
    ATLAS_POKER_ROOMS("3282b8f6-5b3d-450b-b16d-3b09c1775e16"),
    ATLAS_POKER_ROOMS_FEATURED("bf9a5419-fba7-46b3-ac0d-22cd68afc345"),
    ATLAS_CASINO("7f3d6788-87ef-498d-bdac-2d6978dc2547"),
    ATLAS_SERIES("bb2b6af7-1161-4f95-908d-a4b80df055c7"),
    ATLAS_SERIES_DETAILS("ad97509f-ae40-49cc-833f-ea24629a4842"),
    ATLAS_SERIES_EVENTS("d2d41895-4f5f-4b84-b79a-440b6d650324");
    
    private final String id;
    
    private Extractor(String id) {
	this.id = id;
    }
    
    public String getId() {
	return id;
    }
}
