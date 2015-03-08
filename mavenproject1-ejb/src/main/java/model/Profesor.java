/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
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
@Table(name = "profesor")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Profesor.findAll", query = "SELECT p FROM Profesor p"),
    @NamedQuery(name = "Profesor.findByProfesorId", query = "SELECT p FROM Profesor p WHERE p.profesorId = :profesorId"),
    @NamedQuery(name = "Profesor.findByIme", query = "SELECT p FROM Profesor p WHERE p.ime = :ime"),
    @NamedQuery(name = "Profesor.findByPrezime", query = "SELECT p FROM Profesor p WHERE p.prezime = :prezime"),
    @NamedQuery(name = "Profesor.findByEmail", query = "SELECT p FROM Profesor p WHERE p.email = :email"),
//    @NamedQuery(name = "Profesor.findByPredmet", query = "SELECT p FROM Profesor p WHERE p.predmetId = :predmetId"),
    @NamedQuery(name = "Profesor.findByPrefix", query = "SELECT p FROM Profesor p WHERE p.ime like :prefix or p.prezime like :prefix"),
//    @NamedQuery(name = "Profesor.findByPrefixAndPredmet", query = "SELECT p FROM Profesor p WHERE p.predmetId = :predmetId and (p.ime like :prefix or p.prezime like :prefix)")
})
public class Profesor extends Korisnik  implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long profesorId;
    private Set<Konsultacije> konsultacijeList;
    private Set<Predmet> predmetList;

    public Profesor() {
    }

    public Profesor(Long profesorId) {
        this.profesorId = profesorId;
    }

    @Id
    @Basic(optional = false)
    @Column(name = "profesorId")
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    public Long getProfesorId() {
        return profesorId;
    }

    public void setProfesorId(Long profesorId) {
        this.profesorId = profesorId;
    }

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "ime")
    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "prezime")
    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @OneToMany(mappedBy = "profesorId", fetch = FetchType.EAGER)
    @XmlTransient
    public Set<Konsultacije> getKonsultacijeList() {
        return konsultacijeList;
    }

    public void setKonsultacijeList(Set<Konsultacije> konsultacijeList) {
        this.konsultacijeList = konsultacijeList;
    }

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "predaje", joinColumns = { 
            @JoinColumn(name = "profesorId", nullable = false, updatable = false) }, 
            inverseJoinColumns = { @JoinColumn(name = "predmetId", 
                        nullable = false, updatable = false) })
//    @Fetch(value = FetchMode.SUBSELECT)
    public Set<Predmet> getPredmetList() {
        return predmetList;
    }

    public void setPredmetList(Set<Predmet> predmetList) {
        this.predmetList = predmetList;
    }

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "password")
    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += profesorId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Profesor)) {
            return false;
        }
        Profesor other = (Profesor) object;
        if (this.profesorId != other.profesorId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Profesor[ profesorId=" + profesorId + " ]";
    }
    
}
