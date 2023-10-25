# LoyaltyPlatform
Progetto Loyalty Platform per i corsi di Ingegneria del Software e Progettazione Applicazione Web e Mobile dell'università di Camerino.
Il progetto è stato sviluppato utilizzando Spring Boot con Spring Security per realizzare il backend.
Il frontend è stato sviluppato in React ed implementa le funzionalità di registrazione, login ed iscrizione ai programmi fedeltà da parte del cliente. 
L'autenticazione è gestita tramite token JWT generati nel backend e salvati nella memoria locale dal frontend.
Sono inoltre state adottate misure di prevenzione per vulnerabilità di Web Security come XSS, SQL injection e CSRF.

Abbiamo incluso le richieste di Postman utilizzate per testare le funzioni implementate.
Per utilizzare il proprio database MySQL modificare il file "application.properties".
