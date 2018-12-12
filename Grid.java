package Game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Grid {
	Cell grid[][];
	int width;
	int height;

	public Grid(int width, int height) { // initializes grid
		this.width = width;
		this.height = height;
		grid = new Cell[height][width];
		for (int y = 0; y < height; y++) { // iterates through all y coord levels
			for (int x = 0; x < width; x++) { // iterates through all x coords of prior y level
				grid[y][x] = new Cell(false); // sets all cells to being 'dead', or inactive
			}
		}
	}

	public void iterate() {
		List<int[]> usedCells = new ArrayList<int[]>();
		// contains list of all cells that are created this iteration
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				
				//this block tests if the current coord has been made in this iteration
				int[] coord = {x,y};
				boolean used = false;
				for(int i = 0; i < usedCells.size();i++) {
					if(Arrays.equals(usedCells.get(i),coord)) {
						used = true;
						break;
					}
				}
				if(used)
					continue;
				
				if (grid[y][x].isAlive) { // If the cell at the x,y coords is alive, do this
					if(randChance(Math.pow(grid[y][x].ageKillChance,grid[y][x].age))){
						//runs the cell's chance to die, based on its age
						grid[y][x] = new Cell(false);
						continue;
					}
					grid[y][x].age += 1;
					//the cell is still alive, add 1 to its age.
					if(randChance(grid[x][y].growth) && //if the cell will grow
							adjLiving(x, y)<7) { // if there are under 7 adjacent cells, or at least 2 open spaces
						int[][] adj = getAdjSpaces(x, y);
						int[] a;
						int[] b;
						int rand;
						while(true){
							rand = (int) Math.round(Math.random()*8);
							if(!grid[adj[rand][0]][adj[rand][1]].isAlive) {
								//if the randomly selected spot is dead
								a = new int[2];
								a[0] = adj[rand][0];
								a[1] = adj[rand][0];
								grid[a[0]][a[1]] = new Cell(grid[a[0]][a[1]]);
								coord[0] = a[0];
								coord[1] = a[1];
								usedCells.add(coord);
								break;
							}
						}
						while(true){
							rand = (int) Math.round(Math.random()*8);
							if(!grid[adj[rand][0]][adj[rand][1]].isAlive) {
								//if the randomly selected spot is dead
								b = new int[2];
								b[0] = adj[rand][0];
								b[1] = adj[rand][0];
								grid[b[0]][b[1]] = new Cell(grid[b[0]][b[1]]);
								coord[0] = a[0];
								coord[1] = a[1];
								usedCells.add(coord);
								break;
							}
						grid[y][x] = new Cell(false); 
						// the cell splits into 2. Thus, its original position must be empty
						}
					}
				}
			}
		}
		
	}
	public void addCell(Cell cell,int x, int y) {
		grid[x][y] = cell;
	}
	public int[][] getAdjSpaces(int x, int y) { // returns the coordinates of the adjacent spaces to the given coord
		int out[][] = new int[8][2];
		int its = 0; // # of times the for loops have looped, excluding when a == b == 0
		for (int a = -1; a < 1; a++) { // iterate through the y adjacent vals
			for (int b = -1; b < 1; b++) { // iterate through the x adjacent vals
				if (a == 0 && b == 0) { // if a & b == 0, then its looking at the center itself
					continue;
				}
				out[its][0] = foldWidth(x + b);
				out[its][1] = foldHeight(y + a);
				its++;
			}
		}
		return out;
	}
	public int adjLiving(int x, int y) {
		//returns the # of living cells adj. to the provided coord
		int out = 0;
		int adj[][] = getAdjSpaces(x,y);
		for(int[] i : adj)  {
			if(grid[i[0]][i[1]].isAlive) {
				out++;
			}
		}
		return out;
	}
	public int foldHeight(int y) {
		if (y > height)
			return y % height;
		if (y < 0)
			return height - y;
		return y;
	}

	public int foldWidth(int x) {
		if (x > width)
			return x % width;
		if (x < 0)
			return width - x;
		return x;
	}
	public static boolean randChance(double chance) {
		//returns true or false based on provided probability(of returning true)
		return chance > Math.random();
	}
}
