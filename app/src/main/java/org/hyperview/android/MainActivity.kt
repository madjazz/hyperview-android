package org.hyperview.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import org.hyperview.android.ui.theme.HyperviewTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        CoroutineScope(Dispatchers.IO).launch {
            val xml = HyperviewHttpService.get()
            withContext(Dispatchers.Main) {
                enableEdgeToEdge()
                setContent {
                    HyperviewTheme {
                        Hyperview(xml)
                    }
                }
            }
        }
    }
}
