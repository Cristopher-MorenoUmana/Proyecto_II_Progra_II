package dao;

import java.sql.SQLIntegrityConstraintViolationException;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Card;
import models.CardDto;
import util.Response;
import util.EntityManagerHelper;

public class CardDao {

    private final EntityManager em = EntityManagerHelper.getManager();
    private EntityTransaction et;

    public Response getCards() {

        try {

            ObservableList<Card> cards = FXCollections.observableArrayList(
                    em.createQuery("SELECT c FROM Card c", Card.class).getResultList());

            return new Response('S', "", "", "Tarjetas", cards);
        } catch (Exception ex) {

            Logger.getLogger(CardDao.class.getName()).log(Level.SEVERE, "Error al obtener las tarjetas", ex);

            return new Response('N', "Error al obtener las tarjetas", "getCards");
        }
    }

    public Response addCard(CardDto cardDto) {

        try {

            if (cardDto.getID() != null && cardDto.getID() > 0) {

                return new Response('N', "La tarjeta ya existe", "addCard ConflictException");
            }

            et = em.getTransaction();
            et.begin();

            Card card;

            card = new Card(cardDto);
            em.persist(card);

            et.commit();

            return new Response('S', "", "", "Tarjeta", new CardDto(card));
            
        } catch (Exception ex) {

            et.rollback();

            Logger.getLogger(CardDao.class.getName()).log(Level.SEVERE, "Error guardando la tarjeta.", ex);

            return new Response('N', "Error guardando la tarjeta.", "addCard " + ex.getMessage());
        }
    }

    public Response updateCardInDb(CardDto cardDto) {
        
        try {

            if (cardDto.getID() == null || cardDto.getID() <= 0) {

                return new Response('N', "La tarjeta no existe", "updateCard ConflictException");
            }

            et = em.getTransaction();
            et.begin();

            Card card;

            card = em.find(Card.class, cardDto.getID());

            if (card == null) {
                et.rollback();
                return new Response('N', "No se encontro la tarjeta a modificar.", "updateCardInDb NoResultException");
            }

            card.updateCard(cardDto);
            card = em.merge(card);

            et.commit();

            return new Response('S', "", "", "Tarjeta", new CardDto(card));
        } catch (Exception ex) {

            et.rollback();

            Logger.getLogger(CardDao.class.getName()).log(Level.SEVERE, "Error guardando la tarjeta.", ex);

            return new Response('N', "Error actualizando la tarjeta.", "updateCardInDb " + ex.getMessage());
        }
    }

    public Response deleteCard(Integer pId) {

        try {
            et = em.getTransaction();
            et.begin();

            if (pId != null && pId > 0) {

                Card card = em.find(Card.class, pId);

                if (card == null) {
                    et.rollback();
                    return new Response('N', "No se encontro la tarjeta a eliminar.", "deleteCard NoResultException");
                }
                em.remove(card);
                et.commit();
            } else {
                et.rollback();
                return new Response('N', "La tarjeta no existe", "deleteUser InvalidID");
            }

            return new Response('S', "", "");
        } catch (Exception ex) {

            et.rollback();

            if (ex.getCause() != null && ex.getCause().getCause().getClass() == SQLIntegrityConstraintViolationException.class) {
                return new Response('N', "No se puede la tarjeta porque tiene relaciones con otros registros.", "deleteCard " + ex.getMessage());
            }

            Logger.getLogger(CardDao.class.getName()).log(Level.SEVERE, "Error eliminando la tarjeta.", ex);

            return new Response('N', "Error eliminando la tarjeta.", "deleteCard " + ex.getMessage());
        }
    }
}
