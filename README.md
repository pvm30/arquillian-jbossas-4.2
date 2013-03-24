arquillian-jbossas-4.2
======================

Proof of concept on how to use arquillian with JBoss 4.2 and Java 5 

1) Configure JBoss Maven repositories. I added this profile called 'jboss-public-repository' to my settings.xml

	<profile>
		<id>jboss-public-repository</id>
		<activation>
			<activeByDefault>true</activeByDefault>
		</activation>
		<repositories>
			<repository>
				<id>jboss-public-repository-group</id>
				<name>JBoss Public Maven Repository Group</name>
				<url>https://repository.jboss.org/nexus/content/groups/public-jboss</url>
				<layout>default</layout>
				<releases>
					<enabled>true</enabled>
					<updatePolicy>never</updatePolicy>
				</releases>
				<snapshots>
					<enabled>true</enabled>
					<updatePolicy>never</updatePolicy>
				</snapshots>
			</repository>
		</repositories>
		<pluginRepositories>
			<pluginRepository>
				<id>jboss-public-repository-group</id>
				<name>JBoss Public Maven Repository Group</name>
				<url>https://repository.jboss.org/nexus/content/groups/public-jboss</url>
				<layout>default</layout>
				<releases>
					<enabled>true</enabled>
					<updatePolicy>never</updatePolicy>
				</releases>
				<snapshots>
					<enabled>true</enabled>
					<updatePolicy>never</updatePolicy>
				</snapshots>
			</pluginRepository>
		</pluginRepositories>
	</profile>
 
2) Change property jbossHome in arquillian.xml file accordingly to your environment
 
 	<property name="jbossHome">E:/jboss-4.2.3.GA</property>
 	
3) mvn test

This will execute two test: a very dull and simple StatelessHelloWorldTest and a PersistenceServiceTest 
using an injected JPA @PersistenceContext

Regards     