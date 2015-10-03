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
    "items" : [
        {
            "articleId" : "6",
            "count" : "1",
            "unitPrice" : "4.4"
        }
    ]
  }
  ```
  
  ####GET####
  
    ``https://localhost:8081/orders/{orderId}``
    
###SOAP###

* WSDL na adrese: ``https://localhost:8181/orderApplication/order?wsdl``
* Např. přes SOAP-UI (http://www.soapui.org/) vytvořit nový projekt, naimportovat WDSL a nechat si vygenerovat dotazy (create a get)
