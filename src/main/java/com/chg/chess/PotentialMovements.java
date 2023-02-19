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

//Potential movements depending on the piece, as of the geometry of the board, BUT without considering other pieces in the board
public class PotentialMovements {
    private static ArrayList getPotentialRockMovements( int acol, int arow )
    {
        ArrayList al=new ArrayList<Movement>();
        for (int i=acol-1; i>=Board.A; i--)
        {
            al.add(new Movement(acol, arow, i, arow));
        }
        for (int i=acol+1; i<=Board.H; i++)
        {
            al.add(new Movement(acol, arow, i, arow));
        }
        for (int j=arow-1; j>=Board.r1; j--)
        {
            al.add(new Movement(acol, arow, acol, j));
        }
        for (int j=arow+1; j<=Board.r8; j++)
        {
            al.add(new Movement(acol, arow, acol, j));
        }
        return al;
    }

    private static ArrayList getPotentialWhitePawnMovements( int acol, int arow )
    {
        //valid white pawn is not on r8, no need to check limit
        //but we check if it is on r7 as it is a diffetent pawn move, promotion
        ArrayList al=new ArrayList<Movement>();
        if (arow<Board.r7) al.add(new Movement(acol, arow, acol, arow+1));
        if (arow==Board.r2) al.add(new Movement(acol, arow, acol, arow+2));
        return al;
    }

    private static ArrayList getPotentialBlackPawnMovements( int acol, int arow )
    {
        //valid back pawn is not on r1, no need to check limit
        //but we check if it is on r2 as it is a diffetent pawn move, promotion
        ArrayList al=new ArrayList<Movement>();
        if (arow>Board.r2) al.add(new Movement(acol, arow, acol, arow-1));
        if (arow==Board.r7) al.add(new Movement(acol, arow, acol, arow-2));
        return al;
    }
    
    private static ArrayList getPotentialWhitePawnCaptures( int acol, int arow )
    {
        ArrayList al=new ArrayList<PawnCaptureMovement>();
        if (acol>Board.A) al.add(new PawnCaptureMovement(acol, arow, acol-1, arow+1));
        if (acol<Board.H) al.add(new PawnCaptureMovement(acol, arow, acol+1, arow+1));
        return al;
    }

    private static ArrayList getPotentialBlackPawnCaptures( int acol, int arow )
    {
        ArrayList al=new ArrayList<PawnCaptureMovement>();
        if (acol>Board.A) al.add(new PawnCaptureMovement(acol, arow, acol-1, arow-1));
        if (acol<Board.H) al.add(new PawnCaptureMovement(acol, arow, acol+1, arow-1));
        return al;
    }

    private static ArrayList getPotentialWhitePawnPromotions( int acol, int arow )
    {
        ArrayList al=new ArrayList<PawnPromotionMovement>();
        if (arow!=Board.r7) throw new IllegalArgumentException("Invalid promotion move");
        al.add(new PawnPromotionMovement(acol, arow, acol, arow+1, Board.WQUEEN));
        al.add(new PawnPromotionMovement(acol, arow, acol, arow+1, Board.WROCK));
        al.add(new PawnPromotionMovement(acol, arow, acol, arow+1, Board.WBISHOP));
        al.add(new PawnPromotionMovement(acol, arow, acol, arow+1, Board.WKNIGHT));
        return al;
    }

    private static ArrayList getPotentialBlackPawnPromotions( int acol, int arow )
    {
        ArrayList al=new ArrayList<PawnPromotionMovement>();
        if (arow!=Board.r2) throw new IllegalArgumentException("Invalid promotion move");
        al.add(new PawnPromotionMovement(acol, arow, acol, arow-1, Board.BQUEEN));
        al.add(new PawnPromotionMovement(acol, arow, acol, arow-1, Board.BROCK));
        al.add(new PawnPromotionMovement(acol, arow, acol, arow-1, Board.BBISHOP));
        al.add(new PawnPromotionMovement(acol, arow, acol, arow-1, Board.BKNIGHT));
        return al;
    }

