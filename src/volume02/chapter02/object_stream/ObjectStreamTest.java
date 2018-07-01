package volume02.chapter02.object_stream;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * test object I/O stream
 * @author mhts
 * @date 2018Äê7ÔÂ1ÈÕ
 */
public class ObjectStreamTest {

	public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {
		String filePath = System.getProperty("user.dir") + "\\resources\\employee.dat";
		
		Employee jack = new Employee("jack", 20000, 2010, 6, 12);
		Manager mike = new Manager("mike", 20000, 2012, 10, 20);
		Manager sam = new Manager("sam", 10000, 2017, 12, 20);
		mike.setSecretary(jack);
		sam.setSecretary(jack);
		
		Employee[] staff = new Employee[3];
		staff[0] = jack;
		staff[1] = mike;
		staff[2] = sam;
		
		try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filePath))) {
			out.writeObject(staff);
		}
		
		try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(filePath))) {
			Employee[] newStaff = (Employee[]) in.readObject();
			newStaff[1].raiseSalary(20);
			
			for (Employee emp : newStaff) {
				System.out.println(emp);
			}
		}
	}

}
