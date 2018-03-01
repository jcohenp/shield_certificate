package com.bfwg.rest;

import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 *
 */
@RestController
//@RequestMapping( value = "/api", produces = MediaType.APPLICATION_JSON_VALUE )
public class CertController {
    @RequestMapping( method = GET, value= "/certificat/certInfo")
    public void certInformation(){
        /**
         * TODO: display cert info
         */
        String command ="";
        Runtime r=Runtime.getRuntime();
        /*try {
            Process proc =  r.exec(command);
            BufferedReader stdInput = new BufferedReader(new
                    InputStreamReader(proc.getInputStream()));
            BufferedReader stdError = new BufferedReader(new
                    InputStreamReader(proc.getErrorStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }

    //@PreAuthorize("hasRole('ADMIN')")
    @RequestMapping( method = GET, value= "/certificat/validation", produces = MediaType.APPLICATION_JSON_VALUE)
    public String certValidation() {

        String command = "openssl verify -CAfile Certificate_authority/rootCA.pem Certificate_user/cert_user";
        System.out.println("Enter function");
        Runtime r=Runtime.getRuntime();
        try {
            Process proc =  r.exec(command);
            BufferedReader stdInput = new BufferedReader(new
                    InputStreamReader(proc.getInputStream()));
            BufferedReader stdError = new BufferedReader(new
                    InputStreamReader(proc.getErrorStream()));
            System.out.println(stdInput);
            System.err.println(stdError);
            return stdInput.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
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
