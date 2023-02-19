/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.chg.view;

import java.awt.*;

/**
 *
 * @author carlosherrero
 */
public class ChessView extends Frame {
    ChessView(){
Button b=new Button("click me");
b.setBounds(30,100,80,30);// setting button position

add(b);//adding button into frame
setSize(300,300);//frame size 300 width and 300 height
setLayout(null);//no layout now bydefault BorderLayout
setVisible(true);//now frame willbe visible, bydefault not visible

}
public static void main(String args[]){

ChessView f=new ChessView();

}
}
