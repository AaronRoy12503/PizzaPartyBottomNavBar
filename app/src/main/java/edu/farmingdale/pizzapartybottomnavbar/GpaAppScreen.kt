package edu.farmingdale.pizzapartybottomnavbar

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.SideEffect
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GpaAppScreen() {
    val systemUiController = rememberSystemUiController()
    val statusBarColor = Color(0xFF6550a4) // Purple color

    SideEffect {
        systemUiController.setStatusBarColor(
            color = statusBarColor,
            darkIcons = false // Set to true if you want dark status bar icons
        )
    }

    var grade1 by remember { mutableStateOf("") }
    var grade2 by remember { mutableStateOf("") }
    var grade3 by remember { mutableStateOf("") }

    // Declare variables for GPA result and background color
    var gpa by remember { mutableStateOf("") }
    var backColor by remember { mutableStateOf(Color.White) }

    val buttonColor = Color(0xFF6550A4)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Cyan)
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        // Customized TextField for Course 1 Grade
        OutlinedTextField(
            value = grade1,
            onValueChange = { grade1 = it },
            modifier = Modifier
                .padding(vertical = 8.dp),
            label = { Text("Course 1 Grade") },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color(0xFF69878f), // border color when focused
                unfocusedBorderColor = Color(0xFF69878f) // border color when not focused
            )
        )

        // Customized TextField for Course 2 Grade
        OutlinedTextField(
            value = grade2,
            onValueChange = { grade2 = it },
            modifier = Modifier
                .padding(vertical = 8.dp),
            label = { Text("Course 2 Grade") },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color(0xFF69878f),
                unfocusedBorderColor = Color(0xFF69878f)
            )
        )

        // Customized TextField for Course 3 Grade
        OutlinedTextField(
            value = grade3,
            onValueChange = { grade3 = it },
            modifier = Modifier
                .padding(vertical = 8.dp),
            label = { Text("Course 3 Grade") },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color(0xFF6550A4),
                unfocusedBorderColor = Color(0xFF6550A4)
            )
        )

        // "Compute GPA" Button
        Button(
            onClick = {
                val gpaVal = calGPA(grade1, grade2, grade3)
                if (gpaVal != null && !gpaVal.isNaN()) {
                    gpa = String.format("%.2f", gpaVal)

                    // Change background color based on GPA
                    backColor = when {
                        gpaVal < 60 -> Color.Red
                        gpaVal in 60.0..79.0 -> Color.Yellow
                        else -> Color.Green
                    }
                } else {
                    gpa = "Invalid input"
                    backColor = Color.White
                }
            },
            colors = ButtonDefaults.buttonColors(containerColor = buttonColor),
            modifier = Modifier
                .padding(top = 24.dp)
        ) {
            Text("Compute GPA")
        }

        // "Clear" Button
        Button(
            onClick = {
                // Reset all values to none
                grade1 = ""
                grade2 = ""
                grade3 = ""
                gpa = ""
                backColor = Color.White
            },
            colors = ButtonDefaults.buttonColors(containerColor = buttonColor),
            modifier = Modifier
                .padding(top = 8.dp)
        ) {
            Text("Clear")
        }

        if (gpa.isNotEmpty()) {
            Text(
                text = "GPA: $gpa",
                modifier = Modifier
                    .padding(top = 16.dp)
                    .background(backColor)
                    .padding(8.dp),
                style = MaterialTheme.typography.headlineMedium
            )
        }
    }
}

fun calGPA(grade1: String, grade2: String, grade3: String): Double {
    return try {
        val grades = listOf(grade1.toDouble(), grade2.toDouble(), grade3.toDouble())
        grades.average()
    } catch (e: NumberFormatException) {
        Double.NaN
    }
}

