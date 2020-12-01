package GameImplementation;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

public class GameGrid {
    private Grid3x3 grid[][];
    private int depth;
    private List<Pair<Pair<Integer,Integer>,Integer>> dict;
    private Stack<Pair<Pair<Integer,Integer>,Integer>> moves;
    private Helper helper;
    private Grid3x3 winnersGrid;

    public GameGrid(){
        depth=1;
        grid = new Grid3x3[3][3];
        winnersGrid = new Grid3x3(10);
        helper = new Helper();
        dict  = new ArrayList<>();
        moves = new Stack<>();
        moves.add(new Pair<>(new Pair<>(0,0),0));
        int nums=0;
        for(int i =0;i<3;i++)
            for (int j = 0; j < 3; j++) {
                nums++;
                grid[i][j] = new Grid3x3(nums);
                dict.add(new Pair<>(new Pair<>(i+1,j+1),nums));
            }
    }

    public void getDepth(int depth){
        this.depth =depth;
    }
    
    public int getNext3x3Grid(int x,int y){
        int gridID =helper.get3x3GridID(x,y);
        if(gridID==0)
            return 0;
        x=helper.getX3x3G(x,gridID);
        y=helper.getY3x3G(y,gridID);
        for(int i=0;i<dict.size();i++)
            if(dict.get(i).getFirst().getFirst() == x && dict.get(i).getFirst().getSecond() == y){
                for(int k=0;k<3;k++)
                    for(int j=0;j<3;j++)
                        if(grid[k][j].getID() == dict.get(i).getSecond())
                            if(!grid[k][j].isFinished())
                                return dict.get(i).getSecond();
            }
        return 0;
    }

    public boolean getMove(int x , int y , char player){
        int gridID = helper.get3x3GridID(x,y);
        if(helper.availableIndex(getNext3x3Grid(moves.lastElement().getFirst().getFirst(),moves.lastElement().getFirst().getSecond()),x,y)== false)
            return false;
        moves.add(new Pair<>(new Pair<>(x,y),gridID));
        x = helper.getX3x3G(x,gridID);
        y = helper.getY3x3G(y,gridID);
        for(int i=0;i<3;i++)
            for(int j=0;j<3;j++)
                if(gridID==grid[i][j].getID())
                    if(!grid[i][j].isFinished() && grid[i][j].getGrid()[x-1][y-1]==' '){
                        grid[i][j].getMove(x,y,player);
                        grid[i][j].isWin();
                        return true; }
        return false;
    }

    public List<Grid3x3> getAllPossibleMoves(Grid3x3 grid){
        List<Grid3x3> possibleNextMoves = new ArrayList<>();
        for(int i=0;i<3;i++)
            for(int j=0;j<3;j++)
                if(grid.getGrid()[i][j]==' '){
                    grid.changeSquare(i,j,'P');
                    possibleNextMoves.add(new Grid3x3(grid));
                    grid.changeSquare(i,j,' ');
                }
        return possibleNextMoves;
    }
    
    public Grid3x3 findBestMove (int nextGridID , char player){
        List<Grid3x3> movesList = getAllPossibleMoves(getSpecificGrid(nextGridID));
        int bestVal = -1000,returnValue=0;
        int depthCounter=1;
        
            for (int i = 0; i < movesList.size(); i++) {
                int miniMax = miniMax(new Grid3x3(movesList.get(i)), this.depth, false, player);
                if(miniMax>0)
                    return movesList.get(i);
            }
            
        if(bestVal<=0){
            for(int i=0;i<movesList.size();i++){
                int x =helper.getSpacificCoord(movesList.get(i)).getFirst();
                int y =helper.getSpacificCoord(movesList.get(i)).getSecond();
                if(!grid[x][y].isFinished()){
                    int priority = helper.getPriority(x,y);
                    if(priority > bestVal && !grid[x][y].isFinished()){
                        returnValue = i;
                        bestVal=priority; } } } }
        return movesList.get(returnValue);
    }
    public int miniMax(Grid3x3 grid , int depth , boolean isMax, char player){
        if(depth ==0)
            return 0;
        depth--;
        int x = helper.getSpacificCoord(grid).getFirst();
        int y = helper.getSpacificCoord(grid).getSecond();
        grid.changeSquare(x,y,player);
        int score = grid.evaluate(player);
        if(score>0)
            return score;
        grid.changeSquare(x,y,helper.otherPlayer(player));
        score = grid.evaluate(helper.otherPlayer(player));
        if(score > 0)
            return -10*score;
        if(isMax){
            int best =-1000;
            List<Grid3x3> movesList = getAllPossibleMoves(grid);
            for(int i=0;i<movesList.size();i++)
                if(!checkFinished(movesList.get(i)))
                    best = Math.max(best,miniMax(movesList.get(i),depth,!isMax,player));
            return best;
        }
        else{
            int best = 1000;
            List<Grid3x3> movesList = getAllPossibleMoves(grid);
            for(int i=0;i<movesList.size();i++)
                if(!checkFinished(movesList.get(i)))
                    best = Math.min(best,miniMax(movesList.get(i),depth,!isMax,helper.otherPlayer(player)));
            return best;
        }
    }
    
