//import sun.awt.X11ComponentPeer;
import java.util.Scanner;

import javax.lang.model.util.ElementScanner6;

class Main{
  
    public static void printGrid(boolean[][] test, boolean [][] ships)
    {
      System.out.print("  ");
      for (int a=0; a<test.length; a++)
      {
        System.out.print(a+" ");
      }
      System.out.println();

      for (int i=0;i<test.length; i++)
      {
        System.out.print(i+" ");
        for (int j=0; j<test[i].length; j++)
        {
          if(test[i][j]==false){
            System.out.print("- ");
  
          }
          else if(test[i][j]==true && ships[i][j]==true){
            System.out.print("X ");
          }
          else if(test[i][j]==true && ships[i][j]==false){
            System.out.print("O ");
          }
        }
        System.out.println();
      }
    }

  public static boolean[][] setGrid(int rows,int cols,int[] numShips)
  {
    boolean[][] grid=new boolean[rows][cols];
    Scanner s=new Scanner(System.in);
    
    for(int i=0;i<numShips.length;i++)
    {
      System.out.println("Setting up "+(i+2)+" size ships");
      int currentShips=numShips[i];
      while(currentShips>0)
      {
        //coord
        //System.out.println("Where do you want your ship to be (enter in coordinate form example: (3,2))");
        /*String cord=s.nextLine(); 
        cord=cord.replaceAll("(","");
        cord=cord.replaceAll(")","");
        String[] nums=cord.split(",");
        //(3,2)->3,2->[3,2]*/
        //int row= Integer.parseInt(nums[1]);
        //int col= Integer.parseInt(nums[0]);

        //HW 5/4 Change so cols asked 1st
        //Changing input method back to coord (3,2)
        //two player? computer?

        System.out.println("Cols? ");
        int col=s.nextInt();
        boolean valid=true;
        System.out.println("Rows? ");
        int row=s.nextInt();
        
     
        if(rows > row && row >= 0 && cols > col && col >= 0)
        {
          if(grid[row][col]==true)
          {
            valid=false;
            System.out.print("Sorry, but you have already place a ship there, try again.");
          }
        }
        else
        {
          valid=false;
          System.out.print("Sorry, but you have placed your ship outside of the boundaries, try again.");
        }

        //direction
        if(valid)
        {
        System.out.println("Where do you want your ship to face (1=north, 2=west, 3=east, 4=south");
        int direction=s.nextInt();

        int changeC=0;
        int changeR=0;

        if(direction==1)
        {
          changeR=-1;
        }
        else if(direction==4)
        {
          changeR=1;
        }
        else if(direction==2)
        {
          changeC=-1;
        }
        else{
          changeC=1;
        }


        int currR=0;
        int currC=0;
        for(int w=0;w<(i+2);w++)
        {
          if(rows > row+currR && row+currR >= 0 && cols > col+currC && col+currC >= 0)
          {
            if(grid[row+currR][col+currC]==true)
            {
              valid=false;
              System.out.print("Sorry, but you have already place a ship there, try again.");
            }
          }
          else{
            valid=false;
          System.out.print("Sorry, but you have placed your ship outside of the boundaries, try again.");
          }
        
          
          currR+=changeR;
          currC+=changeC;
        }
        if(valid)
        {
          currR=0;
          currC=0;
          for(int w=0;w<(i+2);w++)
          {
            grid[row+currR][col+currC]=true;
            currR+=changeR;
            currC+=changeC;
          }
          currentShips-=1;
        }
         

       
        
        }
      }
    }



    return grid;
  }

  public static void main(String[] args) {

   


    boolean [][] grid=new boolean[10][10];
    boolean [][] ships=new boolean[10][10];

    boolean [][] grid2=new boolean[10][10];
    boolean [][] ships2=new boolean[10][10];

 

    Scanner s=new Scanner(System.in);
    int [] numShips=new int[4];//0,1,2,3
    //0->2
    System.out.println("How many size 2 ships do you want? ");
    numShips[0]=s.nextInt();

    System.out.println("How many size 3 ships do you want? ");
    numShips[1]=s.nextInt();

    System.out.println("How many size 4 ships do you want? ");
    numShips[2]=s.nextInt();

    System.out.println("How many size 5 ships do you want? ");
    numShips[3]=s.nextInt();
    

    int Total=2*numShips[0]+3*numShips[1]+4*numShips[2]+5*numShips[3];
    int Hits=0;
    int Hits2=0;
    System.out.println("Now setting up P1's ships...");
    ships=setGrid(10,10,numShips);
    System.out.println("Now setting up P2's ships...");
    ships2=setGrid(10,10,numShips);

    while(true)
    {

      //fire on first

      System.out.println("Enter a coloumn to place a marker. Or enter -1 to stop");
      int col=s.nextInt();


      System.out.println("Enter a row to place a marker. Or enter -1 to stop");
      int row=s.nextInt();

      printGrid(grid2,ships2);

      if (row==-1 || col==-1)
      {
        break;
      }

      if(grid2[row][col]==true)
      {
        System.out.println("You have already shot there, try again.");
      }
      else if(grid2[row][col]==false && ships2[row][col]==true){
        System.out.println("Hit");
        Hits++;
      }
      else if(grid2[row][col]==false && ships2[row][col]==false)
      {
        System.out.println("Miss");
      } 
      grid2[row][col]=true; 
      printGrid(grid2,ships2);
      if (Hits==Total)
      {
        System.out.println("Nice job, P1 won this battleship game!");
        break;
      }



      //new


      System.out.println("Enter a coloumn to place a marker. Or enter -1 to stop");
      col=s.nextInt();


      System.out.println("Enter a row to place a marker. Or enter -1 to stop");
      row=s.nextInt();

        printGrid(grid,ships);

      if (row==-1 || col==-1)
      {
        break;
      }

      if(grid[row][col]==true)
      {
        System.out.println("You have already shot there, try again.");
      }
      else if(grid[row][col]==false && ships[row][col]==true){
        System.out.println("Hit");
        Hits2++;
      }
      else if(grid[row][col]==false && ships[row][col]==false)
      {
        System.out.println("Miss");
      } 
      grid[row][col]=true; 
      printGrid(grid,ships);
      if (Hits2==Total)
      {
        System.out.println("Nice job, P2 won this battleship game!");
        break;
      }
    }
  }
}
  
