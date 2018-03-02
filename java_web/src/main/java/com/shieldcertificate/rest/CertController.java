package com.shieldcertificate.rest;

import com.shieldcertificate.model.Certificat;
import com.shieldcertificate.model.User;
import com.shieldcertificate.repository.CertificatRepository;
import org.apache.commons.io.IOUtils;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.sql.rowset.serial.SerialClob;
import java.io.*;
import java.sql.Clob;
import java.sql.SQLException;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

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

    @RequestMapping(value = "/certificat/create_certificat", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    @PreAuthorize("hasRole('ADMIN')")
    public byte[] create_certificat(MultipartHttpServletRequest request) throws IOException, InterruptedException, SQLException {
        Iterator<String> itr = request.getFileNames();
        MultipartFile file = request.getFile(itr.next());

        File convFile = new File(file.getOriginalFilename());
        convFile.createNewFile();
        FileOutputStream fos = new FileOutputStream("import_cert/" + convFile);
        fos.write(file.getBytes());
        fos.close();
        int randomNum = ThreadLocalRandom.current().nextInt(0, 10000 + 1);
        String fileName = "cert_" + randomNum + ".csr";
        String command = "openssl x509 -req -in import_cert/" + convFile + " -CA Certificate_authority/rootCA.pem -CAkey Certificate_authority/private_ca.key -CAcreateserial -out Certificate_user/" + fileName + " -days 500 -sha256";
        Runtime r = Runtime.getRuntime();
        Process p1 = r.exec(command);

        p1.waitFor();
        Thread.sleep(1000);

        String command2 = "openssl x509 -noout -text -in Certificate_user/" + fileName;
        Process proc = r.exec(command2);
        BufferedReader stdInput = new BufferedReader(new
                InputStreamReader(proc.getInputStream()));
        BufferedReader stdError = new BufferedReader(new
                InputStreamReader(proc.getErrorStream()));
        String test = IOUtils.toString(stdInput);

        System.out.println(test);
        String[] cert = test.split("\n");


        /*String[] tmp = cert[10].split(",");

        Certificat certificat = new Certificat();
        certificat.setDateValidity(cert[9].split(":", 2)[1]);
        certificat.setValid(true);
        certificat.setCreation(new Date());
        certificat.setPathName(fileName);
        certificat.setPath_client_cert(convFile.toString());
        certificat.setCountry(tmp[0].substring(tmp[0].indexOf("=") + 1));
        certificat.setEmailAdress(tmp[6].split("=")[1]);
        certificat.setOrganizationName(tmp[3].substring(tmp[3].indexOf("=") + 1));
        certificat.setOrganizationalUnitName(tmp[4].substring(tmp[4].indexOf("=") + 1));
        certificat.setState(tmp[1].substring(tmp[1].indexOf("=") + 1));
        certificat.setLocality(tmp[2].substring(tmp[2].indexOf("=") + 1));
        certificat.setCommonName((tmp[5].substring(tmp[5].indexOf("=") + 1)));
        */

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



        InputStream in = new FileInputStream("Certificate_user/" + fileName);
        String str = IOUtils.toString(in);
        Clob clo = new SerialClob(str.toCharArray());
        clo.setString(1, str);
        certificat.setValuecertificate(clo);

        certificatRepository.save(certificat);

        for (Certificat cur : certificatRepository.findAll()) {
            cur.DisplayCert(cur);
        }

        return IOUtils.toByteArray(in);
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
    public byte[] certUpdate(@RequestParam("cert_name") String cert_name) throws IOException, InterruptedException, SQLException {


        Certificat old_certificat = certificatRepository.findFirstByPathName(cert_name);
        old_certificat.setValid(false);
        certificatRepository.save(old_certificat);

        int randomNum = ThreadLocalRandom.current().nextInt(0, 10000 + 1);
        String fileName = "cert_" + randomNum + ".csr";
        String command = "openssl x509 -req -in import_cert/" + old_certificat.getPath_client_cert() + " -CA Certificate_authority/rootCA.pem -CAkey Certificate_authority/private_ca.key -CAcreateserial -out Certificate_user/" + fileName + " -days 700 -sha256";
        Runtime r = Runtime.getRuntime();
        Process p1 = r.exec(command);

        p1.waitFor();
        Thread.sleep(1000);

        String command2 = "openssl x509 -noout -text -in Certificate_user/" + fileName;
        Process proc = r.exec(command2);
        BufferedReader stdInput = new BufferedReader(new
                InputStreamReader(proc.getInputStream()));
        BufferedReader stdError = new BufferedReader(new
                InputStreamReader(proc.getErrorStream()));
        String test = IOUtils.toString(stdInput);

        System.out.println(test);
        String[] cert = test.split("\n");


        /*String[] tmp = cert[10].split(",");

        Certificat certificat = new Certificat();
        certificat.setDateValidity(cert[9].split(":", 2)[1]);
        certificat.setValid(true);
        certificat.setCreation(new Date());
        certificat.setPathName(fileName);
        certificat.setCountry(tmp[0].substring(tmp[0].indexOf("=") + 1));
        certificat.setEmailAdress(tmp[6].split("=")[1]);
        certificat.setOrganizationName(tmp[3].substring(tmp[3].indexOf("=") + 1));
        certificat.setOrganizationalUnitName(tmp[4].substring(tmp[4].indexOf("=") + 1));
        certificat.setState(tmp[1].substring(tmp[1].indexOf("=") + 1));
        certificat.setLocality(tmp[2].substring(tmp[2].indexOf("=") + 1));
        certificat.setCommonName((tmp[5].substring(tmp[5].indexOf("=") + 1)));
        */

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



        InputStream in = new FileInputStream("Certificate_user/" + fileName);
        String str = IOUtils.toString(in);
        Clob clo = new SerialClob(str.toCharArray());
        clo.setString(1, str);
        certificat.setValuecertificate(clo);

        certificatRepository.save(certificat);

        for (Certificat cur : certificatRepository.findAll())
            cur.DisplayCert(cur);

        return IOUtils.toByteArray(in);
    }

    @RequestMapping(method = DELETE, value = "/certificat/revoke")
    public String certRevoke(@RequestParam("cert_name") String cert_name) {

        Certificat certificat = certificatRepository.findFirstByPathName(cert_name);
        certificat.setValid(false);
        certificatRepository.save(certificat);
        return "{\"successRevoke\":1}";
    }

}
