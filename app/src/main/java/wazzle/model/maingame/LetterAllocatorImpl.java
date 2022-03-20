package wazzle.model.maingame;

import java.util.EnumMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import javafx.util.Pair;

public class LetterAllocatorImpl implements LetterAllocator {
	
	private EnumMap<Range, List<Pair<Character, Double>>> choosenLetters;
	
	public LetterAllocatorImpl(EnumMap<Range, List<Pair<Character, Double>>> choosenLetters) {
		this.choosenLetters = new EnumMap<Range, List<Pair<Character, Double>>>(choosenLetters);
	}
	
	public Set<Letter> alloc(){
		//4 - RIMANDO IL CONTROLLO FINO A QUANDO LA GRIGLIA DOPO IL CONTROLLO NON SARA' 
		//DI DIMENSIONI UGUALI A QUELLE DELLA GRIGLIa
		
		System.out.println(this.getSubgridDimension(Stream.of(Range.values())
				  .map(Range::getWeight)
				  .reduce(0, (x, y) -> x + y)));
		return new HashSet<Letter>();
	}
	
	private Pair<Integer, Integer> getSubgridDimension(int totalWeight) {
		return (int) Math.sqrt(totalWeight) * (int) Math.sqrt(totalWeight) == totalWeight ? 
				new Pair<>((int) Math.sqrt(totalWeight), (int) Math.sqrt(totalWeight)) :
				new Pair<>((int) Math.sqrt(totalWeight), totalWeight/(int) Math.sqrt(totalWeight));
	}
	
	private void findSubgrid(int gridDimension, int totalWeight) {
		//1 - DIVIDO IN SOTTOGRIGLIE DI DIMENSIONI QUADRATE O RETTANGOLARI (SQRT(DIMGRIGLIA)) CHE ABBIANO 
		//TANTE CELLE QUANTO LA SOMMA DEI PESI DEI RANGE	
	}
	
	private void extractLetter() {
		//2 - PER OGNI SOTTOGRIGLIA ESTRAGGO CASUALMENTE LE SUE LETTERE IN QTA = PESO DEI RANGE, 
		//FACENDO ATTENZIONE A NON SOVRASCRIVERE QUELLE GIA MESSE
	}
	
	private void checkGrid() {
		//3 - AL TERMINE DELLA GENERAZIONE CASUALE FACCIO UN CONTROLLO CHE ELIMINA LE CELLE IN PIU' DI OGNI TIPO
	}
}
