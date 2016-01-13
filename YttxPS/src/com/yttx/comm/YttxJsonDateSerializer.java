package com.yttx.comm;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializerProvider;
import org.springframework.stereotype.Component;

/** json date 序列化
 * @author Lonvoy
 * @createDate 2016年1月13日
 * @email me@lonvoy.com
 *
 */
@Component
public class YttxJsonDateSerializer extends JsonSerializer<Date> {

	@Override
	public void serialize(Date arg0, JsonGenerator arg1, SerializerProvider arg2)
			throws IOException, JsonProcessingException {
		  SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	        String formattedDate = formatter.format(arg0);
	        arg1.writeString(formattedDate);
	}

}