    private static ArrayList getPotentialBlackPawnPromotionsCaptures( int acol, int arow )
    {
        ArrayList al=new ArrayList<PawnCapturePromotionMovement>();
        if (arow!=Board.r2) throw new IllegalArgumentException("Invalid promotion move");
        if (acol-1>=Board.A) 
        {
            al.add(new PawnCapturePromotionMovement(acol, arow, acol-1, arow-1, Board.BQUEEN));
            al.add(new PawnCapturePromotionMovement(acol, arow, acol-1, arow-1, Board.BROCK));
            al.add(new PawnCapturePromotionMovement(acol, arow, acol-1, arow-1, Board.BBISHOP));
            al.add(new PawnCapturePromotionMovement(acol, arow, acol-1, arow-1, Board.BKNIGHT));
        }
        if (acol+1<=Board.H)
        {
            al.add(new PawnCapturePromotionMovement(acol, arow, acol+1, arow-1, Board.BQUEEN));
            al.add(new PawnCapturePromotionMovement(acol, arow, acol+1, arow-1, Board.BROCK));
            al.add(new PawnCapturePromotionMovement(acol, arow, acol+1, arow-1, Board.BBISHOP));
            al.add(new PawnCapturePromotionMovement(acol, arow, acol+1, arow-1, Board.BKNIGHT));
        }
        return al;
    }

    private static ArrayList getPotentialWhitePawnPromotionsCaptures( int acol, int arow )
    {
        ArrayList al=new ArrayList<PawnCapturePromotionMovement>();
        if (arow!=Board.r7) throw new IllegalArgumentException("Invalid promotion move");
        if (acol-1>=Board.A) 
        {
            al.add(new PawnCapturePromotionMovement(acol, arow, acol-1, arow+1, Board.WQUEEN));
            al.add(new PawnCapturePromotionMovement(acol, arow, acol-1, arow+1, Board.WROCK));
            al.add(new PawnCapturePromotionMovement(acol, arow, acol-1, arow+1, Board.WBISHOP));
            al.add(new PawnCapturePromotionMovement(acol, arow, acol-1, arow+1, Board.WKNIGHT));
        }
        if (acol+1<=Board.H)
        {
            al.add(new PawnCapturePromotionMovement(acol, arow, acol+1, arow+1, Board.WQUEEN));
            al.add(new PawnCapturePromotionMovement(acol, arow, acol+1, arow+1, Board.WROCK));
            al.add(new PawnCapturePromotionMovement(acol, arow, acol+1, arow+1,Board.WBISHOP));
            al.add(new PawnCapturePromotionMovement(acol, arow, acol+1, arow+1, Board.WKNIGHT));
        }
        return al;
    }

    
    private static ArrayList getPotentialBishopMovements( int acol, int arow )
    {
        ArrayList al=new ArrayList<Movement>();
        int offset=1;
        //upright
        while(acol+offset<=Board.H && arow+offset<=Board.r8)
        {
                al.add(new Movement(acol, arow, acol+offset, arow+offset));
                offset++;
        }
        //upleft
        offset=1;
        while(acol-offset>=Board.A && arow+offset<=Board.r8)
        {
                al.add(new Movement(acol, arow, acol-offset, arow+offset));
                offset++;
        }
        //downright
        offset=1;
        while(acol+offset<=Board.H && arow-offset>=Board.r1)
        {
                al.add(new Movement(acol, arow, acol+offset, arow-offset));
                offset++;
        }
        //downleft
        offset=1;
        while(acol-offset>=Board.A && arow-offset>=Board.r1)
        {
                al.add(new Movement(acol, arow, acol-offset, arow-offset));
                offset++;
        }
            
        return al;

    }

    private static ArrayList getPotentialKnightMovements( int acol, int arow )
    {
        ArrayList al=new ArrayList<Movement>();
        // cases i+2, i+1, i-2, i-1, j+2, j+1, j-2, j-1
        // i+- 2, j+- 1 y viceversa
        if (acol+1<=Board.H && arow+2<=Board.r8)
            al.add(new Movement(acol, arow, acol+1, arow+2));
        if (acol+2<=Board.H && arow+1<=Board.r8)
            al.add(new Movement(acol, arow, acol+2, arow+1));
        if (acol+1<=Board.H && arow-2>=Board.r1)
            al.add(new Movement(acol, arow, acol+1, arow-2));
        if (acol+2<=Board.H && arow-1>=Board.r1)
            al.add(new Movement(acol, arow, acol+2, arow-1));
        if (acol-1>=Board.A && arow+2<=Board.r8)
            al.add(new Movement(acol, arow, acol-1, arow+2));
        if (acol-1>=Board.A && arow-2>=Board.r1)
            al.add(new Movement(acol, arow, acol-1, arow-2));
        if (acol-2>=Board.A && arow+1<=Board.r8)
            al.add(new Movement(acol, arow, acol-2, arow+1));
        if (acol-2>=Board.A && arow-1>=Board.r1)
            al.add(new Movement(acol, arow, acol-2, arow-1));
        return al;
    }    
    
