yieldUnescaped '<!DOCTYPE html>'
html(lang:'en'){
    head {
        include template: "layout/main/header.tpl"
    }
    body {
        include template: 'layout/main/menu.tpl'
        
        bodyContents()
        
        include template: 'layout/main/footer.tpl'
    }
}
newLine()
