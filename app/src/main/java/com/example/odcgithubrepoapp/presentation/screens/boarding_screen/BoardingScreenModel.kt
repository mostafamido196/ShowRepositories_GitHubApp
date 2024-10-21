import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.odcgithubrepoapp.presentation.theme.ODCGithubRepoAppTheme

@Composable
fun BoardingScreen(toRepoListScreen: () -> Unit) {
    val screens = listOf(
        BoardingScreenModel(
            title = "Welcome to GitHub Universe",
            description = "Explore repositories like never before",
            icon = Icons.Default.Star,
            iconColor = Color(0xFFFFA000)
        ),
        BoardingScreenModel(
            title = "Discover Trending Repos",
            description = "Find popular projects across the cosmos",
            icon = Icons.Default.Star,
            iconColor = Color(0xFF4CAF50)
        ),
        BoardingScreenModel(
            title = "Powerful Search",
            description = "Locate any repository in the universe",
            icon = Icons.Default.Search,
            iconColor = Color(0xFF2196F3)
        ),
        BoardingScreenModel(
            title = "Organize Your Stars",
            description = "Curate your own galaxy of favorite repos",
            icon = Icons.Default.Star,
            iconColor = Color(0xFFE91E63)
        )
    )

    var currentScreen by remember { mutableStateOf(0) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        screens.forEachIndexed { index, screen ->
            AnimatedVisibility(
                visible = currentScreen == index,
                modifier = Modifier.fillMaxSize()
            ) {
                BoardingScreenContent(screen)
            }
        }

        // Skip button
        TextButton(
            onClick = toRepoListScreen,
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(16.dp)
        ) {
            Text("Skip", color = Color(0xFF1976D2), fontSize = 16.sp, fontWeight = FontWeight.Bold)
        }

        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Indicator dots
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                screens.indices.forEach { index ->
                    IndicatorDot(isSelected = currentScreen == index)
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Navigation button
            Button(
                onClick = {
                    if (currentScreen < screens.size - 1) {
                        currentScreen++
                    } else {
                        toRepoListScreen()
                    }
                },
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .height(56.dp)
                    .clip(RoundedCornerShape(28.dp)),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1976D2))
            ) {
                Text(
                    text = if (currentScreen < screens.size - 1) "Next" else "Get Started",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.width(8.dp))
                Icon(Icons.Default.ArrowForward, contentDescription = null)
            }
        }
    }
}

@Composable
fun BoardingScreenContent(screen: BoardingScreenModel) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        val infiniteRotation = rememberInfiniteTransition()
        val rotation by infiniteRotation.animateFloat(
            initialValue = 0f,
            targetValue = 360f,
            animationSpec = infiniteRepeatable(
                animation = tween(durationMillis = 20000, easing = LinearEasing),
                repeatMode = RepeatMode.Restart
            )
        )

        Icon(
            imageVector = screen.icon,
            contentDescription = null,
            tint = screen.iconColor,
            modifier = Modifier
                .size(120.dp)
                .rotate(rotation)
        )

        Spacer(modifier = Modifier.height(48.dp))

        Text(
            text = screen.title,
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF333333),
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = screen.description,
            fontSize = 18.sp,
            color = Color(0xFF666666),
            modifier = Modifier.padding(horizontal = 32.dp),
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun IndicatorDot(isSelected: Boolean) {
    val size by animateDpAsState(targetValue = if (isSelected) 10.dp else 8.dp)
    Box(
        modifier = Modifier
            .size(size)
            .background(
                color = if (isSelected) Color(0xFF1976D2) else Color.LightGray,
                shape = CircleShape
            )
    )
}

data class BoardingScreenModel(
    val title: String,
    val description: String,
    val icon: ImageVector,
    val iconColor: Color
)

@Preview(showBackground = true)
@Composable
fun PreviewBoardingScreen() {
    ODCGithubRepoAppTheme {
        BoardingScreen(toRepoListScreen = {})
    }
}