package ch.smoca.hyperview

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import arrow.core.Some
import arrow.core.None

@Composable
fun Hyperview(xml: String) {
//    when(val parsed = parse(xml)) {
//        is Some -> Doc(parsed.value)
//        is None -> HvText(text = "Cannot Parse XML")
//    }
}

//@Composable
//fun Doc(document: HXMLRoot.Doc) {
//    when(val screen = document.child) {
//        is Some -> HvScreen(screen = screen.value, styles = document.styles)
//        is None -> {}
//    }
//}
//
//fun String.toColor() = Color(android.graphics.Color.parseColor(this))
//
//@Composable
//fun HvScreen(screen: HXMLRoot.HvScreen, styles: Styles) {
//    val c = styles["mainText"]?.get("backgroundColor")?.toColor() ?: Color.Red
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .background(color = c)
//    ) {
//        val children = screen.children
//        children.forEach { child ->
//            when(child) {
//                is ScreenChildElement.HvText -> HvText(text = child.text)
//                else -> {}
//            }
//        }
//    }
//}
