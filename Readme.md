# A4M36ISS - Order service #
##ISS-Order - final docker##
Docker image obsahující apiman i instanci JBOSS FUSE.
* run: ``docker run -it --net="host" -p 8181:8181 -p 5080:5080 -p 5443:5443 -p 5990:5990 -p 5993:5993 udrzalv/iss-order-all``
* :8181 - hawtio
* :5080/apiman - apiman konzole
* :5443 - rest/soap apiman endpoints (viz. dole)

##Příprava a spuštění projektu##

###ISS-ORDER###
* nainstalujeme projekt s profilem bundle do lokálního maven repozitáře

  ``mvn clean install -DskipTests -P bundle``

###FUSE###
* odkomentovat poslední řádek v ``$FUSE_HOME$/etc/users.properties`` (uživatel admin)
* přidat ``src/main/resources/keystore.jks`` do ``$FUSE_HOME$/bin``

####Spuštění####

  ``bin/fuse debug``

* Po spuštění s parametrem ``debug`` je možné se připojit na remote debug na portu 5005.

* Nastartujeme fabric (uděláme z našeho kontejneru kontejner spravovaný pomocí fabric).

  ``fabric:create``

* Vytvoříme 2 kontejnery v MasterSlave topologii s persistentním uložištěm a skupinou 'masterslave' pro A-MQ. ``$PERSISTENT_STORAGE$`` musí být složka existujici na filesystému (např. ``/Users/jakubchalupa/Documents/iss-amq``).

  ``mq-create --create-container broker --replicas 2 --data $PERSISTENT_STORAGE$ --group masterslave hq-broker``

* Přidáme features repository z projektu iss-order, který budeme chtít nainstalovat jako Karaf feature.

  ``fabric:profile-edit --repository mvn:cz.cvut.iss/iss-order/1.0.0-SNAPSHOT/xml/features fabric``

* Přidáme a spustíme naší Karaf feature.

  ``fabric:profile-edit --feature iss-order fabric``
  
* Zkontrolujeme, zda se správně nainstaloval a nastartoval bundle ``cz.cvut.iss.iss-order``.

  ``list -s``
  
* Při vypnutí a opětovném puštění FUSE bude možná třeba nastartovat znovu child-instance A-MQ.

    ``$FUSE_HOME$/bin/client container-start  broker``
    
    ``$FUSE_HOME$/bin/client container-start  broker2``
  
  
##Endpointy##

###REST###

####POST####

  ``https://localhost:8081/orders``
  
* body

  ```javascript
  {
    "address" : {
        "firstName" : "Jiri",
        "lastName" : "Novak",
        "street" : "Purkynova",
        "city" : "Brno",
        "zipCode" : "61200"
    },
    "item" : {
      "sku" : "rhel",
      "count" : "1",
      "unitPrice" : "4.4"
    }
  }
  ```
  
  ####GET####
  
    ``https://localhost:8081/orders/{orderId}``
    
###SOAP###

* WSDL na adrese: ``https://localhost:9181/orderApplication/order?wsdl``
* Např. přes SOAP-UI (http://www.soapui.org/) vytvořit nový projekt, naimportovat WDSL a nechat si vygenerovat dotazy (create a get)

##APIMAN##
* run: ``docker run -it --net="host" -p 5080:5080 -p 5443:5443 -p 5990:5990 udrzalv/orders-apiman ``
* apiman admin konzole: ``localhost:5080/apimanui``
* apiman login: ``admin/admin123!``
* header for key: ``X-API-Key``

###REST###
*  endpoint: ``https://localhost:5443/apiman-gateway/iss/orders/2.3/orders``
*  premium api key: ``25187ed3-dee4-4715-9bf1-b7da7eb83059``
*  high-volume api key: ``179bf251-52d1-4663-b0ab-dc72c28c97d0``
*  low-volume api key: ``aa4758cd-e53e-4645-b16d-d9caccfe51c0``

###SOAP###
* endpoint: ``https://localhost:5443/apiman-gateway/iss/orders/2.3.4_soap/orders``
* premium api key: ``32864e58-f468-4e62-8594-361bc34b6fef``
* high-volume api key: ``833e0e59-64af-464b-9511-a6e013c784fb`` 
* low-volume api key: ``e570df56-a90a-4433-a505-1700f3df9fd5``

###Users###
* Auth basic
* user:m password:m - manager - POST /orders and GET /orders/{id}
* user:e password:e - employee - only GET /orders/{id}

##ISS-Order docker##
Docker pouze s instancí JBOSS FUSE
* run: ``docker run -it --net="host" -p 8181:8181 -p 8081:8081 -p 9181:9181 udrzalv/iss-order``
