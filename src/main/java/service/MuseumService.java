package service;

import models.MuseumDto;
import dao.MuseumDao;
import util.Response;

public class MuseumService {

    private final MuseumDao museumDao = new MuseumDao();

    public MuseumService() {
    }

    public Response createMuseum(MuseumDto dto) {

        return museumDao.addMuseum(dto);
    }

    public Response updateMuseum(MuseumDto dto) {

        return museumDao.updateMuseumInDb(dto);
    }

    public Response deleteMuseum(Integer id) {

        return museumDao.deleteMuseum(id);
    }

    public Response museumsList() {

        return museumDao.getMuseums();
    }
}
