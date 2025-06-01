package models;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Objects;
import java.time.Instant;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

public class TicketDto {

    private SimpleStringProperty id;
    private ObjectProperty<Date> day;
    private SimpleStringProperty rooms;
    private SimpleStringProperty totalPrice;
    private SimpleStringProperty payState;
    private SimpleStringProperty quantity;

    public TicketDto() {

        this.id = new SimpleStringProperty();
        this.rooms = new SimpleStringProperty();
        this.totalPrice = new SimpleStringProperty();
        this.payState = new SimpleStringProperty();
        this.quantity = new SimpleStringProperty();
        this.day = new SimpleObjectProperty<>();
    }

    public TicketDto(Ticket pTicket) {

        this();
        this.id.setValue(pTicket.getTktId().toString());
        this.rooms.setValue(pTicket.getTktRooms());
        this.totalPrice.setValue(pTicket.getTktTotalPrice().toString());
        this.payState.setValue(pTicket.getTktPayState().toString());
        this.quantity.setValue(pTicket.getTktTicketsQuantity().toString());
        this.day.setValue(pTicket.getTktDay());
    }

    public Date getDay() {
        return day.get();
    }

    public String getRooms() {
        return rooms.get();
    }

    public String getTotalPrice() {
        return totalPrice.get();
    }

    public String getPayState() {
        return payState.get();
    }

    public String getQuantity() {
        return quantity.get();
    }

    public void setDay(Date day) {
        this.day.set(day);
    }

    public void setRooms(String rooms) {
        this.rooms.set(rooms);
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice.set(totalPrice);
    }

    public void setPayState(String payState) {
        this.payState.set(payState);
    }

    public void setQuantity(String quantity) {
        this.quantity.set(quantity);
    }

    public ObjectProperty<Date> dayProperty() {
        return day;
    }

    public SimpleStringProperty roomsProperty() {
        return rooms;
    }

    public SimpleStringProperty totalPriceProperty() {
        return totalPrice;
    }

    public SimpleStringProperty payStateProperty() {
        return payState;
    }

    public SimpleStringProperty quantityProperty() {
        return quantity;
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

    public LocalDate getDayAsLocalDate() {

        Date date = this.day.get();

        if (date == null) {
            return null;
        }

        Instant instant = Instant.ofEpochMilli(date.getTime());
        return instant.atZone(ZoneId.systemDefault()).toLocalDate();
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

        final TicketDto other = (TicketDto) obj;

        return Objects.equals(this.getID(), other.getID());
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        sb.append("TicketDto{id=").append(this.id.get());
        sb.append(", salas=").append(this.rooms.get());
        sb.append(", dia=").append(this.day.get());
        sb.append(", cantidad de tiquetes=").append(this.quantity.get());
        sb.append(", precio total=").append(this.totalPrice.get());
        sb.append(", estado del pago=").append(this.payState.get());
        sb.append('}');
        return sb.toString();
    }
}
