package org.sushisushi.sushiform;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.net.ssl.HttpsURLConnection;
import java.util.List;

import static org.springframework.boot.SpringApplication.*;

@SpringBootApplication
public class SushiFormApplication {

	public static void main(String[] args) throws Exception {

		run(SushiFormApplication.class, args);
	}

}
