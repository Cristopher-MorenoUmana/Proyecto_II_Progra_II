package service;

import models.CardDto;
import dao.CardDao;
import util.Response;

public class CardService {
    
    private final CardDao cardDao = new CardDao();

    public CardService(){  
    }
    
    public Response createCard(CardDto dto) {
        
        return cardDao.addCard(dto);
    }

    public Response updateCard(CardDto dto) {
        
        return cardDao.updateCardInDb(dto);
    }

    public Response deleteCard(Integer id) {
        
        return cardDao.deleteCard(id);
    }

    public Response cardsList() {
        
        return cardDao.getCards();
    }
}
