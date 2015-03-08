/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
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
//    @NamedQuery(name = "Termin.findByTerminId", query = "SELECT t FROM Termin t WHERE t.terminPK.terminId = :terminId"),
//    @NamedQuery(name = "Termin.findByVreme", query = "SELECT t FROM Termin t WHERE t.vreme = :vreme"),
    @NamedQuery(name = "Termin.findByTema", query = "SELECT t FROM Termin t WHERE t.tema = :tema"),
    @NamedQuery(name = "Termin.findByKomentar", query = "SELECT t FROM Termin t WHERE t.komentar = :komentar")})
public class Termin implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @EmbeddedId
    protected TerminPK terminPK;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "tema")
    private String tema;
    
    @Basic(optional = false)
    @Size(min = 1, max = 300)
    @Column(name = "komentar")
    private String komentar;
    
    @Basic(optional = false)
    @JoinColumn(name = "studentId", referencedColumnName = "studentId")
    @ManyToOne
    private Student student;

    public Termin() {
    }
    
    public TerminPK getTerminPK() {
        return terminPK;
    }

    public void setTerminPK(TerminPK terminPK) {
        this.terminPK = terminPK;
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
    public String toString() {
//        return "model.Termin[ terminPK=" + terminPK + " ]";
        return "model.Termin[ vreme=, konsultacijeid="; //+ konsultacijeid + " ]";
    }

    /**
     * @return the konsultacije
     */
//    public Konsultacije getKonsultacije() {
//        return konsultacije;
//    }
    /**
     * @param konsultacije the konsultacije to set
     */
//    public void setKonsultacije(Konsultacije konsultacije) {
//        this.konsultacije = konsultacije;
//        //prevent endless loop
//    if (this.getKonsultacije()==null? konsultacije == null : this.konsultacije.equals(konsultacije))
//      return ;
//    //set new owner
//    Konsultacije oldK = this.konsultacije;
//    this.konsultacije = konsultacije;
//    //remove from the old owner
//    if (oldK!=null)
//      oldK.removeTermin(this);
//    //set myself into new owner
//    if (konsultacije!=null)
//      konsultacije.addTermin(this);
//    }
    @Override
    public int hashCode() {
        int hash = 7;
        hash += terminPK.hashCode();
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Termin other = (Termin) obj;
        if (this.terminPK != other.terminPK && (this.terminPK == null || !this.terminPK.equals(other.terminPK))) {
            return false;
        }
        return true;
    }
    

}
