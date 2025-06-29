package com.prathameshkumbhar.iweather.android.ui

import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.graphics.Paint
import android.util.Log
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.Air
import androidx.compose.material.icons.filled.Explore
import androidx.compose.material.icons.filled.NightsStay
import androidx.compose.material.icons.filled.Thermostat
import androidx.compose.material.icons.filled.WbSunny
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.prathameshkumbhar.iweather.android.utils.formatWeatherTime
import com.prathameshkumbhar.iweather.android.utils.getLastKnownLocation
import com.prathameshkumbhar.iweather.android.viewmodel.AndroidWeatherForecastViewModel
import com.prathameshkumbhar.iweather.connection.models.response.GetWeatherForecastResponse
import com.prathameshkumbhar.iweather.utils.NetworkResult
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun WeatherForecastScreen(
    viewModel: AndroidWeatherForecastViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsState()
    val context = LocalContext.current
    val locationPermission = rememberPermissionState(permission = ACCESS_FINE_LOCATION)


    LaunchedEffect(Unit) {
        if (!locationPermission.status.isGranted) {
            locationPermission.launchPermissionRequest()
        } else {
            context.getLastKnownLocation(
                onSuccess = { location ->
                    viewModel.loadWeather(
                        location.latitude,
                        location.longitude,
                        currentWeather = true
                    )
                },
                onFailure = {

                }
            )
        }
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Log.e("WEATHER", "WeatherForecastScreen: $state")
        when (val result = state) {
            is NetworkResult.Loading -> {
                Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            }

            is NetworkResult.Success -> {
                result.data?.currentWeather?.let { weather ->
                    Log.e("WEATHER", "WeatherForecastScreen: ${weather.toString()}")
                    WeatherContent(
                        weather = weather,
                        isDay = weather.isDay == 1,
                        result.data?.timezone.toString()
                    )
                }
            }

            is NetworkResult.Error -> {
                Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text("Error: ${result.message}")
                    Log.e("WEATHER", "WeatherForecastScreen: ${result.message} ")
                }
            }
        }
    }
}

@Composable
fun WeatherContent(
    weather: GetWeatherForecastResponse.CurrentWeather,
    isDay: Boolean,
    timeZone: String
) {
    val backgroundColor = if (isDay) Color(0xFFBBDEFB) else Color(0xFF121212)
    val textColor = if (isDay) Color.Black else Color.White
    val cardColor = if (isDay) Color.White else Color(0xFF1E1E1E)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            imageVector = if (isDay) Icons.Filled.WbSunny else Icons.Filled.NightsStay,
            contentDescription = "Weather Icon",
            tint = textColor,
            modifier = Modifier.size(64.dp)
        )

        Spacer(Modifier.height(16.dp))

        Text(
            text = "Current Weather",
            style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold),
            color = textColor
        )

        Spacer(Modifier.height(24.dp))

        WeatherInfoCard(
            label = "Temperature",
            value = "${weather.temperature}°C",
            icon = Icons.Filled.Thermostat,
            cardColor = cardColor,
            textColor = textColor
        )

        WeatherInfoCard(
            label = "Wind Speed",
            value = "${weather.windspeed} km/h",
            icon = Icons.Filled.Air,
            cardColor = cardColor,
            textColor = textColor
        )

        WeatherInfoCard(
            label = "Wind Direction",
            value = "${weather.winddirection}°",
            icon = Icons.Filled.Explore,
            cardColor = cardColor,
            textColor = textColor
        )

        WeatherInfoCard(
            label = "Time",
            value = formatWeatherTime(weather.time ?: "", timeZone),
            icon = Icons.Filled.AccessTime,
            cardColor = cardColor,
            textColor = textColor
        )

        Spacer(Modifier.height(24.dp))

        val hourlyTemps = listOf(
            "14:00" to 31,
            "15:00" to 32,
            "16:00" to 33,
            "17:00" to 34,
            "18:00" to 33,
            "19:00" to 31
        )

        TemperatureChart(
            hourlyData = hourlyTemps,
            textColor = Color.White
        )
    }
}


