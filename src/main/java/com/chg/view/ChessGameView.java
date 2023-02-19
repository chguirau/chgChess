/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.chg.view;

/**
 *
 * @author carlosherrero
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import com.chg.chess.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;

/**
 *
 * @author Khouiled
 */
public class ChessGameView {
    
    JFrame frame;
    EvaluatedBoard b;
    Position selectedPosition;
    public int selectedPromoPiece;
    public static Image[] imgs;
    public ChessGame cg;
    
    public static void main(String[] args) throws IOException {
        BufferedImage all=ImageIO.read(new File("/Users/carlosherrero/NetBeansProjects/chessDraw/src/main/java/chess.png"));
        
        imgs=new Image[12];
        int ind=0;
        for(int y=0;y<400;y+=200){
            for(int x=0;x<1200;x+=200){
                imgs[ind]=all.getSubimage(x, y, 200, 200).getScaledInstance(64, 64, BufferedImage.SCALE_SMOOTH);
                ind++;
            }    
        }
        
        ChessGameView cgv=new ChessGameView();
        
 
        
        cgv.b=new EvaluatedBoard();
        cgv.cg=new ChessGame(cgv);
        cgv.cg.doProcess();
        
    }
    
    public JFrame getFrame(ChessGameView acgv)
    {

        JFrame aframe=new JFrame();
        aframe.setBounds(10, 10, 1246, 576);
        aframe.setUndecorated(true);


        JPanel pn=new JPanel()
        {
            @Override
            public void paintComponent(Graphics g) 
            {
                super.paintComponent(g);
                boolean white=false;
                for(int y= 0;y<9;y++)  
                {
                    for(int x= 0;x<9;x++)
                    {
                        if (x==0 || y==8)
                        {
                            g.setColor(new Color(240,255, 255));
                        }
                        else if(white){
                            g.setColor(new Color(235,235, 208));
                        } 
                        else
                        {
                            g.setColor(new Color(119, 148, 85));
                        }
                        g.fillRect(x*64, y*64, 64, 64);
                       white=!white;
                    }
                    //white=!white;
                }
                /*
                boolean white=true;
                for(int y= 0;y<8;y++)  
                {
                    for(int x= 0;x<8;x++)
                    {
                        if(white){
                            g.setColor(new Color(235,235, 208));
                        } 
                        else
                        {
                            g.setColor(new Color(119, 148, 85));
                        }
                        g.fillRect(x*64, y*64, 64, 64);
                       white=!white;
                    }
                    white=!white;
                }
                */

                /*
                GamePiece gp=null;
                //System.out.println(b.boardWhitePieces.size());
                //System.out.println(b.boardBlackPieces.size());
                Iterator<GamePiece> it=b.boardWhitePieces.iterator();
                int ind=0;
                while (it.hasNext())
                {
                    gp=it.next();
                    ind=0;
                    if(gp.piece ==Board.WKING)
                    {
                        ind=0;
                    }
                    if(gp.piece==Board.WQUEEN)
                    {
                        ind=1;
                    }
                    if(gp.piece==Board.WBISHOP)
                    {
                        ind=2;
                    }
                    if(gp.piece==Board.WKNIGHT)
                    {
                        ind=3;
                    }
                    if(gp.piece==Board.WROCK)
                    {
                        ind=4;
                    }
                    if(gp.piece==Board.WPAWN)
                    {
                        ind=5;
                    }
                    //better a switch ?
                    //possible no piece valid value?
                    g.drawImage(imgs[ind], gp.pos.col*64, (Board.r8-gp.pos.row)*64, this);

                }


                it=b.boardBlackPieces.iterator();
                while (it.hasNext())
                {
                    gp=it.next();
                    ind=0;
                    if(gp.piece ==Board.BKING)
                    {
                        ind=0;
                    }
                    if(gp.piece==Board.BQUEEN)
                    {
                        ind=1;
                    }
                    if(gp.piece==Board.BBISHOP)
                    {
                        ind=2;
                    }
                    if(gp.piece==Board.BKNIGHT)
                    {
                        ind=3;
                    }
                    if(gp.piece==Board.BROCK)
                    {
                        ind=4;
                    }
                    if(gp.piece==Board.BPAWN)
                    {
                        ind=5;
                    }
                    //{
                    // Black indexes
                        ind+=6;
                    //}
                    g.drawImage(imgs[ind], gp.pos.col*64, (Board.r8-gp.pos.row)*64, this);
                    //better a switch ?
                    //possible no piece valid value?
                }
                */
                int aPiece=Board.EMPTY;
                int ind=0;
                for (int i=Board.A; i<=Board.H; i++)
                {
                    for (int j=Board.r1; j<=Board.r8; j++)
                    {
                        switch (acgv.b.board[i][j])
                        {
                            case Board.EMPTY:
                                break;
                            case Board.WKING:
                                ind=0;
                                break;
                            case Board.WQUEEN:
                                ind=1;
                                break;
                            case Board.WBISHOP:
                                ind=2;
                                break;
                            case Board.WKNIGHT:
                                ind=3;
                                break;
                            case Board.WROCK:
                                ind=4;
                                break;
                            case Board.WPAWN:
                                ind=5;
                                break;
                            case Board.BKING:
                                ind=6;
                                break;
                            case Board.BQUEEN:
                                ind=7;
                                break;
                            case Board.BBISHOP:
                                ind=8;
                                break;
                            case Board.BKNIGHT:
                                ind=9;
                                break;
                            case Board.BROCK:
                                ind=10;
                                break;
                            case Board.BPAWN:
                                ind=11;                            
                                break;
                        }
                        if (acgv.b.board[i][j] != Board.EMPTY)
                            g.drawImage(imgs[ind], (i+1)*64, (Board.r8-j)*64, this);

                    }
                }
                Graphics2D g2 = (Graphics2D)g;
                g.setColor(new Color(0,0, 0));
                g2.setRenderingHint(
                   RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                Font font = new Font("Serif", Font.PLAIN, 48);
                g2.setFont(font);
                for (int i=Board.r1; i<=Board.r8; i++)
                {
                    g2.drawString("87654321".substring(i, i+1), 20, 50+64*i);
                }
                for (int i=Board.A; i<=Board.H; i++)
                {
                    g2.drawString("abcdefgh".substring(i, i+1), 74+64*i, 554);
                }

                if (acgv.selectedPosition!= null)
                {
                    //highlite
                    //square top line
                    g.setColor(new Color(255,255, 0));
                    g.drawLine((acgv.selectedPosition.col+1)*64, (Board.r8-acgv.selectedPosition.row)*64, 
                            (acgv.selectedPosition.col+2)*64,(Board.r8-acgv.selectedPosition.row)*64);
                    g.drawLine((acgv.selectedPosition.col+1)*64, (Board.r8-acgv.selectedPosition.row)*64+1, 
                            (acgv.selectedPosition.col+2)*64,(Board.r8-acgv.selectedPosition.row)*64+1);
                    g.drawLine((acgv.selectedPosition.col+1)*64, (Board.r8-acgv.selectedPosition.row)*64+2, 
                            (acgv.selectedPosition.col+2)*64,(Board.r8-acgv.selectedPosition.row)*64+2);

                    //square botton line
                    g.drawLine((acgv.selectedPosition.col+1)*64, (Board.r8-acgv.selectedPosition.row+1)*64, 
                            (acgv.selectedPosition.col+2)*64,(Board.r8-acgv.selectedPosition.row+1)*64);
                    g.drawLine((acgv.selectedPosition.col+1)*64, (Board.r8-acgv.selectedPosition.row+1)*64-1, 
                            (acgv.selectedPosition.col+2)*64,(Board.r8-acgv.selectedPosition.row+1)*64-1);
                    g.drawLine((acgv.selectedPosition.col+1)*64, (Board.r8-acgv.selectedPosition.row+1)*64-2, 
                            (acgv.selectedPosition.col+2)*64,(Board.r8-acgv.selectedPosition.row+1)*64-2);

                    //square left line
                    g.drawLine((acgv.selectedPosition.col+1)*64, (Board.r8-acgv.selectedPosition.row)*64, 
                            (acgv.selectedPosition.col+1)*64,(Board.r8-acgv.selectedPosition.row+1)*64);
                    g.drawLine((acgv.selectedPosition.col+1)*64+1, (Board.r8-acgv.selectedPosition.row)*64, 
                            (acgv.selectedPosition.col+1)*64+1,(Board.r8-acgv.selectedPosition.row+1)*64);
                    g.drawLine((acgv.selectedPosition.col+1)*64+2, (Board.r8-acgv.selectedPosition.row)*64, 
                            (acgv.selectedPosition.col+1)*64+2,(Board.r8-acgv.selectedPosition.row+1)*64);

                    //square right line
                    g.drawLine((acgv.selectedPosition.col+2)*64, (Board.r8-acgv.selectedPosition.row)*64, 
                            (acgv.selectedPosition.col+2)*64,(Board.r8-acgv.selectedPosition.row+1)*64);
                    g.drawLine((acgv.selectedPosition.col+2)*64-1, (Board.r8-acgv.selectedPosition.row)*64, 
                            (acgv.selectedPosition.col+2)*64-1,(Board.r8-acgv.selectedPosition.row+1)*64);
                    g.drawLine((acgv.selectedPosition.col+2)*64-2, (Board.r8-acgv.selectedPosition.row)*64, 
                            (acgv.selectedPosition.col+2)*64-2,(Board.r8-acgv.selectedPosition.row+1)*64);

                }

                //Draw game status and movements so far
                font = new Font("Arial", Font.PLAIN, 15);
                g2.setFont(font);                
                g.setColor(new Color(0,0, 0));
                String gmSt="";
                if (acgv.selectedGameMode==ChessGame.OnePlayerBlack)
                {
                    gmSt="1 Player (Black)";
                }
                else if (acgv.selectedGameMode==ChessGame.OnePlayerWhite)
                {
                    gmSt="1 Player (White)";
                }
                else if (acgv.selectedGameMode==ChessGame.TwoPlayers)
                {
                    gmSt="2 Players";
                }
                else if (acgv.selectedGameMode==ChessGame.Computer)
                {
                    gmSt="Computer only";
                }



                g.drawString("  Game Mode " + gmSt, 576, 28);
                if (acgv.b.turn == Board.WHITE) g.drawString("  White to Play ", 576, 52); else g.drawString("  Black to Play ", 576, 52);
                g.drawString("  Movement "+ acgv.b.moves + "; 50 moves rule "+acgv.b.halfMove50rule, 576, 76);
                g.drawString("  En Passant "+ (acgv.b.enPassant != Board.NO_enPassant 
                                ? Board.getAlgebraicPositionFromSquare(acgv.b.enPassant.col, acgv.b.enPassant.row) : " NO "), 576, 100);
                g.drawString("  Castle White 0-0"+ (acgv.b.canWhiteCastleShort ? " YES " : " NO "), 576, 124);
                g.drawString("  Castle White 0-0-0"+ (acgv.b.canWhiteCastleLong ? " YES " : " NO "), 576, 148);
                g.drawString("  Castle Black 0-0"+ (acgv.b.canBlackCastleShort ? " YES " : " NO "), 742, 124);
                g.drawString("  Castle Black 0-0-0"+ (acgv.b.canBlackCastleLong ? " YES " : " NO "), 742, 148);

                if (!acgv.selectedChessPosition.equals(""))
                    g.drawString("  FEN: \n"+acgv.selectedChessPosition, 576, 172);

                String resultSt="";
                font = new Font("Serif", Font.BOLD, 15);
                switch(acgv.b.result)
                {
                    case EvaluatedBoard.BLACKWINS:
                    {
                        resultSt=resultSt+" Black Wins! ";
                        break;
                    }
                    case EvaluatedBoard.WHITEWINS:
                    {
                        resultSt=resultSt+" White Wins! ";
                        break;
                    }
                    case EvaluatedBoard.DRAW:
                    {
                        resultSt=resultSt+" Draw ";
                        break;
                    }
                    case EvaluatedBoard.INPROGRESS:
                    {
                        resultSt=resultSt+" Game in progress ... ";
                        break;
                    }   
                }
                switch(acgv.b.detailResult)
                {
                    case EvaluatedBoard.CHECKMATE:
                    {
                        resultSt=resultSt+" Checkmate ";
                        break;
                    }
                    case EvaluatedBoard.DRAW_STALEMATE:
                    {
                        resultSt=resultSt+" Stalemate! ";
                        break;
                    }
                    case EvaluatedBoard.DRAW_3MOVES_REPETITION:
                    {
                        resultSt=resultSt+" Position repeated 3 times ";
                        break;
                    }
                    case EvaluatedBoard.DRAW_50MOVES_RULE:
                    {
                        resultSt=resultSt+" after 50 moves ";
                        break;
                    }   
                    case EvaluatedBoard.DRAW_INSUFICIENT_MATERIAL:
                    {
                        resultSt=resultSt+" Insuficient material ";
                        break;
                    }   
                    case EvaluatedBoard.DRAW_AGREED:
                    {
                        resultSt=resultSt+" Agreed ";
                        break;
                    }   
                    case EvaluatedBoard.RESIGNS:
                    {
                        resultSt=resultSt+" after opponent resigns ";
                        break;
                    }   
                    case EvaluatedBoard.INPROGRESSD:
                    {
                        resultSt=resultSt+"";
                        break;
                    }   
                }

                g2.setFont(font);
                g.drawString("  Status " + resultSt, 576, 230 );

                font = new Font("Serif", Font.PLAIN, 12);
                Font font2 = new Font("Serif", Font.BOLD, 12);
                g2.setFont(font);
                int x_offset=0;
                int y_offset=0;
                boolean odd=false;
                String [] sta=acgv.moveList.split("\n");
                for (int i=0; i<sta.length; i++)
                {
                    if (odd) g2.setFont(font); else g2.setFont(font2);


                    if (i==15 || i==30 || i==45 || i==60) 
                    {
                        x_offset+=92;
                        y_offset-=300;
                    }
                    g.drawString("  "+sta[i], 576+x_offset, 260+y_offset+20*i);
                    odd=!odd;
                }


            }

        };

        JButton b=new JButton("Resign");  
        b.setBounds(700,32,95,30);  

        aframe.add(b);

        aframe.add(pn);
        //cgv.frame.add(ta);
        aframe.setDefaultCloseOperation(3);
        aframe.setVisible(true);

        
        pn.addMouseListener(acgv.cg);
        b.addActionListener ( acgv.cg);
        
        return aframe;

    } 
        
    //Pending to move to JUnit tests
        // test movements 1 piece
        /*
        ArrayList<GamePiece> test=new ArrayList<GamePiece>();
        GamePiece testp=new GamePiece(Board.e7, Board.WPAWN);
        test.add(testp);
        testp=new GamePiece(Board.d8, Board.BROCK);
        test.add(testp);
        b.initFromGamePieceList(test);
        frame.repaint(); 
        try {
            Thread.sleep(4000);        
        }
        catch (InterruptedException e)
        {
            System.out.println(e.getMessage() );
        }
        
        
        //simulate we move one of the pieces of the board, first piece
        testp=b.boardWhitePieces.get(0);
        //testp=b.boardBlackPieces.get(0);
        GamePiece testp2=null;
        ArrayList<Movement> al=null;
        al=testp.getPotentialMovements(b);

        Movement m=null;
        Movement backm=null;

        if (al!=null)
        {
        
            System.out.println("Size: "+al.size());
            Iterator<Movement> im=al.iterator();
            while (im.hasNext())
            {
                m=im.next();
                System.out.println("Move "+ m.target_col + " "+ m.target_row );
                
                //test promotion move
                //if (m instanceof PawnCapturePromotionMovement)
                //testp2=b.promotionMove(testp, m);
                //else testp2=b.move(testp, m);
                //if (b.isCapture(m)) b.removeCapturedPiece(m);
               
                testp2=b.move(testp, m);

                frame.repaint();
                try {
                    Thread.sleep(1000);        
                }
                catch (InterruptedException e)
                {
                    System.out.println(e.getMessage() );
                }

                //we need to undo move!!
                backm=new Movement(m.target_col, m.target_row, m.orig_col, m.orig_row);
                System.out.println("Back Move "+ backm.target_col + " "+ backm.target_row );

                testp=b.move(testp2, backm);

                frame.repaint();
                try {
                    Thread.sleep(1000);        
                }
                catch (InterruptedException e)
                {
                    System.out.println(e.getMessage() );
                }

            }
        }
        else System.out.println("No valid movements");
        
        //test board positions
        System.out.println(Board.isWhitePosition(Board.a1));
        System.out.println(Board.isWhitePosition(Board.a2));
        System.out.println(Board.isWhitePosition(Board.e1));
        System.out.println(Board.isWhitePosition(Board.f6));
        System.out.println(Board.isWhitePosition(Board.h3));
        
        */
        
        //test save movements in a list
        //then iterate the list, create orig board, get move in the list, move it
        //this way I do not need a move back (which becomes difficut if a capture)
        //simulate we move one of the pieces of the board, first piece

        /*
        ArrayList<GamePiece> test=new ArrayList<GamePiece>();
        GamePiece testp=new GamePiece(Board.c6, Board.WKNIGHT);
        test.add(testp);
        testp=new GamePiece(Board.d8, Board.BROCK);
        test.add(testp);
        b.initFromGamePieceList(test);
        frame.repaint(); 
        try {
            Thread.sleep(4000);        
        }
        catch (InterruptedException e)
        {
            System.out.println(e.getMessage() );
        }
        */
        /*
        //test save movements in a list
        //then iterate the list, create orig board, get move in the list, move it
        //this way I do not need a move back (which becomes difficut if a capture)
        //simulate we move one of the pieces of the board, first piece
        testp=b.boardWhitePieces.get(0);
        //testp=b.boardBlackPieces.get(0);
        GamePiece testp2=null;
        ArrayList<Movement> al=null;
        al=testp.getPotentialMovements();

        Movement m=null;
        Movement backm=null;

        if (al!=null)
        {
        
            System.out.println("Size: "+al.size());
            Iterator<Movement> im=al.iterator();
            while (im.hasNext())
            {
                m=im.next();
                System.out.println("Move "+ m.target_col + " "+ m.target_row );
                
                //test promotion, capture, promotion capture moves ...
                System.out.println("Size black before "+ b.boardBlackPieces.size());
                boolean capt=b.isCapture(m); //before move as it changes the board!!
                if (m instanceof PawnCapturePromotionMovement)
                    testp2=b.promotionMove(testp, m);
                else if (m instanceof PawnPromotionMovement)
                    testp2=b.promotionMove(testp, m);
                else
                    testp2=b.move(testp, m);
                if (capt) b.removeCapturedPiece(m);
                
                System.out.println("Size black after "+ b.boardBlackPieces.size());
               
                frame.repaint();
                try {
                    Thread.sleep(1000);        
                }
                catch (InterruptedException e)
                {
                    System.out.println(e.getMessage() );
                }

                b.initFromGamePieceList(test);
                frame.repaint(); 
                try {
                    Thread.sleep(2000);        
                }
                catch (InterruptedException e)
                {
                    System.out.println(e.getMessage() );
                }
            }
        }
        else System.out.println("No valid movements");
        */

        
        /*
        // total n ply movements tests
        //ply5 from original position ok
        //ply5 after e2e4 ok
        ChessGame cg=new ChessGame();
        Board bb=new Board(true);
        System.out.println("Start test");
        GamePiece tp=bb.getPiece(Board.e2);
        bb.chessMove(tp, new Movement(Board.e2, Board.e4), true); 

        System.out.println(cg.getNumberOfMovements(bb,5));
        frame.repaint(); 
        try {
            Thread.sleep(2000);        
        }
        catch (InterruptedException e)
        {
            System.out.println(e.getMessage() );
        }
        */

        /*
        //test from FEN position
        ChessGame cg=new ChessGame();
        Board bb=new Board(true);
        //bb.initFromFEN("r1bqkbnr/p1p2ppp/2pp4/4p3/4P3/5N2/PPPP1PPP/RNBQK2R w KQkq - 0 5", true); //OK
        bb.initFromFEN("r7/p5rp/8/8/P1p2k2/2P5/2P2P1P/7K b - - 1 28", true); //OK
        
        System.out.println("Start test");
        b.initFromBoard(bb); // b is the board to draw
        frame.repaint(); 
        try {
            Thread.sleep(2000);        
        }
        catch (InterruptedException e)
        {
            System.out.println(e.getMessage() );
        }

        //System.out.println(cg.getNumberOfMovements(bb,5));
        */      

        
        // total n ply movements tests with previous moves
        /*
        ChessGame cg=new ChessGame();
        Board bb=new Board(true);
        GamePiece tp=bb.getPiece(Board.a2);
        bb.chessMove(tp, new Movement(Board.a2, Board.a4), true); 
        
        tp=bb.getPiece(Board.b7);
        bb.chessMove(tp, new Movement(Board.b7, Board.b5), true); 
        tp=bb.getPiece(Board.a4);
        bb.chessMove(tp, new PawnCaptureMovement(Board.a4, Board.b5), true); 

        tp=bb.getPiece(Board.b8);
        bb.chessMove(tp, new Movement(Board.b8, Board.a6), true); 
        
        tp=bb.getPiece(Board.a5);
        bb.chessMove(tp, new PawnCaptureMovement(Board.a5, Board.b6), true); 
        
        System.out.println("Start test");

        System.out.println(cg.getNumberOfMovements(bb,2));
        */
        /*
        frame.repaint(); 
        try {
            Thread.sleep(2000);        
        }
        catch (InterruptedException e)
        {
            System.out.println(e.getMessage() );
        }
        */
        
        /*
        // fails, b4 has a black pawn in board but a white in lists
        //testing first case
        ChessGame cg=new ChessGame();
        Board bb=new Board(true);
        GamePiece tp=bb.getPiece(Board.a2);
        bb.chessMove(tp, new Movement(Board.a2, Board.a3), true); 
        tp=bb.getPiece(Board.a7);
        bb.chessMove(tp, new Movement(Board.a7, Board.a5), true); 
        tp=bb.getPiece(Board.b2);
        bb.chessMove(tp, new Movement(Board.b2, Board.b4), true);
        tp=bb.getPiece(Board.a5);
        bb.chessMove(tp, new PawnCaptureMovement(Board.a5, Board.b4), true);
        tp=bb.getPiece(Board.a3);
        bb.chessMove(tp, new PawnCaptureMovement(Board.a3, Board.b4), true);
        */
        
        /*
        //d2d3 fails in Bxg5 (pawn), Bxh6 (N or pawn)
        //testing first case
        ChessGame cg=new ChessGame();
        Board bb=new Board(true);
        GamePiece tp=bb.getPiece(Board.d2);
        bb.chessMove(tp, new Movement(Board.d2, Board.d4), true); //d2d4
        tp=bb.getPiece(Board.g7);
        bb.chessMove(tp, new Movement(Board.g7, Board.g5), true); //g7g5
        tp=bb.getPiece(Board.c1);
        bb.chessMove(tp, new Movement(Board.c1, Board.g5), true); //Bxg5
        */
        
        /*
        ChessGame cg=new ChessGame();
        Board bb=new Board(true);
        GamePiece tp=bb.getPiece(Board.d2);
        bb.chessMove(tp, new Movement(Board.d2, Board.d4), true);
        System.out.println(cg.getNumberOfMovements(bb,2));
        frame.repaint(); 
        try {
            Thread.sleep(2000);        
        }
        catch (InterruptedException e)
        {
            System.out.println(e.getMessage() );
        }
        */

        /*
        
        ArrayList<Board> bal=b.getAllValidMovements();
        ArrayList<Board> blackbal=null;
        Board ab=null;
        int blackMvmtsCount=0;
        Iterator<Board> bim=bal.iterator();
        Iterator<Board> blackbim=null;
        blackbal=bb.getAllValidMovements();
        blackMvmtsCount=blackMvmtsCount+blackbal.size();
        if (blackbal!=null)
        {
            blackbim=blackbal.iterator();
            while (blackbim.hasNext())
            {
                bb=blackbim.next();
                System.out.println("Move black "+ bb.lastMoveAlgebraicNotation);
                b.initFromBoard(bb); // b is the board to draw
                frame.repaint(); 
                try {
                    Thread.sleep(100);        
                }
                catch (InterruptedException e)
                {
                    System.out.println(e.getMessage() );
                }
                
                bal=bb.getAllValidMovements();
                if (bal!=null)
                {
                    bim=bal.iterator();
                    while (bim.hasNext())
                    {
                        ab=bim.next();
                        System.out.println("Move white "+ ab.lastMoveAlgebraicNotation);
                        b.initFromBoard(ab); // b is the board to draw
                        frame.repaint(); 
                        try {
                            Thread.sleep(100);        
                        }
                        catch (InterruptedException e)
                        {
                            System.out.println(e.getMessage() );
                        }
                    
                    }

                }
                

            }
        }
        System.out.println("Size black: "+blackMvmtsCount);
        */
        
        /*
        ChessGame cg=new ChessGame();
        Board bb=new Board(true);
        GamePiece tp=bb.getPiece(Board.b1);
        bb.chessMove(tp, new Movement(Board.b1, Board.c3), true); //Nc3
        System.out.println(cg.getNumberOfMovements(bb,2));
        frame.repaint(); 
        try {
            Thread.sleep(2000);        
        }
        catch (InterruptedException e)
        {
            System.out.println(e.getMessage() );
        }
        ArrayList<Board> bal=b.getAllValidMovements();
        ArrayList<Board> blackbal=null;
        Board ab=null;
        int blackMvmtsCount=0;
        Iterator<Board> bim=bal.iterator();
        Iterator<Board> blackbim=null;
        blackbal=bb.getAllValidMovements();
        blackMvmtsCount=blackMvmtsCount+blackbal.size();
        if (blackbal!=null)
        {
            blackbim=blackbal.iterator();
            while (blackbim.hasNext())
            {
                bb=blackbim.next();
                System.out.println("Move "+ bb.lastMoveAlgebraicNotation);
                b.initFromBoard(bb); // b is the board to draw
                frame.repaint(); 
                try {
                    Thread.sleep(100);        
                }
                catch (InterruptedException e)
                {
                    System.out.println(e.getMessage() );
                }
                
                bal=bb.getAllValidMovements();
                if (bal!=null)
                {
                    bim=bal.iterator();
                    while (bim.hasNext())
                    {
                        ab=bim.next();
                        System.out.println("Move "+ ab.lastMoveAlgebraicNotation);
                        b.initFromBoard(ab); // b is the board to draw
                        frame.repaint(); 
                        try {
                            Thread.sleep(100);        
                        }
                        catch (InterruptedException e)
                        {
                            System.out.println(e.getMessage() );
                        }
                    
                    }

                }
                

            }
        }
        System.out.println("Size black: "+blackMvmtsCount);

        /*
        b.initOriginalPosition();
        frame.repaint(); 
        try {
            Thread.sleep(2000);        
        }
        catch (InterruptedException e)
        {
            System.out.println(e.getMessage() );
        }
        */
        
        

        /*
        ArrayList<Board> bal=b.getAllValidMovements();
        ArrayList<Board> blackbal=null;
        Board ab=null;
        int blackMvmtsCount=0;
        
        if (bal!=null)
        {
        
            System.out.println("Size White: "+bal.size());
            Iterator<Board> bim=bal.iterator();
            Iterator<Board> blackbim=null;
            while (bim.hasNext())
            {
                ab=bim.next();
                System.out.println("Move "+ ab.lastMoveAlgebraicNotation);
                b.initFromBoard(ab); // b is the board to draw
                frame.repaint(); 
                try {
                    Thread.sleep(1000);        
                }
                catch (InterruptedException e)
                {
                    System.out.println(e.getMessage() );
                }
                
                blackbal=ab.getAllValidMovements();
                blackMvmtsCount=blackMvmtsCount+blackbal.size();
                if (blackbal!=null)
                {
                    blackbim=blackbal.iterator();
                    while (blackbim.hasNext())
                    {
                        ab=blackbim.next();
                        System.out.println("Move "+ ab.lastMoveAlgebraicNotation);
                        b.initFromBoard(ab); // b is the board to draw
                        frame.repaint(); 
                        try {
                            Thread.sleep(100);        
                        }
                        catch (InterruptedException e)
                        {
                            System.out.println(e.getMessage() );
                        }
                        
                    }
                }
                System.out.println("Size black: "+blackMvmtsCount);
            }
        }
        */
        
        // I will use repaint as a response for a move in board!
    
    
    public Position isSquare(MouseEvent e)
    {

            int x=e.getX()/64-1;
            int y=Board.r8-e.getY()/64;
            //System.out.println("x "+x+" y "+y);        
            if (x<Board.A || x>Board.H || y<Board.r1 || y>Board.r8) return null;
            Position p=Board.getPosition(x, y);
            //System.out.println(Board.getAlgebraicPositionFromSquare(p.col, p.row));
            return p;
    }
    
    public void refresh(EvaluatedBoard eb, Position p)
    {
        b.initFromBoard(eb);
        selectedPosition=p;
        frame.repaint();     
    }
        
    public int getPromotionPiece(int aturn)
    {
        int retPromoPiece;
        Dialog d = new Dialog(frame , "Select Promotion Piece", true);  
        d.setLayout( new FlowLayout() );  
        JButton qb, rb, bb, kb;
                
        if (aturn==Board.WHITE)
        {
             qb  = new JButton(new ImageIcon(imgs[1]));
             rb = new JButton (new ImageIcon(imgs[4]));  
             bb = new JButton (new ImageIcon(imgs[2]));  
             kb = new JButton (new ImageIcon(imgs[3]));  
        }
        else
        {
             qb  = new JButton(new ImageIcon(imgs[7]));
             rb = new JButton (new ImageIcon(imgs[10]));  
             bb = new JButton (new ImageIcon(imgs[8]));  
             kb = new JButton (new ImageIcon(imgs[9]));          
        }
        qb.addActionListener ( new ActionListener()  
        {  
            public void actionPerformed( ActionEvent e )  
            {  
                selectedPromoPiece=aturn==Board.WHITE ? Board.WQUEEN :Board.BQUEEN;
                d.setVisible(false);  
            }  
        });  
        rb.addActionListener ( new ActionListener()  
        {  
            public void actionPerformed( ActionEvent e )  
            {  
                selectedPromoPiece=aturn==Board.WHITE ? Board.WROCK : Board.BROCK;
                d.setVisible(false);  
            }  
        });  
        bb.addActionListener ( new ActionListener()  
        {  
            public void actionPerformed( ActionEvent e )  
            {  
                selectedPromoPiece=aturn==Board.WHITE ? Board.WBISHOP : Board.BBISHOP;
                d.setVisible(false);  
            }  
        });  
        kb.addActionListener ( new ActionListener()  
        {  
            public void actionPerformed( ActionEvent e )  
            {  
                selectedPromoPiece=aturn==Board.WHITE ? Board.WKNIGHT : Board.BKNIGHT;
                d.setVisible(false);  
            }  
        });  

        d.add(qb);   

        d.add(rb);   
        d.add(bb);   
        d.add(kb);   

        d.setSize(400,120);    
        d.setVisible(true);  
        return selectedPromoPiece;
    }
    
    public void DialogNewGame()
    {
        frame=getFrame(this);
        Dialog d = new Dialog(frame , "New Game", true);  
        d.setLayout( new FlowLayout() );  
        Button b = new Button ("OK");  
        d.setSize(400,300);    
        
        // creating a text area  
        d.add( new Label ("Select FEN start position or empty for initial position"));  
        JTextArea area = new JTextArea("", 2, 25);
        area.setLineWrap(true);
        
        d.add(area);  

        d.add( new Label ("Select Game Mode                                                    "));  
        
        CheckboxGroup cbg = new CheckboxGroup();  
        Checkbox checkBox1 = new Checkbox("1 Player", cbg, false);    
        checkBox1.setBounds(100,200, 50,50);    
        Checkbox checkBox2 = new Checkbox("2 Players", cbg, true);    
        checkBox2.setBounds(100,250, 50,50);    
        Checkbox checkBox3 = new Checkbox("Computer", cbg, false);    
        checkBox3.setBounds(100,300, 50,50);    
        d.add(checkBox1);    
        d.add(checkBox2);    
        d.add(checkBox3);    

        d.add( new Label ("Select Color for Human Player vs computer             "));  
        CheckboxGroup cbg2 = new CheckboxGroup();  
        Checkbox checkBox4 = new Checkbox("Player 1 is White", cbg2, true);    
        checkBox1.setBounds(100,200, 50,50);    
        Checkbox checkBox5 = new Checkbox("Player 1 is Black", cbg2, false);    
        checkBox2.setBounds(100,250, 50,50);    
        d.add(checkBox4);    
        d.add(checkBox5);    

        
        d.add( new Label ("Click button to continue."));  
        d.add(b);   
        b.addActionListener ( new ActionListener()  
        {  
            public void actionPerformed( ActionEvent e )  
            {  
                d.setVisible(false);  
                selectedChessPosition=area.getText();
                if (checkBox1.getState())
                {
                    if (checkBox4.getState())
                        selectedGameMode=ChessGame.OnePlayerWhite;
                    else
                        selectedGameMode=ChessGame.OnePlayerBlack;
                }
                if (checkBox2.getState())
                {
                    selectedGameMode=ChessGame.TwoPlayers;
                }
                if (checkBox3.getState())
                {
                    selectedGameMode=ChessGame.Computer;
                }
                moveList="";
            }  
        });  
        
        d.setVisible(true);      
    }

    public void doConfirm()
    {
        Dialog d = new Dialog(frame , "End of Game", true);  
        d.setLayout( new FlowLayout() );  
        Button b = new Button ("OK");  
        b.addActionListener ( new ActionListener()  
        {  
            public void actionPerformed( ActionEvent e )  
            {  
                d.setVisible(false);  
            }  
        });  
        d.add( new Label ("Click button to continue."));  
        d.add(b);   
        d.setSize(300,300);    
        d.setVisible(true);      
    }

    
    public int selectedGameMode;
    public String selectedChessPosition=""; //FEN or orig
    public String moveList="";
    
    public void doClose()
    {
        frame.setVisible(false);
        frame.dispose();
    }
            
    
}
