package br.inatel.dm110.web;

import br.inatel.dm110.api.services.ProductService;
import br.inatel.dm110.api.dto.ProductDTO;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import java.util.List;

@Path("/products")
public class ProductRest {

	@Inject
	private ProductService productService;

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public void createProduct(ProductDTO product) {
		productService.createProduct(product);
	}

	@GET
	@Path("/{code}")
	@Produces(MediaType.APPLICATION_JSON)
	public ProductDTO findProduct(@PathParam("code") String code) {
		return productService.findProductByCode(code);
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<ProductDTO> listProducts() {
		return productService.listAllProducts();
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public void updateProduct(ProductDTO product) {
		productService.updateProduct(product);
	}
}
