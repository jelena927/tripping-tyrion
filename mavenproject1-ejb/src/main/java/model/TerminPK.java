/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Jelena
 */
@Embeddable
public class TerminPK implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Basic(optional = false)
    @JoinColumn(name = "konsultacijeId", referencedColumnName = "konsultacijeId", insertable = false, updatable = false)
    @MapsId("konsultacijeid")
    @ManyToOne(optional = false)
    private Konsultacije konsultacije;
    
    @Column(name = "vreme")
    @Temporal(TemporalType.TIMESTAMP)
    private Date vreme;

    public TerminPK() {
    }

    public Konsultacije getKonsultacije() {
        return konsultacije;
    }

    public void setKonsultacije(Konsultacije konsultacije) {
        this.konsultacije = konsultacije;
    }

    public Date getVreme() {
        return vreme;
    }

    public void setVreme(Date vreme) {
        this.vreme = vreme;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash += (konsultacije != null) ? konsultacije.hashCode():0;
        hash += (vreme != null) ? vreme.hashCode():0;
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
        final TerminPK other = (TerminPK) obj;
        if (this.konsultacije != other.konsultacije && (this.konsultacije == null || !this.konsultacije.equals(other.konsultacije))) {
            return false;
        }
        if (this.vreme != other.vreme && (this.vreme == null || !this.vreme.equals(other.vreme))) {
            return false;
        }
        return true;
    }
}
