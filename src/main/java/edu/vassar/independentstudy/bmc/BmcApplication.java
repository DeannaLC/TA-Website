package edu.vassar.independentstudy.bmc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class BmcApplication {

	public static void main(String[] args) {
		SpringApplication.run(edu.vassar.independentstudy.bmc.BmcApplication.class, args);
	}
	@GetMapping("/coaching")
	public String coachApps(@RequestParam(value = "clearance", defaultValue = "Student") String name) {
		if (name.equals("faculty"))
			return "Faculty Coach Selection";
		else if (name.equals("chair"))
			return "Chair Console";
		else
			return "Coaching Application";
	}
}


