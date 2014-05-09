package edu.cqut.util;

import java.io.File;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

//��ȡmvc.xml�����ļ�������
public class ConfigUtil {

	//�������ļ������action����Map����
	@SuppressWarnings("unchecked")
	public static void parseConfigFile(String fileName,
			Map<String, ActionConfig> map) {
		SAXReader reader = new SAXReader();
		try {
			Document doc = reader.read(new File(fileName));
			Element root = doc.getRootElement();
			List<Element> list = root.selectNodes("package/action");

			System.out.println("��ǰ���е�ACTION�У�" + list.size() + "��");

			for (Element actionNode : list) {
				// ��װ��ActionConfig���󣬱��浽map�С�
				ActionConfig config = new ActionConfig();
				// ��action�ڵ��path, type, name����ֵ��ȡ����
				String name = actionNode.attributeValue("name");
				String clzName = actionNode.attributeValue("class");
				String method = actionNode.attributeValue("method");
				config.setName(name);
				config.setClzName(clzName);
				if (method == null || "".equals(method)) {
					method = "execute";
				}
				config.setMethod(method);

				// ����action�ڵ��µ�forward�ӽڵ�
				Iterator itrNodes = actionNode.selectNodes("result").iterator();
				while (itrNodes.hasNext()) {
					Element forwardNode = (Element) itrNodes.next();
					String forwardName = forwardNode.attributeValue("name");
					String forwardPath = forwardNode.getTextTrim();
					if (forwardName == null || "".equals(forwardName)) {
						forwardName = "success";
					}
					//������ֵ�����ֺ͵�ַ����resultMap��
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
