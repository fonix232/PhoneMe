package net.fonix232.phoneme.ui.screens.phone

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import multiplatform.network.cmptoast.showToast
import net.fonix232.phoneme.repositories.PhoneRepository
import net.fonix232.phoneme.ui.controls.PhoneItem
import net.fonix232.phoneme.ui.navigation.PhoneDetail
import net.fonix232.phoneme.viewmodel.PhoneListViewModel
import org.koin.compose.viewmodel.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PhoneListScreen(navController: NavController, viewModel: PhoneListViewModel = koinViewModel()) {
    val repositoryState by viewModel.state.collectAsState()
    val phones by viewModel.phones.collectAsState(emptyList())

    if (repositoryState is PhoneRepository.RepositoryState.ERROR) {
        showToast(
            "Exception: ${(repositoryState as PhoneRepository.RepositoryState.ERROR).exception.message}"
        )
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = { TopAppBar(title = { Text("PhoneMe") }) },
        floatingActionButtonPosition = FabPosition.End,
        floatingActionButton = {
            FloatingActionButton(onClick = { viewModel.addPhone(phones.size + 1) }) {
                Text("+")
            }
        }
    ) { padding ->
        PullToRefreshBox(
            isRefreshing = repositoryState.isLoading,
            onRefresh = { viewModel.refreshPhones() },
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxSize()
            ) {
                items(phones) { phone ->
                    PhoneItem(
                        phone = phone,
                        onClickListener = { navController.navigate(PhoneDetail(phone.id.toInt())) }
                    )
                }
            }
        }
    }
}

