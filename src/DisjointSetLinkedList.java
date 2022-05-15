import java.security.interfaces.ECKey;

public class DisjointSetLinkedList implements DisjointSetDataStructure {

	private class Element{
		int representant;
		int next;
		int length;
		int last;

	}
	
	private static final int NULL=-1;
	
	Element arr[];
	
	public DisjointSetLinkedList(int size) {
		arr = new Element[size];
		for(int i = 0; i < arr.length; i++){
			makeSet(i);
		}
	}
	
	@Override
	public void makeSet(int item) {
		arr[item]=new Element();
		arr[item].length=1;
		arr[item].representant=item;
		arr[item].last=item;
		arr[item].next=NULL;
	}

	@Override
	public int findSet(int item) {
		//TODO
		if(item<0||item>= arr.length)return NULL;
		return arr[item].representant;
	}

	@Override
	public boolean union(int itemA, int itemB) {
		//TODO
		if(findSet(itemA)==-1||findSet(itemB)==-1)return false;
		if(findSet(itemA)==findSet(itemB))return false;
		Element rep1 = arr[findSet(itemA)];
		Element rep2 = arr[findSet(itemB)];
		if(rep1.length < rep2.length){
			rep2 = arr[findSet(itemA)];
			rep1 = arr[findSet(itemB)];
		}
		arr[rep1.last].next = rep2.representant;
		rep1.last = rep2.last;
		rep1.length = rep1.length+ rep2.length;
		Element actelem = rep2;
		for(int i = 0; i < rep2.length; i++){
			actelem.representant = rep1.representant;
			if(actelem.next!=NULL)actelem = arr[actelem.next];
		}
		return true;

	}

	
	@Override
	public String toString() {
		if(arr.length==0)return "Disjoint sets as linked list:";
		String retstr = "Disjoint sets as linked list:+\n";
		int zbior = 0;
		while (zbior!= arr.length){
			if(arr[zbior].representant==zbior){
				int temp = zbior;
				while(temp!=-1){
					retstr+=temp;
					if(arr[temp].next!=-1)retstr+=", ";
					temp = arr[temp].next;
				}
				retstr +="\n";
			}
			zbior++;
		}
		retstr = retstr.substring(0,retstr.length()-1);
		return retstr;
	}

}
