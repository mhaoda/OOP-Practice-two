/*
 * This is the vertical transporter, which transport foods from the farmer to the animal in the same row.
 */
public class VerticalTransporter extends AbstractItem {
	int stock, num;

	public VerticalTransporter(Grid grid, int xCoordinate, int yCoordinate, int num) {
		super(grid, xCoordinate, yCoordinate);
		this.num = num;
		grid.registerItem(xCoordinate, yCoordinate, this);
	}

	public int getNum() {
		return num;
	}

	@Override
	public String toString() {
		return "VT";
	}

	@Override
	public void process(TimeStep timeStep) {
		for (int x = 0; x < xCoordinate; x++) {
			if (grid.grid[x][yCoordinate] != null) {
				// if there are farmers on the top of the VT.
				if (grid.grid[x][yCoordinate].getClass().equals(CornFarmer.class)
						|| grid.grid[x][yCoordinate].getClass().equals(RadishFarmer.class)) {
					for (int xx = xCoordinate; xx < grid.grid.length; xx++) {
						if (grid.grid[xx][yCoordinate] != null) {
							//if there are animals under the VT.
							if (grid.grid[xx][yCoordinate].getClass().equals(Beaver.class)
									|| grid.grid[xx][yCoordinate].getClass().equals(Rabbit.class)) {
								// if the stock of the farmer is enough for HT to carry.
								if (grid.grid[x][yCoordinate].getStock() >= getNum()) {
									addToStock(getNum());
									grid.grid[x][yCoordinate].reduceStock(getNum());
								} else {
									addToStock(grid.grid[x][yCoordinate].getStock());
									grid.grid[x][yCoordinate].reduceStock(grid.grid[x][yCoordinate].getStock());
								}

							}
						}
					}
				}
			}
		}

		for (int x = 0; x < xCoordinate; x++) {
			if (grid.grid[x][yCoordinate] != null) {
				// if there are animals on the top of the VT.
				if (grid.grid[x][yCoordinate].getClass().equals(Beaver.class)
						|| grid.grid[x][yCoordinate].getClass().equals(Rabbit.class)) {
					for (int xx = xCoordinate; xx < grid.grid.length; xx++) {
						if (grid.grid[xx][yCoordinate] != null) {
							// if there are animals under the VT.
							if (grid.grid[xx][yCoordinate].getClass().equals(CornFarmer.class)
									|| grid.grid[xx][yCoordinate].getClass().equals(RadishFarmer.class)) {
								// if the stock of the farmer is enough for HT to carry.
								if (grid.grid[xx][yCoordinate].getStock() >= getNum()) {
									addToStock(getNum());
									grid.grid[xx][yCoordinate].reduceStock(getNum());
								} else {
									addToStock(grid.grid[xx][yCoordinate].getStock());
									grid.grid[xx][yCoordinate].reduceStock(grid.grid[xx][yCoordinate].getStock());
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
