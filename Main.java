package Game;

public class Main {

	
	public static void main(String[] args) {
		Drawing.color = (int) Math.round(Math.random()*0xFFFFFF);
		Grid gameGrid = new Grid(25,25);
		gameGrid.addCell(new Cell(), 5, 5);
		Drawing d = new Drawing();
		Drawing.pixArr = Drawing.grid(gameGrid);
		Drawing.updateNow = true;
		d.Draw();
	}
	public static Cell test(Cell c) {
		//Cell c = new Cell();
		Cell b = new Cell(c);
		/*System.out.println("ageKill: " + b.ageKillChance);
		System.out.println("Growth: "+b.growth);
		System.out.println("Max: "+b.maxAdjCell);
		System.out.println("Min: "+b.minAdjCell);
		System.out.println("Mutate: "+b.mutation);*/
		return b;
		
	}
}
