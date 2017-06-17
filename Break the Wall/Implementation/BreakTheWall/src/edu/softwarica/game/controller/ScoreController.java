/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.softwarica.game.controller;

import edu.softwarica.game.database.DatabaseConnection;
import edu.softwarica.game.game.Game;
import edu.softwarica.game.model.Score;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author rosan
 */
public class ScoreController {

    String query = "";
    private ResultSet rs = null;
    private PreparedStatement pstm = null;
    private Connection con;
    Game game;
    int id;

    public ScoreController(int id) {
        this.id = id;
        this.con = DatabaseConnection.getConnection();
    }

    public int addScore(Score score) {
        int isCreated = 0;
        String query = "insert into score(score,playerId) values(?,?)";
        try {
            pstm = con.prepareStatement(query);
            pstm.setInt(1, score.getScore());
            pstm.setInt(2, score.getPlayerId());
            isCreated = pstm.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        return isCreated;
    }

    public List<Integer> getScore(Score score) {
        List<Integer> scr = new ArrayList<>();
        String query = "select * from score where playerId=? group by score DESC LIMIT 5";
        try {
            pstm = con.prepareStatement(query);
            pstm.setInt(1, id);
            rs = pstm.executeQuery();
            while (rs.next()) {
                scr.add(rs.getInt("score"));
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        return scr;
    }

}
