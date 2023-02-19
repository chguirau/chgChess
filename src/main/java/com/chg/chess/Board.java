/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.chg.chess;
import java.util.*;
//import java.util.concurrent.*;

/**
 *
 * @author carlosherrero
 */
public class Board {
    public static final int WHITE = 1;
    public static final int BLACK = 0;
    
    public static final int EMPTY = 0;
    public static final int WPAWN = 10;
    public static final int WKNIGHT = 11;
    public static final int WBISHOP = 12;
    public static final int WROCK = 13;
    public static final int WQUEEN = 14;
    public static final int WKING = 15;
    public static final int BPAWN = 20;
    public static final int BKNIGHT = 21;
    public static final int BBISHOP = 22;
    public static final int BROCK = 23;
    public static final int BQUEEN = 24;
    public static final int BKING = 25;

    public static final String KingSt ="K";
    public static final String QueenSt ="Q";
    public static final String RockSt ="R";
    public static final String BishopSt ="B";
    public static final String KnightSt ="N";

    
    //cols
    public static int A = 0;
    public static int B = 1;
    public static int C = 2;
    public static int D = 3;
    public static int E = 4;
    public static int F = 5;
    public static int G = 6;
    public static int H = 7;
    
    //rows
    public static int r1 = 0;
    public static int r2 = 1;
    public static int r3 = 2;
    public static int r4 = 3;
    public static int r5 = 4;
    public static int r6 = 5;
    public static int r7 = 6;
    public static int r8 = 7;
    
    public static Position NO_enPassant=new Position(9,9);
    
    public int turn; // WHITE or BLACK
    
    public int[][] board=new int[8][8];
    
    public static final Position a1=new Position(Board.A, Board.r1);
    public static final Position a2=new Position(Board.A, Board.r2);
    public static final Position a3=new Position(Board.A, Board.r3);
    public static final Position a4=new Position(Board.A, Board.r4);
    public static final Position a5=new Position(Board.A, Board.r5);
    public static final Position a6=new Position(Board.A, Board.r6);
    public static final Position a7=new Position(Board.A, Board.r7);
    public static final Position a8=new Position(Board.A, Board.r8);

    public static final Position b1=new Position(Board.B, Board.r1);
    public static final Position b2=new Position(Board.B, Board.r2);
    public static final Position b3=new Position(Board.B, Board.r3);
    public static final Position b4=new Position(Board.B, Board.r4);
    public static final Position b5=new Position(Board.B, Board.r5);
    public static final Position b6=new Position(Board.B, Board.r6);
    public static final Position b7=new Position(Board.B, Board.r7);
    public static final Position b8=new Position(Board.B, Board.r8);
    
    public static final Position c1=new Position(Board.C, Board.r1);
    public static final Position c2=new Position(Board.C, Board.r2);
    public static final Position c3=new Position(Board.C, Board.r3);
    public static final Position c4=new Position(Board.C, Board.r4);
    public static final Position c5=new Position(Board.C, Board.r5);
    public static final Position c6=new Position(Board.C, Board.r6);
    public static final Position c7=new Position(Board.C, Board.r7);
    public static final Position c8=new Position(Board.C, Board.r8);
    
    public static final Position d1=new Position(Board.D, Board.r1);
    public static final Position d2=new Position(Board.D, Board.r2);
    public static final Position d3=new Position(Board.D, Board.r3);
    public static final Position d4=new Position(Board.D, Board.r4);
    public static final Position d5=new Position(Board.D, Board.r5);
    public static final Position d6=new Position(Board.D, Board.r6);
    public static final Position d7=new Position(Board.D, Board.r7);
    public static final Position d8=new Position(Board.D, Board.r8);
    
    public static final Position e1=new Position(Board.E, Board.r1);
    public static final Position e2=new Position(Board.E, Board.r2);
    public static final Position e3=new Position(Board.E, Board.r3);
    public static final Position e4=new Position(Board.E, Board.r4);
    public static final Position e5=new Position(Board.E, Board.r5);
    public static final Position e6=new Position(Board.E, Board.r6);
    public static final Position e7=new Position(Board.E, Board.r7);
    public static final Position e8=new Position(Board.E, Board.r8);
    
    public static final Position f1=new Position(Board.F, Board.r1);
    public static final Position f2=new Position(Board.F, Board.r2);
    public static final Position f3=new Position(Board.F, Board.r3);
    public static final Position f4=new Position(Board.F, Board.r4);
    public static final Position f5=new Position(Board.F, Board.r5);
    public static final Position f6=new Position(Board.F, Board.r6);
    public static final Position f7=new Position(Board.F, Board.r7);
    public static final Position f8=new Position(Board.F, Board.r8);
    
    public static final Position g1=new Position(Board.G, Board.r1);
    public static final Position g2=new Position(Board.G, Board.r2);
    public static final Position g3=new Position(Board.G, Board.r3);
    public static final Position g4=new Position(Board.G, Board.r4);
    public static final Position g5=new Position(Board.G, Board.r5);
    public static final Position g6=new Position(Board.G, Board.r6);
    public static final Position g7=new Position(Board.G, Board.r7);
    public static final Position g8=new Position(Board.G, Board.r8);
    
    public static final Position h1=new Position(Board.H, Board.r1);
    public static final Position h2=new Position(Board.H, Board.r2);
    public static final Position h3=new Position(Board.H, Board.r3);
    public static final Position h4=new Position(Board.H, Board.r4);
    public static final Position h5=new Position(Board.H, Board.r5);
    public static final Position h6=new Position(Board.H, Board.r6);
    public static final Position h7=new Position(Board.H, Board.r7);
    public static final Position h8=new Position(Board.H, Board.r8);
    
    public static final Position[] boardPositions ={
        Board.a1,Board.a2, Board.a3,Board.a4,Board.a5,Board.a6,Board.a7,Board.a8,
        Board.b1,Board.b2, Board.b3,Board.b4,Board.b5,Board.b6,Board.b7,Board.b8,
        Board.c1,Board.c2, Board.c3,Board.c4,Board.c5,Board.c6,Board.c7,Board.c8,
        Board.d1,Board.d2, Board.d3,Board.d4,Board.d5,Board.d6,Board.d7,Board.d8,
        Board.e1,Board.e2, Board.e3,Board.e4,Board.e5,Board.e6,Board.e7,Board.e8,
        Board.f1,Board.f2, Board.f3,Board.f4,Board.f5,Board.f6,Board.f7,Board.f8,
        Board.g1,Board.g2, Board.g3,Board.g4,Board.g5,Board.g6,Board.g7,Board.g8,
        Board.h1,Board.h2, Board.h3,Board.h4,Board.h5,Board.h6,Board.h7,Board.h8
    };
    
