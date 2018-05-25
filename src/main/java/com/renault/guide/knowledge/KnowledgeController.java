package com.renault.guide.knowledge;

import com.renault.guide.knowledge.domain.WikiExtract;
import com.renault.guide.knowledge.domain.WikiResult;
import org.apache.http.client.utils.URIBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.Arrays;

@RestController
public class KnowledgeController {
	private RestTemplate restTemplate = new RestTemplate();

	@GetMapping("/")
	public String base() {
		return "Hi!";
	}

	@GetMapping("/knowledge")
	public String knowledge(String query) {
		try {
			return getWikiExtract(query);
		} catch (Exception e) {
			System.out.println("Error\n\n" + e.getMessage() + "\n" + Arrays.toString(e.getStackTrace()).replaceAll(", ", "\n"));
			return "I'm sorry Johann, I couldn't find any information about " + query + ".";
		}
	}

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
	 * Calls https://en.wikipedia.org/api/rest_v1/page/summary/Louvre
	 */
	private String getWikiExtract(String query) throws Exception {
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

		return exchange.getBody().getExtract();
	}
}
