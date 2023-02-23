layout 'layout/main/main.tpl', true,
    bodyContents: contents {
        model.put('post', content)
        div(class: "row docs") {
            div(class: "col s3 index") {
                div(class: "section back-index") {
                    a(href: "/blog", class: "") {
                        span("Back to index")
                    }
                }
                
                div(class: "author-block brand-logo") {
                    img(src:"/img/avatar.png")
                    div(class: "author-info") {
                        h6(content.author)
                        p("Creator")
                    }
                }
                
                ul(class: "metadata-block") {
                    li {
                        h6("Metadata")
                    }
                    li(class: "metadata") {
                        i(class: "material-icons", "calendar_month")
                        span(content.date?.format("MM/dd/yyyy"))
                    }
                    li(class: "metadata") {
                        i(class: "material-icons", "menu_book")
                        span("In blog")
                    }
                    li(class: "metadata") {
                        i(class: "material-icons", "timer")
                        span("5 min red")
                    }                   
                }
                            
            }

            div(class: "col l6 m9 content") {
                include template: 'components/page-edit.tpl'
                yieldUnescaped content.body
            }

            div(class: "col l3 m0 subindex") {
                include template: 'components/page-right-menu.tpl'                
            }

        }            
    }
