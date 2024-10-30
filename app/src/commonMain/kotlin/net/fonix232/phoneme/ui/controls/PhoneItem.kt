package net.fonix232.phoneme.ui.controls

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import net.fonix232.phoneme.shared.model.Phone
import org.jetbrains.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PhoneItem(phone: Phone, onClickListener: () -> Unit = {}) {
    Card(modifier = Modifier
        .padding(horizontal = 16.dp, vertical = 4.dp)
        .combinedClickable(onClick = onClickListener)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        ) {
            Row(modifier = Modifier.fillMaxWidth()) { Text(text = phone.name) }
            phone.color?.let { Row(modifier = Modifier.fillMaxWidth()) { Text(text = it) } }
            phone.generation?.let { Row(modifier = Modifier.fillMaxWidth()) { Text(text = "$it generation") } }
        }
    }
}

@Preview
@Composable
fun PhoneItemPreview() {
    PhoneItem(Phone(id = 5, name = "Test", price = 599.99, color = "black", generation = "2"))
}
