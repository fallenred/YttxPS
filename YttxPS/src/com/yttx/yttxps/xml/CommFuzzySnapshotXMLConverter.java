package com.yttx.yttxps.xml;

import java.io.StringWriter;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.CompactWriter;

/**
 * 公共模糊资源xml转换
 * @author huangtao
 *
 */
public class CommFuzzySnapshotXMLConverter {
	  private static String XML_DECLARATION = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
	  private static String XML_DECLARATION1 = "<?xml version='1.0' encoding='UTF-8'?>";
	  private static String XML_ROOT = "<g:root xmlns:g=\"http://www.cnacex.com/\">";
	  private static String XML_ROOT1 = "<g:root xmlns:g='http://www.cnacex.com/'>";
	  public static XStream xStream = new XStream();
	  
	  static
	  {
	    xStream.alias("body", Body.class);
	    
	    xStream.addImplicitArray(Body.class, "reslist", Reslist.class);
	    xStream.aliasField("restype", Reslist.class, "restype");
	    xStream.aliasField("resprop", Reslist.class, "resprop");
	    xStream.aliasField("resname", Reslist.class, "resname");
	  }
	  
	  public static String convert2XML(Body input)
	  {
	    StringWriter sw = new StringWriter();
	    xStream.marshal(input, new CompactWriter(sw));
	    return XML_DECLARATION + sw.toString();
	  }
	  
	  public static Body convert2Msg(String xml)
	  {
		//加入实体注解
		//xStream.processAnnotations(Body.class);
	    xml = xml.replace(XML_DECLARATION, "").replace(XML_DECLARATION1, "").replace(XML_ROOT, "").replace(XML_ROOT1, "").replace("</g:root>", "");
	    return (Body)xStream.fromXML(xml);
	  }
}