    public static Position getPosition(int i, int j)
    {
        return boardPositions[i*8+j];
    }
    
    public static final boolean isWhitePosition(Position p)
    {
        return ((p.col+p.row) % 2 != 0);
    }
    
    public static Position getPositionFromAlgebraicNotation(String apos) throws IllegalArgumentException
    {
        if (apos.length()!=2) throw new IllegalArgumentException("Malformed algebraic position ");
        String cols="abcdefgh";
        String rows="12345678";
        int col=cols.indexOf(apos.toCharArray()[0]);
        int row=rows.indexOf(apos.toCharArray()[1]);
        if (col==-1 || row==-1) throw new IllegalArgumentException("Malformed algebraic position ");
        return getPosition(col, row);
    }

    public static String getAlgebraicPositionFromSquare(int acol, int arow)
    {
        if (acol<Board.A || acol>Board.H || arow<Board.r1 || arow>Board.r8) throw new IllegalArgumentException("Wrong position ");
        String cols="abcdefgh";
        String rows="12345678";
        char col=cols.charAt(acol);
        char row=rows.charAt(arow);
        return new String(""+col+row);
    }
    
    public static String getAlgebraicPieceNotation(int apiece)
    {
        switch(apiece)
        {
            case WKING, BKING: return Board.KingSt;
            case WQUEEN, BQUEEN: return Board.QueenSt;
            case WROCK, BROCK: return Board.RockSt;
            case WBISHOP, BBISHOP: return Board.BishopSt;
            case WKNIGHT, BKNIGHT: return Board.KnightSt;
            case WPAWN, BPAWN: return "";
            default: throw new IllegalArgumentException("Invalid move ");
        }
    }
            
    public Position enPassant; // set to NO_enPassant, NO_enPassant if no en passant ("-" in FEN)
    
    public int moves;
    
    public int halfMove50rule;
    
    public boolean canWhiteCastleShort;
    public boolean canWhiteCastleLong;
    public boolean canBlackCastleShort;
    public boolean canBlackCastleLong;
    
    public boolean isCheck;
    
    public String lastMoveAlgebraicNotation="";
    
    public Movement lastMove; //we save last move in board when creating a list of potential moves / boards
    
    public Board()
    {
        initOriginalPosition();
    }
    
    public Board(boolean initialize)
    {
        if (initialize) initOriginalPosition();
        //else
        //{
            //we just create object
        //}
    }
            
    public void initOriginalPosition()
    {
        //init position
        board[A][r1]=WROCK;
        board[B][r1]=WKNIGHT;
        board[C][r1]=WBISHOP;
        board[D][r1]=WQUEEN;
        board[E][r1]=WKING;
        board[F][r1]=WBISHOP;
        board[G][r1]=WKNIGHT;
        board[H][r1]=WROCK;
        for (int i=A; i<=H; i++)
            board[i][r2]=WPAWN;
        for (int i=A; i<=H; i++)
            for (int j=r3; j<=r6; j++)
                board[i][j]=EMPTY;
        for (int i=A; i<=H; i++)
            board[i][r7]=BPAWN;
        board[A][r8]=BROCK;
        board[B][r8]=BKNIGHT;
        board[C][r8]=BBISHOP;
        board[D][r8]=BQUEEN;
        board[E][r8]=BKING;
        board[F][r8]=BBISHOP;
        board[G][r8]=BKNIGHT;
        board[H][r8]=BROCK;        
        
        
        enPassant=Board.NO_enPassant;
        canWhiteCastleShort=true;
        canWhiteCastleLong=true;
        canBlackCastleShort=true;
        canBlackCastleLong=true;
        
        turn=WHITE;
        moves=1;
        halfMove50rule=0;
        
        boardWhitePieces=new ArrayList<GamePiece>();
        boardBlackPieces=new ArrayList<GamePiece>();

        isCheck=false;
        
        getPieces();
        //init lists for easy computing
        
        wkp=Board.e1;
        bkp=Board.e8;
        
    }
    
    protected Position wkp; //white king position, we track to verify if check
    protected Position bkp; //same black king
    
    public boolean isValid()
    //utility, we should not need to use this method once everything is set properly
    //maybe when loading a board from a list of pieces, or from FEN, just in case
    //after that from a valid board, following valid moves, we reach valid boards
    {
        
        // check there are Kings, 1 white, 1 black
        int w_king_found=0;
        for (int i=0; i<=7; i++)
            for (int j=0; j<=7; j++)
            {
                if (board[i][j]==WKING) w_king_found++;
                //System.out.println(board[i][j]);
                if (w_king_found==2) return false; //more than 1 w king
            }
        if (w_king_found == 0 ) return false; // no w king
        
        int b_king_found=0;
        for (int i=0; i<=7; i++)
            for (int j=0; j<=7; j++)
            {
                if (board[i][j]==BKING) b_king_found++;
                if (b_king_found==2) return false; //mora than 1 b king
            }
        if (b_king_found == 0 ) return false; // no b king
        
        //now check if there are more than maximum number of a piece
        //9 queens, 10 rocks, knights, bishops
        //also total pawns + extra pieces is 8
        //also checked if estrange values set!!
        int num_wqueens=0;
        int num_bqueens=0;
        int num_wrocks=0;
        int num_wbishops=0;
        int num_wknights=0;
        int num_wpawns=0;
        int num_brocks=0;
        int num_bbishops=0;
        int num_bknights=0;
        int num_bpawns=0;
        for (int i=0; i<=7; i++)
            for (int j=0; j<=7; j++)
            {
                switch (board[i][j])
                {
                    case WQUEEN: num_wqueens++; break;
                    case BQUEEN: num_bqueens++; break;
                    case WROCK: num_wrocks++; break;
                    case BROCK: num_brocks++; break;
                    case WKNIGHT: num_wknights++; break;
                    case BKNIGHT: num_bknights++; break;
                    case WBISHOP: num_wbishops++; break;
                    case BBISHOP: num_bbishops++; break;
                    case WPAWN: num_wpawns++; break;
                    case BPAWN: num_bpawns++; break;
                    case EMPTY, BKING, WKING: break;
                    default: return false; //invalid value
                }
            }
        
        if (num_wqueens + num_wrocks + num_wbishops + num_wknights + num_wpawns > 15) return false;
        if (num_bqueens + num_brocks + num_bbishops + num_bknights + num_bpawns > 15) return false;
        if (num_wqueens > 9 || num_bqueens > 9 ) return false;
        if (num_wrocks > 10 || num_brocks > 10 ) return false;
        if (num_wknights > 10 || num_bknights > 10 ) return false;
        if (num_wbishops > 10 || num_bbishops > 10 ) return false;
        if (num_wrocks > 10 || num_brocks > 10 ) return false;
        
        //check no w pawns in row 1 or 8 or b pawns in row 8 or 1
        //they move ahead, if they promote, they are not a pawn anymore
        for (int i=0; i<=7; i++) if (board[i][r1]==WPAWN) return false;
        for (int i=0; i<=7; i++) if (board[i][r8]==WPAWN) return false;
        for (int i=0; i<=7; i++) if (board[i][r1]==BPAWN) return false;
        for (int i=0; i<=7; i++) if (board[i][r8]==BPAWN) return false;
        
        //en passant only row 3 for white (pawn in row 4) or row 6 for black (pawn in row 5)
        if (enPassant ==Board.NO_enPassant ) return true;
        if (enPassant.col < A || enPassant.col > H ) return false; // invlaid value, 9 was checked before
        if (enPassant.row == r3 && turn==BLACK)
        {
            return (board[enPassant.col][r4]==WPAWN);
        }
        if (enPassant.row == r6 && turn==WHITE) return true;
        {
            return (board[enPassant.col][r5]==BPAWN);
        }
                
    }
    
