layout 'layout/main/main.tpl', true,
    bodyContents: contents {
        model.put('post', content)
        model.put("entries", published_posts)        
        div(class: "row docs blog") {
            div(class: "col s3 index") {
                
                ul(class: "metadata-block") {
                    li {
                        h6("Latest")
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
                div(class: "row blog-entry") {
                    h4("Blog")
                }
                published_posts.each { entry ->
                    div(class: "row blog-entry") {
                        ul(class: "metadata-block-horizontal") {
                            li(class: "metadata") {
                                img(src:"/img/avatar.png")
                            }
                            li(class: "metadata") {
                                span(content.date?.format("MM/dd/yyyy"))
                            }
                            li(class: "metadata") {
                                span("In blog")
                            }
                            li(class: "metadata") {
                                span("5 min red")
                            }                            
                        }
                        a(href: "/${entry.uri}") {
                            h4(entry.title)
                        }                        
                        h6(entry.hightlight)
                        p(entry.summary)
                        a(href: "/${entry.uri}", class: "bare", "Continue reading")
                    }
                
                }
            }

            div(class: "col l3 m0 subindex") {
                
            }

        }            
    }
