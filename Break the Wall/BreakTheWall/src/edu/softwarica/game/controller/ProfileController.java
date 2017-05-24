/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.softwarica.game.controller;

import edu.softwarica.game.database.DatabaseConnection;
import edu.softwarica.game.model.Profile;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author rosan
 */
public class ProfileController {

    String query = "";
    private ResultSet rs = null;
    private PreparedStatement pstm = null;
    private Connection con;

    public ProfileController() {

        this.con = DatabaseConnection.getConnection();
    }

    public boolean authenticate(Profile profile) {

        boolean isUserAvailable = false;
        try {
            query = "select * from profile where email = ? and password =?";
            pstm = con.prepareStatement(query);
            pstm.setString(1, profile.getEmail());
            pstm.setString(2, profile.getPassword());
            rs = pstm.executeQuery();
            while (rs.next()) {
                isUserAvailable = true;

            }
        } catch (SQLException ex) {
            Logger.getLogger(ProfileController.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        return isUserAvailable;
    }

    public void addProfile(Profile profile) throws SQLException {
        query = "select * from profile where email = ?";
        pstm = con.prepareStatement(query);
        pstm.setString(1, profile.getEmail());
        rs = pstm.executeQuery();
        String em = profile.getEmail();
        String email = null;
        while (rs.next()) {
            email = rs.getString("email");
        }
        if (!em.equals(email)) {
            try {
                query = "Insert into profile(first_name,last_name,email,password,address) values(?,?,?,?,?)";
                pstm = con.prepareStatement(query);
                pstm.setString(1, profile.getFirstName());
                pstm.setString(2, profile.getLastName());
                pstm.setString(3, profile.getEmail());
                pstm.setString(4, profile.getPassword());
                pstm.setString(5, profile.getAddress());
                int status = pstm.executeUpdate();
                if (status == 1) {
                    JOptionPane.showMessageDialog(null, "profile created sucessfully!");
                } else {
                    JOptionPane.showMessageDialog(null, "Sorry could not create profile!");
                }
            } catch (SQLException ex) {
                Logger.getLogger(ProfileController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Email is already used. Try new !!");
        }

    }

    public int getUserId(Profile profile) {
        int userid = 0;
        String query = "select * from profile where email=? and password=?";
        try {
            pstm = con.prepareStatement(query);
            pstm.setString(1, profile.getEmail());
            pstm.setString(2, profile.getPassword());
            rs = pstm.executeQuery();
            while (rs.next()) {
                userid = rs.getInt("Id");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        return userid;
    }
}
