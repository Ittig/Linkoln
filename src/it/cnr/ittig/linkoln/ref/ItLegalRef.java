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
package it.cnr.ittig.linkoln.ref;

public class ItLegalRef extends LegalRef {

	
	//URN-LEX override
	public String getUrnLex() {
		/*
		 * Implementazione UrnLex per riferimenti a legislazione primaria.
		 */
		
		String urn = "urn:nir:" + emanante + ":" + tipo + ":";
		
		if( !data.equals("")) {
			urn += Util.normData(data);
		} else {
			urn += anno;
		}
		
		String urnNumero = numero;
		
		if( !mixed.equals("")) {
			
			urnNumero = mixed;
		}
		
		urn += ";" + urnNumero;
		
		if( !partizione.equals("")) {
			if(partizione.startsWith("all") || partizione.startsWith("tab")) {
				urn += ":" + partizione;
			} else {
				urn += "~" + partizione;
			}
		}
		
		return urn;
	}

	public String getUrnLexHtml() {
		
		return "<a href=\"" + Util.urnResolver + getUrnLex() + "\" target='_blank'>" + text + "</a>";
	}

	//ELI override
	public String getEli() {

		return "";
	}

	public String getEliHtml() {

		return "";
	}

	@Override
	public String getCelex() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getCelexHtml() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getCode() {

		return getUrnLex();
	}

	@Override
	public String getHtml() {

		return "<a href=\"" + Util.urnResolver + getUrnLex() 
				+ "\" target='_blank'>" + getUrnLex() + "</a>";
	}
	
}
