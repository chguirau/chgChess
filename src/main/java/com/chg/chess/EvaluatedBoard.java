/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.chg.chess;
import java.util.*;

/**
 *
 * @author carlosherrero
 */
public class EvaluatedBoard extends Board {
    
    public int result=EvaluatedBoard.INPROGRESS;
    public int detailResult=EvaluatedBoard.INPROGRESSD;
    //result
    public static final int INPROGRESS=0;
    public static final int DRAW=1;
    public static final int WHITEWINS=2;
    public static final int BLACKWINS=3;
    //detail result
    public static final int DRAW_INSUFICIENT_MATERIAL=0;
    public static final int DRAW_50MOVES_RULE=1;
    public static final int DRAW_STALEMATE=2;
    public static final int DRAW_3MOVES_REPETITION=3;
    public static final int DRAW_AGREED=4;    
    public static final int CHECKMATE=5;
    public static final int RESIGNS=6;
    public static final int INPROGRESSD=7;
    
    public int score; // minus inifinite is black wins, infinite white wins, 0 is equal
    public int potentialScore; //score after a depth search from this board
    Movement nextMove; //saved movement in a depth search from this board which caused best score (potantial score)
    
    public EvaluatedBoard()
    {
        super();
    }
    
    public EvaluatedBoard( boolean initialize)
    {
        super(initialize);
    }
        
    //we create EvaluatedBoards from original position or we will initialize afterwards with initXX methods
    //in any case, result is INPROGRESS until any evaluation is done
    //FEN creates an in progress position (we do assume 3moves repetition count is 0, FEN does not provide info)
    //Or we initialize from other board (IN PROGRESS, until we call evaluate method) or from original position
    
    public void evaluate()
    {
        if (evaluated) return;
        if (getAllValidMovements().size()==0)
        {
            if (isCheck)
            {
                if (turn==Board.WHITE)
                {
                    result=EvaluatedBoard.BLACKWINS;
                    detailResult=EvaluatedBoard.CHECKMATE;
                    score=-100000;
                }
                else
                {
                    result=EvaluatedBoard.WHITEWINS;
                    detailResult=EvaluatedBoard.CHECKMATE;
                    score=100000;                
                }
            }
            else
            {
                result=EvaluatedBoard.DRAW;
                detailResult=EvaluatedBoard.DRAW_STALEMATE;
                score=0;
            }
        }
        else if (halfMove50rule==50)
        {
            result=EvaluatedBoard.DRAW;
            detailResult=EvaluatedBoard.DRAW_50MOVES_RULE;
            score=0;            
        }
        else if (evaluate3MovesRepetition())
        {
            result=EvaluatedBoard.DRAW;
            detailResult=EvaluatedBoard.DRAW_3MOVES_REPETITION;
            score=0;                    
        }
        else
        {
            //check insuficient material
            boolean insuf=false;
            Iterator<GamePiece> it;
            if (boardWhitePieces.size()+boardBlackPieces.size() <= 4)
            {
                insuf=true;
                int bcount=0;
                int kcount=0;
                it=boardBlackPieces.iterator();
                while (it.hasNext() && insuf)
                {
                    switch(it.next().piece)
                    {
                        case BPAWN, BROCK, BQUEEN, BKING:
                            insuf=false;
                            break;
                        case BBISHOP:
                            bcount++;
                            break;
                        case BKNIGHT:
                            kcount++;
                            break;
                    }
                }
                if (insuf)
                {
                    insuf= !(bcount==2 || bcount==1 && kcount==1);
                }
                if (kcount==2)
                {
                    insuf=false; //FIDE says insuficient only if it is not possible to mate (to be revised)
                }
                
                int wbcount=0;
                int wkcount=0;
                if (insuf)
                {
                    it=boardWhitePieces.iterator();
                    while (it.hasNext() && insuf)
                    {
                        switch(it.next().piece)
                        {
                            case WPAWN, WROCK, WQUEEN, WKING:
                                insuf=false;
                                break;
                            case WBISHOP:
                                wbcount=bcount++;
                                break;
                            case WKNIGHT:
                                wkcount++;
                                break;
                        }
                    }
                    if (insuf)
                    {
                        insuf= !(wbcount==2 || wbcount==1 && wkcount==1);
                    }
                    if (wkcount==2)
                    {
                        insuf=false; //FIDE says insuficient only if it is not possible to mate (to be revised)
                    }
                }
                
            }
            if (insuf)
            {
                result=EvaluatedBoard.DRAW;
                detailResult=EvaluatedBoard.DRAW_INSUFICIENT_MATERIAL;
                score=0;
            }
            else
            {   
                result=EvaluatedBoard.INPROGRESS;
                detailResult=EvaluatedBoard.INPROGRESSD;
                
                if (materialScored)
                    score=materialScore;
                else
                {
                    score=evaluateMaterial();
                }
            }
        }
        //System.out.println("Evaluation "+score);
        evaluated=true;
    }
    
