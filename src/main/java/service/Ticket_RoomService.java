package service;

import models.Ticket_RoomDto;
import dao.Ticket_RoomDao;
import util.Response;

public class Ticket_RoomService {
    
    private final Ticket_RoomDao ticket_RoomDao = new Ticket_RoomDao();

    public Ticket_RoomService() {
    }

    public Response createTicket_Room(Ticket_RoomDto dto) {

        return ticket_RoomDao.addTicket_Room(dto);
    }

    public Response updateTicket_Room(Ticket_RoomDto dto) {

        return ticket_RoomDao.updateTicket_RoomInDb(dto);
    }

    public Response deleteTicket_Room(Integer id) {

        return ticket_RoomDao.deleteTicket_Room(id);
    }

    public Response ticket_RoomsList() {

        return ticket_RoomDao.getTicket_Rooms();
    }
}
