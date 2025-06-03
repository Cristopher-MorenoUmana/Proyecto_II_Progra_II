package models;

import java.io.Serializable;

@javax.persistence.Entity
@javax.persistence.Table(name = "TBL_PRICES")
@javax.persistence.NamedQueries({
    @javax.persistence.NamedQuery(name = "Prices.findAll", query = "SELECT p FROM Prices p"),
    @javax.persistence.NamedQuery(name = "Prices.findByPriId", query = "SELECT p FROM Prices p WHERE p.priId = :priId"),
    @javax.persistence.NamedQuery(name = "Prices.findByPriType", query = "SELECT p FROM Prices p WHERE p.priType = :priType"),
    @javax.persistence.NamedQuery(name = "Prices.findByPriAmount", query = "SELECT p FROM Prices p WHERE p.priAmount = :priAmount")})
public class Price implements Serializable {

    private static final long serialVersionUID = 1L;

    @javax.persistence.Id
    @javax.persistence.GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
    @javax.persistence.Basic(optional = false)
    @javax.persistence.Column(name = "PRI_ID")
    private Integer priId;
    @javax.persistence.Basic(optional = false)
    @javax.persistence.Column(name = "PRI_TYPE")
    private Integer priType;
    @javax.persistence.Basic(optional = false)
    @javax.persistence.Column(name = "PRI_AMOUNT")
    private Double priAmount;
    @javax.persistence.JoinColumn(name = "PRI_ROOM_ID", referencedColumnName = "ROOM_ID")
    @javax.persistence.ManyToOne(optional = false, fetch = javax.persistence.FetchType.EAGER)
    private Room priRoomId;

    public Price() {
    }

    public Price(Integer priId) {
        this.priId = priId;
    }

    public Price(Integer priId, Integer priType, Double priAmount) {
        this.priId = priId;
        this.priType = priType;
        this.priAmount = priAmount;
    }

    public Price(PriceDto pPriceDto) {
        
        updatePrice(pPriceDto);
    }
    public final void updatePrice(PriceDto pPriceDto) {
        
        this.priAmount = Double.valueOf(pPriceDto.getAmount());
        this.priRoomId = pPriceDto.getRoom();
        this.priType = Integer.valueOf(pPriceDto.getType());
    }
    
    public Integer getPriId() {
        return priId;
    }

    public void setPriId(Integer priId) {
        this.priId = priId;
    }

    public Integer getPriType() {
        return priType;
    }

    public void setPriType(Integer priType) {
        this.priType = priType;
    }

    public Double getPriAmount() {
        return priAmount;
    }

    public void setPriAmount(Double priAmount) {
        this.priAmount = priAmount;
    }

    public Room getPriRoomId() {
        return priRoomId;
    }

    public void setPriRoomId(Room priRoomId) {
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
        
        if (!(object instanceof Price)) {
            return false;
        }
        Price other = (Price) object;
        return !((this.priId == null && other.priId != null) || (this.priId != null && !this.priId.equals(other.priId)));
    }

    @Override
    public String toString() {
        return "models.Prices[ priId=" + priId + " ]";
    }
    
}
