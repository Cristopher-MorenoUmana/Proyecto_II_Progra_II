package models;

import java.util.Objects;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

public class RoomDto {

    private SimpleStringProperty id;
    private SimpleStringProperty name;
    private SimpleStringProperty description;
    private SimpleStringProperty type;
    private ObjectProperty<Museum> museum;

    public RoomDto() {

        this.id = new SimpleStringProperty();
        this.name = new SimpleStringProperty();
        this.description = new SimpleStringProperty();
        this.type = new SimpleStringProperty();
        this.museum = new SimpleObjectProperty<>();
    }

    public RoomDto(Room pRoom) {

        this();
        this.id.setValue(pRoom.getRoomId().toString());
        this.name.setValue(pRoom.getRoomName());
        this.description.setValue(pRoom.getRoomDescription());
        this.type.setValue(pRoom.getRoomType().toString());
        this.museum.setValue(pRoom.getRoomMusId());
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

    public String getName() {
        return name.get();
    }

    public String getDescription() {
        return description.get();
    }

    public String getType() {
        return type.get();
    }

    public Museum getMuseum() {
        return museum.get();
    }

    public void setId(String id) {
        this.id.set(id);
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public void setDescription(String description) {
        this.description.set(description);
    }

    public void setType(String type) {
        this.type.set(type);
    }

    public void setMuseum(Museum museum) {
        this.museum.set(museum);
    }

    public SimpleStringProperty idProperty() {
        return id;
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public SimpleStringProperty descriptionProperty() {
        return description;
    }

    public SimpleStringProperty typeProperty() {
        return type;
    }

    public ObjectProperty<Museum> museumProperty() {
        return museum;
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

        final RoomDto other = (RoomDto) obj;

        return Objects.equals(this.getID(), other.getID());
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        sb.append("RoomDto{id=").append(this.id.get());
        sb.append(", nombre=").append(this.name.get());
        sb.append(", tipo=").append(this.type.get());
        sb.append(", descripcion=").append(this.description.get());
        sb.append(", museo=").append(this.museum.get());
        sb.append('}');
        return sb.toString();
    }
}
