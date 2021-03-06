package com.renault.guide.landmark;

import com.google.maps.model.PlacesSearchResponse;
import com.google.maps.model.PlacesSearchResult;
import org.apache.http.client.utils.URIBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController
public class LandmarkFinder {
	private RestTemplate restTemplate = new RestTemplate();
	private static final int DETECTION_RADIUS = 50;
	private static final String LANDMARK_PRESENTATION_INTRO = "You are approaching ";

	@GetMapping("/landmarks")
	public String landmarks(String gps) {
		try {
			return getLandmarkPresentationIntro(gps);
		} catch (Exception e) {
			return "Error";
		}
	}

	/**
	 * https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=-33.8670522,151.1957362&radius=500&type=restaurant&keyword=cruise&key=AIzaSyA60Dnf3R0MRYDjJqHrXsPLfyMqbND1FyM
	 */
	private List<String> getLandmarks(String gps, String locationType) throws Exception {
		final URI uri = new URIBuilder()
				.setScheme("https")
				.setHost("maps.googleapis.com")
				.setPath("/maps/api/place/nearbysearch/json")
				.addParameter("location", gps)
				.addParameter("radius", Integer.toString(DETECTION_RADIUS))
				.addParameter("type", locationType)
				.addParameter("keyword", "cruise")
				.addParameter("key", "AIzaSyA60Dnf3R0MRYDjJqHrXsPLfyMqbND1FyM")
				.build()
				.toURL()
				.toURI();
		System.out.println(uri.toString());

		HttpEntity<String> entity = new HttpEntity<>(new HttpHeaders());
		ResponseEntity<PlacesSearchResponse> exchange = restTemplate.exchange(uri, HttpMethod.GET, entity, PlacesSearchResponse.class);

		PlacesSearchResult[] results = exchange.getBody().results;

		List<String> landmarksNames = new ArrayList<>();
		for (PlacesSearchResult result : results) {
			landmarksNames.add(result.name);
		}
		return landmarksNames;

	}

	private String getLandmarkPresentationIntro(String gps) throws Exception
	{
		List<String> landmarksNames = new ArrayList<>();
		landmarksNames.addAll(getLandmarks(gps, "museum"));
		landmarksNames.addAll(getLandmarks(gps, "park"));
		landmarksNames.addAll(getLandmarks(gps, "church"));
		landmarksNames.addAll(getLandmarks(gps, "city_hall"));
		if (landmarksNames.size() == 0)
		{
			return "";
		}
		else
		{
			return (LANDMARK_PRESENTATION_INTRO + String.join(", ", landmarksNames) + ".").replaceAll(", ([A-zÀ-úA-zÀ-ÿA-Za-zÀ-ÿ\\s]*)$", " and $1.");
		}

	}
}
