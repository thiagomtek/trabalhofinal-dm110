package br.inatel.dm110.ejb;

import java.util.List;
import java.util.stream.Collectors;

import br.inatel.dm110.api.dto.ProductDTO;
import br.inatel.dm110.api.services.ProductService;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.jms.JMSContext;
import jakarta.jms.Queue;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Stateless
public class ProductServiceImpl implements ProductService {

    @PersistenceContext(unitName = "work")
    private EntityManager em;

    @Inject
    private JMSContext jmsContext;

    @Inject
    private Queue auditQueue;

    @Override
    public void createProduct(ProductDTO product) {
        ProductEntity entity = new ProductEntity();
        entity.setCode(product.getCode());
        entity.setName(product.getName());
        entity.setDescription(product.getDescription());
        entity.setPrice(product.getPrice());
        entity.setCategory(product.getCategory());

        em.persist(entity);

        sendAuditMessage(product.getCode(), "create");
    }

    @Override
    public ProductDTO findProductByCode(String code) {
        ProductEntity entity = em.find(ProductEntity.class, code);
        if (entity == null) return null;

        ProductDTO dto = new ProductDTO();
        dto.setCode(entity.getCode());
        dto.setName(entity.getName());
        dto.setDescription(entity.getDescription());
        dto.setPrice(entity.getPrice());
        dto.setCategory(entity.getCategory());

        return dto;
    }

    @Override
    public List<ProductDTO> listAllProducts() {
        List<ProductEntity> entities = em.createQuery("FROM ProductEntity", ProductEntity.class).getResultList();

        return entities.stream().map(entity -> {
            ProductDTO dto = new ProductDTO();
            dto.setCode(entity.getCode());
            dto.setName(entity.getName());
            dto.setDescription(entity.getDescription());
            dto.setPrice(entity.getPrice());
            dto.setCategory(entity.getCategory());
            return dto;
        }).collect(Collectors.toList());
    }

    @Override
    public void updateProduct(ProductDTO product) {
        ProductEntity entity = em.find(ProductEntity.class, product.getCode());
        if (entity != null) {
            entity.setName(product.getName());
            entity.setDescription(product.getDescription());
            entity.setPrice(product.getPrice());
            entity.setCategory(product.getCategory());
            em.merge(entity);

            sendAuditMessage(product.getCode(), "update");
        }
    }

    private void sendAuditMessage(String code, String operation) {
        String message = code + ";" + operation;
        jmsContext.createProducer().send(auditQueue, message);
    }
}
