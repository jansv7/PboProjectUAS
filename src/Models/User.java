/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

/**
 *
 * @author USER
 */
public class User {
    private int user_id;
    private String username;
    private String password;

    public User(String username, String password, int user_id) {
        
        this.username = username;
        this.password = password;
        this.user_id = user_id;
    }

    public int getUserid() { return user_id; }
    public String getUsername() { return username; }
    public String getPassword() { return password; }
}
