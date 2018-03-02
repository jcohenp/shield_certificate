![alt text](https://gitlab.com/codecritics/shied-certificate/raw/create_certificate/java_web/src/main/resources/static/assets/images/logo.png)

> Fait avec [Springboot](https://projects.spring.io/spring-boot/)  \[Authentification basé sur les token\]  ([JSON Web Token](https://jwt.io/) et [AngularJS](https://angularjs.org/) 

### Demarrage Rapide

**Verifiez que vous avez la commande Maven et Java 1.7 ou plus récente**

```bash
# Cloner ce Repertoire
git clone https://gitlab.com/codecritics/shied-certificate.git
# Aller dans le dossier source.
cd shied-certificate

# Installer le projet avec Maven
mvn install

# Lancer le serveur
mvn spring-boot:run

# L'Application Web se lancera sur le port 8080
# Par defaut pour pourrez vous connecter en utilisant les identifiants ci-dessous:
# - Admin - admin:123
```


### Architecture du Dossier
```
shied-certificate/
 ├──src/                                                        * our source files
 │   ├──main
 │   │   ├──java.com.shieldcertificate
 │   │   │   ├──common
 │   │   │   │config
 │   │   │   │   ├──DeviceProvider.java
 │   │   │   │   └──TimeProvider.java  
 │   │   │   ├──config
 │   │   │   │   └──WebSecurityConfig.java                      * config file for filter, custom userSerivce etc.
 │   │   │   ├──model
 │   │   │   │   ├──Certificate.java
 │   │   │   │   ├──Authority.java
 │   │   │   │   ├──UserTokenState.java                         * JWT model
 │   │   │   │   └──User.java                                   * our main User model.
 │   │   │   ├──repository                                      * repositories folder for accessing database
 │   │   │   │   ├──CertificateRepository.java
 │   │   │   │   └──UserRepository.java
 │   │   │   ├──rest                                            * rest endpoint folder
 │   │   │   │   ├──CertController.java
 │   │   │   │   ├──AuthenticationController.java               * auth related REST controller, refresh token endpoint etc.
 │   │   │   │   └──UserController.java                         * REST controller to handle User related requests
 │   │   │   ├──security                                        * Security related folder(JWT, filters)
 │   │   │   │   ├──auth
 │   │   │   │   │   ├──JwtAuthenticationRequest.java           * login request object, contains username and password
 │   │   │   │   │   ├──RestAuthenticationEntryPoint.java       * handle auth exceptions, like invalid token etc.
 │   │   │   │   │   ├──TokenAuthenticationFilter.java          * the JWT token filter, configured in WebSecurityConfig
 │   │   │   │   │   └──TokenBasedAuthentication.java           * this is our custom Authentication class and it extends AbstractAuthenticationToken.
 │   │   │   │   └──TokenHelper.java                             * token helper class
 │   │   │   ├──service
 │   │   │   │   ├──impl
 │   │   │   │   │   ├──CustomUserDetailsService.java           * custom UserDatilsService implementataion, tells formLogin() where to check username/password
 │   │   │   │   │   └──UserServiceImpl.java
 │   │   │   │   └──UserService.java
 │   │   │   └──Application.java                                * Application main enterance
 │   │   └──recources
 │   │       ├──static                                          * static assets are served here(Angular and html templates)
 │   │       ├──application.yml                                 * application variables are configured here
 │   │       └──import.sql                                      * h2 database query(table creation)
 │   └──test                                                    * Junit test folder
 └──pom.xml                                                     * what maven uses to manage it's dependencies
```
# Sommaire
* [Architecture du Dossier](#file-structure)
* [Configuration](#configuration)

### Configuration
- **WebSecurityConfig.java**: L'Authentification orienté Serveur basé sur les Tokens.
- **application.yml**: Les propriétés de l'Application (la gestion du temps de validité des tokens, le secret du token ect..). Vous trouverez la référence de toutes les propriétés de l'Application ici. [here](http://docs.spring.io/spring-boot/docs/current/reference/html/common-application-properties.html).
- **JWT token TTL**: Les JWT Tokens sont paramétrés pour expirer tous les 10 minutes. Un nouveau token est fourni à chaque authentification.

```
spring:
  jpa:
    hibernate:
      # possible values: validate | update | create | create-drop
      ddl-auto: create-drop
  datasource:
    url: jdbc:mysql://localhost/myDatabase
    username: myUser
    password: myPassword
    driver-class-name: com.mysql.jdbc.Driver
```


### JSON Web Token
> JSON Web Tokens are an open, industry standard RFC 7519 method for representing claims securely between two parties.
for more info, checkout https://jwt.io/

### Contributing
Ce projet a été réalisé dans un cadre académique, aussi toute contribution est allégrement acceptée.

This project is inspried by
- [Cerberus](https://github.com/brahalla/Cerberus)
- [jwt-spring-security-demo](https://github.com/szerhusenBC/jwt-spring-security-demo)

___

# License
 [MIT](/LICENSE)

