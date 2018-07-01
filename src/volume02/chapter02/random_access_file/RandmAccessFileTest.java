package volume02.chapter02.random_access_file;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.time.LocalDate;

/**
 * test random access file
 * @author mhts
 * @date 2018Äê7ÔÂ1ÈÕ
 */
public class RandmAccessFileTest {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		String filePath = System.getProperty("user.dir") + "\\resources\\employee.dat";

		Employee[] staff = new Employee[3];
		staff[0] = new Employee("jack", 20000, 2010, 6, 12);
		staff[1] = new Employee("mike", 20000, 2012, 10, 20);
		staff[2] = new Employee("sam", 10000, 2017, 12, 20);
		
		try (DataOutputStream out = new DataOutputStream(new FileOutputStream(filePath))) {
			for (Employee emp : staff) {
				writeData(out, emp);
			}
		}
		
		try (RandomAccessFile in = new RandomAccessFile(filePath, "r")) {
			int n = (int) (in.length() / Employee.RECORD_SIZE);
			Employee[] newStaff = new Employee[n];
			
			for (int i = n - 1; i >= 0; i--) {
				newStaff[i] = new Employee();
				in.seek(i * Employee.RECORD_SIZE);
				newStaff[i] = readData(in);
			}
			
			for (Employee emp : newStaff) {
				System.out.println(emp);
			}
		}
	}
	
	public static void writeData(DataOutput out, Employee emp) throws IOException {
		DataIO.writeFixedString(emp.getName(), Employee.NAME_SIZE, out);
		out.writeDouble(emp.getSalary());
		LocalDate hireDay = emp.getHireDate();
		out.writeInt(hireDay.getYear());
		out.writeInt(hireDay.getMonthValue());
		out.writeInt(hireDay.getDayOfMonth());
	}
	
	public static Employee readData(DataInput in) throws IOException {
		String name = DataIO.readFixedString(Employee.NAME_SIZE, in);
		double salary = in.readDouble();
		int year = in.readInt();
		int month = in.readInt();
		int day = in.readInt();
		return new Employee(name, salary, year, month, day);
	}

}
