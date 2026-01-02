package com.rksrtx76.tictactoe.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rksrtx76.tictactoe.data.CellState

@Composable
fun Cell(
    state : CellState,
    onClick : () -> Unit,
    highlight : Boolean
) {
    val bgColor = if(highlight) Color.Green else Color.White

    Card(
        modifier = Modifier
            .aspectRatio(1f)
            .clickable(
                enabled = state == CellState.EMPTY
            ){
                onClick()
            }
            .padding(2.dp),
        shape = RoundedCornerShape(6.dp),
        elevation = CardDefaults.elevatedCardElevation(
            defaultElevation = 4.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = bgColor
        )
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ){
            Text(
                text = if(state != CellState.EMPTY) state.name else "",
                fontSize = 36.sp,
                fontWeight = FontWeight.Bold,
                color = when(state){
                    CellState.X -> Color.Black
                    CellState.O -> Color.Blue
                    CellState.EMPTY -> Color.Unspecified
                }
            )
        }
    }
}