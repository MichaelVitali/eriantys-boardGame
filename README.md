# ing-sw-2022-Vitali-Tacca-Simionato

<h2>Prova Finale di Ingegneria del Software
2021 - 2022</h2>

---

![alt text](https://github.com/MichaelVitali/ing-sw-2022-Vitali-Tacca-Simionato/blob/master/src/main/resources/images/eriantys_cover.jpg?raw=true)

<h3>Implementazione del gioco</h3>
Il progetto prevede l'implementazione del gioco da tavolo Eriantys.

Il progetto consiste nell’implementazione del gioco in modo che sia fruibile in un contesto distribuito. Il sistema è composto da un singolo server, esso è in grado di gestire partite multiple. I client (uno per giocatore) si collegano al server e vengono distribuiti tra le varie partite a seconda delle loro scelte riguardo alla tipologia di partita.<br>
L'implementazione dell'intero progetto sfrutta il pattern MVC (Model-View-Controller). <br>
Lo scambio di messaggi tramite la rete è stato gestito mediante socket.

<h2>Documentazione</h2>

---

<h3>Documentazione</h3>
All'interno della cartella "deliverables" si trova la documentazione necessaria per comprendere quale è stata la logica di progettazione del gioco.

<h3>UML</h3>
Le seguenti cartelle contengono rispettivamente i diagrammi delle classi nella loro prima versione e in quella definitiva, la quale è rappresentativa del codice del gioco:

- [UML iniziali](https://github.com/MichaelVitali/ing-sw-2022-Vitali-Tacca-Simionato/tree/master/deliverables/initial%20UMLs) 
- [UML finali](https://github.com/MichaelVitali/ing-sw-2022-Vitali-Tacca-Simionato/tree/master/deliverables/final%20UMLs)

<h3>Protocollo di comunicazione</h3>
Per la comunicazione di rete sono stati usati i Socket di Java. I messaggi che vengono ricevuti lato client renderizzano (o stampano a video) le modifiche effettuate sul modello. I messaggi che vengono inviati dal client, e quindi ricevuti dal server, richiedono di effettuare una certa azione di setup, o di gioco.<br>
Per maggiori dettagli inerenti la comunicazione di rete consultare il seguente link: [protocollo di comunicazione](https://github.com/MichaelVitali/ing-sw-2022-Vitali-Tacca-Simionato/blob/master/deliverables/communication%20protocol/connection_model.odt)

<h3>Test coverage</h3>
Sono stati scritti i test necessari ad avere una buona copertura dei metodi implementati.

<h3>JavaDoc</h3>

---

<h3>Funzionalità sviluppate</h3>

Le funzionalità che sono state sviluppate sono quelle necessarie a ottenere la fascia di voto maggiore (se funzionanti). In particolare l'implementazione prevede:<br>

- Regole complete
- CLI
- GUI (implementata con la libreria JavaFx)
- Comunicazione mediante socket
- Implementazione di tutte le carte personaggio (1ª FA)
- Modalità "4 giocatori" (2ª FA)
- Gestione di partite multiple da parte del server (3ª FA)


<h2>Informazioni inerenti la build e </h2>
Strumenti

<h2>Esecuzione</h2>

---

<h3>Client</h3>

```

```

<h3>Server</h3>

<h2>Componenti del gruppo</h2>

---

- Michael Vitali
- Manuel Tacca
- Simionato Enrico 10698193