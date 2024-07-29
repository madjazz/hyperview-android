package ch.smoca.hyperview

import arrow.core.Either
import ch.smoca.hyperview.types.BodyAttributes
import ch.smoca.hyperview.types.HeaderAttributes
import ch.smoca.hyperview.types.HXMLError
import ch.smoca.hyperview.types.HXMLElement
import ch.smoca.hyperview.types.HXMLTag
import ch.smoca.hyperview.types.ImageAttributes
import ch.smoca.hyperview.types.ListAttributes
import ch.smoca.hyperview.types.ScreenAttributes
import ch.smoca.hyperview.types.SectionListAttributes
import ch.smoca.hyperview.types.SpinnerAttributes
import ch.smoca.hyperview.types.TextAttributes
import ch.smoca.hyperview.types.ViewAttributes
import ch.smoca.hyperview.types.WebViewAttributes
import ch.smoca.hyperview.types.safeValueOf
import ch.smoca.hyperview.types.tagError
import com.ximpleware.AutoPilot
import com.ximpleware.VTDGen
import com.ximpleware.VTDNav

inline fun <reified T : Any> parseAttributes(ap: AutoPilot, vn: VTDNav): T {
    val attributeClass = T::class
    val properties = getAllProperties(attributeClass)
    val propertiesMap = mutableMapOf<String, Any?>()
    ap.selectAttr("*")
    var i = ap.iterateAttr()
    while (i != -1) {
        val attrName = vn.toString(i)
        val attrValue = vn.toString(i + 1)
        if (properties.contains(attrName)) {
            propertiesMap[attrName] = attrValue
        }
        i = ap.iterateAttr()
    }
    return createInstance(attributeClass, propertiesMap)
}

fun parse(xml: String): Either<HXMLError, HXMLElement> {
    try {
        val vg = VTDGen()
        vg.setDoc(xml.toByteArray())
        vg.parse(true)
        val vn: VTDNav = vg.nav
        val ap = AutoPilot(vn)

        ap.selectXPath("//*")
        while (ap.evalXPath() != -1) {
            val elementName = vn.toString(vn.getCurrentIndex())
            return when (safeValueOf(elementName)) {
                HXMLTag.DOC -> parseDoc(vn)

                HXMLTag.VIEW -> {
                    val viewAttributes = parseAttributes<ViewAttributes>(ap, vn)
                    Either.Right(
                        HXMLElement.HvView(
                            attributes = viewAttributes,
                            children = mutableListOf()
                        )
                    )
                }

                HXMLTag.TEXT -> {
                    val textAttributes = parseAttributes<TextAttributes>(ap, vn)
                    Either.Right(
                        HXMLElement.HvText(
                            attributes = textAttributes,
                            children = mutableListOf()
                        )
                    )
                }

                HXMLTag.IMAGE -> {
                    val imageAttributes = parseAttributes<ImageAttributes>(ap, vn)
                    Either.Right(HXMLElement.HvImage(attributes = imageAttributes))
                }

                HXMLTag.LIST -> {
                    val listAttributes = parseAttributes<ListAttributes>(ap, vn)
                    Either.Right(
                        HXMLElement.HvList(
                            attributes = listAttributes,
                            children = mutableListOf()
                        )
                    )
                }

                HXMLTag.SECTION_LIST -> {
                    val sectionListAttributes = parseAttributes<SectionListAttributes>(ap, vn)
                    Either.Right(
                        HXMLElement.HvSectionList(
                            attributes = sectionListAttributes,
                            children = mutableListOf()
                        )
                    )
                }

                else -> Either.Left(HXMLError("<$elementName> is not a valid root element"))
            }
        }
        return Either.Left(HXMLError("HvView does not contain valid Hyperview XML elements"))
    } catch (_: IllegalArgumentException) {
        return Either.Left(HXMLError("HvView '${xml}' is not valid Hyperview XML"))
    }
}

fun parseDoc(vn: VTDNav): Either<HXMLError, HXMLElement.HvDoc> {
    return when (val result = parseDocChildren(vn)) {
        is Either.Right -> Either.Right(HXMLElement.HvDoc(children = result.value))
        is Either.Left -> return result
    }
}

