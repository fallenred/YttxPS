package com.yttx.yttxps.xml;

import java.io.StringWriter;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.CompactWriter;
import com.yttx.yttxps.xml.bean.Body;
import com.yttx.yttxps.xml.bean.Cclist;
import com.yttx.yttxps.xml.bean.Daylist;
import com.yttx.yttxps.xml.bean.Reslist;

/**
 * 资源快照转换
 * @author huangtao
 *
 */
public class ResScheduleXMLConverter {
	  private static String XML_DECLARATION = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
	  private static String XML_DECLARATION1 = "<?xml version='1.0' encoding='UTF-8'?>";
	  private static String XML_ROOT = "<u:root xmlns:u=\"http://www.yttx.com/\">";
	  private static String XML_ROOT1 = "<u:root xmlns:u='http://www.yttx.com/'>";
	  public static XStream xStream = new XStream();
	  
	  static
	  {
	    xStream.alias("body", Body.class);
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
	  
	  public static String convert2XML(Body input)
	  {
	    StringWriter sw = new StringWriter();
	    xStream.marshal(input, new CompactWriter(sw));
	    return XML_DECLARATION + sw.toString();
	  }
	  
	  public static Body convert2Body(String xml)
	  {
		//加入实体注解
		//xStream.processAnnotations(Body.class);
	    xml = xml.replace(XML_DECLARATION, "").replace(XML_DECLARATION1, "").replace(XML_ROOT, "").replace(XML_ROOT1, "").replace("</u:root>", "");
	    return (Body)xStream.fromXML(xml);
	  }
}
