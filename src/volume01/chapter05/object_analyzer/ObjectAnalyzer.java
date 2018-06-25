package volume01.chapter05.object_analyzer;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;

/**
 * a toString() fits for all classes
 * @author mhts
 * @date 2018Äê6ÔÂ18ÈÕ
 */
public class ObjectAnalyzer {
	private static ArrayList<Object> visited = new ArrayList<>();
	
	public static String toString(Object obj) {
		if (obj == null) {
			return null;
		}
		if (visited.contains(obj)) {
			return "...";
		}
		visited.add(obj);
		Class cl = obj.getClass();
		if (cl == String.class) {
			return (String) obj;
		}
		if (cl.isArray()) {
			String result = cl.getComponentType() + "[]{";
			for (int i = 0; i < Array.getLength(obj); i++) {
				if (i > 0) {
					result += ", ";
				}
				Object val = Array.get(obj, i);
				if (cl.getComponentType().isPrimitive()) {
					result += val;
				} else {
					result += toString(val);
				}
			}
			return result + "}";
		}
		String result = cl.getName();
		do {
			result += "[";
			Field[] fields = cl.getDeclaredFields();
			AccessibleObject.setAccessible(fields, true);
			for (Field f : fields) {
				if (!Modifier.isStatic(f.getModifiers())) {
					if (!result.endsWith("[")) {
						result += ", ";
					}
					result += f.getName() + "=";
					try {
						Class clazz = f.getType();
						Object val = f.get(obj);
						if (clazz.isPrimitive()) {
							result += val;
						} else {
							result += toString(val);
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
			result += "]";
			cl = cl.getSuperclass();
		} while (cl != null);
		return result;
	}
}
