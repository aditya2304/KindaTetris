import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.awt.Color;
import java.awt.Graphics;

public abstract class TetrisBlock {

	private int x;
	private int y;
	private int row;
	private int col;
	private boolean placed;
	private Color color;
	private ArrayList<Rectangle> pieces;
	private ArrayList<Rectangle> original;
	private boolean isCurrentPiece;
	
	// constructor
	public TetrisBlock(Point loc, Color color) {
		this.x = (loc.x * 23) + 10;
		this.y = (loc.y * 23) + 10;
		this.row = loc.y;
		this.col = loc.x;
		this.color = color;
		placed = false;
		isCurrentPiece = true;
	}

	//getters and setters
	public boolean isCurrentPiece() {
		return isCurrentPiece;
	}

	public void setCurrentPiece(boolean isCurrentPiece) {
		this.isCurrentPiece = isCurrentPiece;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getCol() {
		return col;
	}

	public void setCol(int col) {
		this.col = col;
	}

	public boolean isPlaced() {
		return placed;
	}

	public void setPlaced(boolean placed) {
		this.placed = placed;
	}
	
	public ArrayList<Rectangle> getPiece() {
		return pieces;
	}

	public void setPiece(ArrayList<Rectangle> pieces) {
		this.pieces = pieces;
	}
	
	public ArrayList<Rectangle> getOriginal() {
		return original;
	}

	public void setOriginal(ArrayList<Rectangle> pieces) {
		this.original = pieces;
	}

	public int getX() {
		return x;
	}
	
	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}
	
	public void setY(int y) {
		this.y = y;
	}

	public void setColor(Color color) {
		this.color = color;
	}
	
	public Color getColor() {
		return color;
	}
	
	//methods
	public void draw(Graphics g) {
		for(Rectangle piece : pieces) {
			g.setColor(color);
			g.fillRect(piece.x, piece.y, piece.width, piece.height);
			g.setColor(Color.BLACK);
			g.drawRect(piece.x, piece.y, piece.width, piece.height);
		}
	}
	
	public void moveLeft() {
		for(int i = 0; i < pieces.size(); i++) {
			Rectangle piece = pieces.get(i);
			pieces.set(i, new Rectangle(piece.x - 23, piece.y, piece.width, piece.height));
		}
	}
	
	public void moveRight() {
		for(int i = 0; i < pieces.size(); i++) {
			Rectangle piece = pieces.get(i);
			pieces.set(i, new Rectangle(piece.x + 23, piece.y, piece.width, piece.height));
		}
	}
	
	public void moveDown() {
		for(int i = 0; i < pieces.size(); i++) {
			Rectangle piece = pieces.get(i);
			pieces.set(i, new Rectangle(piece.x, piece.y + 23, piece.width, piece.height));
		}
	}
	
	public abstract TetrisBlock clone();
	
	public abstract boolean canMoveRight(Rectangle bounds, Rectangle[][] boxes);
	
	public abstract boolean canMoveLeft(Rectangle bounds, Rectangle[][] boxes);
	
	public abstract boolean canMoveDown(Rectangle bounds, Rectangle[][] boxes);
	
	public abstract void updateLoc(Point loc);

}
