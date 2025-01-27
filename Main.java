import java.util.List;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args){
        Board board = new Board();
        board.print();
        List<Integer> mover = new ArrayList<>();
        mover.add(0);
        mover.add(0);
        mover.add(5);
        mover.add(5);
        Board.turn(mover);
    }
}

class Board{
    public List<List<Cell>> board = new ArrayList<>();

    public Board(){
        List<Cell> row1 = new ArrayList<>();
        for(int i=0;i<8;i++){
            row1.add(new Cell(new Piece(new Piecetype("X"))));
        }
        List<Cell> row2 = new ArrayList<>();
        for(int i=0;i<8;i++){
            row2.add(new Cell(new Piece(new Piecetype("0"))));
        }     

        for(int i=0; i<2; i++){
            this.board.add(row1);
        }
        for(int i=0; i<4; i++){
            this.board.add(row2);
        }
        for(int i=0; i<2; i++){
            this.board.add(row1);
        }
    }

    public void print(){
        for(int i=0; i<8; i++){
            List<String> pList = new ArrayList<>();
            for(int j=0; j<8; j++){
                pList.add(this.board.get(i).get(j).piece.type.type);
            }
            System.out.println(pList);
        }
    }

    public void turn(List<Integer> coordList){
        this.board = this.board.get(0).get(0).move(this.board, coordList);
        this.print();
    }
}

class Cell{
    public Piece piece;
    public Cell(Piece piece){
        this.piece = piece;
    }

    public List<List<Cell>> move(List<List<Cell>> board, List<Integer> coords){
        List<List<String>> legalMoves = this.piece.type.legalMoves(board, coords);
        if(legalMoves.get(coords.get(2)).get(coords.get(3))=="X"){
            board.get(coords.get(2)).set(coords.get(3), new Cell(this.piece));
            board.get(coords.get(0)).set(coords.get(1), new Cell(new Piece(new Piecetype("0"))));
        }
        return board;
    }
}

class Piece{
    public Piecetype type;

    public Piece(Piecetype type){
        this.type = type;
    }
}

class Piecetype {
    public String type = "";
    public Piecetype(String type){
        this.type = type;
    }

    public List<List<String>> legalMoves(List<List<Cell>> board, List<Integer> pos){
        List<List<String>> moves = new ArrayList<>();
        List<String> ffs = new ArrayList();
        for(int i=0; i<8; i++){
            ffs.add("X");
        }
        for(int i=0; i<8; i++){
            moves.add(ffs);
        }
        return moves;
    }
}