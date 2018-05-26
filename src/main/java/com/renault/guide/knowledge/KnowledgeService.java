package com.renault.guide.knowledge;

import com.renault.guide.knowledge.domain.WikiExtract;
import com.renault.guide.knowledge.domain.WikiResult;
import org.apache.http.client.utils.URIBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

@Component
public class KnowledgeService {
	private RestTemplate restTemplate = new RestTemplate();

	/**
	 * Calls https://en.wikipedia.org/w/api.php?format=json&action=query&prop=extracts&exintro=&explaintext=&titles=Louvre
	 */
	private String getWikiExtractOld(String query) throws Exception {
		final URI uri = new URIBuilder()
				.setScheme("https")
				.setHost("en.wikipedia.org")
				.setPath("/w/api.php")
				.addParameter("format", "json")
				.addParameter("action", "query")
				.addParameter("prop", "extracts")
				.addParameter("exintro", "")
				.addParameter("explaintext", "")
				.addParameter("redirects", "1")
				.addParameter("titles", query)
				.build()
				.toURL()
				.toURI();
		System.out.println(uri.toString());

		HttpEntity<String> entity = new HttpEntity<>(new HttpHeaders());
		ResponseEntity<WikiResult> exchange = restTemplate.exchange(uri, HttpMethod.GET, entity, WikiResult.class);

		return exchange.getBody().getQuery().getPages().getResult().getExtract();
	}

	/**
	 * https://en.wikipedia.org/w/api.php
	 * ?action=opensearch
	 * &search=abdellah
	 * &prop=revisions
	 * &rvprop=content
	 * &format=json
	 * &formatversion=2
	 */
	String getValidSearch(String initialQuery) {
		URI uri = null;
		try {
			uri = new URIBuilder()
					.setScheme("https")
					.setHost("en.wikipedia.org")
					.setPath("/w/api.php/")
					.addParameter("action", "opensearch")
					.addParameter("search", initialQuery)
					.addParameter("prop", "revisions")
					.addParameter("rvprop", "content")
					.addParameter("format", "json")
					.addParameter("formatversion", "2")
					.build()
					.toURL()
					.toURI();
		} catch (URISyntaxException | MalformedURLException e) {
			e.printStackTrace();
		}
		System.out.println(uri == null ? "I'm sorry, I don't understand " : uri.toString());

		HttpEntity<String> entity = new HttpEntity<>(new HttpHeaders());

		ResponseEntity<String> exchange = restTemplate.exchange(uri, HttpMethod.GET, entity, String.class);

		String result = exchange.getBody();
		StringTokenizer st = new StringTokenizer(result, "[");
		List<String> list = new ArrayList<>();
		while (st.hasMoreTokens()) {
			list.add(st.nextToken());
		}
		System.out.println("Main result: " + list.get(1));
		//System.out.println(result);
		StringTokenizer st1 = new StringTokenizer(list.get(1), ",");
		List<String> list1 = new ArrayList<>();
		while (st1.hasMoreTokens()) {
			list1.add(st1.nextToken());
		}
		String result1 = list1.get(0);
		result1 = result1.replaceAll("\"", "");
		return result1;
	}

	/**
	 * Calls https://en.wikipedia.org/api/rest_v1/page/summary/Louvre
	 */
	public String getWikiExtract(String query) throws Exception {
		final URI uri = new URIBuilder()
				.setScheme("https")
				.setHost("en.wikipedia.org")
				.setPath("/api/rest_v1/page/summary/" + query)
				.build()
				.toURL()
				.toURI();
		System.out.println(uri.toString());

		HttpEntity<String> entity = new HttpEntity<>(new HttpHeaders());
		ResponseEntity<WikiExtract> exchange = restTemplate.exchange(uri, HttpMethod.GET, entity, WikiExtract.class);

		return exchange.getBody().getExtract().split("\\.")[0];
	}
}
