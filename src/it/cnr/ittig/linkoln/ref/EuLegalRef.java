package it.cnr.ittig.linkoln.ref;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EuLegalRef extends LegalRef {


	final static public String EurLex = "http://eur-lex.europa.eu/legal-content/IT/TXT/?uri=";
	

	//URN-LEX override
	
	public String getUrnLex() {
		/*
		 * Implementazione UrnLex per riferimenti a legislazione europea.
		 */
		
		String urn = "urn:nir:eu:" + tipo + ":";
		
		if( !data.equals("")) {
			urn += Util.normData(data);
		} else {
			urn += anno;
		}
		
		urn += ";" + numero;
		
		if( !partizione.equals("")) {
		 	urn += "~" + partizione;
		 }
		
		return urn;
	}

	public String getUrnLexHtml() {
		
		return "<a href=\"" + Util.urnResolver + getUrnLex() + "\" target='_blank'><font color=\"red\">" + text + "</font></a>";
	}

	//ELI override
	
	public String getEli() {

		return "";
	}

	public String getEliHtml() {

		return "";
	}

	public String getCelexHtml() {

		// http://eur-lex.europa.eu/legal-content/IT/TXT/?uri=CELEX:32003D0033

		return "<a href=\"" + EurLex + getCelex() + "\"  target='_blank'><font color=\"red\">" + text + "</font></a>";
	}

	private String getArt(String partizione) {

		if( partizione.equals("")) return "";
		
		int artStart = partizione.indexOf("art");
		
		if(artStart < 0) return "";
		
		Pattern ints = Pattern.compile("\\d+");
		Matcher numMatcher = ints.matcher(text);
		
		if(!numMatcher.find()) return "";
		
		return text.substring(numMatcher.start(), numMatcher.end());
	}
	
	public String getCelex() {


		/*
		 * Trattati - 5 numeri fissi + 3 per l'articolo
		 */
		if(tipo.equalsIgnoreCase("trattato")) {
			//è un alias, il celex è in 'emanante'
			
			String celexNum = emanante;
			
			String art = getArt(partizione);
			
			if( !art.equals("")) {
				if(art.length() == 1) art = "00" + art;
				if(art.length() == 2) art = "0" + art;
				
				celexNum += art;
			}
			
			//Su Eur-Lex non si apre correttamente la pagina del trattato costituzionale, rimandare al primo articolo
			if(celexNum.equalsIgnoreCase("12004V")) {
				celexNum = "12004V001";
			}
			
			return "CELEX:" + celexNum;
		}
		
		/*
		 * 3AAAAtNNNN
		 */
		String t = "";
		if(tipo.equalsIgnoreCase("direttiva")) t = "L";
		if(tipo.equalsIgnoreCase("decisione")) t = "D";
		if(tipo.equalsIgnoreCase("regolamentoeu")) t = "R";
		if(tipo.equalsIgnoreCase("raccomandazione")) t = "H";
		
		String celexNum = numero;
		
		while(celexNum.length() < 4) {
			celexNum = "0" + celexNum;
		}
		
		String celexAnno = anno;
		
		if(celexAnno.equals("") && data.length() > 3) {
			celexAnno = data.substring(data.length()-4, data.length());
		}
		
		String celex = "3" + celexAnno + t + celexNum;
		
		if(celex.length() != 10) {
			System.out.println("### WARNING ### celex: " + celex + " for " + this.getText());
		}

		return "CELEX:" + celex;
	}

	@Override
	public String getCode() {

		return getCelex();
	}

	@Override
	public String getHtml() {

		return "<a href=\"" + EurLex + getCelex() + "\" target='_blank'><font color=\"red\">" 
					+ getCelex() + "</font></a>";	}
}
