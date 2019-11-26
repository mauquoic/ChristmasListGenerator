package com.yak.christmas.ui

import com.vaadin.flow.component.UI
import com.vaadin.flow.component.button.Button
import com.vaadin.flow.component.button.ButtonVariant

class ViewUtil {

    companion object {
        fun logoutButton(): Button {
            val logoutButton = Button("Logout")
            logoutButton.addThemeVariants(ButtonVariant.LUMO_LARGE)
            logoutButton.addClickListener {
                UI.getCurrent().page.setLocation("welcome")
            }
            return logoutButton
        }
    }
}