    public void print(){
        int counter=1;
        while(counter<=7){
            printRow(counter);
            if(counter!=7)
                System.out.println("------+------+-------");
            counter+=3;
        }

    }

    public void printRow(int index){
        Grid3x3 currentGrid;
        for(int j=0;j<3;j++){
            for(int i=index;i<=index+2;i++){
                int ids=i;
                currentGrid=getSpecificGrid(ids);
                currentGrid.printLine(j);
                System.out.print("|");
            }
            System.out.println();
        }
    }

    public void computerPlayer(char player){
        int gridID = getNext3x3Grid(moves.lastElement().getFirst().getFirst(),moves.lastElement().getFirst().getSecond());
        if(gridID ==0)
            gridID=evaluate(player);
        Grid3x3 bestMove = findBestMove(gridID,player);
        int x =0, y=0;
        for(int i=0;i<3;i++)
            for(int j=0;j<3;j++)
                if(gridID==grid[i][j].getID()){
                    x=helper.getNextGridID(bestMove.getGrid(),grid[i][j].getGrid()).getFirst();
                    y=helper.getNextGridID(bestMove.getGrid(),grid[i][j].getGrid()).getSecond();
                }
        getMove(helper.getXinGG(x,gridID),helper.getYinGG(y,gridID),player);
    }

    public void HumanPlayer(char player){
        Scanner scanner = new Scanner(System.in);
        int xCoord,yCoord;
        xCoord=scanner.nextInt();
        yCoord=scanner.nextInt();
        while(!getMove(xCoord,yCoord,player)){
            xCoord=scanner.nextInt();
            yCoord=scanner.nextInt();
        }
    }
 
    public void updateGameGrid(){
        for(int i=0;i<3;i++)
            for(int j=0;j<3;j++)
                if(grid[i][j].isFinished())
                    grid[i][j].winnerGrid();
    }

    public boolean isFinished(){
        updateGameGrid();
        if(winnersGrid.isFinished())
            System.out.println("The Winner is: " + winnersGrid.getWinner());
        return winnersGrid.isFinished();
    }

    public int evaluate(char player){
        for(int i=0;i<3;i++)
            for(int j=0;j<3;j++)
                winnersGrid.changeSquare(i,j,grid[i][j].getWinner());
        int i=helper.getSpacificCoord(findBestMove(10,player)).getFirst();
        int j=helper.getSpacificCoord(findBestMove(10,player)).getSecond();
        return grid[i][j].getID();

    }

    public Grid3x3 getSpecificGrid(int id){
        if(id <10 && id>0)
            for(int i=0;i<3;i++)
                for(int j=0;j<3;j++)
                    if(grid[i][j].getID()==id)
                        return grid[i][j];
        if(id == 10)
            return winnersGrid;
        return null;
    }

    public boolean checkFinished(Grid3x3 grid){
        int x = helper.getSpacificCoord(grid).getFirst();
        int y = helper.getSpacificCoord(grid).getSecond();
        return this.grid[x][y].isFinished();
    }
}
