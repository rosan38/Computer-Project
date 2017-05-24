/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.softwarica.game.model;

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
 * @author rosan
 */
@Entity
@Table(name = "score", catalog = "breakthewall", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Score.findAll", query = "SELECT s FROM Score s")
    , @NamedQuery(name = "Score.findById", query = "SELECT s FROM Score s WHERE s.id = :id")
    , @NamedQuery(name = "Score.findByScore", query = "SELECT s FROM Score s WHERE s.score = :score")
    , @NamedQuery(name = "Score.findByPlayerId", query = "SELECT s FROM Score s WHERE s.playerId = :playerId")})
public class Score implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "score")
    private int score;
    @Basic(optional = false)
    @Column(name = "playerId")
    private int playerId;

    public Score() {
    }

    public Score(Integer id) {
        this.id = id;
    }

    public Score(Integer id, int score, int playerId) {
        this.id = id;
        this.score = score;
        this.playerId = playerId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getPlayerId() {
        return playerId;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
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
        if (!(object instanceof Score)) {
            return false;
        }
        Score other = (Score) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.softwarica.game.model.Score[ id=" + id + " ]";
    }
    
}
