package pvm14.arquillian.stateless;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;

@Stateless
@Local(StatelessHelloWorld.class)
@Remote(StatelessHelloWorld.class)
public class StatelessHelloWorldBean implements StatelessHelloWorld {
	public String sayHello(String name) {
		return "Hello, " + name + " !";
	}
}
