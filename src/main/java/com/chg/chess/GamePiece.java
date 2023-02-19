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
public class GamePiece {
    public Position pos;
    public int piece;
    public int color;
    public GamePiece(Position apos, int apiece)
    {
        //pos=apos; //No, error prune, we have to point to static pos
        pos=Board.getPosition (apos.col, apos.row);
        piece=apiece;
        switch(piece)
        {
            case Board.BBISHOP: 
            case Board.BKNIGHT: 
            case Board.BROCK: 
            case Board.BPAWN: 
            case Board.BQUEEN: 
            case Board.BKING: 
                color=Board.BLACK;
                break;
            case Board.WBISHOP: 
            case Board.WKNIGHT: 
            case Board.WROCK: 
            case Board.WPAWN: 
            case Board.WQUEEN: 
            case Board.WKING: 
                color=Board.WHITE;
                break;
            default:
                throw new IllegalArgumentException("no valid piece ");
        }
    }
    public ArrayList<Movement> getPotentialMovements()
    {   
        //we start with potential movements
        ArrayList<Movement> al=null;
        switch(piece)
        {
            case Board.BBISHOP: 
            case Board.WBISHOP: 
                al=PotentialMovements.potentialBishopMovements.get(pos);
                break;
            case Board.BKNIGHT: 
            case Board.WKNIGHT: 
                al=PotentialMovements.potentialKnightMovements.get(pos);
                break;
            case Board.BROCK: 
            case Board.WROCK: 
                al=PotentialMovements.potentialRockMovements.get(pos);
                break;
            case Board.BQUEEN: 
            case Board.WQUEEN: 
                al=PotentialMovements.potentialQueenMovements.get(pos);
                break;
            case Board.BKING: 
            case Board.WKING: 
                al=PotentialMovements.potentialKingMovements.get(pos);
                break;
            case Board.BPAWN: 
                al=PotentialMovements.potentialBlackPawnMovements.get(pos);
                break;
            case Board.WPAWN: 
                al=PotentialMovements.potentialWhitePawnMovements.get(pos);
                break;
        }
        return al;
    }
}
