package com.rksrtx76.tictactoe.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.rksrtx76.tictactoe.data.CellState
import com.rksrtx76.tictactoe.data.GameState
import com.rksrtx76.tictactoe.data.Player


class GameViewModel : ViewModel(){
    // Board(3x3)
    var board = mutableStateListOf(
        mutableStateListOf(CellState.EMPTY, CellState.EMPTY, CellState.EMPTY),
        mutableStateListOf(CellState.EMPTY, CellState.EMPTY, CellState.EMPTY),
        mutableStateListOf(CellState.EMPTY, CellState.EMPTY, CellState.EMPTY),
    )
        private set       // to ensure no one can modify board from outside but can access


    var currentPlayer = mutableStateOf(Player.X)
        private set   


    var gameStatus = mutableStateOf(GameState.IN_PROGRESS)
        private set

    var message = mutableStateOf("Turn : X")
        private set


    var winningCells = mutableStateListOf<Pair<Int, Int>>()
        private set

    fun makeMove(row : Int, col : Int){
        if(gameStatus.value != GameState.IN_PROGRESS) return
        if(board[row][col] != CellState.EMPTY) return

        board[row][col] = if(currentPlayer.value == Player.X) CellState.X else CellState.O

        // switch player
        currentPlayer.value = if(currentPlayer.value == Player.X) Player.O else Player.X
        // next turn
        message.value = "Turn : ${currentPlayer.value.name}"

        // check win
        val winner = checkWinner()
        if(winner != null){
            gameStatus.value = if(winner == Player.X) GameState.X_WON else GameState.Y_WON
            message.value = "Player ${winner.name} Won!"
            return
        }
        // check draw
        if(isBoardFull()){
            gameStatus.value = GameState.DRAW
            message.value = "Game Draw!"
            return
        }


    }

    private fun checkWinner() : Player?{
        // check for Row
        for(row in 0..2){
            if(board[row][0] != CellState.EMPTY && board[row][0] == board[row][1] && board[row][1] == board[row][2]){
                winningCells.addAll(
                    listOf(
                        Pair(row, 0),
                        Pair(row, 1),
                        Pair(row, 2),
                    )
                )
                return if(board[row][0] == CellState.X) Player.X else Player.O
            }
        }
        // for Column
        for(col in 0..2){
            if(board[0][col] != CellState.EMPTY && board[0][col] == board[1][col] && board[1][col] == board[2][col]){
                winningCells.addAll(
                    listOf(
                        Pair(0, col),
                        Pair(1, col),
                        Pair(2, col),
                    )
                )
                return if(board[0][col] == CellState.X) Player.X else Player.O
            }
        }
        // for diagonal
        if(board[0][0] != CellState.EMPTY && board[0][0] == board[1][1] && board[1][1] == board[2][2]){
            winningCells.addAll(
                listOf(
                    Pair(0, 0),
                    Pair(1, 1),
                    Pair(2, 2),
                )
            )
            return if(board[0][0] == CellState.X) Player.X else Player.O
        }
        // for Anti-digonal
        if(board[0][2] != CellState.EMPTY && board[0][2] == board[1][1] && board[1][1] == board[2][0]){
            winningCells.addAll(
                listOf(
                    Pair(0, 2),
                    Pair(1, 1),
                    Pair(2, 0),
                )
            )
            return if(board[0][2] == CellState.X) Player.X else Player.O
        }

        return null
    }

    fun restartGame(){
        for(row in 0..2){
            for(col in 0..2){
                board[row][col] = CellState.EMPTY
            }
        }

        currentPlayer.value = Player.X
        gameStatus.value = GameState.IN_PROGRESS
        message.value = "Turn : X"
        winningCells.clear()
    }
    fun isBoardFull() : Boolean{
        return board.all { row->
            row.all {
                it != CellState.EMPTY
            }
        }
    }


}