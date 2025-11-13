package com.example.demo3_starter.ui.screens

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun MoodScreen() {
    //List of possible moods and their associated colors.
    val moods = listOf(
        "Happy",
        "Sad",
        "Chilling",
        "Angry",
        "NA"
    )

    //The mood currently selected, initialized to NA
    var currentMood by remember { mutableStateOf(moods[4]) }

    //TODO 5: Change this so that it uses an animated state
    //The background color, initialized to white
    val backgroundColor by animateColorAsState(
        targetValue = getMoodColor(currentMood),
        animationSpec = tween(durationMillis = 1000)
    )

    Column(
        Modifier
            .fillMaxSize()
            .background(backgroundColor),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text("How are you today?", fontSize = 36.sp)

        //TODO 6: Change this so that it uses AnimatedContent
        //Displays the emoji
        AnimatedContent(
            targetState = currentMood,
            transitionSpec = {
                slideInHorizontally(initialOffsetX = { fullWidth ->
                    fullWidth
                }) togetherWith
                        slideOutHorizontally(targetOffsetX = {fullWidth -> -fullWidth})
            }
        ){ mood ->
            Text(
                text = getMoodEmoji(mood),
                fontSize = 200.sp
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        //Creates a Button for each item in moods
        for (mood in moods.subList(0, 4)) {
            Button(
                //TODO 5.5: remove redundant background color update
                onClick = {
                    currentMood = mood
                },
                modifier = Modifier.padding(4.dp)
            ) {
                Text(mood)
            }
        }
    }
}


/**
 * Returns the emoji associated with the given mood.
 * If the mood isn't recognized, returns the blank emoji.
 */
fun getMoodEmoji(mood: String): String {
    return when (mood) {
        "Happy" -> "😊"
        "Sad" -> "😢"
        "Chilling" -> "😌"
        "Angry" -> "😡"
        else -> "😶"
    }
}

/**
 * Returns the color associated with the given mood.
 * If the mood isn't recognized, returns white.
 */
fun getMoodColor(mood: String): Color {
    return when (mood) {
        "Happy" -> Color.Yellow
        "Sad" -> Color.Blue
        "Chilling" -> Color.Green
        "Angry" -> Color.Red
        else -> Color.White
    }
}

@Preview(showBackground = true)
@Composable
fun MoodScreenPreview() {
    MoodScreen()
}