package it.polito.tdp.anagrammi.model;

import java.util.HashSet;
import java.util.Set;

import it.polito.tdp.anagrammi.DAO.AnagrammaDAO;

public class Model {
	
	private Set<String> anagrammi;
	private AnagrammaDAO anagrammaDAO;
	
	public Model() {
		this.anagrammaDAO = new AnagrammaDAO();
	}
	
	public Set<String> trovaAnagrammi(String parola){
		this.anagrammi = new HashSet<>();
		this.anagrammiRicorsivo("", parola, 0);
		return this.anagrammi;
	}
	
	private void anagrammiRicorsivo(String parziale, String rimanenti, int livello) {
		// terminazione
		if(rimanenti.length()==0) {
			this.anagrammi.add(new String(parziale));
			return;
		}
		// ricorsione
		for(int i=0; i<rimanenti.length(); i++) {
			// String nuova_parziale = parziale + rimanenti.charAt(i);
			// String nuova_rimanenti = rimanenti.substring(0, i) + rimanenti.substring(i + 1);
			// this.anagrammiRicorsivo(nuova_parziale, nuova_rimanenti, livello+1);
			parziale += rimanenti.charAt(i);
			rimanenti = rimanenti.substring(0, i) + rimanenti.substring(i+1);
			this.anagrammiRicorsivo(parziale, rimanenti, livello+1);
			// backtracking
			rimanenti = rimanenti.substring(0, i) + parziale.charAt(parziale.length()-1) + rimanenti.substring(i);
			parziale = parziale.substring(0, parziale.length()-1); 
		}
	}
	
	public boolean isCorrect(String anagramma) {
		return this.anagrammaDAO.isCorrect(anagramma);
	}

}
