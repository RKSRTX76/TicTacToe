package com.rksrtx76.tictactoe

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import com.rksrtx76.tictactoe.screens.TicTacTocGame
import com.rksrtx76.tictactoe.ui.theme.TicTacToeTheme
import com.rksrtx76.tictactoe.viewmodel.GameViewModel

class MainActivity : ComponentActivity() {
    private val viewModel by viewModels<GameViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TicTacToeTheme {
                TicTacTocGame(viewModel)
            }
        }
    }
}
