-- ATTENZIONE -- il progetto Linkoln è stato aggiornato, maggiori informazioni all'indirizzo: [https://linkoln.gitlab.io](https://linkoln.gitlab.io)

Linkoln nasce nell'ambito di un progetto promosso e finanziato nel 2015 dal Servizio Informatica del Senato della Repubblica e realizzato dall'Istituto di Teoria e Tecniche dell'Informazione Giuridica del CNR (ITTIG), finalizzato allo sviluppo di uno strumento per l'estrazione automatica di riferimenti normativi nazionali ed europei dai testi legislativi italiani.

Linkoln è un progetto open-source sviluppato in Java, rilasciato con licenza GPL ver. 3 e disponibile sotto forma di libreria jar. La libreria può essere facilmente integrata all'interno di web applications o in applicazioni stand-alone, oppure può essere utilizzata direttamente da linea di comando.

Il processo di riconoscimento dei riferimenti normativi effettuato da Linkoln è basato sul lavoro di diversi moduli specializzati nell'identificazione dei singoli campi che compongono la citazione testuale. I moduli vengono attivati in serie e producono una marcatura interna e temporanea del testo piatto. Tale marcatura viene sfruttata per verificare se una determinata porzione di testo sia o meno un riferimento normativo attraverso il confronto con dei pattern prestabiliti. Infine Linkoln si occupa di produrre gli identificatori standard in urn:nir e CELEX per i riferimenti normativi nazionali ed europei trovati. 

I moduli di Linkoln sono stati implementati con la libreria JFlex, che, attraverso la definizione di espressioni regolari, regole e stati, permette la realizzazione di analizzatori lessicali efficienti.


