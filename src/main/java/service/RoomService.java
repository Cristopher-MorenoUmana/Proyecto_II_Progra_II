package service;

import models.RoomDto;
import dao.RoomDao;
import util.Response;

public class RoomService {
    
    private final RoomDao roomDao = new RoomDao();

    public RoomService() {
    }

    public Response createRoom(RoomDto dto) {

        return roomDao.addRoom(dto);
    }

    public Response updateRoom(RoomDto dto) {

        return roomDao.updateRoomInDb(dto);
    }

    public Response deleteRoom(Integer id) {

        return roomDao.deleteRoom(id);
    }

    public Response roomsList() {

        return roomDao.getRooms();
    }
}
