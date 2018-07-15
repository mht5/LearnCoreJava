package volume02.chapter08.runtime_annotations;

import java.awt.event.ActionListener;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 
 * @author mhts
 * @date 2018Äê7ÔÂ15ÈÕ
 */
public class ActionListenerInstaller {
	public static void processAnnotations(Object obj) throws IllegalArgumentException, Throwable {
		try {
			Class<?> cl = obj.getClass();
			for (Method m : cl.getDeclaredMethods()) {
				ActionListenerFor a = m.getAnnotation(ActionListenerFor.class);
				if (a != null) {
					Field f = cl.getDeclaredField(a.source());
					f.setAccessible(true);
					addListener(f.get(obj), obj, m);
				}
			}
		} catch (ReflectiveOperationException e) {
			e.printStackTrace();
		}
	}
	
	public static void addListener(Object source, final Object param, final Method m) throws Throwable {
		InvocationHandler handler = new InvocationHandler() {
			public Object invoke(Object proxy, Method mm, Object[] args) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
				return m.invoke(param);
			}
		};
		
		Object listener = Proxy.newProxyInstance(null, new Class[] {java.awt.event.ActionListener.class}, handler);
		Method adder = source.getClass().getMethod("addActionListener", ActionListener.class);
		adder.invoke(source, listener);
	}
}
