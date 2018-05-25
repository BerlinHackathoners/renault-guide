package com.renault.guide.intent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IntentModuleController {
	@Autowired
	private IntentDetectionService intentDetectionService;

	@RequestMapping("/intent")
	public String intent(String query) throws Exception {
		return intentDetectionService.getIntentAndEntity(query);
	}
}
