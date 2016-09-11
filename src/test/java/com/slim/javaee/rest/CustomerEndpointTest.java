package com.slim.javaee.rest;

import com.slim.javaee.config.JAXRSConfiguration;
import com.slim.javaee.model.Customer;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(Arquillian.class)
public class CustomerEndpointTest {

	private static final Logger LOGGER = LogManager.getLogger(CustomerEndpointTest.class);

	private static final String RESOURCE_PREFIX = JAXRSConfiguration.class.getAnnotation(ApplicationPath.class).value();

	@Inject
	private CustomerEndpoint clientEndpoint;

	@ArquillianResource
	private URL deploymentUrl;

	@Deployment
	public static WebArchive createDeployment() {
		//there is another way to do this because of NPE on getFile()...
		// but i will don't change it because it's just an example
		File persistenceFile = new File(CustomerEndpointTest.class
				.getClassLoader()
				.getResource("META-INF/persistence.xml")
				.getFile());
		return ShrinkWrap
				.create(WebArchive.class, "poc.war")
				.addClass(Customer.class)
				.addClass(CustomerEndpoint.class)
				.addClass(JAXRSConfiguration.class)
				.addAsResource(persistenceFile, "META-INF/persistence.xml")
				.addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
	}

	@Test
	public void should_be_deployed() {
		assertNotNull(deploymentUrl);
		assertNotNull(clientEndpoint);
	}

	@Test
	@RunAsClient
	public void should_send_a_new_client() throws MalformedURLException {
		// GET http://localhost:8080/api/clients/oneClient

		//        Given
		String requestUrl = RESOURCE_PREFIX + "/clients/oneClient";
		LOGGER.debug("requestUrl : " + requestUrl);

		String fullRequestUrl = deploymentUrl.toString() + requestUrl;
		LOGGER.debug("fullRequestUrl : " + fullRequestUrl);

		Client client = ClientBuilder.newClient();
		WebTarget webTarget = client.target(fullRequestUrl);
		final Invocation invocation = webTarget.request(MediaType.APPLICATION_JSON_TYPE).buildGet();

		//        When
		final Response response = invocation.invoke();
        Customer result = response.readEntity(Customer.class);


		//        Then
		assertEquals(fullRequestUrl, webTarget.getUri().toASCIIString());
		assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
		assertEquals(MediaType.APPLICATION_JSON, response.getMediaType().toString());

		assertNotNull(result);
		assertEquals("the version must equals to 1", 1, result.getVersion());
		assertEquals("the name must be equals to 'client'", "client", result.getName());

	}
}
