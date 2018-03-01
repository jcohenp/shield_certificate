package com.bfwg.rest;

import com.bfwg.model.User;
import com.bfwg.service.UserService;
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
    public Map create_certificat(MultipartHttpServletRequest request, @RequestParam("userName") String userName, @RequestParam("idCert") int idCert) throws IOException, InterruptedException {
        Iterator<String> itr = request.getFileNames();
        MultipartFile file = request.getFile(itr.next());

        File convFile = new File(file.getOriginalFilename());
        convFile.createNewFile();
        FileOutputStream fos = new FileOutputStream("Certificate_user/" + convFile);
        fos.write(file.getBytes());
        fos.close();
        String command="openssl x509 -req -in Certificate_user/" + convFile + " -CA Certificate_authority/rootCA.pem -CAkey Certificate_authority/private_ca.key -CAcreateserial -out Certificate_user/cert_" + userName + "_" + idCert + ".pem -days 500 -sha256";
        Runtime r=Runtime.getRuntime();
        r.exec(command);


        String command2 = "openssl x509 -noout -text -in Certificate_user/cert_user_0.pem";
        Process proc = r.exec(command2);
        BufferedReader stdInput = new BufferedReader(new
                   InputStreamReader(proc.getInputStream()));
        BufferedReader stdError = new BufferedReader(new
                    InputStreamReader(proc.getErrorStream()));
        System.out.println("This io " + IOUtils.toString(stdInput));

        System.err.println(IOUtils.toString(stdError));

        String str = IOUtils.toString(stdInput);
        proc.waitFor();
        String[] cert = IOUtils.toString(stdError).split("/");
        //for (String cur : str.split("=")) {
        System.out.println("print " + str);
       // }



        return Collections.singletonMap("fileName", convFile.getName());
    }
}


