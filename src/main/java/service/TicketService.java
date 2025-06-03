package service;

import models.TicketDto;
import dao.TicketDao;
import util.Response;

public class TicketService {
    
    private final TicketDao ticketDao = new TicketDao();

    public TicketService() {
    }

    public Response createTicket(TicketDto dto) {

        return ticketDao.addTicket(dto);
    }

    public Response updateTicket(TicketDto dto) {

        return ticketDao.updateTicketInDb(dto);
    }

    public Response deleteTicket(Integer id) {

        return ticketDao.deleteTicket(id);
    }

    public Response ticketsList() {

        return ticketDao.getTickets();
    }
}
