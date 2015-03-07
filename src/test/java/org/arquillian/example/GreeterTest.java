package org.arquillian.example;

import javax.inject.Inject;
import javax.swing.text.SimpleAttributeSet;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 *
 * @author stikkas<stikkas@yandex.ru>
 */
@RunWith(Arquillian.class)
public class GreeterTest {

	@Inject
	Greeter greeter;

	@Deployment
	public static JavaArchive createDeploymet() {
		JavaArchive archive = ShrinkWrap.create(JavaArchive.class)
				.addClasses(Greeter.class, PhraseBuilder.class)
				.addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
		System.out.println(archive.toString(true));
		return archive;
	}

	@Test
	public void should_create_greeting() {
		Assert.assertEquals("Hello, Earthling!",
				greeter.createGreeting("Earthling"));
		greeter.greet(System.out, "Earthling");
	}
}
