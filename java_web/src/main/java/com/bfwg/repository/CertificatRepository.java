package com.bfwg.repository;

import com.bfwg.model.Certificat;
import com.bfwg.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CertificatRepository extends JpaRepository<Certificat, Long> {


    //@Query("select * from Certificat")
    //List<Certificat> findAll();

    @Query("select c from Certificat c where c.pathName = :pathName")
    Certificat findFirstByPathName(@Param("pathName") String pathName);
}
