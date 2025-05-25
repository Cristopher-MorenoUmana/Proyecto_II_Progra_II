/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 *
 * @author neynm
 */
@javax.persistence.Entity
@javax.persistence.Table(name = "TBL_CARDS")
@javax.persistence.NamedQueries({
    @javax.persistence.NamedQuery(name = "Card.findAll", query = "SELECT c FROM Card c"),
    @javax.persistence.NamedQuery(name = "Card.findByCardId", query = "SELECT c FROM Card c WHERE c.cardId = :cardId"),
    @javax.persistence.NamedQuery(name = "Card.findByCardType", query = "SELECT c FROM Card c WHERE c.cardType = :cardType"),
    @javax.persistence.NamedQuery(name = "Card.findByCardComision", query = "SELECT c FROM Card c WHERE c.cardComision = :cardComision")})
public class Card implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @javax.persistence.Id
    @javax.persistence.GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
    @javax.persistence.Basic(optional = false)
    @javax.persistence.Column(name = "CARD_ID")
    private BigDecimal cardId;
    @javax.persistence.Basic(optional = false)
    @javax.persistence.Column(name = "CARD_TYPE")
    private String cardType;
    @javax.persistence.Basic(optional = false)
    @javax.persistence.Column(name = "CARD_COMISION")
    private BigDecimal cardComision;
    @javax.persistence.JoinColumn(name = "CARD_TICKET_ID", referencedColumnName = "TKT_ID")
    @javax.persistence.ManyToOne(optional = false, fetch = javax.persistence.FetchType.EAGER)
    private Tickets cardTicketId;

    public Card() {
    }

    public Card(BigDecimal cardId) {
        this.cardId = cardId;
    }

    public Card(BigDecimal cardId, String cardType, BigDecimal cardComision) {
        this.cardId = cardId;
        this.cardType = cardType;
        this.cardComision = cardComision;
    }

    public BigDecimal getCardId() {
        return cardId;
    }

    public void setCardId(BigDecimal cardId) {
        this.cardId = cardId;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public BigDecimal getCardComision() {
        return cardComision;
    }

    public void setCardComision(BigDecimal cardComision) {
        this.cardComision = cardComision;
    }

    public Tickets getCardTicketId() {
        return cardTicketId;
    }

    public void setCardTicketId(Tickets cardTicketId) {
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
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Card)) {
            return false;
        }
        Card other = (Card) object;
        if ((this.cardId == null && other.cardId != null) || (this.cardId != null && !this.cardId.equals(other.cardId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "models.Card[ cardId=" + cardId + " ]";
    }
    
}
