package com.sistema.colegio.fecha_hora;

import com.sistema.colegio.convertir.ObjectUtils;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;

public class JsonLocalDateDeserializer extends StdDeserializer<LocalDate>{

	private static final long serialVersionUID = 1L;


	public JsonLocalDateDeserializer() {
        this(null);
    }
 
    public JsonLocalDateDeserializer(Class t) {
        super(t);
    }


	@Override
	public LocalDate deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
		LocalDate result = null;
		if (!ObjectUtils.isNull(p) && !p.getText().isEmpty()) {
			Instant instant = Instant.parse(p.getText());
			result = instant.atZone(ZoneOffset.UTC).toLocalDate();
			return result;
		}
		return result;
	}
}
