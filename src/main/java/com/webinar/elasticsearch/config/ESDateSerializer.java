package com.webinar.elasticsearch.config;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class ESDateSerializer extends JsonSerializer<LocalDate> {

  @Override
  public void serialize(LocalDate localDate, JsonGenerator jsonGenerator,
      SerializerProvider serializerProvider) throws IOException {
    try {
      String s = localDate.format(DateTimeFormatter.BASIC_ISO_DATE);
      jsonGenerator.writeString(s);
    } catch (DateTimeParseException e) {
      System.err.println(e);
      jsonGenerator.writeString("");
    }
  }
}
