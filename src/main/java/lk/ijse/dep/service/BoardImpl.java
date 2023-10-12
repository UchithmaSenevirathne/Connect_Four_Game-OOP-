package lk.ijse.dep.service;

public class BoardImpl implements Board{
    private Piece[][] pieces;
    private BoardUI boardUI;
    public BoardImpl(BoardUI boardUI){
        this.boardUI = boardUI;

        pieces=new Piece[NUM_OF_COLS][NUM_OF_ROWS];

        for (int i = 0; i < pieces.length; i++) {
            for (int j = 0; j < pieces[i].length; j++) {
                pieces[i][j] = Piece.EMPTY;
            }
        }
    }

    @Override
    public BoardUI getBoardUI() {
        return boardUI;
    }

    @Override
    public int findNextAvailableSpot(int col) {
        for (int i = 0; i < NUM_OF_ROWS; i++) {
            if(pieces[col][i]==Piece.EMPTY){
                return i;
            }
        }
        return -1;
    }

    @Override
    public boolean isLegalMoves(int col) {
        if(findNextAvailableSpot (col) == -1){
            return false;
        }
        return true;
    }

    @Override
    public boolean exitLegalMoves() {
        for (int i = 0; i < pieces.length; i++) {
            for (int j = 0; j < pieces[i].length; j++) {
                if(pieces[i][j] == Piece.EMPTY){
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public void updateMove(int col, Piece move) {
        pieces[col][findNextAvailableSpot (col)] = move;
    }

    @Override
    public Winner findWinner() {
        Piece winningPies = Piece.EMPTY;

        int col1 = 0;
        int row1 = 0;
        int col2 = 0;
        int row2 = 0;

        for (int i = 0; i < 6; i++) {
            if(findNextAvailableSpot (i) == 4 || findNextAvailableSpot (i) == -1){
                if (pieces[i][0] == pieces[i][1] && pieces[i][1] == pieces[i][2] && pieces[i][2] == pieces[i][3]){
//                    col1 = i;
//                    row1 = 0;
//                    col2 = i;
//                    row2 = 3;
//                    winningPies = pieces[i][0];
                    return new Winner (pieces[i][0],i,0,i,3);
                }
            }else{
                if (pieces[i][1] == pieces[i][2] && pieces[i][2] == pieces[i][3] && pieces[i][3] == pieces[i][4]){
                    col1 = i;
                    row1 = 1;
                    col2 = i;
                    row2 = 4;
                    winningPies = pieces[i][1];
                   // return new Winner (pieces[i][1],i,1,i,4);
                }
            }
        }

        for (int i = 0; i < 5; i++) {
            if (findNextAvailableCol (i) == -1 ||  findNextAvailableCol (i) == 4 || findNextAvailableCol (i) == 5){
                if (pieces[0][i] == pieces[1][i] && pieces[1][i] == pieces[2][i] && pieces[2][i] == pieces[3][i]){
//                    col1 = 0;
//                    row1 = i;
//                    col2 = 3;
//                    row2 = i;
//                    winningPies = pieces[0][i];
                    return new Winner (pieces[0][i],0,i,3,i);
                }else if (pieces[1][i] == pieces[2][i] && pieces[2][i] == pieces[3][i] && pieces[3][i] == pieces[4][i]){
//                    col1 = 1;
//                    row1 = i;
//                    col2 = 4;
//                    row2 = i;
//                    winningPies = pieces[1][i];
                    return new Winner (pieces[1][i],1,i,4,i);
                }else if (pieces[2][i] == pieces[3][i] && pieces[3][i] == pieces[4][i] && pieces[4][i] == pieces[5][i]){
//                    col1 = 2;
//                    row1 = i;
//                    col2 = 5;
//                    row2 = i;
//                    winningPies = pieces[2][i];
                    return new Winner (pieces[2][i],2,i,5,i);
                }
            }
        }

        return new Winner (Piece.EMPTY);

//        Winner winner;
//
//        if (winningPies == Piece.EMPTY){
//             winner = new Winner (winningPies);
//        }else{
//             winner = new Winner (winningPies,col1,row1,col2,row2);
//        }
//
//        return  winner;
    }

    public int findNextAvailableCol(int row) {
        for (int i = 0; i < NUM_OF_COLS; i++) {
            if(pieces[i][row]==Piece.EMPTY){
                return i;
            }
        }
        return -1;
    }
}
