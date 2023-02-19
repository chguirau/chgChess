/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.chg.chess;

/**
 *
 * @author carlosherrero
 */
public class FEN {

    private static final char fenChars[] =
        {'K', 'P', 'Q', 'R', 'B', 'N', '-', 'n', 'b', 'r', 'q', 'p', 'k'};
    


    public static final int fenCharToPiece(char ch)
    {
        switch(ch)
        {
            case 'K': return Board.WKING;
            case 'P': return Board.WPAWN;
            case 'Q': return Board.WQUEEN;
            case 'R': return Board.WROCK;
            case 'B': return Board.WBISHOP;
            case 'N': return Board.WKNIGHT;
            case 'n': return Board.BKNIGHT;
            case 'b': return Board.BBISHOP;
            case 'r': return Board.BROCK;
            case 'q': return Board.BQUEEN;
            case 'p': return Board.BPAWN;
            case 'k': return Board.BKING;
            case '-': return Board.EMPTY; // ?? not sure ***
            default: return Board.EMPTY; // ?? not sure ***
        }
    }
    
    public static final char pieceToFenChar(int piece)
    {
        switch(piece)
        {
            case Board.WKING: return 'K';
            case Board.WPAWN: return 'P';
            case Board.WQUEEN: return 'Q';
            case  Board.WROCK: return 'R'; 
            case Board.WBISHOP: return'B';  
            case   Board.WKNIGHT: return 'N';
            case  Board.BKNIGHT: return 'n';
            case  Board.BBISHOP: return 'b';
            case   Board.BROCK: return 'r';
            case  Board.BQUEEN: return 'q'; 
            case  Board.BPAWN: return 'p';
            case  Board.BKING: return 'k'; 
            case  Board.EMPTY: return  '-'; // ?? not sure ***
            default: return '-'; // ?? not sure ***
        }
    }
    
    
    public static final String START_POSITION =
        "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1";
    
    //======================================================================
    
    
    public static void initFromFEN(Board pos, String fen, boolean strict) throws IllegalArgumentException
    {
        //assumes pos includes all data structures
        
        int index = 0;
        char ch;
        
        /*========== 1st field : pieces ==========*/
        int row = 7;
        int col = 0;
        while (index < fen.length() && fen.charAt(index) != ' ') {
            ch = fen.charAt(index);
            if (ch == '/') {
                if (col != 8)
                    throw new IllegalArgumentException("Malformatted fen string: unexpected '/' found at index " + index);
                row--; col = 0;
            } else if (ch >= '1' && ch <= '8') {
                int num = (int)(ch - '0');
                if (col + num > 8)
                    throw new IllegalArgumentException("Malformatted fen string: too many pieces in rank at index " + index + ": " + ch);
                for (int j=0; j<num; j++) {
                    pos.board[col][row]=Board.EMPTY;
                    col++;
                }
            } else {
                int piece = FEN.fenCharToPiece(ch);
                if (piece == Board.EMPTY)
                    throw new IllegalArgumentException("Malformatted fen string: illegal piece char: " + ch);
                pos.board[col][row]=piece;
                col++;
            }
            index++;
        }
        if (row != 0 || col != 8)
            throw new IllegalArgumentException("Malformatted fen string: missing pieces at index: " + index);
        
        /*========== 2nd field : to play ==========*/
        if (index + 1 < fen.length() && fen.charAt(index) == ' ') {
            ch = fen.charAt(index + 1);
            if      (ch == 'w') pos.turn=Board.WHITE;
            else if (ch == 'b') pos.turn=Board.BLACK;
            else
                throw new IllegalArgumentException("Malformatted fen string: expected 'to play' as second field but found " + ch);
            index += 2;
        }
        
        /*========== 3rd field : castles ==========*/
        if (index + 1 < fen.length() && fen.charAt(index) == ' ') {
            index++;
            pos.canBlackCastleLong=false;
            pos.canBlackCastleShort=false;
            pos.canWhiteCastleLong=false;
            pos.canWhiteCastleShort=false;
            if (fen.charAt(index) == '-') {
                index++;
            } else {
                int last = -1;
                while (index < fen.length() && fen.charAt(index) != ' ') {
                    ch = fen.charAt(index);
                    if      (ch == 'K')                          {pos.canWhiteCastleShort=true; last = 0;}
                    else if (ch == 'Q' && (!strict || last < 1)) {pos.canWhiteCastleLong=true;  last = 1;}
                    else if (ch == 'k' && (!strict || last < 2)) {pos.canBlackCastleShort=true; last = 2;}
                    else if (ch == 'q' && (!strict || last < 3)) {pos.canBlackCastleLong=true;  last = 3;}
                    else
                        throw new IllegalArgumentException("Malformatted fen string: illegal castles identifier or sequence " + ch);
                    index++;
                }
            }
        } else {
            throw new IllegalArgumentException("Malformatted fen string: expected castles at index " + index);
        }
        
        /*========== 4th field : ep square ==========*/
        
        if (index + 1 < fen.length() && fen.charAt(index) == ' ') {
            index++;
            if (fen.charAt(index) == '-') {
                pos.enPassant=Board.NO_enPassant; //9 is no En Passant
                index++;
            } else {
                if (index + 2 < fen.length()) {
                    pos.enPassant = pos.getPositionFromAlgebraicNotation(fen.substring(index, index + 2));
                    index += 2;
                }
            }
        } else {
            throw new IllegalArgumentException("Malformatted fen string: expected ep square at index " + index);
        }
        
        /*========== 5th field : half move clock ==========*/
        if (index + 1 < fen.length() && fen.charAt(index) == ' ') {
            index++;
            int start = index; while(index < fen.length() && fen.charAt(index) != ' ') index++;
            pos.halfMove50rule=Integer.parseInt(fen.substring(start, index));
        } else {
            throw new IllegalArgumentException("Malformatted fen string: expected half move clock at index " + index);
        }
        
        /*========== 6th field : full move number ==========*/
        if (index + 1 < fen.length() && fen.charAt(index) == ' ') {
            pos.moves=Integer.parseInt(fen.substring(index + 1));
        } else {
            throw new IllegalArgumentException("Malformatted fen string: expected ply number at index " + index);
        }
        
        /*========== now check the produced position ==========*/
        if (!pos.isValid())
        {
            throw new IllegalArgumentException("Malformatted fen string: ");
        }
    }
    
