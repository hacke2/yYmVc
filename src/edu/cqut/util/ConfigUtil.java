package edu.cqut.util;

import java.io.File;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

//读取mvc.xml配置文件工具类
public class ConfigUtil {

	//将配置文件里面的action读到Map里面
	@SuppressWarnings("unchecked")
	public static void parseConfigFile(String fileName,
			Map<String, ActionConfig> map) {
		SAXReader reader = new SAXReader();
		try {
			Document doc = reader.read(new File(fileName));
			Element root = doc.getRootElement();
			List<Element> list = root.selectNodes("package/action");

			System.out.println("当前含有的ACTION有：" + list.size() + "个");

			for (Element actionNode : list) {
				// 封装成ActionConfig对象，保存到map中。
				ActionConfig config = new ActionConfig();
				// 将action节点的path, type, name属性值获取到。
				String name = actionNode.attributeValue("name");
				String clzName = actionNode.attributeValue("class");
				String method = actionNode.attributeValue("method");
				config.setName(name);
				config.setClzName(clzName);
				if (method == null || "".equals(method)) {
					method = "execute";
				}
				config.setMethod(method);

				// 遍历action节点下的forward子节点
				Iterator itrNodes = actionNode.selectNodes("result").iterator();
				while (itrNodes.hasNext()) {
					Element forwardNode = (Element) itrNodes.next();
					String forwardName = forwardNode.attributeValue("name");
					String forwardPath = forwardNode.getTextTrim();
					if (forwardName == null || "".equals(forwardName)) {
						forwardName = "success";
					}
					//将返回值的名字和地址放入resultMap中
					config.getResultMap().put(forwardName, forwardPath);
				}
				map.put(name, config);
			}
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
	}

}
