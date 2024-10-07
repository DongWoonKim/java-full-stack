package com.example.spring.springbootweather.util;

import com.example.spring.springbootweather.dto.WeatherResponse;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;

import java.io.StringReader;

public class XmlParser {
    public static WeatherResponse parseWeatherResponse(String xmlResponse) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(WeatherResponse.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        StringReader reader = new StringReader(xmlResponse);
        return (WeatherResponse) unmarshaller.unmarshal(reader);
    }
}
