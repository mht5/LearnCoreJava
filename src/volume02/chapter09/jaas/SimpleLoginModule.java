package volume02.chapter09.jaas;

import java.io.IOException;
import java.nio.file.Paths;
import java.security.Principal;
import java.util.Arrays;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import javax.security.auth.Subject;
import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.NameCallback;
import javax.security.auth.callback.PasswordCallback;
import javax.security.auth.callback.UnsupportedCallbackException;
import javax.security.auth.login.LoginException;
import javax.security.auth.spi.LoginModule;

/**
 * authenticates user using username, passeord and role
 * @author mhts
 * @date 2018Äê7ÔÂ19ÈÕ
 */
public class SimpleLoginModule implements LoginModule {
	
	private Subject subject;
	private CallbackHandler callbackHandler;
	private Map<String, ?> options;

	@Override
	public void initialize(Subject subject, CallbackHandler callbackHandler,
			Map<String, ?> sharedState, Map<String, ?> options) {
		this.subject = subject;
		this.callbackHandler = callbackHandler;
		this.options = options;
	}

	@Override
	public boolean login() throws LoginException {
		if (callbackHandler == null) {
			throw new LoginException("no handler");
		}
		NameCallback nameCallback = new NameCallback("username: ");
		PasswordCallback passCallback = new PasswordCallback("password: ", false);
		
		try {
			callbackHandler.handle(new Callback[] {nameCallback, passCallback});
		} catch (UnsupportedCallbackException e) {
			LoginException e2 = new LoginException("Unsupported callback");
			e2.initCause(e);
			throw e2;
		} catch (IOException e) {
			LoginException e2 = new LoginException("I/O exception in callback");
			e2.initCause(e);
			throw e2;
		}
		
		try {
			return checkLogin(nameCallback.getName(), passCallback.getPassword());
		} catch (IOException e) {
			LoginException e2 = new LoginException();
			e2.initCause(e);
			throw e2;
		}
	}
	
	private boolean checkLogin(String username, char[] password) throws IOException {
		try (Scanner in = new Scanner(Paths.get("" + options.get("pwfile")), "UTF-8")) {
			while (in.hasNextLine()) {
				String[] inputs = in.nextLine().split("\\|");
				if (inputs[0].equals(username) && Arrays.equals(inputs[1].toCharArray(), password)) {
					String role = inputs[2];
					Set<Principal> principals = subject.getPrincipals();
					principals.add(new SimplePrincipal("username", username));
					principals.add(new SimplePrincipal("role", role));
					return true;
				}
			}
			return false;
		}
	}

	@Override
	public boolean logout() throws LoginException {
		return true;
	}
	
	@Override
	public boolean abort() throws LoginException {
		return true;
	}

	@Override
	public boolean commit() throws LoginException {
		return true;
	}

}
