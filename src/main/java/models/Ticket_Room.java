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
@javax.persistence.Table(name = "TBL_TICKET_ROOMS")
@javax.persistence.NamedQueries({
    @javax.persistence.NamedQuery(name = "Ticket_Room.findAll", query = "SELECT t FROM Ticket_Room t"),
    @javax.persistence.NamedQuery(name = "Ticket_Room.findByTkrId", query = "SELECT t FROM Ticket_Room t WHERE t.tkrId = :tkrId")})
public class Ticket_Room implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @javax.persistence.Id
    @javax.persistence.GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
    @javax.persistence.Basic(optional = false)
    @javax.persistence.Column(name = "TKR_ID")
    private BigDecimal tkrId;
    @javax.persistence.JoinColumn(name = "TKR_ROOM_ID", referencedColumnName = "ROOM_ID")
    @javax.persistence.ManyToOne(optional = false, fetch = javax.persistence.FetchType.EAGER)
    private Rooms tkrRoomId;
    @javax.persistence.JoinColumn(name = "TKR_TICKET_ID", referencedColumnName = "TKT_ID")
    @javax.persistence.ManyToOne(optional = false, fetch = javax.persistence.FetchType.EAGER)
    private Tickets tkrTicketId;

    public Ticket_Room() {
    }

    public Ticket_Room(BigDecimal tkrId) {
        this.tkrId = tkrId;
    }

    public BigDecimal getTkrId() {
        return tkrId;
    }

    public void setTkrId(BigDecimal tkrId) {
        this.tkrId = tkrId;
    }

    public Rooms getTkrRoomId() {
        return tkrRoomId;
    }

    public void setTkrRoomId(Rooms tkrRoomId) {
        this.tkrRoomId = tkrRoomId;
    }

    public Tickets getTkrTicketId() {
        return tkrTicketId;
    }

    public void setTkrTicketId(Tickets tkrTicketId) {
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
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ticket_Room)) {
            return false;
        }
        Ticket_Room other = (Ticket_Room) object;
        if ((this.tkrId == null && other.tkrId != null) || (this.tkrId != null && !this.tkrId.equals(other.tkrId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "models.Ticket_Room[ tkrId=" + tkrId + " ]";
    }
    
}
