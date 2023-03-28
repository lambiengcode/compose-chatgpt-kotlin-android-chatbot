package com.chatgptlite.wanted.ui.conversations.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.chatgptlite.wanted.ui.theme.PrimaryColor

@Composable
fun TextInput() {
    Column {
        Divider(Modifier.height(0.2.dp))
        Box(
            Modifier.padding(horizontal = 4.dp).padding(top = 6.dp, bottom = 10.dp)
        ) {
            Row {
                TextField(
                    value = "",
                    onValueChange = {

                    },
                    label = null,
                    placeholder = { Text("Ask me anything", fontSize = 12.sp) },
                    shape = RoundedCornerShape(25.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 18.dp).weight(1f),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = Color.Transparent,
                        unfocusedBorderColor = Color.Transparent,
                        textColor = PrimaryColor,
                    ),
                )
                IconButton(onClick = {

                }) {
                    Icon(Icons.Filled.Send, "sendMessage", modifier = Modifier.size(26.dp))
                }
            }
        }
    }
}