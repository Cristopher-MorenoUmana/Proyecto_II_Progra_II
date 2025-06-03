package service;

import models.PriceDto;
import dao.PriceDao;
import util.Response;

public class PriceService {
    
    private final PriceDao priceDao = new PriceDao();

    public PriceService() {
    }

    public Response createPrice(PriceDto dto) {

        return priceDao.addPrice(dto);
    }

    public Response updatePrice(PriceDto dto) {

        return priceDao.updatePriceInDb(dto);
    }

    public Response deletePrice(Integer id) {

        return priceDao.deletePrice(id);
    }

    public Response pricesList() {

        return priceDao.getPrices();
    }
}
