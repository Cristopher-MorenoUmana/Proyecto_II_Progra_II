package models;

import java.io.Serializable;

@javax.persistence.Entity
@javax.persistence.Table(name = "TBL_THEMES")
@javax.persistence.NamedQueries({
    @javax.persistence.NamedQuery(name = "hemes.findAll", query = "SELECT h FROM hemes h"),
    @javax.persistence.NamedQuery(name = "hemes.findByThmId", query = "SELECT h FROM hemes h WHERE h.thmId = :thmId"),
    @javax.persistence.NamedQuery(name = "hemes.findByThmName", query = "SELECT h FROM hemes h WHERE h.thmName = :thmName"),
    @javax.persistence.NamedQuery(name = "hemes.findByThmCharacteristics", query = "SELECT h FROM hemes h WHERE h.thmCharacteristics = :thmCharacteristics"),
    @javax.persistence.NamedQuery(name = "hemes.findByThmEra", query = "SELECT h FROM hemes h WHERE h.thmEra = :thmEra")})
public class Theme implements Serializable {

    private static final long serialVersionUID = 1L;
   
    @javax.persistence.Id
    @javax.persistence.GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
    @javax.persistence.Basic(optional = false)
    @javax.persistence.Column(name = "THM_ID")
    private Integer thmId;
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
    private Room thmRoomId;

    public Theme() {
    }

    public Theme(Integer thmId) {
        this.thmId = thmId;
    }

    public Theme(Integer thmId, String thmName, String thmCharacteristics, String thmEra) {
        this.thmId = thmId;
        this.thmName = thmName;
        this.thmCharacteristics = thmCharacteristics;
        this.thmEra = thmEra;
    }
    
    public Theme(ThemeDto pThemeDto) {
        
        updateTheme(pThemeDto);
    }

    public final void updateTheme(ThemeDto pThemeDto){
     
        this.thmCharacteristics = pThemeDto.getCharacteristics();
        this.thmEra = pThemeDto.getEra();
        this.thmName = pThemeDto.getName();
        this.thmRoomId = pThemeDto.getRoom();
    }
    
    public Integer getThmId() {
        return thmId;
    }

    public void setThmId(Integer thmId) {
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

    public Room getThmRoomId() {
        return thmRoomId;
    }

    public void setThmRoomId(Room thmRoomId) {
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
        
        if (!(object instanceof Theme)) {
            return false;
        }
        Theme other = (Theme) object;
        return !((this.thmId == null && other.thmId != null) || (this.thmId != null && !this.thmId.equals(other.thmId)));
    }

    @Override
    public String toString() {
        return "models.hemes[ thmId=" + thmId + " ]";
    }
    
}
