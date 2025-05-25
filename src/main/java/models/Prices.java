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
@javax.persistence.Table(name = "TBL_PRICES")
@javax.persistence.NamedQueries({
    @javax.persistence.NamedQuery(name = "Prices.findAll", query = "SELECT p FROM Prices p"),
    @javax.persistence.NamedQuery(name = "Prices.findByPriId", query = "SELECT p FROM Prices p WHERE p.priId = :priId"),
    @javax.persistence.NamedQuery(name = "Prices.findByPriType", query = "SELECT p FROM Prices p WHERE p.priType = :priType"),
    @javax.persistence.NamedQuery(name = "Prices.findByPriAmount", query = "SELECT p FROM Prices p WHERE p.priAmount = :priAmount")})
public class Prices implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @javax.persistence.Id
    @javax.persistence.GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
    @javax.persistence.Basic(optional = false)
    @javax.persistence.Column(name = "PRI_ID")
    private BigDecimal priId;
    @javax.persistence.Basic(optional = false)
    @javax.persistence.Column(name = "PRI_TYPE")
    private short priType;
    @javax.persistence.Basic(optional = false)
    @javax.persistence.Column(name = "PRI_AMOUNT")
    private BigDecimal priAmount;
    @javax.persistence.JoinColumn(name = "PRI_ROOM_ID", referencedColumnName = "ROOM_ID")
    @javax.persistence.ManyToOne(optional = false, fetch = javax.persistence.FetchType.EAGER)
    private Rooms priRoomId;

    public Prices() {
    }

    public Prices(BigDecimal priId) {
        this.priId = priId;
    }

    public Prices(BigDecimal priId, short priType, BigDecimal priAmount) {
        this.priId = priId;
        this.priType = priType;
        this.priAmount = priAmount;
    }

    public BigDecimal getPriId() {
        return priId;
    }

    public void setPriId(BigDecimal priId) {
        this.priId = priId;
    }

    public short getPriType() {
        return priType;
    }

    public void setPriType(short priType) {
        this.priType = priType;
    }

    public BigDecimal getPriAmount() {
        return priAmount;
    }

    public void setPriAmount(BigDecimal priAmount) {
        this.priAmount = priAmount;
    }

    public Rooms getPriRoomId() {
        return priRoomId;
    }

    public void setPriRoomId(Rooms priRoomId) {
        this.priRoomId = priRoomId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (priId != null ? priId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Prices)) {
            return false;
        }
        Prices other = (Prices) object;
        if ((this.priId == null && other.priId != null) || (this.priId != null && !this.priId.equals(other.priId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "models.Prices[ priId=" + priId + " ]";
    }
    
}
