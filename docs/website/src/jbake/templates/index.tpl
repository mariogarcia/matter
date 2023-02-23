layout 'layout/main/main.tpl', true,
        projects: projects,
        bodyContents: contents {
            div(class: "section bg-network") {
                div(class: "container jumbo") {     
                    img(src: "/img/atom.svg", width: 80, height: 80)
                    h1(class: "header title center-align indigo-text darken-4", "Matter")
                    div(class: "row subtitle center-align") {
                        h5(class: "header col s12", "A Jupyter Kernel for Groovy Language")
                    }
                    div(class: "row center-align") {
                        a(href: "/guide/intro.html", class: "btn-large waves-effect waves-light indigo", "Get Started")
                    }
                }
            }
            div(class: "container") {
                div(class: "section") {
                    div(class: "row") {
                        div(class: "col s12 m4") {
                            h2(class: "center-align") {
                                i(class: "medium material-icons indigo-text darken-4", "table_view")
                            }
                            h5(class: "center-align", "Groovy Data & Friends")
                            p() {
                                span("Matter uses") 
                                a(class: "feature-link", href: "https://www.groovy-lang.org", target: "_blank", "Groovy") 
                                span("to create an abstraction over different JVM data analysis and ML tools to offer a common interface easier to learn and remember.")
                            }
                        }


                        div(class: "col s12 m4") {
                            h2(class: "center-align") {
                                i(class: "medium material-icons indigo-text darken-4", "insights")
                            }
                            h5(class: "center-align", "Plotting and Table Display")
                            p() {
                                yieldUnescaped """
                                Matter has APIs for interactive time-series, scatter plots, histograms, 
                                heatmaps, and treemaps to be used inside your Jupyter notebooks
                                """
                            }
                        }


                        div(class: "col s12 m4") {
                            h2(class: "center-align") {
                                i(class: "medium material-icons indigo-text darken-4", "hub")
                            }
                            h5(class: "center-align", "Integration with JVM Tools")
                            p() {
                                span("With Matter you can choose whether to use the default implementation, ")
                                span("or the implementation library of your choice: ")
                                a(class: "feature-link", href: "https://github.com/jtablesaw/tablesaw", target: "_blank", "Tablesaw")
                                span(",")
                                a(class: "feature-link", href: "https://haifengl.github.io/", target: "_blank", "Smile")
                                span(",")
                                a(class: "feature-link", href: "https://tribuo.org/", target: "_blank", "Tribuo")
                                span(",")
                                a(class: "feature-link", href: "https://docs.djl.ai/", target: "_blank", "DLJ")                                
                            }
                        }


                    }
                }
            }
        }

