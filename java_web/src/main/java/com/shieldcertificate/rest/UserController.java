package com.shieldcertificate.rest;

import java.util.concurrent.ThreadLocalRandom;
import com.shieldcertificate.model.Certificat;
import com.shieldcertificate.model.User;
import com.shieldcertificate.repository.CertificatRepository;
import com.shieldcertificate.service.UserService;
import org.json.JSONException;
import org.json.JSONObject;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.sql.rowset.serial.SerialClob;
import java.io.*;
import java.security.Principal;
import java.sql.Clob;
import java.sql.SQLException;
import java.util.*;

import static org.springframework.web.bind.annotation.RequestMethod.GET;


@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private CertificatRepository certificatRepository;
    private UserController()
    {}
    private static UserController INSTANCE = new UserController();

    public static UserController getInstance()
    {
        return INSTANCE;
    }
    @RequestMapping(method = GET, value = "/user/{userId}")
    @PreAuthorize("hasRole('ADMIN')")
    public  User loadById(@PathVariable Long userId) {
        return this.userService.findById(userId);
    }

    @RequestMapping(method = GET, value = "/user/all")
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


}