@Composable
fun WeatherInfoCard(
    label: String,
    value: String,
    icon: ImageVector,
    cardColor: Color,
    textColor: Color
) {
    Card(
        colors = CardDefaults.cardColors(containerColor = cardColor),
        elevation = CardDefaults.cardElevation(8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(icon, contentDescription = null, tint = textColor, modifier = Modifier.size(28.dp))
            Spacer(Modifier.width(12.dp))
            Column {
                Text(label, color = textColor.copy(alpha = 0.7f))
                Text(value, color = textColor, style = MaterialTheme.typography.bodyLarge)
            }
        }
    }
}

@Composable
fun TemperatureChart(
    hourlyData: List<Pair<String, Int>>, // Pair<"18:00", 28>
    textColor: Color
) {
    val temperatures = hourlyData.map { it.second }
    val times = hourlyData.map { it.first }

    val maxTemp = (temperatures.maxOrNull() ?: 0) + 2
    val minTemp = (temperatures.minOrNull() ?: 0) - 2

    val yAxisSteps = 4
    val yStepValue = (maxTemp - minTemp) / yAxisSteps.toFloat()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp)
    ) {
        Text(
            text = "Hourly Forecast",
            style = MaterialTheme.typography.titleMedium,
            color = textColor,
            modifier = Modifier.padding(horizontal = 16.dp)
        )

        Canvas(
            modifier = Modifier
                .fillMaxWidth()
                .height(240.dp)
                .padding(horizontal = 32.dp, vertical = 16.dp)
        ) {
            val chartHeight = size.height
            val chartWidth = size.width

            val spacingX = chartWidth / (temperatures.size - 1)
            val spacingY = chartHeight / yAxisSteps


            for (i in 0..yAxisSteps) {
                val y = chartHeight - i * spacingY
                val tempLabel = (minTemp + i * yStepValue).toInt()


                drawLine(
                    color = Color.LightGray,
                    start = Offset(0f, y),
                    end = Offset(chartWidth, y),
                    strokeWidth = 1.dp.toPx()
                )

                drawContext.canvas.nativeCanvas.drawText(
                    "$tempLabel°",
                    -24.dp.toPx(),
                    y + 8f,
                    Paint().apply {
                        color = textColor.toArgb()
                        textAlign = Paint.Align.RIGHT
                        textSize = 26f
                    }
                )
            }


            val points = temperatures.mapIndexed { index, temp ->
                val x = index * spacingX
                val yRatio = (temp - minTemp) / (maxTemp - minTemp).toFloat()
                val y = chartHeight - (yRatio * chartHeight)
                Offset(x, y)
            }


            val path = Path().apply {
                moveTo(points.first().x, points.first().y)
                for (i in 1 until points.size) {
                    lineTo(points[i].x, points[i].y)
                }
            }

            drawPath(
                path = path,
                color = Color.Red,
                style = Stroke(width = 3.dp.toPx(), cap = StrokeCap.Round)
            )


            points.forEachIndexed { index, point ->
                drawCircle(
                    color = Color.White,
                    radius = 8f,
                    center = point
                )
                drawCircle(
                    color = Color.Red,
                    radius = 8f,
                    center = point,
                    style = Stroke(width = 2.dp.toPx())
                )

                drawContext.canvas.nativeCanvas.drawText(
                    "${temperatures[index]}°",
                    point.x,
                    point.y - 14.dp.toPx(),
                    Paint().apply {
                        color = textColor.toArgb()
                        textAlign = Paint.Align.CENTER
                        textSize = 26f
                    }
                )
            }


            points.forEachIndexed { index, point ->
                drawContext.canvas.nativeCanvas.drawText(
                    times[index],
                    point.x,
                    chartHeight + 20.dp.toPx(),
                    Paint().apply {
                        color = textColor.toArgb()
                        textAlign = Paint.Align.CENTER
                        textSize = 22f
                    }
                )
            }
        }
    }
}



