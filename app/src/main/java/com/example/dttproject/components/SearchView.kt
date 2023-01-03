package com.example.dttproject.components

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dttproject.R

@Composable
fun SearchView(state: MutableState<String>) {
    TextField(
        value = state.value,
        onValueChange = { value ->
            state.value = value

        },
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .padding(start = 20.dp, end = 20.dp),

        placeholder = {

                Text(
                    stringResource(id = R.string.search),
                    color = MaterialTheme.colors.error,
                    modifier = Modifier.padding(start = 1.dp, top = 0.dp, bottom = 0.dp),
                    style = MaterialTheme.typography.caption

                )

                },

        textStyle = TextStyle(
            color = MaterialTheme.colors.primaryVariant, fontSize = 16.sp),

        trailingIcon = {
            if (state.value == ""
            ) {

                    Icon(
                        Icons.Default.Search,
                        contentDescription = "",
                        modifier = Modifier
                            .size(24.dp),
                        tint = MaterialTheme.colors.error
                    )

            } else{
                // Remove text from TextField when you press the 'X' icon
                IconButton(
                    onClick = {
                        state.value = ""
                    }
                ) {
                    Icon(
                        Icons.Default.Close,
                        contentDescription = "",
                        modifier = Modifier
                            .size(24.dp),
                        tint = MaterialTheme.colors.primaryVariant
                    )
                }


            }
        },
        singleLine = true,
        shape = RoundedCornerShape(10.dp),
        colors = TextFieldDefaults.textFieldColors(
            textColor = MaterialTheme.colors.error,
            cursorColor = MaterialTheme.colors.error,
            trailingIconColor = MaterialTheme.colors.error,
            backgroundColor = MaterialTheme.colors.surface,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        )
    )
}

