package com.example.lection_3

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyScreen()
        }
    }
}

@Composable
private fun MyCard(count: Int, modifier: Modifier = Modifier) {
    val padding = dimensionResource(R.dimen.item_padding)
    val corner = dimensionResource(R.dimen.corner_radius)
    val fontLarge = dimensionResource(R.dimen.font_large).value.sp
    val borderWidth = dimensionResource(R.dimen.border_width)
    val backgroundColor = if (count % 2 == 0) colorResource(R.color.Red)
                          else colorResource(R.color.Blue)

    Box(
        modifier = modifier
            .fillMaxWidth()
            .aspectRatio(1f)
            .padding(padding)
            .background(backgroundColor, RoundedCornerShape(corner))
            .border(
                width = borderWidth,
                color = colorResource(R.color.Black),
                shape = RoundedCornerShape(corner)
            ),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = count.toString(),
            fontSize = fontLarge,
            color = colorResource(R.color.White),
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
private fun MyScreen(modifier: Modifier = Modifier) {
    var numberList by rememberSaveable { mutableStateOf(listOf<Int>()) }

    fun addNumber() {
        numberList += (numberList.size + 1)
    }

    val topSpacingPortrait = dimensionResource(R.dimen.top_spacing_portrait)
    val topSpacingHorizontal = dimensionResource(R.dimen.top_spacing_horizontal)
    val betweenGridAndButton = dimensionResource(R.dimen.between_grid_and_button)
    val buttonHeight = dimensionResource(R.dimen.button_height)
    val buttonCorner = dimensionResource(R.dimen.button_corner)
    val fontLarge = dimensionResource(R.dimen.font_large).value.sp
    val lazyPadding = dimensionResource(R.dimen.lazy_padding)
    val addPadding = dimensionResource(R.dimen.add_padding)

    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        val configuration = LocalConfiguration.current
        val isPortrait = configuration.orientation == Configuration.ORIENTATION_PORTRAIT
        val columns = if (isPortrait) 3 else 4

        Spacer(
            modifier = Modifier.height(
                if (isPortrait) topSpacingPortrait else topSpacingHorizontal
            )
        )

        LazyVerticalGrid(
            modifier = Modifier
                .weight(1f)
                .padding(
                    start = if (isPortrait) 0.dp else lazyPadding
                ),
            columns = GridCells.Fixed(columns),
        ) {
            items(numberList) { item ->
                MyCard(count = item)
            }
        }

        Spacer(modifier = Modifier.height(betweenGridAndButton))

        Button(
            onClick = { addNumber() },
            colors = ButtonDefaults.buttonColors(
                containerColor = colorResource(R.color.Green)
            ),
            modifier = Modifier
                .height(buttonHeight),
            shape = RoundedCornerShape(buttonCorner)
        ) {
            Text(
                text = stringResource(R.string.add),
                fontSize = fontLarge,
                color = colorResource(R.color.White),
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(horizontal = addPadding)
            )
        }

        Spacer(modifier = Modifier.height(betweenGridAndButton))
    }
}

@Preview(showBackground = true)
@Composable
private fun MyScreenPreview() {
    MyScreen()
}