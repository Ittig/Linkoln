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

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class Run {

	
	public static void main(String[] args) {

		/*
		 * java -jar it.cnr.ittig.linkoln.Run input_file.txt
		 */
		

		if(args.length != 1) {
			
			help();
			return;
		}
		
		String fname = args[0].trim();
		
		File input = new File(fname);
		
		if( !input.exists() || input.isDirectory()) {
			
			help();
			return;
		}
		
		String text = "";
		
		try {
			
			text = file2txt(input, StandardCharsets.UTF_8);
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Linkoln linkoln = new Linkoln();
		linkoln.setInputText(text);
		
		try {
		
			linkoln.run();
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("\n" + linkoln.getHrefText() + "\n");
		
	}

	private static String file2txt(File inputFile, Charset charSet) throws IOException {
		
		BufferedReader reader = null; 
		String line = null;
	    StringBuilder stringBuilder = new StringBuilder();
	    String ls = System.getProperty("line.separator");
	    
	    if(charSet == null) {
	    	reader = new BufferedReader( new InputStreamReader(
	    			new FileInputStream(inputFile)));
	    } else {
	    	reader = new BufferedReader( new InputStreamReader(
	    			new FileInputStream(inputFile), charSet));
	    }

	    while( ( line = reader.readLine() ) != null ) {
	        stringBuilder.append( line );
	        stringBuilder.append( ls );
	    }

	    reader.close();
	    
	    return stringBuilder.toString();
	}
	
	private static void help() {
		
		String help = "\n\nLinkoln (v." + Linkoln.version + ")";
		
		help += "\n\n java -jar ittig-linkoln.jar input_file.txt\n\n";
		
		System.out.println(help);
	}
}