    //test
    public static void main(String[] args) {
        Board aBoard=new Board();
        if (aBoard.isValid()) System.out.println("Valid!"); else System.out.println("Not Valid!");
        String afen=FEN.getFEN(aBoard);
        System.out.println(afen);
        FEN.initFromFEN(aBoard, afen, false);
        afen=FEN.getFEN(aBoard);
        System.out.println(afen);

    }

    public ArrayList<GamePiece> boardWhitePieces;
    public ArrayList<GamePiece> boardBlackPieces;
    //we maintain lists of white and black pieces, prebuilt to easily calculate movements
    //the idea is to update the list after a movement on the board, to avoid looking for pieces every time
    //and ideally to maintain moves? I do not think it is possible, maybe potential moves

    private void getPieces()
    {
        GamePiece p=null;
        int piece=Board.EMPTY;
        for (int i=Board.A; i<=Board.H; i++)
        {
            for (int j=Board.r1; j<=Board.r8; j++)
            {
                piece=board[i][j];
                switch(piece)
                {
                    case Board.BBISHOP: 
                    case Board.BKNIGHT: 
                    case Board.BROCK: 
                    case Board.BPAWN: 
                    case Board.BQUEEN: 
                    case Board.BKING: 
                        p=new GamePiece(getPosition(i,j), piece);
                        boardBlackPieces.add(p);
                        break;
                    case Board.WBISHOP: 
                    case Board.WKNIGHT: 
                    case Board.WROCK: 
                    case Board.WPAWN: 
                    case Board.WQUEEN: 
                    case Board.WKING: 
                        p=new GamePiece(getPosition(i,j), piece);
                        boardWhitePieces.add(p);
                        break;
                    case Board.EMPTY: 
                        break;
                    default:
                        throw new IllegalArgumentException("no valid piece ");
                }
            }
        }
        evaluateMaterial();
    }

    // utility, not for normal game, but to init the board for a GUI to set up a position e.g.
    private void clear()
    {
        for (int i=Board.A; i<=Board.H; i++)
        {
            for (int j=Board.r1; j<=Board.r8; j++)
            {
                board[i][j]=Board.EMPTY;
            }
        }
    }

    private void resetPieces()
    {
        boardWhitePieces.clear();
        boardBlackPieces.clear();
    }

    public void initClear()
    {
        // not a valid chess board
        clear();
        resetPieces();
        getPieces();

    }

    public void initFromGamePieceList (ArrayList<GamePiece> l)
    {
        // maybe not a valid chess board, rest of fields match board pieces?
        // just for test purposes
        clear();
        Iterator<GamePiece> li=l.iterator();
        while (li.hasNext())
        {
            GamePiece agp=li.next();
            board[agp.pos.col][agp.pos.row]= agp.piece;
        }
        resetPieces();
        getPieces();
    }

    public void initFromFEN (String fen, boolean strictflag)
    {
        // valid check board if FEN string is valid
        clear();
        
        FEN.initFromFEN(this, fen, strictflag);
        resetPieces();
        getPieces();
        if(!isValid()) throw new IllegalArgumentException("No valid position");
        
        
        //set king positions
        Iterator<GamePiece> it;
        GamePiece wkpi=null;
        GamePiece bkpi=null;

            
        it=boardWhitePieces.iterator();
        while (it.hasNext())
        {
            wkpi=it.next();
            if (wkpi.piece == WKING)
            {
                wkp=wkpi.pos;
            }
        }
        it=boardBlackPieces.iterator();
        while (it.hasNext())
        {
            bkpi=it.next();
            if (bkpi.piece == BKING)
            {
                bkp=bkpi.pos;
            }
        }
        if (wkpi==null) throw new IllegalArgumentException("No white king");
        if (bkpi==null) throw new IllegalArgumentException("No black king");

        //check is different here, we have already set up the position and turn, 
        //and normally check methods are called before changing turn ...
        if (inCheck(true)) throw new IllegalArgumentException("No valid position");
        isCheck=inCheck(false);

    }