    public void  initFromBoard(EvaluatedBoard eb)
    {
        super.initFromBoard(eb);
        result=eb.result;
        detailResult=eb.detailResult;
        score=eb.score;
        evaluated=eb.evaluated;

    }

    public void set3MovesRepetitionList(EvaluatedBoard aeb)
    {
        boardHashList.putAll(aeb.boardHashList); 
    }

    
    
    protected boolean evaluated=false;
    
    public boolean evaluate3MovesRepetition()
    {
        if (evaluated3MovesRepetition)
            return is3MovesRepetition;
        //we only evaluate once for a board
        
        int hashcode=getHashCode();
        if (reset3MovesRule) //from Board chessMove we set the flag if pawn move or capture
        {
            boardHashList.clear();
            //boardHashList.put(Integer.valueOf(hashcode), Integer.valueOf(1)); //firt time we see this position
            boardHashList.put(hashcode, 1); //firt time we see this position
            is3MovesRepetition=false;
            evaluated3MovesRepetition=true;
            return false; // more efficient we do not check anything else
        }
        int count;
        if (boardHashList.get(hashcode)!=null)
        {
            count=boardHashList.get(hashcode);
            count ++;
            if (count==3)
            {
                is3MovesRepetition=true;
                evaluated3MovesRepetition=true;
                return true;
            }
            else
                boardHashList.replace(hashcode, count);
        }
        else
        {
            boardHashList.put(hashcode, 1); //firt time we see this position
        }
        is3MovesRepetition=false;
        evaluated3MovesRepetition=true;
        return false;
    }
    
    public HashMap<Integer, Integer> boardHashList=new HashMap<Integer,Integer>(); 
    //each entry is the hashCode for the board with number of repetitions;

    public boolean evaluateDrawNoStaleMate()
    //first check if no possible next move because it is already draw
    //these are the cases to check
    //DRAW_INSUFICIENT_MATERIAL
    //DRAW_50MOVES_RULE
    //DRAW_3MOVES_REPETITION
    //rest of the cases MATE, STALEMATE are evaluated on no childs for getAllValidMovements (and it is the best way to check it)
    //if (eb.halfMove50rule==50 || eb.isInsuficientMaterial() || eb.is3MovesRepetition())
    // set result, detailResult and score
    {
        evaluated=true;
        //we cached evaluation, in case we find it is draw, else we set it back to false
        
        if (halfMove50rule==50) 
        {
            result=EvaluatedBoard.DRAW;
            detailResult=EvaluatedBoard.DRAW_50MOVES_RULE;
            score=0;
            return true;
        }
        if (evaluate3MovesRepetition())
        {
            result=EvaluatedBoard.DRAW;
            detailResult=EvaluatedBoard.DRAW_3MOVES_REPETITION;
            score=0;
            return true;        
        }
        //check insuficient material
        boolean insuf=false;
        Iterator<GamePiece> it;
        if (boardWhitePieces.size()+boardBlackPieces.size() <= 4)
        {
            insuf=true;
            int bcount=0;
            int kcount=0;
            it=boardBlackPieces.iterator();
            while (it.hasNext() && insuf)
            {
                switch(it.next().piece)
                {
                    case BPAWN, BROCK, BQUEEN, BKING:
                        insuf=false;
                        break;
                    case BBISHOP:
                        bcount++;
                        break;
                    case BKNIGHT:
                        kcount++;
                        break;
                }
            }
            if (insuf)
            {
                insuf= !(bcount==2 || bcount==1 && kcount==1);
            }
            if (kcount==2)
            {
                insuf=false; //FIDE says insuficient only if it is not possible to mate (to be revised)
            }

            int wbcount=0;
            int wkcount=0;
            if (insuf)
            {
                it=boardWhitePieces.iterator();
                while (it.hasNext() && insuf)
                {
                    switch(it.next().piece)
                    {
                        case WPAWN, WROCK, WQUEEN, WKING:
                            insuf=false;
                            break;
                        case WBISHOP:
                            wbcount=bcount++;
                            break;
                        case WKNIGHT:
                            wkcount++;
                            break;
                    }
                }
                if (insuf)
                {
                    insuf= !(wbcount==2 || wbcount==1 && wkcount==1);
                }
                if (wkcount==2)
                {
                    insuf=false; //FIDE says insuficient only if it is not possible to mate (to be revised)
                }
            }
                
        }
        if (insuf)
        {
            result=EvaluatedBoard.DRAW;
            detailResult=EvaluatedBoard.DRAW_INSUFICIENT_MATERIAL;
            score=0;
            return true;
        }
        else
        {   
            evaluated=false;
            return false;
        }
    }
    
    public boolean is3MovesRepetition=false;
    public boolean evaluated3MovesRepetition=false;    
    //we do not need to copy these fields in initFromBoard (EvaluatedBoard) method, 
    //if we create a new EvaluatedBoard, we do it to show in gui, 
    //and these fields are only relevant to calculate result and detailedResult which are already copied
}