    public static String getFEN(Board pos)
    {
        StringBuffer sb = new StringBuffer();
        
        /*========== 1st field : pieces ==========*/
        int row = 7, col = 0;
        int blanks = 0;
        while (row >= 0) {
            int piece = pos.board[col][row];
            if (piece == Board.EMPTY) {
                blanks++;
            } else {
                if (blanks > 0) {
                    sb.append(blanks); blanks = 0;
                }
                sb.append(pieceToFenChar(piece));
            }
            col++;
            if (col > 7) {
                if (blanks > 0) {
                    sb.append(blanks); blanks = 0;
                }
                row--; col = 0; blanks = 0;
                if (row >= 0) sb.append('/');
            }
        }
        
        /*========== 2nd field : to play ==========*/
        sb.append(' ').append(pos.turn == Board.WHITE ? 'w' : 'b');
        
        /*========== 3rd field : castles ==========*/
        boolean anyCastle=false;
        sb.append(' ');
        if (pos.canWhiteCastleShort) {
            sb.append('K');
            anyCastle=true;
        }
        if (pos.canWhiteCastleLong) {
            sb.append('Q');
            anyCastle=true;
        }
        if (pos.canBlackCastleShort)
        { 
            sb.append('k');
            anyCastle=true;
        }
        if (pos.canBlackCastleLong) {
            sb.append('q');
            anyCastle=true;
        }
        if (!anyCastle)
            sb.append('-');

        
        /*========== 4th field : ep square ==========*/
        sb.append(' ');
        if (pos.enPassant==Board.NO_enPassant)
            sb.append('-');
        else
            sb.append(pos.getAlgebraicPositionFromSquare(pos.enPassant.col, pos.enPassant.row));
        
        /*========== 5th field : half move clock ==========*/
        sb.append(' ').append(pos.halfMove50rule );
        
        /*========== 6th field : full move number ==========*/
        sb.append(' ').append(pos.moves );
        
        return sb.toString();        
    }
}
