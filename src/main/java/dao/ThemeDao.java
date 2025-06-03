package dao;

import java.sql.SQLIntegrityConstraintViolationException;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Theme;
import models.ThemeDto;
import util.Response;
import util.EntityManagerHelper;

public class ThemeDao {

    private final EntityManager em = EntityManagerHelper.getManager();
    private EntityTransaction et;

    public Response getThemes() {

        try {

            ObservableList<Theme> themes = FXCollections.observableArrayList(
                    em.createQuery("SELECT t FROM Theme t", Theme.class).getResultList());

            return new Response('S', "", "", "Tematicas", themes);
        } catch (Exception ex) {

            Logger.getLogger(ThemeDao.class.getName()).log(Level.SEVERE, "Error al obtener las tematica", ex);

            return new Response('N', "Error al obtener las tematicas", "getThemes");
        }
    }

    public Response addTheme(ThemeDto themeDto) {

        try {

            if (themeDto.getID() != null && themeDto.getID() > 0) {

                return new Response('N', "La tematica ya existe", "addTheme ConflictException");
            }

            et = em.getTransaction();
            et.begin();

            Theme theme;

            theme = new Theme(themeDto);
            em.persist(theme);

            et.commit();

            return new Response('S', "", "", "Tematica", new ThemeDto(theme));

        } catch (Exception ex) {

            et.rollback();

            Logger.getLogger(CardDao.class.getName()).log(Level.SEVERE, "Error guardando la tematica.", ex);

            return new Response('N', "Error guardando la tematica.", "addTheme " + ex.getMessage());
        }
    }

    public Response updateThemeInDb(ThemeDto themeDto) {

        try {

            if (themeDto.getID() == null || themeDto.getID() <= 0) {

                return new Response('N', "La tematica no existe", "updateThemeInDb ConflictException");
            }

            et = em.getTransaction();
            et.begin();

            Theme theme;

            theme = em.find(Theme.class, themeDto.getID());

            if (theme == null) {
                et.rollback();
                return new Response('N', "No se encontro la tematica a modificar.", "updateThemeInDb NoResultException");
            }

            theme.updateTheme(themeDto);
            theme = em.merge(theme);

            et.commit();

            return new Response('S', "", "", "Tematica", new ThemeDto(theme));
        } catch (Exception ex) {

            et.rollback();

            Logger.getLogger(CardDao.class.getName()).log(Level.SEVERE, "Error guardando la tematica.", ex);

            return new Response('N', "Error actualizando la tematica.", "updateThemeInDb " + ex.getMessage());
        }
    }

    public Response deleteTheme(Integer pId) {

        try {
            et = em.getTransaction();
            et.begin();

            if (pId != null && pId > 0) {

                Theme theme = em.find(Theme.class, pId);

                if (theme == null) {
                    et.rollback();
                    return new Response('N', "No se encontro la tematica a eliminar.", "deleteTheme NoResultException");
                }
                em.remove(theme);
                et.commit();
            } else {
                et.rollback();
                return new Response('N', "La tematica no existe", "deleteTheme InvalidID");
            }

            return new Response('S', "", "");
        } catch (Exception ex) {

            et.rollback();

            if (ex.getCause() != null && ex.getCause().getCause().getClass() == SQLIntegrityConstraintViolationException.class) {
                return new Response('N', "No se puede eliminar la tematica porque tiene relaciones con otros registros.", "deleteTheme " + ex.getMessage());
            }

            Logger.getLogger(CardDao.class.getName()).log(Level.SEVERE, "Error eliminando la tematica.", ex);

            return new Response('N', "Error eliminando la tematica.", "deleteTheme " + ex.getMessage());
        }
    }
}
