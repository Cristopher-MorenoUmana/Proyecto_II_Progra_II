package models;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Objects;
import java.time.Instant;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

public class SpecieDto {

    private SimpleStringProperty id;
    private SimpleStringProperty commonName;
    private SimpleStringProperty scientistName;
    private SimpleStringProperty era;
    private SimpleStringProperty weight;
    private SimpleStringProperty size;
    private SimpleStringProperty characteristics;
    private ObjectProperty<Date> extinctionDate;

    public SpecieDto() {

        this.id = new SimpleStringProperty();
        this.commonName = new SimpleStringProperty();
        this.scientistName = new SimpleStringProperty();
        this.era = new SimpleStringProperty();
        this.weight = new SimpleStringProperty();
        this.size = new SimpleStringProperty();
        this.characteristics = new SimpleStringProperty();
        this.extinctionDate = new SimpleObjectProperty<>();
    }

    public SpecieDto(Specie pSpecie) {

        this();
        this.id.setValue(pSpecie.getSpeId().toString());
        this.commonName.setValue(pSpecie.getSpeCommonName());
        this.scientistName.setValue(pSpecie.getSpeScientistName());
        this.era.setValue(pSpecie.getSpeEra());
        this.weight.setValue(pSpecie.getSpeWeight().toString());
        this.size.setValue(pSpecie.getSpeSize().toString());
        this.characteristics.setValue(pSpecie.getSpeCharacteristics());
        this.extinctionDate.setValue(pSpecie.getSpeExtinctionDate());
    }

    public String getCommonName() {
        return commonName.get();
    }

    public String getScientistName() {
        return scientistName.get();
    }

    public String getEra() {
        return era.get();
    }

    public String getWeight() {
        return weight.get();
    }

    public String getSize() {
        return size.get();
    }

    public String getCharacteristics() {
        return characteristics.get();
    }

    public Date getExtinctionDate() {
        return extinctionDate.get();
    }

    public void setCommonName(String commonName) {
        this.commonName.set(commonName);
    }

    public void setScientistName(String scientistName) {
        this.scientistName.set(scientistName);
    }

    public void setEra(String era) {
        this.era.set(era);
    }

    public void setWeight(String weight) {
        this.weight.set(weight);
    }

    public void setSize(String size) {
        this.size.set(size);
    }

    public void setCharacteristics(String characteristics) {
        this.characteristics.set(characteristics);
    }

    public void setExtinctionDate(Date extinctionDate) {
        this.extinctionDate.set(extinctionDate);
    }

    public SimpleStringProperty commonNameProperty() {
        return commonName;
    }

    public SimpleStringProperty scientistNameProperty() {
        return scientistName;
    }

    public SimpleStringProperty eraProperty() {
        return era;
    }

    public SimpleStringProperty weightProperty() {
        return weight;
    }

    public SimpleStringProperty sizeProperty() {
        return size;
    }

    public SimpleStringProperty characteristicsProperty() {
        return characteristics;
    }

    public ObjectProperty<Date> extinctionDateProperty() {
        return extinctionDate;
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

    public LocalDate getExtinctionDateAsLocalDate() {

        Date date = this.extinctionDate.get();

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

        final SpecieDto other = (SpecieDto) obj;

        return Objects.equals(this.getID(), other.getID());
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        sb.append("SpecieDto{id=").append(this.id.get());
        sb.append(", nombre comun=").append(this.commonName.get());
        sb.append(", nombre cientifico=").append(this.scientistName.get());
        sb.append(", caracteristicas=").append(this.characteristics.get());
        sb.append(", fecha de extincion=").append(this.extinctionDate.get());
        sb.append(", siglo=").append(this.era.get());
        sb.append(", peso=").append(this.weight.get());
        sb.append(", altura=").append(this.size.get());
        sb.append('}');
        return sb.toString();
    }
}
