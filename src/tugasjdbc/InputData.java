/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tugasjdbc;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;
/**
 *
 * @author acer
 */
public class InputData extends JFrame {
    public String username, password;
    Connector connector = new Connector(); 
    Statement statement;
    ResultSet rs;
    
    //DEKLARASI KOMPONEN
    JFrame window = new JFrame("Tugas JDBC");
   
    JLabel lUserlogin= new JLabel("Username ");
    JTextField tfUserlogin = new JTextField();
    JLabel lPwlogin = new JLabel("Password ");
    JTextField tfPwlogin = new JTextField();
    JButton btnLogin = new JButton("LOGIN");
    JLabel lUserdaftar = new JLabel("Username  ");
    JTextField tfUserdaftar = new JTextField();
    JLabel lPwdaftar = new JLabel("Password  ");
    JTextField tfPwdaftar = new JTextField();
    JButton btnDaftar = new JButton("DAFTAR");

    public InputData() {
        window.setLayout(null);
        window.setSize(400,200);
        window.setVisible(true);
        window.setLocationRelativeTo(null);
        window.setResizable(false);
        window.setDefaultCloseOperation(3);

        //ADD COMPONENT
        window.add(lUserlogin);
        window.add(tfUserlogin);
        window.add(lPwlogin);
        window.add(tfPwlogin);
        window.add(btnLogin);
        window.add(lUserdaftar);
        window.add(tfUserdaftar);
        window.add(tfPwdaftar);
        window.add(tfPwdaftar);
        window.add(btnDaftar);
        
        //KIRI
        lUserlogin.setBounds(25, 15, 120, 25);
        tfUserlogin.setBounds(25, 40, 120, 25);
        lPwlogin.setBounds(25, 65, 120, 25);
        tfPwlogin.setBounds(25, 90, 120, 25);
        btnLogin.setBounds(25, 115, 120, 25);
        
        //KANAN
        lUserdaftar.setBounds(220, 15, 120, 25);
        tfUserdaftar.setBounds(220, 40, 120, 25);
        lPwdaftar.setBounds(220, 65, 120, 25);
        tfPwdaftar.setBounds(220, 90, 120, 25);
        btnDaftar.setBounds(220, 115, 120, 25);
        
        btnDaftar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                try {
                    String query = "SELECT * FROM `users` WHERE username = '" + getUserdaftar() +"'"; 
                    connector.statement = connector.koneksi.createStatement();
                    ResultSet rs = connector.statement.executeQuery(query);
                    if (rs.next()) {
                        JOptionPane.showMessageDialog(rootPane, "Username Sudah Ada");
                    } else {
                        String inputData = "INSERT INTO `users` (`username`, `password`) VALUES ( '"+ getUserdaftar()+"','"+getPwdaftar()+"')";           
                        connector.statement = connector.koneksi.createStatement();
                        connector.statement.executeUpdate(inputData);
                        System.out.println("Berhasil Mendaftar User");
                        JOptionPane.showMessageDialog(null,"Berhasil Mendaftar User");
                    }
                } catch (Exception ex){
                    System.out.println(ex.getMessage());
                }
            }
        });
        
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                try {
                    String query = "SELECT * FROM `users` WHERE username = '" + getUserlogin() +"'"; 
                    connector.statement = connector.koneksi.createStatement();
                    ResultSet rs = connector.statement.executeQuery(query);
                    if (rs.next()) {
                        if (getPwlogin().equals(rs.getString("password"))){
                            System.out.println("Login Sukses");
                            JOptionPane.showMessageDialog(null, "Login Sukses");
                        } else {
                            JOptionPane.showMessageDialog(rootPane,"Password Salah");
                        }
                    } else {
                        JOptionPane.showMessageDialog(rootPane, "Username Tidak Ditemukan");
                    }
                } catch (Exception ex){
                    System.out.println(ex.getMessage());
                }
            }
        });
        
    }
    
    public String getUserdaftar() {
        return tfUserdaftar.getText();
    }
    
    public String getPwdaftar(){
        return tfPwdaftar.getText();
    }

    public String getUserlogin() {
        return tfUserlogin.getText();
    }

    public String getPwlogin() {
        return tfPwlogin.getText();
    }
    
}
