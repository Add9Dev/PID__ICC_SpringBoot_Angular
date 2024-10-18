package be.iccbxl.pid.reservationsspringboot;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class HelloController {

	@RequestMapping("/")
	public String index() {
		String s = "vive intellij";
		System.out.println(s);
		return "Greetings from Spring Boot!";
	}

}
