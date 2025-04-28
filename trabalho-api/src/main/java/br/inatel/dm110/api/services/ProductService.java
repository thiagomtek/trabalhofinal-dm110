package br.inatel.dm110.api.services;

import java.util.List;

import br.inatel.dm110.api.dto.ProductDTO;

public interface ProductService {
	void createProduct(ProductDTO product);

	ProductDTO findProductByCode(String code);

	List<ProductDTO> listAllProducts();

	void updateProduct(ProductDTO product);
}
