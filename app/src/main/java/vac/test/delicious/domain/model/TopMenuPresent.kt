package vac.test.delicious.domain.model

import vac.test.delicious.domain.entities.TopMenu

class TopMenuPresent(private val topMenu: TopMenu) : TopMenu {

    override val id: Long
        get() = topMenu.id
    override val title: String
        get() = topMenu.title

    var isSelected = false
}