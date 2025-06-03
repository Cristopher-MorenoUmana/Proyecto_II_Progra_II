package dao;

import java.sql.SQLIntegrityConstraintViolationException;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Room;
import models.RoomDto;
import util.Response;
import util.EntityManagerHelper;

public class RoomDao {
   
    private final EntityManager em = EntityManagerHelper.getManager();
    private EntityTransaction et;
    
    public Response getRooms() {

        try {

            ObservableList<Room> rooms = FXCollections.observableArrayList(
                    em.createQuery("SELECT r FROM Room r", Room.class).getResultList());

            return new Response('S', "", "", "Salas", rooms);
        } catch (Exception ex) {

            Logger.getLogger(RoomDao.class.getName()).log(Level.SEVERE, "Error al obtener las colecciones", ex);

            return new Response('N', "Error al obtener las salas", "getRooms");
        }
    }

    public Response addRoom(RoomDto roomDto) {

        try {

            if (roomDto.getID() != null && roomDto.getID() > 0) {

                return new Response('N', "La sala ya existe", "addRoom ConflictException");
            }

            et = em.getTransaction();
            et.begin();

            Room room;

            room = new Room(roomDto);
            em.persist(room);

            et.commit();

            return new Response('S', "", "", "Sala", new RoomDto(room));

        } catch (Exception ex) {

            et.rollback();

            Logger.getLogger(CardDao.class.getName()).log(Level.SEVERE, "Error guardando la sala.", ex);

            return new Response('N', "Error guardando la sala.", "addRoom " + ex.getMessage());
        }
    }

    public Response updateRoomInDb(RoomDto roomDto) {

        try {

            if (roomDto.getID() == null || roomDto.getID() <= 0) {

                return new Response('N', "La sala no existe", "updateRoomInDb ConflictException");
            }

            et = em.getTransaction();
            et.begin();

            Room room;

            room = em.find(Room.class, roomDto.getID());

            if (room == null) {
                et.rollback();
                return new Response('N', "No se encontro la sala a modificar.", "updateRoomInDb NoResultException");
            }

            room.updateRoom(roomDto);
            room = em.merge(room);

            et.commit();

            return new Response('S', "", "", "Sala", new RoomDto(room));
        } catch (Exception ex) {

            et.rollback();

            Logger.getLogger(CardDao.class.getName()).log(Level.SEVERE, "Error guardando la sala.", ex);

            return new Response('N', "Error actualizando la sala.", "updateRoomInDb " + ex.getMessage());
        }
    }

    public Response deleteRoom(Integer pId) {

        try {
            et = em.getTransaction();
            et.begin();

            if (pId != null && pId > 0) {

                Room room = em.find(Room.class, pId);

                if (room == null) {
                    et.rollback();
                    return new Response('N', "No se encontro la sala a eliminar.", "deleteRoom NoResultException");
                }
                em.remove(room);
                et.commit();
            } else {
                et.rollback();
                return new Response('N', "La sala no existe", "deleteRoom InvalidID");
            }

            return new Response('S', "", "");
        } catch (Exception ex) {

            et.rollback();

            if (ex.getCause() != null && ex.getCause().getCause().getClass() == SQLIntegrityConstraintViolationException.class) {
                return new Response('N', "No se puede eliminar la sala porque tiene relaciones con otros registros.", "deleteRoom " + ex.getMessage());
            }

            Logger.getLogger(CardDao.class.getName()).log(Level.SEVERE, "Error eliminando la sala.", ex);

            return new Response('N', "Error eliminando la sala.", "deleteRoom " + ex.getMessage());
        }
    }
}
