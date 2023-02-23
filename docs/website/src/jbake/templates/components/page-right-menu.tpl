class NodeX {
    String id = "", title  = ""
    Integer level = 0
    NodeX parent  = null
    List children = []

    void leftShift(NodeX node) {
        this.children << node
        node.parent = this
    }

    String toString() {
        return "$id - $title - $level - ${children.size()}"
    }
}

def pattern  = "(?i)<h([1-6].*?) id=\"(.*)\">(.*?)</h([1-6])>"
def matcher  = content.body =~ pattern
def seed     = new NodeX()

def subindexes = matcher.inject(seed) { acc, next -> 
    def (level, title, id) = next[-1..-3]
    def node = [level: Integer.parseInt(level), title: title, id: id] as NodeX

    if (node.level == acc.level) {
        if (!acc.parent) {
            acc.parent = new NodeX()
        }
        acc.parent << node
    } else if (node.level > acc.level) {
        acc << node
    } else if (node.level < acc.level) {
        node << acc
    }
    acc
}

def createSubindexHref(nodeX, contentX) {
    println contentX.uri
    return nodeX.parent 
        ? "/${contentX.uri}#${nodeX.id}" 
        : "/guide/${nodeX.title.toLowerCase()}"
}

def createSubindexClass(nodeX, parentX) {
    def condition = nodeX.title.toLowerCase() == parentX.title.toLowerCase()
    return "indigo-text ${condition ? 'selected indigo-text' : ''}"
}

def getSubindexIndent(nodeX, parentX) {
    return nodeX.level
}

ul(){
    subindexes.children.each { guide -> 
        li(class: "level-${getSubindexIndent(guide, content)}") {
            a(
                class: createSubindexClass(guide, content), 
                href: createSubindexHref(guide, content),
                guide.title
            )
        }        
    }
}    