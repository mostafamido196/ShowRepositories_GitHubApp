package com.example.odcgithubrepoapp.presentation.screens.repo_list_screen.component;

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction

@Composable
fun CustomSearchBar(
    searchQuery: String,  // Pass searchQuery as a parameter
    onSearchQueryChanged: (String) -> Unit,  // Pass function to update search query
    modifier: Modifier = Modifier,
    onSearch: (String) -> Unit,
) {
    OutlinedTextField(
        value = searchQuery,  // Use the passed searchQuery
        onValueChange = { query ->
            onSearchQueryChanged(query)  // Update the searchQuery state outside
            onSearch(query.trim())  // Call onSearch when query changes
        },
        maxLines = 1, // Limit to a single line
        keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Search),
        label = { Text("Search Repos") },
        modifier = modifier
            .fillMaxWidth(),
        textStyle = MaterialTheme.typography.titleMedium,
        colors = OutlinedTextFieldDefaults.colors(
            cursorColor = Color.Green,  // Color of the cursor
            focusedBorderColor = Color.Blue,  // Border color when focused
            unfocusedBorderColor = MaterialTheme.colorScheme.onPrimary,  // Border color when not focused
            focusedLabelColor = Color.Blue,  // Label color when focused
            unfocusedLabelColor = Color.Black,  // Label color when not focused


        ),
    )
}
//MaterialTheme.colorScheme.primary

val languages = arrayOf(
    "javascript",
    "python",
    "java",
    "csharp",
    "ruby",
    "php",
    "go",
    "rust",
    "typescript",
    "swift",
    "kotlin",
    "cpp",  // C++
    "c",
    "dart",
    "scala"
)