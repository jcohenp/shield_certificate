package com.bfwg.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.Date;

@Entity
@Table(name="CERTIFICAT")
public class Certificat {
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPathName() {
        return pathName;
    }

    public void setPathName(String pathName) {
        this.pathName = pathName;
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
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    public String getOrganizationalUnitName() {
        return organizationalUnitName;
    }

    public void setOrganizationalUnitName(String organizationalUnitName) {
        this.organizationalUnitName = organizationalUnitName;
    }

    public String getCommonName() {
        return commonName;
    }

    public void setCommonName(String commonName) {
        this.commonName = commonName;
    }

    public String getEmailAdress() {
        return emailAdress;
    }

    public void setEmailAdress(String emailAdress) {
        this.emailAdress = emailAdress;
    }

    public String getDateValidity() {
        return dateValidity;
    }

    public void setDateValidity(String dateValidity) {
        this.dateValidity = dateValidity;
    }

    public Boolean getValid() {
        return valid;
    }

    public void setValid(Boolean valid) {
        this.valid = valid;
    }

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @Column(name = "pathName")
    private String pathName;

    @Column(name = "creation")
    private Date creation;

    @Column(name = "country")
    private String country;

    @Column(name = "state")
    private String state;

    @Column(name = "locality")
    private String locality;

    @Column(name = "organizationName")
    private String organizationName;

    @Column(name = "organizationalUnitName")
    private String organizationalUnitName;

    @Column(name = "commonName")
    private String commonName;

    @Column(name = "emailAdress")
    private String emailAdress;

    @Column(name = "dateValidity")
    private String dateValidity;

    @Column(name = "valid")
    private Boolean valid;


    /*
    @Column(name = "version")
    private int version;

    @Column(name = "subject")
    private String subject;

    @JsonIgnore
    @Column(name = "signature_algo")
    private String signature_algo;

    @Column(name = "OID")
    private String OID;

    @Column(name = "key")
    private String key;

    @Column(name = "issuer")
    private String issuer;

    @Column(name = "signature")
    private byte[] signature;


    public void setData(String data) {
        this.data = data;
    }

    @Column(name = "data")
    private String data;

    public void setSerialNumber(BigInteger serialNumber) {
        this.serialNumber = serialNumber;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getSignature_algo() {
        return signature_algo;
    }

    public void setSignature_algo(String signature_algo) {
        this.signature_algo = signature_algo;
    }

    public String getOID() {
        return OID;
    }

    public void setOID(String OID) {
        this.OID = OID;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String isIssuer() {
        return issuer;
    }

    public void setIssuer(String issuer) {
        this.issuer = issuer;
    }

    public byte[] getSignature() {
        return signature;
    }

    public void setSignature(byte[] signature) {
        this.signature = signature;
    }

    public BigInteger getSerialNumber() {
        return serialNumber;
    }

    public String getIssuer() {
        return issuer;
    }

    public String getData() {
        return data;
    }*/


    public void DisplayCert(Certificat certificat) {
        System.out.println("Id " + certificat.getId());
        System.out.println("NamePath " + certificat.getPathName());
        System.out.println("Date " + certificat.getDateValidity());
        System.out.println("Creation " + certificat.getCreation());
        System.out.println("Country " + certificat.getCountry());
        System.out.println("State " + certificat.getState());
        System.out.println("Locality " + certificat.getLocality());
        System.out.println("Organization " + certificat.getOrganizationName());
        System.out.println("OrganizationName " + certificat.getOrganizationalUnitName());
        System.out.println("CN " + certificat.getCommonName());
        System.out.println("Email "+ certificat.getEmailAdress());
    }
}
