package com.bfwg.repository;

import com.bfwg.model.Certificat;
import com.bfwg.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CertificatRepository extends JpaRepository<Certificat, Long> {


    //@Query("select * from Certificat")
    //List<Certificat> findAll();
}
