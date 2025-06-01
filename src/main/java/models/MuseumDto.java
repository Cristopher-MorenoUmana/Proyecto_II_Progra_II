package models;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Objects;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

public class MuseumDto {

    private SimpleStringProperty id;
    private SimpleStringProperty name;
    private SimpleStringProperty location;
    private ObjectProperty<Date> fundationDate;
    private SimpleStringProperty directorName;
    private SimpleStringProperty webSite;
    private SimpleStringProperty type;

    public MuseumDto() {

        this.id = new SimpleStringProperty();
        this.name = new SimpleStringProperty();
        this.location = new SimpleStringProperty();
        this.fundationDate = new SimpleObjectProperty<>();
        this.directorName = new SimpleStringProperty();
        this.webSite = new SimpleStringProperty();
        this.type = new SimpleStringProperty();
    }

    public MuseumDto(Museum museum) {

        this();
        this.id.set(museum.getMusId().toString());
        this.name.set(museum.getMusName());
        this.location.set(museum.getMusLocation());
        this.fundationDate.set(museum.getMusFundationDate());
        this.directorName.set(museum.getMusDirectorName());
        this.webSite.set(museum.getMusWebsite());
        this.type.set(museum.getMusType());
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
        return this.name.get();
    }

    public String getLocation() {
        return this.location.get();
    }

    public String getDirectorName() {
        return this.directorName.get();
    }

    public String getWebSite() {
        return this.webSite.get();
    }

    public String getType() {
        return this.type.get();
    }

    public SimpleStringProperty idProperty() {
        return id;
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public SimpleStringProperty locationProperty() {
        return location;
    }

    public ObjectProperty<Date> fundationDateProperty() {
        return fundationDate;
    }

    public SimpleStringProperty directorNameProperty() {
        return directorName;
    }

    public SimpleStringProperty webSiteProperty() {
        return webSite;
    }

    public SimpleStringProperty typeProperty() {
        return type;
    }

    public LocalDate getFundationDateAsLocalDate() {

        Date date = this.fundationDate.get();

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

        final MuseumDto other = (MuseumDto) obj;

        return Objects.equals(this.getID(), other.getID());
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        sb.append("MuseumDto{id=").append(this.id.get());
        sb.append(", nombre=").append(this.name.get());
        sb.append(", localizacion=").append(this.location.get());
        sb.append(", fecha de fundacion=").append(this.fundationDate.get());
        sb.append(", nombre del director=").append(this.directorName.get());
        sb.append(", sitio web=").append(this.webSite.get());
        sb.append(", tipo=").append(this.type.get());
        sb.append('}');
        return sb.toString();
    }
}
