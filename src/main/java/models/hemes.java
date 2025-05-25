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
@javax.persistence.Table(name = "TBL_THEMES")
@javax.persistence.NamedQueries({
    @javax.persistence.NamedQuery(name = "hemes.findAll", query = "SELECT h FROM hemes h"),
    @javax.persistence.NamedQuery(name = "hemes.findByThmId", query = "SELECT h FROM hemes h WHERE h.thmId = :thmId"),
    @javax.persistence.NamedQuery(name = "hemes.findByThmName", query = "SELECT h FROM hemes h WHERE h.thmName = :thmName"),
    @javax.persistence.NamedQuery(name = "hemes.findByThmCharacteristics", query = "SELECT h FROM hemes h WHERE h.thmCharacteristics = :thmCharacteristics"),
    @javax.persistence.NamedQuery(name = "hemes.findByThmEra", query = "SELECT h FROM hemes h WHERE h.thmEra = :thmEra")})
public class hemes implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @javax.persistence.Id
    @javax.persistence.GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
    @javax.persistence.Basic(optional = false)
    @javax.persistence.Column(name = "THM_ID")
    private BigDecimal thmId;
    @javax.persistence.Basic(optional = false)
    @javax.persistence.Column(name = "THM_NAME")
    private String thmName;
    @javax.persistence.Basic(optional = false)
    @javax.persistence.Column(name = "THM_CHARACTERISTICS")
    private String thmCharacteristics;
    @javax.persistence.Basic(optional = false)
    @javax.persistence.Column(name = "THM_ERA")
    private String thmEra;
    @javax.persistence.JoinColumn(name = "THM_ROOM_ID", referencedColumnName = "ROOM_ID")
    @javax.persistence.ManyToOne(optional = false, fetch = javax.persistence.FetchType.EAGER)
    private Rooms thmRoomId;

    public hemes() {
    }

    public hemes(BigDecimal thmId) {
        this.thmId = thmId;
    }

    public hemes(BigDecimal thmId, String thmName, String thmCharacteristics, String thmEra) {
        this.thmId = thmId;
        this.thmName = thmName;
        this.thmCharacteristics = thmCharacteristics;
        this.thmEra = thmEra;
    }

    public BigDecimal getThmId() {
        return thmId;
    }

    public void setThmId(BigDecimal thmId) {
        this.thmId = thmId;
    }

    public String getThmName() {
        return thmName;
    }

    public void setThmName(String thmName) {
        this.thmName = thmName;
    }

    public String getThmCharacteristics() {
        return thmCharacteristics;
    }

    public void setThmCharacteristics(String thmCharacteristics) {
        this.thmCharacteristics = thmCharacteristics;
    }

    public String getThmEra() {
        return thmEra;
    }

    public void setThmEra(String thmEra) {
        this.thmEra = thmEra;
    }

    public Rooms getThmRoomId() {
        return thmRoomId;
    }

    public void setThmRoomId(Rooms thmRoomId) {
        this.thmRoomId = thmRoomId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (thmId != null ? thmId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof hemes)) {
            return false;
        }
        hemes other = (hemes) object;
        if ((this.thmId == null && other.thmId != null) || (this.thmId != null && !this.thmId.equals(other.thmId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "models.hemes[ thmId=" + thmId + " ]";
    }
    
}
