package volume02.chapter04.interruptible;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.channels.SocketChannel;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * interruptible socket channel and normal socket
 * @author mhts
 * @date 2018Äê7ÔÂ5ÈÕ
 */
public class InterruptibleSocketFrame extends JFrame {
	private static final long serialVersionUID = 4473880408468350833L;
	private Scanner in;
	private JButton interruptibleButton;
	private JButton blockingButton;
	private JButton cancelButton;
	private JTextArea messages;
	private TestServer server;
	private Thread connectThread;
	
	public InterruptibleSocketFrame() {
		JPanel northPanel = new JPanel();
		add(northPanel, BorderLayout.NORTH);
		
		final int TEXT_ROWS = 20;
		final int TEXT_COLUMNS = 60;
		messages = new JTextArea(TEXT_ROWS, TEXT_COLUMNS);
		add(new JScrollPane(messages));
		
		interruptibleButton = new JButton("Interruptible");
		blockingButton = new JButton("BLocking");
		
		northPanel.add(interruptibleButton);
		northPanel.add(blockingButton);
		
		interruptibleButton.addActionListener(event -> {
			interruptibleButton.setEnabled(false);
			blockingButton.setEnabled(false);
			cancelButton.setEnabled(true);
			connectThread = new Thread(() -> {
				try {
					connectInterruptibly();
				} catch (IOException e) {
					messages.append("\nInterruptibleSocketTest.connectInterruptibly: " + e);
				}
			});
			connectThread.start();
		});
		
		blockingButton.addActionListener(event -> {
			interruptibleButton.setEnabled(false);
			blockingButton.setEnabled(false);
			cancelButton.setEnabled(true);
			connectThread = new Thread(() -> {
				try {
					connectBlocking();
				} catch (IOException e) {
					messages.append("\nInterruptibleSocketTest.connectBlocking: " + e);
				}
			});
			connectThread.start();
		});
		
		cancelButton = new JButton("Cancel");
		cancelButton.setEnabled(false);
		northPanel.add(cancelButton);
		cancelButton.addActionListener(event -> {
			connectThread.interrupt();
			cancelButton.setEnabled(false);
		});
		
		server = new TestServer();
		new Thread(server).start();
		pack();
	}
	
	public void connectInterruptibly() throws IOException {
		messages.append("Interruptible: \n");
		try (SocketChannel channel = SocketChannel.open(new InetSocketAddress("localhost", 8189))) {
			in = new Scanner(channel, "UTF-8");
			while (!Thread.currentThread().isInterrupted()) {
				messages.append("Reading ");
				if (in.hasNextLine()) {
					messages.append(in.nextLine() + "\n");
				}
			}
		} finally {
			EventQueue.invokeLater(() -> {
				messages.append("Channel closed\n");
				interruptibleButton.setEnabled(true);
				blockingButton.setEnabled(true);
			});
		}
	}
	
	public void connectBlocking() throws UnknownHostException, IOException {
		messages.append("Blocking:\n");
		try (Socket socket = new Socket("localhost", 8189)) {
			in = new Scanner(socket.getInputStream(), "UTF-8");
			while (!Thread.currentThread().isInterrupted()) {
				messages.append("Reading ");
				if (in.hasNextLine()) {
					messages.append(in.nextLine() + "\n");
				}
			}
		} finally {
			EventQueue.invokeLater(() -> {
				messages.append("Socket closed\n");
				interruptibleButton.setEnabled(true);
				blockingButton.setEnabled(true);
			});
		}
	}
	
	class TestServer implements Runnable {
		
		@Override
		public void run() {
			try (ServerSocket s = new ServerSocket(8189)) {
				while (true) {
					Socket incoming = s.accept();
					Runnable r = new TestServerHandler(incoming);
					Thread t = new Thread(r);
					t.start();
				}
			} catch (IOException e) {
				messages.append("\nTestServer.run: " + e);
			}
		}
		
	}
	
	class TestServerHandler implements Runnable {
		private Socket incoming;
		private int counter;
		
		public TestServerHandler(Socket incoming) {
			this.incoming = incoming;
		}

		@Override
		public void run() {
			try {
				try {
					OutputStream outStream = incoming.getOutputStream();
					PrintWriter out = new PrintWriter(new OutputStreamWriter(outStream, "UTF-8"), true);
					while (counter < 20) {
						counter++;
						if (counter <= 10) {
							out.println(counter);
						}
						Thread.sleep(100);
					}
				} finally {
					incoming.close();
					messages.append("Closing server\n");
				}
			} catch (Exception e) {
				messages.append("\nTestServerHandler.run: " + e);
			}
		}
		
	}

}
