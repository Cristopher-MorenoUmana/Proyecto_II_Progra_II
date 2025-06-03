package dao;

import java.sql.SQLIntegrityConstraintViolationException;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Specie;
import models.SpecieDto;
import util.Response;
import util.EntityManagerHelper;

public class SpecieDao {
    
    private final EntityManager em = EntityManagerHelper.getManager();
    private EntityTransaction et;
    
    public Response getSpecies() {

        try {

            ObservableList<Specie> species = FXCollections.observableArrayList(
                    em.createQuery("SELECT s FROM Specie s", Specie.class).getResultList());

            return new Response('S', "", "", "Especies", species);
        } catch (Exception ex) {

            Logger.getLogger(SpecieDao.class.getName()).log(Level.SEVERE, "Error al obtener las especies", ex);

            return new Response('N', "Error al obtener las especies", "getSpecies");
        }
    }

    public Response addSpecie(SpecieDto specieDto) {

        try {

            if (specieDto.getID() != null && specieDto.getID() > 0) {

                return new Response('N', "La especie ya existe", "addSpecie ConflictException");
            }

            et = em.getTransaction();
            et.begin();

            Specie specie;

            specie = new Specie(specieDto);
            em.persist(specie);

            et.commit();

            return new Response('S', "", "", "Especie", new SpecieDto(specie));

        } catch (Exception ex) {

            et.rollback();

            Logger.getLogger(CardDao.class.getName()).log(Level.SEVERE, "Error guardando la especie.", ex);

            return new Response('N', "Error guardando la especie.", "addSpecie " + ex.getMessage());
        }
    }

    public Response updateSpecieInDb(SpecieDto specieDto) {

        try {

            if (specieDto.getID() == null || specieDto.getID() <= 0) {

                return new Response('N', "La especie no existe", "updateSpecieInDb ConflictException");
            }

            et = em.getTransaction();
            et.begin();

            Specie specie;

            specie = em.find(Specie.class, specieDto.getID());

            if (specie == null) {
                et.rollback();
                return new Response('N', "No se encontro la especie a modificar.", "updateSpecieInDb NoResultException");
            }

            specie.updateSpecie(specieDto);
            specie = em.merge(specie);

            et.commit();

            return new Response('S', "", "", "Especie", new SpecieDto(specie));
        } catch (Exception ex) {

            et.rollback();

            Logger.getLogger(CardDao.class.getName()).log(Level.SEVERE, "Error guardando la especie.", ex);

            return new Response('N', "Error actualizando la especie.", "updateSpecieInDb " + ex.getMessage());
        }
    }

    public Response deleteSpecie(Integer pId) {

        try {
            et = em.getTransaction();
            et.begin();

            if (pId != null && pId > 0) {

                Specie specie = em.find(Specie.class, pId);

                if (specie == null) {
                    et.rollback();
                    return new Response('N', "No se encontro la especie a eliminar.", "deleteSpecie NoResultException");
                }
                em.remove(specie);
                et.commit();
            } else {
                et.rollback();
                return new Response('N', "La especie no existe", "deleteSpecie InvalidID");
            }

            return new Response('S', "", "");
        } catch (Exception ex) {

            et.rollback();

            if (ex.getCause() != null && ex.getCause().getCause().getClass() == SQLIntegrityConstraintViolationException.class) {
                return new Response('N', "No se puede eliminar la especie porque tiene relaciones con otros registros.", "deleteSpecie " + ex.getMessage());
            }

            Logger.getLogger(CardDao.class.getName()).log(Level.SEVERE, "Error eliminando la especie.", ex);

            return new Response('N', "Error eliminando la especie.", "deleteSpecie " + ex.getMessage());
        }
    }
}
