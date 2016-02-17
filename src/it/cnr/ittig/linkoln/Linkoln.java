/*******************************************************************************
 * Copyright (c) 2016 ITTIG.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU GPL license v3.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/gpl-3.0.en.html
 *
 * Contributors:
 *     ITTIG
 *******************************************************************************/
package it.cnr.ittig.linkoln;

import it.cnr.ittig.linkoln.ref.LegalRef;
import it.cnr.ittig.linkoln.scanner.AliasCodici;
import it.cnr.ittig.linkoln.scanner.AliasEuropei;
import it.cnr.ittig.linkoln.scanner.AliasTestiUnici;
import it.cnr.ittig.linkoln.scanner.Date;
import it.cnr.ittig.linkoln.scanner.EmanantiAutorita;
import it.cnr.ittig.linkoln.scanner.EmanantiMinisteri;
import it.cnr.ittig.linkoln.scanner.EmanantiNumeri;
import it.cnr.ittig.linkoln.scanner.EmanantiPrincipali;
import it.cnr.ittig.linkoln.scanner.IgnoraNumeri;
import it.cnr.ittig.linkoln.scanner.LexSeparator;
import it.cnr.ittig.linkoln.scanner.LkCleaner;
import it.cnr.ittig.linkoln.scanner.Numeri;
import it.cnr.ittig.linkoln.scanner.Partizioni;
import it.cnr.ittig.linkoln.scanner.PartizioniGruppi;
import it.cnr.ittig.linkoln.scanner.RiferimentiAlias;
import it.cnr.ittig.linkoln.scanner.RiferimentiAutorita;
import it.cnr.ittig.linkoln.scanner.RiferimentiMinisteri;
import it.cnr.ittig.linkoln.scanner.RiferimentiPrincipali;
import it.cnr.ittig.linkoln.scanner.Serializer;
import it.cnr.ittig.linkoln.scanner.Spawner;
import it.cnr.ittig.linkoln.scanner.Tipi;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;

public class Linkoln {
	/**
	 * Un oggetto di questa classe rappresenta un'istanza di
	 * esecuzione dell'analizzatore di riferimenti normativi
	 * su uno specifico input testuale.
	 * 
	 * L'oggetto Linkoln fornisce i metodi per l'impostazione
	 * dell'input testuale, per il passaggio di eventuali 
	 * metadati dell'input testuale, configurare l'analisi
	 * dei riferimenti, i metodi che espongono l'output in forma di:
	 * - testo con riferimenti marcati con tag <a href="...">
	 * - testo con riferimenti marcati con <span id="...">
	 * - Collection di oggetti LegalRif.
	 * 
	 */

	public final static String version = "0.8.3"; 
	
	private Map<Integer, LegalRef> id2ref = new TreeMap<Integer, LegalRef>();
	
	private Collection<LegalRef> refs = new ArrayList<LegalRef>();
	
	private boolean debug = false;
	
	private String inputText = "";
	
	private String inputOriginalText = "";
	
	private String spanText = "";
	
	private String hrefText = "";
	
	private String entityText = "";
	
	private String patternText = "";
	
	private String debugText = "";
	
