/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.c.finalproject.finalproject;

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
 *
 * @author USER DJOGJA
 */
@Entity
@Table(catalog = "finalproject", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Kependudukan.findAll", query = "SELECT k FROM Kependudukan k"),
    @NamedQuery(name = "Kependudukan.findById", query = "SELECT k FROM Kependudukan k WHERE k.id = :id"),
    @NamedQuery(name = "Kependudukan.findByNama", query = "SELECT k FROM Kependudukan k WHERE k.nama = :nama"),
    @NamedQuery(name = "Kependudukan.findByNik", query = "SELECT k FROM Kependudukan k WHERE k.nik = :nik"),
    @NamedQuery(name = "Kependudukan.findByAlamat", query = "SELECT k FROM Kependudukan k WHERE k.alamat = :alamat")})
public class Kependudukan implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer id;
    @Column(length = 30)
    private String nama;
    private Integer nik;
    @Column(length = 50)
    private String alamat;
    @Lob
    private byte[] picture;

    public Kependudukan() {
    }

    public Kependudukan(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public Integer getNik() {
        return nik;
    }

    public void setNik(Integer nik) {
        this.nik = nik;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public byte[] getPicture() {
        return picture;
    }

    public void setPicture(byte[] picture) {
        this.picture = picture;
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
        if (!(object instanceof Kependudukan)) {
            return false;
        }
        Kependudukan other = (Kependudukan) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ws.c.finalproject.finalproject.Kependudukan[ id=" + id + " ]";
    }
    
}
