/** Clasa Model pentru obiectele de tip Utilizator(fie el admin sau cititor)
 * @author Marcu Stefan-Cristian
 * @version 12 Ianuarie 2025
 */

package com.exemplu.catalogcarti.model;

import jakarta.persistence.*;

@Entity
public class Utilizator {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;
    private String rol; // "admin" sau "cititor"

    public Utilizator() {
    }

    public Utilizator(String username, String password, String rol) {
        this.username = username;
        this.password = password;
        this.rol = rol;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }
}
