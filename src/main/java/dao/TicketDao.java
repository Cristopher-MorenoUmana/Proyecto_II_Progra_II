package dao;

import java.sql.SQLIntegrityConstraintViolationException;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Ticket;
import models.TicketDto;
import util.Response;
import util.EntityManagerHelper;

public class TicketDao {
    
    private final EntityManager em = EntityManagerHelper.getManager();
    private EntityTransaction et;
    
    public Response getTickets() {

        try {

            ObservableList<Ticket> tickets = FXCollections.observableArrayList(
                    em.createQuery("SELECT t FROM Ticket t", Ticket.class).getResultList());

            return new Response('S', "", "", "Tiquetes", tickets);
        } catch (Exception ex) {

            Logger.getLogger(TicketDao.class.getName()).log(Level.SEVERE, "Error al obtener las colecciones", ex);

            return new Response('N', "Error al obtener los tiquetes", "getTickets");
        }
    }

    public Response addTicket(TicketDto ticketDto) {

        try {

            if (ticketDto.getID() != null && ticketDto.getID() > 0) {

                return new Response('N', "La eticket ya existe", "addTicket ConflictException");
            }

            et = em.getTransaction();
            et.begin();

            Ticket ticket;

            ticket = new Ticket(ticketDto);
            em.persist(ticket);

            et.commit();

            return new Response('S', "", "", "Tiquete", new TicketDto(ticket));

        } catch (Exception ex) {

            et.rollback();

            Logger.getLogger(CardDao.class.getName()).log(Level.SEVERE, "Error guardando el tiquete.", ex);

            return new Response('N', "Error guardando el tiquete.", "addTicket " + ex.getMessage());
        }
    }

    public Response updateTicketInDb(TicketDto ticketDto) {

        try {

            if (ticketDto.getID() == null || ticketDto.getID() <= 0) {

                return new Response('N', "La eticket no existe", "updateTicketInDb ConflictException");
            }

            et = em.getTransaction();
            et.begin();

            Ticket ticket;

            ticket = em.find(Ticket.class, ticketDto.getID());

            if (ticket == null) {
                et.rollback();
                return new Response('N', "No se encontro el tiquete a modificar.", "updateTicketInDb NoResultException");
            }

            ticket.updateTicket(ticketDto);
            ticket = em.merge(ticket);

            et.commit();

            return new Response('S', "", "", "Tiquete", new TicketDto(ticket));
        } catch (Exception ex) {

            et.rollback();

            Logger.getLogger(CardDao.class.getName()).log(Level.SEVERE, "Error guardando el tiquete.", ex);

            return new Response('N', "Error actualizando el tiquete.", "updateTicketInDb " + ex.getMessage());
        }
    }

    public Response deleteTicket(Integer pId) {

        try {
            et = em.getTransaction();
            et.begin();

            if (pId != null && pId > 0) {

                Ticket ticket = em.find(Ticket.class, pId);

                if (ticket == null) {
                    et.rollback();
                    return new Response('N', "No se encontro el tiquete a eliminar.", "deleteTicket NoResultException");
                }
                em.remove(ticket);
                et.commit();
            } else {
                et.rollback();
                return new Response('N', "El tiquete no existe", "deleteTicket InvalidID");
            }

            return new Response('S', "", "");
        } catch (Exception ex) {

            et.rollback();

            if (ex.getCause() != null && ex.getCause().getCause().getClass() == SQLIntegrityConstraintViolationException.class) {
                return new Response('N', "No se puede eliminar el tiquete porque tiene relaciones con otros registros.", "deleteTicket " + ex.getMessage());
            }

            Logger.getLogger(CardDao.class.getName()).log(Level.SEVERE, "Error eliminando el tiquete.", ex);

            return new Response('N', "Error eliminando el tiquete.", "deleteTicket " + ex.getMessage());
        }
    }
}
