package models;

import java.util.Objects;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

public class CardDto {

    private SimpleStringProperty id;
    private SimpleStringProperty type;
    private SimpleStringProperty commission;
    private ObjectProperty<Ticket> ticketId;

    public CardDto() {

        this.id = new SimpleStringProperty();
        this.type = new SimpleStringProperty();
        this.commission = new SimpleStringProperty();
        this.ticketId = new SimpleObjectProperty<>();
    }

    public CardDto(Card pCard) {

        this();
        this.id.setValue(pCard.getCardId().toString());
        this.type.setValue(pCard.getCardType());
        this.commission.setValue(pCard.getCardCommission().toString());
        this.ticketId.setValue(pCard.getCardTicketId());
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

    public String getType() {
        return type.get();
    }

    public void setType(String type) {
        this.type.set(type);
    }

    public SimpleStringProperty typeProperty() {
        return type;
    }

    public String getCommission() {
        return commission.get();
    }

    public void setCommission(String commission) {
        this.commission.set(commission);
    }

    public SimpleStringProperty commissionProperty() {
        return commission;
    }

    public Ticket getTicketId() {
        return ticketId.get();
    }

    public void setTicketId(Ticket ticketId) {
        this.ticketId.set(ticketId);
    }

    public ObjectProperty<Ticket> ticketIdProperty() {
        return ticketId;
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

        final CardDto other = (CardDto) obj;

        return Objects.equals(this.getID(), other.getID());
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        sb.append("CardDto{id=").append(this.id.get());
        sb.append(", tipo de tarjeta=").append(this.type.get());
        sb.append(", comision=").append(this.commission.get());
        sb.append(", tiquete=").append(this.ticketId.get());
        sb.append('}');
        return sb.toString();
    }
}
