package com.yak.christmas.ui

import com.vaadin.flow.component.Component
import com.vaadin.flow.component.UI
import com.vaadin.flow.component.button.Button
import com.vaadin.flow.component.button.ButtonVariant
import com.vaadin.flow.component.formlayout.FormLayout
import com.vaadin.flow.component.formlayout.FormLayout.ResponsiveStep
import com.vaadin.flow.component.grid.Grid
import com.vaadin.flow.component.html.H1
import com.vaadin.flow.component.html.H2
import com.vaadin.flow.component.orderedlayout.VerticalLayout
import com.vaadin.flow.component.textfield.TextField
import com.vaadin.flow.router.BeforeEvent
import com.vaadin.flow.router.HasUrlParameter
import com.vaadin.flow.router.Route
import com.vaadin.flow.router.WildcardParameter
import com.yak.christmas.business.ChristmasListBC
import com.yak.christmas.model.Person
import com.yak.christmas.model.Present
import javax.inject.Inject


@Route("persons")
open class PresentView @Inject constructor(private val christmasListBC: ChristmasListBC,
                                           vararg children: Component?) :
        VerticalLayout(*children),
        HasUrlParameter<String> {

    var person: Person? = null

    init {
        this.add(
                H1("Presents")
        )
    }

    override fun setParameter(event: BeforeEvent, @WildcardParameter path: String) {
        person = christmasListBC.findPersonById(path.substring(0, 6))
        person?.giftPerson?.let { } ?: run { event.rerouteTo("welcome") }

        when (path.substring(7)) {
            "presents" -> presentPage()
            "presents-to-buy" -> buyingPage()
        }
    }

    private fun buyingPage() {
        val presentGrid: Grid<Present> = getPresentGrid(person!!.giftPerson!!.wantedPresents)

        val ownPresentsButton = Button("See my present list.")
        ownPresentsButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY)
        ownPresentsButton.addClickListener {
            UI.getCurrent().page.setLocation("persons/${person!!.id}/presents")
        }
        add(H2("Presents that I can buy for ${person!!.giftPersonName}"),
                presentGrid,
                ownPresentsButton,
                ViewUtil.logoutButton())
    }

    private fun presentPage() {
        val presentGrid: Grid<Present> = getPresentGrid(person!!.wantedPresents)

        val presentsToBuyButton = Button("See presents for ${person!!.giftPersonName}")
        presentsToBuyButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY)
        presentsToBuyButton.addClickListener {
            UI.getCurrent().page.setLocation("persons/${person!!.id}/presents-to-buy")
        }

        val titleField = TextField()
        titleField.label = "Title"
        val linkField = TextField()
        linkField.label = "Link"
        val descriptionField = TextField()
        descriptionField.label = "Description"

        presentGrid.addItemClickListener { event ->
            UI.getCurrent().page.setLocation("presents/${event.item.id}")
        }

        add(H2("My presents"),
                presentGrid,
                presentForm(),
                presentsToBuyButton,
                ViewUtil.logoutButton())
    }

    private fun getPresentGrid(presents: List<Present>): Grid<Present> {
        var presentGrid: Grid<Present> = Grid<Present>(Present::class.java)
        presentGrid.setItems(presents)
        presentGrid.setColumns("presentName", "link", "description")
        return presentGrid
    }

    private fun presentForm(): FormLayout {
        val presentLayout = FormLayout()

        val titleField = TextField()
        titleField.label = "Title"
        titleField.placeholder = "Present for Cédric"
        titleField.minLength = 1
        titleField.isRequired = true
        val linkField = TextField()
        linkField.label = "Link"
        linkField.placeholder = "http://mauquoi.com"
        val descriptionField = TextField()
        descriptionField.label = "Description"
        descriptionField.placeholder = "More info"

        presentLayout.setResponsiveSteps(
                ResponsiveStep("25em", 1),
                ResponsiveStep("32em", 2))

        val addPresentButton = Button("Add present")
        addPresentButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY)
        addPresentButton.addClickListener {
            christmasListBC.addPresent(person!!.id, Present(1, titleField.value, linkField.value, descriptionField.value))
            UI.getCurrent().page.reload()
        }

        presentLayout.add(titleField, linkField, descriptionField, addPresentButton)
        return presentLayout
    }
}