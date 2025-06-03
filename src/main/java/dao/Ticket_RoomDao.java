package dao;

import java.sql.SQLIntegrityConstraintViolationException;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Ticket_Room;
import models.Ticket_RoomDto;
import util.Response;
import util.EntityManagerHelper;

public class Ticket_RoomDao {

   private final EntityManager em = EntityManagerHelper.getManager();
    private EntityTransaction et;
    
    public Response getTicket_Rooms() {

        try {

            ObservableList<Ticket_Room> ticket_Rooms = FXCollections.observableArrayList(
                    em.createQuery("SELECT t FROM Ticket_Room t", Ticket_Room.class).getResultList());

            return new Response('S', "", "", "Tiquetes", ticket_Rooms);
        } catch (Exception ex) {

            Logger.getLogger(Ticket_RoomDao.class.getName()).log(Level.SEVERE, "Error al obtener las tiquetes", ex);

            return new Response('N', "Error al obtener los tiquetes", "getTicket_Rooms");
        }
    }

    public Response addTicket_Room(Ticket_RoomDto ticket_RoomDto) {

        try {

            if (ticket_RoomDto.getID() != null && ticket_RoomDto.getID() > 0) {

                return new Response('N', "La eticket_Room ya existe", "addTicket_Room ConflictException");
            }

            et = em.getTransaction();
            et.begin();

            Ticket_Room ticket_Room;

            ticket_Room = new Ticket_Room(ticket_RoomDto);
            em.persist(ticket_Room);

            et.commit();

            return new Response('S', "", "", "Tiquete", new Ticket_RoomDto(ticket_Room));

        } catch (Exception ex) {

            et.rollback();

            Logger.getLogger(CardDao.class.getName()).log(Level.SEVERE, "Error guardando el tiquete.", ex);

            return new Response('N', "Error guardando el tiquete.", "addTicket_Room " + ex.getMessage());
        }
    }

    public Response updateTicket_RoomInDb(Ticket_RoomDto ticket_RoomDto) {

        try {

            if (ticket_RoomDto.getID() == null || ticket_RoomDto.getID() <= 0) {

                return new Response('N', "La eticket_Room no existe", "updateTicket_RoomInDb ConflictException");
            }

            et = em.getTransaction();
            et.begin();

            Ticket_Room ticket_Room;

            ticket_Room = em.find(Ticket_Room.class, ticket_RoomDto.getID());

            if (ticket_Room == null) {
                et.rollback();
                return new Response('N', "No se encontro el tiquete a modificar.", "updateTicket_RoomInDb NoResultException");
            }

            ticket_Room.updateTicket_Room(ticket_RoomDto);
            ticket_Room = em.merge(ticket_Room);

            et.commit();

            return new Response('S', "", "", "Tiquete", new Ticket_RoomDto(ticket_Room));
        } catch (Exception ex) {

            et.rollback();

            Logger.getLogger(CardDao.class.getName()).log(Level.SEVERE, "Error guardando el tiquete.", ex);

            return new Response('N', "Error actualizando el tiquete.", "updateTicket_RoomInDb " + ex.getMessage());
        }
    }

    public Response deleteTicket_Room(Integer pId) {

        try {
            et = em.getTransaction();
            et.begin();

            if (pId != null && pId > 0) {

                Ticket_Room ticket_Room = em.find(Ticket_Room.class, pId);

                if (ticket_Room == null) {
                    et.rollback();
                    return new Response('N', "No se encontro el tiquete a eliminar.", "deleteTicket_Room NoResultException");
                }
                em.remove(ticket_Room);
                et.commit();
            } else {
                et.rollback();
                return new Response('N', "El tiquete no existe", "deleteTicket_Room InvalidID");
            }

            return new Response('S', "", "");
        } catch (Exception ex) {

            et.rollback();

            if (ex.getCause() != null && ex.getCause().getCause().getClass() == SQLIntegrityConstraintViolationException.class) {
                return new Response('N', "No se puede eliminar el tiquete porque tiene relaciones con otros registros.", "deleteTicket_Room " + ex.getMessage());
            }

            Logger.getLogger(CardDao.class.getName()).log(Level.SEVERE, "Error eliminando el tiquete.", ex);

            return new Response('N', "Error eliminando el tiquete.", "deleteTicket_Room " + ex.getMessage());
        }
    }
}
