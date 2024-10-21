package com.example.odcgithubrepoapp.presentation.screens.repo_issues_screen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.odcgithubrepoapp.presentation.screens.repo_issues_screen.model.RepoIssueItemUiModel


@Composable
fun IssueItem(issueItem: RepoIssueItemUiModel, onIssueItemClicked: (String) -> Unit = {}) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp)
            .background(
                color = MaterialTheme.colorScheme.surface,
                shape = MaterialTheme.shapes.medium
            )
            .clickable {
                onIssueItemClicked(issueItem.htmlUrl)
            }
    ) {
        Box(
            modifier = Modifier
                .padding(top = 12.dp, start = 12.dp)
                .size(50.dp)
                .clip(CircleShape)
        ) {
            RingWithPoint()
        }
        Column(
            Modifier.padding(8.dp)
        ) {
            Spacer(Modifier.height(10.dp))
            Row(
                Modifier.padding(end = 8.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = issueItem.title,
                    maxLines = 1,
                    style = MaterialTheme.typography.titleMedium,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier
                        .padding(end = 10.dp)
                        .weight(1f)
                )
                Text(
                    text = issueItem.state,
                    style = MaterialTheme.typography.bodySmall
                )
            }

            Spacer(Modifier.height(4.dp))
            Text(
                issueItem.authorAssociation,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurface
            )
            Spacer(Modifier.height(4.dp))
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    "Created At: ",
                    style = MaterialTheme.typography.titleSmall,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Text(
                    issueItem.createdAt,
                    color = MaterialTheme.colorScheme.onSurface,
                    style = MaterialTheme.typography.bodySmall,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }

        }

    }
}

@Preview
@Composable
private fun PreviewIssueItem() {
    //  ODCGithubRepoAppTheme {
    IssueItem(
        RepoIssueItemUiModel(
            124,
            "2024-10-8, 8:00 Am",
            "Bump Bump Payarrow from 7 Payer Bump Payarrow from 7 Payer",
            "",
            "Open",
            "None"
        )
    )
    // }
}