/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.chg.chess;

/**
 *
 * @author carlosherrero
 */
public class PawnPromotionMovement extends Movement {
    int promotionPiece;
    public PawnPromotionMovement(int orig_col, int orig_row, int target_col, int target_row, int targetPiece)
    {
        super(orig_col,orig_row,target_col,target_row);
        movement_type=Movement.PAWN_PROMOTION;
        if (target_row == Board.r8) // white
        {
            switch(targetPiece)
            {
                case Board.WROCK:
                case Board.WQUEEN:
                case Board.WBISHOP:
                case Board.WKNIGHT:
                    break;
                case Board.BROCK:
                case Board.BQUEEN:
                case Board.BBISHOP:
                case Board.BKNIGHT:
                case Board.WKING:
                case Board.BKING:
                case Board.WPAWN:
                case Board.BPAWN:
                    throw new IllegalArgumentException("Invalid pawn promotion");
            }           
        }
        else if (target_row == Board.r1) // black
        {
            switch(targetPiece)
            {
                case Board.BROCK:
                case Board.BQUEEN:
                case Board.BBISHOP:
                case Board.BKNIGHT:
                    break;
                case Board.WROCK:
                case Board.WQUEEN:
                case Board.WBISHOP:
                case Board.WKNIGHT:
                case Board.WKING:
                case Board.BKING:
                case Board.WPAWN:
                case Board.BPAWN:
                    throw new IllegalArgumentException("Invalid pawn promotion");
            }           
        }
        else throw new IllegalArgumentException("Invalid pawn promotion");
        promotionPiece=targetPiece;
    }

}
