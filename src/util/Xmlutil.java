package util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

public class Xmlutil {

	public static Element XmlParse(String path) throws JDOMException, IOException {
		  SAXBuilder builder = new SAXBuilder();
		  InputStream file = new FileInputStream(path);
		  Document document = builder.build(file);//获得文档对象
		  Element root = document.getRootElement();//获得根节点
		  return root;
	}	
}
