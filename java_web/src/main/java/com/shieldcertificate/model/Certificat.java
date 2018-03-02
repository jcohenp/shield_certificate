package com.shieldcertificate.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.math.BigInteger;
import java.sql.Clob;
import java.sql.Timestamp;
import java.util.Date;

@Entity
@Table(name = "CERTIFICAT")
public class Certificat {
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPathName(String path) {
        this.path = path;
    }

    public Date getCreation() {
        return creation;
    }

    public void setCreation(Date creation) {
        this.creation = creation;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getLocality() {
        return locality;
    }

    public void setLocality(String locality) {
        this.locality = locality;
    }

    public String getOrganizationName() {
        return organizationname;
    }

    public void setOrganizationName(String organizationname) {
        this.organizationname = organizationname;
    }

    public String getOrganizationalUnitName() {
        return organizationalunitname;
    }

    public void setOrganizationalUnitName(String organizationalunitname) {
        this.organizationalunitname = organizationalunitname;
    }

    public String getCommonName() {
        return commonname;
    }

    public void setCommonName(String commonname) {
        this.commonname = commonname;
    }

    public String getEmailAdress() {
        return emailadress;
    }

    public void setEmailAdress(String emailadress) {
        this.emailadress = emailadress;
    }

    public String getDateValidity() {
        return datevalidity;
    }

    public void setDateValidity(String dateValidity) {
        this.datevalidity = dateValidity;
    }

    public Boolean getValid() {
        return valid;
    }

    public void setValid(Boolean valid) {
        this.valid = valid;
    }

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "path")
    private String path;

    @Column(name = "creation")
    private Date creation;

    @Column(name = "country")
    private String country;

    public Clob getValuecertificate() {
        return valuecertificate;
    }

    public void setValuecertificate(Clob valuecertificate) {
        this.valuecertificate = valuecertificate;
    }

    @JsonIgnore
    @Column(name = "valuecertificate")
    private Clob valuecertificate;

    @Column(name = "state")
    private String state;

    @Column(name = "locality")
    private String locality;

    @Column(name = "organizationname")
    private String organizationname;

    @Column(name = "organizationalunitname")
    private String organizationalunitname;

    @Column(name = "commonname")
    private String commonname;

    @Column(name = "emailadress")
    private String emailadress;

    @Column(name = "datevalidity")
    private String datevalidity;

    @Column(name = "valid")
    private Boolean valid;

    @Column(name = "path_client_cert")
    private String path_client_cert;

    public String getPath_client_cert() {
        return path_client_cert;
    }

    public void setPath_client_cert(String path_client_cert) {
        this.path_client_cert = path_client_cert;
    }

    public String getCert() {
        return cert;
    }

    public void setCert(String cert) {
        this.cert = cert;
    }

    private String cert;



    public void DisplayCert(Certificat certificat) {
        System.out.println("Id " + certificat.getId());
        System.out.println("Pa " + certificat.getPath());
        System.out.println("Date " + certificat.getDateValidity());
        System.out.println("Creation " + certificat.getCreation());
        System.out.println("Country " + certificat.getCountry());
        System.out.println("State " + certificat.getState());
        System.out.println("Locality " + certificat.getLocality());
        System.out.println("Organization " + certificat.getOrganizationName());
        System.out.println("OrganizationName " + certificat.getOrganizationalUnitName());
        System.out.println("CN " + certificat.getCommonName());
        System.out.println("Email " + certificat.getEmailAdress());
    }
}
