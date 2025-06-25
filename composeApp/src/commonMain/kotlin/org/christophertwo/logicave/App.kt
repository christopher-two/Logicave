package org.christophertwo.logicave

import androidx.compose.runtime.Composable
import logicave.composeapp.generated.resources.Res
import logicave.composeapp.generated.resources.page_1
import logicave.composeapp.generated.resources.page_2
import logicave.composeapp.generated.resources.page_3
import logicave.composeapp.generated.resources.page_4
import logicave.composeapp.generated.resources.page_5
import logicave.composeapp.generated.resources.page_6
import logicave.composeapp.generated.resources.page_7
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.override.book.logicave.LogicaveRoot

@Composable
@Preview
fun App() {
    LogicaveRoot(
        listOf(
            Res.drawable.page_1,
            Res.drawable.page_2,
            Res.drawable.page_3,
            Res.drawable.page_4,
            Res.drawable.page_5,
            Res.drawable.page_6,
            Res.drawable.page_7
        )
    )
}