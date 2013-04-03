package pvm14.arquillian.tests;

import javax.ejb.EJB;
import javax.naming.NamingException;

import junit.framework.Assert;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import pvm14.arquillian.stateless.StatelessHelloWorld;
import pvm14.arquillian.stateless.StatelessHelloWorldBean;
import pvm14.arquillian.utils.ArquillianTestUtils;

@RunWith(Arquillian.class)
public class JBoss4ArquillianTest {

	@Deployment
	public static Archive<?> createArchiveForDeployment() {
		JavaArchive javaArchive = ArquillianTestUtils.createJavaArchive()
				.addClasses(StatelessHelloWorld.class,
						StatelessHelloWorldBean.class,
						JBoss4ArquillianTest.class, ArquillianTestUtils.class);

		return ArquillianTestUtils.createEarForDeployment(javaArchive);
	}

	// The only way I found of resolving these injections was using the JNDI
	// names through the attribute mappedName

	// [org.jboss.ejb3.stateless.BaseStatelessProxyFactory] Binding proxy for
	// StatelessHelloWorldBean in JNDI at pvm14/StatelessHelloWorldBean/remote
	@EJB(mappedName = "pvm14/StatelessHelloWorldBean/remote")
	StatelessHelloWorld statelessHelloWorldRemote;

	// [org.jboss.ejb3.stateless.BaseStatelessProxyFactory] Binding proxy for
	// StatelessHelloWorldBean in JNDI at pvm14/StatelessHelloWorldBean/local
	@EJB(mappedName = "pvm14/StatelessHelloWorldBean/local")
	StatelessHelloWorld statelessHelloWorldLocal;

	@Test
	public void testStatelessHelloWorldRemote() throws NamingException {
		ArquillianTestUtils.printJNDIContext();
		String name = "pititer";
		Assert.assertEquals("Hello, " + name + " !",
				statelessHelloWorldRemote.sayHello(name));
	}

	@Test
	public void testStatelessHelloWorldLocal() throws NamingException {
		ArquillianTestUtils.printJNDIContext();
		String name = "pititer";
		Assert.assertEquals("Hello, " + name + " !",
				statelessHelloWorldLocal.sayHello(name));
	}

}