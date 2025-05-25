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
@javax.persistence.Table(name = "TBL_COLLECTIONS")
@javax.persistence.NamedQueries({
    @javax.persistence.NamedQuery(name = "Collection.findAll", query = "SELECT c FROM Collection c"),
    @javax.persistence.NamedQuery(name = "Collection.findByCltId", query = "SELECT c FROM Collection c WHERE c.cltId = :cltId"),
    @javax.persistence.NamedQuery(name = "Collection.findByCltName", query = "SELECT c FROM Collection c WHERE c.cltName = :cltName"),
    @javax.persistence.NamedQuery(name = "Collection.findByCltEra", query = "SELECT c FROM Collection c WHERE c.cltEra = :cltEra"),
    @javax.persistence.NamedQuery(name = "Collection.findByCltDescription", query = "SELECT c FROM Collection c WHERE c.cltDescription = :cltDescription")})
public class Collection implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @javax.persistence.Id
    @javax.persistence.GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
    @javax.persistence.Basic(optional = false)
    @javax.persistence.Column(name = "CLT_ID")
    private BigDecimal cltId;
    @javax.persistence.Basic(optional = false)
    @javax.persistence.Column(name = "CLT_NAME")
    private String cltName;
    @javax.persistence.Basic(optional = false)
    @javax.persistence.Column(name = "CLT_ERA")
    private String cltEra;
    @javax.persistence.Basic(optional = false)
    @javax.persistence.Column(name = "CLT_DESCRIPTION")
    private String cltDescription;
    @javax.persistence.OneToMany(cascade = javax.persistence.CascadeType.ALL, mappedBy = "speCollectionId", fetch = javax.persistence.FetchType.EAGER)
    private java.util.Collection<Species> speciesCollection;
    @javax.persistence.JoinColumn(name = "CLT_ROOM_ID", referencedColumnName = "ROOM_ID")
    @javax.persistence.ManyToOne(optional = false, fetch = javax.persistence.FetchType.EAGER)
    private Rooms cltRoomId;

    public Collection() {
    }

    public Collection(BigDecimal cltId) {
        this.cltId = cltId;
    }

    public Collection(BigDecimal cltId, String cltName, String cltEra, String cltDescription) {
        this.cltId = cltId;
        this.cltName = cltName;
        this.cltEra = cltEra;
        this.cltDescription = cltDescription;
    }

    public BigDecimal getCltId() {
        return cltId;
    }

    public void setCltId(BigDecimal cltId) {
        this.cltId = cltId;
    }

    public String getCltName() {
        return cltName;
    }

    public void setCltName(String cltName) {
        this.cltName = cltName;
    }

    public String getCltEra() {
        return cltEra;
    }

    public void setCltEra(String cltEra) {
        this.cltEra = cltEra;
    }

    public String getCltDescription() {
        return cltDescription;
    }

    public void setCltDescription(String cltDescription) {
        this.cltDescription = cltDescription;
    }

    public java.util.Collection<Species> getSpeciesCollection() {
        return speciesCollection;
    }

    public void setSpeciesCollection(java.util.Collection<Species> speciesCollection) {
        this.speciesCollection = speciesCollection;
    }

    public Rooms getCltRoomId() {
        return cltRoomId;
    }

    public void setCltRoomId(Rooms cltRoomId) {
        this.cltRoomId = cltRoomId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cltId != null ? cltId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Collection)) {
            return false;
        }
        Collection other = (Collection) object;
        if ((this.cltId == null && other.cltId != null) || (this.cltId != null && !this.cltId.equals(other.cltId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "models.Collection[ cltId=" + cltId + " ]";
    }
    
}
