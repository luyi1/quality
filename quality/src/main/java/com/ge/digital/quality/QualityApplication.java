package com.ge.digital.quality;

import java.lang.reflect.Type;
import java.util.Date;
import java.util.TimeZone;

import javax.annotation.PostConstruct;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

@EntityScan({ "com.ge.digital.quality.entity", "com.ge.digital.gearbox.common.autoIncrKey",
		"com.ge.digital.schedule.entity" })
@EnableJpaRepositories({ "com.ge.digital.gearbox.common.autoIncrKey", "com.ge.digital.quality.mapper",
		"com.ge.digital.schedule.mapper" })
@SpringBootApplication(scanBasePackages = { "com.ge.digital.gearbox.common", "com.ge.digital.quality" })
@EnableEurekaClient
public class QualityApplication {

	@Bean
	public Gson getGson() {
		GsonBuilder builder = new GsonBuilder();
		// Register an adapter to manage the date types as long values
		builder.registerTypeAdapter(Date.class, new JsonDeserializer<Date>() {
			public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
					throws JsonParseException {
				return new Date(json.getAsJsonPrimitive().getAsLong());
			}
		});
		Gson gson = builder.create();
		return gson;
	}

	public static void main(String[] args) {
		SpringApplication.run(QualityApplication.class, args);
	}

	@PostConstruct
	void setDefaultTimezone() {
		TimeZone.setDefault(TimeZone.getTimeZone("Asia/Shanghai"));
	}

}
