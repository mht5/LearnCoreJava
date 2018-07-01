package volume02.chapter02.text_file;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.time.LocalDate;
import java.util.Scanner;

/**
 * text file I/O
 * @author mhts
 * @date 2018Äê7ÔÂ1ÈÕ
 */
public class TextFileTest {

	public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
		String filePath = System.getProperty("user.dir") + "\\resources\\employee.dat";
		Employee[] staff = new Employee[3];
		staff[0] = new Employee("jack", 20000, 2010, 6, 12);
		staff[1] = new Employee("mike", 20000, 2012, 10, 20);
		staff[2] = new Employee("sam", 10000, 2017, 12, 12);
		
		try (PrintWriter out = new PrintWriter(filePath, "UTF-8")) {
			writeDate(out, staff);
		}
		
		try (Scanner in = new Scanner(new FileInputStream(filePath), "UTF-8")) {
			Employee[] newStaff = readData(in);
			for (Employee emp : newStaff) {
				System.out.println(emp);
			}
		}
		
	}
	
	private static void writeDate(PrintWriter out, Employee[] employees) {
		out.println(employees.length);
		for (Employee emp : employees) {
			writeEmployee(out, emp);
		}
	}
	
	private static Employee[] readData(Scanner in) {
		int n = in.nextInt();
		in.nextLine();
		Employee[] employees = new Employee[n];
		for (int i = 0; i < n; i++) {
			employees[i] = readEmployee(in);
		}
		return employees;
	}
	
	public static void writeEmployee(PrintWriter out, Employee emp) {
		out.println(emp.getName() + "|" + emp.getSalary() + "|" + emp.getHireDate());
	}
	
	public static Employee readEmployee(Scanner in) {
		String line = in.nextLine();
		String[] tokens = line.split("\\|");
		String name = tokens[0];
		double salary = Double.parseDouble(tokens[1]);
		LocalDate hireDate = LocalDate.parse(tokens[2]);
		int year = hireDate.getYear();
		int month = hireDate.getMonthValue();
		int day = hireDate.getDayOfMonth();
		return new Employee(name, salary, year, month, day);
	}

}
