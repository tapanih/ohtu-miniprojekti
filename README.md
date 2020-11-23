# Lukuvinkkisovellus
Projektin aiheena on luoda lukuvinkkisovellus, jonka avulla voi ylläpitää omaa lukuvinkkikirjastoa. Sovellukseen voi tallentaa kirjan tiedot, artikkelin tai videon linkin ja omia lukuvinkkejään voi hallinnoida sovelluksen avulla. Sovellus on työpöytäsovellus, joka toteutetaan Javalla.  

Projekti on Ohjelmistotuotanto-kurssin miniprojekti.  

![GitHub Actions](https://github.com/tapanih/ohtu-miniprojekti/workflows/Java%20CI%20with%20Gradle/badge.svg)

## Projektin oleelliset tiedot
**Definition of Done**  
* Kaikki ryhmän jäsenet ovat sitä mieltä, että product backlogiin kirjatut asiakkaan kanssa neuvotellut hyväksymiskriteerit toteutuvat ja ovat kokeilleet ohjelman toimintaa omalla koneellaan. 
* User storylle, jolle on mahdollista ja järkevää kirjoittaa automatisoidut testit (JUnit, Cucumber), testit pitää olla valmiit, suoritettavissa ja ne pitää mennä läpi sekä jokaisen omalla koneella, (että GitHub Actionissa, sitten kun se saadaan käyttöön).  
* Checkstyle-virheitä ei saa olla ja koodin pitää olla yleisesti siistiä ja luokka/metodi-jaon tulee olla järkevä.  
  
**[Product Backlog](https://docs.google.com/spreadsheets/d/1f-38FdB34sLDpAlHOOb8wHRx4k4TWji5HSkWHmvbnTc/)**


## Releaset ## 

+ [Release 1](https://github.com/tapanih/ohtu-miniprojekti/releases/tag/release1)

## Asennus- ja käyttöohjeet ##

Jarin ajaminen komentoriviltä:

`java -jar ohtu-miniprojekti.jar`

Gradle-projektin ajaminen kun repositorio on kloonattu koneelle, tapahtuu ajamalla /app/ kansiosta:

`./gradlew run`

Jarin voi generoida /app/ kansiossa komennolla:

`./gradlew build`

Valmis jar ilmestyy polkuun /app/build/libs/ohtu-miniprojekti.jar

Checkstylen suorittaminen: mene ensin /app/ kansioon, ja aja:

`./gradlew checkstyleMain`

Raportti löytyy kansiosta /app/build/reports/checkstyle/main.html

Testien ajaminen /app/ kansiosta:

`./gradlew test`

Sovelluksen käyttö on yksinkertaista. Alkunäkymässä sovellus listaa siihen syötetyt kirjat. Voit lisätä kirjan painamalla lisää kirja, syöttämällä kirjan tiedot lomakkeisiin, ja painamalla Lisää.
