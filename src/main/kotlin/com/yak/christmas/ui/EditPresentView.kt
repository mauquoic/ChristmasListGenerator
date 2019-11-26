package com.yak.christmas.ui

import com.vaadin.flow.component.Component
import com.vaadin.flow.component.UI
import com.vaadin.flow.component.button.Button
import com.vaadin.flow.component.button.ButtonVariant
import com.vaadin.flow.component.formlayout.FormLayout
import com.vaadin.flow.component.formlayout.FormLayout.ResponsiveStep
import com.vaadin.flow.component.html.H1
import com.vaadin.flow.component.orderedlayout.VerticalLayout
import com.vaadin.flow.component.textfield.TextField
import com.vaadin.flow.router.BeforeEvent
import com.vaadin.flow.router.HasUrlParameter
import com.vaadin.flow.router.Route
import com.yak.christmas.business.ChristmasListBC
import com.yak.christmas.model.Present
import javax.inject.Inject

@Route("presents")
open class EditPresentView @Inject constructor(private val christmasListBC: ChristmasListBC,
                                               vararg children: Component?) :
        VerticalLayout(*children),
        HasUrlParameter<String> {

    var present: Present? = null

    init {
        add(
                H1("Presents")
        )
    }

    override fun setParameter(event: BeforeEvent, id: String) {
        present = christmasListBC.findPresentById(id.toInt())
        present?.presentName?.let { } ?: run { event.rerouteTo("welcome") }

        val presentLayout = FormLayout()

        val titleField = TextField()
        titleField.label = "Title"
        titleField.minLength = 1
        titleField.value = present!!.presentName
        titleField.isRequired = true
        val linkField = TextField()
        linkField.label = "Link"
        linkField.value = present!!.link
        val descriptionField = TextField()
        descriptionField.label = "Description"
        descriptionField.value = present!!.description

        presentLayout.setResponsiveSteps(
                ResponsiveStep("25em", 1),
                ResponsiveStep("32em", 2))

        val editPresentButton = Button("Save")
        editPresentButton.addThemeVariants(ButtonVariant.LUMO_SMALL)
        editPresentButton.addClickListener {
            christmasListBC.editPresent(Present(id.toInt(), titleField.value, linkField.value, descriptionField.value))
            UI.getCurrent().page.setLocation("persons/${christmasListBC.findPersonWhoWantsPresentWithId(id.toInt()).id}/presents")
        }

        val deletePresentButton = Button("Delete")
        deletePresentButton.addThemeVariants(ButtonVariant.LUMO_CONTRAST)
        deletePresentButton.addClickListener {
            val personId = christmasListBC.findPersonWhoWantsPresentWithId(id.toInt()).id
            christmasListBC.deletePresentById(id.toInt())
            UI.getCurrent().page.setLocation("persons/${personId}/presents")
        }
        presentLayout.add(titleField, linkField, descriptionField)
        presentLayout.add(editPresentButton, 2)
        presentLayout.add(deletePresentButton, 2)
        add(H1("Edit a present"),
                presentLayout,
                ViewUtil.logoutButton()
        )
    }

}


