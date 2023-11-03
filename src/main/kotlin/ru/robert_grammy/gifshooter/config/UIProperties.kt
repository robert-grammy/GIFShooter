package ru.robert_grammy.gifshooter.config

import javax.swing.UIManager
import javax.swing.plaf.UIResource

object UIProperties {

    fun loadProperties() {
        set(ComponentKey.PANEL, PropertyKey.BACKGROUND, Theme.SECONDARY_COLOR.resource())
        set(ComponentKey.BUTTON, PropertyKey.BACKGROUND, Theme.PRIMARY_COLOR.resource())
        set(ComponentKey.COMBO_BOX, PropertyKey.BACKGROUND, Theme.PRIMARY_COLOR.resource())
        set(ComponentKey.FORMATTED_TEXT_FIELD, PropertyKey.BACKGROUND, Theme.PRIMARY_COLOR.resource())
        set(ComponentKey.TEXT_FIELD, PropertyKey.BACKGROUND, Theme.PRIMARY_COLOR.resource())
        set(ComponentKey.LIST, PropertyKey.BACKGROUND, Theme.PRIMARY_COLOR.resource())
        set(ComponentKey.SCROLL_PANE, PropertyKey.BACKGROUND, Theme.PRIMARY_COLOR.resource())
        set(ComponentKey.PROGRESS_BAR, PropertyKey.BACKGROUND, Theme.SECONDARY_COLOR.resource())

        set(ComponentKey.LABEL, PropertyKey.FOREGROUND, Theme.TEXT_COLOR.resource())
        set(ComponentKey.BUTTON, PropertyKey.FOREGROUND, Theme.TEXT_COLOR.resource())
        set(ComponentKey.COMBO_BOX, PropertyKey.FOREGROUND, Theme.TEXT_COLOR.resource())
        set(ComponentKey.FORMATTED_TEXT_FIELD, PropertyKey.FOREGROUND, Theme.TEXT_COLOR.resource())
        set(ComponentKey.TEXT_FIELD, PropertyKey.FOREGROUND, Theme.TEXT_COLOR.resource())
        set(ComponentKey.LIST, PropertyKey.FOREGROUND, Theme.TEXT_COLOR.resource())
        set(ComponentKey.SCROLL_PANE, PropertyKey.FOREGROUND, Theme.TEXT_COLOR.resource())
        set(ComponentKey.PROGRESS_BAR, PropertyKey.FOREGROUND, Theme.HOVER_COLOR.resource())

        set(ComponentKey.COMBO_BOX, PropertyKey.SELECTION_BACKGROUND, Theme.PRIMARY_COLOR.resource())
        set(ComponentKey.COMBO_BOX, PropertyKey.SELECTION_FOREGROUND, Theme.TEXT_COLOR.resource())
        set(ComponentKey.PROGRESS_BAR, PropertyKey.SELECTION_BACKGROUND, Theme.TEXT_COLOR.resource())
        set(ComponentKey.PROGRESS_BAR, PropertyKey.SELECTION_FOREGROUND, Theme.TEXT_COLOR.resource())
    }

    fun loadFont() {
        set(ComponentKey.LABEL, PropertyKey.FONT, ProgramFont.SIZE_16.resource())
        set(ComponentKey.BUTTON, PropertyKey.FONT, ProgramFont.SIZE_16.resource())
        set(ComponentKey.COMBO_BOX, PropertyKey.FONT, ProgramFont.SIZE_16.resource())
        set(ComponentKey.SPINNER, PropertyKey.FONT, ProgramFont.SIZE_16.resource())
        set(ComponentKey.TEXT_FIELD, PropertyKey.FONT, ProgramFont.SIZE_16.resource())
        set(ComponentKey.LIST, PropertyKey.FONT, ProgramFont.SIZE_16.resource())
        set(ComponentKey.PROGRESS_BAR, PropertyKey.FONT, ProgramFont.SIZE_12.resource())
    }

    fun set(component: ComponentKey, property: PropertyKey, resource: UIResource) {
        UIManager.put(component.and(property), resource)
    }

    enum class ComponentKey(private val componentName: String) {
        PANEL("Panel"),
        BUTTON("Button"),
        COMBO_BOX("ComboBox"),
        FORMATTED_TEXT_FIELD("FormattedTextField"),
        TEXT_FIELD("TextField"),
        LABEL("Label"),
        SPINNER("Spinner"),
        LIST("List"),
        SCROLL_PANE("ScrollPane"),
        PROGRESS_BAR("ProgressBar");

        fun and(property: PropertyKey) = "${componentName}.${property.get()}"
    }

    enum class PropertyKey(private val propertyName: String) {
        BACKGROUND("background"),
        FOREGROUND("foreground"),
        FONT("font"),
        SELECTION_BACKGROUND("selectionBackground"),
        SELECTION_FOREGROUND("selectionForeground");

        fun get() = propertyName
    }

}