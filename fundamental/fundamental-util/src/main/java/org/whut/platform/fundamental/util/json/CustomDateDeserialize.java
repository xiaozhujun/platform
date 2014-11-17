package org.whut.platform.fundamental.util.json;

import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.JsonDeserializer;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: xiaozhujun
 * Date: 14-11-18
 * Time: 上午1:08
 * To change this template use File | Settings | File Templates.
 */
public class CustomDateDeserialize extends JsonDeserializer<Date> {

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    public Date deserialize(JsonParser jp, DeserializationContext ctxt)
            throws IOException, JsonProcessingException {

        Date date = null;
        try {
            date = sdf.parse(jp.getText());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }
}