	public void run() throws IOException {
	
		if(debug) System.out.println("INPUT TEXT: " + inputText);
		if(inputText.trim().length() < 5) {
			return;
		}
		
		String firstRow = "<tr><td align=\"center\"><strong>TESTO ORIGINALE</strong></td><td align=\"center\"><strong>MARCATURA INTERMEDIA</strong></td><td align=\"center\"><strong>COPERTURA DEI RIFERIMENTI</strong></td><td align=\"center\"><strong>SERIALIZZAZIONE</strong></td></tr>";
		debugText = "<table border='1'>" + firstRow + "<tr><td>" + inputText + "</td>";
		
		LexSeparator lexs = new LexSeparator(new StringReader(inputText));
    	lexs.yylex();
    	String output = lexs.getOutput();
    	
    	if(debug) System.out.println(output);
    	
       	EmanantiNumeri emaNumeri = new EmanantiNumeri(new StringReader(output));
    	emaNumeri.yylex();
    	output = emaNumeri.getOutput();
    	
    	if(debug) System.out.println(output);
    	
    	AliasCodici aliasCod = new AliasCodici(new StringReader(output));	
    	aliasCod.yylex();
    	output = aliasCod.getOutput();
    	
    	AliasTestiUnici aliasTU = new AliasTestiUnici(new StringReader(output));	
    	aliasTU.yylex();
    	output = aliasTU.getOutput();
    	
    	AliasEuropei aliasEU = new AliasEuropei(new StringReader(output));	
    	aliasEU.yylex();
    	output = aliasEU.getOutput();
    	
    	if(debug) System.out.println(output);
    	
    	EmanantiMinisteri ministeri = new EmanantiMinisteri(new StringReader(output));
    	ministeri.yylex();
    	output = ministeri.getOutput();
		
    	EmanantiAutorita emananti = new EmanantiAutorita(new StringReader(output));
    	emananti.yylex();
    	output = emananti.getOutput();
		
    	EmanantiPrincipali principali = new EmanantiPrincipali(new StringReader(output));
    	principali.yylex();
    	output = principali.getOutput();
		
    	if(debug) System.out.println(output);
    	
    	Tipi tipi = new Tipi(new StringReader(output));
    	tipi.yylex();
    	output = tipi.getOutput();
		    	
    	//Remove <LEXSEP>
    	output = output.replaceAll("<LEXSEP>", "");
    	output = output.replaceAll("</LEXSEP>", "");
    	
    	if(debug) System.out.println(output);
    	
    	Date datemarker = new Date(new StringReader(output));
    	datemarker.yylex();
    	output = datemarker.getOutput();
    	
    	if(debug) System.out.println(output);
    	
    	Partizioni partizioni = new Partizioni(new StringReader(output));
    	partizioni.yylex();
    	output = partizioni.getOutput();
    	
    	if(debug) System.out.println(output);
    	
    	PartizioniGruppi gruppipartizioni = new PartizioniGruppi(new StringReader(output));
    	gruppipartizioni.yylex();
    	output = gruppipartizioni.getOutput();
		
    	if(debug) System.out.println(output);
    	
    	Numeri numeri = new Numeri(new StringReader(output));
    	numeri.yylex();
    	output = numeri.getOutput();
		
    	if(debug) System.out.println(output);
    	
    	IgnoraNumeri ignora = new IgnoraNumeri(new StringReader(output));
    	if(debug) {
    		ignora.setDebug(true);
    	}
    	ignora.yylex();
    	output = ignora.getOutput();
    	
    	entityText = output;
		
    	debugText += "<td>" + entityText + "</td>";

    	if(debug) System.out.println(output);

    	RiferimentiMinisteri rifMin = new RiferimentiMinisteri(new StringReader(output));
    	rifMin.yylex();
    	output = rifMin.getOutput();

    	RiferimentiAutorita rifAut = new RiferimentiAutorita(new StringReader(output));
    	rifAut.yylex();
    	output = rifAut.getOutput();

    	RiferimentiAlias rifAlias = new RiferimentiAlias(new StringReader(output));
    	rifAlias.yylex();
    	output = rifAlias.getOutput();
    	
    	RiferimentiPrincipali rifPri = new RiferimentiPrincipali(new StringReader(output));
    	rifPri.yylex();
    	output = rifPri.getOutput();

    	if(debug) System.out.println(output);

    	patternText = output;
    	
		debugText += "<td>" + patternText + "</td>";
		
    	Spawner rsp = new Spawner(new StringReader(output));
    	rsp.yylex();
    	output = rsp.getOutput();
    	
    	LkCleaner lkc = new LkCleaner(new StringReader(output));
    	lkc.yylex();
    	output = lkc.getOutput();
    	
    	spanText = output;
    	
    	for(LegalRef rif : rsp.getRifs()) {
    		String id = rif.getId().replaceAll("lkrif", "");
    		int num = Integer.valueOf(id);
    		id2ref.put(num, rif);
    	}
		
    	if(debug) System.out.println(output);
    	
    	Serializer ser = new Serializer(new StringReader(output));
    	ser.setRifsMap(id2ref);
    	ser.yylex();
    	output = ser.getOutput();
		
    	if(debug) System.out.println(output);

    	hrefText = output;
    	
    	debugText += "<td>" + hrefText + "</td></tr></table>";
   		
   		for(Integer id : id2ref.keySet()) {   			
   			refs.add(id2ref.get(id));
   		}
	}

	public Collection<LegalRef> getRefs() {
		return refs;
	}

	public String getRefsHtml() {
		/*
		 * Per ogni legal rif, verifica se è coinvolto in un multiplo e poi scrivi una riga di tabella html
		 * <tr><td> mtext </td> <td> urn1<br>urn2 </td></tr>
		 */
		
		String prevText = "";

		String html = "<p><table class='table'><tr><td align='center'><strong>FRAMMENTI DI TESTO</strong></td><td align='center'><strong>RIFERIMENTI LEGISLATIVI</strong></td></tr>";
		
		for(LegalRef rif : refs) {
		
			if(rif.getMtext().equals(prevText)) {
				
				html += " <br> " + rif.getHtml();
				
			} else {
				
				if( !prevText.equals("")) {
					html += "</td></tr>";
				}
				
				html += "<tr><td> " + "<i>&ldquo;"
						+ rif.getMtext() + "&rdquo;</i></td><td> " + rif.getHtml();
			}
						
			prevText = rif.getMtext();
		}
		
		html += "</td></tr></table>";
		
		return html;
	}
	
	public String getTextHtml() {
		
		String html = "<p><table class='table'><tr><td align='center'><strong>TESTO MARCATO</strong></td></tr>";
		
		html += "<tr><td>" + getHrefText() + "</td></tr></table>";
		
		return html;
	}
	
	public String getSpanText() {
		return spanText;
	}

	public String getHrefText() {
		return fixHtml(hrefText);
	}

	public String getEntityText() {
		return entityText;
	}

	public String getPatternText() {
		return patternText;
	}

	public String getDebugText() {
		return debugText;
	}

	public void setInputText(String inputText) {
		
		inputOriginalText = inputText;
		
		this.inputText = inputText;
	}

	public boolean isDebug() {
		return debug;
	}

	public void setDebug(boolean debug) {
		this.debug = debug;
	}

	private String fixHtml(String text) {
		
		String html = text.replaceAll("\r\n", "<br>");
		html = html.replaceAll("\n", "<br>");
		html = html.replaceAll("\r", "<br>");
		
		return html;
	}
}
