/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;

/**
 *
 * @author neynm
 */
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
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @javax.persistence.Id
    @javax.persistence.GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
    @javax.persistence.Basic(optional = false)
    @javax.persistence.Column(name = "MUS_ID")
    private BigDecimal musId;
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
    private Collection<Rooms> roomsCollection;

    public Museum() {
    }

    public Museum(BigDecimal musId) {
        this.musId = musId;
    }

    public Museum(BigDecimal musId, String musName, String musLocation, Date musFundationDate, String musDirectorName, String musWebsite, String musType) {
        this.musId = musId;
        this.musName = musName;
        this.musLocation = musLocation;
        this.musFundationDate = musFundationDate;
        this.musDirectorName = musDirectorName;
        this.musWebsite = musWebsite;
        this.musType = musType;
    }

    public BigDecimal getMusId() {
        return musId;
    }

    public void setMusId(BigDecimal musId) {
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

    public Collection<Rooms> getRoomsCollection() {
        return roomsCollection;
    }

    public void setRoomsCollection(Collection<Rooms> roomsCollection) {
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
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Museum)) {
            return false;
        }
        Museum other = (Museum) object;
        if ((this.musId == null && other.musId != null) || (this.musId != null && !this.musId.equals(other.musId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "models.Museum[ musId=" + musId + " ]";
    }
    
}
