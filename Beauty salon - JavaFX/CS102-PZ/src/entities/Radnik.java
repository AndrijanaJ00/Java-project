package entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Andrijana Jovanovic
 */
@Entity
@Table(name = "radnik")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Radnik.findAll", query = "SELECT r FROM Radnik r")
    , @NamedQuery(name = "Radnik.findById", query = "SELECT r FROM Radnik r WHERE r.id = :id")
    , @NamedQuery(name = "Radnik.findByIme", query = "SELECT r FROM Radnik r WHERE r.ime = :ime")
    , @NamedQuery(name = "Radnik.findByPrezime", query = "SELECT r FROM Radnik r WHERE r.prezime = :prezime")
    , @NamedQuery(name = "Radnik.findBySpecijalnost", query = "SELECT r FROM Radnik r WHERE r.specijalnost = :specijalnost")
    , @NamedQuery(name = "Radnik.findByZarada", query = "SELECT r FROM Radnik r WHERE r.zarada = :zarada")})
public class Radnik implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "ime")
    private String ime;
    @Basic(optional = false)
    @Column(name = "prezime")
    private String prezime;
    @Basic(optional = false)
    @Column(name = "specijalnost")
    private String specijalnost;
    @Basic(optional = false)
    @Column(name = "zarada")
    private int zarada;

    public Radnik() {
    }

    public Radnik(Integer id) {
        this.id = id;
    }

    public Radnik(Integer id, String ime, String prezime, String specijalnost, int zarada) {
        this.id = id;
        this.ime = ime;
        this.prezime = prezime;
        this.specijalnost = specijalnost;
        this.zarada = zarada;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getSpecijalnost() {
        return specijalnost;
    }

    public void setSpecijalnost(String specijalnost) {
        this.specijalnost = specijalnost;
    }

    public int getZarada() {
        return zarada;
    }

    public void setZarada(int zarada) {
        this.zarada = zarada;
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
        if (!(object instanceof Radnik)) {
            return false;
        }
        Radnik other = (Radnik) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Ime: " + this.ime + "\nPrezime: " + this.prezime + "\nSpecijalnost: " + this.specijalnost;
    }
    
    public int racunajZaradu(Usluga usluga) {
        return (int)(usluga.getCena() * 0.5);
    }
}
