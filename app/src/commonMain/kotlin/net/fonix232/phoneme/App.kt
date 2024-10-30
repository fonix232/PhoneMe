package net.fonix232.phoneme

import androidx.compose.runtime.Composable
import net.fonix232.phoneme.ui.navigation.NavigationStack
import net.fonix232.phoneme.ui.theme.PhoneMeTheme
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    PhoneMeTheme {
        NavigationStack()
    }
}