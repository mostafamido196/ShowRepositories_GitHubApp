import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.odcgithubrepoapp.R
import com.example.odcgithubrepoapp.presentation.screens.splashscreen.SplashScreenViewModel
import com.example.odcgithubrepoapp.presentation.theme.ODCGithubRepoAppTheme
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(goToHome: () -> Unit, goToBoarding: () -> Unit) {
    val viewModel: SplashScreenViewModel = hiltViewModel()
    val isFirstTime by viewModel.reposStateFlow.collectAsStateWithLifecycle()



    LaunchedEffect(isFirstTime) {
        delay(2000) // 2 seconds splash screen delay
        when (isFirstTime) {
            true -> {
                viewModel.setFirstTime(false)
                goToBoarding()
                // Move this to after successful navigation to boarding screen
                // viewModel.setFirstTime(false)
            }
            false -> goToHome()
            null -> {} // Still loading, do nothing
        }
    }

    // Splash Screen UI
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primary), // Dark background
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // GitHub logo from drawable
            Image(
                painter = painterResource(id = R.drawable.ic_github), // Replace with your XML drawable
                contentDescription = "GitHub Logo",
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .size(150.dp)
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Text: Show Repos
            Text(
                text = "Show Repos",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewSplashScreen() {
    ODCGithubRepoAppTheme {
        SplashScreen(goToHome = {}, goToBoarding = {})
    }
}
