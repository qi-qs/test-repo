// 处理 Name 的函数
def processName = { name ->
    if (name == null || name.toString().trim().isEmpty()) {
        // 如果 nickName 为空，则设置为 name + '.1'
        return name + ".1"
    } else {
        // 如果 nickName 包含 .n，则提取数字并 +1
        def match = name =~ /\.(\d+)$/
        if (match.find()) {
            def num = match[0][1] as Integer
            def baseName = name.substring(0, name.lastIndexOf('.'))
            return baseName + "." + (num + 1)
        } else {
            // 如果不包含 .n 格式，则直接返回原 nickName
            return name + ".1"
        }
    }
}

var list = dataList.collect{ 
    
    return [id: it.id, 
    name: processName(it.name), 
    description: processName(it.description),
    permissions: [],
    permissionGroups: []
]}

def ids = list.collect{it.id}

return [dataList: list, ids: ids]