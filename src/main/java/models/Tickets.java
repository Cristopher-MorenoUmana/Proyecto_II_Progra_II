/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Collection;
import java.util.Date;

/**
 *
 * @author neynm
 */
@javax.persistence.Entity
@javax.persistence.Table(name = "TBL_TICKETS")
@javax.persistence.NamedQueries({
    @javax.persistence.NamedQuery(name = "Tickets.findAll", query = "SELECT t FROM Tickets t"),
    @javax.persistence.NamedQuery(name = "Tickets.findByTktId", query = "SELECT t FROM Tickets t WHERE t.tktId = :tktId"),
    @javax.persistence.NamedQuery(name = "Tickets.findByTktDay", query = "SELECT t FROM Tickets t WHERE t.tktDay = :tktDay"),
    @javax.persistence.NamedQuery(name = "Tickets.findByTktRooms", query = "SELECT t FROM Tickets t WHERE t.tktRooms = :tktRooms"),
    @javax.persistence.NamedQuery(name = "Tickets.findByTktTotalPrice", query = "SELECT t FROM Tickets t WHERE t.tktTotalPrice = :tktTotalPrice"),
    @javax.persistence.NamedQuery(name = "Tickets.findByTktPayState", query = "SELECT t FROM Tickets t WHERE t.tktPayState = :tktPayState"),
    @javax.persistence.NamedQuery(name = "Tickets.findByTktTicketsQuantity", query = "SELECT t FROM Tickets t WHERE t.tktTicketsQuantity = :tktTicketsQuantity")})
public class Tickets implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @javax.persistence.Id
    @javax.persistence.GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
    @javax.persistence.Basic(optional = false)
    @javax.persistence.Column(name = "TKT_ID")
    private BigDecimal tktId;
    @javax.persistence.Basic(optional = false)
    @javax.persistence.Column(name = "TKT_DAY")
    @javax.persistence.Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date tktDay;
    @javax.persistence.Basic(optional = false)
    @javax.persistence.Column(name = "TKT_ROOMS")
    private String tktRooms;
    @javax.persistence.Basic(optional = false)
    @javax.persistence.Column(name = "TKT_TOTAL_PRICE")
    private BigDecimal tktTotalPrice;
    @javax.persistence.Basic(optional = false)
    @javax.persistence.Column(name = "TKT_PAY_STATE")
    private short tktPayState;
    @javax.persistence.Basic(optional = false)
    @javax.persistence.Column(name = "TKT_TICKETS_QUANTITY")
    private BigInteger tktTicketsQuantity;
    @javax.persistence.OneToMany(cascade = javax.persistence.CascadeType.ALL, mappedBy = "cardTicketId", fetch = javax.persistence.FetchType.EAGER)
    private Collection<Card> cardCollection;
    @javax.persistence.OneToMany(cascade = javax.persistence.CascadeType.ALL, mappedBy = "tkrTicketId", fetch = javax.persistence.FetchType.EAGER)
    private Collection<Ticket_Room> ticketRoomCollection;

    public Tickets() {
    }

    public Tickets(BigDecimal tktId) {
        this.tktId = tktId;
    }

    public Tickets(BigDecimal tktId, Date tktDay, String tktRooms, BigDecimal tktTotalPrice, short tktPayState, BigInteger tktTicketsQuantity) {
        this.tktId = tktId;
        this.tktDay = tktDay;
        this.tktRooms = tktRooms;
        this.tktTotalPrice = tktTotalPrice;
        this.tktPayState = tktPayState;
        this.tktTicketsQuantity = tktTicketsQuantity;
    }

    public BigDecimal getTktId() {
        return tktId;
    }

    public void setTktId(BigDecimal tktId) {
        this.tktId = tktId;
    }

    public Date getTktDay() {
        return tktDay;
    }

    public void setTktDay(Date tktDay) {
        this.tktDay = tktDay;
    }

    public String getTktRooms() {
        return tktRooms;
    }

    public void setTktRooms(String tktRooms) {
        this.tktRooms = tktRooms;
    }

    public BigDecimal getTktTotalPrice() {
        return tktTotalPrice;
    }

    public void setTktTotalPrice(BigDecimal tktTotalPrice) {
        this.tktTotalPrice = tktTotalPrice;
    }

    public short getTktPayState() {
        return tktPayState;
    }

    public void setTktPayState(short tktPayState) {
        this.tktPayState = tktPayState;
    }

    public BigInteger getTktTicketsQuantity() {
        return tktTicketsQuantity;
    }

    public void setTktTicketsQuantity(BigInteger tktTicketsQuantity) {
        this.tktTicketsQuantity = tktTicketsQuantity;
    }

    public Collection<Card> getCardCollection() {
        return cardCollection;
    }

    public void setCardCollection(Collection<Card> cardCollection) {
        this.cardCollection = cardCollection;
    }

    public Collection<Ticket_Room> getTicketRoomCollection() {
        return ticketRoomCollection;
    }

    public void setTicketRoomCollection(Collection<Ticket_Room> ticketRoomCollection) {
        this.ticketRoomCollection = ticketRoomCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tktId != null ? tktId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tickets)) {
            return false;
        }
        Tickets other = (Tickets) object;
        if ((this.tktId == null && other.tktId != null) || (this.tktId != null && !this.tktId.equals(other.tktId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "models.Tickets[ tktId=" + tktId + " ]";
    }
    
}
