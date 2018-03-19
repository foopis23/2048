package foopis.game;

import java.awt.event.*;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;

public class Game implements KeyListener, MouseListener
{
    private Board board;

    private Game()
    {
        JFrame frame = new JFrame("2048");
        board = new Board(this);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.getContentPane().add(board);
        frame.pack();
    	startLoop();
    }

    public static void main(String[] args)
    {
        new Game();
    }

    public void keyTyped(KeyEvent e)
    {

    }

    public void keyPressed(KeyEvent e)
    {

    }

    public void keyReleased(KeyEvent e)
    {
        int k = e.getKeyCode();

        if(k==KeyEvent.VK_W)
        {
        	board.moveTiles(Board.UP);
        	board.repaint();
        }

        if(k==KeyEvent.VK_A)
        {
        	board.moveTiles(Board.LEFT);
        	board.repaint();
        }

        if(k==KeyEvent.VK_S)
        {
        	board.moveTiles(Board.DOWN);
        	board.repaint();
        }

        if(k==KeyEvent.VK_D)
        {
        	board.moveTiles(Board.RIGHT);
        	board.repaint();
        }
    }

	@Override
	public void mouseClicked(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}

	@Override
	public void mousePressed(MouseEvent e) {}

	@Override
	public void mouseReleased(MouseEvent e)
	{
		if(board.pressedReset())
		{
			board.clearBoard();
		}
	}
	
	public void startLoop()
	{
		Timer timer = new Timer();
	    timer.schedule(new Loop(), 0l, (long)1000 / 24);
	}
	
	class Loop extends TimerTask
	{
		public void run()
		{
			board.repaint();
			board.requestFocus();
		}
	}
}
