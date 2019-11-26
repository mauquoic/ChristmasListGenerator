package com.yak.christmas.ui

import com.vaadin.flow.component.Component
import com.vaadin.flow.component.Key.ENTER
import com.vaadin.flow.component.UI
import com.vaadin.flow.component.button.Button
import com.vaadin.flow.component.button.ButtonVariant
import com.vaadin.flow.component.html.H1
import com.vaadin.flow.component.html.H2
import com.vaadin.flow.component.orderedlayout.VerticalLayout
import com.vaadin.flow.component.textfield.TextField
import com.vaadin.flow.router.Route
import com.yak.christmas.business.ChristmasListBC
import javax.inject.Inject


@Route("welcome")
class MainView @Inject constructor(private val christmasListBC: ChristmasListBC,
                                   vararg children: Component?) : VerticalLayout(*children) {

    init {
        add(
                H1("Welcome to the fantastic Generator by Yakman!")
        )
        christmasListBC.families[0].members[0].giftPersonName?.let { welcomePersonScreen() } ?: run { listScreen() }
    }

    private fun welcomePersonScreen() {
        val idField = TextField()
        val enterButton = Button("Enter")
        enterButton.addClickShortcut(ENTER)
        enterButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY)
        enterButton.addClickListener {
            UI.getCurrent().page.setLocation("persons/${idField.value}/presents")
        }
        add(
                H2("Get to your own page"),
                idField,
                enterButton
        )
    }

    private fun listScreen() {
        val drawListButton = Button("Draw Lists")
        drawListButton.addClickShortcut(ENTER)
        drawListButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY)
        drawListButton.addClickListener {
            christmasListBC.determineLists()
            UI.getCurrent().page.reload()
        }
        add(
                drawListButton
        )
    }
}