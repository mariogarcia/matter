layout 'layout/main/main.tpl', true, 
    bodyContents: contents {
    model.put(
        "parentSection",
        published_guides.find { it.title.toLowerCase() == content.parent } ?: content
    )
    model.put(
        "sections", 
        [parentSection] + (!content.parent 
            ? published_guides.findAll { it?.parent == content.title.toLowerCase() }
            : published_guides.findAll { it.parent == content.parent })
    )
    div(class: "row docs") {
        div(class: "col s3 index") {
            ul(){        
                published_guides
                    .findAll { !it.parent }
                    .sort { it.order }
                    .each { guide -> 
                        li() {
                            a(
                                class: "indigo-text ${guide.title.toLowerCase() == content.title.toLowerCase() || guide.title.toLowerCase() == content.parent ? 'selected indigo-text' : ''}",
                                href: "/guide/${guide.title.toLowerCase()}.html", 
                                "${guide.title}"
                            )
                        }        
                }
            }
        }

        div(class: "col l6 m9 body") {
            include template: 'components/page-edit.tpl'
            yieldUnescaped content.body
        }

        div(class: "col l3 m0 subindex") {
            include template: 'components/page-right-menu.tpl'
        }
    }
}