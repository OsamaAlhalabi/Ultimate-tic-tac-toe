package GameImplementation;

public class Helper {

    public int getX3x3G(int x, int gridID){
        if(gridID == 9 ||gridID == 8 || gridID ==7 )
            return x-=6;
        if (gridID == 4||gridID == 5 || gridID ==6)
            return x-=3;
        return x;
    }
    public int getY3x3G(int y, int gridID){
        if(gridID == 9 ||gridID == 6 || gridID ==3 )
            return y-=6;
        if (gridID == 2||gridID == 5 || gridID ==8)
            return y-=3;
        return y;
    }
    public int getXinGG(int x , int id){
        if(id == 4 || id == 5 || id == 6)
            return x+3;
        if(id == 7 || id == 8 || id == 9)
            return x+6;
        return x;
    }
    public int getYinGG(int y , int id){
        if(id == 2 || id == 5 || id == 8)
            return y+3;
        if(id == 3 || id == 6 || id == 9)
            return y+6;
        return y;
    }
    public int get3x3GridID(int x , int y ){
        if(x >0 && x<4){
            if(y>0 && y<4)
                return 1;
            if(y>3 && y<7)
                return 2;
            if(y>6 && y<10)
                return 3;
        }
        else if(x >3 && x<7){
            if(y>0 && y<4)
                return 4;
            if(y>3 && y<7)
                return 5;
            if(y>6 && y<10)
                return 6;
        }
        else if(x >6 && x<10){
            if(y>0 && y<4)
                return 7;
            if(y>3 && y<7)
                return 8;
            if(y>6 && y<10)
                return 9;
        }
        return 0;
    }

    public boolean availableIndex(int gridID , int x ,int y ){
        if (x == -1 && y == -1 && gridID ==0)
            return true;
        if(gridID == 1){
            if(x>=1 && x<=3)
                if(y>=1 && y<=3)
                    return true;
        }
        else if(gridID == 2){
            if(x>0 && x<4)
                if(y>3 && y<7)
                    return true;
        }
        else if(gridID == 3){
            if(x>0 && x<4)
                if(y>6 && y<10)
                    return true;
        }
        else if(gridID == 4){
            if(x>3 && x<7)
                if(y>0 && y<4)
                    return true;
        }
        else if(gridID == 5){
            if(x>3 && x<7)
                if(y>3 && y<7)
                    return true;
        }
        else if(gridID == 6){
            if(x>3 && x<7)
                if(y>6 && y<10)
                    return true;
        }
        else if(gridID == 7){
            if(x>6 && x<10)
                if(y>0 && y<4)
                    return true;
        }
        else if(gridID == 8){
            if(x>6 && x<10)
                if(y>3 && y<7)
                    return true;
        }
        else if(gridID == 9){
            if(x>6 && x<10)
                if(y>6 && y<10)
                    return true;
        }

        else if(gridID==0)
            return true;
        return false;
    }
    public char otherPlayer(char player){
        if(player == 'X')
            return 'O';
        return 'X';
    }

    public Pair<Integer,Integer> getNextGridID(char[][] g1 , char[][] g2){
        for(int i=0;i<3;i++)
            for(int j=0;j<3;j++)
                if(g1[i][j]!=g2[i][j])
                    return new Pair<>(i+1,j+1);
         return new Pair<>(0,0);
    }

    public Pair<Integer,Integer>getSpacificCoord(Grid3x3 g){
        for (int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                if(g.getGrid()[i][j]=='P')
                    return new Pair<>(i,j);
            }
        }
        return new Pair<>(0,0);
    }
 
    public int getPriority(int i , int j){
        if(i == 1 && j == 1 )
            return 3;
        if((i == 0 && j == 2)||(i == 0 && j == 0)||(i == 2 && j == 2)||(i == 2 && j == 0))
            return 2;
        return 1;
    }

}
