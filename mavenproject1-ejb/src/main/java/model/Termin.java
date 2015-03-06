/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Jelena
 */
@Entity
@Table(name = "termin")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Termin.findAll", query = "SELECT t FROM Termin t"),
//    @NamedQuery(name = "Termin.findByKonsultacijeId", query = "SELECT t FROM Termin t WHERE t.terminPK.konsultacijeId = :konsultacijeId"),
    @NamedQuery(name = "Termin.findByTerminId", query = "SELECT t FROM Termin t WHERE t.terminPK.terminId = :terminId"),
    @NamedQuery(name = "Termin.findByVreme", query = "SELECT t FROM Termin t WHERE t.vreme = :vreme"),
    @NamedQuery(name = "Termin.findByTema", query = "SELECT t FROM Termin t WHERE t.tema = :tema"),
    @NamedQuery(name = "Termin.findByKomentar", query = "SELECT t FROM Termin t WHERE t.komentar = :komentar")})
public class Termin implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @EmbeddedId
    protected TerminPK terminPK;
    
    @Column(name = "vreme")
    @Temporal(TemporalType.TIMESTAMP)
    private Date vreme;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "tema")
    private String tema;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 300)
    @Column(name = "komentar")
    private String komentar;
    
    @Basic(optional = false)
    @Size(min = 1, max = 10)
    @JoinColumn(name = "studentId", referencedColumnName = "studentId")
    @ManyToOne
    private Student student;
    
    @Basic(optional = false)
    @JoinColumn(name = "konsultacijeId", referencedColumnName = "konsultacijeId", insertable = false, updatable = false)
    @MapsId("konsultacijeid")
    @ManyToOne(optional = false)
    private Konsultacije konsultacije;

    public Termin() {
    }

    public Termin(TerminPK terminPK) {
        this.terminPK = terminPK;
    }

    public Termin(TerminPK terminPK, String tema, String komentar) {
        this.terminPK = terminPK;
        this.tema = tema;
        this.komentar = komentar;
    }

    public Termin(String konsultacijeId, String terminId) {
        this.terminPK = new TerminPK(konsultacijeId, terminId);
    }

    public TerminPK getTerminPK() {
        return terminPK;
    }

    public void setTerminPK(TerminPK terminPK) {
        this.terminPK = terminPK;
    }

    public Date getVreme() {
        return vreme;
    }

    public void setVreme(Date vreme) {
        this.vreme = vreme;
    }

    public String getTema() {
        return tema;
    }

    public void setTema(String tema) {
        this.tema = tema;
    }

    public String getKomentar() {
        return komentar;
    }

    public void setKomentar(String komentar) {
        this.komentar = komentar;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student studentId) {
        this.student = studentId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (terminPK != null ? terminPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Termin)) {
            return false;
        }
        Termin other = (Termin) object;
        if ((this.terminPK == null && other.terminPK != null) || (this.terminPK != null && !this.terminPK.equals(other.terminPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Termin[ terminPK=" + terminPK + " ]";
    }

    /**
     * @return the konsultacije
     */
    public Konsultacije getKonsultacije() {
        return konsultacije;
    }

    /**
     * @param konsultacije the konsultacije to set
     */
    public void setKonsultacije(Konsultacije konsultacije) {
        this.konsultacije = konsultacije;
    }
    
}
