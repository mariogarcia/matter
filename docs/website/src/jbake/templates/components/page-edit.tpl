def generateGithubLink(config, title) {
    return "${config.site_github_source}/docs/website/${title.toLowerCase().replace('html', 'adoc')}"
}

def generateGithubEditLink(config, title){
    return "${config.site_github_source}/edit/master/docs/website/${title.toLowerCase().replace('html', 'adoc')}"
}

ul(class: "page-source-links page-tools") {
    li {
        a(
            href: generateGithubLink(config, content.uri), 
            class: "tooltipped", 
            'data-tooltip': "Check Page Source"
        ) {
            i(class: "material-icons", "description")
        }                        
    }
    li {
        a(
            href: generateGithubEditLink(config, content.uri),
            class: "tooltipped", 
            'data-tooltip': "Edit Page in Github"
        ) {
            i(class: "material-icons", "edit")
        }
    }
}