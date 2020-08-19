package web.chat.backend.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by koseungbin on 2020-08-19
 */

@RestController
public class HealthController {
	@GetMapping(path = "/health")
	@ResponseStatus(HttpStatus.OK)
	public String getMessages() {
		return "healthy";
	}
}
