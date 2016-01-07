package it.cnr.ittig.linkoln.scanner;

class UtilRif {
	
	static String openRifTag = "<font color=\"LightBlue\"><LK:LRIF ";  
	static String closeRifTag = "</LK:LRIF></font>"; 

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
	

}
