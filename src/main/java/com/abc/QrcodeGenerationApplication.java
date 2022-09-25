package com.abc;

import java.awt.image.BufferedImage;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.BufferedImageHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "qrcode generation", version = "1.0", description = "qr code generation by giving some text as input", contact = @Contact(name = "Bharath", email = "abc@gmail.com", url = "www.google.com"), license = @License(name = "abc", url = "www.extension.com")))
@RestController
public class QrcodeGenerationApplication {
	@Autowired
	private QrCodeConfig qrCodeConfig;

	@PostMapping(path = "/", produces = MediaType.IMAGE_PNG_VALUE)
	public ResponseEntity<BufferedImage> generateQrCode(@RequestBody String url) throws IOException {
		BufferedImage generateQrCodeImage = qrCodeConfig.generateQrCodeImage(url);
		System.out.println(generateQrCodeImage);

		return new ResponseEntity<BufferedImage>(generateQrCodeImage, HttpStatus.OK);

	}

	@Bean
	public HttpMessageConverter<BufferedImage> createImageHttpMessageConverter() {
		return new BufferedImageHttpMessageConverter();
	}

	public static void main(String[] args) {
		SpringApplication.run(QrcodeGenerationApplication.class, args);
	}

}
