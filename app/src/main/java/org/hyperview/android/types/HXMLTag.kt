package org.hyperview.android.types

enum class HXMLTag(val value: String) {
    ALERT("alert:option"),
    BEHAVIOR("behavior"),
    BODY("body"),
    DATA_FIELD("data-field"),
    DOC("doc"),
    FORM("form"),
    HEADER("header"),
    IMAGE("image"),
    ITEM("item"),
    ITEMS("items"),
    LIST("list"),
    MODIFIER("modifier"),
    NAVIGATOR("navigator"),
    NAV_ROUTE("nav-route"),
    OPTION("option"),
    PICKER_FIELD("picker-field"),
    PICKER_ITEM("picker-item"),
    SCREEN("screen"),
    SECTION_LIST("section-list"),
    SECTION_TITLE("section-title"),
    SELECT_MULTIPLE("select-multiple"),
    SELECT_SINGLE("select-single"),
    SPINNER("spinner"),
    STYLE("style"),
    STYLES("styles"),
    TEXT("text"),
    TEXT_AREA("text-area"),
    TEXT_FIELD("text-field"),
    VIEW("view"),
    WEB_VIEW("web-view")
}

fun safeValueOf(value: String): HXMLTag? {
    return enumValues<HXMLTag>().firstOrNull { it.value == value }
}

fun tagError(parent: HXMLTag, tag: String): HXMLError {
    return HXMLError("<${tag}> cannot be a child of <${parent.value}>")
}

fun tagError(parent: HXMLTag, tag: HXMLTag): HXMLError {
    return HXMLError("<${tag.value}> cannot be a child of <${parent.value}>")
}
