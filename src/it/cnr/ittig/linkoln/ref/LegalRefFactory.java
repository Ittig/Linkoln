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
