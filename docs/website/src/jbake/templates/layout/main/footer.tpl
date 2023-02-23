footer(class: "page-footer indigo") {
    include template: 'layout/main/footer/links.tpl'
    include template: 'layout/main/footer/copyright.tpl'
}

newLine()
script(src:"${config.site_contextPath}js/materialize.js"){} newLine()
script(src:"${config.site_contextPath}js/highlight.js"){} newLine()
script(src:"${config.site_contextPath}js/groovy.min.js"){} newLine()


newLine()
script { 
    yieldUnescaped "M.AutoInit();"
    yieldUnescaped "hljs.highlightAll();"
}