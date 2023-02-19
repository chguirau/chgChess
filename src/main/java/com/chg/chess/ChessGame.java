/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.chg.chess;
import java.util.*;
import com.chg.view.*;
import java.awt.*;
import java.awt.event.*;  
import javax.swing.Timer;
import javax.swing.SwingWorker;


/**
 *
 * @author carlosherrero
 */
public class ChessGame implements MouseListener, ActionListener {

    public static int getNumberOfMovements (Board aBoard, int depth)
    //first entry in recursive method, we trace
    {
        int count=1;
        ArrayList<Board> al=null;
        Iterator<Board> it=null;
        Board bb=null;
        if (depth ==0)
        {
            System.out.println(aBoard.lastMoveAlgebraicNotation);
            return count;
        }
        else
        {
            count=0;
            int inc=0;
            al=aBoard.getAllValidMovements();
            it=al.iterator();            
            System.out.println("***** Depth "+depth+" Movements "+ al.size());
            while (it.hasNext())
            {
                bb=it.next();
                System.out.println(bb.lastMoveAlgebraicNotation);
                inc=getNumberOfMovementsIterate(bb, depth-1);
                count+=inc;
                System.out.println("Count "+inc);
            }
        }
        return count;
    }

    public static int getNumberOfMovementsIterate (Board aBoard, int depth)
    {
        int count=1;
        ArrayList<Board> al=null;
        Iterator<Board> it=null;
        if (depth ==0)
        {
            return count;
        }
        else
        {
            count=0;
            al=aBoard.getAllValidMovements();
            it=al.iterator();            
            while (it.hasNext())
            {
                count+=getNumberOfMovementsIterate(it.next(), depth-1);
            }
        }

        
        return count;
    }
    
    public ChessGame()
    {
        state=ChessGame.stateInit;
        doProcess();
    }
    
    public EvaluatedBoard activeBoard;
    public int gameMode=ChessGame.TwoPlayers;
    public static final int TwoPlayers=0; //2 human players, we just allow valid moves in the game, and check for game end
    public static final int OnePlayerWhite=1; //1 player is computer, we allow human player to move, and automatically move for computer after alfabeta
    public static final int OnePlayerBlack=2; //1 player is computer, we allow human player to move, and automatically move for computer after alfabeta
    public static final int Computer=3; //both players computer, we automatically move for both
    
    ChessGameView gui;
    
    //chessGame is a stateMachine
    //different states in doProcess
    
    public static final int stateInit=0;
    public static final int stateWaitSelectPieceToMove=1;
    public static final int stateWaitSelectTargetSquareToMove=2;
    public static final int stateCheckNextPlayer=3;
    public static final int stateWait=4; //wait after end game to click on new game;
    public static final int stateTest = 5;
        
    
    public int state=ChessGame.stateInit;
    Position orig;
    
    public ChessGame(ChessGameView cgv)
    {
        gui=cgv;
    }
    
