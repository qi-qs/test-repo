def count = 10000
def prefix = 'c'
def data = (1..count).collect { i ->
        def roleId = ((i - 1) % 2) + 1  // 1,2,1,2...
        def sourceId = ((i - 1) % 20000) + 1
        def source = prefix + "-source-" + sourceId

        def roleLabel = "role${roleId}".toString()
        [
            username: prefix + "-dev1-${i}".toString(),
            email: prefix + "-dev1-${i}@xxx.com".toString(),
            userSource: source
            // role: [
            //     [label: roleLabel, id: roleId]
            // ]
        ]
    }

def dataList = groovy.json.JsonOutput.toJson(data)

println(dataList)

return [data: data]