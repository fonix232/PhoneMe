package net.fonix232.phoneme.ui.screens.phone

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import net.fonix232.phoneme.viewmodel.PhoneDetailViewModel
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun PhoneDetailScreen(
    navController: NavController,
    phoneId: Int = -1,
    viewModel: PhoneDetailViewModel = koinViewModel(
        key = phoneId.toString(),
        parameters = { parametersOf(phoneId) }
    )
) {
    val phone by viewModel.phone.collectAsState()

    phone?.let { phone ->
        Box(
            modifier = Modifier
                .padding(horizontal = 24.dp)
                .fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.align(Alignment.TopCenter).fillMaxWidth()
            ) {
                Text(
                    text = "Phone Details",
                    //color = Color.White,
                    fontSize = 32.sp,
                    modifier = Modifier
                        .padding(vertical = 16.dp)
                        .align(Alignment.CenterHorizontally)
                )
                Text(
                    text = "${phone.name}",
                    //color = Color.White,
                    fontSize = 24.sp,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                phone.color?.let {
                    Text(
                        text = "Color: $it",
                        //color = Color.White,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                }
                phone.capacity?.let {
                    Text(
                        text = "Capacity: $it",
                        //color = Color.White,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                }
                phone.generation?.let {
                    Text(
                        text = "Generation: $it",
                        //color = Color.White,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                }
                phone.lastUpdated?.let {
                    Text(
                        text = "Last Updated: $it",
                        //color = Color.White,
                        fontSize = 11.sp,
                        modifier = Modifier.padding(top = 16.dp)
                    )
                }
            }

            phone.price?.let {
                Text(
                    text = "$it£",
                    Modifier
                        .align(Alignment.BottomEnd)
                        .padding(top = 48.dp),
                    //color = Color.White,
                )
            }
        }
    }
}