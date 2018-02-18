package com.bfwg.rest;

import com.bfwg.model.Certificat;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.Iterator;

import static org.springframework.web.bind.annotation.RequestMethod.POST;


@RestController
@RequestMapping( value = "/api", produces = MediaType.APPLICATION_JSON_VALUE )
public class CertificatController {

    @RequestMapping( method = POST, value = "/certificat/create_certificat" )
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseBody
    public void create_certificat(MultipartHttpServletRequest request, HttpServletResponse response) {
        //openssl x509 -x509toreq -in certificate.crt -out CSR.csr -signkey privateKey.key
            Iterator<String> itr=request.getFileNames();

            MultipartFile file=request.getFile(itr.next());
    }
}
