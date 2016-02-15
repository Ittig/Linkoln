package it.cnr.ittig.linkoln.scanner;

class Util {
	
	static String removeTags(String text) {
		
		//System.out.println("REMOVETAGS: " + text);
		int start = text.indexOf("<");
		int end = -1;
		
		while(start > -1) {
			
			end = text.indexOf(">");
			text = text.substring(0, start) + text.substring(end + 1);
			//System.out.println("start:" + start + " end:" + end + " text:" + text);
			start = text.indexOf("<");
		}
		
		return text;
	}
	
	static String readPartizione(String text) {

		int start = text.indexOf("<LK:PARTIZIONE");
		if(start == -1) {
			return "";
		}
		text = text.substring(start);
		
		return getValue(text);
	}
	
	static String readAnno(String text) {

		int start = text.indexOf("<LK:NUMDOC");
		if(start == -1) {
			return "";
		}
		text = text.substring(start);
		
		return getAnno(text);
	}
	
	static String readNumero(String text) {

		int start = text.indexOf("<LK:NUMDOC");
		if(start == -1) {
			return "";
		}
		text = text.substring(start);
		
		return getValue(text);
	}
	
	static String readMixed(String text) {

		int start = text.indexOf("<LK:NUMDOC");
		if(start == -1) {
			return "";
		}
		text = text.substring(start);
		
		return getMixed(text);
	}
	
	static String readData(String text) {

		int start = text.indexOf("<LK:DATA");
		if(start == -1) {
			return "";
		}
		text = text.substring(start);
		
		String data = getValue(text);
		if(data.equals("")) {
			return "";
		}
		
		return data;
	}
	
	static String readTipo(String text) {

		int start = text.indexOf("<LK:TIPO");
		if(start == -1) {
			return "";
		}
		text = text.substring(start);
		
		return getValue(text);
	}
	
	static String readEmanante(String text) {

		int start = text.indexOf("<LK:TIPO");
		if(start == -1) {
			return "";
		}
		text = text.substring(start);
		
		return getEmanante(text);
	}
		
	static String getValue(String text) {

		int start = text.indexOf("value=\"");

		if(start == -1) {
			return "";
		}

		text = text.substring(start+7);
		
		int end = text.indexOf("\"");
		
		String out = text.substring(0, end);
		
		return out;
	}
	
	static String getEmanante(String text) {

		int start = text.indexOf("emanante=\"");
		
		if(start == -1) {
			return "";
		}
		
		text = text.substring(start+10);
		
		int end = text.indexOf("\"");
		
		String out = text.substring(0, end);
		
		return out;
	}
	
	static String getAnno(String text) {

		int start = text.indexOf("anno=\"");
		
		if(start == -1) {
			return "";
		}

		text = text.substring(start+6);
		
		int end = text.indexOf("\"");
		
		String out = text.substring(0, end);
		
		return out;
	}
	
	static String getTipo(String text) {

		int start = text.indexOf("tipo=\"");
		
		if(start == -1) {
			return "";
		}

		text = text.substring(start+6);
		
		int end = text.indexOf("\"");
		
		String out = text.substring(0, end);
		
		return out;
	}
	
	static String getData(String text) {

		int start = text.indexOf("data=\"");
		
		if(start == -1) {
			return "";
		}

		text = text.substring(start+6);
		
		int end = text.indexOf("\"");
		
		String out = text.substring(0, end);
		
		return out;
	}
	
	static String getNumero(String text) {

		int start = text.indexOf("numero=\"");
		
		if(start == -1) {
			return "";
		}

		text = text.substring(start+8);
		
		int end = text.indexOf("\"");
		
		String out = text.substring(0, end);
		
		return out;
	}
	
	static String getMixed(String text) {

		int start = text.indexOf("mixed=\"");
		
		if(start == -1) {
			return "";
		}

		text = text.substring(start+7);
		
		int end = text.indexOf("\"");
		
		String out = text.substring(0, end);
		
		return out;
	}
	
	static String getPartizione(String text) {

		int start = text.indexOf("partizione=\"");
		
		if(start == -1) {
			return "";
		}

		text = text.substring(start+12);
		
		int end = text.indexOf("\"");
		
		String out = text.substring(0, end);
		
		return out;
	}
	
	static String getText(String text) {

		int tagStart = text.indexOf("LK:LRIF");
		text = text.substring(tagStart+1);
		
		int start = text.indexOf(">");
		text = text.substring(start+1);
		
		int end = text.indexOf("<");
		text = text.substring(0, end);
		
		return text;
	}
	
