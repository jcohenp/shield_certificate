package com.bfwg.rest;

import com.bfwg.model.Certificat;
import com.bfwg.repository.CertificatRepository;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
    public List<Certificat> getAllCert() {

     //   Certificat certificat = new Certificat();
     //   certificat.setValid();
        List<Certificat> certificat = certificatRepository.findAll();
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
    public void certUpdate() {
        /**
         * TODO: update cert
         */

    }

    @RequestMapping(method = DELETE, value = "/certificat/revoke")
    public String certRevoke(@RequestParam("cert_name") String cert_name) {

        Certificat certificat = certificatRepository.findFirstByPathName(cert_name + ".pem");
        certificat.setValid(false);
        certificatRepository.save(certificat);
        return "{\"successRevoke\":1}";
    }

}
