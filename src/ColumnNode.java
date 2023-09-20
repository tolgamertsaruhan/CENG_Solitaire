public class ColumnNode {
	private String columnName;
	private ColumnNode right;
	private NumberNode down;
	
	public ColumnNode(String dataToAdd) {
		columnName=dataToAdd;
		down=null;
		right=null;
	}

	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	public ColumnNode getRight() {
		return right;
	}

	public void setRight(ColumnNode right) {
		this.right = right;
	}

	public NumberNode getDown() {
		return down;
	}

	public void setDown(NumberNode down) {
		this.down = down;
	}
	
	
}
