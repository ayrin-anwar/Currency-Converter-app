package com.example.currencyconverterapp

import android.os.Bundle

import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember

import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.currencyconverterapp.ui.theme.CurrencyConverterAppTheme
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CurrencyConverterAppTheme {
               unitConverter()
            }
        }
    }
}


@Composable
fun unitConverter() {
    var inputValue by remember { mutableStateOf("") }
    var outputValue by remember { mutableStateOf("") }
    var inputUnit by remember { mutableStateOf("Meters") }
    var outputUnit by remember { mutableStateOf("Meters") }
    var iExpanded by remember { mutableStateOf(false) }
    var oExpanded by remember { mutableStateOf(false) }
    var conversionFactor = remember { mutableStateOf(0.01) }
    var oConversionFactor = remember { mutableStateOf(1.00) }
    val customTextStyle = TextStyle (
        fontFamily =  FontFamily.Default,
        fontSize = 16.sp,
        color = Color.Red
    )
    fun convertUnits() {

        val inputValueDouble = inputValue.toDoubleOrNull()?:0.0
        val result = (inputValueDouble*conversionFactor.value*100.0/oConversionFactor.value).roundToInt()/100.0
        outputValue = result.toString()
    }
    Column (
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    )
    {

        // here all the ui elements will be stacked below each other
        //val username by rememberSaveable { mutableStateOf(TextFieldValue) }
        Text("Unit Converter", modifier = Modifier.padding(15.dp), style = customTextStyle)
        Spacer(modifier = Modifier.height(16.dp))
//        var text by rememberSaveable(stateSaver = TextFieldValue.Saver) {
//            mutableStateOf(TextFieldValue("example",TextRange(0,7)))
//        }


        OutlinedTextField(
            value = inputValue,
            onValueChange = { inputValue = it
                             convertUnits()
                            },
            label = { Text("Enter value") }
        )
        Spacer(modifier = Modifier.height(16.dp))
        Row {
            Box {

                val context = LocalContext.current
                Button(onClick ={iExpanded = true}) {
                    Text("select")
                    Icon(
                        Icons.Default.ArrowDropDown,
                        contentDescription ="Arrow Down")
                }

                DropdownMenu(expanded = iExpanded, onDismissRequest = {iExpanded = false}) {
                    DropdownMenuItem(
                        text = { Text("Centimeters")},
                        onClick = {iExpanded = false
                            inputUnit = "Centimeters"
                            conversionFactor.value = 0.01
                            convertUnits()

                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Meter")},
                        onClick = {iExpanded = false
                            inputUnit = "Meters"
                            conversionFactor.value = 1.0
                            convertUnits()

                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Feet")},
                        onClick = {
                            iExpanded = false
                            inputUnit = "Feet"
                            conversionFactor.value = .3048
                            convertUnits()
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("milimeters")},
                        onClick = {
                            iExpanded = false
                            inputUnit = "milimeters"
                            conversionFactor.value = 0.001
                            convertUnits()
                        }
                    )
                }


            }
            Spacer(modifier = Modifier.width(16.dp))

            Box {

                val context = LocalContext.current
                Button(onClick ={oExpanded = true}) {
                    Text("select")
                    Icon(
                        Icons.Default.ArrowDropDown,
                        contentDescription ="")
                }
                DropdownMenu(expanded = oExpanded, onDismissRequest = {oExpanded = false}) {
                    DropdownMenuItem(
                        text = { Text("Centimeters")},
                        onClick = {oExpanded = false
                            outputUnit = "Centimeters"
                            oConversionFactor.value = 0.01
                            convertUnits()}
                    )
                    DropdownMenuItem(
                        text = { Text("Meters")},
                        onClick = {oExpanded = false
                            outputUnit = "Meters"
                            oConversionFactor.value = 1.00
                            convertUnits()}
                    )
                    DropdownMenuItem(
                        text = { Text("Feet")},
                        onClick = {oExpanded = false
                            outputUnit = "Feet"
                            oConversionFactor.value = .3048
                            convertUnits()}
                    )
                    DropdownMenuItem(
                        text = { Text("milimeters")},
                        onClick = {oExpanded = false
                            outputUnit = "milimeters"
                            oConversionFactor.value = 0.001
                            convertUnits()}
                    )
                }


            }
//            val context = LocalContext.current
//            Button(onClick = {
//               Toast.makeText(context,"You clicked on button",Toast.LENGTH_SHORT).show()
//            }, Modifier.padding(top = 16.dp)) {
//
//                Text(text = "Basic Button")
//            }




       }
        Spacer(modifier = Modifier.height(16.dp))
        Text("Result:$outputValue $outputUnit",
             style = MaterialTheme.typography.headlineSmall


        )
    }
}
@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun UnitConverterPreview() {
    unitConverter()
}