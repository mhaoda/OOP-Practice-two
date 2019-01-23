/*
 * This is the beaver class
 */
public class Beaver extends AbstractItem {
	int stock;

	public Beaver(Grid grid, int xCoordinate, int yCoordinate) {
		super(grid, xCoordinate, yCoordinate);
		grid.registerItem(xCoordinate, yCoordinate, this);
	}

	@Override
	public String toString() {
		return "Beaver(" + stock + ")";
	}

	@Override
	public void process(TimeStep timeStep) {
		// TODO Auto-generated method stub
		// search the HT on the left of beaver.
		for (int y = 0; y < yCoordinate; y++) {
			if (grid.grid[xCoordinate][y] != null) {
				if (grid.grid[xCoordinate][y].getClass().equals(HorizontalTransporter.class)) {
					if (grid.grid[xCoordinate][y].getStock() > 0) {
						addToStock(grid.grid[xCoordinate][y].getStock());
						grid.grid[xCoordinate][y].reduceStock(grid.grid[xCoordinate][y].getStock());
					}
				}
			}
		}
		// search the HT on the right of beaver.
		for (int y = yCoordinate; y < grid.grid[xCoordinate].length; y++) {
			if (grid.grid[xCoordinate][y] != null) {
				if (grid.grid[xCoordinate][y].getClass().equals(HorizontalTransporter.class)) {
					if (grid.grid[xCoordinate][y].getStock() > 0) {
						addToStock(grid.grid[xCoordinate][y].getStock());
						grid.grid[xCoordinate][y].reduceStock(grid.grid[xCoordinate][y].getStock());
					}
				}
			}
		}
		// search the VT on the top of beaver.
		for (int x = 0; x < xCoordinate; x++) {
			if (grid.grid[x][yCoordinate] != null) {
				if (grid.grid[x][yCoordinate].getClass().equals(VerticalTransporter.class)) {
					if (grid.grid[x][yCoordinate].getStock() > 0) {
						addToStock(grid.grid[x][yCoordinate].getStock());
						grid.grid[x][yCoordinate].reduceStock(grid.grid[x][yCoordinate].getStock());
					}
				}
			}
		}
		// search the VT under the beaver.
		for (int x = xCoordinate; x < grid.grid.length; x++) {
			if (grid.grid[x][yCoordinate] != null) {
				if (grid.grid[x][yCoordinate].getClass().equals(VerticalTransporter.class)) {
					if (grid.grid[x][yCoordinate].getStock() > 0) {
						addToStock(grid.grid[x][yCoordinate].getStock());
						grid.grid[x][yCoordinate].reduceStock(grid.grid[x][yCoordinate].getStock());
					}
				}
			}
		}
		// search the NT from the whole grid and judge if the coordinate stored in it
		// equals to beaver's coordinate.
		for (int m = 0; m < grid.grid.length; m++) {
			for (int n = 0; n < grid.grid[m].length; n++) {
				if (grid.grid[m][n] != null && grid.grid[m][n].getClass().equals(NearestTransporter.class)) {
					NearestTransporter nt = (NearestTransporter) grid.grid[m][n];
					if (nt.conx == xCoordinate && nt.cony == yCoordinate) {
						if (grid.grid[m][n].getStock() > 0) {
							addToStock(grid.grid[m][n].getStock());
							grid.grid[m][n].reduceStock(grid.grid[m][n].getStock());
						}
					}
				}
			}
		}
		// consume the food.
		if (getStock() >= 5) {
			reduceStock(5);
			grid.recordConsumption(5);
		}
		// up to the upper limit 
		if (getStock() > 50) {
			stock = 50;
		}
	}

	@Override
	protected int getStock() {
		// TODO Auto-generated method stub
		return stock;
	}

	@Override
	protected void addToStock(int nutrition) {
		// TODO Auto-generated method stub
		stock = stock + nutrition;
	}

	@Override
	protected void reduceStock(int nutrition) {
		// TODO Auto-generated method stub
		stock = stock - nutrition;
	}

}
