package com.sistema.colegio.fecha_hora;

import com.sistema.colegio.convertir.ObjectUtils;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class JsonLocalDateTimeDeserializer extends StdDeserializer<LocalDateTime>{

	private static final long serialVersionUID = 1L;


	public JsonLocalDateTimeDeserializer() {
        this(null);
    }
 
    public JsonLocalDateTimeDeserializer(Class t) {
        super(t);
    }


	@Override
	public LocalDateTime deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
		LocalDateTime result = null;
		if (!ObjectUtils.isNull(p) && !p.getText().isEmpty()) {
			Instant instant = Instant.parse(p.getText());
			result = LocalDateTime.ofInstant(instant, ZoneOffset.ofHours(-5));
			return result;
		}
		return result;
	}
}
