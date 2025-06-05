"# AnnotationApp"

Application web **Spring Boot / Thymeleaf** pour la gestion et l’annotation collaborative de jeux de paires de phrases (NLI, similarité, etc.).

> **Encadrant :** Pr. Tarik **Boudaa**  
> **Contributeurs :** El Guelta Mohamed-Saber** · **El Hadifi Soukaina**

---

## Fonctionnalités

| Bloc | Ce que l’on peut faire |
|------|------------------------|
| **Authentification** | Connexion / déconnexion sécurisée via Spring Security. |
| **Administration** | • Créer / modifier des annotateurs <br> • Activer / désactiver (suppression logique) |
| **Datasets** | • Import CSV / JSON (`id,text1,text2`) <br> • Aperçu des 5 premières paires <br> • Affectation d’annotateurs <br> • Calcul de l’avancement (%) |
| **Annotation** | Interface minimale où chaque annotateur étiquette ses paires restantes. |
| **Export** | CSV final : `id,texte1,texte2,classe,annotateur,date`. |
---

## Architecture rapide

- `src/main/java`
    - `controller`     ← couche web
    - `service`        ← logique métier (import, export, métriques …)
    - `entity`         ← entités JPA
    - `repository`     ← interfaces Spring Data
    - `security`       ← configuration Spring Security

- `src/main/resources/templates`
    - …                ← vues Thymeleaf (`.html`)



---

## Prérequis

| Outil | Version conseillée                 |
|-------|------------------------------------|
| JDK   | 17 +                               |
| Maven | 4.0 +                              |
| MySQL | 8 + (ou tout SGDB compatible JDBC) |

---

## Installation & exécution

```bash
# 1. cloner le projet
git clone https://github.com/saber-elg/SpringAnnotationApp.git

# 2. configurer la BDD (src/main/resources/application.properties)
#    spring.datasource.url, username, password


```
Par défaut, les scripts Python sont cherchés dans ./scripts.

## Comptes de démo
Rôle	Login / mot de passe
Admin:	admin / ENSAH
Annot:
        med                              IDiWKPP4
        soukaina                         Ax99gavQ
        lee                              0SdTbmNX
        test                             JNSjrvAd

(cf. data.sql ou créer via l’interface admin)

## Parcours typique
Connexion en tant qu’admin (/login).


Onglet Datasets :

Créer → formulaire (nom, description, fichier CSV/JSON).

Affecter → bouton Affecter → coche les annotateurs.

Détails → aperçus, % d’avancement, export, métriques, spammeurs.

Annotation (profil annotateur) : liste de paires non étiquetées, choix du label, sauvegarde.

Export final → CSV signé (id, textes, label majoritaire, annotateur, date).


##  Demo 
Vous pouvez consulter le demo dans le lien suivant: https://youtu.be/VgtuN56y99U