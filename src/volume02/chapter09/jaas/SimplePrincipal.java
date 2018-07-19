package volume02.chapter09.jaas;

import java.security.Principal;
import java.util.Objects;

/**
 * a principal with a named value
 * @author mhts
 * @date 2018Äê7ÔÂ19ÈÕ
 */
public class SimplePrincipal implements Principal {
	private String descr;
	private String value;
	
	public SimplePrincipal(String descr, String value) {
		this.descr = descr;
		this.value = value;
	}
	
	@Override
	public String getName() {
		return descr + "=" + value;
	}
	
	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (other == null) {
			return false;
		}
		if (getClass() != other.getClass()) {
			return false;
		}
		SimplePrincipal otherPrincipal = (SimplePrincipal) other;
		return Objects.equals(getName(), otherPrincipal.getName());
	}
	
	public int hashCode() {
		return Objects.hash(getName());
	}
	
}
