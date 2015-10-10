# A4M36ISS - Order service #

##Příprava a spuštění projektu##

###ISS-ORDER###
* nainstalujeme projekt s profilem bundle do lokálního maven repozitáře

  ``mvn clean install -P bundle``

###FUSE###
* odkomentovat poslední řádek v ``$FUSE_HOME$/etc/users.properties`` (uživatel admin)
* přidat ``src/main/resources/keystore.jks`` do ``$FUSE_HOME$/bin``

####Spuštění####

``bin/fuse debug``

* nyní je možné se připojit na remote debug - port 5005
* nainstalujeme features

  ``features:install camel-http4``
  
  ``features:install camel-jackson``
  
  ``features:install camel-restlet``

* přidáme iss-order jako bundle

  ``osgi:install -s mvn:cz.cvut.iss/iss-order/1.0.0-SNAPSHOT``
  
* nyní zjistíme ID nainstalovaného bundlu:

  ``list -s``
  
* Po změně v projektu iss-order (a novým nainstalováním iss-order do mvn repo - přes remote debug se mi to zatím nepodařilo rozběhnout) je třeba zaktualizovat bundle

  ``update $BUNDLE_ID$``
  
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
* header for key: ``X-API-Key``

###REST###
*  endpoint: ``https://localhost:5443/apiman-gateway/iss/orders/2.3/orders``
*  premium api key: ``f16d6cc3-1b93-4521-8fe9-b1b635a4d08f``
*  high-volume api key: ``656b9895-47cb-46d6-8323-2b6684793a88``
*  low-volume api key: ``a24db9ad-c0c2-4332-bf07-211288dd2fbf``

###SOAP###
* endpoint: ``https://localhost:5443/apiman-gateway/iss/orders/2.3.3_soap/orders``
* premium api key: ``f16d6cc3-1b93-4521-8fe9-b1b635a4d08f``
* high-volume api key: ``5d7bd3af-7da6-4d36-ba7e-111e7e4e479f`` 
* low-volume api key: ``89c4c433-b77f-4922-bffd-d9e3476d9264``

###Users###
* Auth basic
* user:m password:m - manager - POST /orders and GET /orders/{id}
* user:e password:e - employee - only GET /orders/{id}
