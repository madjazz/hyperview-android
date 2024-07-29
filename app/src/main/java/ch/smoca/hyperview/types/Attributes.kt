package ch.smoca.hyperview.types

import arrow.core.Option

data class AlertAttributes(
    val title: String,
    val message: Option<String>,
    val label: String,
    val style: Option<String>,
    val trigger: Option<String>,
    val href: Option<String>,
    val verb: Option<String>,
    val action: Option<String>,
    val target: Option<String>,
    val showDuringLoad: Option<String>,
    val hideDuringLoad: Option<String>,
    val delay: Option<Int>,
    val once: Boolean = false,
    val newValue: Option<String>,
)

data class BehaviorAttributes(
    val trigger: Option<String>,
    val href: Option<String>,
    val verb: Option<String>,
    val action: Option<String>,
    val target: Option<String>,
    val showDuringLoad: Option<String>,
    val hideDuringLoad: Option<String>,
    val delay: Option<Int>,
    val once: Boolean = false,
    val newValue: Option<String>,
    val xmlnsAlert: Option<String>,
    val alertTitle: Option<String>,
    val alertMessage: Option<String>,
    val xmlnsShare: Option<String>,
    val shareDialogTitle: Option<String>,
    val shareTitle: Option<String>,
    val shareUrl: Option<String>,
    val shareMessage: Option<String>
)

data class BodyAttributes(
    val trigger: Option<String>,
    val href: Option<String>,
    val verb: Option<String>,
    val action: Option<String>,
    val target: Option<String>,
    val showDuringLoad: Option<String>,
    val hideDuringLoad: Option<String>,
    val delay: Option<Int>,
    val once: Boolean = false,
    val newValue: Option<String>,
    val safeArea: Boolean = false,
    val style: Option<String>,
    val scroll: Boolean = false,
    val scrollOrientation: Boolean = false,
    val scrollToInputOffset: Int = 120,
    val showsScrollIndicator: Boolean = true,
    val id: Option<String>
)

data class HeaderAttributes(
    val trigger: Option<String>,
    val href: Option<String>,
    val verb: Option<String>,
    val action: Option<String>,
    val target: Option<String>,
    val showDuringLoad: Option<String>,
    val hideDuringLoad: Option<String>,
    val delay: Option<Int>,
    val once: Boolean = false,
    val newValue: Option<String>,
    val safeArea: Boolean = false,
    val style: Option<String>,
    val id: Option<String>,
    val hide: Boolean = false
)

data class ListAttributes(
    val trigger: Option<String>,
    val href: Option<String>,
    val verb: Option<String>,
    val action: Option<String>,
    val target: Option<String>,
    val showDuringLoad: Option<String>,
    val hideDuringLoad: Option<String>,
    val delay: Option<Int>,
    val once: Boolean = false,
    val newValue: Option<String>,
    val style: Option<String>,
    val itemHeight: Option<Int>,
    val id: Option<String>,
    val hide: Boolean = false,
    val scrollOrientation: Boolean = false,
    val showsScrollIndicator: Boolean = true,
    val keyboardDismissMode: Option<String>
)

data class ImageAttributes(val id: Option<String>)

data class ItemAttributes(
    val trigger: Option<String>,
    val href: Option<String>,
    val verb: Option<String>,
    val action: Option<String>,
    val target: Option<String>,
    val showDuringLoad: Option<String>,
    val hideDuringLoad: Option<String>,
    val delay: Option<Int>,
    val once: Boolean = false,
    val newValue: Option<String>,
    val key: String,
    val style: Option<String>,
    val id: Option<String>,
    val hide: Boolean = false,
    val sticky: Boolean  = false,
)

data class ScreenAttributes(val id: Option<String>)

data class SectionListAttributes(
    val trigger: Option<String>,
    val href: Option<String>,
    val verb: Option<String>,
    val action: Option<String>,
    val target: Option<String>,
    val showDuringLoad: Option<String>,
    val hideDuringLoad: Option<String>,
    val delay: Option<Int>,
    val once: Boolean = false,
    val newValue: Option<String>,
    val style: Option<String>,
    val id: Option<String>,
    val hide: Boolean,
    val stickySectionTitles: Option<Boolean>,
    val keyboardDismissMode: Option<String>
)

data class SectionTitleAttributes(
    val trigger: Option<String>,
    val href: Option<String>,
    val verb: Option<String>,
    val action: Option<String>,
    val target: Option<String>,
    val showDuringLoad: Option<String>,
    val hideDuringLoad: Option<String>,
    val delay: Option<Int>,
    val once: Boolean = false,
    val newValue: Option<String>,
    val style: Option<String>,
    val id: Option<String>,
    val hide: Boolean = false
)

data class SpinnerAttributes(
    val color: Option<String>,
    val id: Option<String>,
    val hide: Boolean = false
)

data class TextAttributes(
    val trigger: Option<String>,
    val href: Option<String>,
    val verb: Option<String>,
    val action: Option<String>,
    val target: Option<String>,
    val showDuringLoad: Option<String>,
    val hideDuringLoad: Option<String>,
    val delay: Option<Int>,
    val once: Boolean = false,
    val newValue: Option<String>,
    val style: Option<String>,
    val numberOfLines: Option<Int>,
    val id: Option<String>,
    val hide: Boolean = false,
    val selectable: Option<Boolean>,
    val adjustFontSize: Option<Boolean>,
    val preformatted: Option<Boolean>
)

data class ViewAttributes(
    val trigger: Option<String>,
    val href: Option<String>,
    val verb: Option<String>,
    val action: Option<String>,
    val target: Option<String>,
    val showDuringLoad: Option<String>,
    val hideDuringLoad: Option<String>,
    val delay: Option<Int>,
    val once: Boolean = false,
    val newValue: Option<String>,
    val safeArea: Boolean = false,
    val style: Option<String>,
    val scroll: Boolean = false,
    val scrollOrientation: Boolean = false,
    val scrollToInputOffset: Int = 120,
    val showsScrollIndicator: Boolean = true,
    val id: Option<String>,
    val hide: Boolean = false,
    val avoidKeyboard: Boolean = false,
    val sticky: Boolean = false,
    val keyboardDismissMode: Option<String>
)

data class WebViewAttributes(
    val url: Option<String>,
    val html: Option<String>,
    val activityIndicatorColor: Option<String>,
    val injectedJavaScript: Option<String>,
    val showLoadingIndicator: Option<String>,
    val id: Option<String>
)