    public GamePiece move(GamePiece gp, Movement m)
    {
        //move from a pure geometric perspective
        //assume a valid movement
        //check movement origin is the piece pos orig, or else error
        //check movement origin pos has the piece
        //set origin EMPTY and target the original piece

        //we do not remove target piece if it is a capture
        int piece=gp.piece;

        // ** 
        // ** Potentially remove for optimization
        if (gp.pos.col !=m.orig_col || gp.pos.row != m.orig_row || board[m.orig_col][m.orig_row]!=gp.piece)
        {
            throw new IllegalArgumentException();
        }

        GamePiece targetPiece=null;

        board[m.orig_col][m.orig_row]=Board.EMPTY;
        board[m.target_col][m.target_row]=piece;
        targetPiece=new GamePiece(getPosition(m.target_col, m.target_row),piece);
        //System.out.println("pieces: "+boardWhitePieces.size());
        if (gp.color==Board.WHITE)
        {
            boardWhitePieces.remove(gp);
            boardWhitePieces.add(targetPiece);
        }
        else
        {
            boardBlackPieces.remove(gp);
            boardBlackPieces.add(targetPiece);
        }
        //System.out.println("pieces after: "+boardWhitePieces.size()); 

        if (piece==WKING) wkp=getPosition(m.target_col, m.target_row);
        if (piece==BKING) bkp=getPosition(m.target_col, m.target_row);
        //convenience just in case we want to check last piece moved
        return targetPiece;
    }

    
    public void removeCapturedPiece(Movement m)
    //we normally after we check a move is valid, execute the move (special move)
    //if we confirm it is a capture we remove opponent piece from list (the board is already updated with our piece)
    //enPassant we remove the opponet piece as per this Movement argument (although we do the trick in the caller, changing to right piece)
    {
        ArrayList<GamePiece> pl; //opponents pieces
        Iterator<GamePiece> it;
        GamePiece ap=null;
        if (turn==Board.WHITE)
        {
            pl=boardBlackPieces;
        }
        else
        {
            pl=boardWhitePieces;
        }
        it=pl.iterator();
        boolean found=false;
        while (it.hasNext() && !found)
        {
            ap=it.next();
            if (ap.pos.col == m.target_col && ap.pos.row == m.target_row)
            {
                it.remove();
                found=true;
            }
        }
        if (!found ) throw new IllegalArgumentException("No capture piece found");
        
        //also we update materialScore, any capture means a different material score
        switch(ap.piece)
        {
            case WQUEEN: 
            {
                materialScore=materialScore-9; //if we capture WQUEEN score is -9!
                break;
            }
            case WROCK: 
            {
                materialScore=materialScore-5;
                break;
            }
            case WBISHOP: 
            {
                materialScore=materialScore-3;
                break;
            }
            case WKNIGHT: 
            {
                materialScore=materialScore-3;
                break;
            }
            case WPAWN: 
            {
                materialScore=materialScore-1;
                break;
            }
            case BQUEEN: 
            {
                materialScore=materialScore+9;
                break;
            }
            case BROCK: 
            {
                materialScore=materialScore+5;
                break;
            }
            case BBISHOP: 
            {
                materialScore=materialScore+3;
                break;
            }
            case BKNIGHT: 
            {
                materialScore=materialScore+3;
                break;
            }
            case BPAWN: 
            {
                materialScore=materialScore+1;
                break;
            }
        }

    }
    
    public GamePiece promotionMove(GamePiece gp, Movement m)
    // here we just execute a promotion move, we assume we have previously checked it is valid
    // just PawnPromotionMovement, PawnCapturePromotionMovement is a different method
    {
        PawnPromotionMovement pm;
        GamePiece targetPiece=null;
        if (m instanceof PawnPromotionMovement || m instanceof PawnCapturePromotionMovement )
        // more robust code, remove for performance
        // && (promotionPiece!=Board.EMPTY)
        {
            if (m instanceof PawnPromotionMovement)
            pm=(PawnPromotionMovement)m;
            else
            pm=(PawnCapturePromotionMovement)m;
                

            board[m.orig_col][m.orig_row]=Board.EMPTY;
            board[m.target_col][m.target_row]=pm.promotionPiece;
            targetPiece=new GamePiece(getPosition(m.target_col, m.target_row),pm.promotionPiece);
            //System.out.println("pieces: "+boardWhitePieces.size());
            if (gp.color==Board.WHITE)
            // more robust code, remove for performance
            //if (gp.piece != Board.BPAWN && gp.piece != Board.WPAWN)
            //    throw new IllegalArgumentException("Invalid promotion move ");
            //if (gp.color==Board.WHITE && m.orig_row==Board.r7 && m.target_row==Board.r8 && 
            //        (promotionPiece==Board.WROCK || promotionPiece==Board.WQUEEN || promotionPiece == Board.WBISHOP || promotionPiece==Board.WKNIGHT) 
            //   )  
            {
                boardWhitePieces.remove(gp);
                boardWhitePieces.add(targetPiece);
            }
            else
            // more robust code, remove for performance
            //else if (gp.color==Board.BLACK && m.orig_row==Board.r2 && m.target_row==Board.r1 &&
            //        (promotionPiece==Board.BROCK || promotionPiece==Board.BQUEEN || promotionPiece == Board.BBISHOP || promotionPiece==Board.BKNIGHT) 
            //   )
            {
                boardBlackPieces.remove(gp);
                boardBlackPieces.add(targetPiece);
            }
            // more robust code, remove for performance
            //else throw new IllegalArgumentException("Invalid promotion move ");    
        }
        else throw new IllegalArgumentException("No promotion movement");

        //convenience just in case we want to check last piece moved
        return targetPiece;
            
    }

        protected ArrayList<Board> allValidMovements=null;
        
        public ArrayList<Board> getAllValidMovements()
        {
            if (allValidMovements!=null) return allValidMovements; //get cached if possible
            
            //filters list of all potential movements from every piece in the color turn
            //we return a new list, the list for each piece movements is used in every move, do not change it
            //the new list we will use in AlfaBeta: clone board in an evaluatedBoard, chessMove method (versus move, just piecemove) generates a new Board
            //evaluate it, iterate recursively
            
            //**
            // include getAllPotentialMovements code here to extract movements per piece and call chessMove
            ArrayList<Movement> al, tempal;
            al=new ArrayList<Movement>();
            Iterator<GamePiece> it;
            ArrayList<GamePiece> gpl;
            GamePiece ap;
            ArrayList<Board> vm=new ArrayList<Board>();
            Iterator<Movement> mit;
            Movement aMove;
            Board copyb;

            //get all pieces in the board for the color of the player in his turn
            //get all movements for each piece
            if (turn==Board.WHITE)
            {
                gpl=boardWhitePieces;
            }
            else
            {
                gpl=boardBlackPieces;            
            }
            it=gpl.iterator();
            while (it.hasNext())
            {
                ap=it.next();
                tempal=ap.getPotentialMovements();
                mit=tempal.iterator();
                while (mit.hasNext())
                {
                    aMove=mit.next();
                    copyb=this.copy();
                    if (copyb.chessMove(ap,aMove, true))
                    {
                        vm.add(copyb);
                        copyb.lastMove=aMove;
                    }
                }
            }
            allValidMovements=vm;
            return vm;
        }
        