fun parseDocChildren(vn: VTDNav): Either<HXMLError, List<HXMLElement>> {
    val ap = AutoPilot(vn)
    ap.selectXPath("*")
    val children = mutableListOf<HXMLElement>()
    while (ap.evalXPath() != -1) {
        val elementName = vn.toString(vn.getCurrentIndex())
        when (safeValueOf(elementName)) {
            HXMLTag.SCREEN -> {
                val screenAttributes = parseAttributes<ScreenAttributes>(ap, vn)
                when (val result = parseScreenChildren(vn)) {
                    is Either.Right -> {
                        children.add(
                            HXMLElement.HvScreen(
                                children = result.value,
                                attributes = screenAttributes
                            )
                        )
                    }

                    is Either.Left -> return result
                }

            }

            else -> {
                return Either.Left(tagError(HXMLTag.DOC, elementName))
            }
        }
    }
    return Either.Right(children)
}

fun parseScreenChildren(vn: VTDNav): Either<HXMLError, List<HXMLElement>> {
    val parent = HXMLTag.SCREEN
    val ap = AutoPilot(vn)
    val children = mutableListOf<HXMLElement>()
    ap.selectXPath("*")

    while (ap.evalXPath() != -1) {
        val elementName = vn.toString(vn.getCurrentIndex())
        when (safeValueOf(elementName)) {
            HXMLTag.BODY -> {
                val bodyAttributes = parseAttributes<BodyAttributes>(ap, vn)
                children.add(
                    HXMLElement.HvBody(
                        attributes = bodyAttributes,
                        children = mutableListOf()
                    )
                )
            }

            HXMLTag.HEADER -> {
                val headerAttributes = parseAttributes<HeaderAttributes>(ap, vn)
                children.add(
                    HXMLElement.HvHeader(
                        attributes = headerAttributes,
                        children = mutableListOf()
                    )
                )
            }

            HXMLTag.IMAGE -> {
                val imageAttributes = parseAttributes<ImageAttributes>(ap, vn)
                children.add(
                    HXMLElement.HvImage(
                        attributes = imageAttributes
                    )
                )
            }

            HXMLTag.ITEMS -> {
                children.add(
                    HXMLElement.HvItems(
                        children = mutableListOf()
                    )
                )
            }

            HXMLTag.LIST -> {
                val listAttributes = parseAttributes<ListAttributes>(ap, vn)
                children.add(
                    HXMLElement.HvList(
                        attributes = listAttributes,
                        children = mutableListOf()
                    )
                )
            }

            HXMLTag.SECTION_LIST -> {
                val sectionListAttributes = parseAttributes<SectionListAttributes>(ap, vn)
                children.add(
                    HXMLElement.HvSectionList(
                        attributes = sectionListAttributes,
                        children = mutableListOf()
                    )
                )
            }

            HXMLTag.SPINNER -> {
                val spinnerAttributes = parseAttributes<SpinnerAttributes>(ap, vn)
                children.add(
                    HXMLElement.HvSpinner(
                        attributes = spinnerAttributes
                    )
                )
            }

            HXMLTag.TEXT -> {
                val textAttributes = parseAttributes<TextAttributes>(ap, vn)
                children.add(
                    HXMLElement.HvText(
                        attributes = textAttributes,
                        children = mutableListOf()
                    )
                )
            }

            HXMLTag.VIEW -> {
                val viewAttributes = parseAttributes<ViewAttributes>(ap, vn)
                children.add(
                    HXMLElement.HvView(
                        attributes = viewAttributes,
                        children = mutableListOf()
                    )
                )
            }

            HXMLTag.WEB_VIEW -> {
                val webViewAttributes = parseAttributes<WebViewAttributes>(ap, vn)
                children.add(
                    HXMLElement.HvWebView(
                        attributes = webViewAttributes
                    )
                )
            }

            else -> {
                return Either.Left(tagError(parent, elementName))
            }
        }
    }
    return Either.Right(children)
}
