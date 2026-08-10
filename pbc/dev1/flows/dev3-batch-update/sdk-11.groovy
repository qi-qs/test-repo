// 处理 nickName 的函数
def processNickName = { name, nickName ->
    if (nickName == null || nickName.toString().trim().isEmpty()) {
        // 如果 nickName 为空，则设置为 name + '.1'
        return name + ".1"
    } else {
        // 如果 nickName 包含 .n，则提取数字并 +1
        def match = nickName =~ /\.(\d+)$/
        if (match.find()) {
            def num = match[0][1] as Integer
            def baseName = nickName.substring(0, nickName.lastIndexOf('.'))
            return baseName + "." + (num + 1)
        } else {
            // 如果不包含 .n 格式，则直接返回原 nickName
            return nickName + ".1"
        }
    }
}

// 创建 name -> [id, nickName] 的映射，只保留 id 不为空的 dataList 项
def nameToInfoMap = idList.findAll { it.id != null }.collectEntries { 
    def processedNickName = processNickName(it.name, it.nickName)

    println("nameToInfoMap::name=${it.name},nickName=${it.nickName},processedNickName=$processedNickName")

    [(it.name): [id: it.id, nickName: processedNickName]]
}

def list = dataList.findAll { dataItem ->
    nameToInfoMap.containsKey(dataItem.username)
}.collect { dataItem ->
    def mergedItem = new LinkedHashMap(dataItem)
    def info = nameToInfoMap[dataItem.username]
    mergedItem["id"] = info.id
    mergedItem["nickName"] = info.nickName
    return mergedItem
}

def ids = list.collect{it.id}

return [dataList: list, ids: ids]