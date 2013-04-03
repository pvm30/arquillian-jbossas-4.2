package pvm14.arquillian.tests;

import static org.junit.Assert.assertEquals;

import javax.ejb.EJB;
import javax.naming.NamingException;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import pvm14.arquillian.persistence.Record;
import pvm14.arquillian.stateless.PersistenceService;
import pvm14.arquillian.stateless.PersistenceServiceBean;
import pvm14.arquillian.utils.ArquillianTestUtils;

@RunWith(Arquillian.class)
public class PersistenceServiceTest {

	@Deployment
	public static Archive<?> createArchiveForDeployment() {
		JavaArchive javaArchive = ArquillianTestUtils
				.createJavaArchive()
				.addClasses(Record.class, PersistenceService.class,
						PersistenceServiceBean.class,
						PersistenceServiceTest.class, ArquillianTestUtils.class)
				.addAsManifestResource("test-persistence.xml",
						"persistence.xml");

		return ArquillianTestUtils.createEarForDeployment(javaArchive);
	}

	// The only way I found of resolving these injections was using the JNDI
	// names through the attribute mappedName

	// [org.jboss.ejb3.stateless.BaseStatelessProxyFactory] Binding proxy for
	// PersistenceServiceBean in JNDI at pvm14/PersistenceServiceBean/remote
	@EJB(mappedName = "pvm14/PersistenceServiceBean/local")
	PersistenceService service;

	@Test
	public void testInsert() throws NamingException {
		ArquillianTestUtils.printJNDIContext();
		String strings[] = { "record1", "record2" };
		for (String string : strings) {
			service.insert(string);
		}

		assertEquals(strings.length, service.selectAll().size());
	}
}