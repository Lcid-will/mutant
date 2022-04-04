/*
 * Developed on 29/03/2022
 * by: William Rozo
 */

package com.co.lcid.mutant.db.model;
import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * The Class Dna.
 *
 * @author William_Rozo
 */


@Entity
@Table(name = "dna")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Dna.findAll", query = "SELECT d FROM Dna d"),
    @NamedQuery(name = "Dna.isMutant", query = "SELECT d.isMutan FROM Dna d where dna=:dna"),
    @NamedQuery(name = "Dna.findByIsMutan", query = "SELECT d FROM Dna d WHERE d.isMutan = :isMutan")})
public class Dna implements Serializable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;
    
    /** The dna. */
    @Id
    @Basic(optional = false)
    @Lob
    @Column(name = "dna")
    private String dna;
    
    /** The is mutan. */
    @Basic(optional = false)
    @Column(name = "is_mutan")
    private boolean isMutan;

    /**
     * Instantiates a new dna.
     */
    public Dna() {
    }

    /**
     * Instantiates a new dna.
     *
     * @param dna the dna
     */
    public Dna(String dna) {
        this.dna = dna;
    }

    /**
     * Instantiates a new dna.
     *
     * @param dna the dna
     * @param isMutan the is mutan
     */
    public Dna(String dna, boolean isMutan) {
        this.dna = dna;
        this.isMutan = isMutan;
    }

    /**
     * Gets the dna.
     *
     * @return the dna
     */
    public String getDna() {
        return dna;
    }

    /**
     * Sets the dna.
     *
     * @param dna the new dna
     */
    public void setDna(String dna) {
        this.dna = dna;
    }

    /**
     * Gets the checks if is mutan.
     *
     * @return the checks if is mutan
     */
    public boolean getIsMutan() {
        return isMutan;
    }

    /**
     * Sets the checks if is mutan.
     *
     * @param isMutan the new checks if is mutan
     */
    public void setIsMutan(boolean isMutan) {
        this.isMutan = isMutan;
    }

    /**
     * Hash code.
     *
     * @return the int
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (dna != null ? dna.hashCode() : 0);
        return hash;
    }

    /**
     * Equals.
     *
     * @param object the object
     * @return true, if successful
     */
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Dna)) {
            return false;
        }
        Dna other = (Dna) object;
        if ((this.dna == null && other.dna != null) || (this.dna != null && !this.dna.equals(other.dna))) {
            return false;
        }
        return true;
    }

    /**
     * To string.
     *
     * @return the string
     */
    @Override
    public String toString() {
        return "com.co.moon.sisvotph.models.entity.Dna[ dna=" + dna + " ]";
    }
    
}
