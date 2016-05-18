package autoscaler.controller;

import org.junit.Assert;
import org.junit.Test;

import autoscaler.model.Catalog;
import autoscaler.model.fixture.CatalogFixture;
import autoscaler.service.BeanCatalogService;

public class ServiceBrokerControllerTest {

	@Test
	public void itRetrievesTheCatalog() {
		Catalog aCatalog = CatalogFixture.getCatalogWithRequires();
		BeanCatalogService bcs = new BeanCatalogService(aCatalog);
		ServiceBrokerController sbc = new ServiceBrokerController(bcs, null);
		Catalog catalog = sbc.getCatalog();
		Assert.assertEquals(aCatalog, catalog);
	}
}
