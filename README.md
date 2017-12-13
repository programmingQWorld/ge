# ge
A website for showing and selling painting, deploy by SSM framework.

********
API 文档
====
目录
----
* [获取商品接口](#获取商品接口)
* [根据标签id获取商品接口](#根据标签id获取商品接口)
* [根据商品id获取单个商品接口](#根据商品id获取单个商品接口)
* [获取标签接口](#获取标签接口)
* [上传商品图片接口](#上传商品图片接口)


获取商品接口
----
查询存在的商品，按上架时间倒序排序

#### HTTP请求方式
GET

#### 请求URL
http://10.10.112.170:8080/commodity/list

#### 请求参数
|   参数名 |   必选 |   类型 |   说明 |
| :------  | :----: | :----: | :----: |
| offset   |   否   |   int  | 从哪条记录开始查询，默认为0 | 
| limit    |   否   |   int  | 查询多少条记录，默认为4     |

#### 返回数据
|   参数名 |   类型 |   说明 |
| :------  | :----: | :----: |
| success  | boolean| 请求是否成功标识 | 
| errorMsg | string | 默认为空，success不为true时返回错误信息 |
| data     | json   | 结果数据，成功时返回，失败时返回空      |
| __status | int    | 状态码，具体解释看info参数 |
| __info   | string | 具体的状态信息 |
| __commodityList| json | 查询出来的商品json数据集合 |
| __commodity| json | 单个商品json数据，在此请求默认为空 |

----

根据标签id获取商品接口
----
查询某标签下的商品，按上架时间倒序排序

#### HTTP请求方式
GET

#### 请求URL
http://10.10.112.170:8080/tag/{tagId}/commodity

#### 请求参数
|   参数名 |   必选 |   类型 |   说明 |
| :------  | :----: | :----: | :----: |
| {tagId}  |   是   |   int  | 标签id | 
| offset   |   否   |   int  | 从哪条记录开始查询，默认为0 | 
| limit    |   否   |   int  | 查询多少条记录，默认为4     |

#### 返回数据
|   参数名 |   类型 |   说明 |
| :------  | :----: | :----: |
| success  | boolean| 请求是否成功标识 | 
| errorMsg | string | 默认为空，success不为true时返回错误信息 |
| data     | json   | 结果数据，成功时返回，失败时返回空      |
| __status | int    | 状态码，具体解释看info参数 |
| __info   | string | 具体的状态信息 |
| __commodityList| json | 查询出来的商品json数据集合 |
| __commodity| json | 单个商品json数据，在此请求默认为空 |

----

根据商品id获取单个商品接口
----
查询单个商品

#### HTTP请求方式
GET

#### 请求URL
http://10.10.112.170:8080/commodity/{commodityId}/detail

#### 请求参数
|   参数名 |   必选 |   类型 |   说明 |
| :------  | :----: | :----: | :----: |
| {commodityId}|   是   |   int  | 商品id | 

#### 返回数据
|   参数名 |   类型 |   说明 |
| :------  | :----: | :----: |
| success  | boolean| 请求是否成功标识 | 
| errorMsg | string | 默认为空，success不为true时返回错误信息 |
| data     | json   | 结果数据，成功时返回，失败时返回空      |
| __status | int    | 状态码，具体解释看info参数 |
| __info   | string | 具体的状态信息 |
| __commodityList| json | 查询出来的商品json数据集合，在此请求默认为空 |
| __commodity| json | 单个商品json数据 |

----

获取标签接口
----
查询已存在的标签

#### HTTP请求方式
GET

#### 请求URL
http://10.10.112.170:8080/tag/list

#### 请求参数
|   参数名 |   必选 |   类型 |   说明 |
| :------  | :----: | :----: | :----: |
| offset   |   否   |   int  | 从哪条记录开始查询，默认为0 | 
| limit    |   否   |   int  | 查询多少条记录，默认为4     |

#### 返回数据
|   参数名 |   类型 |   说明 |
| :------  | :----: | :----: |
| success  | boolean| 请求是否成功标识 | 
| errorMsg | string | 默认为空，success不为true时返回错误信息 |
| data     | json   | 结果数据，成功时返回，失败时返回空      |
| __status | int    | 状态码，具体解释看info参数 |
| __info   | string | 具体的状态信息 |
| __tagList| json   | 查询出来的标签json数据集合 |

----

上传商品图片接口
----
上传商品图片

#### HTTP请求方式
POST

#### 请求URL
http://10.10.112.170:8080/commodity/upload

#### 请求参数
|   参数名 |   必选 |   类型 |   说明 |
| :------  | :----: | :----: | :----: |
| file     |   是   |   MultipartFile  | 上传的图片文件 |

#### 返回数据
|   参数名 |   类型 |   说明 |
| :------  | :----: | :----: |
| success  | boolean| 请求是否成功标识 | 
| errorMsg | string | 默认为空，success不为true时返回错误信息 |
| data     | json   | 结果数据，成功时返回，失败时返回空      |
| __status | int    | 状态码，具体解释看info参数 |
| __info   | string | 具体的状态信息 |
| __filename| string| 上传后的文件路径 |

----