package com.bfwg.rest;

import com.bfwg.model.Certificat;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import java.security.cert.X509Certificate;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import sun.security.tools.keytool.CertAndKeyGen;
import sun.security.x509.X500Name;


@RestController
@RequestMapping( value = "/api", produces = MediaType.APPLICATION_JSON_VALUE )
public class CertificatController {

    @RequestMapping( method = POST, value = "/certificat/create_certificat" )
    @PreAuthorize("hasRole('ADMIN')")
    public Certificat create_certificat(@RequestBody HashMap date) throws ParseException {

        Date today = new Date();
        Date datepicker = new SimpleDateFormat("dd-MMM-yyyy").parse(date.get("date").toString());
        long date_validity = datepicker.getTime() - today.getTime();
        date_validity = date_validity / 1000;




        Certificat cert = new Certificat();
        try{
            CertAndKeyGen keyGen=new CertAndKeyGen("RSA","SHA1WithRSA",null);
            keyGen.generate(1024);

            //Generate self signed certificate
            X509Certificate[] chain=new X509Certificate[1];
            chain[0]=keyGen.getSelfCertificate(new X500Name("CN=ROOT"), (long)date_validity);

            cert.setSerialNumber(chain[0].getSerialNumber());
            cert.setVersion(chain[0].getVersion());
            cert.setSubject(chain[0].getSubjectDN().toString());
            cert.setSignature_algo(chain[0].getSigAlgName());
            cert.setOID(chain[0].getSigAlgOID());
            cert.setKey(chain[0].getPublicKey().toString());
            cert.setIssuer(chain[0].getIssuerDN().toString());
            cert.setSignature(chain[0].getSignature());
            cert.setData(chain[0].toString());
            System.out.println("Certificate : "+chain[0].toString());
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return cert;
    }
}
