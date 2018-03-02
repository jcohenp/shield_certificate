package com.bfwg.model;

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

    public String getCert() {
        return cert;
    }

    public void setCert(String cert) {
        this.cert = cert;
    }

    private String cert;

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