        public static boolean isWhitePiece(int piece)
        {
            boolean isw;
            switch(piece)
            {
                case Board.WQUEEN:
                case Board.WROCK:
                case Board.WBISHOP:
                case Board.WKNIGHT:
                case Board.WPAWN:
                case Board.WKING:
                    isw=true;
                    break;
                default:
                    isw=false;
            }
            return isw;
        }

        public static boolean isBlackPiece(int piece)
        {
            boolean isw;
            switch(piece)
            {
                case Board.BQUEEN:
                case Board.BROCK:
                case Board.BBISHOP:
                case Board.BKNIGHT:
                case Board.BPAWN:
                case Board.BKING:
                    isw=true;
                    break;
                default:
                    isw=false;
            }
            return isw;
        }

        public boolean isCapture(Movement m)
        //simple version for now
        //call before move, promotionMove, ... as they change the board
        //is for all pieces but pawns normal moves (including promotion) !!
        // works for pawn capture moves (including promotion) although in these cases it is implicit
        {
            int targetPiece=board[m.target_col][m.target_row];
            if (targetPiece==Board.EMPTY) return false;
            if (turn==Board.WHITE)
            {
                return isBlackPiece(targetPiece);
            }
            else 
            {
                return isWhitePiece(targetPiece);
            }
        }
        

       
        public boolean chessMove(GamePiece gp, Movement m, boolean lookForCheck)
        {
            //returns true if move is valid
            // we can change Board object, if we finally return false, this Board is discarded!!
            // if we return true, this Board is changed with the valid move accordingly
            
            //allValidMovements set to null, it was cached, now we move it is no longer valid
            //even if move is not valid (return false) this board is changed
            allValidMovements=null;
            
            lastMoveAlgebraicNotation="";
            String pieceString="";
            
            boolean isCapture=false;
            boolean reset50Movesrule=false;
            reset3MovesRule=false;
            
            //check target square is not busy with our own pieces
            if (isWhitePiece(board[m.target_col][m.target_row]) && turn==Board.WHITE) return false;
            else if (isBlackPiece(board[m.target_col][m.target_row]) && turn==Board.BLACK) return false;

            //check if pawn move, which is very special
            if (gp.piece == WPAWN || gp.piece == BPAWN)
            {
                reset3MovesRule=true;
                reset50Movesrule=true;
                if (m.movement_type==Movement.PAWN_CAPTURE)
                // is it not so expensive as instanceof
                {
                    
                    isCapture=true; //set to remove capture piece later
                    if (enPassant!=Board.NO_enPassant 
                            && m.target_col == enPassant.col && m.orig_row == enPassant.row)
                    //it seems it would be enough to track enPassant col, implicit with turn what row!
                    //if (m.target.col == enPassant.col && m.orig_row == r4 for black r5 for white)
                    {
                        //no need to check if it is really a capture, if enPassant there is an opponent piece for sure, a pawn
                        // and it will fail as it is empty (it is enPassant!)
                        move(gp, m);             
                        lastMoveAlgebraicNotation=Board.getAlgebraicPositionFromSquare(m.orig_col, m.orig_row).substring(0,1)
                                +"x"+Board.getAlgebraicPositionFromSquare(m.target_col, m.target_row)+" e.p.";
                        //now change target to remove the captured opponents enPssant pawn
                        m=new Movement(m.orig_col, m.orig_row, m.target_col,enPassant.row); //for the removeCapturePiece method to work, removes from pieces lists
                        board[m.target_col][m.target_row]=Board.EMPTY; //remove from board
                    }
                    else
                    {
                        if (!isCapture(m)) return false; //this was supposed to be a capture but there is no piece to capture
                        lastMoveAlgebraicNotation=Board.getAlgebraicPositionFromSquare(m.orig_col, m.orig_row).substring(0,1)
                                +"x"+Board.getAlgebraicPositionFromSquare(m.target_col, m.target_row);
                        move(gp, m);
                    }
                    //debug
                    //System.out.println("Debug pawn captures "+lastMoveAlgebraicNotation + " " + (turn==Board.WHITE ? "White" : "Black"));

                    //any move we clear enPassant unless it is a new enPassant
                    enPassant = Board.NO_enPassant;
                }
                else if (m.movement_type==Movement.PAWN_PROMOTION)
                {
                    //any move we clear enPassant unless it is a new enPassant
                    enPassant = Board.NO_enPassant;
                    if (board[m.target_col][m.target_row]!=EMPTY) return false;
                    promotionMove(gp,m);
                    updateMaterialScorePromotion((PawnPromotionMovement) m);
                    lastMoveAlgebraicNotation=Board.getAlgebraicPositionFromSquare(m.target_col, m.target_row)
                            +"="+Board.getAlgebraicPieceNotation(board[m.target_col][m.target_row]);
                }
                else if (m.movement_type==Movement.PAWN_PROMOTION_CAPTURE)
                {
                    //any move we clear enPassant unless it is a new enPassant
                    enPassant = Board.NO_enPassant;
                    isCapture=true; //set to remove capture piece later
                    if (!isCapture(m)) return false; //this was supposed to be a capture but there is no piece to capture
                    promotionMove(gp,m);
                    updateMaterialScorePromotion((PawnPromotionMovement) m);
                    lastMoveAlgebraicNotation=Board.getAlgebraicPositionFromSquare(m.orig_col, m.orig_row).substring(0,1)
                            +"x"+Board.getAlgebraicPositionFromSquare(m.target_col, m.target_row)
                            +"="+Board.getAlgebraicPieceNotation(board[m.target_col][m.target_row]);
                }
                else //normal pawn movement
                {
                    if (board[m.target_col][m.target_row]!=EMPTY) return false;
                    if (turn==Board.WHITE && m.orig_row==Board.r2 && m.target_row==Board.r4)
                    {
                        if (board[m.target_col][Board.r3]!=EMPTY) return false; //path for 2-ahead pawn move is not empty
                        enPassant.col=m.target_col;
                        enPassant.row=m.target_row;                        
                    }
                    else if (turn==Board.BLACK && m.orig_row==Board.r7 && m.target_row==Board.r5)
                    {
                        if (board[m.target_col][Board.r6]!=EMPTY) return false; //path for 2-ahead pawn move is not empty
                        enPassant.col=m.target_col;
                        enPassant.row=m.target_row;                        
                    }
                    else
                    {
                        //any move we clear enPassant unless it is a new enPassant
                        enPassant = Board.NO_enPassant;
                    }
                    move(gp, m);                    
                    lastMoveAlgebraicNotation=Board.getAlgebraicPositionFromSquare(m.target_col, m.target_row);
                }
            }
            else
            {
                //any move we clear enPassant unless it is a new enPassant
                enPassant = Board.NO_enPassant;
                
                isCapture=isCapture(m);
                if (m.movement_type==Movement.NORMAL)
                {
                    if (!checkPath(gp,m)) return false;
                    lastMoveAlgebraicNotation=Board.getAlgebraicPieceNotation(board[m.orig_col][m.orig_row])
                            + (isCapture ? "x" : "")
                            +Board.getAlgebraicPositionFromSquare(m.target_col, m.target_row);
                    move(gp, m);
                    
                    //castler flags
                    //we never set back the right!! We set false for the right if it is the case
                    if (turn==Board.WHITE && (canWhiteCastleShort || canWhiteCastleLong)) 
                    //if still right to castle
                    {
                        if (gp.piece==Board.WKING) //king move, lose castle right
                        {
                            canWhiteCastleShort=false;
                            canWhiteCastleLong=false;
                        }
                        else if (gp.piece==Board.WROCK) //rock move, from original position
                        {
                            if (m.orig_col==Board.A && m.orig_row==Board.r1)
                            {
                                canWhiteCastleLong=false;                            
                            }
                            else if (m.orig_col==Board.H && m.orig_row==Board.r1)
                            {
                                canWhiteCastleShort=false;                            
                            }
                        }
                    }
                    else //same for Black
                    {
                        if (gp.piece==Board.BKING) //king move, lose castle right
                        {
                            canBlackCastleShort=false;
                            canBlackCastleLong=false;
                        }
                        else if (gp.piece==Board.BROCK) //rock move, from original position
                        {
                            if (m.orig_col==Board.A && m.orig_row==Board.r8)
                            {
                                canBlackCastleLong=false;                            
                            }
                            else if (m.orig_col==Board.H && m.orig_row==Board.r8)
                            {
                                canBlackCastleShort=false;                            
                            }
                        }                    
                    }
                }
                else if (m.movement_type==Movement.CASTLE)
                {
                    if (isCheck) return false; //we can not castle in check
                    CastleMovement cm= (CastleMovement) m;
                    if (turn==Board.WHITE)
                    {
                        if (cm.longCastle && !canWhiteCastleLong) return false;
                        if (!cm.longCastle && !canWhiteCastleShort) return false;
                        if (!checkCastlePath(gp,cm.longCastle)) return false;
                        //check rock is there!
                        if (board[cm.rockMovement.orig_col][cm.rockMovement.orig_row]!=Board.WROCK) return false;
                        move(gp,m);
                        GamePiece theCastleRock=getPiece(getPosition(cm.rockMovement.orig_col,cm.rockMovement.orig_row));
                        /*
                        GamePiece theCastleRock=null;
                        Iterator<GamePiece> gpit=boardWhitePieces.iterator();
                        boolean found=false;
                        while (gpit.hasNext() && !found)
                        {
                            theCastleRock=gpit.next();
                            if (theCastleRock.pos.col == cm.rockMovement.orig_col && theCastleRock.pos.row == cm.rockMovement.orig_row )
                            {
                                found=true;
                            }
                        }
                        if (!found) throw new IllegalArgumentException("Invalid Move");    
                        */
                        move(theCastleRock, cm.rockMovement);
                        lastMoveAlgebraicNotation=cm.longCastle ? "0-0-0" : "0-0";
                        //once we castle no other castle possible
                        canWhiteCastleShort=false;
                        canWhiteCastleLong=false;
                    }
                    else //same for black
                    {
                        if (cm.longCastle && !canBlackCastleLong) return false;
                        if (!cm.longCastle && !canBlackCastleShort) return false;
                        if (!checkCastlePath(gp, cm.longCastle)) return false;
                        if (board[cm.rockMovement.orig_col][cm.rockMovement.orig_row]!=Board.WROCK) return false;
                        move(gp,m);
                        GamePiece theCastleRock=getPiece(getPosition(cm.rockMovement.orig_col,cm.rockMovement.orig_row));
                        /*        
                        theCastleRock=null;
                        Iterator<GamePiece> gpit=boardBlackPieces.iterator();
                        boolean found=false;
                        while (gpit.hasNext() && !found)
                        {
                            theCastleRock=gpit.next();
                            if (theCastleRock.pos.col == cm.rockMovement.orig_col && theCastleRock.pos.row == cm.rockMovement.orig_row )
                            {
                                found=true;
                            }
                        }
                        if (!found) throw new IllegalArgumentException("Invalid Move");    
                        */
                        move(theCastleRock, cm.rockMovement);
                        canWhiteCastleShort=false;
                        canWhiteCastleLong=false;
                    }
                    lastMoveAlgebraicNotation=cm.longCastle ? "0-0-0" : "0-0";                        
                }
            }
            if (isCapture)
            {
                //remove captured piece
                removeCapturedPiece(m); //search opponent piece in target and remove from opponent piece list
                reset50Movesrule=true;
                reset3MovesRule=true;
            }
            
            if (lookForCheck)
            //we do not look for check if recursively we are calling chessMove from inCheck or isCheck itself!
            {
                if (inCheck(false)) return false; //if we are in check after the move, is not valid
                isCheck=isCheck(); // we set the check flag, is the opponents in check after this move?
            }
            
            if (turn==Board.BLACK) moves++; //full moves incremented after blacks move
            if (turn==Board.WHITE) turn=Board.BLACK; else turn=Board.WHITE;
            //here we have a valid move, so we update the 50MovesRule counter
            if (reset50Movesrule) halfMove50rule=0; else halfMove50rule++;
            return true;
        }
        
