package foopis.game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.LinkedList;
import java.util.Random;

import javax.swing.JPanel;

public class Board extends JPanel
{
	private static final long serialVersionUID = 1L;
	public static final int UP = 1;
    public static final int LEFT = 2;
    public static final int DOWN = 3;
    public static final int RIGHT = 4;
    public static final double TILE_RATIO = 500d/76d;
    public static final double PADDING_RATIO = 500d/20d;
    
    private int[][] tiles;
    private int[] reset = new int[5];
    private int score;
    private boolean gameOver;

    Board(Game game)
    {
    	this.addKeyListener(game);
    	this.addMouseListener(game);
        this.setPreferredSize(new Dimension(500,500));
        tiles = new int[4][4];
        addNewTile();
        addNewTile();
        score = 0;
        gameOver=false;
    }
    
    void clearBoard()
    {
        tiles = new int[4][4];
        addNewTile();
        addNewTile();
        score = 0;
        gameOver=false;
    }

    void moveTiles(int direction)
    {
    	boolean tileMoved = false;
        if(direction==UP)
        {
            for(int col=1;col<4;col++)
            {
                for(int row=0;row<4;row++)
                {
                    int cc = col;
                    int cr = row;
                    while(true)
                    {
                        int cv = tiles[cc][cr];
                        if(cc<1||cv==0)
                        {
                            break;
                        }else if(tiles[cc-1][cr]==0)
                        {
                            tiles[cc][cr]=0;
                            cc--;
                            tiles[cc][cr]+=cv;
                            tileMoved=true;
                        }else if(cv==tiles[cc-1][cr])
                        {
                            tiles[cc][cr]=0;
                            cc--;
                            tiles[cc][cr]+=cv;
                            tileMoved=true;
                            score+=(cv*2);
                            break;
                        }else{
                            break;
                        }
                    }
                }
            }
        }

        if(direction==LEFT)
        {
            for(int row=1;row<4;row++)
            {
                for(int col=0;col<4;col++)
                {
                    int cc=col;
                    int cr=row;
                    while(true)
                    {
                        int cv = tiles[cc][cr];
                        if(cr<1||cv==0)
                        {
                            break;
                        }else if(tiles[cc][cr-1]==0)
                        {
                            tiles[cc][cr]=0;
                            cr--;
                            tiles[cc][cr]+=cv;
                            tileMoved=true;
                        }else if(cv==tiles[cc][cr-1])
                        {
                            tiles[cc][cr]=0;
                            cr--;
                            tiles[cc][cr]+=cv;
                            tileMoved=true;
                            score+=(cv*2);
                            break;
                        }else{
                            break;
                        }
                    }
                }
            }
        }

        if(direction==DOWN)
        {
            for(int col=3;col>-1;col--)
            {
                for(int row=0;row<4;row++)
                {
                    int cc = col;
                    int cr = row;
                    while(true)
                    {
                        int cv = tiles[cc][cr];
                        if(cc>2||cv==0)
                        {
                            break;
                        }else if(tiles[cc+1][cr]==0)
                        {
                            tiles[cc][cr]=0;
                            cc++;
                            tiles[cc][cr]+=cv;
                            tileMoved=true;
                        }else if(cv==tiles[cc+1][cr])
                        {
                            tiles[cc][cr]=0;
                            cc++;
                            tiles[cc][cr]+=cv;
                            tileMoved=true;
                            score+=(cv*2);
                            break;
                        }else{
                            break;
                        }
                    }
                }
            }
        }

        if(direction==RIGHT)
        {
            for(int row=3;row>-1;row--)
            {
                for(int col=0;col<4;col++)
                {
                    int cc=col;
                    int cr=row;
                    while(true)
                    {
                        int cv = tiles[cc][cr];
                        if(cr>2||cv==0)
                        {
                            break;
                        }else if(tiles[cc][cr+1]==0)
                        {
                            tiles[cc][cr]=0;
                            cr++;
                            tiles[cc][cr]+=cv;
                            tileMoved=true;
                        }else if(cv==tiles[cc][cr+1])
                        {
                            tiles[cc][cr]=0;
                            cr++;
                            tiles[cc][cr]+=cv;
                            tileMoved=true;
                            score+=(cv*2);
                            break;
                        }else{
                            break;
                        }
                    }
                }
            }
        }
        
        if(tileMoved)
        	addNewTile();
    }
    
