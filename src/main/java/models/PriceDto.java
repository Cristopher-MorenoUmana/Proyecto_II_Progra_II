package models;

import java.util.Objects;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

public class PriceDto {

    private SimpleStringProperty id;
    private SimpleStringProperty type;
    private SimpleStringProperty amount;
    private ObjectProperty<Room> room;

    public PriceDto() {

        this.id = new SimpleStringProperty();
        this.type = new SimpleStringProperty();
        this.amount = new SimpleStringProperty();
        this.room = new SimpleObjectProperty<>();
    }

    public PriceDto(Price pPrice) {

        this();
        this.id.setValue(pPrice.getPriId().toString());
        this.type.setValue(pPrice.getPriType().toString());
        this.amount.setValue(pPrice.getPriAmount().toString());
        this.room.setValue(pPrice.getPriRoomId());
    }

    public String getType() {
        return type.get();
    }

    public void setType(String type) {
        this.type.set(type);
    }

    public String getAmount() {
        return amount.get();
    }

    public void setAmount(String amount) {
        this.amount.set(amount);
    }

    public Room getRoom() {
        return room.get();
    }

    public void setRoom(Room room) {
        this.room.set(room);
    }

    public String getId() {
        return id.get();
    }

    public void setId(String id) {
        this.id.set(id);
    }

    public SimpleStringProperty idProperty() {
        return id;
    }

    public SimpleStringProperty typeProperty() {
        return type;
    }

    public SimpleStringProperty amountProperty() {
        return amount;
    }

    public ObjectProperty<Room> roomProperty() {
        return room;
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

        final PriceDto other = (PriceDto) obj;

        return Objects.equals(this.getID(), other.getID());
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        sb.append("PriceDto{id=").append(this.id.get());
        sb.append(", tipo=").append(this.type.get());
        sb.append(", precio=").append(this.amount.get());
        sb.append(", sala=").append(this.room.get());
        sb.append('}');
        return sb.toString();
    }
}
