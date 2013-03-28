/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.sql.Time;
import java.util.Date;
import javax.persistence.*;
/**
 *
 * @author Thalita
 */
@Entity
@Table(name="compromisso")
public class Compromisso implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idcompromisso")
    private Integer id;  
    private String titulo;
    private String descricao;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dia;
    @Temporal(javax.persistence.TemporalType.TIME)
    private Date hora;
    private Integer status;
    @ManyToOne
    @JoinColumn(name = "idpessoa")
    private Pessoa usuario;

 
    
    public Compromisso() {
        
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Date getDia() {
        return dia;
    }

    public void setDia(Date dia) {
        this.dia = dia;
    }

    public Date getHora() {
        return hora;
    }

    public void setHora(Date hora) {
        this.hora = hora;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Pessoa getUsuario() {
        return usuario;
    }

    public void setUsuario(Pessoa usuario) {
        this.usuario = usuario;
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
        if (!(object instanceof Compromisso)) {
            return false;
        }
        Compromisso other = (Compromisso) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.Compromisso[ id="+ id +" ]";
    }
    
}
