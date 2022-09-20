package entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Andrijana Jovanovic
 */
@Entity
@Table(name = "tretman")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tretman.findAll", query = "SELECT t FROM Tretman t")
    , @NamedQuery(name = "Tretman.findById", query = "SELECT t FROM Tretman t WHERE t.id = :id")
  //  , @NamedQuery(name = "Tretman.findByStatusId", query = "SELECT t FROM Tretman t WHERE t.idStatusa = :id")
})
public class Tretman implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @JoinColumn(name = "id_klijenta", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Klijent idKlijenta;
    @JoinColumn(name = "id_radnika", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Radnik idRadnika;
    @JoinColumn(name = "id_usluge", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Usluga idUsluge;
    @JoinColumn(name = "id_statusa", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Status idStatusa;

    public Tretman() {
    }

    public Tretman(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Klijent getIdKlijenta() {
        return idKlijenta;
    }

    public void setIdKlijenta(Klijent idKlijenta) {
        this.idKlijenta = idKlijenta;
    }

    public Radnik getIdRadnika() {
        return idRadnika;
    }

    public void setIdRadnika(Radnik idRadnika) {
        this.idRadnika = idRadnika;
    }

    public Usluga getIdUsluge() {
        return idUsluge;
    }

    public void setIdUsluge(Usluga idUsluge) {
        this.idUsluge = idUsluge;
    }

    public Status getIdStatusa() {
        return idStatusa;
    }

    public void setIdStatusa(Status idStatusa) {
        this.idStatusa = idStatusa;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tretman)) {
            return false;
        }
        Tretman other = (Tretman) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Radnik: " + this.idRadnika +  "\nKlijent: " + this.idKlijenta + "\nUsluga " + this.idUsluge;
    }
    
}

/*
        return "Model: " + this.model + "\nBoja: " + this.boja + "\nKubikaza: " + this.kubikaza + "\nSnaga motora: " + this.snagaMotora + "\nCena: " + this.cena;

*/
