package br.ufc.quixada.blog.converters;

import org.springframework.core.convert.converter.Converter;
import java.util.Date;

public class DateConverter implements Converter<Date, java.sql.Date> {

    @Override
    public java.sql.Date convert(Date source) {
        return (source != null ? new java.sql.Date(source.getTime()) : null);
    }
}
