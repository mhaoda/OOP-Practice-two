/*
 * This is the rabbit class.
 */

public class Rabbit extends AbstractItem {
	int stock;

	public Rabbit(AbstractGrid grid, int xCoordinate, int yCoordinate) {
		super(grid, xCoordinate, yCoordinate);
		grid.registerItem(xCoordinate, yCoordinate, this);
	}

	@Override
	public String toString() {
		return "Rabbit(" + stock + ")";
	}

	@Override
	public void process(TimeStep timeStep) {
		// TODO Auto-generated method stub
		// search the HT on the left of rabbit.
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
		// search the HT on the right of rabbit.
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
		// search the VT on the top of rabbit.
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
		// search the VT under the rabbit.
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
		if (getStock() >= 8) {
			reduceStock(8);
			grid.recordConsumption(8);
		} else {
			grid.recordConsumption(getStock());
			reduceStock(getStock());
		}
		stock = 0;
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
