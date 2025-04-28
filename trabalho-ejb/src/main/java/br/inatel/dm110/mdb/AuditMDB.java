package br.inatel.dm110.mdb;

import br.inatel.dm110.ejb.AuditEntity;

import jakarta.ejb.ActivationConfigProperty;
import jakarta.ejb.MessageDriven;
import jakarta.jms.Message;
import jakarta.jms.MessageListener;
import jakarta.jms.TextMessage;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.time.LocalDateTime;

@MessageDriven(activationConfig = {
    @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "jms/auditQueue"),
    @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "jakarta.jms.Queue")
})
public class AuditMDB implements MessageListener {

    @PersistenceContext(unitName = "work")
    private EntityManager em;

    @Override
    public void onMessage(Message message) {
        try {
            if (message instanceof TextMessage) {
                String[] parts = ((TextMessage) message).getText().split(";");

                AuditEntity audit = new AuditEntity();
                audit.setRegisterCode(parts[0]);
                audit.setOperation(parts[1]);
                audit.setTimestamp(LocalDateTime.now());

                em.persist(audit);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
