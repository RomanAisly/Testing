package com.example.testing

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.testing.domain.navigation.BottomBar
import com.example.testing.domain.navigation.NavGraph
import com.example.testing.ui.theme.TestingTheme

class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navHostController = rememberNavController()
            TestingTheme {
                Scaffold(modifier = Modifier.fillMaxSize(),
                    bottomBar = { BottomBar(navHostController = navHostController) }) { innerPadding ->
                    NavGraph(
                        modifier = Modifier.padding(innerPadding),
                        navHostController = navHostController
                    )
                }
            }
        }
    }
}