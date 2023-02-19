/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.chg.chess;

/**
 *
 * @author carlosherrero
 */
public class PawnCapturePromotionMovement extends PawnPromotionMovement {
    public PawnCapturePromotionMovement (int i, int j, int bi, int bj, int prom)
    {
        super(i,j,bi,bj,prom);
        movement_type=Movement.PAWN_PROMOTION_CAPTURE;
    }
}
