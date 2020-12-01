package GameImplementation;

public class Grid3x3 {
    private char[][] grid;
    private int gridId;
    private char winner;
    Grid3x3(int id){
        grid = new char[3][3];
        fillGrid();
        this.gridId = id;
        winner = ' ';
    }
    Grid3x3 (Grid3x3 grid3x3){
        grid = new char[3][3];
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                grid[i][j]=grid3x3.getGrid()[i][j];
            }
        }
    }
    public char getWinner(){
        return winner;
    }
    public int getID(){
        return gridId;
    }
    public boolean getMove(int x , int y , char player){
        if(grid[x-1][y-1]==' '){
            grid[x-1][y-1]=player;
            return true;
        }
        return false;
    }
    public char[][] getGrid(){
        return grid;
    }
    public void change3x3Grid(char[][] lilGrid){
        this.grid = lilGrid;
    }
    public void changeSquare(int i, int j, char player){
        grid[i][j]=player;
    }

    public int evaluate(char player){
    	int pWin = 0;
    	int opWin = 0;
        for(int i=0;i<3;i++){//check rows
        	int cnt1 = 0;
        	int cnt2 = 0;
        	char c = '\0';
        	for(int j = 0;j < 3;j++)
        		if(grid[i][j] == ' ')
        			cnt2++;
        		else
        			if(c == '\0') {
        				c = grid[i][j];
        				cnt1++;
        			}
        			else
        				if(grid[i][j] == c)
        					cnt1++;
    		if((cnt1 == 2 && cnt2 == 1) || cnt1 == 3)
    			if(c == player)
    				pWin++;
    			else
    				opWin++;
        }
        for(int j=0;j<3;j++){//check columns
        	int cnt1 = 0;
        	int cnt2 = 0;
        	char c = '\0';
        	for(int i = 0;i < 3;i++)
        		if(grid[i][j] == ' ')
        			cnt2++;
        		else
        			if(c == '\0') {
        				c = grid[i][j];
        				cnt1++;
        			}
        			else
        				if(grid[i][j] == c)
        					cnt1++;
    		if((cnt1 == 2 && cnt2 == 1) || cnt1 == 3)
    			if(c == player)
    				pWin++;
    			else
    				opWin++;
        }
        {
        	int cnt1 = 0;
        	int cnt2 = 0;
        	char c = '\0';
        	for(int j = 0;j < 3;j++)//check main diagonal
        		if(grid[j][j] == ' ')
        			cnt2++;
        		else
        			if(c == '\0') {
        				c = grid[j][j];
        				cnt1++;
        			}
        			else
        				if(grid[j][j] == c)
        					cnt1++;
    		if((cnt1 == 2 && cnt2 == 1) || cnt1 == 3)
    			if(c == player)
    				pWin++;
    			else
    				opWin++;
        }
        {
        	int cnt1 = 0;
        	int cnt2 = 0;
        	char c = '\0';
        	for(int j = 0;j < 3;j++)//check secondary diagonal
        		if(grid[j][3 - j - 1] == ' ')
        			cnt2++;
        		else
        			if(c == '\0') {
        				c = grid[j][3 - j - 1];
        				cnt1++;
        			}
        			else
        				if(grid[j][3 - j - 1] == c)
        					cnt1++;
    		if((cnt1 == 2 && cnt2 == 1) || cnt1 == 3)
    			if(c == player)
    				pWin++;
    			else
    				opWin++;
        }
        if(pWin >= 1)
        	return 10;
        if(opWin >= 2)
        	return -10;
        return 0;
    }
    
    public boolean isWin(){
        for(int i=0;i<3;i++){//check rows
            if(grid[i][0]==grid[i][1]&& grid[i][1]==grid[i][2] && grid[i][1]!=' '){
                winner=grid[i][0];
                return true;
            }
        }
        for(int i=0;i<3;i++){//check columns
            if(grid[0][i]==grid[1][i]&& grid[1][i]==grid[2][i] && grid[2][i]!=' '){
                winner=grid[0][i];
                return true;
            }
        }
        //check main diagonal
        if(grid[0][0]==grid[1][1]&& grid[1][1]==grid[2][2]&&grid[1][1]!=' '){
            winner=grid[0][0];
            return true;
        }
        //check secondary diagonal
        if(grid[0][2]==grid[1][1]&& grid[1][1]==grid[2][0]&&grid[1][1]!=' '){
            winner=grid[2][0];
            return true;
        }
        return false;
    }
    
    public boolean isFinished(){
    	if(isWin())
            return true;
    	//check withdraw
        int count=0;
        for(int i=0;i<3;i++)
            for(int j=0;j<3;j++)
                if(grid[i][j]!=' ')
                    count++;
        if(count == 9)
            return true;
        return false;
    }

    public void fillGrid(){
        for(int i=0;i<3;i++)
            for(int j=0;j<3;j++)
                grid[i][j]=' ';
    }
    public void printLine(int i){
        for(int j=0;j<3;j++){
            System.out.print(grid[i][j]+" ");
        }
    }
    public void print(){
        for(int i=0;i<3;i++){
            System.out.println();
            for(int j=0;j<3;j++){
                System.out.print(grid[i][j] + "|");
            }
        }
    }
    public void winnerGrid(){
        if(winner=='X'){
            char[][] winGrid ={{'X',' ', 'X'},{' ', 'X', ' '},{'X',' ', 'X'}};
            change3x3Grid(winGrid);
        }
        else if(winner =='O'){
            char[][] winGrid ={{' ','O', ' '},{'O', ' ', 'O'},{' ','O', ' '}};
            change3x3Grid(winGrid);
        }
    }


}
