package dev.rrohaill.parkfree

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.selection.selectable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.MultiplePermissionsState
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.Priority
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.api.model.RectangularBounds
import com.google.android.libraries.places.api.net.PlacesClient
import com.google.android.libraries.places.api.net.SearchByTextRequest
import com.google.maps.android.SphericalUtil
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.MarkerComposable
import com.google.maps.android.compose.rememberCameraPositionState
import com.google.maps.android.compose.rememberMarkerState
import dev.rrohaill.designsystem.input.DsPrimaryButton
import dev.rrohaill.designsystem.input.DsSecondaryButton
import dev.rrohaill.designsystem.models.UiImage
import dev.rrohaill.designsystem.standard.DsIcon
import dev.rrohaill.designsystem.standard.DsText
import dev.rrohaill.designsystem.standard.IconSize
import dev.rrohaill.designsystem.text.uiText
import kotlinx.coroutines.tasks.await

sealed class ButtonFilter(val query: String, val text: String) {
    data class FreeParking(val buttonText: String = "Free parking") :
        ButtonFilter("Free parking near me", buttonText)

    data class AllParking(val buttonText: String = "All parking") :
        ButtonFilter("Parking near me", buttonText)
}

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun MapScreenWithPermissions(
    fusedLocationClient: FusedLocationProviderClient,
    placesClient: PlacesClient
) {
    val defaultLocation = rememberSaveable {
        LatLng(37.7749, -122.4194) // San Francisco
    }
    val context = LocalContext.current
    val permissionState = rememberMultiplePermissionsState(
        permissions = listOf(
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
        )
    )

    var currentLocation by remember { mutableStateOf<LatLng?>(null) }
    var isLoading by remember { mutableStateOf(false) }

    LaunchedEffect(permissionState.allPermissionsGranted) {
        if (permissionState.allPermissionsGranted) {
            isLoading = true
            try {
                val location = getCurrentLocation(
                    context = context,
                    defaultLocation = defaultLocation,
                    fusedLocationClient = fusedLocationClient
                )
                currentLocation = location
            } catch (e: Exception) {
                // Handle location error
                Toast.makeText(context, "Location error: ${e.message}", Toast.LENGTH_SHORT).show()
            } finally {
                isLoading = false
            }
        }
    }

    when {
        permissionState.allPermissionsGranted -> {
            MapContent(
                currentLocation = currentLocation,
                placesClient = placesClient,
                defaultLocation = defaultLocation,
                isLoading = isLoading
            )
        }

        permissionState.shouldShowRationale -> {
            RationaleDialog(permissionState)
        }

        else -> {
            PermissionRequest(permissionState)
        }
    }
}

@Composable
fun MapContent(
    currentLocation: LatLng?,
    placesClient: PlacesClient,
    defaultLocation: LatLng,
    isLoading: Boolean
) {
    var searchQuery by remember { mutableStateOf(ButtonFilter.FreeParking().query) }
    var placesList by remember { mutableStateOf<List<Place>>(emptyList()) }

    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(
            currentLocation ?: defaultLocation,
            14f
        )
    }

    LaunchedEffect(currentLocation) {
        cameraPositionState.animate(
            update = CameraUpdateFactory.newLatLngZoom(
                currentLocation ?: defaultLocation,
                14f
            )
        )
    }

    LaunchedEffect(searchQuery, currentLocation) {
        if (searchQuery.isNotEmpty() && currentLocation != null) {
            fetchPlaceDetails(
                searchQuery = searchQuery,
                currentLocation = currentLocation,
                placesClient = placesClient
            ) { places ->
                placesList = places
            }
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        GoogleMap(
            modifier = Modifier.fillMaxSize(),
            cameraPositionState = cameraPositionState,
            properties = MapProperties(isMyLocationEnabled = true),
            uiSettings = MapUiSettings(
                zoomControlsEnabled = true,
                myLocationButtonEnabled = true
            )
        ) {
            currentLocation?.let { location ->
                MarkerComposable(
                    state = rememberMarkerState(position = currentLocation),
                    title = "Current Location",
                ) {
                    DsIcon(
                        icon = UiImage.of(Icons.Default.Person)
                    )
                }
            }
            placesList.map { place ->
                MarkerComposable(
                    state = rememberMarkerState(position = place.location!!),
                    title = place.displayName ?: "",
                ) {
                    DsIcon(
                        icon = UiImage.CoilImage(
                            model = place.iconMaskUrl,
                            transform = UiImage.CoilImage.transformOf(
                                placeholder = painterResource(R.drawable.ic_parking)
                            )
                        ),
                        tint = place.iconBackgroundColor?.toInt()?.let { Color(it) }
                            ?: LocalContentColor.current,
                        iconSize = IconSize.Large
                    )
                }

            }
        }
        RadioButtonRow(listOf(ButtonFilter.FreeParking(), ButtonFilter.AllParking())) {
            searchQuery = it.query
        }
        if (isLoading) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )
        }

    }
}

