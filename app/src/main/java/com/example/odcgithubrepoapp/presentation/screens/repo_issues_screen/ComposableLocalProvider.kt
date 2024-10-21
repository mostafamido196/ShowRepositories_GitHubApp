package com.example.odcgithubrepoapp.presentation.screens.repo_issues_screen

import androidx.compose.runtime.compositionLocalOf

val LocalOnShowIssueItemClicked = compositionLocalOf<(String) -> Unit> {
    { _ -> }
}