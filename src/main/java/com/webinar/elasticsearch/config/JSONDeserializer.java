package com.webinar.elasticsearch.config;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class JSONDeserializer extends JsonDeserializer<LocalDate> {

  @Override
  public LocalDate deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
      throws IOException, JacksonException {
    String str = jsonParser.getText();
    try {
      return LocalDate.parse(str, DateTimeFormatter.BASIC_ISO_DATE);
    } catch (DateTimeParseException e) {
      System.err.println(e);
      return null;
    }
  }
}
