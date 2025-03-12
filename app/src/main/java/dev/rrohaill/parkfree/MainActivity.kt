package dev.rrohaill.parkfree

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.model.LatLng
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.net.PlacesClient
import dev.rrohaill.designsystem.ui.theme.DesignSystemTheme
import dev.rrohaill.designsystem.ui.theme.DsScaffold
import dev.rrohaill.designsystem.ui.theme.fullScreenWindowInset

class MainActivity : ComponentActivity() {
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var placesClient: PlacesClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        Places.initialize(applicationContext, BuildConfig.PLACES_API_KEY)
        placesClient = Places.createClient(this)

        setContent {
            DesignSystemTheme {
                DsScaffold(contentWindowInsets = fullScreenWindowInset()) {
                    MapScreenWithPermissions(fusedLocationClient,placesClient)
                }
            }
        }
    }
}