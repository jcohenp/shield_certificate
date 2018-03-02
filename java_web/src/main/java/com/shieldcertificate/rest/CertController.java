package com.shieldcertificate.rest;

import com.shieldcertificate.model.Certificat;
import com.shieldcertificate.repository.CertificatRepository;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;
import java.sql.SQLException;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;


@RestController
public class CertController {


    @Autowired
    private CertificatRepository certificatRepository;

    @RequestMapping(method = GET, value = "/certificat/certInfo")
    public Certificat certInformation(@RequestParam("cert_name") String cert_name) {
        Certificat certificat = certificatRepository.findFirstByPathName(cert_name + ".pem");
        return certificat;
    }

    @RequestMapping(method = GET, value = "/certificat/getAllCert")
    public List<Certificat> getAllCert() throws SQLException, IOException {

     //   Certificat certificat = new Certificat();
     //   certificat.setValid();
        List<Certificat> certificat = certificatRepository.findAll();
        for (Certificat cur : certificat) {
            InputStream in = cur.getValuecertificate().getAsciiStream();
            StringWriter w = new StringWriter();
            IOUtils.copy(in,w);
            String certficateAsString = w.toString();
            cur.setCert(certficateAsString);
        }

        return certificat;
    }

    @RequestMapping(method = GET, value = "/certificat/validation", produces = MediaType.APPLICATION_JSON_VALUE)
    public String certValidation(@RequestParam("cert_name") String cert_name) {
        String command = "openssl verify -CAfile Certificate_authority/rootCA.pem Certificate_user/" + cert_name + ".pem";
        Runtime r = Runtime.getRuntime();
        try {
            Process proc = r.exec(command);
            BufferedReader stdInput = new BufferedReader(new
                    InputStreamReader(proc.getInputStream()));
            BufferedReader stdError = new BufferedReader(new
                    InputStreamReader(proc.getErrorStream()));
            //System.out.println(stdInput);
            //System.err.println(stdError);
            String out = IOUtils.toString(stdInput);
            if (out.contains("OK") && certificatRepository.
                    findFirstByPathName(cert_name + ".pem").getValid())
                return "{\"successValid\":true}";
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "{\"Valid\":false}";
    }

    @RequestMapping(method = PUT, value = "/certificat/update")
    public void certUpdate(@RequestParam("cert_name") String cert_name) {
        /**
         * TODO: update cert
         */
        Certificat certificat = certificatRepository.findFirstByPathName(cert_name);
        certificat.setValid(false);
        certificatRepository.save(certificat);

    }

    @RequestMapping(method = DELETE, value = "/certificat/revoke")
    public String certRevoke(@RequestParam("cert_name") String cert_name) {

        Certificat certificat = certificatRepository.findFirstByPathName(cert_name);
        certificat.setValid(false);
        certificatRepository.save(certificat);
        return "{\"successRevoke\":1}";
    }

}
