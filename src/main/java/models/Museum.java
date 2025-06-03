package models;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

@javax.persistence.Entity
@javax.persistence.Table(name = "TBL_MUSEUM")
@javax.persistence.NamedQueries({
    @javax.persistence.NamedQuery(name = "Museum.findAll", query = "SELECT m FROM Museum m"),
    @javax.persistence.NamedQuery(name = "Museum.findByMusId", query = "SELECT m FROM Museum m WHERE m.musId = :musId"),
    @javax.persistence.NamedQuery(name = "Museum.findByMusName", query = "SELECT m FROM Museum m WHERE m.musName = :musName"),
    @javax.persistence.NamedQuery(name = "Museum.findByMusLocation", query = "SELECT m FROM Museum m WHERE m.musLocation = :musLocation"),
    @javax.persistence.NamedQuery(name = "Museum.findByMusFundationDate", query = "SELECT m FROM Museum m WHERE m.musFundationDate = :musFundationDate"),
    @javax.persistence.NamedQuery(name = "Museum.findByMusDirectorName", query = "SELECT m FROM Museum m WHERE m.musDirectorName = :musDirectorName"),
    @javax.persistence.NamedQuery(name = "Museum.findByMusWebsite", query = "SELECT m FROM Museum m WHERE m.musWebsite = :musWebsite"),
    @javax.persistence.NamedQuery(name = "Museum.findByMusType", query = "SELECT m FROM Museum m WHERE m.musType = :musType")})
public class Museum implements Serializable {

    private static final long serialVersionUID = 1L;

    @javax.persistence.Id
    @javax.persistence.GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
    @javax.persistence.Basic(optional = false)
    @javax.persistence.Column(name = "MUS_ID")
    private Integer musId;
    @javax.persistence.Basic(optional = false)
    @javax.persistence.Column(name = "MUS_NAME")
    private String musName;
    @javax.persistence.Basic(optional = false)
    @javax.persistence.Column(name = "MUS_LOCATION")
    private String musLocation;
    @javax.persistence.Basic(optional = false)
    @javax.persistence.Column(name = "MUS_FUNDATION_DATE")
    @javax.persistence.Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date musFundationDate;
    @javax.persistence.Basic(optional = false)
    @javax.persistence.Column(name = "MUS_DIRECTOR_NAME")
    private String musDirectorName;
    @javax.persistence.Basic(optional = false)
    @javax.persistence.Column(name = "MUS_WEBSITE")
    private String musWebsite;
    @javax.persistence.Basic(optional = false)
    @javax.persistence.Column(name = "MUS_TYPE")
    private String musType;
    @javax.persistence.OneToMany(cascade = javax.persistence.CascadeType.ALL, mappedBy = "roomMusId", fetch = javax.persistence.FetchType.EAGER)
    private Collection<Room> roomsCollection;

    public Museum() {
    }

    public Museum(Integer musId) {
        this.musId = musId;
    }

    public Museum(Integer musId, String musName, String musLocation, Date musFundationDate, String musDirectorName, String musWebsite, String musType) {
        this.musId = musId;
        this.musName = musName;
        this.musLocation = musLocation;
        this.musFundationDate = musFundationDate;
        this.musDirectorName = musDirectorName;
        this.musWebsite = musWebsite;
        this.musType = musType;
    }

    public Museum(MuseumDto pMuseumDto) {
         
        updateMuseum(pMuseumDto);
    }
    
    public final void updateMuseum(MuseumDto pMuseumDto) {
        
        this.musDirectorName = pMuseumDto.getDirectorName();
        this.musFundationDate = pMuseumDto.getFundationDate();
        this.musLocation = pMuseumDto.getLocation();
        this.musName = pMuseumDto.getName();
        this.musType = pMuseumDto.getType();
        this.musWebsite = pMuseumDto.getWebSite();
    }
    
    public Integer getMusId() {
        return musId;
    }

    public void setMusId(Integer musId) {
        this.musId = musId;
    }

    public String getMusName() {
        return musName;
    }

    public void setMusName(String musName) {
        this.musName = musName;
    }

    public String getMusLocation() {
        return musLocation;
    }

    public void setMusLocation(String musLocation) {
        this.musLocation = musLocation;
    }

    public Date getMusFundationDate() {
        return musFundationDate;
    }

    public void setMusFundationDate(Date musFundationDate) {
        this.musFundationDate = musFundationDate;
    }

    public String getMusDirectorName() {
        return musDirectorName;
    }

    public void setMusDirectorName(String musDirectorName) {
        this.musDirectorName = musDirectorName;
    }

    public String getMusWebsite() {
        return musWebsite;
    }

    public void setMusWebsite(String musWebsite) {
        this.musWebsite = musWebsite;
    }

    public String getMusType() {
        return musType;
    }

    public void setMusType(String musType) {
        this.musType = musType;
    }

    public Collection<Room> getRoomsCollection() {
        return roomsCollection;
    }

    public void setRoomsCollection(Collection<Room> roomsCollection) {
        this.roomsCollection = roomsCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (musId != null ? musId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        
        if (!(object instanceof Museum)) {
            return false;
        }
        Museum other = (Museum) object;
        return !((this.musId == null && other.musId != null) || (this.musId != null && !this.musId.equals(other.musId)));
    }

    @Override
    public String toString() {
        return "models.Museum[ musId=" + musId + " ]";
    }
    
}
