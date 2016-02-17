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
package it.cnr.ittig.linkoln.ref;

public class Util {

	
	final static public String urnResolver = "http://www.normattiva.it/uri-res/N2Ls?";
	
	static String normData(String data) {
		/*
		 * Converte dal formata GGMMAAAA al formato di data URN-LEX aaaa-mm-gg
		 */
		
		if(data.length() != 8) {
			System.err.println("normData() - arg err - data:" + data);
			return "";
		}
		
		return data.substring(4) + "-" + data.substring(2,4) + "-" + data.substring(0,2);
	}

}
