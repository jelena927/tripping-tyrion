/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Jelena
 */
@Embeddable
public class TerminPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "konsultacijeId")
    private String konsultacijeId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "terminId")
    private String terminId;

    public TerminPK() {
    }

    public TerminPK(String konsultacijeId, String terminId) {
        this.konsultacijeId = konsultacijeId;
        this.terminId = terminId;
    }

    public String getKonsultacijeId() {
        return konsultacijeId;
    }

    public void setKonsultacijeId(String konsultacijeId) {
        this.konsultacijeId = konsultacijeId;
    }

    public String getTerminId() {
        return terminId;
    }

    public void setTerminId(String terminId) {
        this.terminId = terminId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (konsultacijeId != null ? konsultacijeId.hashCode() : 0);
        hash += (terminId != null ? terminId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TerminPK)) {
            return false;
        }
        TerminPK other = (TerminPK) object;
        if ((this.konsultacijeId == null && other.konsultacijeId != null) || (this.konsultacijeId != null && !this.konsultacijeId.equals(other.konsultacijeId))) {
            return false;
        }
        if ((this.terminId == null && other.terminId != null) || (this.terminId != null && !this.terminId.equals(other.terminId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.TerminPK[ konsultacijeId=" + konsultacijeId + ", terminId=" + terminId + " ]";
    }
    
}
