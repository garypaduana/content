import groovy.json.JsonSlurper
import static groovy.json.JsonOutput.*

def s3Root = "https://s3-us-west-2.amazonaws.com/paduana-photos/photography"

def slurper = new JsonSlurper()
File parent = new File(args[0])
json = [:]
json.images = []
parent.eachFile(){child ->
    if(child.isFile() && child.getName() ==~ ".*(jpg|JPG|png|PNG)"){
        json.images.add([
            title: "",
            url: "$s3Root/${child.getName()}",
            thumbnail: "$s3Root/thumbnails/${child.getName()}"
        ])
    }
}

println prettyPrint(toJson(json))