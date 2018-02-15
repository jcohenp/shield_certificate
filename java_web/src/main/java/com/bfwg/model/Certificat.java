package com.bfwg.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigInteger;
import java.sql.Timestamp;

public class Certificat {
    @Id
    @Column(name = "serialNumber")
    private BigInteger serialNumber;

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
    }
}
