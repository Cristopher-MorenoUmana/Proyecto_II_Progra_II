package models;

import java.util.Objects;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

public class Ticket_RoomDto {

    private SimpleStringProperty id;
    private ObjectProperty<Room> room;
    private ObjectProperty<Ticket> ticket;

    public Ticket_RoomDto() {

        this.id = new SimpleStringProperty();
        this.room = new SimpleObjectProperty<>();
        this.ticket = new SimpleObjectProperty<>();
    }

    public Ticket_RoomDto(Ticket_Room linkedTicket) {

        this();
        this.id.setValue(linkedTicket.getTkrId().toString());
        this.room.setValue(linkedTicket.getTkrRoomId());
        this.ticket.setValue(linkedTicket.getTkrTicketId());
    }

    public Room getRoom() {
        return room.get();
    }

    public Ticket getTicket() {
        return ticket.get();
    }

    public void setRoom(Room room) {
        this.room.set(room);
    }

    public void setTicket(Ticket ticket) {
        this.ticket.set(ticket);
    }

    public ObjectProperty<Room> roomProperty() {
        return room;
    }

    public ObjectProperty<Ticket> ticketProperty() {
        return ticket;
    }

    public Integer getID() {

        if (id.get() == null || id.get().isEmpty()) {
            return null;
        }
        try {
            return Integer.valueOf(id.get());
        } catch (NumberFormatException e) {
            return null;
        }
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + Objects.hashCode(this.getID());
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }

        final Ticket_RoomDto other = (Ticket_RoomDto) obj;

        return Objects.equals(this.getID(), other.getID());
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        sb.append("Ticket_Room{id=").append(this.id.get());
        sb.append(", tiquete=").append(this.ticket.get());
        sb.append(", sala=").append(this.room.get());
        sb.append('}');
        return sb.toString();
    }
}