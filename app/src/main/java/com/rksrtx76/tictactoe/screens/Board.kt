package com.rksrtx76.tictactoe.screens

import android.annotation.SuppressLint
import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import com.rksrtx76.tictactoe.data.CellState

@SuppressLint("ConfigurationScreenWidthHeight")
@Composable
fun Board(
    board : List<MutableList<CellState>>,
    winningCells : List<Pair<Int, Int>>,
    onCellClick : (row : Int, column : Int) -> Unit
){
    // For Responsive board we need device screen details
    val config = LocalConfiguration.current
    val size = if(config.orientation == Configuration.ORIENTATION_LANDSCAPE){
        (config.screenWidthDp.dp - 64.dp)
            .coerceAtMost(480.dp)
    }else{
        (config.screenWidthDp.dp - 48.dp)
            .coerceAtMost(360.dp)
    }


    Column(
        modifier = Modifier
            .size(size)
            .padding(8.dp),
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        for(row in 0..2){
            Row(
                modifier = Modifier.weight(1f),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                for(col in 0..2){
                    val highlight = winningCells.contains(Pair(row, col))

                    Cell(
                        state = board[row][col],
                        onClick = {
                            onCellClick(row, col)
                        },
                        highlight = highlight
                    )
                }
            }
        }
    }

}