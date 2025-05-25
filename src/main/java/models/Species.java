/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author neynm
 */
@javax.persistence.Entity
@javax.persistence.Table(name = "TBL_SPECIES")
@javax.persistence.NamedQueries({
    @javax.persistence.NamedQuery(name = "Species.findAll", query = "SELECT s FROM Species s"),
    @javax.persistence.NamedQuery(name = "Species.findBySpeId", query = "SELECT s FROM Species s WHERE s.speId = :speId"),
    @javax.persistence.NamedQuery(name = "Species.findBySpeScientistName", query = "SELECT s FROM Species s WHERE s.speScientistName = :speScientistName"),
    @javax.persistence.NamedQuery(name = "Species.findBySpeCommonName", query = "SELECT s FROM Species s WHERE s.speCommonName = :speCommonName"),
    @javax.persistence.NamedQuery(name = "Species.findBySpeEra", query = "SELECT s FROM Species s WHERE s.speEra = :speEra"),
    @javax.persistence.NamedQuery(name = "Species.findBySpeWeight", query = "SELECT s FROM Species s WHERE s.speWeight = :speWeight"),
    @javax.persistence.NamedQuery(name = "Species.findBySpeSize", query = "SELECT s FROM Species s WHERE s.speSize = :speSize"),
    @javax.persistence.NamedQuery(name = "Species.findBySpeExtinctionDate", query = "SELECT s FROM Species s WHERE s.speExtinctionDate = :speExtinctionDate"),
    @javax.persistence.NamedQuery(name = "Species.findBySpeCharacteristics", query = "SELECT s FROM Species s WHERE s.speCharacteristics = :speCharacteristics")})
public class Species implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @javax.persistence.Id
    @javax.persistence.GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
    @javax.persistence.Basic(optional = false)
    @javax.persistence.Column(name = "SPE_ID")
    private BigDecimal speId;
    @javax.persistence.Basic(optional = false)
    @javax.persistence.Column(name = "SPE_SCIENTIST_NAME")
    private String speScientistName;
    @javax.persistence.Basic(optional = false)
    @javax.persistence.Column(name = "SPE_COMMON_NAME")
    private String speCommonName;
    @javax.persistence.Basic(optional = false)
    @javax.persistence.Column(name = "SPE_ERA")
    private String speEra;
    @javax.persistence.Basic(optional = false)
    @javax.persistence.Column(name = "SPE_WEIGHT")
    private BigDecimal speWeight;
    @javax.persistence.Basic(optional = false)
    @javax.persistence.Column(name = "SPE_SIZE")
    private BigDecimal speSize;
    @javax.persistence.Basic(optional = false)
    @javax.persistence.Column(name = "SPE_EXTINCTION_DATE")
    @javax.persistence.Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date speExtinctionDate;
    @javax.persistence.Basic(optional = false)
    @javax.persistence.Column(name = "SPE_CHARACTERISTICS")
    private String speCharacteristics;
    @javax.persistence.JoinColumn(name = "SPE_COLLECTION_ID", referencedColumnName = "CLT_ID")
    @javax.persistence.ManyToOne(optional = false, fetch = javax.persistence.FetchType.EAGER)
    private Collection speCollectionId;

    public Species() {
    }

    public Species(BigDecimal speId) {
        this.speId = speId;
    }

    public Species(BigDecimal speId, String speScientistName, String speCommonName, String speEra, BigDecimal speWeight, BigDecimal speSize, Date speExtinctionDate, String speCharacteristics) {
        this.speId = speId;
        this.speScientistName = speScientistName;
        this.speCommonName = speCommonName;
        this.speEra = speEra;
        this.speWeight = speWeight;
        this.speSize = speSize;
        this.speExtinctionDate = speExtinctionDate;
        this.speCharacteristics = speCharacteristics;
    }

    public BigDecimal getSpeId() {
        return speId;
    }

    public void setSpeId(BigDecimal speId) {
        this.speId = speId;
    }

    public String getSpeScientistName() {
        return speScientistName;
    }

    public void setSpeScientistName(String speScientistName) {
        this.speScientistName = speScientistName;
    }

    public String getSpeCommonName() {
        return speCommonName;
    }

    public void setSpeCommonName(String speCommonName) {
        this.speCommonName = speCommonName;
    }

    public String getSpeEra() {
        return speEra;
    }

    public void setSpeEra(String speEra) {
        this.speEra = speEra;
    }

    public BigDecimal getSpeWeight() {
        return speWeight;
    }

    public void setSpeWeight(BigDecimal speWeight) {
        this.speWeight = speWeight;
    }

    public BigDecimal getSpeSize() {
        return speSize;
    }

    public void setSpeSize(BigDecimal speSize) {
        this.speSize = speSize;
    }

    public Date getSpeExtinctionDate() {
        return speExtinctionDate;
    }

    public void setSpeExtinctionDate(Date speExtinctionDate) {
        this.speExtinctionDate = speExtinctionDate;
    }

    public String getSpeCharacteristics() {
        return speCharacteristics;
    }

    public void setSpeCharacteristics(String speCharacteristics) {
        this.speCharacteristics = speCharacteristics;
    }

    public Collection getSpeCollectionId() {
        return speCollectionId;
    }

    public void setSpeCollectionId(Collection speCollectionId) {
        this.speCollectionId = speCollectionId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (speId != null ? speId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Species)) {
            return false;
        }
        Species other = (Species) object;
        if ((this.speId == null && other.speId != null) || (this.speId != null && !this.speId.equals(other.speId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "models.Species[ speId=" + speId + " ]";
    }
    
}
