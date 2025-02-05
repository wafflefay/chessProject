import java.util.List;
import java.util.Scanner;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args){
        Board board = new Board();
        board.print();
        Scanner scanner = new Scanner(System.in);
        while(true){
            // System.out.println("running turn");
            board.turn(scanner);
            // System.out.println("turn ran");
            board.print();
        }
        // scanner.close();
        
    }
}

class Board{
    public List<List<Cell>> board = new ArrayList<>();

    public Board(){
        for (int i = 0; i < 8; i++) {
        board.add(new ArrayList<>()); // Create a new list for each row
        }
        for(int i = 0; i<8; i+=7){
            board.get(i).add(new Cell(new Piece(new Piecetype("R"))));
            board.get(i).add(new Cell(new Piece(new Piecetype("N"))));
            board.get(i).add(new Cell(new Piece(new Piecetype("B"))));
            board.get(i).add(new Cell(new Piece(new Piecetype("Q"))));
            board.get(i).add(new Cell(new Piece(new Piecetype("K"))));
            board.get(i).add(new Cell(new Piece(new Piecetype("B"))));
            board.get(i).add(new Cell(new Piece(new Piecetype("N"))));
            board.get(i).add(new Cell(new Piece(new Piecetype("R"))));
        }
        for(int i = 1; i<=6; i+=5){
            for(int j=0; j<8; j++){
                board.get(i).add(new Cell(new Piece(new Piecetype("P"))));
            }
        }
        for(int i = 2; i<6; i++){
            for(int j = 0; j<8; j++){
                board.get(i).add(new Cell(new Piece(new Piecetype("0"))));
            }
        }
    }


    public void print(){
        System.out.println("");
        for(int i=0; i<8; i++){
            List<String> pList = new ArrayList<>();
            for(int j=0; j<8; j++){
                pList.add(this.board.get(i).get(j).piece.type.type);
            }
            System.out.println(pList);
        }
    }

    public void turn(Scanner scanner){
        List<Integer> mover = new ArrayList<>();
        for(int i=0; i<4; i++){
            String numStr = scanner.nextLine();
            int num = Integer.parseInt(numStr);
            mover.add(num);
        }
        this.move(mover);
    }

    public void move(List<Integer> coordList){
        this.board = this.board.get(coordList.get(1)).get(coordList.get(0)).move(this.board, coordList);
    }
}

class Cell{
    public Piece piece;
    public Cell(Piece piece){
        this.piece = piece;
    }

    public List<List<Cell>> move(List<List<Cell>> board, List<Integer> coords){
        List<List<String>> legalMoves = this.piece.type.legalMoves(board, coords);
        if(legalMoves.get(coords.get(3)).get(coords.get(2))=="X"){
            board.get(coords.get(3)).set(coords.get(2), new Cell(this.piece));
            board.get(coords.get(1)).set(coords.get(0), new Cell(new Piece(new Piecetype("0"))));
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
        List<String> ffs = new ArrayList<>();
        for(int i=0; i<8; i++){
            ffs.add("X");
        }
        for(int i=0; i<8; i++){
            moves.add(ffs);
        }
        return moves;
    }
}