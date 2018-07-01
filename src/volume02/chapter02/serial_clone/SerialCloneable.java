package volume02.chapter02.serial_clone;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * override clone()
 * @author mhts
 * @date 2018Äê7ÔÂ1ÈÕ
 */
public class SerialCloneable implements Cloneable, Serializable{
	private static final long serialVersionUID = -3265418257410374391L;

		public Object clone() throws CloneNotSupportedException {
			try {
				ByteArrayOutputStream bout = new ByteArrayOutputStream();
				try (ObjectOutputStream out = new ObjectOutputStream(bout)) {
					out.writeObject(this);
				}
				try (InputStream bin = new ByteArrayInputStream(bout.toByteArray())) {
					ObjectInputStream in = new ObjectInputStream(bin);
					return in.readObject();
				}
			} catch (IOException | ClassNotFoundException e) {
				CloneNotSupportedException e2 = new CloneNotSupportedException();
				e2.initCause(e);
				throw e2;
			}
		}
}
