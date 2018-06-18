package chapter05.reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Scanner;

/**
 * test reflection
 * @author mhts
 * @date 2018Äê6ÔÂ18ÈÕ
 */
public class ReflectionTest {
	public static void main(String[] args) {
		String name;
		if (args.length > 0) {
			name = args[0];
		} else {
			Scanner in = new Scanner(System.in);
			System.out.print("Enter class name(e.g. java.util.Date): ");
			name = in.next();
			in.close();
		}
		
		try {
			Class cl = Class.forName(name);
			Class supercl = cl.getSuperclass();
			String modifiers = Modifier.toString(cl.getModifiers());
			if (modifiers.length() > 0) {
				System.out.print(modifiers + " ");
			}
			System.out.print("class " + name);
			if (supercl != null && supercl != Object.class) {
				System.out.print(" extends " + supercl.getName());
			}
			System.out.print("{\n");
			printConstructors(cl);
			System.out.println();
			printMethods(cl);
			System.out.println();
			printFields(cl);
			System.out.println("}");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static void printConstructors(Class cl) {
		String name;
		String modifiers;
		Constructor[] constructors = cl.getConstructors();
		for (Constructor c : constructors) {
			name = c.getName();
			System.out.print("    ");
			modifiers = Modifier.toString(c.getModifiers());
			if (modifiers.length() > 0) {
				System.out.print(modifiers + " ");
			}
			System.out.print(name + "(");
			Class[] paramTypes = c.getParameterTypes();
			for (int i = 0; i < paramTypes.length; i++) {
				if (i > 0) {
					System.out.print(", ");
				}
				System.out.print(paramTypes[i].getName());
			}
			System.out.println(");");
		}
	}
	
	public static void printMethods(Class cl) {
		Method[] methods = cl.getDeclaredMethods();
		Class returnType;
		String name;
		String modifiers;
		for (Method m : methods) {
			returnType = m.getReturnType();
			name = m.getName();
			System.out.print("    ");
			modifiers = Modifier.toString(m.getModifiers());
			if (modifiers.length() > 0) {
				System.out.print(modifiers + " ");
			}
			System.out.print(returnType.getName() + " " + name + "(");
			Class[] paramTypes = m.getParameterTypes();
			for (int i = 0; i < paramTypes.length; i++) {
				if (i > 0) {
					System.out.print(", ");
				}
				System.out.print(paramTypes[i].getName());
			}
			System.out.println(")");
		}
	}
	
	public static void printFields(Class cl) {
		Field[] fields = cl.getDeclaredFields();
		Class type;
		String name;
		String modifiers;
		for (Field f : fields) {
			type = f.getType();
			name = f.getName();
			System.out.print("    ");
			modifiers = Modifier.toString(f.getModifiers());
			if (modifiers.length() > 0) {
				System.out.print(modifiers + " ");
			}
			System.out.println(type.getName() + " " + name + "");
		}
	}
}
