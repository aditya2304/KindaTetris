import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;

public class PieceI1 extends TetrisBlock{

	public PieceI1(Point loc, Color color) {
		super(loc,color);
		ArrayList<Rectangle> pieces = new ArrayList<>();
		pieces.add(new Rectangle (this.getX(), this.getY() - 23 , 23, 23));
		pieces.add(new Rectangle (this.getX(), this.getY(), 23, 23));
		pieces.add(new Rectangle (this.getX(), this.getY() + 23, 23, 23));
		pieces.add(new Rectangle (this.getX(), this.getY() + 46, 23, 23));
		this.setPiece(pieces);
		
	}
	
	public TetrisBlock clone() {
		PieceI1 newPiece = new PieceI1(new Point(this.getCol(),this.getRow()), this.getColor());
		return newPiece;
	}
	
	public boolean canMoveLeft(Rectangle bounds, Rectangle[][] boxes){
		ArrayList<Rectangle> check = new ArrayList<Rectangle>(this.getPiece());
		
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
		
		ArrayList<Rectangle> check = new ArrayList<Rectangle>(this.getPiece());
		
		if(this.getPiece().size() != 4) {
			this.setCurrentPiece(false);
		}
		
		if(this.getPiece().size() == 4) {
			check.remove(0);
			check.remove(0);
			check.remove(0);
		}
		
		if(this.getPiece().size() == 3) {
			check.remove(0);
			check.remove(0);
		}
		
		if(this.getPiece().size() == 2) {
			check.remove(0);
		}
		
		if(this.getPiece().size() == 1) {
			check = new ArrayList<Rectangle>(this.getPiece());
		}
		
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
		PieceI1 updatedPiece = new PieceI1(loc, this.getColor());
		this.setPiece(updatedPiece.getPiece());
	}
}