        public boolean chessMove(String algebraicMove)
        //PENDING
        {
            String pieceSt=algebraicMove.substring(0,1);
            int apiece=Board.EMPTY;
            boolean isCastle=false;
            if (pieceSt.equals("R"))
            {
                if (turn==Board.WHITE) apiece=Board.WROCK; else apiece=Board.BROCK;
            }
            else 
            if (pieceSt.equals("B"))
            {
                if (turn==Board.WHITE) apiece=Board.WBISHOP; else apiece=Board.BBISHOP;
            }
            else 
            if (pieceSt.equals("N"))
            {
                if (turn==Board.WHITE) apiece=Board.WKNIGHT; else apiece=Board.BKNIGHT;
            }
            else 
            if (pieceSt.equals("Q"))
            {
                if (turn==Board.WHITE) apiece=Board.WQUEEN; else apiece=Board.BQUEEN;
            }
            else 
            if (pieceSt.equals("K"))
            {
                if (turn==Board.WHITE) apiece=Board.WKING; else apiece=Board.BKING;
            }
            else if (pieceSt.equals("O")) 
            {
                if (turn==Board.WHITE) apiece=Board.WKING; else apiece=Board.BKING;
                isCastle=true;
            }
            else if (turn==Board.WHITE) apiece=Board.WPAWN; else apiece=Board.BPAWN;
            
            return false;

        }
        
