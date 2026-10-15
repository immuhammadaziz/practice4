package com.example.practice4

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.practice4.mvc.view.MvcActivity
import com.example.practice4.mvp.view.MvpActivity
import com.example.practice4.mvvm.view.MvvmActivity
import com.example.practice4.mvi.view.MviActivity

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MaterialTheme {
                Launcher(onOpen = { target -> startActivity(Intent(this, target)) })
            }
        }
    }
}
 
@Composable
private fun Launcher(onOpen: (Class<*>) -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize().safeDrawingPadding().padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        Text("Weather: four patterns", style = MaterialTheme.typography.headlineSmall)
        Button(onClick = { onOpen(MvcActivity::class.java) }) { Text("MVC") }
        Button(onClick = { onOpen(MvpActivity::class.java) }) { Text("MVP") }
        Button(onClick = { onOpen(MvvmActivity::class.java) }) { Text("MVVM") }
        Button(onClick = { onOpen(MviActivity::class.java) }) { Text("MVI") }
    }
}
