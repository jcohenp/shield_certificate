package com.bfwg.rest;

import com.bfwg.model.Certificat;
import com.bfwg.model.User;
import com.bfwg.repository.CertificatRepository;
import com.bfwg.service.UserService;
import org.json.JSONException;
import org.json.JSONObject;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.*;
import java.security.Principal;
import java.util.*;

import static org.springframework.web.bind.annotation.RequestMethod.GET;


@RestController
@RequestMapping( value = "/api", produces = MediaType.APPLICATION_JSON_VALUE )
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private CertificatRepository certificatRepository;

    @RequestMapping( method = GET, value = "/user/{userId}" )
    @PreAuthorize("hasRole('ADMIN')")
    public User loadById( @PathVariable Long userId ) {
        return this.userService.findById( userId );
    }

    @RequestMapping( method = GET, value= "/user/all")
    @PreAuthorize("hasRole('ADMIN')")
    public List<User> loadAll() {
        return this.userService.findAll();
    }


    /*
     *  We are not using userService.findByUsername here(we could),
     *  so it is good that we are making sure that the user has role "ROLE_USER"
     *  to access this endpoint.
     */
    @RequestMapping("/whoami")
    @PreAuthorize("hasRole('USER')")
    public User user(Principal user) {
        return this.userService.findByUsername(user.getName());
    }

    @RequestMapping(value = "/user/create_certificat", method = RequestMethod.POST, produces = "application/json")
    @PreAuthorize("hasRole('ADMIN')")
    public String create_certificat(MultipartHttpServletRequest request, @RequestParam("userName") String userName, @RequestParam("idCert") int idCert) throws IOException, JSONException, InterruptedException {
        Iterator<String> itr = request.getFileNames();
        MultipartFile file = request.getFile(itr.next());

        File convFile = new File(file.getOriginalFilename());
        convFile.createNewFile();
        FileOutputStream fos = new FileOutputStream("Certificate_user/" + convFile);
        fos.write(file.getBytes());
        fos.close();
        String fileName = "cert_" + userName + "_" + idCert + ".pem";
        String command="openssl x509 -req -in Certificate_user/" + convFile + " -CA Certificate_authority/rootCA.pem -CAkey Certificate_authority/private_ca.key -CAcreateserial -out Certificate_user/" + fileName + " -days 500 -sha256";
        Runtime r=Runtime.getRuntime();
        r.exec(command);
        JSONObject obj = new JSONObject();
        obj.put("fileName", fileName);
        obj.put("userName", userName);

        //TODO: use cert name
        String command2 = "openssl x509 -noout -text -in Certificate_user/cert_user_0.pem";
        Process proc = r.exec(command2);
        BufferedReader stdInput = new BufferedReader(new
                   InputStreamReader(proc.getInputStream()));
        BufferedReader stdError = new BufferedReader(new
                    InputStreamReader(proc.getErrorStream()));
        //System.out.println("This io " + IOUtils.toString(stdInput));
        String test = IOUtils.toString(stdInput);

        System.err.println(IOUtils.toString(stdError));

       // String str = IOUtils.toString(stdInput);
        //proc.waitFor();
       // String[] cert = IOUtils.toString(stdError).split("/");
        //for (String cur : str.split("=")) {
       // System.out.println("print " + str);
       // }

        //System.out.println(test);
        String[] cert = test.split("\n");
        /*for (String cur : cert) {
             System.out.print(cur);
        }*/

        String[] tmp = cert[9].split(",");

        Certificat certificat = new Certificat();
        certificat.setDateValidity(cert[8]);
        certificat.setValid(true);
        certificat.setCreation(new Date());
        certificat.setPathName(fileName);
        certificat.setCountry(tmp[0].substring(tmp[0].indexOf("=") + 1));
        certificat.setEmailAdress(tmp[5].split("/")[1].split("=")[1]);
        certificat.setOrganizationName(tmp[3].substring(tmp[3].indexOf("=") + 1));
        certificat.setOrganizationalUnitName(tmp[4].substring(tmp[4].indexOf("=") + 1));
        certificat.setState(tmp[1].substring(tmp[1].indexOf("=") + 1));
        certificat.setLocality(tmp[2].substring(tmp[2].indexOf("=") + 1));
        certificat.setCommonName((tmp[5].substring(tmp[5].indexOf("=") + 1)).substring(0,
                (tmp[5].substring(tmp[5].indexOf("=") + 1).indexOf("/"))));




        certificatRepository.save(certificat);
        //certificatRepository.save(certificat);
        //certificatRepository.save(certificat);

        /*for (Certificat cur : certificatRepository.findAll()) {
            cur.DisplayCert(cur);
        }*/

        //System.out.println("test val " + cert[9]);

        return obj.toString();
    }
}


