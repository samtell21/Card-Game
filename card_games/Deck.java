package card_games;

import java.util.LinkedList;
import java.util.Random;

public class Deck{
	protected LinkedList<Card> deck;
	protected LinkedList<Card> table;
	protected LinkedList<Card> burn;
	
	public Deck() throws Exception{
		deck = new LinkedList<>();
		table = new LinkedList<>();
		burn = new LinkedList<>();
		for(int i = 0; i<13; i++)
			for(int j = 0; j<4; j++)
				deck.add(new Card(i,j));
	}
	
	public Card drawRand() throws OverdrawnException{
		Random r = new Random();
		//the exception is thrown when r tries to get nextInt on deck.size()=0
		//looking at it now, I should just use an if-else
		//TODO
		try{
			int i = r.nextInt(deck.size());
			Card c = deck.remove(i);
			table.add(c);
			return c;
		}
		catch(Exception e){
			swap();
			if(deck.isEmpty())
				throw new OverdrawnException("All the cards are on the table");
			
			return drawRand();
		}
	}
	
	protected void swap(){
		LinkedList<Card> t = deck;
		deck = burn;
		burn = t;
	}
	
	public void burn(){
		while(!table.isEmpty())
			burn.add(table.pop());
	}
	
	public String toString(){
		return "Deck: "+deck+"\nTable: "+table+"\nBurn: "+burn;
	}
	
	public LinkedList<?> getDeck(){
		return deck;
	}
	public LinkedList<?> getTable(){
		return table;
	}
}

