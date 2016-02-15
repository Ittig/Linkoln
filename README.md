# Linkoln

Linkoln è un progetto open-source sviluppato in Java, rilasciato con licenza GPL ver. 3 e disponibile sotto forma di libreria jar. La libreria può essere facilmente integrata all'interno di web applications o in applicazioni stand-alone, oppure può essere utilizzata direttamente da linea di comando.

Il processo di riconoscimento dei riferimenti normativi effettuato da Linkoln è basato sul lavoro di diversi moduli specializzati nell'identificazione dei singoli campi che compongono la citazione testuale. I moduli vengono attivati in serie e producono una marcatura interna e temporanea del testo piatto. Tale marcatura viene sfruttata per verificare se una determinata porzione di testo sia o meno un riferimento normativo attraverso il confronto con dei pattern prestabiliti. Infine Linkoln si occupa di produrre gli identificatori standard in urn:nir e CELEX per i riferimenti normativi trovati. I moduli di Linkoln sono stati implementati con la libreria JFlex, che, attraverso la definizione di espressioni regolari, regole e stati, permette la realizzazione di analizzatori lessicali efficienti.

Linkoln è disponibile in versione dimostrativa attraverso questa web demo. Dalla stessa pagina è inoltre possibile inviare segnalazioni riguardanti errori o mancati riconoscimenti. Il software utilizza testo piatto come input e fornisce due tipi di output: l'elenco dei riferimenti normativi nell'ordine in cui questi compaiono nel testo; il testo originale con i riferimenti evidenziati sotto forma di link HTML.


