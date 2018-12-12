package Game;

import java.awt.Color;

public class Cell {
	//cell traits
	boolean isAlive = false;
	//whether or not the cell is alive. If this is false, then the rest of the data is irrelevant.
	double growth;
	//odds per iteration that a cell will grow a new one
	double age;
	//the age of the cell. always starts at 0, and increments by 1 each iteration.
	double ageKillChance;
	//this number, raised to the cell's age, determines the chance that the cell dies of age
	double minAdjCell; 
	// this # of adj cells, and down, the cell will die.
	double maxAdjCell; 
	// this # of adj cells, and up, the cell will die. Decimals are probabilities
	double mutation;
	// this # affects how likely the cells traits are to mutate each iteration
	Color color;
	public Cell() { //the "baseline" or default cell
		growth = .5;
		ageKillChance = .95;
		minAdjCell = 1;
		maxAdjCell = 4;
		mutation = .5;
		age = 0;
		color = new Color(0x808080); // Mid-gray
		isAlive = true;
	}
	public Cell(double growth, double ageKillChance, double minAdjCell, double maxAdjCell, double mutation, Color color) {
		this.growth = growth;
		this.ageKillChance = ageKillChance;
		this.minAdjCell = minAdjCell;
		this.maxAdjCell = maxAdjCell;
		this.mutation = mutation;
		this.color = color;
		age = 0;
		isAlive = true;
	}
	public Cell(boolean isAlive) {
		this.isAlive = isAlive;
	}
	public Cell(Cell cell) { // used to construct a new cell when they reproduce
		age = 0;
		growth = cell.growth;
		ageKillChance=cell.ageKillChance;
		minAdjCell=cell.minAdjCell;
		maxAdjCell=cell.maxAdjCell;
		mutation=cell.mutation;
		color = color;
		if(Grid.randChance(cell.mutation/5)) {
			growth =.5 > Math.random() ?  // this section makes it so that the value
					Math.pow(growth, 	  // never exceeds 1 or is lower than 0
							Math.random()*cell.mutation):
						Math.pow(growth, 
								1/Math.random()*cell.mutation);
		}
		if(Grid.randChance(cell.mutation/5)) {
			ageKillChance = .5 > Math.random() ? 
					Math.pow(ageKillChance, 
							Math.random()*cell.mutation):
						Math.pow(ageKillChance, 
								1/Math.random()*cell.mutation);
		}
		if(Grid.randChance(cell.mutation/5)) {
			minAdjCell = minAdjCell + 
					(cell.mutation/2)-Math.random()*cell.mutation; //this shifts it either up or down, based on mutation
		}
		if(Grid.randChance(cell.mutation/5)) {
			maxAdjCell = maxAdjCell +
					(cell.mutation/2)-Math.random()*cell.mutation;
		}
		if(Grid.randChance(cell.mutation/5)) {
			mutation = .5 > Math.random() ? 
					Math.pow(mutation, 
							Math.random()*cell.mutation):
						Math.pow(mutation, 
								1/Math.random()*cell.mutation);
		}
		int R = color.getRed();
		int G = color.getGreen();
		int B = color.getBlue();
		if(Grid.randChance(cell.mutation/5)) {
			R = (int) (.5 > Math.random() ?
					Math.round(Math.pow(R/0xFF, 
							Math.random()*cell.mutation)*0xFF):
						Math.round(Math.pow(R/0xFF, 
								Math.random()*cell.mutation)*0xFF));
		}
		if(Grid.randChance(cell.mutation/5)) {
			G = (int) (.5 > Math.random() ?
					Math.round(Math.pow(G/0xFF, 
							Math.random()*cell.mutation)*0xFF):
						Math.round(Math.pow(G/0xFF, 
								Math.random()*cell.mutation)*0xFF));
		}
		if(Grid.randChance(cell.mutation/5)) {
			B = (int) (.5 > Math.random() ?
					Math.round(Math.pow(B/0xFF, 
							Math.random()*cell.mutation)*0xFF):
						Math.round(Math.pow(B/0xFF, 
								Math.random()*cell.mutation)*0xFF));
		}
		color = new Color(R, G, B);
		/*if(Grid.randChance(cell.mutation/5)) {
		growth = .5 > Math.random() ? 
				Math.pow(growth, 
						Math.random()*cell.mutation):
					Math.pow(growth, 
							1/Math.random()*cell.mutation);
	}*/
	}
	
}