    private boolean canMove()
    {
        for(int col=1;col<4;col++)
        {//up
            for(int row=0;row<4;row++)
            {
                int cc = col;
                int cr = row;
                while(true)
                {
                    int cv = tiles[cc][cr];
                    if(cc<1||cv==0)
                    {
                        break;
                    }else if(tiles[cc-1][cr]==0)
                    {
                        return true;
                    }else if(cv==tiles[cc-1][cr])
                    {
                        return true;
                    }else{
                        break;
                    }
                }
            }
        }
        
        for(int row=1;row<4;row++)
        {//left
            for(int col=0;col<4;col++)
            {
                int cc=col;
                int cr=row;
                while(true)
                {
                    int cv = tiles[cc][cr];
                    if(cr<1||cv==0)
                    {
                        break;
                    }else if(tiles[cc][cr-1]==0)
                    {
                        return true;
                    }else if(cv==tiles[cc][cr-1])
                    {
                    	return true;
                    }else{
                        break;
                    }
                }
            }
        }
    	
        for(int col=3;col>-1;col--)
        {//down
            for(int row=0;row<4;row++)
            {
                int cc = col;
                int cr = row;
                while(true)
                {
                    int cv = tiles[cc][cr];
                    if(cc>2||cv==0)
                    {
                        break;
                    }else if(tiles[cc+1][cr]==0)
                    {
                        return true;
                    }else if(cv==tiles[cc+1][cr])
                    {
                    	return true;
                    }else{
                        break;
                    }
                }
            }
        }
        
        for(int row=3;row>-1;row--)
        {//right
            for(int col=0;col<4;col++)
            {
                int cc=col;
                int cr=row;
                while(true)
                {
                    int cv = tiles[cc][cr];
                    if(cr>2||cv==0)
                    {
                        break;
                    }else if(tiles[cc][cr+1]==0)
                    {
                        return true;
                    }else if(cv==tiles[cc][cr+1])
                    {
                        return true;
                    }else{
                        break;
                    }
                }
            }
        }
    	
    	return false;
    }
    
    private void addNewTile()
    {
    	Random random = new Random(System.currentTimeMillis());
    	int value = random.nextInt(4);
    	if(value!=4)
    	{
    		value=2;
    	}
    	
    	LinkedList<int[]> a = new LinkedList<>();
    	
        for(int col=0;col<tiles.length;col++)
        {
        	int[] r = tiles[col];
        	for(int row=0;row<r.length;row++)
        	{
        		if(tiles[col][row]==0)
        		{
        			a.add(new int[] {col,row});
        		}
        	}
        }
        	
    	if(a.size()>1)
    	{
    		int[] point = a.get(random.nextInt(a.size()-1));
    		tiles[point[0]][point[1]]=value;
    	}else if(a.size()==1){
    		int[] point = a.get(0);
    		tiles[point[0]][point[1]]=value;
    		if(!canMove())
    		{
    			gameOver=true;
    		}
    	}
    }

    private int getResetState(Rectangle r)
    {
    	Point m = this.getMousePosition();
    	if(m!=null)
    	{
    		Rectangle r2 = new Rectangle((int)m.getX(),(int)m.getY(),1,1);
    		if(r2.intersects(r))
    		{
    			return 1;
    		}else {
    			return 0;
    		}
    	}else {
    		return 0;
    	}
    }

    boolean pressedReset()
    {
    	if(getResetState(new Rectangle(reset[0],reset[1],reset[2],reset[3]))==1)
    	{
    		return true;
    	}else {
    		return false;
    	}
    }
    
    private Color getColor(int v)
    {
        Random random = new Random(1590*v);
        
        return new Color(random.nextInt(254)+1,random.nextInt(254)+1,random.nextInt(254)+1);
    }

