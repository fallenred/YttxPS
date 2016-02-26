package com.yttx.yttxps.xml;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.namespace.QName;

import org.dom4j.Document;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.QNameMap;
import com.thoughtworks.xstream.io.xml.StaxDriver;
import com.yttx.yttxps.xml.bean.Body;
import com.yttx.yttxps.xml.bean.Cclist;
import com.yttx.yttxps.xml.bean.Daylist;
import com.yttx.yttxps.xml.bean.Reslist;
import com.yttx.yttxps.xml.bean.Root;

/**
 * 资源快照转换
 * @author huangtao
 *
 */
public class ResScheduleXMLConverter {
	public static XStream xStream = null;

	static
	{
		QNameMap qmap = new QNameMap();
		qmap.setDefaultNamespace("http://www.cnacex.com/");
		StaxDriver staxDriver = new StaxDriver(qmap); 
		staxDriver.setQnameMap(qmap);
		xStream = new XStream(staxDriver);
		xStream.alias("root", Root.class);
		xStream.aliasField("body", Body.class, "body");
		xStream.addImplicitCollection(Body.class, "reslist", "reslist", Reslist.class);
		xStream.addImplicitCollection(Body.class, "daylist", "daylist", Daylist.class);
		xStream.aliasField("dayflag", Daylist.class, "dayflag");

		xStream.aliasField("ccno", Cclist.class, "ccno");
		xStream.aliasField("ccname", Cclist.class, "ccname");
		xStream.aliasField("price", Cclist.class, "price");
		xStream.addImplicitArray(Daylist.class, "reslist", Reslist.class);
		xStream.aliasField("restype", Reslist.class, "restype");
		xStream.aliasField("resprop", Reslist.class, "resprop");
		xStream.aliasField("resno", Reslist.class, "resno");
		xStream.aliasField("resname", Reslist.class, "resname");
		xStream.aliasField("cclist", Reslist.class, "cclist");

		xStream.aliasField("ccno", Cclist.class, "ccno");
		xStream.aliasField("ccname", Cclist.class, "ccname");
		xStream.aliasField("price", Cclist.class, "price");
		xStream.aliasField("cctype", Cclist.class, "cctype");
		xStream.aliasField("usernum", Cclist.class, "usernum");

	}

	static String CHARS = "abcdefghijklmnopqrstuvwxyz";

	/**
	 *  返回一个字符串对象<br/>
	 * 
	 * 根据传输对象返回XML字符串
	 * 
	 * @author kereny
	 * @date 2015-6-3 下午3:05:16
	 * @param obj
	 * @return
	 * String
	 * @throws Exception 
	 *
	 */
	public static String toXml(String url, Object obj) throws Exception {

		QNameMap qmap = new QNameMap();
		qmap.setDefaultPrefix("");
		int rand =  (int)(Math.random() * 26);
		qmap.registerMapping(new QName(url, "root", CHARS.substring(rand, rand+1)), "root");
		StaxDriver staxDriver = new StaxDriver(qmap); 
		staxDriver.setQnameMap(qmap);
		staxDriver.setRepairingNamespace(true);

		XStream xs = new XStream(staxDriver);
		xs.processAnnotations(obj.getClass());
		xs.alias("root", obj.getClass());
		xs.aliasSystemAttribute(null, "body");

		String xmlStr = xs.toXML(obj);
		return ResScheduleXMLConverter.formatXML(xmlStr);

	}

	@SuppressWarnings("unchecked")
	public static <T> T fromXml(String url, String xml, Class<T> cls) {
		QNameMap qmap = new QNameMap();
		qmap.setDefaultNamespace(url);
		StaxDriver staxDriver = new StaxDriver(qmap); 
		staxDriver.setQnameMap(qmap);
		XStream xs = new XStream(staxDriver);
		xs.processAnnotations(cls);
		xs.alias("root",cls);
		xs.aliasSystemAttribute(null, "class");

		T obj = (T) xs.fromXML(xml);
		return obj;
	}

	public static String formatXML(String inputXML) throws Exception {  
		SAXReader reader = new SAXReader();  
		Document document = reader.read(new StringReader(inputXML));  
		String requestXML = null;  
		XMLWriter writer = null;  
		if (document != null) {  
			try {  
				StringWriter stringWriter = new StringWriter();  
				OutputFormat format = new OutputFormat(" ", true);  
				writer = new XMLWriter(stringWriter, format);  
				writer.write(document);  
				writer.flush();  
				requestXML = stringWriter.getBuffer().toString();  
			} finally {  
				if (writer != null) {  
					try {  
						writer.close();  
					} catch (IOException e) {  
					}  
				}  
			}  
		}  
		return requestXML;  
	}
}
