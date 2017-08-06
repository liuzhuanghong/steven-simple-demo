package com.steven.gis.geocoder;

import org.junit.Test;

import com.google.code.geocoder.Geocoder;
import com.google.code.geocoder.GeocoderRequestBuilder;
import com.google.code.geocoder.model.GeocoderRequest;

public class TestGeocoder {

	@Test
	public void test() throws Exception {
		final Geocoder geocoder = new Geocoder();
		GeocoderRequest geocoderRequest = new GeocoderRequestBuilder().setAddress("Paris, France").setLanguage("en")
				.getGeocoderRequest();
		com.google.code.geocoder.model.GeocodeResponse geocoderResponse = geocoder.geocode(geocoderRequest);
		System.out.println(geocoderResponse.getResults().get(0).getFormattedAddress());

	}

}