    @Override
    public void paint(Graphics g)
    {
    	super.paint(g);
        int padding = 20;
        int tileSize = 100;
        int offsetX = 0;
        int offsetY = 0;
        int menuOffsetX = 0;
        int menuOffsetY = 0;
        
        if(this.getWidth()>this.getHeight())
        {
            padding = (int)(this.getHeight()/PADDING_RATIO);
            tileSize = (int)(this.getHeight()/TILE_RATIO);
            offsetX = (int)(((this.getWidth()-this.getHeight())/2));
        }else{
            padding = (int)(this.getWidth()/PADDING_RATIO);
            tileSize = (int)(this.getWidth()/TILE_RATIO);
            offsetY = (int)((this.getHeight()-this.getWidth())/2);
        }
        
        menuOffsetY = tileSize+padding;
        menuOffsetX = menuOffsetY/2;
        
        int textMax = (int)(tileSize*.85);
        int fontSize = 1;
        while(true)
        {
        	g.setFont(new Font("Courier",Font.TRUETYPE_FONT,fontSize));
        	FontMetrics metrics = g.getFontMetrics();
        	if(metrics.stringWidth("2048")>textMax)
        	{
        		fontSize--;
            	g.setFont(new Font("Courier",Font.TRUETYPE_FONT,fontSize));
            	break;
        	}
        	fontSize++;
        }
    	FontMetrics metrics = g.getFontMetrics();
        
        g.setColor(Color.GRAY);
        for(int x=0;x<4;x++)
        {
            for(int y=0;y<4;y++)
            {
                int renderX =((x+1)*padding)+(x*tileSize) + offsetX + menuOffsetX;
                int renderY = ((y+1)*padding)+(y*tileSize) + offsetY + menuOffsetY;
                g.fillRect(renderX,renderY,tileSize,tileSize);
            }
        }

        for(int x=0;x<4;x++)
        {
            for(int y=0;y<4;y++)
            {
                if(tiles[y][x]>0)
                {
                    g.setColor(getColor(tiles[y][x]));
                    int renderX =((x+1)*padding)+(x*tileSize) + offsetX + menuOffsetX;
                    int renderY = ((y+1)*padding)+(y*tileSize) + offsetY +menuOffsetY;
                    g.fillRect(renderX,renderY,tileSize,tileSize);
                    int textWidth = metrics.stringWidth(Integer.toString(tiles[y][x]));
                    int textYOffset = ((tileSize+padding)/2);
                    int textXOffset = (tileSize - textWidth)/2;
                    g.setColor(g.getColor().darker().darker());
                    g.drawString(Integer.toString(tiles[y][x]), (renderX+textXOffset), (renderY+textYOffset));
                }
            }
        }
         
        if(gameOver)
        {
        	g.setColor(new Color(45,45,45,175));
        	g.fillRect(0, 0, this.getWidth(), this.getHeight());
        	g.setColor(Color.WHITE);
        	int renderX = (this.getWidth()-metrics.stringWidth("Game Over!"))/2;
        	int renderY = (this.getHeight()-metrics.getHeight())/2;
        	g.drawString("Game Over!", renderX, renderY);
        }
        
        reset[0] = menuOffsetX+offsetX+padding;
        reset[1] = padding+offsetY;
        reset[2] = tileSize*2+padding;
        reset[3] = tileSize;
        reset[4] = getResetState(new Rectangle(reset[0],reset[1],reset[2],reset[3]));
        int resetWidth = metrics.stringWidth("Reset");
        int resetYOffset = (reset[3]+padding)/2;
        int resetXOffset = (reset[2]-resetWidth)/2;
        if(reset[4]==0)
        {
            g.setColor(Color.GRAY);
        	g.drawRect(reset[0],reset[1],reset[2], reset[3]);
            g.drawString("Reset", reset[0]+resetXOffset, reset[1]+resetYOffset);
        }
        
        if(reset[4]==1)
        {
            g.setColor(Color.GRAY);
        	g.fillRect(reset[0],reset[1],reset[2], reset[3]);
        	g.setColor(Color.WHITE);
            g.drawString("Reset", reset[0]+resetXOffset, reset[1]+resetYOffset);
        }
        
        
        int scoreWidth = metrics.stringWidth(Integer.toString(score));
        int scoreYOffset = (reset[3]+padding)/2;
        int scoreXOffset = (reset[2]-scoreWidth)/2;
        g.setColor(Color.GRAY);
    	g.drawRect(menuOffsetX+offsetX+(3*padding)+(2*tileSize),padding+offsetY,tileSize*2+padding, tileSize);
        g.drawString(Integer.toString(score), (menuOffsetX+offsetX+(3*padding)+(2*tileSize))+scoreXOffset, reset[1]+scoreYOffset);
    }
}