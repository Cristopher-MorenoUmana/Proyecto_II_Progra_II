package dao;

import java.sql.SQLIntegrityConstraintViolationException;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Museum;
import models.MuseumDto;
import util.Response;
import util.EntityManagerHelper;

public class MuseumDao {
    
    private final EntityManager em = EntityManagerHelper.getManager();
    private EntityTransaction et;
    
    public Response getMuseums() {

        try {

            ObservableList<Museum> museums = FXCollections.observableArrayList(
                    em.createQuery("SELECT m FROM Museum m", Museum.class).getResultList());

            return new Response('S', "", "", "Museos", museums);
        } catch (Exception ex) {

            Logger.getLogger(MuseumDao.class.getName()).log(Level.SEVERE, "Error al obtener las colecciones", ex);

            return new Response('N', "Error al obtener los museos", "getMuseums");
        }
    }

    public Response addMuseum(MuseumDto museumDto) {

        try {

            if (museumDto.getID() != null && museumDto.getID() > 0) {

                return new Response('N', "El museo ya existe", "addMuseum ConflictException");
            }

            et = em.getTransaction();
            et.begin();

            Museum museum;

            museum = new Museum(museumDto);
            em.persist(museum);

            et.commit();

            return new Response('S', "", "", "Museo", new MuseumDto(museum));

        } catch (Exception ex) {

            et.rollback();

            Logger.getLogger(CardDao.class.getName()).log(Level.SEVERE, "Error guardando el museo.", ex);

            return new Response('N', "Error guardando el museo.", "addMuseum " + ex.getMessage());
        }
    }

    public Response updateMuseumInDb(MuseumDto museumDto) {

        try {

            if (museumDto.getID() == null || museumDto.getID() <= 0) {

                return new Response('N', "El museo no existe", "updateMuseumInDb ConflictException");
            }

            et = em.getTransaction();
            et.begin();

            Museum museum;

            museum = em.find(Museum.class, museumDto.getID());

            if (museum == null) {
                et.rollback();
                return new Response('N', "No se encontro el museo a modificar.", "updateMuseum NoResultException");
            }

            museum.updateMuseum(museumDto);
            museum = em.merge(museum);

            et.commit();

            return new Response('S', "", "", "Museo", new MuseumDto(museum));
        } catch (Exception ex) {

            et.rollback();

            Logger.getLogger(CardDao.class.getName()).log(Level.SEVERE, "Error guardando el museo.", ex);

            return new Response('N', "Error actualizando el museo.", "updateMuseumInDb " + ex.getMessage());
        }
    }

    public Response deleteMuseum(Integer pId) {

        try {
            et = em.getTransaction();
            et.begin();

            if (pId != null && pId > 0) {

                Museum museum = em.find(Museum.class, pId);

                if (museum == null) {
                    et.rollback();
                    return new Response('N', "No se encontro el museo a eliminar.", "deleteMuseum NoResultException");
                }
                em.remove(museum);
                et.commit();
            } else {
                et.rollback();
                return new Response('N', "El museo no existe", "deleteMuseum InvalidID");
            }

            return new Response('S', "", "");
        } catch (Exception ex) {

            et.rollback();

            if (ex.getCause() != null && ex.getCause().getCause().getClass() == SQLIntegrityConstraintViolationException.class) {
                return new Response('N', "No se puede eliminar el museo porque tiene relaciones con otros registros.", "deleteMuseum " + ex.getMessage());
            }

            Logger.getLogger(CardDao.class.getName()).log(Level.SEVERE, "Error eliminando el museo.", ex);

            return new Response('N', "Error eliminando el museo.", "deleteMuseum " + ex.getMessage());
        }
    }
}
