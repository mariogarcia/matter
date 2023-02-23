div(class: "container") {
    div(class: "row project-links") {
        div(class: "col s12 m6") {
            h5(class: "indigo-text text-lighten-5", "matter")
            p(class: "indigo-text text-lighten-5") {
                yieldUnescaped """
                Matter is an open source project to bring Groovy to the data 
                analysis scene.
                We are a team of motivated individuals working on this project like
                it's our full time job. Any amount would help support and continue
                development on this project and is greatly appreciated.
                """
            }
        }
        div(class: "col s12 m3") {
            h5("links")
            ul {
                li { a(class: "white-text", href: "/guide/intro.html", "Documentation") }
                li { a(class: "white-text", href: "https://www.github.com/grooviter/matter", "Source Code")}
            }
        }
        div(class: "col s12 m3") {
            h5("status")
            ul {
                li {
                    a(href:"/grooviter/gql/blob/main") {
                        img(
                            src: "https://camo.githubusercontent.com/e3ac0274e03fbc68150e17dd0999fa82cb35dcfa9ea6fecefced20f5e81c05a6/68747470733a2f2f696d672e736869656c64732e696f2f6769746875622f6c6963656e73652f67726f6f76697465722f67716c2e737667",
                            alt: "license" ,
                            style: "max-width: 100%;"                        
                        )
                    }
                }
            }
        }
    }
}