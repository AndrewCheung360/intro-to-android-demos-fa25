package com.cornellappdev.todolist.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cornellappdev.todolist.ui.components.TodoCard

@Composable
fun TodoScreen() {
    var newTodo by remember { mutableStateOf("") }
    val todoList = remember { mutableStateListOf<String>() }
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        Text(
            text = "My Todo List!",
            fontSize = 36.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(30.dp))
        TextField(
            value = newTodo,
            onValueChange = { newTodo = it },
            placeholder = { Text(text = "new todo...") }
        )
        Button(
            onClick = {
                if (newTodo.isNotBlank()) {
                    todoList.add(newTodo)
                    newTodo = ""
                }
            }
        ) {
            Text(text = "Add Todo")
        }
        Spacer(modifier = Modifier.height(30.dp))
        LazyColumn(
            state = rememberLazyListState(),
            modifier = Modifier.height(400.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items(todoList) {
                TodoCard(todoText = it)
            }
        }

    }
}

@Preview(showBackground = true)
@Composable
fun TodoScreenPreview() {
    TodoScreen()
}