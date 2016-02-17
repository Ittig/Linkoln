/*******************************************************************************
 * Copyright (c) 2016 Institute of Legal Information Theory and Techniques (ITTIG).
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU GPL license v3.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/gpl-3.0.en.html
 *
 * Contributors: 
 *   Institute of Legal Information Theory and Techniques (ITTIG/CNR)
 *******************************************************************************/
package it.cnr.ittig.linkoln.scanner;

public class Riferimenti {

	protected static String openRifTag = "<font color=\"LightBlue\"><LK:LRIF ";  
	protected static String closeRifTag = "</LK:LRIF></font>";
	
	protected static String openRifsTag = "<LK:LRIFS>";  
	protected static String closeRifsTag = "</LK:LRIFS>";
	
	protected StringBuilder output = new StringBuilder();

	public String getOutput() {
		return output.toString();
	}

	protected String riferimento = ""; 
	protected int offset = 0;
	
	protected String tipo = "";
	protected String emanante = "";
	protected String data = "";
	protected String anno = "";
	protected String numero = "";
	protected String mixed = "";
	protected String partizione = "";
		
	protected void saveRif(String text) {
		
		output.append(openRifTag + getAttributi(text) + "\">" + Util.removeTags(text) + closeRifTag);	
	}
	
	protected void saveRif(String text, boolean keepTipo, boolean keepData) {
		
		output.append(openRifTag + getAttributi(text, keepTipo, keepData) + "\">" + Util.removeTags(text) + closeRifTag);	
	}
	
	protected void openRifs() {
		
		output.append(openRifsTag);
	}
	
	protected void closeRifs() {
		
		output.append(closeRifsTag);
	}
	
	protected void readAttributi(String text) {
		
		tipo = Util.readTipo(text);
		emanante = Util.readEmanante(text);
		data = Util.readData(text);
		anno = Util.readAnno(text);
		numero = Util.readNumero(text);
		mixed = Util.readMixed(text);
		partizione = Util.readPartizione(text);
	}
	
	protected void readAttributiEmanante(String text) {
		
		tipo = Util.readTipo(text);
		data = Util.readData(text);		
		anno = Util.readAnno(text);
		numero = Util.readNumero(text);
		mixed = Util.readMixed(text);		
		partizione = Util.readPartizione(text);
		
		int start = text.indexOf("<LK:EMANANTE");
		if(start == -1) return;
	
		text = text.substring(start);
		int cut = text.indexOf("value=\"");
		if(cut == -1) return;
		String cutStr = text.substring(cut+7);
		int end = cutStr.indexOf("\"");
		
		emanante = cutStr.substring(0, end);
	}
	
	protected void readAttributiAutorita(String text) {
		
		tipo = Util.readTipo(text);
		data = Util.readData(text);		
		anno = Util.readAnno(text);
		numero = Util.readNumero(text);
		mixed = Util.readMixed(text);
		partizione = Util.readPartizione(text);

		int start = text.indexOf("<LK:AUTORITA");
		if(start == -1) return;
	
		text = text.substring(start);
		int cut = text.indexOf("value=\"");
		if(cut == -1) return;
		String cutStr = text.substring(cut+7);
		int end = cutStr.indexOf("\"");
		
		emanante = cutStr.substring(0, end);	
	}
	
	protected void readAttributiMinistero(String text) {
		
		tipo = Util.readTipo(text);
		data = Util.readData(text);		
		anno = Util.readAnno(text);
		numero = Util.readNumero(text);
		mixed = Util.readMixed(text);
		partizione = Util.readPartizione(text);
		
		//Possono esserci più MINISTERI consecutivi		
		int start = text.indexOf("<LK:MINISTERO");
		String ema = "";
		
		while(start > -1) {
			
			text = text.substring(start);
			int cut = text.indexOf("value=\"");
			if(cut == -1) return;
			String cutStr = text.substring(cut+7);
			int end = cutStr.indexOf("\"");
			
			if(ema.equals("")) {
				
				ema = cutStr.substring(0, end);
			
			} else {
				
				ema += "+" + cutStr.substring(0, end);
			}
			
			text = text.substring(end);
			start = text.indexOf("<LK:MINISTERO");
		}
		
		emanante = ema;
	}
	
	protected void readAttributiAlias(String text) {
		/* Dal tag ALIAS si leggono: tipo, emanante, data, numero */
		
		int start = text.indexOf("<LK:ALIAS");
		if(start == -1) {
			return;
		}
	
		text = text.substring(start);
		int cut = text.indexOf("emanante=\"");
		if(cut == -1) return; 
		String cutStr = text.substring(cut+10);
		int end = cutStr.indexOf("\"");
		emanante = cutStr.substring(0, end);

		cut = text.indexOf("value=\"");
		if(cut == -1) return;
		cutStr = text.substring(cut+7);
		end = cutStr.indexOf("\"");
		tipo = cutStr.substring(0, end);
		
		cut = text.indexOf("data=\"");
		if(cut == -1) return;
		cutStr = text.substring(cut+6);
		end = cutStr.indexOf("\"");
		//data = normData(cutStr.substring(0, end));
		data = cutStr.substring(0, end);
		
		cut = text.indexOf("numero=\"");
		if(cut == -1) return;
		cutStr = text.substring(cut+8);
		end = cutStr.indexOf("\"");
		numero = cutStr.substring(0, end);
				
	}
	
	protected String getAttributi(String text, boolean keepTipo, boolean keepData) {
		
		if( !keepTipo) {
			tipo = Util.readTipo(text);
			emanante = Util.readEmanante(text);
		}
		if( !keepData) {
			data = Util.readData(text);
		}
		anno = Util.readAnno(text);
		numero = Util.readNumero(text);
		mixed = Util.readMixed(text);
		partizione = Util.readPartizione(text);
		
		String attributi = "tipo=\"" + tipo + "\" emanante=\"" + emanante + "\" data=\"" + data + 
				"\" anno=\"" + anno + "\" numero=\"" + numero + "\" mixed=\"" + mixed + "\" partizione=\"" + partizione;  
	
		return attributi;
	}
	
	protected String getAttributi(String text) {
		/* Legge soltanto la partizione per ricalcolare la urn */
		
		partizione = Util.readPartizione(text);
		
		String attributi = "tipo=\"" + tipo + "\" emanante=\"" + emanante + "\" data=\"" + data + 
					"\" anno=\"" + anno + "\" numero=\"" + numero + "\" mixed=\"" + mixed + "\" partizione=\"" + partizione;  
		
		return attributi;
	}
	
}
