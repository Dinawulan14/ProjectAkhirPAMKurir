package com.example.projectakhirpamkurir

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.projectakhirpamkurir.data.Kurir
import com.example.projectakhirpamkurir.model.HomeViewModel
import com.example.projectakhirpamkurir.model.PenyediaViewModel
import com.example.projectakhirpamkurir.navigasi.DestinasiNavigasi

object DestinasiHome : DestinasiNavigasi {
    override val route = "home"
    override val titleRes = R.string.app_name
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navigateToItemEntry: () -> Unit,
    modifier: Modifier = Modifier,
    onDetailClick: (Int) -> Unit = {},
    viewModel: HomeViewModel = viewModel(factory = PenyediaViewModel.Factory)
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            KurirTopAppBar(
                title = stringResource(DestinasiHome.titleRes),
                canNavigateBack = false,
                scrollBehavior = scrollBehavior
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = navigateToItemEntry,
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_large))
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = stringResource(R.string.entry_kurir)
                )
            }
        },
    ) { innerPadding ->
        val uiStateKurir by viewModel.homeUiState.collectAsState()
        BodyHome(
            itemKurir = uiStateKurir.listKurir,
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            onKurirClick = onDetailClick
        )
    }
}

@Composable
fun BodyHome(
    itemKurir: List<Kurir>,
    modifier: Modifier = Modifier,
    onKurirClick: (Int) -> Unit = {}
){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ){
        if (itemKurir.isEmpty()) {
            Text(
                text = stringResource(R.string.deskripsi_no_item),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleLarge
            )
        } else {
            ListKurir(
                itemKurir = itemKurir,
                modifier = Modifier.padding(horizontal = dimensionResource(id = R.dimen.padding_small)),
                onItemClick = {onKurirClick(it.id)}
            )
        }
    }
}
@Composable
fun ListKurir(
    itemKurir: List<Kurir>,
    modifier: Modifier = Modifier,
    onItemClick: (Kurir) -> Unit

) {
    LazyColumn(modifier = Modifier){
        items(items = itemKurir, key = {it.id}){
                person ->
            DataKurir(
                kurir = person,
                modifier = Modifier
                    .padding(dimensionResource(id = R.dimen.padding_small))
                    .clickable { onItemClick(person) }
            )
        }
    }
}
