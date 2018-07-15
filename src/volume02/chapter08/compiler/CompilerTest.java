package volume02.chapter08.compiler;

import java.awt.EventQueue;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.swing.JFrame;
import javax.tools.Diagnostic;
import javax.tools.DiagnosticCollector;
import javax.tools.FileObject;
import javax.tools.ForwardingJavaFileManager;
import javax.tools.JavaCompiler;
import javax.tools.JavaFileManager;
import javax.tools.JavaFileObject;
import javax.tools.JavaFileObject.Kind;
import javax.tools.ToolProvider;

/**
 * somehow it does not work
 * @author mhts
 * @date 2018Äê7ÔÂ15ÈÕ
 */
public class CompilerTest {

	public static void main(String[] args) throws IOException, ClassNotFoundException {
		JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
		final List<ByteArrayJavaClass> classFileObjects = new ArrayList<>();
		DiagnosticCollector<JavaFileObject> diagnostics = new DiagnosticCollector<>();
		JavaFileManager fileManager = compiler.getStandardFileManager(diagnostics, null, null);
		
		fileManager = new ForwardingJavaFileManager<JavaFileManager>(fileManager) {
			public JavaFileObject getJavaFileForOutput(Location location, final String className,
					Kind kind, FileObject sibling) throws IOException {
				if (className.startsWith("x.")) {
					ByteArrayJavaClass fileObject = new ByteArrayJavaClass(className);
					classFileObjects.add(fileObject);
					return fileObject;
				} else {
					return super.getJavaFileForOutput(location, className, kind, sibling);
				}
			}
		};
		
		String frameClassName = args.length == 0 ? "buttons.ButtonFrame" : args[0];
		JavaFileObject source = buildSource(frameClassName);
		JavaCompiler.CompilationTask task = compiler.getTask(null, fileManager, diagnostics, null, null, Arrays.asList(source));
		Boolean result = task.call();
		
		for (Diagnostic<? extends JavaFileObject> d : diagnostics.getDiagnostics()) {
			System.out.println(d.getKind() + ": " + d.getMessage(null));
		}
		fileManager.close();
		if (!result) {
			System.out.println("Compilation failed.");
			System.exit(1);
		}
		
		EventQueue.invokeLater(() -> {
			try {
				Map<String, byte[]> byteCodeMap = new HashMap<>();
				for (ByteArrayJavaClass cl : classFileObjects) {
					byteCodeMap.put(cl.getName(), cl.getBytes());
				}
				ClassLoader loader = new MapClassLoader(byteCodeMap);
				JFrame frame = (JFrame) loader.loadClass("x.Frame").newInstance();
				frame.setTitle("CompilerTest");
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}
	
	static JavaFileObject buildSource(String superclassName) throws ClassNotFoundException, IOException {
		StringBuilderJavaSource source = new StringBuilderJavaSource("x.Frame");
		source.append("package x;\n");
		source.append("public class Frame extends " + superclassName + "{");
		source.append("protected void addEventHandlers() {");
		final Properties props = new Properties();
		props.load(Class.forName(superclassName).getResourceAsStream(
				System.getProperty("user.dir") + "\\resources\\action.properties"));
		for (Map.Entry<Object, Object> e : props.entrySet()) {
			String beanName = (String) e.getKey();
			String eventCode = (String) e.getValue();
			source.append(beanName + ".addActionListener(event -> {)");
			source.append(eventCode);
			source.append("});");
		}
		source.append("}}");
		return source;
	}

}
