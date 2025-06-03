package service;

import models.ThemeDto;
import dao.ThemeDao;
import util.Response;

public class ThemeService {
    
    private final ThemeDao themeDao = new ThemeDao();

    public ThemeService() {
    }

    public Response createTheme(ThemeDto dto) {

        return themeDao.addTheme(dto);
    }

    public Response updateTheme(ThemeDto dto) {

        return themeDao.updateThemeInDb(dto);
    }

    public Response deleteTheme(Integer id) {

        return themeDao.deleteTheme(id);
    }

    public Response themesList() {

        return themeDao.getThemes();
    }
}
