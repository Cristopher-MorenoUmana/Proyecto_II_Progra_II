package dao;

import java.sql.SQLIntegrityConstraintViolationException;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Collection;
import models.CollectionDto;
import util.Response;
import util.EntityManagerHelper;

public class CollectionDao {

    private final EntityManager em = EntityManagerHelper.getManager();
    private EntityTransaction et;

    public Response getCollections() {

        try {

            ObservableList<Collection> collections = FXCollections.observableArrayList(
                    em.createQuery("SELECT c FROM Collection c", Collection.class).getResultList());

            return new Response('S', "", "", "Colecciones", collections);
        } catch (Exception ex) {

            Logger.getLogger(CollectionDao.class.getName()).log(Level.SEVERE, "Error al obtener las colecciones", ex);

            return new Response('N', "Error al obtener las colecciones", "getCollections");
        }
    }

    public Response addCollection(CollectionDto collectionDto) {

        try {

            if (collectionDto.getID() != null && collectionDto.getID() > 0) {

                return new Response('N', "La coleccion ya existe", "addCollection ConflictException");
            }

            et = em.getTransaction();
            et.begin();

            Collection collection;

            collection = new Collection(collectionDto);
            em.persist(collection);

            et.commit();

            return new Response('S', "", "", "Coleccion", new CollectionDto(collection));

        } catch (Exception ex) {

            et.rollback();

            Logger.getLogger(CardDao.class.getName()).log(Level.SEVERE, "Error guardando la coleccion.", ex);

            return new Response('N', "Error guardando la coleccion.", "addCollection " + ex.getMessage());
        }
    }

    public Response updateCollectionInDb(CollectionDto collectionDto) {

        try {

            if (collectionDto.getID() == null || collectionDto.getID() <= 0) {

                return new Response('N', "La coleccion no existe", "updateCollectionInDb ConflictException");
            }

            et = em.getTransaction();
            et.begin();

            Collection collection;

            collection = em.find(Collection.class, collectionDto.getID());

            if (collection == null) {
                et.rollback();
                return new Response('N', "No se encontro la coleccion a modificar.", "updateCollection NoResultException");
            }

            collection.updateCollection(collectionDto);
            collection = em.merge(collection);

            et.commit();

            return new Response('S', "", "", "Coleccion", new CollectionDto(collection));
        } catch (Exception ex) {

            et.rollback();

            Logger.getLogger(CardDao.class.getName()).log(Level.SEVERE, "Error guardando la coleccion.", ex);

            return new Response('N', "Error actualizando la coleccion.", "updateCollectionInDb " + ex.getMessage());
        }
    }

    public Response deleteCollection(Integer pId) {

        try {
            et = em.getTransaction();
            et.begin();

            if (pId != null && pId > 0) {

                Collection collection = em.find(Collection.class, pId);

                if (collection == null) {
                    et.rollback();
                    return new Response('N', "No se encontro la coleccion a eliminar.", "deleteCollection NoResultException");
                }
                em.remove(collection);
                et.commit();
            } else {
                et.rollback();
                return new Response('N', "La coleccion no existe", "deleteCollection InvalidID");
            }

            return new Response('S', "", "");
        } catch (Exception ex) {

            et.rollback();

            if (ex.getCause() != null && ex.getCause().getCause().getClass() == SQLIntegrityConstraintViolationException.class) {
                return new Response('N', "No se puede eliminar la coleccion porque tiene relaciones con otros registros.", "deleteCollection " + ex.getMessage());
            }

            Logger.getLogger(CardDao.class.getName()).log(Level.SEVERE, "Error eliminando la coleccion.", ex);

            return new Response('N', "Error eliminando la coleccion.", "deleteCollection " + ex.getMessage());
        }
    }
}
