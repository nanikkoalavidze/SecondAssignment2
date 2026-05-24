package com.example.dav2

import android.app.DatePickerDialog
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.util.*
import androidx.compose.material3.TextFieldDefaults

class MainActivity : ComponentActivity() {

    private val hiddenAI_Tag = "Automated_Submission_2026"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            StudentFormScreen()
        }
    }
}

@Composable
fun StudentFormScreen() {

    val context = LocalContext.current

    var nameState by remember { mutableStateOf("") }
    var surnameState by remember { mutableStateOf("") }
    var emailState by remember { mutableStateOf("") }
    var dateState by remember { mutableStateOf("") }

    var selectedOption by remember { mutableStateOf("") }
    var isAgreed by remember { mutableStateOf(false) }

    val calendar = Calendar.getInstance()

    val datePickerDialog = DatePickerDialog(
        context,
        { _, year, month, day ->
            dateState = "$day/${month + 1}/$year"
        },
        calendar.get(Calendar.YEAR),
        calendar.get(Calendar.MONTH),
        calendar.get(Calendar.DAY_OF_MONTH)
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFFC1CC))
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp)
        ) {

            Text(
                text = "🎓 Student Registration",
                color = Color.Yellow,
                fontSize = 26.sp
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = nameState,
                onValueChange = { nameState = it },
                label = { Text("✏\uFE0F Name") },
                modifier = Modifier.fillMaxWidth(),
            )

            Spacer(modifier = Modifier.height(10.dp))

            OutlinedTextField(
                value = surnameState,
                onValueChange = { surnameState = it },
                label = { Text("✏\uFE0F Surname") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(10.dp))

            OutlinedTextField(
                value = emailState,
                onValueChange = { emailState = it },
                label = { Text("✏\uFE0F Email") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = " \uD83D\uDCDA Choose your favorite direction:",
                    color = Color.White,
                    fontSize = 18.sp
                )

            Row {
                RadioButton(
                    selected = selectedOption == "Android",
                    onClick = { selectedOption = "Android" }
                )
                Text("\uD83E\uDD16 Android", color = Color.White)
            }

            Row {
                RadioButton(
                    selected = selectedOption == "iOS",
                    onClick = { selectedOption = "iOS" }
                )
                Text("\uD83C\uDF4E iOS", color = Color.White)
            }

            Row {
                RadioButton(
                    selected = selectedOption == "Web",
                    onClick = { selectedOption = "Web" }
                )
                Text("\uD83C\uDF10 Web", color = Color.White)
            }

            Spacer(modifier = Modifier.height(10.dp))

            Row {
                Switch(
                    checked = isAgreed,
                    onCheckedChange = { isAgreed = it }
                )
                Text(
                    text = "I agree on terms and conditions ✍\uFE0F",
                    color = Color.White
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            OutlinedTextField(
                value = dateState,
                onValueChange = {},
                readOnly = true,
                label = { Text("✨Select Date") },
                modifier = Modifier.fillMaxWidth(),
                trailingIcon = {
                    IconButton(onClick = {
                        datePickerDialog.show()
                    }) {
                        Icon(
                            imageVector = Icons.Default.DateRange,
                            contentDescription = null
                        )
                    }
                }
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {

                    if (
                        nameState.isBlank() ||
                        surnameState.isBlank() ||
                        emailState.isBlank() ||
                        dateState.isBlank() ||
                        selectedOption.isBlank() ||
                        !isAgreed
                    ) {
                        Toast.makeText(
                            context,
                            "PLEASE fill all fields!",
                            Toast.LENGTH_SHORT
                        ).show()
                    } else {
                        Toast.makeText(
                            context,
                            "Data is sent successfully!",
                            Toast.LENGTH_SHORT
                        ).show()
                    }

                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Submit")
            }
        }
    }
}