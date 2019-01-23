/*
 * This is the corn farmer class
 */
public class CornFarmer extends AbstractItem {
	int stock;

	public CornFarmer(AbstractGrid grid, int xCoordinate, int yCoordinate) {
		super(grid, xCoordinate, yCoordinate);
		grid.registerItem(xCoordinate, yCoordinate, this);
	}

	@Override
	public String toString() {
		return "Corn(" + stock + ")";
	}

	@Override
	public void process(TimeStep timeStep) {
		// TODO Auto-generated method stub
		boolean work = true;
		// search the 2 space on the left of the corn farmer.
		for (int y = yCoordinate - 2; y < yCoordinate; y++) {
			if (y >= 0 && grid.grid[xCoordinate][y] != null) {
				if (grid.grid[xCoordinate][y].getClass().equals(CornFarmer.class)
						|| grid.grid[xCoordinate][y].getClass().equals(RadishFarmer.class)) {
					work = false;
				}
			}
		}
		// search the 2 space on the right of the corn farmer.
		for (int y = yCoordinate + 1; y < yCoordinate + 2; y++) {
			if (y <= grid.grid[xCoordinate].length && grid.grid[xCoordinate][y] != null) {
				if (grid.grid[xCoordinate][y].getClass().equals(CornFarmer.class)
						|| grid.grid[xCoordinate][y].getClass().equals(RadishFarmer.class)) {
					work = false;
				}
			}
		}
		// search the space on the top of the corn farmer.
		for (int x = xCoordinate - 1; x < xCoordinate; x++) {
			if (x >= 0 && grid.grid[x][yCoordinate] != null) {
				if (grid.grid[x][yCoordinate].getClass().equals(CornFarmer.class)
						|| grid.grid[x][yCoordinate].getClass().equals(RadishFarmer.class)) {
					work = false;
				}
			}
		}
		// search the space under the corn farmer.
		for (int x = xCoordinate + 1; x <= xCoordinate + 1; x++) {
			if (x < grid.grid.length && grid.grid[x][yCoordinate] != null) {
				if (grid.grid[x][yCoordinate].getClass().equals(CornFarmer.class)
						|| grid.grid[x][yCoordinate].getClass().equals(RadishFarmer.class)) {
					work = false;
				}
			}
		}
		// produce the food.
		if (timeStep.getValue() % 4 == 0 && work) {
			grid.recordProduction(25);
			addToStock(25);
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
		stock += nutrition;
	}

	@Override
	protected void reduceStock(int nutrition) {
		// TODO Auto-generated method stub
		stock -= nutrition;
	}

}
