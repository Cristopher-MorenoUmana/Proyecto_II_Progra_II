package models;

import java.io.Serializable;

@javax.persistence.Entity
@javax.persistence.Table(name = "TBL_TICKET_ROOMS")
@javax.persistence.NamedQueries({
    @javax.persistence.NamedQuery(name = "Ticket_Room.findAll", query = "SELECT t FROM Ticket_Room t"),
    @javax.persistence.NamedQuery(name = "Ticket_Room.findByTkrId", query = "SELECT t FROM Ticket_Room t WHERE t.tkrId = :tkrId")})
public class Ticket_Room implements Serializable {

    private static final long serialVersionUID = 1L;

    @javax.persistence.Id
    @javax.persistence.GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
    @javax.persistence.Basic(optional = false)
    @javax.persistence.Column(name = "TKR_ID")
    private Integer tkrId;
    @javax.persistence.JoinColumn(name = "TKR_ROOM_ID", referencedColumnName = "ROOM_ID")
    @javax.persistence.ManyToOne(optional = false, fetch = javax.persistence.FetchType.EAGER)
    private Room tkrRoomId;
    @javax.persistence.JoinColumn(name = "TKR_TICKET_ID", referencedColumnName = "TKT_ID")
    @javax.persistence.ManyToOne(optional = false, fetch = javax.persistence.FetchType.EAGER)
    private Ticket tkrTicketId;

    public Ticket_Room() {
    }

    public Ticket_Room(Integer tkrId) {
        this.tkrId = tkrId;
    }

    public Integer getTkrId() {
        return tkrId;
    }

    public void setTkrId(Integer tkrId) {
        this.tkrId = tkrId;
    }

    public Room getTkrRoomId() {
        return tkrRoomId;
    }

    public void setTkrRoomId(Room tkrRoomId) {
        this.tkrRoomId = tkrRoomId;
    }

    public Ticket getTkrTicketId() {
        return tkrTicketId;
    }

    public void setTkrTicketId(Ticket tkrTicketId) {
        this.tkrTicketId = tkrTicketId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tkrId != null ? tkrId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        
        if (!(object instanceof Ticket_Room)) {
            return false;
        }
        Ticket_Room other = (Ticket_Room) object;
        return !((this.tkrId == null && other.tkrId != null) || (this.tkrId != null && !this.tkrId.equals(other.tkrId)));
    }

    @Override
    public String toString() {
        return "models.Ticket_Room[ tkrId=" + tkrId + " ]";
    }
    
}
