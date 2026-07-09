def count = 1000
def prefix='c'
def data = (1..count).collect { i ->
        def roleId = ((i - 1) % 2) + 1  // 1,2,1,2...
        def roleLabel = "role${roleId}".toString()
        [
            username: prefix + "-dev1-${i}".toString(),
            email: prefix + "-dev1-${i}@xxx.com".toString(),
            role: [
                [label: roleLabel, id: roleId]
            ]
        ]
    }

def dataList = groovy.json.JsonOutput.toJson(data)
def ids = []

println(dataList)

return [data: data, ids:ids]