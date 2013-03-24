package pvm14.arquillian.utils;

import javax.naming.InitialContext;
import javax.naming.NameClassPair;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;

public class TestUtils {

	private static InitialContext initialContext;
	static {
		try {
			initialContext = new InitialContext();
		} catch (NamingException namingException) {
			throw new ExceptionInInitializerError(namingException);
		}
	}

	public static void printJNDIContext() throws NamingException {
		System.out.println("JNDI PVM14 contents ....................");
		NamingEnumeration<NameClassPair> namingEnumeration = initialContext
				.list("");
		while (namingEnumeration.hasMore()) {
			NameClassPair nameClassPair = namingEnumeration.next();
			System.out.println("JNDI PVM14 Element:[" + nameClassPair.getName()
					+ "]-[" + nameClassPair.getClassName() + "]");
		}

	}

}
