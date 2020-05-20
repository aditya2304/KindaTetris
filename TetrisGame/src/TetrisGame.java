import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.net.URL;
import java.awt.event.KeyEvent;
import java.applet.AudioClip;   
import java.util.ArrayList;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;


public class TetrisGame extends AnimationPanel 
{
	// Constants
	public static final Color[] COLORS = new Color[] { Color.blue, Color.cyan, Color.green, Color.magenta, Color.orange, Color.pink, Color.red};
	
    //Instance Variables
    private ArrayList<TetrisBlock> pieces;
    private Rectangle[][] rectangles;
    private int score;
    Rectangle testBound;
    ArrayList<TetrisBlock> startPieces;
    TetrisBlock currentPiece;
    TetrisBlock nextPiece;
    Font tetris;
    Color font;
    Color backgroundColor;
    Color pointFont;
    private StringBuilder keySequence;
    Clip theme;
    

    //Constructor
    public TetrisGame()
    {   //Enter the name and width and height.  
        super("ArcadeDemo", 640, 550);
        testBound = new Rectangle(10, 10,  230, 460);
        pieces = new ArrayList<TetrisBlock>();
        rectangles = new Rectangle[20][10];
        startPieces = new ArrayList<TetrisBlock>();
        backgroundColor = Color.WHITE;
        pointFont = Color.BLACK;
        keySequence = new StringBuilder();
        
        startPieces.add(new PieceI(new Point(5, 1), Color.RED));
        startPieces.add(new PieceI1(new Point(5, 1), Color.RED));
        startPieces.add(new PieceO(new Point(5, 1), Color.RED));
        startPieces.add(new PieceJ(new Point(5, 1), Color.RED));
        startPieces.add(new PieceJ1(new Point(5, 1), Color.RED));
        startPieces.add(new PieceJ2(new Point(5, 1), Color.RED));
        startPieces.add(new PieceJ3(new Point(5, 1), Color.RED));
        startPieces.add(new PieceS(new Point(5, 1), Color.RED));
        startPieces.add(new PieceS1(new Point(5, 1), Color.RED));
        startPieces.add(new PieceT(new Point(5, 1), Color.RED));
        startPieces.add(new PieceT1(new Point(5, 1), Color.RED));
        startPieces.add(new PieceT2(new Point(5, 1), Color.RED));
        startPieces.add(new PieceT3(new Point(5, 1), Color.RED));
        startPieces.add(new PieceZ(new Point(5, 1), Color.RED));
        startPieces.add(new PieceZ1(new Point(5, 1), Color.RED));
        startPieces.add(new PieceL(new Point(5, 1), Color.RED));
        startPieces.add(new PieceL1(new Point(5, 1), Color.RED));
        startPieces.add(new PieceL2(new Point(5, 1), Color.RED));
        startPieces.add(new PieceL3(new Point(5, 1), Color.RED));
        
        currentPiece = startPieces.get((int)(Math.random() * startPieces.size())).clone();
        nextPiece = startPieces.get((int)(Math.random() * startPieces.size())).clone();
        nextPiece.updateLoc(new Point(17, 8));
        nextPiece.setColor(COLORS[(int)(Math.random() * COLORS.length)]);
        
        pieces.add(currentPiece);
        
        try {
        	InputStream thing = getClass().getResourceAsStream("/Tetris.ttf");
			tetris = Font.createFont(Font.TRUETYPE_FONT, thing).deriveFont(40.0f);
			GraphicsEnvironment g = GraphicsEnvironment.getLocalGraphicsEnvironment();
			g.registerFont(tetris);
			
			theme = loadAudioClip(getClass().getClassLoader().getResource("TetrisTheme.wav"), 0.0f);
		} catch (FontFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedAudioFileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
       
    //The renderFrame method is the one which is called each time a frame is drawn.
    protected Graphics renderFrame(Graphics g) 
    {	
    	g.setColor(Color.LIGHT_GRAY);
    	g.fillRect(0, 0, 640, 550);
    	
    	theme.loop(Clip.LOOP_CONTINUOUSLY);
    	g.setColor(Color.BLACK);
    	g.fillRect(255, 160, 105, 70);
    	g.setColor(Color.WHITE);
    	g.fillRect(260, 165, 95, 60);
    
    	g.setFont(new Font("SansSerif", Font.PLAIN, 30));
    	g.setColor(Color.BLACK);
    	g.drawString("Next", 270, 190);
    	g.drawString("Piece:", 265, 220);
    	
    	if(frameNumber < 600) {
    		g.drawString("Use a,s,d for moving", 265, 300);
    		g.drawString("Use spacebar to reset", 265, 330);
    	}
    			
    	if(frameNumber %50 == 0) {
    		for(TetrisBlock piece : pieces) {
    			if(piece.canMoveDown(testBound, rectangles))
    			piece.moveDown();
    		}
    		font = (COLORS[(int)(Math.random() * COLORS.length)]);
    	}
    	
    	g.setColor(font);
    	g.setFont(tetris.deriveFont(100.0f));
    	g.drawString("Tetris", 260 , 90);
    	
    	int counter = 0;
    	
    	for(TetrisBlock piece : pieces) {
			if(piece.canMoveDown(testBound, rectangles) && piece.isPlaced())
				piece.moveDown();
		}
    	
    	if(!currentPiece.isCurrentPiece()) {
    		currentPiece = nextPiece.clone();
    		currentPiece.setColor(nextPiece.getColor());
    		nextPiece = startPieces.get((int)(Math.random() * startPieces.size())).clone();
            nextPiece.updateLoc(new Point(17, 8));
            nextPiece.setColor(COLORS[(int)(Math.random() * COLORS.length)]);
    		currentPiece.setCol(1);
    		currentPiece.setRow(5);
    		pieces.add(currentPiece);
    	}
    	
		rectangles = new Rectangle[20][10];
    	
        //Tetris game background
        g.setColor(Color.BLACK);
        g.fillRect(9,9,232,462);
        g.setColor(backgroundColor);
        g.fillRect(10,10,230,460);
        
        //points
        g.setFont(new Font("SansSerif", Font.PLAIN, 30));
    	g.setColor(pointFont);
    	g.drawString(Integer.toString(score), 12, 35);
        
        for(TetrisBlock piece : pieces) {
         	ArrayList<Rectangle> blocks = piece.getPiece();
         	for(int i = 0; i < blocks.size(); i++) {
         		rectangles[(blocks.get(i).y - 10)/23][(blocks.get(i).x - 10)/23] = blocks.get(i);
         	}
         	if(!piece.canMoveDown(testBound, rectangles)) {
         		piece.setPlaced(true);
         	}
         	if(piece.isPlaced()) {
         		piece.setCurrentPiece(false);
         	}
         }
        
        for(int b = 0; b < rectangles.length; b ++) {
        	counter = 0;
    		for(int j = 0; j < rectangles[0].length; j++) {
    			if(rectangles[b][j] != null) {
    				counter++;
    			}
    			else {
    				counter = 0;
    			}
    			if(counter == rectangles[0].length) {
    				score += 100;
    				for(int a = 0; a < rectangles[0].length; a++) {
    		    		rectangles[b][a] = null;
    		    		System.out.println("true:");
    		    		System.out.println(rectangles[b][a]);
    				}
    			}
    		}
    	}
        
        for(TetrisBlock piece : pieces) {
        	ArrayList<Rectangle> blocks = new ArrayList<Rectangle>(piece.getPiece());
        	
        	for(int i = 0; i < blocks.size(); i++) {
        		if(rectangles[(blocks.get(i).y - 10)/23][(blocks.get(i).x - 10)/23] == null) {
        			//System.out.println("true");
        			//System.out.println("before" + piece.getPiece());
        			piece.getPiece().remove(blocks.get(i));
        			//System.out.println("after" + piece.getPiece());
        		}	
        	}
         	piece.draw(g);
        }
        
        nextPiece.draw(g);
        
        return g;
    }
    
    //Respond to Mouse Events
    public void mouseClicked(MouseEvent e)  
    { 
        //Display what is in the block 2d array
    	for (int i = 0; i < rectangles.length; i++) {
            for (int j = 0; j < rectangles[0].length; j++) {
                System.out.println(rectangles[i][j] + " Loc: (" + i + "," + j + ")");
            }
            System.out.println();
        }
    }
    
    //Respond to Keyboard Events
    public void keyTyped(KeyEvent e) 
    {
        char c = e.getKeyChar();
        
        for(TetrisBlock piece : pieces) {
        	if(c == 'a' && piece.canMoveLeft(testBound, rectangles) && piece.isCurrentPiece())
        		piece.moveLeft();
        
        	if(c == 'd' && piece.canMoveRight(testBound, rectangles) && piece.isCurrentPiece())
        		piece.moveRight();
        
        	if(c == 's' && piece.canMoveDown(testBound, rectangles) && piece.isCurrentPiece())
        		piece.moveDown();
        	
        	
        	if(c == ' ') {
        		pieces = new ArrayList<TetrisBlock>();
        		currentPiece = startPieces.get((int)(Math.random() * startPieces.size())).clone();
        		pieces.add(currentPiece);
        		score = 0;
        	}
			
        	// DarkBackground EASTER_EGG
        	if (c == 'A') {
    			keySequence = new StringBuilder("A");
    		}
    		else {
    			keySequence.append(c);
    		}
        	
        	if (keySequence.toString().equals("Aditya")) {
    			keySequence.setLength(0);
    			backgroundColor = Color.black;
    			pointFont = Color.WHITE;
    		}
        }    
    }
    
    public void keyPressed(KeyEvent e)
    {
        int v = e.getKeyCode();
    }

    public void keyReleased(KeyEvent e)
    {
        int v = e.getKeyCode();
    }
    
    // audio clips
	
	private static Clip loadAudioClip(URL file, float gain)
			throws UnsupportedAudioFileException, IOException, LineUnavailableException {
		AudioInputStream ais = AudioSystem.getAudioInputStream(file);
		Clip clip = AudioSystem.getClip();
		clip.open(ais);
		FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
		gainControl.setValue(gain);
		return clip;
	}
	
	
}

