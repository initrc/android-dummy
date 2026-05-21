package io.github.initrc.dummy

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import io.github.initrc.dummy.ui.feature.Feature
import io.github.initrc.dummy.ui.theme.DummyTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DummyTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Feature(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}
