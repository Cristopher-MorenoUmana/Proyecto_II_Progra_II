package models;

import java.io.Serializable;
import java.util.Collection;

@javax.persistence.Entity
@javax.persistence.Table(name = "TBL_ROOMS")
@javax.persistence.NamedQueries({
    @javax.persistence.NamedQuery(name = "Rooms.findAll", query = "SELECT r FROM Rooms r"),
    @javax.persistence.NamedQuery(name = "Rooms.findByRoomId", query = "SELECT r FROM Rooms r WHERE r.roomId = :roomId"),
    @javax.persistence.NamedQuery(name = "Rooms.findByRoomName", query = "SELECT r FROM Rooms r WHERE r.roomName = :roomName"),
    @javax.persistence.NamedQuery(name = "Rooms.findByRoomDescription", query = "SELECT r FROM Rooms r WHERE r.roomDescription = :roomDescription"),
    @javax.persistence.NamedQuery(name = "Rooms.findByRoomType", query = "SELECT r FROM Rooms r WHERE r.roomType = :roomType")})
public class Room implements Serializable {

    private static final long serialVersionUID = 1L;

    @javax.persistence.Id
    @javax.persistence.GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
    @javax.persistence.Basic(optional = false)
    @javax.persistence.Column(name = "ROOM_ID")
    private Integer roomId;
    @javax.persistence.Basic(optional = false)
    @javax.persistence.Column(name = "ROOM_NAME")
    private String roomName;
    @javax.persistence.Basic(optional = false)
    @javax.persistence.Column(name = "ROOM_DESCRIPTION")
    private String roomDescription;
    @javax.persistence.Basic(optional = false)
    @javax.persistence.Column(name = "ROOM_TYPE")
    private Integer roomType;
    @javax.persistence.OneToMany(cascade = javax.persistence.CascadeType.ALL, mappedBy = "cltRoomId", fetch = javax.persistence.FetchType.EAGER)
    private Collection<models.Collection> collectionCollection;
    @javax.persistence.JoinColumn(name = "ROOM_MUS_ID", referencedColumnName = "MUS_ID")
    @javax.persistence.ManyToOne(optional = false, fetch = javax.persistence.FetchType.EAGER)
    private Museum roomMusId;
    @javax.persistence.OneToMany(cascade = javax.persistence.CascadeType.ALL, mappedBy = "priRoomId", fetch = javax.persistence.FetchType.EAGER)
    private Collection<Price> pricesCollection;
    @javax.persistence.OneToMany(cascade = javax.persistence.CascadeType.ALL, mappedBy = "tkrRoomId", fetch = javax.persistence.FetchType.EAGER)
    private Collection<Ticket_Room> ticketRoomCollection;
    @javax.persistence.OneToMany(cascade = javax.persistence.CascadeType.ALL, mappedBy = "thmRoomId", fetch = javax.persistence.FetchType.EAGER)
    private Collection<Theme> hemesCollection;

    public Room() {
    }

    public Room(Integer roomId) {
        this.roomId = roomId;
    }

    public Room(Integer roomId, String roomName, String roomDescription, Integer roomType) {
        this.roomId = roomId;
        this.roomName = roomName;
        this.roomDescription = roomDescription;
        this.roomType = roomType;
    }

    public Room(RoomDto pRoomDto){
        
        updateRoom(pRoomDto);
    }
    
    public final void updateRoom(RoomDto pRoomDto) {
        
        this.roomDescription = pRoomDto.getDescription();
        this.roomMusId = pRoomDto.getMuseum();
        this.roomName = pRoomDto.getName();
        this.roomType = Integer.valueOf(pRoomDto.getType());
    }
    
    public Integer getRoomId() {
        return roomId;
    }

    public void setRoomId(Integer roomId) {
        this.roomId = roomId;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public String getRoomDescription() {
        return roomDescription;
    }

    public void setRoomDescription(String roomDescription) {
        this.roomDescription = roomDescription;
    }

    public Integer getRoomType() {
        return roomType;
    }

    public void setRoomType(Integer roomType) {
        this.roomType = roomType;
    }

    public Collection<models.Collection> getCollectionCollection() {
        return collectionCollection;
    }

    public void setCollectionCollection(Collection<models.Collection> collectionCollection) {
        this.collectionCollection = collectionCollection;
    }

    public Museum getRoomMusId() {
        return roomMusId;
    }

    public void setRoomMusId(Museum roomMusId) {
        this.roomMusId = roomMusId;
    }

    public Collection<Price> getPricesCollection() {
        return pricesCollection;
    }

    public void setPricesCollection(Collection<Price> pricesCollection) {
        this.pricesCollection = pricesCollection;
    }

    public Collection<Ticket_Room> getTicketRoomCollection() {
        return ticketRoomCollection;
    }

    public void setTicketRoomCollection(Collection<Ticket_Room> ticketRoomCollection) {
        this.ticketRoomCollection = ticketRoomCollection;
    }

    public Collection<Theme> getHemesCollection() {
        return hemesCollection;
    }

    public void setHemesCollection(Collection<Theme> hemesCollection) {
        this.hemesCollection = hemesCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (roomId != null ? roomId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Room)) {
            return false;
        }
        Room other = (Room) object;
        return !((this.roomId == null && other.roomId != null) || (this.roomId != null && !this.roomId.equals(other.roomId)));
    }

    @Override
    public String toString() {
        return "models.Rooms[ roomId=" + roomId + " ]";
    }
    
}
