package com.bfwg.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * Created by dada2 on 01/03/2018.
 */
@RestController
//@RequestMapping( value = "/api", produces = MediaType.APPLICATION_JSON_VALUE )
public class CertController {
    @RequestMapping( method = GET, value= "/certificat/certInfo")
    public void certInformation(){
        /**
         * TODO: display cert info
         */
        String command="openssl x509 -req -in Certificate_user/"  + " -CA Certificate_authority/rootCA.pem -CAkey Certificate_authority/private_ca.key -CAcreateserial -out Certificate_user/cert1.csr -days 500 -sha256";
        Runtime r=Runtime.getRuntime();
        try {
            Process proc =  r.exec(command);
            BufferedReader stdInput = new BufferedReader(new
                    InputStreamReader(proc.getInputStream()));
            BufferedReader stdError = new BufferedReader(new
                    InputStreamReader(proc.getErrorStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping( method = GET, value= "/certificat/validation")
    public String certValidation() {
        return "Hello World";
        /**
         * TODO: check cert validation
         */

    }

    @RequestMapping( method = GET, value= "/certificat/info")
    public void certUpdate() {
        /**
         * TODO: update cert
         */

    }

    @RequestMapping( method = GET, value= "/certificat/revoke")
    public void certRevoke() {
        /**
         * TODO: revoke cert
         */
    }

}