	static String getLatin(String text) {
		/*
		 * Normalizzazione degli ordinali LATIN.
		 * È importante l'ordine.
		 */
		
		text = text.toLowerCase().trim();
		
		if(text.indexOf("bis") > -1) return "bis";

		if(text.indexOf("undecies") > -1) return "undecies";
		if(text.indexOf("duodecies") > -1) return "duodecies";
		if(text.indexOf("quaterdecies") > -1) return "quaterdecies";
		if(text.indexOf("terdecies") > -1) return "terdecies";
		if(text.indexOf("quindecies") > -1) return "quindecies";
		if(text.indexOf("sexdecies") > -1) return "sexdecies";
		if(text.indexOf("septdecies") > -1) return "septdecies";
		if(text.indexOf("octodecies") > -1) return "octodecies";
		if(text.indexOf("novodecies") > -1) return "novodecies";
		if(text.indexOf("decies") > -1) return "decies";
		
		if(text.indexOf("unvicies") > -1) return "unvicies";
		if(text.indexOf("duovicies") > -1) return "duovicies";
		if(text.indexOf("quatervicies") > -1) return "quatervicies";
		if(text.indexOf("tervicies") > -1) return "tervicies";
		if(text.indexOf("quinvicies") > -1) return "quinvicies";
		if(text.indexOf("vicies") > -1) return "vicies";

		if(text.indexOf("quater") > -1) return "quater";
		if(text.indexOf("ter") > -1) return "ter";
		if(text.indexOf("quinquies") > -1) return "quinquies";
		if(text.indexOf("sexies") > -1) return "sexies";
		if(text.indexOf("septies") > -1) return "septies";
		if(text.indexOf("octies") > -1) return "octies";
		if(text.indexOf("novies") > -1) return "novies";
		
		return "";
	}

	static String getOrdinale(String text) {
		/*
		 * Normalizzazione degli ORDINALI.
		 */
		 
		text = text.toLowerCase().trim();
		
		if(text.indexOf("primo") > -1) return "1";
		if(text.indexOf("secondo") > -1) return "2";
		if(text.indexOf("terzo") > -1) return "3";
		if(text.indexOf("quarto") > -1) return "4";
		if(text.indexOf("quinto") > -1) return "5";
		if(text.indexOf("sesto") > -1) return "bis";
		if(text.indexOf("settimo") > -1) return "bis";
		if(text.indexOf("ottavo") > -1) return "bis";
		if(text.indexOf("nono") > -1) return "bis";
		if(text.indexOf("decimo") > -1) return "bis";
		if(text.indexOf("undicesimo") > -1) return "bis";
		if(text.indexOf("dodicesimo") > -1) return "bis";
		if(text.indexOf("tredicesimo") > -1) return "bis";
		if(text.indexOf("quattordicesimo") > -1) return "bis";
		if(text.indexOf("quindicesimo") > -1) return "bis";
		if(text.indexOf("sedicesimo") > -1) return "bis";
		if(text.indexOf("diciassettesimo") > -1) return "bis";
		if(text.indexOf("diciottesimo") > -1) return "bis";
		if(text.indexOf("diciannovesimo") > -1) return "bis";
		if(text.indexOf("ventesimo") > -1) return "bis";
		if(text.indexOf("ventunesimo") > -1) return "bis";
		if(text.indexOf("ventiduesimo") > -1) return "bis";
		if(text.indexOf("ventitreesimo") > -1) return "bis";
		if(text.indexOf("ventiquattresimo") > -1) return "bis";
		if(text.indexOf("venticinquesimo") > -1) return "bis";
			
		return "";
	}

	static String getLatinCard(String text) {
		/*
		 * Normalizzazione dei cardinali LATIN.
		 * È importante l'ordine.
		 */
		
		//Possibile input: "vii, allegata"? NO.
		
		text = text.toUpperCase().trim();

		text = text.replaceAll(",", " ");
		text = text.split(" ")[0];
				
		if(text.indexOf("XXV") > -1) return "25";
		if(text.indexOf("XXIV") > -1) return "24";
		if(text.indexOf("XXIII") > -1) return "23";
		if(text.indexOf("XXII") > -1) return "22";
		if(text.indexOf("XXI") > -1) return "21";
		if(text.indexOf("XX") > -1) return "20";
		if(text.indexOf("XIX") > -1) return "19";
		if(text.indexOf("XVIII") > -1) return "18";
		if(text.indexOf("XVII") > -1) return "17";
		if(text.indexOf("XVI") > -1) return "16";
		if(text.indexOf("XV") > -1) return "15";
		if(text.indexOf("XIV") > -1) return "14";
		if(text.indexOf("XIII") > -1) return "13";
		if(text.indexOf("XII") > -1) return "12";
		if(text.indexOf("XI") > -1) return "11";
		if(text.indexOf("IX") > -1) return "9";
		if(text.indexOf("X") > -1) return "10";
		if(text.indexOf("VIII") > -1) return "8";
		if(text.indexOf("VII") > -1) return "7";
		if(text.indexOf("VI") > -1) return "6";
		if(text.indexOf("IV") > -1) return "4";
		if(text.indexOf("V") > -1) return "5";
		if(text.indexOf("III") > -1) return "3";
		if(text.indexOf("II") > -1) return "2";
		if(text.indexOf("I") > -1) return "1";
		
		return "";
	}
	
}
