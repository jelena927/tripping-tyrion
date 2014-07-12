/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Jelena
 */
@Entity
@Table(name = "konsultacije")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Konsultacije.findAll", query = "SELECT k FROM Konsultacije k"),
    @NamedQuery(name = "Konsultacije.findByKonsultacijeId", query = "SELECT k FROM Konsultacije k WHERE k.konsultacijeId = :konsultacijeId"),
    @NamedQuery(name = "Konsultacije.findByVremePocetka", query = "SELECT k FROM Konsultacije k WHERE k.vremePocetka = :vremePocetka"),
    @NamedQuery(name = "Konsultacije.findByVremeZavrsetka", query = "SELECT k FROM Konsultacije k WHERE k.vremeZavrsetka = :vremeZavrsetka"),
    @NamedQuery(name = "Konsultacije.findByTrajanjeJednogTermina", query = "SELECT k FROM Konsultacije k WHERE k.trajanjeJednogTermina = :trajanjeJednogTermina")})
public class Konsultacije implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "konsultacijeId")
    private String konsultacijeId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "vremePocetka")
    @Temporal(TemporalType.TIMESTAMP)
    private Date vremePocetka;
    @Basic(optional = false)
    @NotNull
    @Column(name = "vremeZavrsetka")
    @Temporal(TemporalType.TIMESTAMP)
    private Date vremeZavrsetka;
    @Column(name = "trajanjeJednogTermina")
    private Integer trajanjeJednogTermina;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "konsultacije", fetch = FetchType.EAGER)
    private Set<Termin> terminList;
    @JoinColumn(name = "profesorId", referencedColumnName = "profesorId")
    @ManyToOne(optional = false)
    private Profesor profesorId;
    @JoinColumn(name = "predmetId", referencedColumnName = "predmetId")
    @ManyToOne(optional = false)
    private Predmet predmetId;

    public Konsultacije() {
    }

    public Konsultacije(String konsultacijeId) {
        this.konsultacijeId = konsultacijeId;
    }

    public Konsultacije(String konsultacijeId, Date vremePocetka, Date vremeZavrsetka) {
        this.konsultacijeId = konsultacijeId;
        this.vremePocetka = vremePocetka;
        this.vremeZavrsetka = vremeZavrsetka;
    }

    public String getKonsultacijeId() {
        return konsultacijeId;
    }

    public void setKonsultacijeId(String konsultacijeId) {
        this.konsultacijeId = konsultacijeId;
    }

    public Date getVremePocetka() {
        return vremePocetka;
    }

    public void setVremePocetka(Date vremePocetka) {
        this.vremePocetka = vremePocetka;
    }

    public Date getVremeZavrsetka() {
        return vremeZavrsetka;
    }

    public void setVremeZavrsetka(Date vremeZavrsetka) {
        this.vremeZavrsetka = vremeZavrsetka;
    }

    public Integer getTrajanjeJednogTermina() {
        return trajanjeJednogTermina;
    }

    public void setTrajanjeJednogTermina(Integer trajanjeJednogTermina) {
        this.trajanjeJednogTermina = trajanjeJednogTermina;
    }

    @XmlTransient
    public Set<Termin> getTerminList() {
        return terminList;
    }

    public void setTerminList(Set<Termin> terminList) {
        this.terminList = terminList;
    }

    public Profesor getProfesorId() {
        return profesorId;
    }

    public void setProfesorId(Profesor profesorId) {
        this.profesorId = profesorId;
    }

    public Predmet getPredmetId() {
        return predmetId;
    }

    public void setPredmetId(Predmet predmetId) {
        this.predmetId = predmetId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (konsultacijeId != null ? konsultacijeId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Konsultacije)) {
            return false;
        }
        Konsultacije other = (Konsultacije) object;
        if ((this.konsultacijeId == null && other.konsultacijeId != null) || (this.konsultacijeId != null && !this.konsultacijeId.equals(other.konsultacijeId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Konsultacije[ konsultacijeId=" + konsultacijeId + " ]";
    }
    
}
