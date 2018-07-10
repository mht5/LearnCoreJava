package volume02.chapter07.util;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.TreeMap;

import javax.swing.JComboBox;

/**
 * a combo box which allows user to select from a given list
 * @author mhts
 * @date 2018Äê7ÔÂ10ÈÕ
 * @param <T>
 */
public class EnumCombo<T> extends JComboBox<String> {
	private static final long serialVersionUID = -2990758749861377225L;
	private Map<String, T> table = new TreeMap<>();
	
	public EnumCombo(Class<?> cl, String... labels) {
		for (String label : labels) {
			String name = label.toUpperCase().replace(' ', '_');
			try {
				Field f = cl.getField(name);
				@SuppressWarnings("unchecked")
				T value = (T) f.get(cl);
				table.put(label, value);
			} catch (Exception e) {
				label = "(" + label + ")";
				table.put(label, null);
			}
			addItem(label);
		}
		setSelectedItem(labels[0]);
	}
	
	public T getValue() {
		return table.get(getSelectedItem());
	}
}