    public void doProcess() 
    // we process any action in GUI that implies a change in current game
    {
        switch(state)
        //select GameMode and init Board
        {
            case ChessGame.stateInit:
                gui.DialogNewGame(); //defines 1 player, 2 players, computer and initial position
                state=ChessGame.stateCheckNextPlayer;
                gameMode=gui.selectedGameMode;
                activeBoard=new EvaluatedBoard(true); //the board as it is shown in GUI
                if (!gui.selectedChessPosition.equals(""))
                {
                    activeBoard.initFromFEN(gui.selectedChessPosition, true);
                    //moveList="FEN: \n"+gui.selectedChessPosition+"\n";
                }
                moveList="Moves: \n"+ activeBoard.moves+". ";
                gui.moveList=moveList;
                //boardHashList.clear();
                //gui.reopen();
                gui.refresh(activeBoard, null);
                doProcess();
                break;
            case ChessGame.stateCheckNextPlayer:
                activeBoard.evaluate();
                if (isEndGame())
                {
                    //System.out.println("End of game");
                    state=ChessGame.stateWait;
                    gui.refresh(activeBoard, null);
                    doProcess();
                }
                else
                {
                    if (gameMode==ChessGame.TwoPlayers)
                    {
                        state=ChessGame.stateWaitSelectPieceToMove;
                    }
                    else if (gameMode==ChessGame.OnePlayerBlack && activeBoard.turn==Board.BLACK)
                    {
                        state=ChessGame.stateWaitSelectPieceToMove;                    
                    }
                    else if (gameMode==ChessGame.OnePlayerWhite && activeBoard.turn==Board.WHITE)
                    {
                        state=ChessGame.stateWaitSelectPieceToMove;                    
                    }
                    else //computer turn
                    {                    
                        gui.refresh(activeBoard,null);
                        
                        SwingWorker alphabetaWorker=new SwingWorker<EvaluatedBoard, Object>() {
                            @Override
                            public EvaluatedBoard doInBackground() {
                                return alphabeta(activeBoard,5,-100000,100000, activeBoard.turn==Board.WHITE);
                            }

                            @Override
                            protected void done() {
                                try {
                                    Movement m=get().nextMove;
                                    GamePiece gp=activeBoard.getPiece(Board.getPosition(m.orig_col, m.orig_row));
                                    activeBoard.chessMove(gp, m, true);
                                    //we need to reset flag evaluated3MovesRepetition as it is set during alphabeta, but we have moved!
                                    //we need to include the board in the hashlist
                                    activeBoard.evaluated3MovesRepetition=false;
                                    //same for evaluated
                                    //activeBoard.evaluated=false;
                                    //it is not needed
                                    System.out.println(activeBoard.lastMoveAlgebraicNotation);
                                    moveList=moveList+activeBoard.lastMoveAlgebraicNotation+(activeBoard.turn==Board.BLACK? "; " : "\n"+(activeBoard.moves) + ". " ); 
                                    gui.moveList=moveList;

                                    gui.refresh(activeBoard, null);
                                    
                                    int delay = 100; //milliseconds, we want to move human move, then computer, we do not wnat to see both together
                                    ActionListener taskPerformer = new ActionListener() {
                                        public void actionPerformed(ActionEvent evt) {
                                            doProcess(); //after a move, we proceed to next turn, if any
                                        }
                                    };
                                    Timer t=new Timer(delay, taskPerformer);
                                    t.setRepeats(false);
                                    t.start();
                                    
                                } catch (Exception ignore) {
                                }
                            }
                        };

                        alphabetaWorker.execute();

                    }
                }
                break;
            case ChessGame.stateWait:
                gui.refresh(activeBoard, null);
                gui.doConfirm();
                //System.out.println("Status Wait");
                gui.doClose();
                state=ChessGame.stateInit;
                doProcess();
                break;                
        }
        
    }
    
    
    public boolean isEndGame()
    {
        if (activeBoard.result==EvaluatedBoard.INPROGRESS) return false;
        if (activeBoard.turn==Board.WHITE && activeBoard.result==EvaluatedBoard.BLACKWINS) return true;
        if (activeBoard.turn==Board.BLACK && activeBoard.result==EvaluatedBoard.WHITEWINS) return true;
        if (activeBoard.result==EvaluatedBoard.DRAW) return true;
        return false;
    }
    
