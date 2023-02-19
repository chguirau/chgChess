/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.chg.chess;

/**
 *
 * @author carlosherrero
 */
public class Movement {
    public int orig_col;
    public int orig_row;
    public int target_col;
    public int target_row;
    
    public static final int NORMAL=0;
    public static final int PAWN_CAPTURE=1;
    public static final int PAWN_PROMOTION=2;
    public static final int PAWN_PROMOTION_CAPTURE=3;
    public static final int CASTLE=4;
    
    
    public int movement_type=Movement.NORMAL;
    public Movement (int ai,int aj, int bi, int bj)
    {
        orig_col=ai;
        orig_row=aj;
        target_col=bi;
        target_row=bj;
    }

    public Movement (Position orig, Position target)
    {
        orig_col=orig.col;
        orig_row=orig.row;
        target_col=target.col;
        target_row=target.row;
    }
    
}
