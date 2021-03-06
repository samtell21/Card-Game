package card_games;

import java.util.LinkedList;

public class Hand2{
	private final LinkedList<Card> l = new LinkedList<>();
	private int b;
	
	public Hand2(Deck d) throws Exception{
		b = 0;
		hit(d); hit(d);
	}
	protected Hand2 (Card c, Deck d) throws Exception{
		b=0;
		l.add(c);
		hit(d);
	}
	
	public Hand2(Card c, Card d){
		l.add(c);
		l.add(d);
	}
	
	public void hit(Deck d) throws Exception
	{
		l.add(d.drawRand());
	}
	
	public Hand2 split(Deck d) throws Exception{
		if(l.size()!=2)
			throw new Exception("Hand not splittable");
		hit(d);
		return new Hand2(l.remove(1),d);
	}
	
	public void bet(int n){
		b+=n;
	}
	public int getBet(){
		return b;
	}
	
	public String toString(){
		return l.toString();
	}
	public String dealerString(){
		String o = "[XXXX, ";
		return o+ l.get(1)+"]";
	}
	
	public boolean dealerHit(Deck d) throws Exception{
		if(tot()<17){
			hit(d);
			return true;
		}
		else{
			return false;
		}
	}
	
	int highNutBust(LinkedList<Integer> l) throws Exception{
		LinkedList<Integer> m = new LinkedList<>();
		while(!l.isEmpty()){
			int x = l.pop();
			if(x<=21)
				m.add(x);
		}
		if(m.isEmpty())
		//jezus what is with this use of exceptions for flow control??
		//TODO
			throw new Exception("All are bust!");
		int x = m.get(0);
		for(int i=1;i<m.size();i++)
			if(m.get(i)>x)
				x = m.get(i);
		return x;
	}
	
	public int tot(){
		LinkedList<Integer> x = new LinkedList<>();
		x.add(0);
		for(Card c:l){
			try{
				for(int i=0;i<x.size();i++)
					x.set(i, x.get(i)+Integer.parseInt(c.getNum()));
			}
			catch(Exception e){
				switch(c.getNum()){
					case "A":
						int t = x.size();
						for(int i=0;i<t;i++){
							x.add(x.get(i)+11);
							x.set(i, x.get(i)+1);
						}
						break;
					case "J":case"Q":case"K":
						for(int i=0;i<x.size();i++)
							x.set(i, x.get(i)+10);
				}
			}
		}
		try{
			return highNutBust(x);
		}
		catch(Exception e){
			return 22;
		}
	}
	
	public boolean bust(){
		return tot()>21;
	}
	
	public int size(){
		//System.out.println(l.size());
		return l.size();
	}
	
	public boolean isSplit(){
		return l.get(0).getNum().equals(l.get(1).getNum());
	}
}