    private static ArrayList getPotentialQueenMovements( int acol, int arow )
    {
        ArrayList al=new ArrayList<Movement>();
        Iterator<Movement> al2=PotentialMovements.getPotentialRockMovements(acol,arow).iterator();
        Iterator<Movement> al3=PotentialMovements.getPotentialBishopMovements(acol,arow).iterator();
        while (al2.hasNext()) al.add(al2.next());
        while (al3.hasNext()) al.add(al3.next());
        return al;
    }
    
    private static ArrayList getPotentialKingMovements( int acol, int arow )
    {
        ArrayList al=new ArrayList<Movement>();
        if (acol<Board.H) al.add(new Movement(acol, arow, acol+1, arow));
        if (acol>Board.A) al.add(new Movement(acol, arow, acol-1, arow));
        if (arow<Board.r8) al.add(new Movement(acol, arow, acol, arow+1));
        if (arow>Board.r1) al.add(new Movement(acol, arow, acol, arow-1));
        if (acol<Board.H && arow<Board.r8 ) al.add(new Movement(acol, arow, acol+1, arow+1));
        if (acol>Board.A && arow>Board.r1 ) al.add(new Movement(acol, arow, acol-1, arow-1));
        if (acol>Board.A && arow<Board.r8) al.add(new Movement(acol, arow, acol-1, arow+1));
        if (acol<Board.H && arow>Board.r1) al.add(new Movement(acol, arow, acol+1, arow-1));
        return al;
    }
    
    private static ArrayList getPotentialWhiteKingCastleMovements( int acol, int arow )
    {
        ArrayList al=new ArrayList<Movement>();
        if (acol==Board.E && arow==Board.r1)
        {
            al.add(new CastleMovement(acol, arow, Board.C, Board.r1, new Movement(Board.A, Board.r1, Board.D, Board.r1), true)); //long
            al.add(new CastleMovement(acol, arow, Board.G, Board.r1, new Movement(Board.H, Board.r1, Board.F, Board.r1), false)); //short
        }
        return al;
    }

    private static ArrayList getPotentialBlackKingCastleMovements( int acol, int arow )
    {
        ArrayList al=new ArrayList<Movement>();
        if (acol==Board.E && arow==Board.r8)
        {
            al.add(new CastleMovement(acol, arow, Board.C, Board.r8, new Movement(Board.A, Board.r8, Board.D, Board.r8), true) ); //long
            al.add(new CastleMovement(acol, arow, Board.G, Board.r8, new Movement(Board.H, Board.r8, Board.F, Board.r8), false)); //short
        }
        return al;
    }
    
    
    private static HashMap<Position,ArrayList<Movement>> getPotentialRockMovements()
    {
        HashMap<Position,ArrayList<Movement>> hm=new HashMap<Position,ArrayList<Movement>>();
        for (int i=Board.A; i<=Board.H; i++) 
        {
            for (int j=Board.r1; j<=Board.r8; j++)
            {
                hm.put(Board.boardPositions[i*8+j],getPotentialRockMovements(i,j));
            }
        }
        return hm;
    }
    
    //with private previous methods we calculate potential movements depending on the piece and position, and we generate a HashMap with them all
    //then saved in the public static final fields, to get better performance
    public static final HashMap<Position,ArrayList<Movement>> potentialRockMovements=getPotentialRockMovements();

    private static HashMap<Position,ArrayList<Movement>> getPotentialBishopMovements()
    {
        HashMap<Position,ArrayList<Movement>> hm=new HashMap<Position,ArrayList<Movement>>();
        for (int i=Board.A; i<=Board.H; i++) 
        {
            for (int j=Board.r1; j<=Board.r8; j++)
            {
                hm.put(Board.boardPositions[i*8+j],getPotentialBishopMovements(i,j));
            }
        }
        return hm;
    }
    
    public static final HashMap<Position,ArrayList<Movement>> potentialBishopMovements=getPotentialBishopMovements();

    private static HashMap<Position,ArrayList<Movement>> getPotentialKnightMovements()
    {
        HashMap<Position,ArrayList<Movement>> hm=new HashMap<Position,ArrayList<Movement>>();
        for (int i=Board.A; i<=Board.H; i++) 
        {
            for (int j=Board.r1; j<=Board.r8; j++)
            {
                hm.put(Board.boardPositions[i*8+j],getPotentialKnightMovements(i,j));
            }
        }
        return hm;
    }
    
