package com.example.projectakhirpamkurir.ui.theme.halaman

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.projectakhirpamkurir.R
import com.example.projectakhirpamkurir.model.DetailKurir
import com.example.projectakhirpamkurir.model.EntryViewModel
import com.example.projectakhirpamkurir.model.PenyediaViewModel
import com.example.projectakhirpamkurir.model.UIStateKurir
import com.example.projectakhirpamkurir.navigasi.DestinasiNavigasi
import com.example.projectakhirpamkurir.navigasi.KurirTopAppBar
import kotlinx.coroutines.launch

object DestinasiEntry: DestinasiNavigasi {
    override val route = "item_entry"
    override val titleRes = R.string.entry_kurir
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EntryKurirScreen(
    navigateBack: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: EntryViewModel = viewModel(factory = PenyediaViewModel.Factory)
){
    val coroutineScope = rememberCoroutineScope()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    Scaffold (
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            KurirTopAppBar(
                title = stringResource(DestinasiEntry.titleRes),
                canNavigateBack = true,
                scrollBehavior = scrollBehavior
            )
        }
    ){innerPadding ->
        EntryKurirBody(
            uiStateKurir = viewModel.uiStateKurir,
            onKurirValueChange = viewModel::updateUIState,
            onSaveClick = {
                coroutineScope.launch {
                    viewModel.saveKurir()
                    navigateBack()
                }
            },
            modifier = Modifier
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .fillMaxWidth()
        )
    }
}

@Composable
fun EntryKurirBody(
    uiStateKurir: UIStateKurir,
    onKurirValueChange: (DetailKurir) -> Unit,
    onSaveClick: () -> Unit,
    modifier: Modifier = Modifier
){
    Column(
        verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_large)),
        modifier = modifier.padding(dimensionResource(id = R.dimen.padding_medium))
    ){
        FormInputKurir(
            detailKurir = uiStateKurir.detailKurir,
            onValueChange = onKurirValueChange,
            modifier = Modifier.fillMaxWidth()
        )
        Button(
            onClick = onSaveClick,
            enabled = uiStateKurir.isEntryValid,
            shape = MaterialTheme.shapes.small,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(stringResource(R.string.btn_submit))
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormInputKurir(
    detailKurir: DetailKurir,
    modifier: Modifier = Modifier,
    onValueChange: (DetailKurir) -> Unit = {},
    enabled: Boolean = true
){
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_medium))
    ){
        OutlinedTextField(
            value = detailKurir.alamat,
            onValueChange = {onValueChange(detailKurir.copy(alamat = it))},
            label = { Text(stringResource(R.string.alamat)) },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )
        OutlinedTextField(
            value = detailKurir.telepon,
            onValueChange = {onValueChange(detailKurir.copy(telepon = it))},
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            label = { Text(stringResource(R.string.telepon)) },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )
        OutlinedTextField(
            value = detailKurir.beratbarang,
            onValueChange = {onValueChange(detailKurir.copy(beratbarang = it))},
            label = { Text(stringResource(R.string.berat_barang)) },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )
        OutlinedTextField(
            value = detailKurir.statuspengiriman,
            onValueChange = {onValueChange(detailKurir.copy(statuspengiriman = it))},
            label = { Text(stringResource(R.string.status_pengiriman)) },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )
        OutlinedTextField(
            value = detailKurir.namakurir,
            onValueChange = {onValueChange(detailKurir.copy(namakurir = it))},
            label = { Text(stringResource(R.string.nama_kurir)) },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )
        if (enabled){
            Text(
                text = stringResource(R.string.required_field),
                modifier = Modifier.padding(start = dimensionResource(id = R.dimen.padding_medium))
            )}
        Divider(
            thickness = dimensionResource(R.dimen.padding_small),
            modifier = Modifier.padding(bottom = dimensionResource(R.dimen.padding_medium))
        )
    }
}
