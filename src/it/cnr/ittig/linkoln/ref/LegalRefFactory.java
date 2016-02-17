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

public class LegalRefFactory {
	
	
	public static LegalRef getLegalRef(
			String tipo, String emanante, String mixed) {

			LegalRef rif = null;
			
			if(tipo.equals("direttiva") 
			|| tipo.equals("decisione")
			|| tipo.equals("regolamentoeu")
			|| tipo.equals("raccomandazione") 
			|| tipo.equals("trattato")) {
			
				rif = new EuLegalRef();
			
			} else {
			
				rif = new ItLegalRef();
			}
			
			rif.setTipo(tipo);
			rif.setEmanante(emanante);
			rif.setMixed(mixed);
			
			return rif;
		}

}
