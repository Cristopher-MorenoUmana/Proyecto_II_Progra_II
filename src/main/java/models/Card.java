
package models;

import java.io.Serializable;

@javax.persistence.Entity
@javax.persistence.Table(name = "TBL_CARDS")
@javax.persistence.NamedQueries({
    @javax.persistence.NamedQuery(name = "Card.findAll", query = "SELECT c FROM Card c"),
    @javax.persistence.NamedQuery(name = "Card.findByCardId", query = "SELECT c FROM Card c WHERE c.cardId = :cardId"),
    @javax.persistence.NamedQuery(name = "Card.findByCardType", query = "SELECT c FROM Card c WHERE c.cardType = :cardType"),
    @javax.persistence.NamedQuery(name = "Card.findByCardComision", query = "SELECT c FROM Card c WHERE c.cardComision = :cardComision")})
public class Card implements Serializable {

    private static final long serialVersionUID = 1L;

    @javax.persistence.Id
    @javax.persistence.GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
    @javax.persistence.Basic(optional = false)
    @javax.persistence.Column(name = "CARD_ID")
    private Integer cardId;
    @javax.persistence.Basic(optional = false)
    @javax.persistence.Column(name = "CARD_TYPE")
    private String cardType;
    @javax.persistence.Basic(optional = false)
    @javax.persistence.Column(name = "CARD_COMMISSION")
    private Double cardCommission;
    @javax.persistence.JoinColumn(name = "CARD_TICKET_ID", referencedColumnName = "TKT_ID")
    @javax.persistence.ManyToOne(optional = false, fetch = javax.persistence.FetchType.EAGER)
    private Ticket cardTicketId;

    public Card() {
    }

    public Card(Integer cardId) {
        this.cardId = cardId;
    }

    public Card(Integer cardId, String cardType, Double cardComision) {
        this.cardId = cardId;
        this.cardType = cardType;
        this.cardCommission = cardComision;
    }

    public Integer getCardId() {
        return cardId;
    }

    public void setCardId(Integer cardId) {
        this.cardId = cardId;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public Double getCardCommission() {
        return cardCommission;
    }

    public void setCardCommission(Double cardCommission) {
        this.cardCommission = cardCommission;
    }

    public Ticket getCardTicketId() {
        return cardTicketId;
    }

    public void setCardTicketId(Ticket cardTicketId) {
        this.cardTicketId = cardTicketId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cardId != null ? cardId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        
        if (!(object instanceof Card)) {
            return false;
        }
        Card other = (Card) object;
        return !((this.cardId == null && other.cardId != null) || (this.cardId != null && !this.cardId.equals(other.cardId)));
    }

    @Override
    public String toString() {
        return "models.Card[ cardId=" + cardId + " ]";
    }
}
