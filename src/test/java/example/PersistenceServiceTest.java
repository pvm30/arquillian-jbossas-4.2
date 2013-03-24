package example;

import static org.junit.Assert.assertEquals;

import java.io.File;

import javax.ejb.EJB;
import javax.naming.NamingException;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.EnterpriseArchive;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import pvm14.arquillian.persistence.Record;
import pvm14.arquillian.stateless.PersistenceService;
import pvm14.arquillian.stateless.PersistenceServiceBean;
import pvm14.arquillian.utils.TestUtils;

@RunWith(Arquillian.class)
public class PersistenceServiceTest {

	@Deployment
	public static Archive<?> createDeployment() {
		JavaArchive javaArchive = ShrinkWrap
				.create(JavaArchive.class, "ejb-pvm14.jar")
				.addClasses(Record.class, PersistenceService.class,
						PersistenceServiceBean.class,
						PersistenceServiceTest.class, TestUtils.class)
				.addAsManifestResource("test-persistence.xml",
						"persistence.xml");

		EnterpriseArchive enterpriseArchive = ShrinkWrap
				.create(EnterpriseArchive.class, "pvm14.ear")
				.addAsModule(javaArchive)
				.setApplicationXML(new File("./application.xml"));

		return enterpriseArchive;
	}

	// [org.jboss.ejb3.stateless.BaseStatelessProxyFactory] Binding proxy for
	// PersistenceServiceBean in JNDI at pvm14/PersistenceServiceBean/remote
	@EJB(mappedName = "pvm14/PersistenceServiceBean/local")
	PersistenceService service;

	@Test
	public void queryShouldFindSeedRecord() throws NamingException {
		TestUtils.printJNDIContext();
		String strings[] = { "record1", "record2" };
		for (String string : strings) {
			service.insert(string);
		}

		assertEquals(strings.length, service.selectAll().size());
	}
}