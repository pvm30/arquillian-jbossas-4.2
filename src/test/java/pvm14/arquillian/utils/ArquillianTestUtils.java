package pvm14.arquillian.utils;

import java.io.File;

import javax.naming.InitialContext;
import javax.naming.NameClassPair;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;

import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.EnterpriseArchive;
import org.jboss.shrinkwrap.api.spec.JavaArchive;

public class ArquillianTestUtils {

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

	private static final String JAR_NAME = "ejb-pvm14.jar";

	public static JavaArchive createJavaArchive() {
		return ShrinkWrap.create(JavaArchive.class, JAR_NAME);
	}

	public static Archive<?> createEarForDeployment(JavaArchive javaArchive) {
		EnterpriseArchive enterpriseArchive = ShrinkWrap
				.create(EnterpriseArchive.class, "pvm14.ear")
				.addAsModule(javaArchive)
				.setApplicationXML(new File("./application.xml"));

		return enterpriseArchive;
	}

}
