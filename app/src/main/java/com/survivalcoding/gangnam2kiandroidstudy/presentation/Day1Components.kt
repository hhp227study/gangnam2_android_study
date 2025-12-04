package com.survivalcoding.gangnam2kiandroidstudy.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.BigButton
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.InputField
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.MediumButton
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.SmallButton
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.Tabs

@Composable
fun Day1Components() {
    var text by remember { mutableStateOf("") }
    var selectedFirstTab by remember { mutableStateOf(0) }
    var selectedSecondTab by remember { mutableStateOf(0) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Column {
            BigButton(
                text = "Button",
                onClick = {
                    println("클릭!!!!!!")
                }
            )
            Spacer(modifier = Modifier.height(36.dp))
            MediumButton(text = "Button")
            Spacer(modifier = Modifier.height(36.dp))
            SmallButton(text = "Button")
            Spacer(modifier = Modifier.height(36.dp))
            InputField("Label", text, onValueChange = { text = it }, placeholder = "Placeholder")
            Spacer(modifier = Modifier.height(36.dp))
            Tabs(listOf("Label", "Label"), selectedFirstTab, onTabSelected = { selectedFirstTab = it })
            Spacer(modifier = Modifier.height(36.dp))
            Tabs(listOf("Label", "Label", "Label"), selectedSecondTab, onTabSelected = { selectedSecondTab = it })
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Day1ComponentsPreview() {
    Day1Components()
}
