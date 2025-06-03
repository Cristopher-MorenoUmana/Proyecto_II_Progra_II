package service;

import models.SpecieDto;
import dao.SpecieDao;
import util.Response;

public class SpecieService {
 
    private final SpecieDao specieDao = new SpecieDao();

    public SpecieService() {
    }

    public Response createSpecie(SpecieDto dto) {

        return specieDao.addSpecie(dto);
    }

    public Response updateSpecie(SpecieDto dto) {

        return specieDao.updateSpecieInDb(dto);
    }

    public Response deleteSpecie(Integer id) {

        return specieDao.deleteSpecie(id);
    }

    public Response speciesList() {

        return specieDao.getSpecies();
    }
}
