package card_games;

import java.util.*;

public class Multideck extends Deck{
    
    public Multideck(int n) throws Exception{
        deck = new LinkedList<>();
		table = new LinkedList<>();
		burn = new LinkedList<>();
		for(int s = 0; s<n; s++)
            for(int i = 0; i<13; i++)
                for(int j = 0; j<4; j++)
                    deck.add(new Card(i,j));
    }
}
