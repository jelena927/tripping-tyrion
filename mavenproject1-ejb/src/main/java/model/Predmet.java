/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Jelena
 */
@Entity
@Table(name = "predmet")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Predmet.findAll", query = "SELECT p FROM Predmet p"),
    @NamedQuery(name = "Predmet.findByPredmetId", query = "SELECT p FROM Predmet p WHERE p.predmetId = :predmetId"),
    @NamedQuery(name = "Predmet.findByNaziv", query = "SELECT p FROM Predmet p WHERE p.naziv = :naziv"),
    @NamedQuery(name = "Predmet.findByPrefix", query = "SELECT p FROM Predmet p WHERE p.naziv like :naziv")})
public class Predmet implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @Basic(optional = false)
    @Column(name = "predmetId")
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long predmetId;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "naziv")
    private String naziv;
    
    @OneToMany( mappedBy = "predmetId", targetEntity = Konsultacije.class)
    private List<Konsultacije> konsultacijeList;
    
    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "predmetList", targetEntity = Profesor.class)
    private List<Profesor> profesorList;

    public Predmet() {
    }

    public Predmet(Long predmetId) {
        this.predmetId = predmetId;
    }

    public Predmet(Long predmetId, String naziv) {
        this.predmetId = predmetId;
        this.naziv = naziv;
    }

    public Long getPredmetId() {
        return predmetId;
    }

    public void setPredmetId(Long predmetId) {
        this.predmetId = predmetId;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    @XmlTransient
    public List<Konsultacije> getKonsultacijeList() {
        return konsultacijeList;
    }

    public void setKonsultacijeList(List<Konsultacije> konsultacijeList) {
        this.konsultacijeList = konsultacijeList;
    }

    public List<Profesor> getProfesorList() {
        return profesorList;
    }

    public void setProfesorList(List<Profesor> profesorList) {
        this.profesorList = profesorList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (predmetId != null ? predmetId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Predmet)) {
            return false;
        }
        Predmet other = (Predmet) object;
        if ((this.predmetId == null && other.predmetId != null) || (this.predmetId != null && !this.predmetId.equals(other.predmetId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Predmet[ predmetId=" + predmetId + " ]";
    }
    
}
