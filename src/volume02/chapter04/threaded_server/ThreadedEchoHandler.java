package volume02.chapter04.threaded_server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * a thread to handle the client input
 * @author mhts
 * @date 2018Äê7ÔÂ5ÈÕ
 */
public class ThreadedEchoHandler implements Runnable {
	private Socket incoming;
	
	public ThreadedEchoHandler(Socket incomingSocket) {
		incoming = incomingSocket;
	}

	@Override
	public void run() {
		try (InputStream inStream = incoming.getInputStream();
				OutputStream outStream = incoming.getOutputStream();
				Scanner in = new Scanner(inStream, "UTF-8")) {
			
			PrintWriter out = new PrintWriter(new OutputStreamWriter(outStream, "UTF-8"), true);
			out.println("Hello, Enter BYE to exit.");
			
			boolean done = false;
			while (!done && in.hasNextLine()) {
				String line = in.nextLine();
				out.println("Echo: " + line);
				if (line.trim().equals("BYE")) {
					done = true;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
