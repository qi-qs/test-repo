def count = 1000
def prefix = 'c'
def data = (1..count).collect { i ->
        def index = ((i - 1) % 20000) + 1
        def name =  prefix + "-role-" + index
        def age =  prefix + "-age-" + index

        [
           name:name,
           age: age
        ]
    }

def dataList = groovy.json.JsonOutput.toJson(data)

println(dataList)
return [data: data]