/*
 * This is the radish farmer class.
 */
public class RadishFarmer extends AbstractItem {
	int stock;

	public RadishFarmer(AbstractGrid grid, int xCoordinate, int yCoordinate) {
		super(grid, xCoordinate, yCoordinate);
		grid.registerItem(xCoordinate, yCoordinate, this);
	}

	@Override
	public String toString() {
		return "Radish(" + stock + ")";
	}

	@Override
	public void process(TimeStep timeStep) {
		// TODO Auto-generated method stub
		boolean work = true;
		// if there are any farmers around the radish farmer.
		for (int y = yCoordinate - 1; y < yCoordinate; y++) {
			if (y >= 0 && grid.grid[xCoordinate][y] != null) {
				if (grid.grid[xCoordinate][y].getClass().equals(CornFarmer.class)
						|| grid.grid[xCoordinate][y].getClass().equals(RadishFarmer.class)) {
					work = false;
				}
			}
		}
		for (int y = yCoordinate + 1; y < yCoordinate + 1; y++) {
			if (y <= grid.grid.length && grid.grid[xCoordinate][y] != null) {
				if (grid.grid[xCoordinate][y].getClass().equals(CornFarmer.class)
						|| grid.grid[xCoordinate][y].getClass().equals(RadishFarmer.class)) {
					work = false;
				}
			}
		}
		for (int x = xCoordinate - 1; x < xCoordinate; x++) {
			if (x >= 0 && grid.grid[x][yCoordinate] != null) {
				if (grid.grid[x][yCoordinate].getClass().equals(CornFarmer.class)
						|| grid.grid[x][yCoordinate].getClass().equals(RadishFarmer.class)) {
					work = false;
				}
			}
		}
		for (int x = xCoordinate + 1; x <= xCoordinate + 1; x++) {
			if (x < grid.grid.length && grid.grid[x][yCoordinate] != null) {
				if (grid.grid[x][yCoordinate].getClass().equals(CornFarmer.class)
						|| grid.grid[x][yCoordinate].getClass().equals(RadishFarmer.class)) {
					work = false;
				}
			}
		}
		// produce the food.
		if (timeStep.getValue() % 3 == 0 && work) {
			grid.recordProduction(10);
			addToStock(10);
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
