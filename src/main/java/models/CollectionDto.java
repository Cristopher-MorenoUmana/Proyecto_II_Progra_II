package models;

import java.util.Objects;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

public class CollectionDto {

    private SimpleStringProperty id;
    private SimpleStringProperty name;
    private SimpleStringProperty era;
    private SimpleStringProperty description;
    private ObjectProperty<Room> room;

    public CollectionDto() {

        this.id = new SimpleStringProperty();
        this.name = new SimpleStringProperty();
        this.era = new SimpleStringProperty();
        this.description = new SimpleStringProperty();
        this.room = new SimpleObjectProperty<>();
    }

    public CollectionDto(Collection pCollection) {

        this();
        this.id.setValue(pCollection.getCltId().toString());
        this.era.setValue(pCollection.getCltEra());
        this.name.setValue(pCollection.getCltName());
        this.description.setValue(pCollection.getCltDescription());
        this.room.setValue(pCollection.getCltRoomId());
    }

    public void setId(String id) {
        this.id.set(id);
    }

    public SimpleStringProperty idProperty() {
        return id;
    }

    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public String getEra() {
        return era.get();
    }

    public void setEra(String era) {
        this.era.set(era);
    }

    public SimpleStringProperty eraProperty() {
        return era;
    }

    public String getDescription() {
        return description.get();
    }

    public void setDescription(String description) {
        this.description.set(description);
    }

    public SimpleStringProperty descriptionProperty() {
        return description;
    }

    public Room getRoom() {
        return room.get();
    }

    public void setRoom(Room room) {
        this.room.set(room);
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

        final CollectionDto other = (CollectionDto) obj;

        return Objects.equals(this.getID(), other.getID());
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        sb.append("CollectionDto{id=").append(this.id.get());
        sb.append(", nombre=").append(this.name.get());
        sb.append(", Epoca=").append(this.era.get());
        sb.append(", Descripcion=").append(this.description.get());
        sb.append(", Sala=").append(this.room.get());
        sb.append('}');
        return sb.toString();
    }
}
