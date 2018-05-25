package com.renault.guide.knowledge;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@RestController
public class KnowledgeController {
	@Autowired
	private KnowledgeService knowledgeService;

	@GetMapping("/")
	public String base() {
		return "Hi!";
	}

	@GetMapping("/knowledge")
	public String knowledge(String query) {
		try {
			return knowledgeService.getWikiExtract(query);
		} catch (Exception e) {
			System.out.println("Error\n\n" + e.getMessage() + "\n" + Arrays.toString(e.getStackTrace()).replaceAll(", ", "\n"));
			try {
				return knowledgeService.getWikiExtract(knowledgeService.getValidSearch(query));
			} catch (Exception e1) {
				return "I'm sorry Johann, I couldn't find any information about " + query + ".";
			}
		}
	}
}
