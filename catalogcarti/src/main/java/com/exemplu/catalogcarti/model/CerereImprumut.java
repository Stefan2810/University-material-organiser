/** Clasa Model pentru obiectele de tip CerereImprumut
 * @author Marcu Stefan-Cristian
 * @version 12 Ianuarie 2025
 */
package com.exemplu.catalogcarti.model;

import jakarta.persistence.*;
@Entity
public class CerereImprumut {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String usernameCititor;
    private String titluCarte;
    private String status; // "in asteptare", "aprobat", "respins"

    public CerereImprumut() {}

    public CerereImprumut(String usernameCititor, String titluCarte, String status) {
        this.usernameCititor = usernameCititor;
        this.titluCarte = titluCarte;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsernameCititor() {
        return usernameCititor;
    }

    public void setUsernameCititor(String usernameCititor) {
        this.usernameCititor = usernameCititor;
    }

    public String getTitluCarte() {
        return titluCarte;
    }

    public void setTitluCarte(String titluCarte) {
        this.titluCarte = titluCarte;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
