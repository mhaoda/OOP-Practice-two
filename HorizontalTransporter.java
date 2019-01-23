/*
 * This is the horizontal transporter class, which to transport foods 
 * from farmers to animals in the same column.
 */
public class HorizontalTransporter extends AbstractItem {
	int stock, num;

	public HorizontalTransporter(Grid grid, int xCoordinate, int yCoordinate, int num) {
		super(grid, xCoordinate, yCoordinate);
		this.num = num;
		grid.registerItem(xCoordinate, yCoordinate, this);
	}

	public int getNum() {
		return num;
	}

	@Override
	public String toString() {
		return "HT";
	}

	@Override
	public void process(TimeStep timeStep) {
		for (int y = 0; y < yCoordinate; y++) {
			if (grid.grid[xCoordinate][y] != null) {
				// if there are farmers on the left of the HT.
				if (grid.grid[xCoordinate][y].getClass().equals(CornFarmer.class)
						|| grid.grid[xCoordinate][y].getClass().equals(RadishFarmer.class)) {
					//if there are animals on the right of the HT.
					for (int yy = yCoordinate; yy < grid.grid[xCoordinate].length; yy++) {
						if (grid.grid[xCoordinate][yy] != null) {
							if (grid.grid[xCoordinate][yy].getClass().equals(Beaver.class)
									|| grid.grid[xCoordinate][yy].getClass().equals(Rabbit.class)) {
								// if the stock of the farmer is enough for HT to carry.
								if (grid.grid[xCoordinate][y].getStock() >= getNum()) {
									addToStock(getNum());
									grid.grid[xCoordinate][y].reduceStock(getNum());
								} else {
									addToStock(grid.grid[xCoordinate][y].getStock());
									grid.grid[xCoordinate][y].reduceStock(grid.grid[xCoordinate][y].getStock());
								}

							}
						}
					}
				}
			}
		}

		for (int y = 0; y < yCoordinate; y++) {
			if (grid.grid[xCoordinate][y] != null) {
				// if there are animals on the left of the HT.
				if (grid.grid[xCoordinate][y].getClass().equals(Beaver.class)
						|| grid.grid[xCoordinate][y].getClass().equals(Rabbit.class)) {
					for (int yy = yCoordinate; yy < grid.grid[xCoordinate].length; yy++) {
						if (grid.grid[xCoordinate][yy] != null) {
							// if there are farmers on the right of the HT.
							if (grid.grid[xCoordinate][yy].getClass().equals(CornFarmer.class)
									|| grid.grid[xCoordinate][yy].getClass().equals(RadishFarmer.class)) {
								// if the stock of the farmer is enough for HT to carry.
								if (grid.grid[xCoordinate][yy].getStock() >= getNum()) {
									addToStock(getNum());
									grid.grid[xCoordinate][yy].reduceStock(getNum());
								} else {
									addToStock(grid.grid[xCoordinate][yy].getStock());
									grid.grid[xCoordinate][yy].reduceStock(grid.grid[xCoordinate][yy].getStock());
								}

							}
						}
					}
				}
			}
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