    public static final HashMap<Position,ArrayList<Movement>> potentialKnightMovements=getPotentialKnightMovements();
    
    private static HashMap<Position,ArrayList<Movement>> getPotentialQueenMovements()
    {
        HashMap<Position,ArrayList<Movement>> hm=new HashMap<Position,ArrayList<Movement>>();
        for (int i=Board.A; i<=Board.H; i++) 
        {
            for (int j=Board.r1; j<=Board.r8; j++)
            {
                hm.put(Board.boardPositions[i*8+j],getPotentialQueenMovements(i,j));
            }
        }
        return hm;
    }
    
    public static final HashMap<Position,ArrayList<Movement>> potentialQueenMovements=getPotentialQueenMovements();
    
    private static HashMap<Position,ArrayList<Movement>> getPotentialWhitePawnMovements()
    {
        int j;
        HashMap<Position,ArrayList<Movement>> hm=new HashMap<Position,ArrayList<Movement>>();
        ArrayList<Movement> al, al2, al3, al4;
        Iterator<Movement> it;
        for (int i=Board.A; i<=Board.H; i++) 
        {
            for (j=Board.r2; j<Board.r7; j++)
            {
                al=getPotentialWhitePawnMovements(i,j);
                al2=getPotentialWhitePawnCaptures(i,j);
                it=al2.iterator();
                while (it.hasNext())
                    al.add(it.next());                
                hm.put(Board.boardPositions[i*8+j],al);
            }
            j=Board.r7;
            al3=getPotentialWhitePawnPromotions(i,j);
            al4=getPotentialWhitePawnPromotionsCaptures(i,j);
            it=al3.iterator();
            while (it.hasNext())
                al4.add(it.next());                
            hm.put(Board.boardPositions[i*8+j],al4);
        }
        return hm;
    }
    
    public static final HashMap<Position,ArrayList<Movement>> potentialWhitePawnMovements=getPotentialWhitePawnMovements();

    private static HashMap<Position,ArrayList<Movement>> getPotentialBlackPawnMovements()
    {
        int j;
        HashMap<Position,ArrayList<Movement>> hm=new HashMap<Position,ArrayList<Movement>>();
        ArrayList<Movement> al, al2, al3, al4;
        Iterator<Movement> it;
        for (int i=Board.A; i<=Board.H; i++) 
        {
            for (j=Board.r3; j<=Board.r7; j++)
            {
                al=getPotentialBlackPawnMovements(i,j);
                al2=getPotentialBlackPawnCaptures(i,j);
                it=al2.iterator();
                while (it.hasNext())
                    al.add(it.next());                
                hm.put(Board.boardPositions[i*8+j],al);
            }
            j=Board.r2;
            al3=getPotentialBlackPawnPromotions(i,j);
            al4=getPotentialBlackPawnPromotionsCaptures(i,j);
            it=al3.iterator();
            while (it.hasNext())
                al4.add(it.next());                
            hm.put(Board.boardPositions[i*8+j],al4);
        }
        return hm;
    }
    
    public static final HashMap<Position,ArrayList<Movement>> potentialBlackPawnMovements=getPotentialBlackPawnMovements();

    private static HashMap<Position,ArrayList<Movement>> getPotentialKingMovements()
    {
        HashMap<Position,ArrayList<Movement>> hm=new HashMap<Position,ArrayList<Movement>>();
        ArrayList<Movement> al=null;
        ArrayList<Movement> al2=null;
        Iterator<Movement> it;
        for (int i=Board.A; i<=Board.H; i++) 
        {
            for (int j=Board.r1; j<=Board.r8; j++)
            {
                al=getPotentialKingMovements(i,j);
                if (i==Board.E)
                {
                    if (j==Board.r1 )
                    {
                        al2=getPotentialWhiteKingCastleMovements(i,j);
                    }
                    else if (j==Board.r8)
                    {
                        al2=getPotentialBlackKingCastleMovements(i,j);
                    }
                }
                if (al2!=null)
                {
                    it=al2.iterator();
                    while (it.hasNext())
                        al.add(it.next());
                }
                hm.put(Board.boardPositions[i*8+j],al);
            }
        }
        return hm;
    }

    public static final HashMap<Position,ArrayList<Movement>> potentialKingMovements=getPotentialKingMovements();
    


}
