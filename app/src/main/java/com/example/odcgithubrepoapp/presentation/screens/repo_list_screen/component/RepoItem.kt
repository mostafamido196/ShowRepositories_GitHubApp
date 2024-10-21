package com.example.odcgithubrepoapp.presentation.screens.repo_list_screen.component

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.odcgithubrepoapp.R
import com.example.odcgithubrepoapp.presentation.screens.repo_list_screen.model.GithubReposUiModel
import com.example.odcgithubrepoapp.presentation.screens.repo_list_screen.preview_data.fakeRepoUiModelList
import com.example.odcgithubrepoapp.presentation.theme.ODCGithubRepoAppTheme
import com.example.odcgithubrepoapp.presentation.theme.Yellow

@Composable
fun RepoItem(
    githubRepoUiModel: GithubReposUiModel,
    onRepoItemClicked: (String, String) -> Unit,
) {
    val imageCrossFadeAnimationDuration = 1000
    var isExpanded by remember { mutableStateOf(false) }
    var isAnyTextOverflowing by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp)
            .background(
                color = MaterialTheme.colorScheme.onSecondary,
                shape = MaterialTheme.shapes.medium
            )
            .clickable {
                onRepoItemClicked(githubRepoUiModel.ownerName, githubRepoUiModel.name)
            }
            .animateContentSize()
    ) {
        Row(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.onSecondary)
        ) {
            Image(
                painter = rememberAsyncImagePainter(
                    ImageRequest.Builder(LocalContext.current)
                        .data(data = githubRepoUiModel.avatarUrl).apply {
                            crossfade(imageCrossFadeAnimationDuration)
                            placeholder(R.drawable.ic_github_placeholser)
                            error(R.drawable.ic_github_placeholser)
                        }.build()
                ),
                contentDescription = null,
                modifier = Modifier
                    .padding(top = 12.dp, start = 12.dp)
                    .size(50.dp)
                    .clip(CircleShape)
            )

            Column(
                Modifier.padding(12.dp)
            ) {
                Row {
                    Text(
                        text = githubRepoUiModel.name,
                        maxLines = if (isExpanded) Int.MAX_VALUE else 1,
                        overflow = TextOverflow.Ellipsis,
                        style = MaterialTheme.typography.titleMedium,
                        modifier = Modifier.weight(1f),
                        onTextLayout = { textLayoutResult ->
                            if (!isExpanded && textLayoutResult.hasVisualOverflow) {
                                isAnyTextOverflowing = true
                            }
                        }
                    )
                    Text(
                        text = githubRepoUiModel.starsCount,
                        style = MaterialTheme.typography.bodySmall,
                    )
                    Icon(
                        painter = painterResource(R.drawable.ic_star),
                        contentDescription = null,
                        tint = Yellow,
                        modifier = Modifier.padding(horizontal = 8.dp)
                    )
                }

                Row {
                    Text(
                        modifier = Modifier
                            .padding(end = 20.dp)
                            .weight(1f),
                        text = githubRepoUiModel.ownerName,
                        maxLines = if (isExpanded) Int.MAX_VALUE else 1,
                        overflow = TextOverflow.Ellipsis,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurface,
                        onTextLayout = { textLayoutResult ->
                            if (!isExpanded && textLayoutResult.hasVisualOverflow) {
                                isAnyTextOverflowing = true
                            }
                        }
                    )

                    if (isAnyTextOverflowing || isExpanded) {
                        Surface(
                            modifier = Modifier
                                .size(32.dp)
                                .clickable {
                                    isExpanded = !isExpanded
                                },
                            shadowElevation = 4.dp,
                            shape = CircleShape
                        ) {
                            Image(
                                painter = painterResource(
                                    id = if (isExpanded) R.drawable.baseline_keyboard_arrow_up_24 else R.drawable.baseline_keyboard_arrow_down_24
                                ),
                                contentDescription = if (isExpanded) "Collapse" else "Expand",
                                modifier = Modifier.size(24.dp),
                                colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onBackground)
                            )
                        }
                    }
                }

                Text(
                    githubRepoUiModel.description,
                    color = MaterialTheme.colorScheme.onSurface,
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.padding(top = 12.dp, end = 20.dp),
                    maxLines = if (isExpanded) Int.MAX_VALUE else 1,
                    overflow = TextOverflow.Ellipsis,
                    onTextLayout = { textLayoutResult ->
                        if (!isExpanded && textLayoutResult.hasVisualOverflow) {
                            isAnyTextOverflowing = true
                        }
                    }
                )
            }
        }
    }
}

@Preview
@Composable
private fun PreviewRepoItem() {
    ODCGithubRepoAppTheme {
        RepoItem(
            githubRepoUiModel = fakeRepoUiModelList.first(),
            onRepoItemClicked = { _, _ ->
            }
        )
    }
}