        boolean checkPath(GamePiece gp, Movement m)
        {
            //assume we check a path for a potential movement, so it is from a board geometry perspective valid
            int col_offset=0;
            int row_offset=0;
            int col=m.orig_col;
            int row=m.orig_row;
            switch(gp.piece)
            {
                case Board.WKING:
                case Board.BKING:
                case Board.WKNIGHT:
                case Board.BKNIGHT:
                case Board.WPAWN:
                case Board.BPAWN:
                    return true;
                case Board.BBISHOP:
                case Board.WBISHOP:
                case Board.BROCK:
                case Board.WROCK:
                case Board.WQUEEN, Board.BQUEEN:
                    if (m.orig_col > m.target_col) 
                    {
                        col_offset=-1;
                    }
                    else if (m.orig_col < m.target_col)
                    {
                        col_offset=1;                 
                    }
                    if (m.orig_row > m.target_row) 
                    {
                        row_offset=-1;
                    }
                    else if (m.orig_row < m.target_row)
                    {
                        row_offset=1;                 
                    }
                    
                    col=col+col_offset;
                    row=row+row_offset;
                    while (col!=m.target_col || row != m.target_row)
                    {
                        if (board[col][row]!=Board.EMPTY) return false;
                        col=col+col_offset;
                        row=row+row_offset;
                    }
                    break;
            }
            return true;
        }
        
        boolean checkCastlePath(GamePiece gp, boolean longc)
        //maybe we can remove gp as argument in move? not in general ... we may think so here as castle implies king!
        {
            if (turn==Board.WHITE)
            {
                if (longc)
                {
                    //c1,d1 for king, b1 for rock
                    if (board[Board.B][Board.r1]!=EMPTY) return false;
                    if (board[Board.C][Board.r1]!=EMPTY) return false;
                    if (board[Board.D][Board.r1]!=EMPTY) return false;
                    //d1 not in check! 
                    //orig not in check verified before this method, target not in check, always verified for any move
                    return this.copy().chessMove(gp, new Movement(Board.E, Board.r1, Board.D, Board.r1 ), true);
                    //false if not valid! after all previous checks it is only if in check!
                }
                else
                {
                    //f1, g1 king, g1 rock
                    if (board[Board.F][Board.r1]!=EMPTY) return false;
                    if (board[Board.G][Board.r1]!=EMPTY) return false;   
                    //f1 not in check
                    //orig not in check verified before this method, target not in check, always cverified for any move
                    return this.copy().chessMove(gp, new Movement(Board.E, Board.r1, Board.F, Board.r1 ), true);
                    //false if not valid! after all previous checks it is only if in check!
                }
            }
            else //same black
            {
                if (longc)
                {
                    if (board[Board.B][Board.r8]!=EMPTY) return false;
                    if (board[Board.C][Board.r8]!=EMPTY) return false;
                    if (board[Board.D][Board.r8]!=EMPTY) return false;
                    return this.copy().chessMove(gp, new Movement(Board.E, Board.r8, Board.D, Board.r8 ), true);
                }
                else
                {
                    if (board[Board.F][Board.r8]!=EMPTY) return false;
                    if (board[Board.G][Board.r8]!=EMPTY) return false;                
                    return this.copy().chessMove(gp, new Movement(Board.E, Board.r8, Board.F, Board.r8 ), true);
                }
            }
        }
        
        public boolean isCheck()
        //after a move, isCheck() verifies if opponents king is in check
        {
            return inCheck(true);
        }
        
        public boolean inCheck(boolean opponent)
        //after our potential move, we check if our king is in check (not a valid move)
        //or if opponent is true, we check it this move is check, his king is in check
        {
            //generate all validMoves for my opponent and see if one of them is the opponent capturing my king??
            Board ab=copy();
            if (!opponent)
            {
                if (turn==Board.WHITE) ab.turn=Board.BLACK;
                else ab.turn=Board.WHITE;
            }
            //we work in a copy of the board where move is already in place and turn is changed, so next is opponents move
            //but if it is not valid, inCheck, then we did not change the Board

            ArrayList<Movement> tempal;
            Iterator<GamePiece> it;
            ArrayList<GamePiece> gpl;
            ArrayList<GamePiece> ogpl; //opponents pieces, to search for king
            Position kpp = null; //king position
            int kingPiece;
            GamePiece ap, kp;
            Iterator<Movement> mit;
            Movement aMove;
            Board copyb;

            //get all pieces in the board for the color of the player in his turn
            //get all movements for each piece
            if (ab.turn==Board.WHITE)
            {
                gpl=ab.boardWhitePieces;
                ogpl=ab.boardBlackPieces;
                //kingPiece=Board.BKING;
                kpp=bkp;
                
            }
            else
            {
                gpl=ab.boardBlackPieces;            
                ogpl=ab.boardWhitePieces;
                //kingPiece=Board.WKING;
                kpp=wkp;
            }
            
            /*
            it=ogpl.iterator();
            while (it.hasNext())
            {
                kp=it.next();
                if (kp.piece == kingPiece)
                {
                    kpp=kp.pos;
                }
            }
            */

            it=gpl.iterator();
            while (it.hasNext())
            {
                ap=it.next();
                tempal=ap.getPotentialMovements();
                mit=tempal.iterator();
                while (mit.hasNext())
                {
                    aMove=mit.next();
                    if (aMove.target_col == kpp.col && aMove.target_row == kpp.row)
                    {
                        copyb=ab.copy();
                        if (copyb.chessMove(ap,aMove, false))
                        {
                            return true;
                        }
                    }
                }
            }             
            return false;
        }
        