    public void mouseClicked(MouseEvent e)
    //Invoked when the mouse button has been clicked (pressed and released) on a component.
    {
        Position p=gui.isSquare(e);
        if (p!=null) //valid board click
        {
            if (state==stateWaitSelectPieceToMove)
            {
                if (activeBoard.turn==Board.WHITE)
                {
                    if (Board.isWhitePiece(activeBoard.board[p.col][p.row]))
                    {
                        orig=p;
                        state=stateWaitSelectTargetSquareToMove;
                        //pending highlite selected square
                        gui.refresh(activeBoard, orig);
                    }
                }
                else
                {
                    if (Board.isBlackPiece(activeBoard.board[p.col][p.row]))
                    {
                        orig=p;
                        state=stateWaitSelectTargetSquareToMove;
                        //pending highlite selected square
                        gui.refresh(activeBoard, orig);
                    }                
                }
            }
            else if (state==stateWaitSelectTargetSquareToMove)
            {
                ArrayList<GamePiece> gpl, ogpl;
                if (activeBoard.turn==Board.WHITE)
                {
                    gpl=activeBoard.boardWhitePieces;
                    ogpl=activeBoard.boardBlackPieces;
                }
                else
                {
                    gpl=activeBoard.boardBlackPieces;
                    ogpl=activeBoard.boardWhitePieces;
                }
                Iterator<GamePiece> it=gpl.iterator();
                GamePiece gp;
                boolean found=false;
                boolean movementFound=false;
                while (it.hasNext() && !found)
                {
                    gp=it.next();
                    if (gp.pos==orig)
                    //pending check we always use static final positions in board
                    {
                        found=true;
                        ArrayList<Movement> ml=gp.getPotentialMovements();
                        Iterator<Movement> mi=ml.iterator();
                        Movement m;
                        movementFound=false;
                        while (mi.hasNext() && !movementFound)
                        {
                            m=mi.next();
                            if (m.target_col==p.col && m.target_row==p.row)
                            {
                                movementFound=true;
                                if (activeBoard.copy().chessMove(gp, m, true))
                                {
                                    if (m.movement_type == Movement.PAWN_PROMOTION || m.movement_type==Movement.PAWN_PROMOTION_CAPTURE)
                                    {
                                        //special case, potentially there is a list of 4 valid movements
                                        //any of them valid (if any was invalid, all were invalid, so even if we checked only first of them, we do not enter here)
                                        int prom;
                                        PawnPromotionMovement ppm=null;
                                        PawnCapturePromotionMovement pcpm=null;
                                        prom=gui.getPromotionPiece(activeBoard.turn);
                                        if (m.movement_type== Movement.PAWN_PROMOTION)
                                        {
                                            ppm=(PawnPromotionMovement)m;
                                            ppm.promotionPiece=prom;
                                            m=ppm;
                                        }
                                        else
                                        {
                                            pcpm=(PawnCapturePromotionMovement)m;
                                            pcpm.promotionPiece=prom;
                                            m=pcpm;
                                        }
                                    }
                                    activeBoard.chessMove(gp,m,true);
                                    activeBoard.evaluated=false;
                                    activeBoard.evaluated3MovesRepetition=false;

                                    System.out.println(activeBoard.lastMoveAlgebraicNotation);
                                    moveList=moveList+activeBoard.lastMoveAlgebraicNotation+(activeBoard.turn==Board.BLACK? "; " : "\n"+(activeBoard.moves) + ". " ); 
                                    gui.moveList=moveList;
                                    state=ChessGame.stateCheckNextPlayer;
                                    orig=null;
                                    gui.refresh(activeBoard, orig);

                                    int delay = 100; //milliseconds, we want to move human move, then computer, we do not wnat to see both together
                                    ActionListener taskPerformer = new ActionListener() {
                                        public void actionPerformed(ActionEvent evt) {
                                            doProcess(); //after a move, we proceed to next turn, if any
                                        }
                                    };
                                    Timer t=new Timer(delay, taskPerformer);
                                    t.setRepeats(false);
                                    t.start();
                                }                                                    
                            }
                        }                        
                    }
                }
                if (!found) throw new IllegalArgumentException("Should be a piece in the list");
                //we come from a click in turn's piece, now it is not in the list?
                if (!movementFound || (movementFound && state==stateWaitSelectTargetSquareToMove)) 
                {
                    if (movementFound && state==stateWaitSelectTargetSquareToMove) 
                        System.out.println(Board.getAlgebraicPositionFromSquare(p.col, p.row)+ " is not a valid chess move");
                    //we keep in the same state stateWaitSelectTargetSquareToMove
                    //System.out to be remove
                    if (!movementFound) 
                        System.out.println(Board.getAlgebraicPositionFromSquare(p.col, p.row)+ " is not a possible move");
                    //we keep in the same state stateWaitSelectTargetSquareToMove
                    //System.out to be remove
                    //unless we have clicked in another of our pieces, we change selection
                    if (activeBoard.turn==Board.WHITE)
                    {
                        if (Board.isWhitePiece(activeBoard.board[p.col][p.row]))
                        {
                            orig=p;
                            //highlite selected square
                            gui.refresh(activeBoard, orig);
                        }
                        //else we do nothing, we keep already selected piece
                    }
                    else
                    {
                        if (Board.isBlackPiece(activeBoard.board[p.col][p.row]))
                        {
                            orig=p;
                            //highlite selected square
                            gui.refresh(activeBoard, orig);
                        }                
                        //else we do nothing, we keep already selected piece
                    }
                    
                }
            }
        }
    }

    public void	mouseEntered(MouseEvent e)
    //Invoked when the mouse enters a component.
    {
    }
    
    public void	mouseExited(MouseEvent e)
    //Invoked when the mouse exits a component.
    {
    }
    
    public void	mousePressed(MouseEvent e)
    //Invoked when a mouse button has been pressed on a component.
    {
    }
    
