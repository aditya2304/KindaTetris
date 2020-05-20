import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;

public class PieceI extends TetrisBlock{

	public PieceI(Point loc, Color color) {
		super(loc,color);
		ArrayList<Rectangle> pieces = new ArrayList<>();
		pieces.add(new Rectangle (this.getX() - 23, this.getY(), 23, 23));
		pieces.add(new Rectangle (this.getX(), this.getY(), 23, 23));
		pieces.add(new Rectangle (this.getX() + 23, this.getY(), 23, 23));
		pieces.add(new Rectangle (this.getX() + (23*2), this.getY(), 23, 23));
		this.setPiece(pieces);
		
	}
	
	public TetrisBlock clone() {
		PieceI newPiece = new PieceI(new Point(this.getCol(),this.getRow()), this.getColor());
		return newPiece;
	}
	
	public boolean canMoveLeft(Rectangle bounds, Rectangle[][] boxes){
		ArrayList<Rectangle> check = new ArrayList<Rectangle>(this.getPiece());
		
		if(this.getPiece().size() == 4) {
			check.remove(3);
			check.remove(2);
			check.remove(1);
		}
		
		for(Rectangle piece : check) {
			if(piece.x - 23 < bounds.x) {
				return false;
			}
			if(boxes[(piece.y - 10) / 23][((piece.x - 10) / 23) - 1] != null) {
				return false;
			}	
		}
		return true;
	}
	
	public boolean canMoveRight(Rectangle bounds, Rectangle[][] boxes){
		ArrayList<Rectangle> check = new ArrayList<Rectangle>(this.getPiece());
		
		if(this.getPiece().size() == 4) {
			check.remove(0);
			check.remove(0);
			check.remove(0);
		}
		
		for(Rectangle piece : check) {
			if(piece.x + 46 > bounds.width + bounds.x) {
				return false;
			}
			if(boxes[(piece.y - 10) / 23][((piece.x - 10) / 23) + 1] != null) {
				return false;
			}
		}
		return true;
	}
	
	
	public boolean canMoveDown(Rectangle bounds, Rectangle[][] boxes){
		
		if(this.getPiece().size() != 4) {
			this.setCurrentPiece(false);
		}
		
		ArrayList<Rectangle> check = this.getPiece();
		for(Rectangle piece : check) {
			if(piece.y + 46 > bounds.height + bounds.y) {
				return false;
			}
			if(boxes[((piece.y - 10) / 23) + 1][((piece.x - 10) / 23)] != null) {
				return false;
			}
		}
		return true;
	}
	
	public void updateLoc(Point loc) {
		PieceI updatedPiece = new PieceI(loc, this.getColor());
		this.setPiece(updatedPiece.getPiece());
	}
}
