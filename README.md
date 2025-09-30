# safetynet
SafetyNet is a company that connects individuals with emergency services

# Endpoints REST
## 1. Recherche et alertes

GET /firestation?stationNumber=<station_number>
Retourne la liste des personnes couvertes par une caserne :
Prénom, nom, adresse, téléphone
Décompte adultes / enfants (≤ 18 ans)

----------
GET /childAlert?address=<address>
Retourne la liste des enfants vivant à une adresse donnée :
Prénom, nom, âge
Liste des autres membres du foyer
Si aucun enfant : retourne une chaîne vide.

----------
GET /phoneAlert?firestation=<firestation_number>
Retourne les numéros de téléphone des habitants desservis par une caserne.

----------
GET /fire?address=<address>
Retourne les habitants d’une adresse + numéro de la caserne qui les couvre :
Nom, téléphone, âge
Antécédents médicaux (médicaments, posologie, allergies)

----------
GET /flood/stations?stations=<list_of_station_numbers>
Retourne la liste de tous les foyers couverts par les casernes données :
Groupés par adresse
Nom, téléphone, âge
Antécédents médicaux

----------
GET /personInfo?lastName=<lastName>
Retourne pour chaque habitant ayant ce nom :
Nom, adresse, âge, email
Antécédents médicaux

----------
GET /communityEmail?city=<city>
Retourne les emails de tous les habitants d’une ville.



# 2. Gestion des entités
/person
POST : ajouter une nouvelle personne
PUT : mettre à jour une personne (nom/prénom inchangés)
DELETE : supprimer une personne (identifiée par prénom + nom)

/firestation
POST : ajouter un mapping caserne/adresse
PUT : mettre à jour le numéro de caserne d’une adresse
DELETE : supprimer un mapping caserne/adresse

/medicalRecord
POST : ajouter un dossier médical
PUT : mettre à jour un dossier médical (nom/prénom inchangés)
DELETE : supprimer un dossier médical (identifié par prénom + nom)

Exemple d’appels API
Via cURL
# Récupérer les habitants pour une caserne
curl -X GET "http://localhost:8080/firestation?stationNumber=1" -H "accept: application/json"

# Lancer les tests :
mvn test

# Générer le rapport de couverture JaCoCo :
mvn verify

# Rapport disponible dans :
target/site/jacoco/index.html
