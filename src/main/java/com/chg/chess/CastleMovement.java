/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.chg.chess;

/**
 *
 * @author carlosherrero
 */
public class CastleMovement extends Movement {
    public CastleMovement(int orig_col, int orig_row, int target_col, int target_row, Movement rMovement, boolean lCastle)
    {
        super(orig_col,orig_row,target_col,target_row);
        movement_type=Movement.CASTLE;
        rockMovement=rMovement;
        longCastle=lCastle;
        
    }

    //implicit the rock movement
    public Movement rockMovement;
    public boolean longCastle;
}
