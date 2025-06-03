package dao;

import java.sql.SQLIntegrityConstraintViolationException;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Price;
import models.PriceDto;
import util.Response;
import util.EntityManagerHelper;

public class PriceDao {

    private final EntityManager em = EntityManagerHelper.getManager();
    private EntityTransaction et;
    
    public Response getPrices() {

        try {

            ObservableList<Price> prices = FXCollections.observableArrayList(
                    em.createQuery("SELECT p FROM Price p", Price.class).getResultList());

            return new Response('S', "", "", "Precios", prices);
        } catch (Exception ex) {

            Logger.getLogger(PriceDao.class.getName()).log(Level.SEVERE, "Error al obtener las colecciones", ex);

            return new Response('N', "Error al obtener los precios", "getPrices");
        }
    }

    public Response addPrice(PriceDto priceDto) {

        try {

            if (priceDto.getID() != null && priceDto.getID() > 0) {

                return new Response('N', "El precio ya existe", "addPrice ConflictException");
            }

            et = em.getTransaction();
            et.begin();

            Price price;

            price = new Price(priceDto);
            em.persist(price);

            et.commit();

            return new Response('S', "", "", "Precio", new PriceDto(price));

        } catch (Exception ex) {

            et.rollback();

            Logger.getLogger(CardDao.class.getName()).log(Level.SEVERE, "Error guardando el precio.", ex);

            return new Response('N', "Error guardando el precio.", "addPrice " + ex.getMessage());
        }
    }

    public Response updatePriceInDb(PriceDto priceDto) {

        try {

            if (priceDto.getID() == null || priceDto.getID() <= 0) {

                return new Response('N', "El precio no existe", "updatePriceInDb ConflictException");
            }

            et = em.getTransaction();
            et.begin();

            Price price;

            price = em.find(Price.class, priceDto.getID());

            if (price == null) {
                et.rollback();
                return new Response('N', "No se encontro el precio a modificar.", "updatePrice NoResultException");
            }

            price.updatePrice(priceDto);
            price = em.merge(price);

            et.commit();

            return new Response('S', "", "", "Precio", new PriceDto(price));
        } catch (Exception ex) {

            et.rollback();

            Logger.getLogger(CardDao.class.getName()).log(Level.SEVERE, "Error guardando el precio.", ex);

            return new Response('N', "Error actualizando el precio.", "updatePriceInDb " + ex.getMessage());
        }
    }

    public Response deletePrice(Integer pId) {

        try {
            et = em.getTransaction();
            et.begin();

            if (pId != null && pId > 0) {

                Price price = em.find(Price.class, pId);

                if (price == null) {
                    et.rollback();
                    return new Response('N', "No se encontro el precio a eliminar.", "deletePrice NoResultException");
                }
                em.remove(price);
                et.commit();
            } else {
                et.rollback();
                return new Response('N', "El precio no existe", "deletePrice InvalidID");
            }

            return new Response('S', "", "");
        } catch (Exception ex) {

            et.rollback();

            if (ex.getCause() != null && ex.getCause().getCause().getClass() == SQLIntegrityConstraintViolationException.class) {
                return new Response('N', "No se puede eliminar el precio porque tiene relaciones con otros registros.", "deletePrice " + ex.getMessage());
            }

            Logger.getLogger(CardDao.class.getName()).log(Level.SEVERE, "Error eliminando el precio.", ex);

            return new Response('N', "Error eliminando el precio.", "deletePrice " + ex.getMessage());
        }
    }
}
