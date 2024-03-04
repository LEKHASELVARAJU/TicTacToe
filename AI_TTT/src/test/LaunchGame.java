package test;
import java.util.Scanner;
import java.util.Random;
class TicTacToe
{
	 static char[][] board;
	  public TicTacToe()
	  {
		  board=new char[3][3];
		  assign();
	  }
	   void assign()
	 {
		 for(int i=0;i<3;i++)
		 {
			 for(int j=0;j<3;j++)
			 {
				board[i][j]=' ';	
			 }
		 }
	 }
	 static void placeMark(int row,int col,char mark) {
		 if(row>=0&&row<=2&&col>=0&&col<=2)
		 {
		 board[row][col]=mark;
		 }
		 else
		 {
			 System.out.print("Invalid Input");
		 }
	 }
	
	 static boolean checkRowWin() {
		 for(int i=0;i<3;i++)
		 {
			 if(board[i][0]!=' '&&board[i][0]==board[i][1]&&board[i][1]==board[i][2])
			 {
				 return true;
			 }
		 }
		 return false;
	 }
	 static boolean checkDiagWin() {
			 if(board[0][0]!=' '&&board[0][0]==board[1][1]&&board[1][1]==board[2][2]||
			 board[0][2]!=' '&&board[0][2]==board[1][1]&&board[1][1]==board[2][0] )
			 {
				 return true;
			 }
			 else
			 {
		         return false;
			 }
	 }
	 
	static  boolean checkColWin() {
		 for(int j=0;j<3;j++)
		 {
			 if(board[0][j]!=' '&&board[0][j]==board[1][j]&&board[1][j]==board[2][j])
			 {
				 return true;
			 }
		 }
		 return false;
	 }
	static boolean end()
	{
		for(int i=0;i<3;i++)
		{
			for(int j=0;j<3;j++)
			{
				if(board[i][j]==' ')
				{
					return true;
				}
			}
		}
		return false;
	}
	 public static void dis()
	 {
		 System.out.println("-------------");
		 for(int i=0;i<3;i++)
		 {
			 System.out.print("| ");
			 for(int j=0;j<3;j++)
			 {
			  System.out.print(board[i][j]+" | ");
			 }
			 System.out.println();
			 System.out.println("-------------");
		 }
	 }
}
abstract class Player{
	String name;
	char mark;
	abstract void makeMove();
	boolean isValidMove(int row,int col) {
		 if(row>=0&&row<=2&&col>=0&&col<=2)
		 {
		    if(TicTacToe.board[row][col]==' ')
		    {
		    	return true;
		    }
		 }
		 return false;
	}
	
}
class Human extends Player{
	
	public Human(String name,char mark)
	{
		this.name=name;
		this.mark=mark;
	}
    void makeMove()
	{
		Scanner sc=new Scanner(System.in);
		int row,col;
		do
		{
			System.out.println("Enter row and col");
			row=sc.nextInt();
			col=sc.nextInt();
		}while(!isValidMove(row,col));
		TicTacToe.placeMark(row,col,mark);
		
	}


}
class AI extends Player{
	
	public AI(String name,char mark)
	{
		this.name=name;
		this.mark=mark;
	}
    void makeMove()
	{
		int row,col;
		do
		{
			System.out.println("Enter row and col");
			Random r=new Random();
			row=r.nextInt(3);
			col=r.nextInt(3);
		}while(!isValidMove(row,col));
		TicTacToe.placeMark(row,col,mark);
		
	}
}
public class LaunchGame {

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		TicTacToe t=new TicTacToe();
		System.out.print("Enter both player's name");
	    String s1=sc.nextLine();
	    String s2=sc.nextLine();
		Human p1=new Human(s1,'X');
		AI p2=new AI(s2,'O');
		Player cp=p1;
		while(true)
		{
			System.out.println(cp.name+" turn");
			cp.makeMove();
			TicTacToe.dis();
			if(TicTacToe.checkColWin()||TicTacToe.checkRowWin()||
					TicTacToe.checkDiagWin())
			{
				System.out.print(cp.name+" has won :)");
				break;
			}
			else if(!TicTacToe.end())
			{
				System.out.print("No one wins\nGAME OVER!!");
				break;
			}
			else
			{
				if(cp==p1)
				{
					cp=p2;
				}
				else
				{
					cp=p1;
				}
			}
			
		}

	}

}
