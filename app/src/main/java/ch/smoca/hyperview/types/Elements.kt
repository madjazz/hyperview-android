package ch.smoca.hyperview.types

sealed class HXMLElement {
    data class HvAlert(val attributes: AlertAttributes) : HXMLElement()

    data class HvBehavior(val attributes: BehaviorAttributes) : HXMLElement()

    data class HvBody(val attributes: BodyAttributes, val children: List<HXMLElement>) :
        HXMLElement()

    data class HvDoc(val children: List<HXMLElement>): HXMLElement()

    data class HvHeader(val attributes: HeaderAttributes, val children: List<HXMLElement>) :
        HXMLElement()

    data class HvImage(val attributes: ImageAttributes) : HXMLElement()

    data class HvItem(val attributes: ItemAttributes, val children: List<HXMLElement>) : HXMLElement()

    data class HvItems(val children: List<HXMLElement>) : HXMLElement()

    data class HvList(val attributes: ListAttributes, val children: List<HXMLElement>) :
        HXMLElement()

    data class HvScreen(val attributes: ScreenAttributes, val children: List<HXMLElement>) :
        HXMLElement()

    data class HvSectionList(
        val attributes: SectionListAttributes,
        val children: List<HXMLElement>
    ) :
        HXMLElement()

    data class HvSectionTitle(
        val attributes: SectionTitleAttributes,
        val children: List<HXMLElement>
    ) :
        HXMLElement()

    data class HvSpinner(
        val attributes: SpinnerAttributes
    ) : HXMLElement()

    data class HvText(val attributes: TextAttributes, val children: List<HXMLElement>) :
        HXMLElement()

    data class HvView(val attributes: ViewAttributes, val children: List<HXMLElement>) :
        HXMLElement()

    data class HvWebView(val attributes: WebViewAttributes) : HXMLElement()
}
