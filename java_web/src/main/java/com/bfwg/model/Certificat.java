package com.bfwg.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Timestamp;

public class Certificat {
    @Id
    @Column(name = "serialNumber")
    private Long serialNumber;

    @Column(name = "version")
    private String version;

    @Column(name = "subject")
    private String subject;

    @JsonIgnore
    @Column(name = "signature_algo")
    private String signature_algo;

    @Column(name = "OID")
    private String OID;

    @Column(name = "key")
    private String key;

    @Column(name = "validity")
    private String validity;

    @Column(name = "issuer")
    private boolean issuer;


    @Column(name = "algorithm")
    private Timestamp algorithm;
    @Column(name = "signature")
    private Timestamp signature;

    public void setSerialNumber(Long serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
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

    public String getValidity() {
        return validity;
    }

    public void setValidity(String validity) {
        this.validity = validity;
    }

    public boolean isIssuer() {
        return issuer;
    }

    public void setIssuer(boolean issuer) {
        this.issuer = issuer;
    }


    public Timestamp getAlgorithm() {
        return algorithm;
    }

    public void setAlgorithm(Timestamp algorithm) {
        this.algorithm = algorithm;
    }

    public Timestamp getSignature() {
        return signature;
    }

    public void setSignature(Timestamp signature) {
        this.signature = signature;
    }
}
