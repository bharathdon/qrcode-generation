package com.abc;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.apache.catalina.User;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;

import net.glxn.qrgen.javase.QRCode;

@Configuration
public class QrCodeConfig {

	public BufferedImage generateQrCodeImage(String url) throws IOException {

		ObjectMapper objectMapper = new ObjectMapper();
		  Data data2 = objectMapper.readValue(url, Data.class);
		
		Data data = new Data();
//		data.setId(1);
		data.setName(data2.getName());
		data.setUrl(data2.getUrl());
		ByteArrayOutputStream byteArrayOutputStream = QRCode.from(url).withSize(250, 250).stream();

		ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());

		return ImageIO.read(byteArrayInputStream);

	}
}
