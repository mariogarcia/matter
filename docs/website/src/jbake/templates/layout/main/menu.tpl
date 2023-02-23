div(class:"navbar-fixed") {
    nav(role: "navigation", class: "indigo z-depth-1") {
        div(class: "nav-wrapper container") {
            a(href: "/", class: "brand-logo", "Matter") {
                img(src: "/img/atom.svg")
                span("Matter")
            }
            ul(class: "right hide-on-med-and-down") {
                li { 
                    a(href: "/guide/intro.html", "Documentation") 
                }

                li(class: "github-wrapper") {
                    div(class: "github") {
                        img(src:"/img/git.svg")
                        div(class: "version") {
                            a(class: "ghrepo", target: "_blank", href: config.site_github_site, "grooviter/matter")                                                    
                            span(config.matter_version)
                        }
                    }                  
                }
            }            
            ul(id:"nav-mobile", class: "sidenav") {
                li { a(href: "/guide/intro.html", "Documentation") }
            }
            a(href: "#", 'data-target': "nav-mobile", class: "sidenav-trigger") {
                i(class: "material-icons-menu", "menu")
            }
        }
    }
}