        public Board copy()
        {
            Board copiedBoard=new Board(false);

            copiedBoard.board=new int[8][8];
            for (int i=A; i<=H; i++)
                for (int j=r1; j<=r8; j++)
                    copiedBoard.board[i][j]=board[i][j];



            //copiedBoard.enPassant=new Position(enPassant.col,enPassant.row);
            copiedBoard.enPassant=enPassant;
            copiedBoard.canWhiteCastleShort=canWhiteCastleShort;
            copiedBoard.canWhiteCastleLong=canWhiteCastleLong;
            copiedBoard.canBlackCastleShort=canBlackCastleShort;
            copiedBoard.canBlackCastleLong=canBlackCastleLong;

            copiedBoard.turn=turn;
            copiedBoard.moves=moves;
            copiedBoard.halfMove50rule=halfMove50rule;

            copiedBoard.boardWhitePieces=new ArrayList<GamePiece>();
            copiedBoard.boardBlackPieces=new ArrayList<GamePiece>();

            copiedBoard.getPieces();
            //init lists for easy computing

            copiedBoard.isCheck=isCheck;
            
            copiedBoard.wkp=wkp;
            copiedBoard.bkp=bkp;
            
            return copiedBoard;
            
        }
        
        public void  initFromBoard(Board ab)
        {

            for (int i=A; i<=H; i++)
                for (int j=r1; j<=r8; j++)
                    board[i][j]=ab.board[i][j];



            enPassant=ab.enPassant;
            canWhiteCastleShort=ab.canWhiteCastleShort;
            canWhiteCastleLong=ab.canWhiteCastleLong;
            canBlackCastleShort=ab.canBlackCastleShort;
            canBlackCastleLong=ab.canBlackCastleLong;

            turn=ab.turn;
            moves=ab.moves;
            halfMove50rule=ab.halfMove50rule;

            boardWhitePieces=new ArrayList<GamePiece>();
            boardBlackPieces=new ArrayList<GamePiece>();

            getPieces();
            //init lists for easy computing

            isCheck=ab.isCheck;
            
            lastMove=ab.lastMove;
            
            wkp=ab.wkp;
            bkp=ab.bkp;
            
            materialScored=ab.materialScored;
            materialScore=ab.materialScore;

        }
        
        //utility to introduce movments manually in tests
        public GamePiece getPiece(Position pos)
        {
            int ap=board[pos.col][pos.row];
            ArrayList<GamePiece> al=null;
            Iterator<GamePiece> it=null;
            GamePiece gp=null;
            if (turn==Board.WHITE)
            {
               al=boardWhitePieces;
            }
            else
            {
                al=boardBlackPieces;
            }
            it=al.iterator();
            while(it.hasNext())
            {
                gp=it.next();
                if (gp.piece == ap && gp.pos.col == pos.col && gp.pos.row == pos.row)
                    return gp;
            }
            return gp;
        }

    protected boolean materialScored=false; 
    // we calculate materialScore once, and set materialScored to true, 
    // then we change materialScore every move
    // it is the pieces material white minus black
    public int materialScore=0;
    
    public int evaluateMaterial()
    {
        if (!materialScored)
        {
            Iterator<GamePiece> it;
            int score=0;
            it=boardWhitePieces.iterator();
            while (it.hasNext())
            {
                switch(it.next().piece)
                {
                    case WQUEEN: 
                    {
                        score=score+9;
                        break;
                    }
                    case WROCK: 
                    {
                        score=score+5;
                        break;
                    }
                    case WBISHOP: 
                    {
                        score=score+3;
                        break;
                    }
                    case WKNIGHT: 
                    {
                        score=score+3;
                        break;
                    }
                    case WPAWN: 
                    {
                        score=score+1;
                        break;
                    }
                }
            }
            it=boardBlackPieces.iterator();
            while (it.hasNext())
            {
                switch(it.next().piece)
                {
                    case BQUEEN: 
                    {
                        score=score-9;
                        break;
                    }
                    case BROCK: 
                    {
                        score=score-5;
                        break;
                    }
                    case BBISHOP: 
                    {
                        score=score-3;
                        break;
                    }
                    case BKNIGHT: 
                    {
                        score=score-3;
                        break;
                    }
                    case BPAWN: 
                    {
                        score=score-1;
                        break;
                    }
                }
            }
            materialScored=true;
            materialScore=score;
        }
        return materialScore;

    }
            
    public void updateMaterialScorePromotion(PawnPromotionMovement m)
    {
        //we need to update material score
        //+/- promotion piece - 1 (pawn value)
        switch (m.promotionPiece)
        {
            case WQUEEN: 
                materialScore=materialScore+8; //9-1
                break;
            case WROCK:
                materialScore=materialScore+4;
                break;
            case WBISHOP:
                materialScore=materialScore+2;
                break;
            case WKNIGHT:
                materialScore=materialScore+2;
                break;
            case BQUEEN: 
                materialScore=materialScore-8; //-9+1
                break;
            case BROCK:
                materialScore=materialScore-4;
                break;
            case BBISHOP:
                materialScore=materialScore-2;
                break;
            case BKNIGHT:
                materialScore=materialScore-2;
                break;                
        }
        //if a PawnCapturePromotionMovement the captured piece score is rested as any capture
    }

    public int getHashCode() //to compare positions in 3moves repetition rule
    {
        for (int i=A; i<=H;i++)
        {
            hashCodes[i]=Arrays.hashCode(board[i]);
        }
        //System.out.println(Arrays.hashCode(hashCodes));
        return Arrays.hashCode(hashCodes);
    }
    
    public boolean reset3MovesRule=false;
    
    protected int[] hashCodes=new int[8];
}

// we start from a valid position isValid() in Board

