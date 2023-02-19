/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.chg.chess;

/**
 *
 * @author carlosherrero
 */
public class PawnCaptureMovement  extends Movement {
    public PawnCaptureMovement(int orig_col, int orig_row, int target_col, int target_row)
    {
        super(orig_col,orig_row,target_col,target_row);
        movement_type=Movement.PAWN_CAPTURE;
    }
    
    public PawnCaptureMovement(Position orig, Position target)
    {
        super(orig, target);
        movement_type=Movement.PAWN_CAPTURE;
    }
}
