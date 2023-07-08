package com.example.jetbmicalculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetbmicalculator.ui.theme.JetBmiCalculatorTheme

class MainActivity : ComponentActivity() {
    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetBmiCalculatorTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column(
                        horizontalAlignment = Alignment.Start,
                        modifier = Modifier.padding(32.dp),
                    ) {
                        Text(
                            text = "BMI計算アプリ",
                            color = MaterialTheme.colorScheme.onBackground,
                            fontSize = 24.sp,
                            fontWeight = FontWeight.ExtraBold,
                        )
                        Spacer(modifier = Modifier.height(32.dp))

                        // 身長
                        PinkLabeledTextField(
                            title = "身長（cm）",
                            value = viewModel.height,
                            onValueChange = { viewModel.height = it },
                            placeHolder = "170",
                        )
                        Spacer(modifier = Modifier.height(16.dp))

                        // 体重
                        PinkLabeledTextField(
                            title = "体重（kg）",
                            value = viewModel.weight,
                            onValueChange = { viewModel.weight = it },
                            placeHolder = "65",
                        )
                        Spacer(modifier = Modifier.height(24.dp))

                        Button(
                            modifier = Modifier.fillMaxWidth(),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(0xFFF85F6A)
                            ),
                            onClick = { viewModel.calculateBMI() },
                        ) {
                            Text(
                                text = "計算する",
                                color = Color.White,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.ExtraBold,
                            )
                        }
                        Spacer(modifier = Modifier.height(24.dp))

                        // 計算結果
                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            textAlign = TextAlign.Center,
                            text = "あなたのBMIは ${viewModel.bmi} です。",
                            color = viewModel.judgedColor,
                            fontSize = 24.sp,
                            fontWeight = FontWeight.ExtraBold,

                            )
                    }
                }
            }
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    private fun PinkLabeledTextField(
        value: String,
        onValueChange: (String) -> Unit,
        title: String,
        placeHolder: String,
    ) {
        Text(
            text = title,
            color = Color(0xFFF85F6A),
            fontWeight = FontWeight.Bold,
        )
        Spacer(modifier = Modifier.height(4.dp))
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = value,
            onValueChange = onValueChange,
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color.Transparent
            ),
            placeholder = {
                Text(
                    text = placeHolder,
                    color = Color.LightGray,
                )
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            singleLine = true,
        )


    }
}

