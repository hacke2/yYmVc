package edu.cqut.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

public class BeanUtil {

	//
	@SuppressWarnings("unchecked")
	public static void requestToAction(HttpServletRequest request, Object action) {
		Class clzAction = action.getClass();
		Enumeration<String> names = request.getParameterNames();
		while (names.hasMoreElements()) {
			String name = names.nextElement(); // 获得每一个参数名 eg:username
			String[] value = request.getParameterValues(name); // 获取这个参数对应的值 eg:leno
			if (value != null) {
				try {
					// 看Action里是否有name这个属性，并获取在Action里的数据类型
					Class fieldType = clzAction.getDeclaredField(name).getType();
					String setName = "set" + name.substring(0, 1).toUpperCase()
							+ name.substring(1);
					Method method = clzAction.getMethod(setName,
							new Class[] { fieldType });

					Object[] o = transfer(fieldType, value);

					// 判断是否为数组属性
					if (fieldType.isArray()) {
						method.invoke(action, new Object[] { o });
					} else {
						method.invoke(action, new Object[] { o[0] });
					}
				} catch (NoSuchFieldException e) {
					// e.printStackTrace();不用处理,因为有些表单字段在Action中没有对应的属性。
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	// 将action里的值设置到HttpServletRequest里
	public static void actionToRequest(HttpServletRequest request, Object action) {

		// 得到action的属性
		Field[] fields = action.getClass().getDeclaredFields();

		for (int i = 0; i < fields.length; i++) {
			String fieldName = fields[i].getName();
			String getMethodName = "get" + fieldName.substring(0, 1).toUpperCase()
					+ fieldName.substring(1);
			// 执行get方法
			try {
				Method getMethod = action.getClass().getMethod(getMethodName,
						new Class[] {});
				Object value = getMethod.invoke(action, new Object[] {});
				if (value != null) {
					request.setAttribute(fieldName, value);
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

	// 将String类型的数组转为指定类型的数组
	@SuppressWarnings("unchecked")
	private static Object[] transfer(Class fieldType, String[] value) {

		Object[] os = null;
		String typeString = fieldType.getSimpleName().replace("[]", "");
		if ("String".equalsIgnoreCase(typeString)) {
			os = value;
		} else if ("int".equals(typeString) || "Integer".equals(typeString)) {
			os = new Integer[value.length];
			for (int i = 0; i < os.length; i++) {
				os[i] = Integer.parseInt(value[i]);
			}
		} else if ("float".equals(typeString) || "Float".equals(typeString)) {
			os = new Float[value.length];
			for (int i = 0; i < os.length; i++) {
				os[i] = Float.parseFloat(value[i]);
			}
		} else if ("double".equals(typeString) || "Double".equals(typeString)) {
			os = new Double[value.length];
			for (int i = 0; i < os.length; i++) {
				os[i] = Double.parseDouble(value[i]);
			}
		}

		return os;

	}

	// 得到一个类名，将其实例化
	public static Object getAction(String clzActionName) {
		Object action = null;
		try {
			action = Class.forName(clzActionName).newInstance();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return action;
	}

	//执行用户定义方法，若无定义执行excute方法然后在返回返回结果要转到的地址
	public static String executeAction(ActionConfig config, Object action) {
		String method = config.getMethod();
		String result = null;
		try {
			Method callMethod = action.getClass().getMethod(method, new Class[] {});
			result = (String) callMethod.invoke(action, new Object[] {});
		} catch (Exception e) {
			e.printStackTrace();
		}
		return config.getResultMap().get(result);
	}
}