@Composable
fun RadioButtonRow(
    buttonLabels: List<ButtonFilter>,
    onSelectionChanged: (ButtonFilter) -> Unit
) {
    var selectedLabel by remember { mutableStateOf(buttonLabels.first()) }
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .statusBarsPadding(),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(horizontal = 16.dp)
    ) {
        items(buttonLabels) { label ->
            RadioButtonOption(
                label = label,
                isSelected = selectedLabel == label,
                onSelectionChange = {
                    selectedLabel = it
                    onSelectionChanged(it)
                }
            )
        }
    }
}

@Composable
fun RadioButtonOption(
    label: ButtonFilter,
    isSelected: Boolean,
    onSelectionChange: (ButtonFilter) -> Unit
) {
    Row(
        modifier = Modifier
            .selectable(
                selected = isSelected,
                onClick = { onSelectionChange(label) },
                role = Role.Button
            )
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (isSelected) {
            DsPrimaryButton(label = {
                DsText(label.text.uiText())
            }, onClick = {
                onSelectionChange(label)
            })
        } else {
            DsSecondaryButton(label = {
                DsText(text = label.text.uiText())
            }, onClick = {
                onSelectionChange(label)
            })
        }
    }
}

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun RationaleDialog(permissionState: MultiplePermissionsState) {
    AlertDialog(
        onDismissRequest = { },
        title = { Text("Location Permission Required") },
        text = { Text("Location permission is required to show your current location on the map.") },
        confirmButton = {
            Button(onClick = { permissionState.launchMultiplePermissionRequest() }) {
                Text("Grant Permission")
            }
        }
    )
}

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun PermissionRequest(permissionState: MultiplePermissionsState) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Location permission is required")
        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = { permissionState.launchMultiplePermissionRequest() }) {
            Text("Request Permission")
        }
    }
}

private suspend fun getCurrentLocation(
    context: Context,
    defaultLocation: LatLng,
    fusedLocationClient: FusedLocationProviderClient
): LatLng {
    val locationRequest = LocationRequest.Builder(
        Priority.PRIORITY_HIGH_ACCURACY,
        100
    ).build()

    val location = if (ActivityCompat.checkSelfPermission(
            context,
            Manifest.permission.ACCESS_FINE_LOCATION
        ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
            context,
            Manifest.permission.ACCESS_COARSE_LOCATION
        ) != PackageManager.PERMISSION_GRANTED
    ) {
        // TODO: Consider calling
        //    ActivityCompat#requestPermissions
        // here to request the missing permissions, and then overriding
        //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
        //                                          int[] grantResults)
        // to handle the case where the user grants the permission. See the documentation
        // for ActivityCompat#requestPermissions for more details.
        return defaultLocation
    } else {
        fusedLocationClient.getCurrentLocation(
            Priority.PRIORITY_HIGH_ACCURACY,
            null
        ).await() ?: fusedLocationClient.lastLocation.await()
    }

    return LatLng(location.latitude, location.longitude)
}

suspend fun fetchPlaceDetails(
    searchQuery: String,
    currentLocation: LatLng,
    placesClient: PlacesClient,
    onPlaceFetched: (List<Place>) -> Unit
) {
    val distanceInMeters = 2000.0 // 2 km

    val northeast = SphericalUtil.computeOffset(
        currentLocation,
        distanceInMeters,
        45.0
    )
    val southwest = SphericalUtil.computeOffset(
        currentLocation,
        distanceInMeters,
        225.0
    )

    val bounds = RectangularBounds.newInstance(southwest, northeast)

    val placeFields = Place.Field.entries.toTypedArray().toList()

    // Use the builder to create a SearchByTextRequest object.
    val searchByTextRequest = SearchByTextRequest.builder(searchQuery, placeFields)
        .setMaxResultCount(10)
        .setLocationRestriction(bounds).build()

    // Call PlacesClient.searchByText() to perform the search.
    // Define a response handler to process the returned List of Place objects.
    placesClient.searchByText(searchByTextRequest)
        .addOnSuccessListener { response ->
            Log.d("Places: ", response.places.toString())
            onPlaceFetched(response.places)
        }.await()

}