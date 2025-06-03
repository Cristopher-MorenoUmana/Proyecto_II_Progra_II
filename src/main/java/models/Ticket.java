package models;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

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
public class Ticket implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @javax.persistence.Id
    @javax.persistence.GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
    @javax.persistence.Basic(optional = false)
    @javax.persistence.Column(name = "TKT_ID")
    private Integer tktId;
    @javax.persistence.Basic(optional = false)
    @javax.persistence.Column(name = "TKT_DAY")
    @javax.persistence.Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date tktDay;
    @javax.persistence.Basic(optional = false)
    @javax.persistence.Column(name = "TKT_ROOMS")
    private String tktRooms;
    @javax.persistence.Basic(optional = false)
    @javax.persistence.Column(name = "TKT_TOTAL_PRICE")
    private Double tktTotalPrice;
    @javax.persistence.Basic(optional = false)
    @javax.persistence.Column(name = "TKT_PAY_STATE")
    private Integer tktPayState;
    @javax.persistence.Basic(optional = false)
    @javax.persistence.Column(name = "TKT_TICKETS_QUANTITY")
    private Integer tktTicketsQuantity;
    @javax.persistence.OneToMany(cascade = javax.persistence.CascadeType.ALL, mappedBy = "cardTicketId", fetch = javax.persistence.FetchType.EAGER)
    private Collection<Card> cardCollection;
    @javax.persistence.OneToMany(cascade = javax.persistence.CascadeType.ALL, mappedBy = "tkrTicketId", fetch = javax.persistence.FetchType.EAGER)
    private Collection<Ticket_Room> ticketRoomCollection;

    public Ticket() {
    }

    public Ticket(Integer tktId) {
        this.tktId = tktId;
    }

    public Ticket(Integer tktId, Date tktDay, String tktRooms, Double tktTotalPrice, Integer tktPayState, Integer tktTicketsQuantity) {
        this.tktId = tktId;
        this.tktDay = tktDay;
        this.tktRooms = tktRooms;
        this.tktTotalPrice = tktTotalPrice;
        this.tktPayState = tktPayState;
        this.tktTicketsQuantity = tktTicketsQuantity;
    }

    public Ticket(TicketDto pTicketDto){
        
        updateTicket(pTicketDto);
    }
    
    public final void updateTicket(TicketDto pTicketDto){
        
        this.tktDay = pTicketDto.getDay();
        this.tktRooms = pTicketDto.getRooms();
        this.tktTicketsQuantity = Integer.valueOf(pTicketDto.getQuantity());
        this.tktPayState = Integer.valueOf(pTicketDto.getPayState());
        this.tktTotalPrice = Double.valueOf(pTicketDto.getTotalPrice());
    }
    public Integer getTktId() {
        return tktId;
    }

    public void setTktId(Integer tktId) {
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

    public Double getTktTotalPrice() {
        return tktTotalPrice;
    }

    public void setTktTotalPrice(Double tktTotalPrice) {
        this.tktTotalPrice = tktTotalPrice;
    }

    public Integer getTktPayState() {
        return tktPayState;
    }

    public void setTktPayState(Integer tktPayState) {
        this.tktPayState = tktPayState;
    }

    public Integer getTktTicketsQuantity() {
        return tktTicketsQuantity;
    }

    public void setTktTicketsQuantity(Integer tktTicketsQuantity) {
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
  
        if (!(object instanceof Ticket)) {
            return false;
        }
        Ticket other = (Ticket) object;
        return !((this.tktId == null && other.tktId != null) || (this.tktId != null && !this.tktId.equals(other.tktId)));
    }

    @Override
    public String toString() {
        return "models.Tickets[ tktId=" + tktId + " ]";
    }
    
}
