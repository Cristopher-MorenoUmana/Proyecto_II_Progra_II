package models;

import java.util.Objects;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

public class ThemeDto {

    private SimpleStringProperty id;
    private SimpleStringProperty name;
    private SimpleStringProperty characteristics;
    private SimpleStringProperty era;
    private ObjectProperty<Room> room;

    public ThemeDto() {

        this.id = new SimpleStringProperty();
        this.name = new SimpleStringProperty();
        this.characteristics = new SimpleStringProperty();
        this.era = new SimpleStringProperty();
        this.room = new SimpleObjectProperty<>();
    }

    public ThemeDto(Theme pTheme) {

        this();
        this.id.setValue(pTheme.getThmId().toString());
        this.name.setValue(pTheme.getThmName());
        this.characteristics.setValue(pTheme.getThmCharacteristics());
        this.era.setValue(pTheme.getThmEra());
        this.room.setValue(pTheme.getThmRoomId());
    }

    public String getName() {
        return name.get();
    }

    public String getCharacteristics() {
        return characteristics.get();
    }

    public String getEra() {
        return era.get();
    }

    public Room getRoom() {
        return room.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public void setCharacteristics(String characteristics) {
        this.characteristics.set(characteristics);
    }

    public void setEra(String era) {
        this.era.set(era);
    }

    public void setRoom(Room room) {
        this.room.set(room);
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public SimpleStringProperty characteristicsProperty() {
        return characteristics;
    }

    public SimpleStringProperty eraProperty() {
        return era;
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

        final ThemeDto other = (ThemeDto) obj;

        return Objects.equals(this.getID(), other.getID());
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        sb.append("ThemeDto{id=").append(this.id.get());
        sb.append(", nombre=").append(this.name.get());
        sb.append(", siglo=").append(this.era.get());
        sb.append(", caracteristicas=").append(this.characteristics.get());
        sb.append(", sala=").append(this.room.get());
        sb.append('}');
        return sb.toString();
    }
}