    public void mouseReleased(MouseEvent e)
    //Invoked when a mouse button has been released on a component.
    {
    }
    
    
    public EvaluatedBoard alphabeta(EvaluatedBoard eb, int depth, int alphaScore, int betaScore, boolean isWhite)
    {
        int tempScore;
        Movement tempMove;
        ArrayList<Board> ebl;
        Iterator<Board> it;
        EvaluatedBoard iterativeeb;
        EvaluatedBoard neb;
        boolean hasChild;
        if (depth == 0)
        {
            eb.evaluate();
            eb.potentialScore=eb.score;
            //debug
            //System.out.println("AB - "+eb.potentialScore+" depth 0");
            return eb;
        }
        else
        {
            //first check if no possible next move because it is already draw
            //these are the cases to check
            //DRAW_INSUFICIENT_MATERIAL
            //DRAW_50MOVES_RULE
            //DRAW_3MOVES_REPETITION
            //rest of the cases MATE, STALEMATE are evaluated on no childs for getAllValidMovements (and it is the best way to check it)
            if (eb.evaluateDrawNoStaleMate())
            {
                eb.score=0;
                eb.potentialScore=eb.score;
                //debug
                //System.out.println("AB - "+eb.potentialScore+" evaluated draw no stale mate 0");
                return eb;
            }
            
            ebl=eb.getAllValidMovements();
            it=ebl.iterator();
            hasChild=it.hasNext();
            if (hasChild) 
            {
                if (isWhite)
                {
                    tempScore=-100001; //to distinguish with checkmate 100000
                    do
                    {
                        neb=new EvaluatedBoard(false);
                        neb.initFromBoard(it.next());
                        neb.set3MovesRepetitionList(eb);
                        iterativeeb=alphabeta(neb, depth-1, alphaScore, betaScore, !isWhite);
                        if (iterativeeb.potentialScore>tempScore)
                        {
                            tempScore=iterativeeb.potentialScore;
                            eb.nextMove=iterativeeb.lastMove;
                        }
                        //if (tempScore>betaScore) break; //hard cut
                        if (tempScore>=betaScore) break; //soft cut
                        if (tempScore>alphaScore) alphaScore=tempScore;
                    }
                    while(it.hasNext());
                }
                else
                {
                    tempScore=100001; //to distinguish with checkmate 100000
                    do
                    {
                        neb=new EvaluatedBoard(false);
                        neb.initFromBoard(it.next());
                        neb.set3MovesRepetitionList(eb);
                        iterativeeb=alphabeta(neb, depth-1, alphaScore, betaScore, !isWhite);
                        if (iterativeeb.potentialScore<tempScore) 
                        {
                            tempScore=iterativeeb.potentialScore;
                            eb.nextMove=iterativeeb.lastMove;
                        }
                        //if (tempScore<alphaScore) break; //hard cut
                        if (tempScore<=alphaScore) break; //soft cut
                        if (tempScore<betaScore) betaScore=tempScore;
                    }
                    while(it.hasNext());
                }
                eb.potentialScore=tempScore;
            }
            else //no child
            {
                eb.evaluate();
                eb.potentialScore=eb.score;
            }
                
            return eb;
        }
    }
    
    /* pseudocode
    
    if depth = 0 or node is a terminal node then
        return the heuristic value of node
    if maximizingPlayer then
        value := −∞
        for each child of node do
            value := max(value, alphabeta(child, depth − 1, α, β, FALSE))
            if value > β then
                break (* β cutoff *)
            α := max(α, value)
        return value
    else
        value := +∞
        for each child of node do
            value := min(value, alphabeta(child, depth − 1, α, β, TRUE))
            if value < α then
                break (* α cutoff *)
            β := min(β, value)
        return value    
    */
    
    public String moveList="";
        
    
    public void actionPerformed(ActionEvent e)
    //resign button
    {
     
        if (activeBoard.turn==Board.WHITE && (gameMode==TwoPlayers || gameMode==OnePlayerWhite)) 
        {
            activeBoard.result=EvaluatedBoard.BLACKWINS;
            activeBoard.detailResult=EvaluatedBoard.RESIGNS;   
            state=ChessGame.stateCheckNextPlayer;
            doProcess();
        }
        else if (activeBoard.turn==Board.BLACK && (gameMode==TwoPlayers || gameMode==OnePlayerBlack))
        {
            activeBoard.result=EvaluatedBoard.WHITEWINS;
            activeBoard.detailResult=EvaluatedBoard.RESIGNS;   
            state=ChessGame.stateCheckNextPlayer;
            doProcess();
        }
        //else do nothing, computer never resigns :)
        //in reality future feature computer will decide to resing in some conditions?
        
    }
    

}
