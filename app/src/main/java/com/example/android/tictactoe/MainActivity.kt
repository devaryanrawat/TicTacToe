package com.example.android.tictactoe

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {
    var Player=true
    var TurnCount=0
    var Xwins=0
    var Owins=0
    var Draw=0;
    var BoardStatus=Array(3){
        IntArray(3)
    }

    lateinit var Matrix: Array<Array<Button>>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Matrix = arrayOf(
                arrayOf(Button1,Button2,Button3),
                arrayOf(Button4,Button5,Button6),
                arrayOf(Button7,Button8,Button9)
        )
        for(i in Matrix){
            for(button in i){
                button.setOnClickListener(this)
            }
        }
        initialiseBoardStatus()
        ButtonReset.setOnClickListener{
            Player=true
            TurnCount=0
            displayTv.text="Player X Turn"
            for(i in 0..2){
                for (j in 0..2){
                    BoardStatus[i][j]=-1
                    Matrix[i][j].isEnabled=true
                    Matrix[i][j].text=""
                }
            }
        }
    }
    private fun initialiseBoardStatus(){
        for(i in 0..2){
            for (j in 0..2){
                BoardStatus[i][j]=-1
                Matrix[i][j].isEnabled=true
                Matrix[i][j].text=""
            }
        }
    }
    override fun onClick(view: View) {
        when(view.id){
            R.id.Button1->{
                UpdateValue(row=0,col=0,TurnCount,Player)
            }
            R.id.Button2->{
                UpdateValue(row=0,col=1,TurnCount,Player)
            }
            R.id.Button3->{
                UpdateValue(row=0,col=2,TurnCount,Player)
            }
            R.id.Button4->{
                UpdateValue(row=1,col=0,TurnCount,Player)
            }
            R.id.Button5->{
                UpdateValue(row=1,col=1,TurnCount,Player)
            }
            R.id.Button6->{
                UpdateValue(row=1,col=2,TurnCount,Player)
            }
            R.id.Button7->{
                UpdateValue(row=2,col=0,TurnCount,Player)
            }
            R.id.Button8->{
                UpdateValue(row=2,col=1,TurnCount,Player)
            }
            R.id.Button9->{
                UpdateValue(row=2,col=2,TurnCount,Player)
            }
        }
        Player=!(Player)
        TurnCount++
        if(Player){
            updateDisplay("Player X Turn")
        }
        else{
            updateDisplay("Player O Turn")
        }
        var flag :Boolean = checkWinner()
        if(TurnCount==9&&!(flag)){
            Draw++;
            updateDisplay("Game Draw")
        }
    }
    private fun checkWinner ():Boolean{
        for(i in 0..2){
            if(BoardStatus[i][0]!=-1&&BoardStatus[i][0]==BoardStatus[i][1]&&BoardStatus[i][1]==BoardStatus[i][2]){
                if(Player) {
                    updateDisplay("Player O Won")
                    return true
                }
                else {
                    updateDisplay("Player X Won")
                    return true
                }
                break
            }
        }
        for(j in 0..2){
            if(BoardStatus[0][j]!=-1&&BoardStatus[0][j]==BoardStatus[1][j]&&BoardStatus[1][j]==BoardStatus[2][j]){
                if(Player) {
                    updateDisplay("Player O Won")
                    return true
                }
                else {
                    updateDisplay("Player X Won")
                    return true
                }
                break
            }
        }
        if(BoardStatus[0][0]!=-1&&BoardStatus[0][0]==BoardStatus[1][1]&&BoardStatus[1][1]==BoardStatus[2][2]){
            if(Player) {
                updateDisplay("Player O Won")
                return true
            }
            else {
                updateDisplay("Player X Won")
                return true
            }
        }
        if(BoardStatus[2][0]!=-1&&BoardStatus[2][0]==BoardStatus[1][1]&&BoardStatus[1][1]==BoardStatus[0][2]){
            if(Player) {
                updateDisplay("Player O Won")
                return true
            }
            else {
                updateDisplay("Player X Won")
                return true
            }
        }
        return false
    }
    private fun updateDisplay(text:String){
        displayTv.text=text
        if(text.contains("Won")){
            for(i in 0..2){
                for (j in 0..2){
                    Matrix[i][j].isEnabled=false
                }
            }
        }
        if(text.contains("X Won")){
            Xwins++;
        }
        else if(text.contains("O Won")){
            Owins++;
        }
        TextDraw.text="Draws:"+Draw
        TextXWin.text="X Wins:"+Xwins
        TextOWin.text="O Wins:"+Owins
    }

    private fun UpdateValue(row: Int,col: Int,TurnCount: Int, Player: Boolean){
        val text= if(Player) "X" else "O"
        val value= if(Player) 1 else 0
        Matrix[row][col].apply{
            isEnabled=false;
            setText(text)
        }
        BoardStatus[row][col]=value
    }
}