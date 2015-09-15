package spring;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jdom.Element;

import util.Xmlutil;

public class ApplicationContext {
	private Map<String,Object> beans;
	
	public ApplicationContext(String path){
		beans = new HashMap<String,Object>();
		Element root;
		//解析xml
		try {
			root = Xmlutil.XmlParse(path);
			//组装bean
			initBeans(root);
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	
	private void initBeans(Element root) throws NoSuchMethodException {
		if(!root.getName().equals("beans"))
			return;
		List<Element> children = root.getChildren();
		for(Element temp : children){
			if(!temp.getName().equals("bean"))
				return;
			String classname = temp.getAttribute("class").getValue();
			String id = temp.getAttribute("id").getValue();
			Element property = temp.getChild("property");
			String propertyName ="";
			String propertyValue = "";
			Object bean = null;
			if(property != null){
				propertyName = property.getAttribute("name").getValue();
				propertyValue = property.getAttribute("value").getValue();
			}
			
			try {
				Class clazz  = Class.forName(classname);
				bean = clazz.newInstance();
				//调用其setter方法
				Method[] methods = clazz.getDeclaredMethods();
				Method setMethod = null;
				for(Method tempMethod : methods){
					if(tempMethod.getName().equals("set"+ propertyName.toUpperCase())){
						setMethod = tempMethod;
						break;
					}
				}
				if(setMethod != null){
					Class propertyClazz =  Class.forName(propertyValue);
					Object propertyBean = propertyClazz.newInstance();
					setMethod.invoke(bean,propertyBean);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			beans.put(id, bean);
		}
	}

	public Object getBean(String beanId){
		return beans.get(beanId);
	}

}
