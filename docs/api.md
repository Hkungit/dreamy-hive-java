# OpenAPI definition


**简介**:OpenAPI definition


**HOST**:http://localhost:8080/api


**联系人**:


**Version**:v0


**接口路径**:/api/v3/api-docs/default


[TOC]






# 地址模块


## 添加收货地址


**接口地址**:`/api/api/v1/address/add`


**请求方式**:`POST`


**请求数据类型**:`application/x-www-form-urlencoded,application/json`


**响应数据类型**:`*/*`


**接口描述**:<p>添加新的收货地址</p>



**请求示例**:


```javascript
{
  "id": 0,
  "receiver": "",
  "phone": "",
  "province": "",
  "city": "",
  "district": "",
  "detail": "",
  "isDefault": 0
}
```


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|addressRequestDTO|AddressRequestDTO|body|true|AddressRequestDTO|AddressRequestDTO|
|&emsp;&emsp;id|||false|integer(int64)||
|&emsp;&emsp;receiver|||true|string||
|&emsp;&emsp;phone|||true|string||
|&emsp;&emsp;province|||true|string||
|&emsp;&emsp;city|||true|string||
|&emsp;&emsp;district|||true|string||
|&emsp;&emsp;detail|||true|string||
|&emsp;&emsp;isDefault|||false|integer(int32)||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|添加成功|ResultObject|
|400|添加失败，参数不合法|ResultObject|
|401|未登录|ResultObject|
|500|Internal Server Error|ResultObject|


**响应状态码-200**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


**响应状态码-400**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


**响应状态码-401**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


**响应状态码-500**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


## 获取默认地址


**接口地址**:`/api/api/v1/address/default`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:<p>获取当前用户的默认收货地址</p>



**请求参数**:


暂无


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|获取成功|AddressResponseDTO|
|400|Bad Request|ResultObject|
|401|未登录|ResultObject|
|500|Internal Server Error|ResultObject|


**响应状态码-200**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|id||integer(int64)|integer(int64)|
|userId||integer(int64)|integer(int64)|
|receiver||string||
|phone||string||
|province||string||
|city||string||
|district||string||
|detail||string||
|isDefault||integer(int32)|integer(int32)|
|fullAddress||string||
|createTime||string(date-time)|string(date-time)|


**响应示例**:
```javascript
{
	"id": 0,
	"userId": 0,
	"receiver": "",
	"phone": "",
	"province": "",
	"city": "",
	"district": "",
	"detail": "",
	"isDefault": 0,
	"fullAddress": "",
	"createTime": ""
}
```


**响应状态码-400**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


**响应状态码-401**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


**响应状态码-500**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


## 删除地址


**接口地址**:`/api/api/v1/address/delete/{id}`


**请求方式**:`DELETE`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:<p>删除指定的收货地址</p>



**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|id|地址ID|path|true|integer(int64)||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|删除成功|ResultObject|
|400|删除失败，地址不存在或无权操作|ResultObject|
|401|未登录|ResultObject|
|500|Internal Server Error|ResultObject|


**响应状态码-200**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


**响应状态码-400**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


**响应状态码-401**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


**响应状态码-500**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


## 获取地址详情


**接口地址**:`/api/api/v1/address/detail/{id}`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:<p>根据ID获取收货地址详情</p>



**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|id|地址ID|path|true|integer(int64)||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|获取成功|AddressResponseDTO|
|400|地址不存在或无权访问|ResultObject|
|401|未登录|ResultObject|
|500|Internal Server Error|ResultObject|


**响应状态码-200**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|id||integer(int64)|integer(int64)|
|userId||integer(int64)|integer(int64)|
|receiver||string||
|phone||string||
|province||string||
|city||string||
|district||string||
|detail||string||
|isDefault||integer(int32)|integer(int32)|
|fullAddress||string||
|createTime||string(date-time)|string(date-time)|


**响应示例**:
```javascript
{
	"id": 0,
	"userId": 0,
	"receiver": "",
	"phone": "",
	"province": "",
	"city": "",
	"district": "",
	"detail": "",
	"isDefault": 0,
	"fullAddress": "",
	"createTime": ""
}
```


**响应状态码-400**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


**响应状态码-401**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


**响应状态码-500**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


## 获取地址列表


**接口地址**:`/api/api/v1/address/list`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:<p>获取当前用户的所有收货地址</p>



**请求参数**:


暂无


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|获取成功|AddressResponseDTO|
|400|Bad Request|ResultObject|
|401|未登录|ResultObject|
|500|Internal Server Error|ResultObject|


**响应状态码-200**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|id||integer(int64)|integer(int64)|
|userId||integer(int64)|integer(int64)|
|receiver||string||
|phone||string||
|province||string||
|city||string||
|district||string||
|detail||string||
|isDefault||integer(int32)|integer(int32)|
|fullAddress||string||
|createTime||string(date-time)|string(date-time)|


**响应示例**:
```javascript
[
	{
		"id": 0,
		"userId": 0,
		"receiver": "",
		"phone": "",
		"province": "",
		"city": "",
		"district": "",
		"detail": "",
		"isDefault": 0,
		"fullAddress": "",
		"createTime": ""
	}
]
```


**响应状态码-400**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
[
	{
		"success": true,
		"code": 0,
		"message": "",
		"data": {}
	}
]
```


**响应状态码-401**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
[
	{
		"success": true,
		"code": 0,
		"message": "",
		"data": {}
	}
]
```


**响应状态码-500**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
[
	{
		"success": true,
		"code": 0,
		"message": "",
		"data": {}
	}
]
```


## 设置默认地址


**接口地址**:`/api/api/v1/address/set-default/{id}`


**请求方式**:`PUT`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:<p>将指定地址设为默认收货地址</p>



**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|id|地址ID|path|true|integer(int64)||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|设置成功|ResultObject|
|400|设置失败，地址不存在或无权操作|ResultObject|
|401|未登录|ResultObject|
|500|Internal Server Error|ResultObject|


**响应状态码-200**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


**响应状态码-400**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


**响应状态码-401**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


**响应状态码-500**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


## 更新地址


**接口地址**:`/api/api/v1/address/update`


**请求方式**:`PUT`


**请求数据类型**:`application/x-www-form-urlencoded,application/json`


**响应数据类型**:`*/*`


**接口描述**:<p>更新现有的收货地址</p>



**请求示例**:


```javascript
{
  "id": 0,
  "receiver": "",
  "phone": "",
  "province": "",
  "city": "",
  "district": "",
  "detail": "",
  "isDefault": 0
}
```


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|addressRequestDTO|AddressRequestDTO|body|true|AddressRequestDTO|AddressRequestDTO|
|&emsp;&emsp;id|||false|integer(int64)||
|&emsp;&emsp;receiver|||true|string||
|&emsp;&emsp;phone|||true|string||
|&emsp;&emsp;province|||true|string||
|&emsp;&emsp;city|||true|string||
|&emsp;&emsp;district|||true|string||
|&emsp;&emsp;detail|||true|string||
|&emsp;&emsp;isDefault|||false|integer(int32)||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|更新成功|ResultObject|
|400|更新失败，地址不存在或无权操作|ResultObject|
|401|未登录|ResultObject|
|500|Internal Server Error|ResultObject|


**响应状态码-200**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


**响应状态码-400**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


**响应状态码-401**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


**响应状态码-500**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


# 点赞管理


## 点赞或取消点赞


**接口地址**:`/api/api/v1/posts/{postId}/like`


**请求方式**:`POST`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:<p>对帖子进行点赞或取消点赞操作</p>



**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|postId||path|true|integer(int64)||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|ResultBoolean|
|400|Bad Request|ResultObject|
|500|Internal Server Error|ResultObject|


**响应状态码-200**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||boolean||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": true
}
```


**响应状态码-400**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


**响应状态码-500**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


## 获取点赞用户


**接口地址**:`/api/api/v1/posts/{postId}/likes`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:<p>获取对帖子点赞的用户列表</p>



**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|postId||path|true|integer(int64)||
|pageNum||query|false|integer(int32)||
|pageSize||query|false|integer(int32)||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|ResultIPageUser|
|400|Bad Request|ResultObject|
|500|Internal Server Error|ResultObject|


**响应状态码-200**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||IPageUser|IPageUser|
|&emsp;&emsp;size||integer(int64)||
|&emsp;&emsp;total||integer(int64)||
|&emsp;&emsp;current||integer(int64)||
|&emsp;&emsp;records||array|User|
|&emsp;&emsp;&emsp;&emsp;id||integer(int64)||
|&emsp;&emsp;&emsp;&emsp;username||string||
|&emsp;&emsp;&emsp;&emsp;password||string||
|&emsp;&emsp;&emsp;&emsp;nickname||string||
|&emsp;&emsp;&emsp;&emsp;avatar||string||
|&emsp;&emsp;&emsp;&emsp;phone||string||
|&emsp;&emsp;&emsp;&emsp;email||string||
|&emsp;&emsp;&emsp;&emsp;gender||integer(int32)||
|&emsp;&emsp;&emsp;&emsp;birthday||string(date-time)||
|&emsp;&emsp;&emsp;&emsp;status||integer(int32)||
|&emsp;&emsp;&emsp;&emsp;createTime||string(date-time)||
|&emsp;&emsp;&emsp;&emsp;updateTime||string(date-time)||
|&emsp;&emsp;&emsp;&emsp;createBy||integer(int64)||
|&emsp;&emsp;&emsp;&emsp;updateBy||integer(int64)||
|&emsp;&emsp;&emsp;&emsp;deleted||integer(int32)||
|&emsp;&emsp;pages||integer(int64)||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {
		"size": 0,
		"total": 0,
		"current": 0,
		"records": [
			{
				"id": 0,
				"username": "",
				"password": "",
				"nickname": "",
				"avatar": "",
				"phone": "",
				"email": "",
				"gender": 0,
				"birthday": "",
				"status": 0,
				"createTime": "",
				"updateTime": "",
				"createBy": 0,
				"updateBy": 0,
				"deleted": 0
			}
		],
		"pages": 0
	}
}
```


**响应状态码-400**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


**响应状态码-500**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


## 获取用户点赞帖子


**接口地址**:`/api/api/v1/users/{userId}/liked-posts`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:<p>获取用户点赞过的帖子ID列表</p>



**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|userId||path|true|integer(int64)||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|ResultListLong|
|400|Bad Request|ResultObject|
|500|Internal Server Error|ResultObject|


**响应状态码-200**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||array||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": []
}
```


**响应状态码-400**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


**响应状态码-500**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


# 购物车模块


## 添加商品到购物车


**接口地址**:`/api/api/v1/cart/add`


**请求方式**:`POST`


**请求数据类型**:`application/x-www-form-urlencoded,application/json`


**响应数据类型**:`*/*`


**接口描述**:<p>添加商品到购物车，如果已存在则增加数量</p>



**请求示例**:


```javascript
{
  "skuId": 0,
  "quantity": 0
}
```


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|cartAddRequestDTO|CartAddRequestDTO|body|true|CartAddRequestDTO|CartAddRequestDTO|
|&emsp;&emsp;skuId|||true|integer(int64)||
|&emsp;&emsp;quantity|||true|integer(int32)||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|添加成功|ResultObject|
|400|添加失败，商品不存在或参数不合法|ResultObject|
|401|未登录|ResultObject|
|500|Internal Server Error|ResultObject|


**响应状态码-200**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


**响应状态码-400**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


**响应状态码-401**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


**响应状态码-500**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


## 选中-取消选中购物车商品


**接口地址**:`/api/api/v1/cart/check/{id}/{checked}`


**请求方式**:`PUT`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:<p>选中或取消选中购物车中的商品</p>



**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|id|购物车项ID|path|true|integer(int64)||
|checked|是否选中：1-选中，0-取消选中|path|true|integer(int32)||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|操作成功|ResultObject|
|400|操作失败，商品不存在或无权操作|ResultObject|
|401|未登录|ResultObject|
|500|Internal Server Error|ResultObject|


**响应状态码-200**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


**响应状态码-400**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


**响应状态码-401**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


**响应状态码-500**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


## 删除购物车商品


**接口地址**:`/api/api/v1/cart/delete/{id}`


**请求方式**:`DELETE`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:<p>从购物车中删除指定商品</p>



**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|id|购物车项ID|path|true|integer(int64)||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|删除成功|ResultObject|
|400|删除失败，商品不存在或无权操作|ResultObject|
|401|未登录|ResultObject|
|500|Internal Server Error|ResultObject|


**响应状态码-200**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


**响应状态码-400**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


**响应状态码-401**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


**响应状态码-500**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


## 获取购物车列表


**接口地址**:`/api/api/v1/cart/list`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:<p>获取当前用户的购物车商品列表</p>



**请求参数**:


暂无


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|获取成功|CartItemResponseDTO|
|400|Bad Request|ResultObject|
|401|未登录|ResultObject|
|500|Internal Server Error|ResultObject|


**响应状态码-200**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|id||integer(int64)|integer(int64)|
|userId||integer(int64)|integer(int64)|
|skuId||integer(int64)|integer(int64)|
|spuId||integer(int64)|integer(int64)|
|quantity||integer(int32)|integer(int32)|
|checked||integer(int32)|integer(int32)|
|productName||string||
|skuName||string||
|pic||string||
|price||number||
|specs||string||
|subtotal||number||
|stock||integer(int32)|integer(int32)|


**响应示例**:
```javascript
[
	{
		"id": 0,
		"userId": 0,
		"skuId": 0,
		"spuId": 0,
		"quantity": 0,
		"checked": 0,
		"productName": "",
		"skuName": "",
		"pic": "",
		"price": 0,
		"specs": "",
		"subtotal": 0,
		"stock": 0
	}
]
```


**响应状态码-400**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
[
	{
		"success": true,
		"code": 0,
		"message": "",
		"data": {}
	}
]
```


**响应状态码-401**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
[
	{
		"success": true,
		"code": 0,
		"message": "",
		"data": {}
	}
]
```


**响应状态码-500**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
[
	{
		"success": true,
		"code": 0,
		"message": "",
		"data": {}
	}
]
```


## 更新购物车商品数量


**接口地址**:`/api/api/v1/cart/update`


**请求方式**:`PUT`


**请求数据类型**:`application/x-www-form-urlencoded,application/json`


**响应数据类型**:`*/*`


**接口描述**:<p>更新购物车中商品的数量</p>



**请求示例**:


```javascript
{
  "id": 0,
  "quantity": 0
}
```


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|cartUpdateRequestDTO|CartUpdateRequestDTO|body|true|CartUpdateRequestDTO|CartUpdateRequestDTO|
|&emsp;&emsp;id|||true|integer(int64)||
|&emsp;&emsp;quantity|||true|integer(int32)||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|更新成功|ResultObject|
|400|更新失败，商品不存在或参数不合法|ResultObject|
|401|未登录|ResultObject|
|500|Internal Server Error|ResultObject|


**响应状态码-200**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


**响应状态码-400**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


**响应状态码-401**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


**响应状态码-500**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


# 管理端-商品分类


## 分页查询分类列表


**接口地址**:`/api/api/v1/admin/categories`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:<p>分页查询分类列表，支持按名称搜索</p>



**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|pageNum||query|false|integer(int32)||
|pageSize||query|false|integer(int32)||
|name||query|false|string||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|ResultPageCategory|
|400|Bad Request|ResultObject|
|500|Internal Server Error|ResultObject|


**响应状态码-200**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||PageCategory|PageCategory|
|&emsp;&emsp;records||array|Category|
|&emsp;&emsp;&emsp;&emsp;id||integer(int64)||
|&emsp;&emsp;&emsp;&emsp;parentId||integer(int64)||
|&emsp;&emsp;&emsp;&emsp;name||string||
|&emsp;&emsp;&emsp;&emsp;level||integer(int32)||
|&emsp;&emsp;&emsp;&emsp;icon||string||
|&emsp;&emsp;&emsp;&emsp;description||string||
|&emsp;&emsp;&emsp;&emsp;sort||integer(int32)||
|&emsp;&emsp;&emsp;&emsp;status||integer(int32)||
|&emsp;&emsp;&emsp;&emsp;createTime||string(date-time)||
|&emsp;&emsp;&emsp;&emsp;updateTime||string(date-time)||
|&emsp;&emsp;&emsp;&emsp;deleted||integer(int32)||
|&emsp;&emsp;&emsp;&emsp;children||array|Category|
|&emsp;&emsp;total||integer(int64)||
|&emsp;&emsp;size||integer(int64)||
|&emsp;&emsp;current||integer(int64)||
|&emsp;&emsp;orders||array|OrderItem|
|&emsp;&emsp;&emsp;&emsp;column||string||
|&emsp;&emsp;&emsp;&emsp;asc||boolean||
|&emsp;&emsp;optimizeCountSql||boolean||
|&emsp;&emsp;searchCount||boolean||
|&emsp;&emsp;optimizeJoinOfCountSql||boolean||
|&emsp;&emsp;maxLimit||integer(int64)||
|&emsp;&emsp;countId||string||
|&emsp;&emsp;pages||integer(int64)||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {
		"records": [
			{
				"id": 0,
				"parentId": 0,
				"name": "",
				"level": 0,
				"icon": "",
				"description": "",
				"sort": 0,
				"status": 0,
				"createTime": "",
				"updateTime": "",
				"deleted": 0,
				"children": [
					{
						"id": 0,
						"parentId": 0,
						"name": "",
						"level": 0,
						"icon": "",
						"description": "",
						"sort": 0,
						"status": 0,
						"createTime": "",
						"updateTime": "",
						"deleted": 0,
						"children": [
							{}
						]
					}
				]
			}
		],
		"total": 0,
		"size": 0,
		"current": 0,
		"orders": [
			{
				"column": "",
				"asc": true
			}
		],
		"optimizeCountSql": true,
		"searchCount": true,
		"optimizeJoinOfCountSql": true,
		"maxLimit": 0,
		"countId": "",
		"pages": 0
	}
}
```


**响应状态码-400**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


**响应状态码-500**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


## 添加分类


**接口地址**:`/api/api/v1/admin/categories`


**请求方式**:`POST`


**请求数据类型**:`application/x-www-form-urlencoded,application/json`


**响应数据类型**:`*/*`


**接口描述**:<p>添加新的商品分类</p>



**请求示例**:


```javascript
{
  "id": 0,
  "parentId": 0,
  "name": "",
  "level": 0,
  "icon": "",
  "description": "",
  "sort": 0,
  "status": 0,
  "createTime": "",
  "updateTime": "",
  "deleted": 0,
  "children": [
    {
      "id": 0,
      "parentId": 0,
      "name": "",
      "level": 0,
      "icon": "",
      "description": "",
      "sort": 0,
      "status": 0,
      "createTime": "",
      "updateTime": "",
      "deleted": 0,
      "children": [
        {
          "id": 0,
          "parentId": 0,
          "name": "",
          "level": 0,
          "icon": "",
          "description": "",
          "sort": 0,
          "status": 0,
          "createTime": "",
          "updateTime": "",
          "deleted": 0,
          "children": [
            {}
          ]
        }
      ]
    }
  ]
}
```


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|category|Category|body|true|Category|Category|
|&emsp;&emsp;id|||false|integer(int64)||
|&emsp;&emsp;parentId|||false|integer(int64)||
|&emsp;&emsp;name|||false|string||
|&emsp;&emsp;level|||false|integer(int32)||
|&emsp;&emsp;icon|||false|string||
|&emsp;&emsp;description|||false|string||
|&emsp;&emsp;sort|||false|integer(int32)||
|&emsp;&emsp;status|||false|integer(int32)||
|&emsp;&emsp;createTime|||false|string(date-time)||
|&emsp;&emsp;updateTime|||false|string(date-time)||
|&emsp;&emsp;deleted|||false|integer(int32)||
|&emsp;&emsp;children|||false|array|Category|


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|ResultObject|
|400|Bad Request|ResultObject|
|500|Internal Server Error|ResultObject|


**响应状态码-200**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


**响应状态码-400**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


**响应状态码-500**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


## 获取分类详情


**接口地址**:`/api/api/v1/admin/categories/{id}`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:<p>根据ID获取分类详情</p>



**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|id||path|true|integer(int64)||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|ResultCategory|
|400|Bad Request|ResultObject|
|500|Internal Server Error|ResultObject|


**响应状态码-200**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||Category|Category|
|&emsp;&emsp;id||integer(int64)||
|&emsp;&emsp;parentId||integer(int64)||
|&emsp;&emsp;name||string||
|&emsp;&emsp;level||integer(int32)||
|&emsp;&emsp;icon||string||
|&emsp;&emsp;description||string||
|&emsp;&emsp;sort||integer(int32)||
|&emsp;&emsp;status||integer(int32)||
|&emsp;&emsp;createTime||string(date-time)||
|&emsp;&emsp;updateTime||string(date-time)||
|&emsp;&emsp;deleted||integer(int32)||
|&emsp;&emsp;children||array|Category|


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {
		"id": 0,
		"parentId": 0,
		"name": "",
		"level": 0,
		"icon": "",
		"description": "",
		"sort": 0,
		"status": 0,
		"createTime": "",
		"updateTime": "",
		"deleted": 0,
		"children": [
			{
				"id": 0,
				"parentId": 0,
				"name": "",
				"level": 0,
				"icon": "",
				"description": "",
				"sort": 0,
				"status": 0,
				"createTime": "",
				"updateTime": "",
				"deleted": 0,
				"children": [
					{}
				]
			}
		]
	}
}
```


**响应状态码-400**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


**响应状态码-500**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


## 更新分类


**接口地址**:`/api/api/v1/admin/categories/{id}`


**请求方式**:`PUT`


**请求数据类型**:`application/x-www-form-urlencoded,application/json`


**响应数据类型**:`*/*`


**接口描述**:<p>根据ID更新商品分类</p>



**请求示例**:


```javascript
{
  "id": 0,
  "parentId": 0,
  "name": "",
  "level": 0,
  "icon": "",
  "description": "",
  "sort": 0,
  "status": 0,
  "createTime": "",
  "updateTime": "",
  "deleted": 0,
  "children": [
    {
      "id": 0,
      "parentId": 0,
      "name": "",
      "level": 0,
      "icon": "",
      "description": "",
      "sort": 0,
      "status": 0,
      "createTime": "",
      "updateTime": "",
      "deleted": 0,
      "children": [
        {
          "id": 0,
          "parentId": 0,
          "name": "",
          "level": 0,
          "icon": "",
          "description": "",
          "sort": 0,
          "status": 0,
          "createTime": "",
          "updateTime": "",
          "deleted": 0,
          "children": [
            {}
          ]
        }
      ]
    }
  ]
}
```


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|id||path|true|integer(int64)||
|category|Category|body|true|Category|Category|
|&emsp;&emsp;id|||false|integer(int64)||
|&emsp;&emsp;parentId|||false|integer(int64)||
|&emsp;&emsp;name|||false|string||
|&emsp;&emsp;level|||false|integer(int32)||
|&emsp;&emsp;icon|||false|string||
|&emsp;&emsp;description|||false|string||
|&emsp;&emsp;sort|||false|integer(int32)||
|&emsp;&emsp;status|||false|integer(int32)||
|&emsp;&emsp;createTime|||false|string(date-time)||
|&emsp;&emsp;updateTime|||false|string(date-time)||
|&emsp;&emsp;deleted|||false|integer(int32)||
|&emsp;&emsp;children|||false|array|Category|


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|ResultObject|
|400|Bad Request|ResultObject|
|500|Internal Server Error|ResultObject|


**响应状态码-200**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


**响应状态码-400**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


**响应状态码-500**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


## 删除分类


**接口地址**:`/api/api/v1/admin/categories/{id}`


**请求方式**:`DELETE`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:<p>根据ID删除商品分类</p>



**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|id||path|true|integer(int64)||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|ResultObject|
|400|Bad Request|ResultObject|
|500|Internal Server Error|ResultObject|


**响应状态码-200**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


**响应状态码-400**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


**响应状态码-500**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


## 获取分类树形结构


**接口地址**:`/api/api/v1/admin/categories/tree`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:<p>获取所有分类的树形结构</p>



**请求参数**:


暂无


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|ResultListCategory|
|400|Bad Request|ResultObject|
|500|Internal Server Error|ResultObject|


**响应状态码-200**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||array|Category|
|&emsp;&emsp;id||integer(int64)||
|&emsp;&emsp;parentId||integer(int64)||
|&emsp;&emsp;name||string||
|&emsp;&emsp;level||integer(int32)||
|&emsp;&emsp;icon||string||
|&emsp;&emsp;description||string||
|&emsp;&emsp;sort||integer(int32)||
|&emsp;&emsp;status||integer(int32)||
|&emsp;&emsp;createTime||string(date-time)||
|&emsp;&emsp;updateTime||string(date-time)||
|&emsp;&emsp;deleted||integer(int32)||
|&emsp;&emsp;children||array|Category|


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": [
		{
			"id": 0,
			"parentId": 0,
			"name": "",
			"level": 0,
			"icon": "",
			"description": "",
			"sort": 0,
			"status": 0,
			"createTime": "",
			"updateTime": "",
			"deleted": 0,
			"children": [
				{
					"id": 0,
					"parentId": 0,
					"name": "",
					"level": 0,
					"icon": "",
					"description": "",
					"sort": 0,
					"status": 0,
					"createTime": "",
					"updateTime": "",
					"deleted": 0,
					"children": [
						{}
					]
				}
			]
		}
	]
}
```


**响应状态码-400**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


**响应状态码-500**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


# 管理端-商品管理


## 分页查询商品列表


**接口地址**:`/api/v1/admin/products`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:<p>分页查询商品列表，支持按名称、分类、状态搜索</p>



**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|pageNum||query|false|integer(int32)||
|pageSize||query|false|integer(int32)||
|name||query|false|string||
|categoryId||query|false|integer(int64)||
|status||query|false|integer(int32)||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|ResultPageSpu|
|400|Bad Request|ResultObject|
|500|Internal Server Error|ResultObject|


**响应状态码-200**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||PageSpu|PageSpu|
|&emsp;&emsp;records||array|Spu|
|&emsp;&emsp;&emsp;&emsp;id||integer(int64)||
|&emsp;&emsp;&emsp;&emsp;name||string||
|&emsp;&emsp;&emsp;&emsp;subTitle||string||
|&emsp;&emsp;&emsp;&emsp;categoryId||integer(int64)||
|&emsp;&emsp;&emsp;&emsp;description||string||
|&emsp;&emsp;&emsp;&emsp;detailHtml||string||
|&emsp;&emsp;&emsp;&emsp;status||integer(int32)||
|&emsp;&emsp;&emsp;&emsp;createTime||string(date-time)||
|&emsp;&emsp;&emsp;&emsp;updateTime||string(date-time)||
|&emsp;&emsp;&emsp;&emsp;createBy||integer(int64)||
|&emsp;&emsp;&emsp;&emsp;updateBy||integer(int64)||
|&emsp;&emsp;&emsp;&emsp;deleted||integer(int32)||
|&emsp;&emsp;total||integer(int64)||
|&emsp;&emsp;size||integer(int64)||
|&emsp;&emsp;current||integer(int64)||
|&emsp;&emsp;orders||array|OrderItem|
|&emsp;&emsp;&emsp;&emsp;column||string||
|&emsp;&emsp;&emsp;&emsp;asc||boolean||
|&emsp;&emsp;optimizeCountSql||boolean||
|&emsp;&emsp;searchCount||boolean||
|&emsp;&emsp;optimizeJoinOfCountSql||boolean||
|&emsp;&emsp;maxLimit||integer(int64)||
|&emsp;&emsp;countId||string||
|&emsp;&emsp;pages||integer(int64)||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {
		"records": [
			{
				"id": 0,
				"name": "",
				"subTitle": "",
				"categoryId": 0,
				"description": "",
				"detailHtml": "",
				"status": 0,
				"createTime": "",
				"updateTime": "",
				"createBy": 0,
				"updateBy": 0,
				"deleted": 0
			}
		],
		"total": 0,
		"size": 0,
		"current": 0,
		"orders": [
			{
				"column": "",
				"asc": true
			}
		],
		"optimizeCountSql": true,
		"searchCount": true,
		"optimizeJoinOfCountSql": true,
		"maxLimit": 0,
		"countId": "",
		"pages": 0
	}
}
```


**响应状态码-400**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


**响应状态码-500**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


## 添加商品


**接口地址**:`/api/v1/admin/products`


**请求方式**:`POST`


**请求数据类型**:`application/x-www-form-urlencoded,application/json`


**响应数据类型**:`*/*`


**接口描述**:<p>添加新的商品</p>



**请求示例**:


```javascript
{
  "id": 0,
  "name": "",
  "subTitle": "",
  "categoryId": 0,
  "description": "",
  "detailHtml": "",
  "status": 0,
  "createTime": "",
  "updateTime": "",
  "createBy": 0,
  "updateBy": 0,
  "deleted": 0
}
```


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|spu|Spu|body|true|Spu|Spu|
|&emsp;&emsp;id|||false|integer(int64)||
|&emsp;&emsp;name|||false|string||
|&emsp;&emsp;subTitle|||false|string||
|&emsp;&emsp;categoryId|||false|integer(int64)||
|&emsp;&emsp;description|||false|string||
|&emsp;&emsp;detailHtml|||false|string||
|&emsp;&emsp;status|||false|integer(int32)||
|&emsp;&emsp;createTime|||false|string(date-time)||
|&emsp;&emsp;updateTime|||false|string(date-time)||
|&emsp;&emsp;createBy|||false|integer(int64)||
|&emsp;&emsp;updateBy|||false|integer(int64)||
|&emsp;&emsp;deleted|||false|integer(int32)||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|ResultObject|
|400|Bad Request|ResultObject|
|500|Internal Server Error|ResultObject|


**响应状态码-200**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


**响应状态码-400**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


**响应状态码-500**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


## 获取商品详情


**接口地址**:`/api/v1/admin/products/{id}`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:<p>根据ID获取商品详情</p>



**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|id||path|true|integer(int64)||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|ResultSpu|
|400|Bad Request|ResultObject|
|500|Internal Server Error|ResultObject|


**响应状态码-200**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||Spu|Spu|
|&emsp;&emsp;id||integer(int64)||
|&emsp;&emsp;name||string||
|&emsp;&emsp;subTitle||string||
|&emsp;&emsp;categoryId||integer(int64)||
|&emsp;&emsp;description||string||
|&emsp;&emsp;detailHtml||string||
|&emsp;&emsp;status||integer(int32)||
|&emsp;&emsp;createTime||string(date-time)||
|&emsp;&emsp;updateTime||string(date-time)||
|&emsp;&emsp;createBy||integer(int64)||
|&emsp;&emsp;updateBy||integer(int64)||
|&emsp;&emsp;deleted||integer(int32)||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {
		"id": 0,
		"name": "",
		"subTitle": "",
		"categoryId": 0,
		"description": "",
		"detailHtml": "",
		"status": 0,
		"createTime": "",
		"updateTime": "",
		"createBy": 0,
		"updateBy": 0,
		"deleted": 0
	}
}
```


**响应状态码-400**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


**响应状态码-500**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


## 更新商品


**接口地址**:`/api/v1/admin/products/{id}`


**请求方式**:`PUT`


**请求数据类型**:`application/x-www-form-urlencoded,application/json`


**响应数据类型**:`*/*`


**接口描述**:<p>根据ID更新商品</p>



**请求示例**:


```javascript
{
  "id": 0,
  "name": "",
  "subTitle": "",
  "categoryId": 0,
  "description": "",
  "detailHtml": "",
  "status": 0,
  "createTime": "",
  "updateTime": "",
  "createBy": 0,
  "updateBy": 0,
  "deleted": 0
}
```


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|id||path|true|integer(int64)||
|spu|Spu|body|true|Spu|Spu|
|&emsp;&emsp;id|||false|integer(int64)||
|&emsp;&emsp;name|||false|string||
|&emsp;&emsp;subTitle|||false|string||
|&emsp;&emsp;categoryId|||false|integer(int64)||
|&emsp;&emsp;description|||false|string||
|&emsp;&emsp;detailHtml|||false|string||
|&emsp;&emsp;status|||false|integer(int32)||
|&emsp;&emsp;createTime|||false|string(date-time)||
|&emsp;&emsp;updateTime|||false|string(date-time)||
|&emsp;&emsp;createBy|||false|integer(int64)||
|&emsp;&emsp;updateBy|||false|integer(int64)||
|&emsp;&emsp;deleted|||false|integer(int32)||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|ResultObject|
|400|Bad Request|ResultObject|
|500|Internal Server Error|ResultObject|


**响应状态码-200**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


**响应状态码-400**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


**响应状态码-500**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


## 删除商品


**接口地址**:`/api/v1/admin/products/{id}`


**请求方式**:`DELETE`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:<p>根据ID删除商品</p>



**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|id||path|true|integer(int64)||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|ResultObject|
|400|Bad Request|ResultObject|
|500|Internal Server Error|ResultObject|


**响应状态码-200**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


**响应状态码-400**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


**响应状态码-500**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


## 更新商品状态


**接口地址**:`/api/v1/admin/products/{id}/status`


**请求方式**:`PUT`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:<p>更新商品上下架状态</p>



**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|id||path|true|integer(int64)||
|status||query|true|integer(int32)||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|ResultObject|
|400|Bad Request|ResultObject|
|500|Internal Server Error|ResultObject|


**响应状态码-200**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


**响应状态码-400**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


**响应状态码-500**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


# 后台角色管理


## 创建角色


**接口地址**:`/api/api/v1/admin/role`


**请求方式**:`POST`


**请求数据类型**:`application/x-www-form-urlencoded,application/json`


**响应数据类型**:`*/*`


**接口描述**:


**请求示例**:


```javascript
{
  "id": 0,
  "name": "",
  "code": "",
  "description": "",
  "status": 0,
  "createTime": "",
  "updateTime": "",
  "createBy": 0,
  "updateBy": 0,
  "deleted": 0
}
```


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|role|Role|body|true|Role|Role|
|&emsp;&emsp;id|||false|integer(int64)||
|&emsp;&emsp;name|||false|string||
|&emsp;&emsp;code|||false|string||
|&emsp;&emsp;description|||false|string||
|&emsp;&emsp;status|||false|integer(int32)||
|&emsp;&emsp;createTime|||false|string(date-time)||
|&emsp;&emsp;updateTime|||false|string(date-time)||
|&emsp;&emsp;createBy|||false|integer(int64)||
|&emsp;&emsp;updateBy|||false|integer(int64)||
|&emsp;&emsp;deleted|||false|integer(int32)||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|ResultObject|
|400|Bad Request|ResultObject|
|500|Internal Server Error|ResultObject|


**响应状态码-200**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


**响应状态码-400**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


**响应状态码-500**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


## 获取角色详情


**接口地址**:`/api/api/v1/admin/role/{roleId}`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|roleId||path|true|integer(int64)||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|ResultObject|
|400|Bad Request|ResultObject|
|500|Internal Server Error|ResultObject|


**响应状态码-200**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


**响应状态码-400**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


**响应状态码-500**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


## 更新角色


**接口地址**:`/api/api/v1/admin/role/{roleId}`


**请求方式**:`PUT`


**请求数据类型**:`application/x-www-form-urlencoded,application/json`


**响应数据类型**:`*/*`


**接口描述**:


**请求示例**:


```javascript
{
  "id": 0,
  "name": "",
  "code": "",
  "description": "",
  "status": 0,
  "createTime": "",
  "updateTime": "",
  "createBy": 0,
  "updateBy": 0,
  "deleted": 0
}
```


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|roleId||path|true|integer(int64)||
|role|Role|body|true|Role|Role|
|&emsp;&emsp;id|||false|integer(int64)||
|&emsp;&emsp;name|||false|string||
|&emsp;&emsp;code|||false|string||
|&emsp;&emsp;description|||false|string||
|&emsp;&emsp;status|||false|integer(int32)||
|&emsp;&emsp;createTime|||false|string(date-time)||
|&emsp;&emsp;updateTime|||false|string(date-time)||
|&emsp;&emsp;createBy|||false|integer(int64)||
|&emsp;&emsp;updateBy|||false|integer(int64)||
|&emsp;&emsp;deleted|||false|integer(int32)||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|ResultObject|
|400|Bad Request|ResultObject|
|500|Internal Server Error|ResultObject|


**响应状态码-200**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


**响应状态码-400**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


**响应状态码-500**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


## 删除角色


**接口地址**:`/api/api/v1/admin/role/{roleId}`


**请求方式**:`DELETE`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|roleId||path|true|integer(int64)||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|ResultObject|
|400|Bad Request|ResultObject|
|500|Internal Server Error|ResultObject|


**响应状态码-200**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


**响应状态码-400**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


**响应状态码-500**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


## 获取角色的权限列表


**接口地址**:`/api/api/v1/admin/role/{roleId}/permissions`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|roleId||path|true|integer(int64)||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|ResultObject|
|400|Bad Request|ResultObject|
|500|Internal Server Error|ResultObject|


**响应状态码-200**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


**响应状态码-400**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


**响应状态码-500**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


## 为角色分配权限


**接口地址**:`/api/api/v1/admin/role/{roleId}/permissions`


**请求方式**:`POST`


**请求数据类型**:`application/x-www-form-urlencoded,application/json`


**响应数据类型**:`*/*`


**接口描述**:


**请求示例**:


```javascript
[]
```


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|roleId||path|true|integer(int64)||
|integers|integer|body|true|array||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|ResultObject|
|400|Bad Request|ResultObject|
|500|Internal Server Error|ResultObject|


**响应状态码-200**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


**响应状态码-400**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


**响应状态码-500**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


## 角色分页列表


**接口地址**:`/api/api/v1/admin/role/list`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|pageNum||query|false|integer(int32)||
|pageSize||query|false|integer(int32)||
|name||query|false|string||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|ResultObject|
|400|Bad Request|ResultObject|
|500|Internal Server Error|ResultObject|


**响应状态码-200**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


**响应状态码-400**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


**响应状态码-500**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


## 获取用户的角色列表


**接口地址**:`/api/api/v1/admin/role/user/{userId}`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|userId||path|true|integer(int64)||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|ResultObject|
|400|Bad Request|ResultObject|
|500|Internal Server Error|ResultObject|


**响应状态码-200**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


**响应状态码-400**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


**响应状态码-500**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


## 为用户分配角色


**接口地址**:`/api/api/v1/admin/role/user/{userId}`


**请求方式**:`POST`


**请求数据类型**:`application/x-www-form-urlencoded,application/json`


**响应数据类型**:`*/*`


**接口描述**:


**请求示例**:


```javascript
[]
```


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|userId||path|true|integer(int64)||
|integers|integer|body|true|array||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|ResultObject|
|400|Bad Request|ResultObject|
|500|Internal Server Error|ResultObject|


**响应状态码-200**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


**响应状态码-400**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


**响应状态码-500**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


# 后台评论管理


## 获取评论列表


**接口地址**:`/api/v1/admin/comments`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:<p>分页获取评论列表，支持帖子ID筛选和状态筛选</p>



**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|pageNum||query|false|integer(int32)||
|pageSize||query|false|integer(int32)||
|postId||query|false|integer(int64)||
|status||query|false|integer(int32)||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|ResultIPageComment|
|400|Bad Request|ResultObject|
|500|Internal Server Error|ResultObject|


**响应状态码-200**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||IPageComment|IPageComment|
|&emsp;&emsp;size||integer(int64)||
|&emsp;&emsp;total||integer(int64)||
|&emsp;&emsp;current||integer(int64)||
|&emsp;&emsp;records||array|Comment|
|&emsp;&emsp;&emsp;&emsp;id||integer(int64)||
|&emsp;&emsp;&emsp;&emsp;postId||integer(int64)||
|&emsp;&emsp;&emsp;&emsp;userId||integer(int64)||
|&emsp;&emsp;&emsp;&emsp;content||string||
|&emsp;&emsp;&emsp;&emsp;parentId||integer(int64)||
|&emsp;&emsp;&emsp;&emsp;replyUserId||integer(int64)||
|&emsp;&emsp;&emsp;&emsp;status||integer(int32)||
|&emsp;&emsp;&emsp;&emsp;createTime||string(date-time)||
|&emsp;&emsp;&emsp;&emsp;updateTime||string(date-time)||
|&emsp;&emsp;&emsp;&emsp;createBy||integer(int64)||
|&emsp;&emsp;&emsp;&emsp;updateBy||integer(int64)||
|&emsp;&emsp;&emsp;&emsp;deleted||integer(int32)||
|&emsp;&emsp;pages||integer(int64)||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {
		"size": 0,
		"total": 0,
		"current": 0,
		"records": [
			{
				"id": 0,
				"postId": 0,
				"userId": 0,
				"content": "",
				"parentId": 0,
				"replyUserId": 0,
				"status": 0,
				"createTime": "",
				"updateTime": "",
				"createBy": 0,
				"updateBy": 0,
				"deleted": 0
			}
		],
		"pages": 0
	}
}
```


**响应状态码-400**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


**响应状态码-500**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


## 获取评论详情


**接口地址**:`/api/v1/admin/comments/{commentId}`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:<p>获取指定评论的详细信息</p>



**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|commentId||path|true|integer(int64)||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|ResultComment|
|400|Bad Request|ResultObject|
|500|Internal Server Error|ResultObject|


**响应状态码-200**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||Comment|Comment|
|&emsp;&emsp;id||integer(int64)||
|&emsp;&emsp;postId||integer(int64)||
|&emsp;&emsp;userId||integer(int64)||
|&emsp;&emsp;content||string||
|&emsp;&emsp;parentId||integer(int64)||
|&emsp;&emsp;replyUserId||integer(int64)||
|&emsp;&emsp;status||integer(int32)||
|&emsp;&emsp;createTime||string(date-time)||
|&emsp;&emsp;updateTime||string(date-time)||
|&emsp;&emsp;createBy||integer(int64)||
|&emsp;&emsp;updateBy||integer(int64)||
|&emsp;&emsp;deleted||integer(int32)||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {
		"id": 0,
		"postId": 0,
		"userId": 0,
		"content": "",
		"parentId": 0,
		"replyUserId": 0,
		"status": 0,
		"createTime": "",
		"updateTime": "",
		"createBy": 0,
		"updateBy": 0,
		"deleted": 0
	}
}
```


**响应状态码-400**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


**响应状态码-500**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


## 删除评论


**接口地址**:`/api/v1/admin/comments/{commentId}`


**请求方式**:`DELETE`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:<p>删除指定评论</p>



**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|commentId||path|true|integer(int64)||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|ResultObject|
|400|Bad Request|ResultObject|
|500|Internal Server Error|ResultObject|


**响应状态码-200**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


**响应状态码-400**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


**响应状态码-500**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


## 更新评论状态


**接口地址**:`/api/v1/admin/comments/{commentId}/status`


**请求方式**:`PUT`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:<p>更新指定评论的状态</p>



**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|commentId||path|true|integer(int64)||
|status||query|true|integer(int32)||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|ResultObject|
|400|Bad Request|ResultObject|
|500|Internal Server Error|ResultObject|


**响应状态码-200**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


**响应状态码-400**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


**响应状态码-500**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


# 后台权限管理


## 创建权限


**接口地址**:`/api/api/v1/admin/permission`


**请求方式**:`POST`


**请求数据类型**:`application/x-www-form-urlencoded,application/json`


**响应数据类型**:`*/*`


**接口描述**:


**请求示例**:


```javascript
{
  "id": 0,
  "name": "",
  "code": "",
  "description": "",
  "status": 0,
  "createTime": "",
  "updateTime": "",
  "createBy": 0,
  "updateBy": 0,
  "deleted": 0
}
```


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|permission|Permission|body|true|Permission|Permission|
|&emsp;&emsp;id|||false|integer(int64)||
|&emsp;&emsp;name|||false|string||
|&emsp;&emsp;code|||false|string||
|&emsp;&emsp;description|||false|string||
|&emsp;&emsp;status|||false|integer(int32)||
|&emsp;&emsp;createTime|||false|string(date-time)||
|&emsp;&emsp;updateTime|||false|string(date-time)||
|&emsp;&emsp;createBy|||false|integer(int64)||
|&emsp;&emsp;updateBy|||false|integer(int64)||
|&emsp;&emsp;deleted|||false|integer(int32)||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|ResultObject|
|400|Bad Request|ResultObject|
|500|Internal Server Error|ResultObject|


**响应状态码-200**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


**响应状态码-400**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


**响应状态码-500**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


## 获取权限详情


**接口地址**:`/api/api/v1/admin/permission/{permissionId}`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|permissionId||path|true|integer(int64)||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|ResultObject|
|400|Bad Request|ResultObject|
|500|Internal Server Error|ResultObject|


**响应状态码-200**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


**响应状态码-400**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


**响应状态码-500**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


## 更新权限


**接口地址**:`/api/api/v1/admin/permission/{permissionId}`


**请求方式**:`PUT`


**请求数据类型**:`application/x-www-form-urlencoded,application/json`


**响应数据类型**:`*/*`


**接口描述**:


**请求示例**:


```javascript
{
  "id": 0,
  "name": "",
  "code": "",
  "description": "",
  "status": 0,
  "createTime": "",
  "updateTime": "",
  "createBy": 0,
  "updateBy": 0,
  "deleted": 0
}
```


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|permissionId||path|true|integer(int64)||
|permission|Permission|body|true|Permission|Permission|
|&emsp;&emsp;id|||false|integer(int64)||
|&emsp;&emsp;name|||false|string||
|&emsp;&emsp;code|||false|string||
|&emsp;&emsp;description|||false|string||
|&emsp;&emsp;status|||false|integer(int32)||
|&emsp;&emsp;createTime|||false|string(date-time)||
|&emsp;&emsp;updateTime|||false|string(date-time)||
|&emsp;&emsp;createBy|||false|integer(int64)||
|&emsp;&emsp;updateBy|||false|integer(int64)||
|&emsp;&emsp;deleted|||false|integer(int32)||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|ResultObject|
|400|Bad Request|ResultObject|
|500|Internal Server Error|ResultObject|


**响应状态码-200**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


**响应状态码-400**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


**响应状态码-500**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


## 删除权限


**接口地址**:`/api/api/v1/admin/permission/{permissionId}`


**请求方式**:`DELETE`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|permissionId||path|true|integer(int64)||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|ResultObject|
|400|Bad Request|ResultObject|
|500|Internal Server Error|ResultObject|


**响应状态码-200**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


**响应状态码-400**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


**响应状态码-500**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


## 权限分页列表


**接口地址**:`/api/api/v1/admin/permission/list`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|pageNum||query|false|integer(int32)||
|pageSize||query|false|integer(int32)||
|name||query|false|string||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|ResultObject|
|400|Bad Request|ResultObject|
|500|Internal Server Error|ResultObject|


**响应状态码-200**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


**响应状态码-400**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


**响应状态码-500**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


## 根据角色ID获取权限列表


**接口地址**:`/api/api/v1/admin/permission/role/{roleId}`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|roleId||path|true|integer(int64)||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|ResultObject|
|400|Bad Request|ResultObject|
|500|Internal Server Error|ResultObject|


**响应状态码-200**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


**响应状态码-400**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


**响应状态码-500**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


## 根据用户ID获取权限列表


**接口地址**:`/api/api/v1/admin/permission/user/{userId}`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|userId||path|true|integer(int64)||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|ResultObject|
|400|Bad Request|ResultObject|
|500|Internal Server Error|ResultObject|


**响应状态码-200**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


**响应状态码-400**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


**响应状态码-500**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


# 后台帖子管理


## 获取帖子列表


**接口地址**:`/api/api/admin/posts`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:<p>分页获取帖子列表，支持关键词搜索和状态筛选</p>



**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|pageNum||query|false|integer(int32)||
|pageSize||query|false|integer(int32)||
|keyword||query|false|string||
|status||query|false|integer(int32)||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|ResultIPagePost|
|400|Bad Request|ResultObject|
|500|Internal Server Error|ResultObject|


**响应状态码-200**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||IPagePost|IPagePost|
|&emsp;&emsp;size||integer(int64)||
|&emsp;&emsp;total||integer(int64)||
|&emsp;&emsp;current||integer(int64)||
|&emsp;&emsp;records||array|Post|
|&emsp;&emsp;&emsp;&emsp;id||integer(int64)||
|&emsp;&emsp;&emsp;&emsp;userId||integer(int64)||
|&emsp;&emsp;&emsp;&emsp;title||string||
|&emsp;&emsp;&emsp;&emsp;content||string||
|&emsp;&emsp;&emsp;&emsp;images||string||
|&emsp;&emsp;&emsp;&emsp;viewCount||integer(int32)||
|&emsp;&emsp;&emsp;&emsp;likeCount||integer(int32)||
|&emsp;&emsp;&emsp;&emsp;commentCount||integer(int32)||
|&emsp;&emsp;&emsp;&emsp;status||integer(int32)||
|&emsp;&emsp;&emsp;&emsp;createTime||string(date-time)||
|&emsp;&emsp;&emsp;&emsp;updateTime||string(date-time)||
|&emsp;&emsp;&emsp;&emsp;createBy||integer(int64)||
|&emsp;&emsp;&emsp;&emsp;updateBy||integer(int64)||
|&emsp;&emsp;&emsp;&emsp;deleted||integer(int32)||
|&emsp;&emsp;pages||integer(int64)||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {
		"size": 0,
		"total": 0,
		"current": 0,
		"records": [
			{
				"id": 0,
				"userId": 0,
				"title": "",
				"content": "",
				"images": "",
				"viewCount": 0,
				"likeCount": 0,
				"commentCount": 0,
				"status": 0,
				"createTime": "",
				"updateTime": "",
				"createBy": 0,
				"updateBy": 0,
				"deleted": 0
			}
		],
		"pages": 0
	}
}
```


**响应状态码-400**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


**响应状态码-500**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


## 获取帖子详情


**接口地址**:`/api/api/admin/posts/{postId}`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:<p>获取指定帖子的详细信息</p>



**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|postId||path|true|integer(int64)||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|ResultPost|
|400|Bad Request|ResultObject|
|500|Internal Server Error|ResultObject|


**响应状态码-200**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||Post|Post|
|&emsp;&emsp;id||integer(int64)||
|&emsp;&emsp;userId||integer(int64)||
|&emsp;&emsp;title||string||
|&emsp;&emsp;content||string||
|&emsp;&emsp;images||string||
|&emsp;&emsp;viewCount||integer(int32)||
|&emsp;&emsp;likeCount||integer(int32)||
|&emsp;&emsp;commentCount||integer(int32)||
|&emsp;&emsp;status||integer(int32)||
|&emsp;&emsp;createTime||string(date-time)||
|&emsp;&emsp;updateTime||string(date-time)||
|&emsp;&emsp;createBy||integer(int64)||
|&emsp;&emsp;updateBy||integer(int64)||
|&emsp;&emsp;deleted||integer(int32)||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {
		"id": 0,
		"userId": 0,
		"title": "",
		"content": "",
		"images": "",
		"viewCount": 0,
		"likeCount": 0,
		"commentCount": 0,
		"status": 0,
		"createTime": "",
		"updateTime": "",
		"createBy": 0,
		"updateBy": 0,
		"deleted": 0
	}
}
```


**响应状态码-400**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


**响应状态码-500**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


## 删除帖子


**接口地址**:`/api/api/admin/posts/{postId}`


**请求方式**:`DELETE`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:<p>删除指定帖子</p>



**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|postId||path|true|integer(int64)||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|ResultObject|
|400|Bad Request|ResultObject|
|500|Internal Server Error|ResultObject|


**响应状态码-200**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


**响应状态码-400**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


**响应状态码-500**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


## 更新帖子状态


**接口地址**:`/api/api/admin/posts/{postId}/status`


**请求方式**:`PUT`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:<p>更新指定帖子的状态</p>



**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|postId||path|true|integer(int64)||
|status||query|true|integer(int32)||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|ResultObject|
|400|Bad Request|ResultObject|
|500|Internal Server Error|ResultObject|


**响应状态码-200**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


**响应状态码-400**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


**响应状态码-500**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


# 后台用户管理


## 获取用户详情


**接口地址**:`/api/v1/admin/user/{userId}`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|userId||path|true|integer(int64)||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|ResultObject|
|400|Bad Request|ResultObject|
|500|Internal Server Error|ResultObject|


**响应状态码-200**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


**响应状态码-400**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


**响应状态码-500**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


## 编辑用户信息


**接口地址**:`/api/v1/admin/user/{userId}`


**请求方式**:`PUT`


**请求数据类型**:`application/x-www-form-urlencoded,application/json`


**响应数据类型**:`*/*`


**接口描述**:


**请求示例**:


```javascript
{
  "id": 0,
  "username": "",
  "password": "",
  "nickname": "",
  "avatar": "",
  "phone": "",
  "email": "",
  "gender": 0,
  "birthday": "",
  "status": 0,
  "createTime": "",
  "updateTime": "",
  "createBy": 0,
  "updateBy": 0,
  "deleted": 0
}
```


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|userId||path|true|integer(int64)||
|user|User|body|true|User|User|
|&emsp;&emsp;id|||false|integer(int64)||
|&emsp;&emsp;username|||false|string||
|&emsp;&emsp;password|||false|string||
|&emsp;&emsp;nickname|||false|string||
|&emsp;&emsp;avatar|||false|string||
|&emsp;&emsp;phone|||false|string||
|&emsp;&emsp;email|||false|string||
|&emsp;&emsp;gender|||false|integer(int32)||
|&emsp;&emsp;birthday|||false|string(date-time)||
|&emsp;&emsp;status|||false|integer(int32)||
|&emsp;&emsp;createTime|||false|string(date-time)||
|&emsp;&emsp;updateTime|||false|string(date-time)||
|&emsp;&emsp;createBy|||false|integer(int64)||
|&emsp;&emsp;updateBy|||false|integer(int64)||
|&emsp;&emsp;deleted|||false|integer(int32)||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|ResultObject|
|400|Bad Request|ResultObject|
|500|Internal Server Error|ResultObject|


**响应状态码-200**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


**响应状态码-400**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


**响应状态码-500**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


## 重置用户密码


**接口地址**:`/api/v1/admin/user/{userId}/reset-password`


**请求方式**:`PUT`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|userId||path|true|integer(int64)||
|newPassword||query|true|string||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|ResultObject|
|400|Bad Request|ResultObject|
|500|Internal Server Error|ResultObject|


**响应状态码-200**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


**响应状态码-400**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


**响应状态码-500**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


## 用户分页列表


**接口地址**:`/api/v1/admin/user/list`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|pageNum||query|false|integer(int32)||
|pageSize||query|false|integer(int32)||
|username||query|false|string||
|status||query|false|integer(int32)||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|ResultObject|
|400|Bad Request|ResultObject|
|500|Internal Server Error|ResultObject|


**响应状态码-200**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


**响应状态码-400**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


**响应状态码-500**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


## 用户登录


**接口地址**:`/api/v1/admin/user/login`


**请求方式**:`POST`


**请求数据类型**:`application/x-www-form-urlencoded,application/json`


**响应数据类型**:`*/*`


**接口描述**:<p>用户登录接口，需提供用户名和密码</p>



**请求示例**:


```javascript
{
  "username": "",
  "password": ""
}
```


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|userLoginRequestDTO|UserLoginRequestDTO|body|true|UserLoginRequestDTO|UserLoginRequestDTO|
|&emsp;&emsp;username|||false|string||
|&emsp;&emsp;password|||false|string||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|登录成功，返回token|ResultObject|
|400|登录失败，用户名或密码错误|ResultObject|
|500|Internal Server Error|ResultObject|


**响应状态码-200**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


**响应状态码-400**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


**响应状态码-500**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


## 修改用户状态


**接口地址**:`/api/v1/admin/user/status`


**请求方式**:`PUT`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|userId||query|true|integer(int64)||
|status||query|true|integer(int32)||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|ResultObject|
|400|Bad Request|ResultObject|
|500|Internal Server Error|ResultObject|


**响应状态码-200**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


**响应状态码-400**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


**响应状态码-500**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


# 评论管理


## 删除评论


**接口地址**:`/api/api/v1/comments/{commentId}`


**请求方式**:`DELETE`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:<p>删除指定评论</p>



**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|commentId||path|true|integer(int64)||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|ResultObject|
|400|Bad Request|ResultObject|
|500|Internal Server Error|ResultObject|


**响应状态码-200**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


**响应状态码-400**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


**响应状态码-500**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


## 获取评论回复


**接口地址**:`/api/api/v1/comments/{commentId}/replies`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:<p>获取评论的回复列表</p>



**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|commentId||path|true|integer(int64)||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|ResultListComment|
|400|Bad Request|ResultObject|
|500|Internal Server Error|ResultObject|


**响应状态码-200**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||array|Comment|
|&emsp;&emsp;id||integer(int64)||
|&emsp;&emsp;postId||integer(int64)||
|&emsp;&emsp;userId||integer(int64)||
|&emsp;&emsp;content||string||
|&emsp;&emsp;parentId||integer(int64)||
|&emsp;&emsp;replyUserId||integer(int64)||
|&emsp;&emsp;status||integer(int32)||
|&emsp;&emsp;createTime||string(date-time)||
|&emsp;&emsp;updateTime||string(date-time)||
|&emsp;&emsp;createBy||integer(int64)||
|&emsp;&emsp;updateBy||integer(int64)||
|&emsp;&emsp;deleted||integer(int32)||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": [
		{
			"id": 0,
			"postId": 0,
			"userId": 0,
			"content": "",
			"parentId": 0,
			"replyUserId": 0,
			"status": 0,
			"createTime": "",
			"updateTime": "",
			"createBy": 0,
			"updateBy": 0,
			"deleted": 0
		}
	]
}
```


**响应状态码-400**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


**响应状态码-500**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


## 回复评论


**接口地址**:`/api/api/v1/comments/{commentId}/replies`


**请求方式**:`POST`


**请求数据类型**:`application/x-www-form-urlencoded,application/json`


**响应数据类型**:`*/*`


**接口描述**:<p>回复指定评论</p>



**请求示例**:


```javascript
{
  "postId": 0,
  "content": "",
  "parentId": 0,
  "replyUserId": 0
}
```


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|commentId||path|true|integer(int64)||
|commentRequestDTO|CommentRequestDTO|body|true|CommentRequestDTO|CommentRequestDTO|
|&emsp;&emsp;postId|||false|integer(int64)||
|&emsp;&emsp;content|||false|string||
|&emsp;&emsp;parentId|||false|integer(int64)||
|&emsp;&emsp;replyUserId|||false|integer(int64)||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|ResultObject|
|400|Bad Request|ResultObject|
|500|Internal Server Error|ResultObject|


**响应状态码-200**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


**响应状态码-400**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


**响应状态码-500**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


## 获取帖子评论


**接口地址**:`/api/api/v1/posts/{postId}/comments`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:<p>获取帖子的评论列表</p>



**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|postId||path|true|integer(int64)||
|pageNum||query|false|integer(int32)||
|pageSize||query|false|integer(int32)||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|ResultIPageComment|
|400|Bad Request|ResultObject|
|500|Internal Server Error|ResultObject|


**响应状态码-200**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||IPageComment|IPageComment|
|&emsp;&emsp;size||integer(int64)||
|&emsp;&emsp;total||integer(int64)||
|&emsp;&emsp;current||integer(int64)||
|&emsp;&emsp;records||array|Comment|
|&emsp;&emsp;&emsp;&emsp;id||integer(int64)||
|&emsp;&emsp;&emsp;&emsp;postId||integer(int64)||
|&emsp;&emsp;&emsp;&emsp;userId||integer(int64)||
|&emsp;&emsp;&emsp;&emsp;content||string||
|&emsp;&emsp;&emsp;&emsp;parentId||integer(int64)||
|&emsp;&emsp;&emsp;&emsp;replyUserId||integer(int64)||
|&emsp;&emsp;&emsp;&emsp;status||integer(int32)||
|&emsp;&emsp;&emsp;&emsp;createTime||string(date-time)||
|&emsp;&emsp;&emsp;&emsp;updateTime||string(date-time)||
|&emsp;&emsp;&emsp;&emsp;createBy||integer(int64)||
|&emsp;&emsp;&emsp;&emsp;updateBy||integer(int64)||
|&emsp;&emsp;&emsp;&emsp;deleted||integer(int32)||
|&emsp;&emsp;pages||integer(int64)||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {
		"size": 0,
		"total": 0,
		"current": 0,
		"records": [
			{
				"id": 0,
				"postId": 0,
				"userId": 0,
				"content": "",
				"parentId": 0,
				"replyUserId": 0,
				"status": 0,
				"createTime": "",
				"updateTime": "",
				"createBy": 0,
				"updateBy": 0,
				"deleted": 0
			}
		],
		"pages": 0
	}
}
```


**响应状态码-400**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


**响应状态码-500**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


## 添加评论


**接口地址**:`/api/api/v1/posts/{postId}/comments`


**请求方式**:`POST`


**请求数据类型**:`application/x-www-form-urlencoded,application/json`


**响应数据类型**:`*/*`


**接口描述**:<p>向帖子添加评论</p>



**请求示例**:


```javascript
{
  "postId": 0,
  "content": "",
  "parentId": 0,
  "replyUserId": 0
}
```


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|postId||path|true|integer(int64)||
|commentRequestDTO|CommentRequestDTO|body|true|CommentRequestDTO|CommentRequestDTO|
|&emsp;&emsp;postId|||false|integer(int64)||
|&emsp;&emsp;content|||false|string||
|&emsp;&emsp;parentId|||false|integer(int64)||
|&emsp;&emsp;replyUserId|||false|integer(int64)||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|ResultObject|
|400|Bad Request|ResultObject|
|500|Internal Server Error|ResultObject|


**响应状态码-200**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


**响应状态码-400**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


**响应状态码-500**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


# 商品


## 分页查询商品列表


**接口地址**:`/api/api/v1/products`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:<p>分页查询商品列表，支持按名称、分类搜索</p>



**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|pageNum||query|false|integer(int32)||
|pageSize||query|false|integer(int32)||
|name||query|false|string||
|categoryId||query|false|integer(int64)||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|ResultPageSpu|
|400|Bad Request|ResultObject|
|500|Internal Server Error|ResultObject|


**响应状态码-200**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||PageSpu|PageSpu|
|&emsp;&emsp;records||array|Spu|
|&emsp;&emsp;&emsp;&emsp;id||integer(int64)||
|&emsp;&emsp;&emsp;&emsp;name||string||
|&emsp;&emsp;&emsp;&emsp;subTitle||string||
|&emsp;&emsp;&emsp;&emsp;categoryId||integer(int64)||
|&emsp;&emsp;&emsp;&emsp;description||string||
|&emsp;&emsp;&emsp;&emsp;detailHtml||string||
|&emsp;&emsp;&emsp;&emsp;status||integer(int32)||
|&emsp;&emsp;&emsp;&emsp;createTime||string(date-time)||
|&emsp;&emsp;&emsp;&emsp;updateTime||string(date-time)||
|&emsp;&emsp;&emsp;&emsp;createBy||integer(int64)||
|&emsp;&emsp;&emsp;&emsp;updateBy||integer(int64)||
|&emsp;&emsp;&emsp;&emsp;deleted||integer(int32)||
|&emsp;&emsp;total||integer(int64)||
|&emsp;&emsp;size||integer(int64)||
|&emsp;&emsp;current||integer(int64)||
|&emsp;&emsp;orders||array|OrderItem|
|&emsp;&emsp;&emsp;&emsp;column||string||
|&emsp;&emsp;&emsp;&emsp;asc||boolean||
|&emsp;&emsp;optimizeCountSql||boolean||
|&emsp;&emsp;searchCount||boolean||
|&emsp;&emsp;optimizeJoinOfCountSql||boolean||
|&emsp;&emsp;maxLimit||integer(int64)||
|&emsp;&emsp;countId||string||
|&emsp;&emsp;pages||integer(int64)||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {
		"records": [
			{
				"id": 0,
				"name": "",
				"subTitle": "",
				"categoryId": 0,
				"description": "",
				"detailHtml": "",
				"status": 0,
				"createTime": "",
				"updateTime": "",
				"createBy": 0,
				"updateBy": 0,
				"deleted": 0
			}
		],
		"total": 0,
		"size": 0,
		"current": 0,
		"orders": [
			{
				"column": "",
				"asc": true
			}
		],
		"optimizeCountSql": true,
		"searchCount": true,
		"optimizeJoinOfCountSql": true,
		"maxLimit": 0,
		"countId": "",
		"pages": 0
	}
}
```


**响应状态码-400**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


**响应状态码-500**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


## 获取商品详情


**接口地址**:`/api/api/v1/products/{id}`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:<p>根据ID获取商品详情</p>



**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|id||path|true|integer(int64)||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|ResultSpu|
|400|Bad Request|ResultObject|
|500|Internal Server Error|ResultObject|


**响应状态码-200**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||Spu|Spu|
|&emsp;&emsp;id||integer(int64)||
|&emsp;&emsp;name||string||
|&emsp;&emsp;subTitle||string||
|&emsp;&emsp;categoryId||integer(int64)||
|&emsp;&emsp;description||string||
|&emsp;&emsp;detailHtml||string||
|&emsp;&emsp;status||integer(int32)||
|&emsp;&emsp;createTime||string(date-time)||
|&emsp;&emsp;updateTime||string(date-time)||
|&emsp;&emsp;createBy||integer(int64)||
|&emsp;&emsp;updateBy||integer(int64)||
|&emsp;&emsp;deleted||integer(int32)||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {
		"id": 0,
		"name": "",
		"subTitle": "",
		"categoryId": 0,
		"description": "",
		"detailHtml": "",
		"status": 0,
		"createTime": "",
		"updateTime": "",
		"createBy": 0,
		"updateBy": 0,
		"deleted": 0
	}
}
```


**响应状态码-400**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


**响应状态码-500**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


## 获取商品详情页


**接口地址**:`/api/api/v1/products/{id}/detail`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:<p>获取商品详情页信息，包含SKU列表、图片轮播等</p>



**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|id||path|true|integer(int64)||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|ResultProductDetailDTO|
|400|Bad Request|ResultObject|
|500|Internal Server Error|ResultObject|


**响应状态码-200**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||ProductDetailDTO|ProductDetailDTO|
|&emsp;&emsp;spu||Spu|Spu|
|&emsp;&emsp;&emsp;&emsp;id||integer(int64)||
|&emsp;&emsp;&emsp;&emsp;name||string||
|&emsp;&emsp;&emsp;&emsp;subTitle||string||
|&emsp;&emsp;&emsp;&emsp;categoryId||integer(int64)||
|&emsp;&emsp;&emsp;&emsp;description||string||
|&emsp;&emsp;&emsp;&emsp;detailHtml||string||
|&emsp;&emsp;&emsp;&emsp;status||integer(int32)||
|&emsp;&emsp;&emsp;&emsp;createTime||string(date-time)||
|&emsp;&emsp;&emsp;&emsp;updateTime||string(date-time)||
|&emsp;&emsp;&emsp;&emsp;createBy||integer(int64)||
|&emsp;&emsp;&emsp;&emsp;updateBy||integer(int64)||
|&emsp;&emsp;&emsp;&emsp;deleted||integer(int32)||
|&emsp;&emsp;category||Category|Category|
|&emsp;&emsp;&emsp;&emsp;id||integer(int64)||
|&emsp;&emsp;&emsp;&emsp;parentId||integer(int64)||
|&emsp;&emsp;&emsp;&emsp;name||string||
|&emsp;&emsp;&emsp;&emsp;level||integer(int32)||
|&emsp;&emsp;&emsp;&emsp;icon||string||
|&emsp;&emsp;&emsp;&emsp;description||string||
|&emsp;&emsp;&emsp;&emsp;sort||integer(int32)||
|&emsp;&emsp;&emsp;&emsp;status||integer(int32)||
|&emsp;&emsp;&emsp;&emsp;createTime||string(date-time)||
|&emsp;&emsp;&emsp;&emsp;updateTime||string(date-time)||
|&emsp;&emsp;&emsp;&emsp;deleted||integer(int32)||
|&emsp;&emsp;&emsp;&emsp;children||array|Category|
|&emsp;&emsp;skuList||array|Sku|
|&emsp;&emsp;&emsp;&emsp;id||integer(int64)||
|&emsp;&emsp;&emsp;&emsp;spuId||integer(int64)||
|&emsp;&emsp;&emsp;&emsp;skuCode||string||
|&emsp;&emsp;&emsp;&emsp;name||string||
|&emsp;&emsp;&emsp;&emsp;specs||string||
|&emsp;&emsp;&emsp;&emsp;price||number||
|&emsp;&emsp;&emsp;&emsp;originalPrice||number||
|&emsp;&emsp;&emsp;&emsp;stock||integer(int32)||
|&emsp;&emsp;&emsp;&emsp;lowStock||integer(int32)||
|&emsp;&emsp;&emsp;&emsp;pic||string||
|&emsp;&emsp;&emsp;&emsp;status||integer(int32)||
|&emsp;&emsp;&emsp;&emsp;sort||integer(int32)||
|&emsp;&emsp;&emsp;&emsp;createTime||string(date-time)||
|&emsp;&emsp;&emsp;&emsp;updateTime||string(date-time)||
|&emsp;&emsp;&emsp;&emsp;deleted||integer(int32)||
|&emsp;&emsp;carouselImages||array|ProductImage|
|&emsp;&emsp;&emsp;&emsp;id||integer(int64)||
|&emsp;&emsp;&emsp;&emsp;spuId||integer(int64)||
|&emsp;&emsp;&emsp;&emsp;skuId||integer(int64)||
|&emsp;&emsp;&emsp;&emsp;url||string||
|&emsp;&emsp;&emsp;&emsp;sort||integer(int32)||
|&emsp;&emsp;&emsp;&emsp;type||integer(int32)||
|&emsp;&emsp;&emsp;&emsp;createTime||string(date-time)||
|&emsp;&emsp;&emsp;&emsp;updateTime||string(date-time)||
|&emsp;&emsp;&emsp;&emsp;deleted||integer(int32)||
|&emsp;&emsp;detailImages||array|ProductImage|
|&emsp;&emsp;&emsp;&emsp;id||integer(int64)||
|&emsp;&emsp;&emsp;&emsp;spuId||integer(int64)||
|&emsp;&emsp;&emsp;&emsp;skuId||integer(int64)||
|&emsp;&emsp;&emsp;&emsp;url||string||
|&emsp;&emsp;&emsp;&emsp;sort||integer(int32)||
|&emsp;&emsp;&emsp;&emsp;type||integer(int32)||
|&emsp;&emsp;&emsp;&emsp;createTime||string(date-time)||
|&emsp;&emsp;&emsp;&emsp;updateTime||string(date-time)||
|&emsp;&emsp;&emsp;&emsp;deleted||integer(int32)||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {
		"spu": {
			"id": 0,
			"name": "",
			"subTitle": "",
			"categoryId": 0,
			"description": "",
			"detailHtml": "",
			"status": 0,
			"createTime": "",
			"updateTime": "",
			"createBy": 0,
			"updateBy": 0,
			"deleted": 0
		},
		"category": {
			"id": 0,
			"parentId": 0,
			"name": "",
			"level": 0,
			"icon": "",
			"description": "",
			"sort": 0,
			"status": 0,
			"createTime": "",
			"updateTime": "",
			"deleted": 0,
			"children": [
				{
					"id": 0,
					"parentId": 0,
					"name": "",
					"level": 0,
					"icon": "",
					"description": "",
					"sort": 0,
					"status": 0,
					"createTime": "",
					"updateTime": "",
					"deleted": 0,
					"children": [
						{}
					]
				}
			]
		},
		"skuList": [
			{
				"id": 0,
				"spuId": 0,
				"skuCode": "",
				"name": "",
				"specs": "",
				"price": 0,
				"originalPrice": 0,
				"stock": 0,
				"lowStock": 0,
				"pic": "",
				"status": 0,
				"sort": 0,
				"createTime": "",
				"updateTime": "",
				"deleted": 0
			}
		],
		"carouselImages": [
			{
				"id": 0,
				"spuId": 0,
				"skuId": 0,
				"url": "",
				"sort": 0,
				"type": 0,
				"createTime": "",
				"updateTime": "",
				"deleted": 0
			}
		],
		"detailImages": [
			{
				"id": 0,
				"spuId": 0,
				"skuId": 0,
				"url": "",
				"sort": 0,
				"type": 0,
				"createTime": "",
				"updateTime": "",
				"deleted": 0
			}
		]
	}
}
```


**响应状态码-400**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


**响应状态码-500**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


## 获取分类商品


**接口地址**:`/api/api/v1/products/category/{categoryId}`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:<p>根据分类ID获取商品列表</p>



**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|categoryId||path|true|integer(int64)||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|ResultListSpu|
|400|Bad Request|ResultObject|
|500|Internal Server Error|ResultObject|


**响应状态码-200**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||array|Spu|
|&emsp;&emsp;id||integer(int64)||
|&emsp;&emsp;name||string||
|&emsp;&emsp;subTitle||string||
|&emsp;&emsp;categoryId||integer(int64)||
|&emsp;&emsp;description||string||
|&emsp;&emsp;detailHtml||string||
|&emsp;&emsp;status||integer(int32)||
|&emsp;&emsp;createTime||string(date-time)||
|&emsp;&emsp;updateTime||string(date-time)||
|&emsp;&emsp;createBy||integer(int64)||
|&emsp;&emsp;updateBy||integer(int64)||
|&emsp;&emsp;deleted||integer(int32)||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": [
		{
			"id": 0,
			"name": "",
			"subTitle": "",
			"categoryId": 0,
			"description": "",
			"detailHtml": "",
			"status": 0,
			"createTime": "",
			"updateTime": "",
			"createBy": 0,
			"updateBy": 0,
			"deleted": 0
		}
	]
}
```


**响应状态码-400**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


**响应状态码-500**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


## 搜索商品


**接口地址**:`/api/api/v1/products/search`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:<p>根据关键词搜索商品</p>



**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|keyword||query|true|string||
|pageNum||query|false|integer(int32)||
|pageSize||query|false|integer(int32)||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|ResultPageSpu|
|400|Bad Request|ResultObject|
|500|Internal Server Error|ResultObject|


**响应状态码-200**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||PageSpu|PageSpu|
|&emsp;&emsp;records||array|Spu|
|&emsp;&emsp;&emsp;&emsp;id||integer(int64)||
|&emsp;&emsp;&emsp;&emsp;name||string||
|&emsp;&emsp;&emsp;&emsp;subTitle||string||
|&emsp;&emsp;&emsp;&emsp;categoryId||integer(int64)||
|&emsp;&emsp;&emsp;&emsp;description||string||
|&emsp;&emsp;&emsp;&emsp;detailHtml||string||
|&emsp;&emsp;&emsp;&emsp;status||integer(int32)||
|&emsp;&emsp;&emsp;&emsp;createTime||string(date-time)||
|&emsp;&emsp;&emsp;&emsp;updateTime||string(date-time)||
|&emsp;&emsp;&emsp;&emsp;createBy||integer(int64)||
|&emsp;&emsp;&emsp;&emsp;updateBy||integer(int64)||
|&emsp;&emsp;&emsp;&emsp;deleted||integer(int32)||
|&emsp;&emsp;total||integer(int64)||
|&emsp;&emsp;size||integer(int64)||
|&emsp;&emsp;current||integer(int64)||
|&emsp;&emsp;orders||array|OrderItem|
|&emsp;&emsp;&emsp;&emsp;column||string||
|&emsp;&emsp;&emsp;&emsp;asc||boolean||
|&emsp;&emsp;optimizeCountSql||boolean||
|&emsp;&emsp;searchCount||boolean||
|&emsp;&emsp;optimizeJoinOfCountSql||boolean||
|&emsp;&emsp;maxLimit||integer(int64)||
|&emsp;&emsp;countId||string||
|&emsp;&emsp;pages||integer(int64)||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {
		"records": [
			{
				"id": 0,
				"name": "",
				"subTitle": "",
				"categoryId": 0,
				"description": "",
				"detailHtml": "",
				"status": 0,
				"createTime": "",
				"updateTime": "",
				"createBy": 0,
				"updateBy": 0,
				"deleted": 0
			}
		],
		"total": 0,
		"size": 0,
		"current": 0,
		"orders": [
			{
				"column": "",
				"asc": true
			}
		],
		"optimizeCountSql": true,
		"searchCount": true,
		"optimizeJoinOfCountSql": true,
		"maxLimit": 0,
		"countId": "",
		"pages": 0
	}
}
```


**响应状态码-400**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


**响应状态码-500**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


# 商品分类


## 获取分类详情


**接口地址**:`/api/api/v1/categories/{id}`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:<p>根据ID获取分类详情</p>



**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|id||path|true|integer(int64)||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|ResultCategory|
|400|Bad Request|ResultObject|
|500|Internal Server Error|ResultObject|


**响应状态码-200**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||Category|Category|
|&emsp;&emsp;id||integer(int64)||
|&emsp;&emsp;parentId||integer(int64)||
|&emsp;&emsp;name||string||
|&emsp;&emsp;level||integer(int32)||
|&emsp;&emsp;icon||string||
|&emsp;&emsp;description||string||
|&emsp;&emsp;sort||integer(int32)||
|&emsp;&emsp;status||integer(int32)||
|&emsp;&emsp;createTime||string(date-time)||
|&emsp;&emsp;updateTime||string(date-time)||
|&emsp;&emsp;deleted||integer(int32)||
|&emsp;&emsp;children||array|Category|


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {
		"id": 0,
		"parentId": 0,
		"name": "",
		"level": 0,
		"icon": "",
		"description": "",
		"sort": 0,
		"status": 0,
		"createTime": "",
		"updateTime": "",
		"deleted": 0,
		"children": [
			{
				"id": 0,
				"parentId": 0,
				"name": "",
				"level": 0,
				"icon": "",
				"description": "",
				"sort": 0,
				"status": 0,
				"createTime": "",
				"updateTime": "",
				"deleted": 0,
				"children": [
					{}
				]
			}
		]
	}
}
```


**响应状态码-400**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


**响应状态码-500**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


## 获取子分类


**接口地址**:`/api/api/v1/categories/children/{parentId}`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:<p>根据父级ID获取子分类列表</p>



**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|parentId||path|true|integer(int64)||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|ResultListCategory|
|400|Bad Request|ResultObject|
|500|Internal Server Error|ResultObject|


**响应状态码-200**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||array|Category|
|&emsp;&emsp;id||integer(int64)||
|&emsp;&emsp;parentId||integer(int64)||
|&emsp;&emsp;name||string||
|&emsp;&emsp;level||integer(int32)||
|&emsp;&emsp;icon||string||
|&emsp;&emsp;description||string||
|&emsp;&emsp;sort||integer(int32)||
|&emsp;&emsp;status||integer(int32)||
|&emsp;&emsp;createTime||string(date-time)||
|&emsp;&emsp;updateTime||string(date-time)||
|&emsp;&emsp;deleted||integer(int32)||
|&emsp;&emsp;children||array|Category|


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": [
		{
			"id": 0,
			"parentId": 0,
			"name": "",
			"level": 0,
			"icon": "",
			"description": "",
			"sort": 0,
			"status": 0,
			"createTime": "",
			"updateTime": "",
			"deleted": 0,
			"children": [
				{
					"id": 0,
					"parentId": 0,
					"name": "",
					"level": 0,
					"icon": "",
					"description": "",
					"sort": 0,
					"status": 0,
					"createTime": "",
					"updateTime": "",
					"deleted": 0,
					"children": [
						{}
					]
				}
			]
		}
	]
}
```


**响应状态码-400**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


**响应状态码-500**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


## 获取分类树形结构


**接口地址**:`/api/api/v1/categories/tree`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:<p>获取所有分类的树形结构</p>



**请求参数**:


暂无


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|ResultListCategory|
|400|Bad Request|ResultObject|
|500|Internal Server Error|ResultObject|


**响应状态码-200**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||array|Category|
|&emsp;&emsp;id||integer(int64)||
|&emsp;&emsp;parentId||integer(int64)||
|&emsp;&emsp;name||string||
|&emsp;&emsp;level||integer(int32)||
|&emsp;&emsp;icon||string||
|&emsp;&emsp;description||string||
|&emsp;&emsp;sort||integer(int32)||
|&emsp;&emsp;status||integer(int32)||
|&emsp;&emsp;createTime||string(date-time)||
|&emsp;&emsp;updateTime||string(date-time)||
|&emsp;&emsp;deleted||integer(int32)||
|&emsp;&emsp;children||array|Category|


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": [
		{
			"id": 0,
			"parentId": 0,
			"name": "",
			"level": 0,
			"icon": "",
			"description": "",
			"sort": 0,
			"status": 0,
			"createTime": "",
			"updateTime": "",
			"deleted": 0,
			"children": [
				{
					"id": 0,
					"parentId": 0,
					"name": "",
					"level": 0,
					"icon": "",
					"description": "",
					"sort": 0,
					"status": 0,
					"createTime": "",
					"updateTime": "",
					"deleted": 0,
					"children": [
						{}
					]
				}
			]
		}
	]
}
```


**响应状态码-400**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


**响应状态码-500**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


# 商品图片管理


## 添加商品图片


**接口地址**:`/api/api/v1/admin/product-images`


**请求方式**:`POST`


**请求数据类型**:`application/x-www-form-urlencoded,application/json`


**响应数据类型**:`*/*`


**接口描述**:<p>添加商品图片信息</p>



**请求示例**:


```javascript
{
  "id": 0,
  "spuId": 0,
  "skuId": 0,
  "url": "",
  "sort": 0,
  "type": 0,
  "createTime": "",
  "updateTime": "",
  "deleted": 0
}
```


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|productImage|ProductImage|body|true|ProductImage|ProductImage|
|&emsp;&emsp;id|||false|integer(int64)||
|&emsp;&emsp;spuId|||false|integer(int64)||
|&emsp;&emsp;skuId|||false|integer(int64)||
|&emsp;&emsp;url|||false|string||
|&emsp;&emsp;sort|||false|integer(int32)||
|&emsp;&emsp;type|||false|integer(int32)||
|&emsp;&emsp;createTime|||false|string(date-time)||
|&emsp;&emsp;updateTime|||false|string(date-time)||
|&emsp;&emsp;deleted|||false|integer(int32)||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|ResultObject|
|400|Bad Request|ResultObject|
|500|Internal Server Error|ResultObject|


**响应状态码-200**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


**响应状态码-400**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


**响应状态码-500**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


## 删除商品图片


**接口地址**:`/api/api/v1/admin/product-images/{id}`


**请求方式**:`DELETE`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:<p>根据ID删除商品图片</p>



**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|id||path|true|integer(int64)||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|ResultObject|
|400|Bad Request|ResultObject|
|500|Internal Server Error|ResultObject|


**响应状态码-200**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


**响应状态码-400**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


**响应状态码-500**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


## 更新商品图片排序


**接口地址**:`/api/api/v1/admin/product-images/{id}/sort/{sort}`


**请求方式**:`PUT`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:<p>根据ID更新商品图片排序</p>



**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|id||path|true|integer(int64)||
|sort||path|true|integer(int32)||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|ResultObject|
|400|Bad Request|ResultObject|
|500|Internal Server Error|ResultObject|


**响应状态码-200**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


**响应状态码-400**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


**响应状态码-500**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


## 批量添加商品图片


**接口地址**:`/api/api/v1/admin/product-images/batch`


**请求方式**:`POST`


**请求数据类型**:`application/x-www-form-urlencoded,application/json`


**响应数据类型**:`*/*`


**接口描述**:<p>批量添加商品图片信息</p>



**请求示例**:


```javascript
[
  {
    "id": 0,
    "spuId": 0,
    "skuId": 0,
    "url": "",
    "sort": 0,
    "type": 0,
    "createTime": "",
    "updateTime": "",
    "deleted": 0
  }
]
```


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|productImages|ProductImage|body|true|array|ProductImage|
|&emsp;&emsp;id|||false|integer(int64)||
|&emsp;&emsp;spuId|||false|integer(int64)||
|&emsp;&emsp;skuId|||false|integer(int64)||
|&emsp;&emsp;url|||false|string||
|&emsp;&emsp;sort|||false|integer(int32)||
|&emsp;&emsp;type|||false|integer(int32)||
|&emsp;&emsp;createTime|||false|string(date-time)||
|&emsp;&emsp;updateTime|||false|string(date-time)||
|&emsp;&emsp;deleted|||false|integer(int32)||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|ResultObject|
|400|Bad Request|ResultObject|
|500|Internal Server Error|ResultObject|


**响应状态码-200**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


**响应状态码-400**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


**响应状态码-500**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


## 获取SKU图片列表


**接口地址**:`/api/api/v1/admin/product-images/sku/{skuId}`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:<p>根据SKU ID获取SKU图片列表</p>



**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|skuId||path|true|integer(int64)||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|ResultListProductImage|
|400|Bad Request|ResultObject|
|500|Internal Server Error|ResultObject|


**响应状态码-200**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||array|ProductImage|
|&emsp;&emsp;id||integer(int64)||
|&emsp;&emsp;spuId||integer(int64)||
|&emsp;&emsp;skuId||integer(int64)||
|&emsp;&emsp;url||string||
|&emsp;&emsp;sort||integer(int32)||
|&emsp;&emsp;type||integer(int32)||
|&emsp;&emsp;createTime||string(date-time)||
|&emsp;&emsp;updateTime||string(date-time)||
|&emsp;&emsp;deleted||integer(int32)||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": [
		{
			"id": 0,
			"spuId": 0,
			"skuId": 0,
			"url": "",
			"sort": 0,
			"type": 0,
			"createTime": "",
			"updateTime": "",
			"deleted": 0
		}
	]
}
```


**响应状态码-400**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


**响应状态码-500**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


## 获取商品图片列表


**接口地址**:`/api/api/v1/admin/product-images/spu/{spuId}`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:<p>根据SPU ID获取商品图片列表</p>



**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|spuId||path|true|integer(int64)||
|type||query|false|integer(int32)||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|ResultListProductImage|
|400|Bad Request|ResultObject|
|500|Internal Server Error|ResultObject|


**响应状态码-200**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||array|ProductImage|
|&emsp;&emsp;id||integer(int64)||
|&emsp;&emsp;spuId||integer(int64)||
|&emsp;&emsp;skuId||integer(int64)||
|&emsp;&emsp;url||string||
|&emsp;&emsp;sort||integer(int32)||
|&emsp;&emsp;type||integer(int32)||
|&emsp;&emsp;createTime||string(date-time)||
|&emsp;&emsp;updateTime||string(date-time)||
|&emsp;&emsp;deleted||integer(int32)||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": [
		{
			"id": 0,
			"spuId": 0,
			"skuId": 0,
			"url": "",
			"sort": 0,
			"type": 0,
			"createTime": "",
			"updateTime": "",
			"deleted": 0
		}
	]
}
```


**响应状态码-400**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


**响应状态码-500**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


## 批量上传商品图片


**接口地址**:`/api/api/v1/admin/product-images/upload-multiple/{type}`


**请求方式**:`POST`


**请求数据类型**:`multipart/form-data`


**响应数据类型**:`*/*`


**接口描述**:<p>批量上传商品图片，type: 1-主图，2-详情图</p>



**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|files||query|true|array|file|
|type||path|true|integer(int32)||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|ResultListString|
|400|Bad Request|ResultObject|
|500|Internal Server Error|ResultObject|


**响应状态码-200**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||array||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": []
}
```


**响应状态码-400**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


**响应状态码-500**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


## 上传商品图片


**接口地址**:`/api/api/v1/admin/product-images/upload/{type}`


**请求方式**:`POST`


**请求数据类型**:`application/x-www-form-urlencoded,application/json`


**响应数据类型**:`*/*`


**接口描述**:<p>上传商品图片，type: 1-主图，2-详情图</p>



**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|type||path|true|integer(int32)||
|file||query|true|file||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|ResultString|
|400|Bad Request|ResultObject|
|500|Internal Server Error|ResultObject|


**响应状态码-200**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||string||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": ""
}
```


**响应状态码-400**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


**响应状态码-500**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


# 属性管理


## 添加属性


**接口地址**:`/api/api/v1/admin/attributes`


**请求方式**:`POST`


**请求数据类型**:`application/x-www-form-urlencoded,application/json`


**响应数据类型**:`*/*`


**接口描述**:<p>添加新的商品属性</p>



**请求示例**:


```javascript
{
  "id": 0,
  "categoryId": 0,
  "name": "",
  "inputType": 0,
  "selectList": "",
  "sort": 0,
  "isFilter": 0,
  "createTime": "",
  "updateTime": "",
  "deleted": 0
}
```


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|attribute|Attribute|body|true|Attribute|Attribute|
|&emsp;&emsp;id|||false|integer(int64)||
|&emsp;&emsp;categoryId|||false|integer(int64)||
|&emsp;&emsp;name|||false|string||
|&emsp;&emsp;inputType|||false|integer(int32)||
|&emsp;&emsp;selectList|||false|string||
|&emsp;&emsp;sort|||false|integer(int32)||
|&emsp;&emsp;isFilter|||false|integer(int32)||
|&emsp;&emsp;createTime|||false|string(date-time)||
|&emsp;&emsp;updateTime|||false|string(date-time)||
|&emsp;&emsp;deleted|||false|integer(int32)||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|ResultObject|
|400|Bad Request|ResultObject|
|500|Internal Server Error|ResultObject|


**响应状态码-200**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


**响应状态码-400**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


**响应状态码-500**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


## 更新属性


**接口地址**:`/api/api/v1/admin/attributes/{id}`


**请求方式**:`PUT`


**请求数据类型**:`application/x-www-form-urlencoded,application/json`


**响应数据类型**:`*/*`


**接口描述**:<p>根据ID更新属性信息</p>



**请求示例**:


```javascript
{
  "id": 0,
  "categoryId": 0,
  "name": "",
  "inputType": 0,
  "selectList": "",
  "sort": 0,
  "isFilter": 0,
  "createTime": "",
  "updateTime": "",
  "deleted": 0
}
```


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|id||path|true|integer(int64)||
|attribute|Attribute|body|true|Attribute|Attribute|
|&emsp;&emsp;id|||false|integer(int64)||
|&emsp;&emsp;categoryId|||false|integer(int64)||
|&emsp;&emsp;name|||false|string||
|&emsp;&emsp;inputType|||false|integer(int32)||
|&emsp;&emsp;selectList|||false|string||
|&emsp;&emsp;sort|||false|integer(int32)||
|&emsp;&emsp;isFilter|||false|integer(int32)||
|&emsp;&emsp;createTime|||false|string(date-time)||
|&emsp;&emsp;updateTime|||false|string(date-time)||
|&emsp;&emsp;deleted|||false|integer(int32)||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|ResultObject|
|400|Bad Request|ResultObject|
|500|Internal Server Error|ResultObject|


**响应状态码-200**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


**响应状态码-400**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


**响应状态码-500**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


## 删除属性


**接口地址**:`/api/api/v1/admin/attributes/{id}`


**请求方式**:`DELETE`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:<p>根据ID删除属性</p>



**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|id||path|true|integer(int64)||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|ResultObject|
|400|Bad Request|ResultObject|
|500|Internal Server Error|ResultObject|


**响应状态码-200**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


**响应状态码-400**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


**响应状态码-500**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


## 获取分类属性


**接口地址**:`/api/api/v1/admin/attributes/category/{categoryId}`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:<p>根据分类ID获取属性列表</p>



**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|categoryId||path|true|integer(int64)||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|ResultListAttribute|
|400|Bad Request|ResultObject|
|500|Internal Server Error|ResultObject|


**响应状态码-200**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||array|Attribute|
|&emsp;&emsp;id||integer(int64)||
|&emsp;&emsp;categoryId||integer(int64)||
|&emsp;&emsp;name||string||
|&emsp;&emsp;inputType||integer(int32)||
|&emsp;&emsp;selectList||string||
|&emsp;&emsp;sort||integer(int32)||
|&emsp;&emsp;isFilter||integer(int32)||
|&emsp;&emsp;createTime||string(date-time)||
|&emsp;&emsp;updateTime||string(date-time)||
|&emsp;&emsp;deleted||integer(int32)||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": [
		{
			"id": 0,
			"categoryId": 0,
			"name": "",
			"inputType": 0,
			"selectList": "",
			"sort": 0,
			"isFilter": 0,
			"createTime": "",
			"updateTime": "",
			"deleted": 0
		}
	]
}
```


**响应状态码-400**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


**响应状态码-500**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


# 帖子管理


## 获取帖子列表


**接口地址**:`/api/api/v1/posts`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:<p>分页获取帖子列表，支持关键词搜索</p>



**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|pageNum||query|false|integer(int32)||
|pageSize||query|false|integer(int32)||
|keyword||query|false|string||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|ResultIPagePostResponseDTO|
|400|Bad Request|ResultObject|
|500|Internal Server Error|ResultObject|


**响应状态码-200**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||IPagePostResponseDTO|IPagePostResponseDTO|
|&emsp;&emsp;size||integer(int64)||
|&emsp;&emsp;total||integer(int64)||
|&emsp;&emsp;current||integer(int64)||
|&emsp;&emsp;records||array|PostResponseDTO|
|&emsp;&emsp;&emsp;&emsp;id||integer(int64)||
|&emsp;&emsp;&emsp;&emsp;userId||integer(int64)||
|&emsp;&emsp;&emsp;&emsp;user||User|User|
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;id||integer(int64)||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;username||string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;password||string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;nickname||string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;avatar||string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;phone||string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;email||string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;gender||integer(int32)||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;birthday||string(date-time)||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;status||integer(int32)||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;createTime||string(date-time)||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;updateTime||string(date-time)||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;createBy||integer(int64)||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;updateBy||integer(int64)||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;deleted||integer(int32)||
|&emsp;&emsp;&emsp;&emsp;title||string||
|&emsp;&emsp;&emsp;&emsp;content||string||
|&emsp;&emsp;&emsp;&emsp;images||string||
|&emsp;&emsp;&emsp;&emsp;imageList||array|string|
|&emsp;&emsp;&emsp;&emsp;viewCount||integer(int32)||
|&emsp;&emsp;&emsp;&emsp;likeCount||integer(int32)||
|&emsp;&emsp;&emsp;&emsp;commentCount||integer(int32)||
|&emsp;&emsp;&emsp;&emsp;status||integer(int32)||
|&emsp;&emsp;&emsp;&emsp;createTime||string(date-time)||
|&emsp;&emsp;&emsp;&emsp;liked||boolean||
|&emsp;&emsp;pages||integer(int64)||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {
		"size": 0,
		"total": 0,
		"current": 0,
		"records": [
			{
				"id": 0,
				"userId": 0,
				"user": {
					"id": 0,
					"username": "",
					"password": "",
					"nickname": "",
					"avatar": "",
					"phone": "",
					"email": "",
					"gender": 0,
					"birthday": "",
					"status": 0,
					"createTime": "",
					"updateTime": "",
					"createBy": 0,
					"updateBy": 0,
					"deleted": 0
				},
				"title": "",
				"content": "",
				"images": "",
				"imageList": [],
				"viewCount": 0,
				"likeCount": 0,
				"commentCount": 0,
				"status": 0,
				"createTime": "",
				"liked": true
			}
		],
		"pages": 0
	}
}
```


**响应状态码-400**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


**响应状态码-500**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


## 创建帖子


**接口地址**:`/api/api/v1/posts`


**请求方式**:`POST`


**请求数据类型**:`application/x-www-form-urlencoded,application/json`


**响应数据类型**:`*/*`


**接口描述**:<p>创建新帖子</p>



**请求示例**:


```javascript
{
  "title": "",
  "content": "",
  "imageFiles": [],
  "imageUrls": []
}
```


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|postCreateRequestDTO|PostCreateRequestDTO|body|true|PostCreateRequestDTO|PostCreateRequestDTO|
|&emsp;&emsp;title|||false|string||
|&emsp;&emsp;content|||false|string||
|&emsp;&emsp;imageFiles|||false|array|string(binary)|
|&emsp;&emsp;imageUrls|||false|array|string|


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|ResultPost|
|400|Bad Request|ResultObject|
|500|Internal Server Error|ResultObject|


**响应状态码-200**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||Post|Post|
|&emsp;&emsp;id||integer(int64)||
|&emsp;&emsp;userId||integer(int64)||
|&emsp;&emsp;title||string||
|&emsp;&emsp;content||string||
|&emsp;&emsp;images||string||
|&emsp;&emsp;viewCount||integer(int32)||
|&emsp;&emsp;likeCount||integer(int32)||
|&emsp;&emsp;commentCount||integer(int32)||
|&emsp;&emsp;status||integer(int32)||
|&emsp;&emsp;createTime||string(date-time)||
|&emsp;&emsp;updateTime||string(date-time)||
|&emsp;&emsp;createBy||integer(int64)||
|&emsp;&emsp;updateBy||integer(int64)||
|&emsp;&emsp;deleted||integer(int32)||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {
		"id": 0,
		"userId": 0,
		"title": "",
		"content": "",
		"images": "",
		"viewCount": 0,
		"likeCount": 0,
		"commentCount": 0,
		"status": 0,
		"createTime": "",
		"updateTime": "",
		"createBy": 0,
		"updateBy": 0,
		"deleted": 0
	}
}
```


**响应状态码-400**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


**响应状态码-500**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


## 获取帖子详情


**接口地址**:`/api/api/v1/posts/{postId}`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:<p>获取指定帖子的详细信息</p>



**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|postId||path|true|integer(int64)||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|ResultPostResponseDTO|
|400|Bad Request|ResultObject|
|500|Internal Server Error|ResultObject|


**响应状态码-200**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||PostResponseDTO|PostResponseDTO|
|&emsp;&emsp;id||integer(int64)||
|&emsp;&emsp;userId||integer(int64)||
|&emsp;&emsp;user||User|User|
|&emsp;&emsp;&emsp;&emsp;id||integer(int64)||
|&emsp;&emsp;&emsp;&emsp;username||string||
|&emsp;&emsp;&emsp;&emsp;password||string||
|&emsp;&emsp;&emsp;&emsp;nickname||string||
|&emsp;&emsp;&emsp;&emsp;avatar||string||
|&emsp;&emsp;&emsp;&emsp;phone||string||
|&emsp;&emsp;&emsp;&emsp;email||string||
|&emsp;&emsp;&emsp;&emsp;gender||integer(int32)||
|&emsp;&emsp;&emsp;&emsp;birthday||string(date-time)||
|&emsp;&emsp;&emsp;&emsp;status||integer(int32)||
|&emsp;&emsp;&emsp;&emsp;createTime||string(date-time)||
|&emsp;&emsp;&emsp;&emsp;updateTime||string(date-time)||
|&emsp;&emsp;&emsp;&emsp;createBy||integer(int64)||
|&emsp;&emsp;&emsp;&emsp;updateBy||integer(int64)||
|&emsp;&emsp;&emsp;&emsp;deleted||integer(int32)||
|&emsp;&emsp;title||string||
|&emsp;&emsp;content||string||
|&emsp;&emsp;images||string||
|&emsp;&emsp;imageList||array|string|
|&emsp;&emsp;viewCount||integer(int32)||
|&emsp;&emsp;likeCount||integer(int32)||
|&emsp;&emsp;commentCount||integer(int32)||
|&emsp;&emsp;status||integer(int32)||
|&emsp;&emsp;createTime||string(date-time)||
|&emsp;&emsp;liked||boolean||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {
		"id": 0,
		"userId": 0,
		"user": {
			"id": 0,
			"username": "",
			"password": "",
			"nickname": "",
			"avatar": "",
			"phone": "",
			"email": "",
			"gender": 0,
			"birthday": "",
			"status": 0,
			"createTime": "",
			"updateTime": "",
			"createBy": 0,
			"updateBy": 0,
			"deleted": 0
		},
		"title": "",
		"content": "",
		"images": "",
		"imageList": [],
		"viewCount": 0,
		"likeCount": 0,
		"commentCount": 0,
		"status": 0,
		"createTime": "",
		"liked": true
	}
}
```


**响应状态码-400**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


**响应状态码-500**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


## 删除帖子


**接口地址**:`/api/api/v1/posts/{postId}`


**请求方式**:`DELETE`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:<p>删除指定帖子</p>



**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|postId||path|true|integer(int64)||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|ResultObject|
|400|Bad Request|ResultObject|
|500|Internal Server Error|ResultObject|


**响应状态码-200**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


**响应状态码-400**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


**响应状态码-500**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


## 上传帖子图片


**接口地址**:`/api/api/v1/posts/upload-image`


**请求方式**:`POST`


**请求数据类型**:`application/x-www-form-urlencoded,application/json`


**响应数据类型**:`*/*`


**接口描述**:<p>上传帖子图片，返回图片URL</p>



**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|file||query|true|file||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|ResultString|
|400|Bad Request|ResultObject|
|500|Internal Server Error|ResultObject|


**响应状态码-200**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||string||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": ""
}
```


**响应状态码-400**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


**响应状态码-500**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


## 获取用户帖子


**接口地址**:`/api/api/v1/posts/user/{userId}`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:<p>获取指定用户的帖子列表</p>



**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|userId||path|true|integer(int64)||
|pageNum||query|false|integer(int32)||
|pageSize||query|false|integer(int32)||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|ResultIPagePostResponseDTO|
|400|Bad Request|ResultObject|
|500|Internal Server Error|ResultObject|


**响应状态码-200**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||IPagePostResponseDTO|IPagePostResponseDTO|
|&emsp;&emsp;size||integer(int64)||
|&emsp;&emsp;total||integer(int64)||
|&emsp;&emsp;current||integer(int64)||
|&emsp;&emsp;records||array|PostResponseDTO|
|&emsp;&emsp;&emsp;&emsp;id||integer(int64)||
|&emsp;&emsp;&emsp;&emsp;userId||integer(int64)||
|&emsp;&emsp;&emsp;&emsp;user||User|User|
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;id||integer(int64)||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;username||string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;password||string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;nickname||string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;avatar||string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;phone||string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;email||string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;gender||integer(int32)||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;birthday||string(date-time)||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;status||integer(int32)||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;createTime||string(date-time)||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;updateTime||string(date-time)||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;createBy||integer(int64)||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;updateBy||integer(int64)||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;deleted||integer(int32)||
|&emsp;&emsp;&emsp;&emsp;title||string||
|&emsp;&emsp;&emsp;&emsp;content||string||
|&emsp;&emsp;&emsp;&emsp;images||string||
|&emsp;&emsp;&emsp;&emsp;imageList||array|string|
|&emsp;&emsp;&emsp;&emsp;viewCount||integer(int32)||
|&emsp;&emsp;&emsp;&emsp;likeCount||integer(int32)||
|&emsp;&emsp;&emsp;&emsp;commentCount||integer(int32)||
|&emsp;&emsp;&emsp;&emsp;status||integer(int32)||
|&emsp;&emsp;&emsp;&emsp;createTime||string(date-time)||
|&emsp;&emsp;&emsp;&emsp;liked||boolean||
|&emsp;&emsp;pages||integer(int64)||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {
		"size": 0,
		"total": 0,
		"current": 0,
		"records": [
			{
				"id": 0,
				"userId": 0,
				"user": {
					"id": 0,
					"username": "",
					"password": "",
					"nickname": "",
					"avatar": "",
					"phone": "",
					"email": "",
					"gender": 0,
					"birthday": "",
					"status": 0,
					"createTime": "",
					"updateTime": "",
					"createBy": 0,
					"updateBy": 0,
					"deleted": 0
				},
				"title": "",
				"content": "",
				"images": "",
				"imageList": [],
				"viewCount": 0,
				"likeCount": 0,
				"commentCount": 0,
				"status": 0,
				"createTime": "",
				"liked": true
			}
		],
		"pages": 0
	}
}
```


**响应状态码-400**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


**响应状态码-500**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


# 文件管理


## 下载文件（兼容旧版本）


**接口地址**:`/api/api/v1/common/files/{directory}/{filename}`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:<p>从本地存储的指定目录下载文件</p>



**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|directory||path|true|string||
|filename||path|true|string||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK||
|400|Bad Request|ResultObject|
|500|Internal Server Error|ResultObject|


**响应状态码-400**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


**响应状态码-500**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


## 下载文件


**接口地址**:`/api/api/v1/common/files/{storageType}/{directory}/{filename}`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:<p>从指定存储类型和目录下载文件</p>



**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|storageType||path|true|string||
|directory||path|true|string||
|filename||path|true|string||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK||
|400|Bad Request|ResultObject|
|500|Internal Server Error|ResultObject|


**响应状态码-400**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


**响应状态码-500**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


## 删除文件


**接口地址**:`/api/api/v1/common/files/{storageType}/{directory}/{filename}`


**请求方式**:`DELETE`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:<p>从指定存储类型和目录删除文件</p>



**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|storageType||path|true|string||
|directory||path|true|string||
|filename||path|true|string||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|ResultObject|
|400|Bad Request|ResultObject|
|500|Internal Server Error|ResultObject|


**响应状态码-200**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


**响应状态码-400**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


**响应状态码-500**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


## 获取图片


**接口地址**:`/api/api/v1/common/files/image/{storageType}/{directory}/{filename}`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`image/jpeg,*/*`


**接口描述**:<p>从指定存储类型和目录获取图片</p>



**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|storageType||path|true|string||
|directory||path|true|string||
|filename||path|true|string||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK||
|400|Bad Request|ResultObject|
|500|Internal Server Error|ResultObject|


**响应状态码-400**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


**响应状态码-500**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


## 上传多个文件


**接口地址**:`/api/api/v1/common/files/upload-multiple/{storageType}/{directory}`


**请求方式**:`POST`


**请求数据类型**:`multipart/form-data`


**响应数据类型**:`*/*`


**接口描述**:<p>上传多个文件到指定存储类型和目录</p>



**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|files||query|true|array|file|
|storageType||path|true|string||
|directory||path|true|string||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|ResultListString|
|400|Bad Request|ResultObject|
|500|Internal Server Error|ResultObject|


**响应状态码-200**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||array||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": []
}
```


**响应状态码-400**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


**响应状态码-500**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


## 上传单个文件（兼容旧版本）


**接口地址**:`/api/api/v1/common/files/upload/{directory}`


**请求方式**:`POST`


**请求数据类型**:`application/x-www-form-urlencoded,application/json`


**响应数据类型**:`*/*`


**接口描述**:<p>上传单个文件到本地存储的指定目录</p>



**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|directory||path|true|string||
|file||query|true|file||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|ResultString|
|400|Bad Request|ResultObject|
|500|Internal Server Error|ResultObject|


**响应状态码-200**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||string||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": ""
}
```


**响应状态码-400**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


**响应状态码-500**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


## 上传单个文件


**接口地址**:`/api/api/v1/common/files/upload/{storageType}/{directory}`


**请求方式**:`POST`


**请求数据类型**:`application/x-www-form-urlencoded,application/json`


**响应数据类型**:`*/*`


**接口描述**:<p>上传单个文件到指定存储类型和目录</p>



**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|storageType||path|true|string||
|directory||path|true|string||
|file||query|true|file||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|ResultString|
|400|Bad Request|ResultObject|
|500|Internal Server Error|ResultObject|


**响应状态码-200**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||string||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": ""
}
```


**响应状态码-400**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


**响应状态码-500**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


# 用户模块


## 上传并更新用户头像


**接口地址**:`/api/v1/user/avatar`


**请求方式**:`POST`


**请求数据类型**:`application/x-www-form-urlencoded,application/json`


**响应数据类型**:`*/*`


**接口描述**:<p>上传并更新当前登录用户的头像</p>



**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|file|头像文件，支持jpg、png格式，大小不超过2MB|query|true|file||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|上传成功|ResultObject|
|400|上传失败，文件格式不支持或文件过大|ResultObject|
|401|未登录|ResultObject|
|500|Internal Server Error|ResultObject|


**响应状态码-200**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


**响应状态码-400**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


**响应状态码-401**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


**响应状态码-500**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


## 用户登录


**接口地址**:`/api/v1/user/login`


**请求方式**:`POST`


**请求数据类型**:`application/x-www-form-urlencoded,application/json`


**响应数据类型**:`*/*`


**接口描述**:<p>用户登录接口，需提供用户名和密码</p>



**请求示例**:


```javascript
{
  "username": "",
  "password": ""
}
```


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|userLoginRequestDTO|UserLoginRequestDTO|body|true|UserLoginRequestDTO|UserLoginRequestDTO|
|&emsp;&emsp;username|||false|string||
|&emsp;&emsp;password|||false|string||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|登录成功，返回token|ResultObject|
|400|登录失败，用户名或密码错误|ResultObject|
|500|Internal Server Error|ResultObject|


**响应状态码-200**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


**响应状态码-400**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


**响应状态码-500**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


## 用户登出


**接口地址**:`/api/v1/user/logout`


**请求方式**:`POST`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:<p>用户登出接口，清除登录状态</p>



**请求参数**:


暂无


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|登出成功|ResultObject|
|400|Bad Request|ResultObject|
|500|Internal Server Error|ResultObject|


**响应状态码-200**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


**响应状态码-400**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


**响应状态码-500**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


## 修改密码


**接口地址**:`/api/v1/user/password`


**请求方式**:`PUT`


**请求数据类型**:`application/x-www-form-urlencoded,application/json`


**响应数据类型**:`*/*`


**接口描述**:<p>修改当前登录用户的密码</p>



**请求示例**:


```javascript
{
  "oldPassword": "",
  "newPassword": ""
}
```


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|userChangePasswordRequestDTO|UserChangePasswordRequestDTO|body|true|UserChangePasswordRequestDTO|UserChangePasswordRequestDTO|
|&emsp;&emsp;oldPassword|||false|string||
|&emsp;&emsp;newPassword|||false|string||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|修改成功|ResultObject|
|400|修改失败，原密码错误或参数不合法|ResultObject|
|401|未登录|ResultObject|
|500|Internal Server Error|ResultObject|


**响应状态码-200**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


**响应状态码-400**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


**响应状态码-401**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


**响应状态码-500**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


## 获取当前用户的权限编码列表


**接口地址**:`/api/v1/user/permissions`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:<p>获取当前登录用户拥有的所有权限编码</p>



**请求参数**:


暂无


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|获取成功|ResultObject|
|400|Bad Request|ResultObject|
|401|未登录|ResultObject|
|500|Internal Server Error|ResultObject|


**响应状态码-200**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


**响应状态码-400**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


**响应状态码-401**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


**响应状态码-500**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


## 获取个人信息


**接口地址**:`/api/v1/user/profile`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:<p>获取当前登录用户的个人信息</p>



**请求参数**:


暂无


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|获取成功|User|
|400|Bad Request|ResultObject|
|401|未登录|ResultUser|
|500|Internal Server Error|ResultObject|


**响应状态码-200**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|id||integer(int64)|integer(int64)|
|username||string||
|password||string||
|nickname||string||
|avatar||string||
|phone||string||
|email||string||
|gender||integer(int32)|integer(int32)|
|birthday||string(date-time)|string(date-time)|
|status||integer(int32)|integer(int32)|
|createTime||string(date-time)|string(date-time)|
|updateTime||string(date-time)|string(date-time)|
|createBy||integer(int64)|integer(int64)|
|updateBy||integer(int64)|integer(int64)|
|deleted||integer(int32)|integer(int32)|


**响应示例**:
```javascript
{
	"id": 0,
	"username": "",
	"password": "",
	"nickname": "",
	"avatar": "",
	"phone": "",
	"email": "",
	"gender": 0,
	"birthday": "",
	"status": 0,
	"createTime": "",
	"updateTime": "",
	"createBy": 0,
	"updateBy": 0,
	"deleted": 0
}
```


**响应状态码-400**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


**响应状态码-401**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||User|User|
|&emsp;&emsp;id||integer(int64)||
|&emsp;&emsp;username||string||
|&emsp;&emsp;password||string||
|&emsp;&emsp;nickname||string||
|&emsp;&emsp;avatar||string||
|&emsp;&emsp;phone||string||
|&emsp;&emsp;email||string||
|&emsp;&emsp;gender||integer(int32)||
|&emsp;&emsp;birthday||string(date-time)||
|&emsp;&emsp;status||integer(int32)||
|&emsp;&emsp;createTime||string(date-time)||
|&emsp;&emsp;updateTime||string(date-time)||
|&emsp;&emsp;createBy||integer(int64)||
|&emsp;&emsp;updateBy||integer(int64)||
|&emsp;&emsp;deleted||integer(int32)||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {
		"id": 0,
		"username": "",
		"password": "",
		"nickname": "",
		"avatar": "",
		"phone": "",
		"email": "",
		"gender": 0,
		"birthday": "",
		"status": 0,
		"createTime": "",
		"updateTime": "",
		"createBy": 0,
		"updateBy": 0,
		"deleted": 0
	}
}
```


**响应状态码-500**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


## 修改个人信息


**接口地址**:`/api/v1/user/profile`


**请求方式**:`PUT`


**请求数据类型**:`application/x-www-form-urlencoded,application/json`


**响应数据类型**:`*/*`


**接口描述**:<p>修改当前登录用户的个人信息</p>



**请求示例**:


```javascript
{
  "nickname": "",
  "email": "",
  "phone": ""
}
```


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|userUpdateInfoRequestDTO|UserUpdateInfoRequestDTO|body|true|UserUpdateInfoRequestDTO|UserUpdateInfoRequestDTO|
|&emsp;&emsp;nickname|||false|string||
|&emsp;&emsp;email|||false|string||
|&emsp;&emsp;phone|||false|string||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|修改成功|ResultObject|
|400|修改失败，参数不合法|ResultObject|
|401|未登录|ResultObject|
|500|Internal Server Error|ResultObject|


**响应状态码-200**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


**响应状态码-400**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


**响应状态码-401**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


**响应状态码-500**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


## 用户注册


**接口地址**:`/api/v1/user/register`


**请求方式**:`POST`


**请求数据类型**:`application/x-www-form-urlencoded,application/json`


**响应数据类型**:`*/*`


**接口描述**:<p>新用户注册接口，需提供用户名、密码等信息</p>



**请求示例**:


```javascript
{
  "username": "",
  "password": ""
}
```


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|userRegisterRequestDTO|UserRegisterRequestDTO|body|true|UserRegisterRequestDTO|UserRegisterRequestDTO|
|&emsp;&emsp;username|||false|string||
|&emsp;&emsp;password|||false|string||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|注册成功|ResultObject|
|400|注册失败，用户名已存在或参数不合法|ResultObject|
|500|Internal Server Error|ResultObject|


**响应状态码-200**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


**响应状态码-400**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


**响应状态码-500**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


## 获取当前用户的角色编码列表


**接口地址**:`/api/v1/user/roles`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:<p>获取当前登录用户拥有的所有角色编码</p>



**请求参数**:


暂无


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|获取成功|ResultObject|
|400|Bad Request|ResultObject|
|401|未登录|ResultObject|
|500|Internal Server Error|ResultObject|


**响应状态码-200**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


**响应状态码-400**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


**响应状态码-401**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


**响应状态码-500**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


## 测试权限控制


**接口地址**:`/api/v1/user/test-permission`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:<p>测试权限控制功能，需要user:view权限</p>



**请求参数**:


暂无


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|有权限访问|ResultObject|
|400|Bad Request|ResultObject|
|403|无权限访问|ResultObject|
|500|Internal Server Error|ResultObject|


**响应状态码-200**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


**响应状态码-400**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


**响应状态码-403**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


**响应状态码-500**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


# admin-order-controller


## getOrderList


**接口地址**:`/api/api/v1/admin/orders`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|userId||query|true|integer(int64)||
|page||query|false|integer(int32)||
|size||query|false|integer(int32)||
|status||query|false|integer(int32)||
|orderNo||query|false|string||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|ResultIPageOrderResponseDTO|
|400|Bad Request|ResultObject|
|500|Internal Server Error|ResultObject|


**响应状态码-200**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||IPageOrderResponseDTO|IPageOrderResponseDTO|
|&emsp;&emsp;size||integer(int64)||
|&emsp;&emsp;total||integer(int64)||
|&emsp;&emsp;current||integer(int64)||
|&emsp;&emsp;records||array|OrderResponseDTO|
|&emsp;&emsp;&emsp;&emsp;id||integer(int64)||
|&emsp;&emsp;&emsp;&emsp;orderNo||string||
|&emsp;&emsp;&emsp;&emsp;userId||integer(int64)||
|&emsp;&emsp;&emsp;&emsp;addressId||integer(int64)||
|&emsp;&emsp;&emsp;&emsp;address||AddressResponseDTO|AddressResponseDTO|
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;id||integer(int64)||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;userId||integer(int64)||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;receiver||string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;phone||string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;province||string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;city||string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;district||string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;detail||string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;isDefault||integer(int32)||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;fullAddress||string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;createTime||string(date-time)||
|&emsp;&emsp;&emsp;&emsp;totalAmount||number||
|&emsp;&emsp;&emsp;&emsp;status||integer(int32)||
|&emsp;&emsp;&emsp;&emsp;statusDesc||string||
|&emsp;&emsp;&emsp;&emsp;paymentTime||string(date-time)||
|&emsp;&emsp;&emsp;&emsp;shippingTime||string(date-time)||
|&emsp;&emsp;&emsp;&emsp;completionTime||string(date-time)||
|&emsp;&emsp;&emsp;&emsp;createTime||string(date-time)||
|&emsp;&emsp;&emsp;&emsp;orderDetails||array|OrderDetailResponseDTO|
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;id||integer(int64)||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;orderId||integer(int64)||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;spuId||integer(int64)||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;skuId||integer(int64)||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;skuCode||string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;productName||string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;specs||string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;price||number||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;originalPrice||number||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;quantity||integer(int32)||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;subtotal||number||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;pic||string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;createTime||string(date-time)||
|&emsp;&emsp;pages||integer(int64)||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {
		"size": 0,
		"total": 0,
		"current": 0,
		"records": [
			{
				"id": 0,
				"orderNo": "",
				"userId": 0,
				"addressId": 0,
				"address": {
					"id": 0,
					"userId": 0,
					"receiver": "",
					"phone": "",
					"province": "",
					"city": "",
					"district": "",
					"detail": "",
					"isDefault": 0,
					"fullAddress": "",
					"createTime": ""
				},
				"totalAmount": 0,
				"status": 0,
				"statusDesc": "",
				"paymentTime": "",
				"shippingTime": "",
				"completionTime": "",
				"createTime": "",
				"orderDetails": [
					{
						"id": 0,
						"orderId": 0,
						"spuId": 0,
						"skuId": 0,
						"skuCode": "",
						"productName": "",
						"specs": "",
						"price": 0,
						"originalPrice": 0,
						"quantity": 0,
						"subtotal": 0,
						"pic": "",
						"createTime": ""
					}
				]
			}
		],
		"pages": 0
	}
}
```


**响应状态码-400**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


**响应状态码-500**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


## getOrderDetail_1


**接口地址**:`/api/api/v1/admin/orders/{orderId}`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|userId||query|true|integer(int64)||
|orderId||path|true|integer(int64)||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|ResultOrderResponseDTO|
|400|Bad Request|ResultObject|
|500|Internal Server Error|ResultObject|


**响应状态码-200**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||OrderResponseDTO|OrderResponseDTO|
|&emsp;&emsp;id||integer(int64)||
|&emsp;&emsp;orderNo||string||
|&emsp;&emsp;userId||integer(int64)||
|&emsp;&emsp;addressId||integer(int64)||
|&emsp;&emsp;address||AddressResponseDTO|AddressResponseDTO|
|&emsp;&emsp;&emsp;&emsp;id||integer(int64)||
|&emsp;&emsp;&emsp;&emsp;userId||integer(int64)||
|&emsp;&emsp;&emsp;&emsp;receiver||string||
|&emsp;&emsp;&emsp;&emsp;phone||string||
|&emsp;&emsp;&emsp;&emsp;province||string||
|&emsp;&emsp;&emsp;&emsp;city||string||
|&emsp;&emsp;&emsp;&emsp;district||string||
|&emsp;&emsp;&emsp;&emsp;detail||string||
|&emsp;&emsp;&emsp;&emsp;isDefault||integer(int32)||
|&emsp;&emsp;&emsp;&emsp;fullAddress||string||
|&emsp;&emsp;&emsp;&emsp;createTime||string(date-time)||
|&emsp;&emsp;totalAmount||number||
|&emsp;&emsp;status||integer(int32)||
|&emsp;&emsp;statusDesc||string||
|&emsp;&emsp;paymentTime||string(date-time)||
|&emsp;&emsp;shippingTime||string(date-time)||
|&emsp;&emsp;completionTime||string(date-time)||
|&emsp;&emsp;createTime||string(date-time)||
|&emsp;&emsp;orderDetails||array|OrderDetailResponseDTO|
|&emsp;&emsp;&emsp;&emsp;id||integer(int64)||
|&emsp;&emsp;&emsp;&emsp;orderId||integer(int64)||
|&emsp;&emsp;&emsp;&emsp;spuId||integer(int64)||
|&emsp;&emsp;&emsp;&emsp;skuId||integer(int64)||
|&emsp;&emsp;&emsp;&emsp;skuCode||string||
|&emsp;&emsp;&emsp;&emsp;productName||string||
|&emsp;&emsp;&emsp;&emsp;specs||string||
|&emsp;&emsp;&emsp;&emsp;price||number||
|&emsp;&emsp;&emsp;&emsp;originalPrice||number||
|&emsp;&emsp;&emsp;&emsp;quantity||integer(int32)||
|&emsp;&emsp;&emsp;&emsp;subtotal||number||
|&emsp;&emsp;&emsp;&emsp;pic||string||
|&emsp;&emsp;&emsp;&emsp;createTime||string(date-time)||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {
		"id": 0,
		"orderNo": "",
		"userId": 0,
		"addressId": 0,
		"address": {
			"id": 0,
			"userId": 0,
			"receiver": "",
			"phone": "",
			"province": "",
			"city": "",
			"district": "",
			"detail": "",
			"isDefault": 0,
			"fullAddress": "",
			"createTime": ""
		},
		"totalAmount": 0,
		"status": 0,
		"statusDesc": "",
		"paymentTime": "",
		"shippingTime": "",
		"completionTime": "",
		"createTime": "",
		"orderDetails": [
			{
				"id": 0,
				"orderId": 0,
				"spuId": 0,
				"skuId": 0,
				"skuCode": "",
				"productName": "",
				"specs": "",
				"price": 0,
				"originalPrice": 0,
				"quantity": 0,
				"subtotal": 0,
				"pic": "",
				"createTime": ""
			}
		]
	}
}
```


**响应状态码-400**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


**响应状态码-500**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


## cancelOrder_1


**接口地址**:`/api/api/v1/admin/orders/{orderId}/cancel`


**请求方式**:`POST`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|userId||query|true|integer(int64)||
|orderId||path|true|integer(int64)||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|ResultBoolean|
|400|Bad Request|ResultObject|
|500|Internal Server Error|ResultObject|


**响应状态码-200**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||boolean||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": true
}
```


**响应状态码-400**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


**响应状态码-500**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


## shipOrder


**接口地址**:`/api/api/v1/admin/orders/{orderId}/ship`


**请求方式**:`POST`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|userId||query|true|integer(int64)||
|orderId||path|true|integer(int64)||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|ResultBoolean|
|400|Bad Request|ResultObject|
|500|Internal Server Error|ResultObject|


**响应状态码-200**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||boolean||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": true
}
```


**响应状态码-400**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


**响应状态码-500**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


# order-controller


## getUserOrders


**接口地址**:`/api/api/v1/orders`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|userId||query|true|integer(int64)||
|page||query|false|integer(int32)||
|size||query|false|integer(int32)||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|ResultIPageOrderResponseDTO|
|400|Bad Request|ResultObject|
|500|Internal Server Error|ResultObject|


**响应状态码-200**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||IPageOrderResponseDTO|IPageOrderResponseDTO|
|&emsp;&emsp;size||integer(int64)||
|&emsp;&emsp;total||integer(int64)||
|&emsp;&emsp;current||integer(int64)||
|&emsp;&emsp;records||array|OrderResponseDTO|
|&emsp;&emsp;&emsp;&emsp;id||integer(int64)||
|&emsp;&emsp;&emsp;&emsp;orderNo||string||
|&emsp;&emsp;&emsp;&emsp;userId||integer(int64)||
|&emsp;&emsp;&emsp;&emsp;addressId||integer(int64)||
|&emsp;&emsp;&emsp;&emsp;address||AddressResponseDTO|AddressResponseDTO|
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;id||integer(int64)||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;userId||integer(int64)||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;receiver||string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;phone||string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;province||string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;city||string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;district||string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;detail||string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;isDefault||integer(int32)||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;fullAddress||string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;createTime||string(date-time)||
|&emsp;&emsp;&emsp;&emsp;totalAmount||number||
|&emsp;&emsp;&emsp;&emsp;status||integer(int32)||
|&emsp;&emsp;&emsp;&emsp;statusDesc||string||
|&emsp;&emsp;&emsp;&emsp;paymentTime||string(date-time)||
|&emsp;&emsp;&emsp;&emsp;shippingTime||string(date-time)||
|&emsp;&emsp;&emsp;&emsp;completionTime||string(date-time)||
|&emsp;&emsp;&emsp;&emsp;createTime||string(date-time)||
|&emsp;&emsp;&emsp;&emsp;orderDetails||array|OrderDetailResponseDTO|
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;id||integer(int64)||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;orderId||integer(int64)||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;spuId||integer(int64)||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;skuId||integer(int64)||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;skuCode||string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;productName||string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;specs||string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;price||number||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;originalPrice||number||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;quantity||integer(int32)||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;subtotal||number||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;pic||string||
|&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;createTime||string(date-time)||
|&emsp;&emsp;pages||integer(int64)||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {
		"size": 0,
		"total": 0,
		"current": 0,
		"records": [
			{
				"id": 0,
				"orderNo": "",
				"userId": 0,
				"addressId": 0,
				"address": {
					"id": 0,
					"userId": 0,
					"receiver": "",
					"phone": "",
					"province": "",
					"city": "",
					"district": "",
					"detail": "",
					"isDefault": 0,
					"fullAddress": "",
					"createTime": ""
				},
				"totalAmount": 0,
				"status": 0,
				"statusDesc": "",
				"paymentTime": "",
				"shippingTime": "",
				"completionTime": "",
				"createTime": "",
				"orderDetails": [
					{
						"id": 0,
						"orderId": 0,
						"spuId": 0,
						"skuId": 0,
						"skuCode": "",
						"productName": "",
						"specs": "",
						"price": 0,
						"originalPrice": 0,
						"quantity": 0,
						"subtotal": 0,
						"pic": "",
						"createTime": ""
					}
				]
			}
		],
		"pages": 0
	}
}
```


**响应状态码-400**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


**响应状态码-500**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


## createOrder


**接口地址**:`/api/api/v1/orders`


**请求方式**:`POST`


**请求数据类型**:`application/x-www-form-urlencoded,application/json`


**响应数据类型**:`*/*`


**接口描述**:


**请求示例**:


```javascript
{
  "addressId": 0
}
```


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|userId||query|true|integer(int64)||
|orderCreateRequestDTO|OrderCreateRequestDTO|body|true|OrderCreateRequestDTO|OrderCreateRequestDTO|
|&emsp;&emsp;addressId|||true|integer(int64)||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|ResultOrderResponseDTO|
|400|Bad Request|ResultObject|
|500|Internal Server Error|ResultObject|


**响应状态码-200**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||OrderResponseDTO|OrderResponseDTO|
|&emsp;&emsp;id||integer(int64)||
|&emsp;&emsp;orderNo||string||
|&emsp;&emsp;userId||integer(int64)||
|&emsp;&emsp;addressId||integer(int64)||
|&emsp;&emsp;address||AddressResponseDTO|AddressResponseDTO|
|&emsp;&emsp;&emsp;&emsp;id||integer(int64)||
|&emsp;&emsp;&emsp;&emsp;userId||integer(int64)||
|&emsp;&emsp;&emsp;&emsp;receiver||string||
|&emsp;&emsp;&emsp;&emsp;phone||string||
|&emsp;&emsp;&emsp;&emsp;province||string||
|&emsp;&emsp;&emsp;&emsp;city||string||
|&emsp;&emsp;&emsp;&emsp;district||string||
|&emsp;&emsp;&emsp;&emsp;detail||string||
|&emsp;&emsp;&emsp;&emsp;isDefault||integer(int32)||
|&emsp;&emsp;&emsp;&emsp;fullAddress||string||
|&emsp;&emsp;&emsp;&emsp;createTime||string(date-time)||
|&emsp;&emsp;totalAmount||number||
|&emsp;&emsp;status||integer(int32)||
|&emsp;&emsp;statusDesc||string||
|&emsp;&emsp;paymentTime||string(date-time)||
|&emsp;&emsp;shippingTime||string(date-time)||
|&emsp;&emsp;completionTime||string(date-time)||
|&emsp;&emsp;createTime||string(date-time)||
|&emsp;&emsp;orderDetails||array|OrderDetailResponseDTO|
|&emsp;&emsp;&emsp;&emsp;id||integer(int64)||
|&emsp;&emsp;&emsp;&emsp;orderId||integer(int64)||
|&emsp;&emsp;&emsp;&emsp;spuId||integer(int64)||
|&emsp;&emsp;&emsp;&emsp;skuId||integer(int64)||
|&emsp;&emsp;&emsp;&emsp;skuCode||string||
|&emsp;&emsp;&emsp;&emsp;productName||string||
|&emsp;&emsp;&emsp;&emsp;specs||string||
|&emsp;&emsp;&emsp;&emsp;price||number||
|&emsp;&emsp;&emsp;&emsp;originalPrice||number||
|&emsp;&emsp;&emsp;&emsp;quantity||integer(int32)||
|&emsp;&emsp;&emsp;&emsp;subtotal||number||
|&emsp;&emsp;&emsp;&emsp;pic||string||
|&emsp;&emsp;&emsp;&emsp;createTime||string(date-time)||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {
		"id": 0,
		"orderNo": "",
		"userId": 0,
		"addressId": 0,
		"address": {
			"id": 0,
			"userId": 0,
			"receiver": "",
			"phone": "",
			"province": "",
			"city": "",
			"district": "",
			"detail": "",
			"isDefault": 0,
			"fullAddress": "",
			"createTime": ""
		},
		"totalAmount": 0,
		"status": 0,
		"statusDesc": "",
		"paymentTime": "",
		"shippingTime": "",
		"completionTime": "",
		"createTime": "",
		"orderDetails": [
			{
				"id": 0,
				"orderId": 0,
				"spuId": 0,
				"skuId": 0,
				"skuCode": "",
				"productName": "",
				"specs": "",
				"price": 0,
				"originalPrice": 0,
				"quantity": 0,
				"subtotal": 0,
				"pic": "",
				"createTime": ""
			}
		]
	}
}
```


**响应状态码-400**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


**响应状态码-500**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


## getOrderDetail


**接口地址**:`/api/api/v1/orders/{orderId}`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|userId||query|true|integer(int64)||
|orderId||path|true|integer(int64)||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|ResultOrderResponseDTO|
|400|Bad Request|ResultObject|
|500|Internal Server Error|ResultObject|


**响应状态码-200**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||OrderResponseDTO|OrderResponseDTO|
|&emsp;&emsp;id||integer(int64)||
|&emsp;&emsp;orderNo||string||
|&emsp;&emsp;userId||integer(int64)||
|&emsp;&emsp;addressId||integer(int64)||
|&emsp;&emsp;address||AddressResponseDTO|AddressResponseDTO|
|&emsp;&emsp;&emsp;&emsp;id||integer(int64)||
|&emsp;&emsp;&emsp;&emsp;userId||integer(int64)||
|&emsp;&emsp;&emsp;&emsp;receiver||string||
|&emsp;&emsp;&emsp;&emsp;phone||string||
|&emsp;&emsp;&emsp;&emsp;province||string||
|&emsp;&emsp;&emsp;&emsp;city||string||
|&emsp;&emsp;&emsp;&emsp;district||string||
|&emsp;&emsp;&emsp;&emsp;detail||string||
|&emsp;&emsp;&emsp;&emsp;isDefault||integer(int32)||
|&emsp;&emsp;&emsp;&emsp;fullAddress||string||
|&emsp;&emsp;&emsp;&emsp;createTime||string(date-time)||
|&emsp;&emsp;totalAmount||number||
|&emsp;&emsp;status||integer(int32)||
|&emsp;&emsp;statusDesc||string||
|&emsp;&emsp;paymentTime||string(date-time)||
|&emsp;&emsp;shippingTime||string(date-time)||
|&emsp;&emsp;completionTime||string(date-time)||
|&emsp;&emsp;createTime||string(date-time)||
|&emsp;&emsp;orderDetails||array|OrderDetailResponseDTO|
|&emsp;&emsp;&emsp;&emsp;id||integer(int64)||
|&emsp;&emsp;&emsp;&emsp;orderId||integer(int64)||
|&emsp;&emsp;&emsp;&emsp;spuId||integer(int64)||
|&emsp;&emsp;&emsp;&emsp;skuId||integer(int64)||
|&emsp;&emsp;&emsp;&emsp;skuCode||string||
|&emsp;&emsp;&emsp;&emsp;productName||string||
|&emsp;&emsp;&emsp;&emsp;specs||string||
|&emsp;&emsp;&emsp;&emsp;price||number||
|&emsp;&emsp;&emsp;&emsp;originalPrice||number||
|&emsp;&emsp;&emsp;&emsp;quantity||integer(int32)||
|&emsp;&emsp;&emsp;&emsp;subtotal||number||
|&emsp;&emsp;&emsp;&emsp;pic||string||
|&emsp;&emsp;&emsp;&emsp;createTime||string(date-time)||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {
		"id": 0,
		"orderNo": "",
		"userId": 0,
		"addressId": 0,
		"address": {
			"id": 0,
			"userId": 0,
			"receiver": "",
			"phone": "",
			"province": "",
			"city": "",
			"district": "",
			"detail": "",
			"isDefault": 0,
			"fullAddress": "",
			"createTime": ""
		},
		"totalAmount": 0,
		"status": 0,
		"statusDesc": "",
		"paymentTime": "",
		"shippingTime": "",
		"completionTime": "",
		"createTime": "",
		"orderDetails": [
			{
				"id": 0,
				"orderId": 0,
				"spuId": 0,
				"skuId": 0,
				"skuCode": "",
				"productName": "",
				"specs": "",
				"price": 0,
				"originalPrice": 0,
				"quantity": 0,
				"subtotal": 0,
				"pic": "",
				"createTime": ""
			}
		]
	}
}
```


**响应状态码-400**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


**响应状态码-500**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


## cancelOrder


**接口地址**:`/api/api/v1/orders/{orderId}/cancel`


**请求方式**:`POST`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|userId||query|true|integer(int64)||
|orderId||path|true|integer(int64)||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|ResultBoolean|
|400|Bad Request|ResultObject|
|500|Internal Server Error|ResultObject|


**响应状态码-200**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||boolean||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": true
}
```


**响应状态码-400**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


**响应状态码-500**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


## confirmReceipt


**接口地址**:`/api/api/v1/orders/{orderId}/confirm`


**请求方式**:`POST`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|userId||query|true|integer(int64)||
|orderId||path|true|integer(int64)||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|ResultBoolean|
|400|Bad Request|ResultObject|
|500|Internal Server Error|ResultObject|


**响应状态码-200**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||boolean||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": true
}
```


**响应状态码-400**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


**响应状态码-500**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


## payOrder


**接口地址**:`/api/api/v1/orders/{orderId}/pay`


**请求方式**:`POST`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|userId||query|true|integer(int64)||
|orderId||path|true|integer(int64)||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|ResultBoolean|
|400|Bad Request|ResultObject|
|500|Internal Server Error|ResultObject|


**响应状态码-200**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||boolean||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": true
}
```


**响应状态码-400**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


**响应状态码-500**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


# Redis测试接口


## 删除Redis键


**接口地址**:`/api/api/v1/common/redis/delete/{key}`


**请求方式**:`DELETE`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:<p>删除指定的Redis键</p>



**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|key||path|true|string||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|ResultString|
|400|Bad Request|ResultObject|
|500|Internal Server Error|ResultObject|


**响应状态码-200**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||string||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": ""
}
```


**响应状态码-400**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


**响应状态码-500**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


## 测试Redis哈希操作


**接口地址**:`/api/api/v1/common/redis/hash/{key}`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:<p>测试设置和获取Hash类型的数据</p>



**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|key||path|true|string||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|ResultMapObjectObject|
|400|Bad Request|ResultObject|
|500|Internal Server Error|ResultObject|


**响应状态码-200**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


**响应状态码-400**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


**响应状态码-500**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


## 测试Redis列表操作


**接口地址**:`/api/api/v1/common/redis/list/{key}`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:<p>测试向列表添加数据并获取</p>



**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|key||path|true|string||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|ResultObject|
|400|Bad Request|ResultObject|
|500|Internal Server Error|ResultObject|


**响应状态码-200**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


**响应状态码-400**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


**响应状态码-500**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


## 测试Redis集合操作


**接口地址**:`/api/api/v1/common/redis/set/{key}`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:<p>测试向集合添加数据并获取</p>



**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|key||path|true|string||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|ResultObject|
|400|Bad Request|ResultObject|
|500|Internal Server Error|ResultObject|


**响应状态码-200**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


**响应状态码-400**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


**响应状态码-500**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


## 测试Redis字符串操作


**接口地址**:`/api/api/v1/common/redis/string/{key}/{value}`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:<p>测试设置和获取String类型的数据</p>



**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|key||path|true|string||
|value||path|true|string||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|ResultString|
|400|Bad Request|ResultObject|
|500|Internal Server Error|ResultObject|


**响应状态码-200**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||string||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": ""
}
```


**响应状态码-400**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


**响应状态码-500**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


## 测试Redis字符串设置过期时间


**接口地址**:`/api/api/v1/common/redis/string/expire/{key}/{value}/{time}`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:<p>测试设置String类型的数据并设置过期时间</p>



**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|key||path|true|string||
|value||path|true|string||
|time||path|true|integer(int64)||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|ResultString|
|400|Bad Request|ResultObject|
|500|Internal Server Error|ResultObject|


**响应状态码-200**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||string||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": ""
}
```


**响应状态码-400**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


**响应状态码-500**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


# SKU管理


## 添加SKU


**接口地址**:`/api/api/v1/admin/skus`


**请求方式**:`POST`


**请求数据类型**:`application/x-www-form-urlencoded,application/json`


**响应数据类型**:`*/*`


**接口描述**:<p>添加新的商品SKU</p>



**请求示例**:


```javascript
{
  "id": 0,
  "spuId": 0,
  "skuCode": "",
  "name": "",
  "specs": "",
  "price": 0,
  "originalPrice": 0,
  "stock": 0,
  "lowStock": 0,
  "pic": "",
  "status": 0,
  "sort": 0,
  "createTime": "",
  "updateTime": "",
  "deleted": 0
}
```


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|sku|Sku|body|true|Sku|Sku|
|&emsp;&emsp;id|||false|integer(int64)||
|&emsp;&emsp;spuId|||false|integer(int64)||
|&emsp;&emsp;skuCode|||false|string||
|&emsp;&emsp;name|||false|string||
|&emsp;&emsp;specs|||false|string||
|&emsp;&emsp;price|||false|number||
|&emsp;&emsp;originalPrice|||false|number||
|&emsp;&emsp;stock|||false|integer(int32)||
|&emsp;&emsp;lowStock|||false|integer(int32)||
|&emsp;&emsp;pic|||false|string||
|&emsp;&emsp;status|||false|integer(int32)||
|&emsp;&emsp;sort|||false|integer(int32)||
|&emsp;&emsp;createTime|||false|string(date-time)||
|&emsp;&emsp;updateTime|||false|string(date-time)||
|&emsp;&emsp;deleted|||false|integer(int32)||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|ResultObject|
|400|Bad Request|ResultObject|
|500|Internal Server Error|ResultObject|


**响应状态码-200**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


**响应状态码-400**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


**响应状态码-500**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


## 获取SKU详情


**接口地址**:`/api/api/v1/admin/skus/{id}`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:<p>根据ID获取SKU详情</p>



**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|id||path|true|integer(int64)||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|ResultSku|
|400|Bad Request|ResultObject|
|500|Internal Server Error|ResultObject|


**响应状态码-200**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||Sku|Sku|
|&emsp;&emsp;id||integer(int64)||
|&emsp;&emsp;spuId||integer(int64)||
|&emsp;&emsp;skuCode||string||
|&emsp;&emsp;name||string||
|&emsp;&emsp;specs||string||
|&emsp;&emsp;price||number||
|&emsp;&emsp;originalPrice||number||
|&emsp;&emsp;stock||integer(int32)||
|&emsp;&emsp;lowStock||integer(int32)||
|&emsp;&emsp;pic||string||
|&emsp;&emsp;status||integer(int32)||
|&emsp;&emsp;sort||integer(int32)||
|&emsp;&emsp;createTime||string(date-time)||
|&emsp;&emsp;updateTime||string(date-time)||
|&emsp;&emsp;deleted||integer(int32)||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {
		"id": 0,
		"spuId": 0,
		"skuCode": "",
		"name": "",
		"specs": "",
		"price": 0,
		"originalPrice": 0,
		"stock": 0,
		"lowStock": 0,
		"pic": "",
		"status": 0,
		"sort": 0,
		"createTime": "",
		"updateTime": "",
		"deleted": 0
	}
}
```


**响应状态码-400**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


**响应状态码-500**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


## 更新SKU


**接口地址**:`/api/api/v1/admin/skus/{id}`


**请求方式**:`PUT`


**请求数据类型**:`application/x-www-form-urlencoded,application/json`


**响应数据类型**:`*/*`


**接口描述**:<p>根据ID更新SKU信息</p>



**请求示例**:


```javascript
{
  "id": 0,
  "spuId": 0,
  "skuCode": "",
  "name": "",
  "specs": "",
  "price": 0,
  "originalPrice": 0,
  "stock": 0,
  "lowStock": 0,
  "pic": "",
  "status": 0,
  "sort": 0,
  "createTime": "",
  "updateTime": "",
  "deleted": 0
}
```


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|id||path|true|integer(int64)||
|sku|Sku|body|true|Sku|Sku|
|&emsp;&emsp;id|||false|integer(int64)||
|&emsp;&emsp;spuId|||false|integer(int64)||
|&emsp;&emsp;skuCode|||false|string||
|&emsp;&emsp;name|||false|string||
|&emsp;&emsp;specs|||false|string||
|&emsp;&emsp;price|||false|number||
|&emsp;&emsp;originalPrice|||false|number||
|&emsp;&emsp;stock|||false|integer(int32)||
|&emsp;&emsp;lowStock|||false|integer(int32)||
|&emsp;&emsp;pic|||false|string||
|&emsp;&emsp;status|||false|integer(int32)||
|&emsp;&emsp;sort|||false|integer(int32)||
|&emsp;&emsp;createTime|||false|string(date-time)||
|&emsp;&emsp;updateTime|||false|string(date-time)||
|&emsp;&emsp;deleted|||false|integer(int32)||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|ResultObject|
|400|Bad Request|ResultObject|
|500|Internal Server Error|ResultObject|


**响应状态码-200**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


**响应状态码-400**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


**响应状态码-500**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


## 删除SKU


**接口地址**:`/api/api/v1/admin/skus/{id}`


**请求方式**:`DELETE`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:<p>根据ID删除SKU</p>



**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|id||path|true|integer(int64)||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|ResultObject|
|400|Bad Request|ResultObject|
|500|Internal Server Error|ResultObject|


**响应状态码-200**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


**响应状态码-400**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


**响应状态码-500**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


## 获取SKU及其属性


**接口地址**:`/api/api/v1/admin/skus/{id}/attributes`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:<p>根据ID获取SKU及其属性信息</p>



**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|id||path|true|integer(int64)||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|ResultSkuWithAttributesDTO|
|400|Bad Request|ResultObject|
|500|Internal Server Error|ResultObject|


**响应状态码-200**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||SkuWithAttributesDTO|SkuWithAttributesDTO|
|&emsp;&emsp;sku||Sku|Sku|
|&emsp;&emsp;&emsp;&emsp;id||integer(int64)||
|&emsp;&emsp;&emsp;&emsp;spuId||integer(int64)||
|&emsp;&emsp;&emsp;&emsp;skuCode||string||
|&emsp;&emsp;&emsp;&emsp;name||string||
|&emsp;&emsp;&emsp;&emsp;specs||string||
|&emsp;&emsp;&emsp;&emsp;price||number||
|&emsp;&emsp;&emsp;&emsp;originalPrice||number||
|&emsp;&emsp;&emsp;&emsp;stock||integer(int32)||
|&emsp;&emsp;&emsp;&emsp;lowStock||integer(int32)||
|&emsp;&emsp;&emsp;&emsp;pic||string||
|&emsp;&emsp;&emsp;&emsp;status||integer(int32)||
|&emsp;&emsp;&emsp;&emsp;sort||integer(int32)||
|&emsp;&emsp;&emsp;&emsp;createTime||string(date-time)||
|&emsp;&emsp;&emsp;&emsp;updateTime||string(date-time)||
|&emsp;&emsp;&emsp;&emsp;deleted||integer(int32)||
|&emsp;&emsp;attributes||array|SkuAttribute|
|&emsp;&emsp;&emsp;&emsp;id||integer(int64)||
|&emsp;&emsp;&emsp;&emsp;skuId||integer(int64)||
|&emsp;&emsp;&emsp;&emsp;attributeId||integer(int64)||
|&emsp;&emsp;&emsp;&emsp;attributeValue||string||
|&emsp;&emsp;&emsp;&emsp;createTime||string(date-time)||
|&emsp;&emsp;&emsp;&emsp;updateTime||string(date-time)||
|&emsp;&emsp;&emsp;&emsp;deleted||integer(int32)||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {
		"sku": {
			"id": 0,
			"spuId": 0,
			"skuCode": "",
			"name": "",
			"specs": "",
			"price": 0,
			"originalPrice": 0,
			"stock": 0,
			"lowStock": 0,
			"pic": "",
			"status": 0,
			"sort": 0,
			"createTime": "",
			"updateTime": "",
			"deleted": 0
		},
		"attributes": [
			{
				"id": 0,
				"skuId": 0,
				"attributeId": 0,
				"attributeValue": "",
				"createTime": "",
				"updateTime": "",
				"deleted": 0
			}
		]
	}
}
```


**响应状态码-400**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


**响应状态码-500**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


## 更新SKU状态


**接口地址**:`/api/api/v1/admin/skus/{id}/status/{status}`


**请求方式**:`PUT`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:<p>根据ID更新SKU状态</p>



**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|id||path|true|integer(int64)||
|status||path|true|integer(int32)||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|ResultObject|
|400|Bad Request|ResultObject|
|500|Internal Server Error|ResultObject|


**响应状态码-200**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


**响应状态码-400**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


**响应状态码-500**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


## 更新SKU及其属性


**接口地址**:`/api/api/v1/admin/skus/{id}/with-attributes`


**请求方式**:`PUT`


**请求数据类型**:`application/x-www-form-urlencoded,application/json`


**响应数据类型**:`*/*`


**接口描述**:<p>根据ID更新SKU及其属性信息</p>



**请求示例**:


```javascript
{
  "sku": {
    "id": 0,
    "spuId": 0,
    "skuCode": "",
    "name": "",
    "specs": "",
    "price": 0,
    "originalPrice": 0,
    "stock": 0,
    "lowStock": 0,
    "pic": "",
    "status": 0,
    "sort": 0,
    "createTime": "",
    "updateTime": "",
    "deleted": 0
  },
  "attributes": [
    {
      "id": 0,
      "skuId": 0,
      "attributeId": 0,
      "attributeValue": "",
      "createTime": "",
      "updateTime": "",
      "deleted": 0
    }
  ]
}
```


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|id||path|true|integer(int64)||
|skuWithAttributesDTO|SkuWithAttributesDTO|body|true|SkuWithAttributesDTO|SkuWithAttributesDTO|
|&emsp;&emsp;sku|||false|Sku|Sku|
|&emsp;&emsp;&emsp;&emsp;id|||false|integer(int64)||
|&emsp;&emsp;&emsp;&emsp;spuId|||false|integer(int64)||
|&emsp;&emsp;&emsp;&emsp;skuCode|||false|string||
|&emsp;&emsp;&emsp;&emsp;name|||false|string||
|&emsp;&emsp;&emsp;&emsp;specs|||false|string||
|&emsp;&emsp;&emsp;&emsp;price|||false|number||
|&emsp;&emsp;&emsp;&emsp;originalPrice|||false|number||
|&emsp;&emsp;&emsp;&emsp;stock|||false|integer(int32)||
|&emsp;&emsp;&emsp;&emsp;lowStock|||false|integer(int32)||
|&emsp;&emsp;&emsp;&emsp;pic|||false|string||
|&emsp;&emsp;&emsp;&emsp;status|||false|integer(int32)||
|&emsp;&emsp;&emsp;&emsp;sort|||false|integer(int32)||
|&emsp;&emsp;&emsp;&emsp;createTime|||false|string(date-time)||
|&emsp;&emsp;&emsp;&emsp;updateTime|||false|string(date-time)||
|&emsp;&emsp;&emsp;&emsp;deleted|||false|integer(int32)||
|&emsp;&emsp;attributes|||false|array|SkuAttribute|
|&emsp;&emsp;&emsp;&emsp;id|||false|integer(int64)||
|&emsp;&emsp;&emsp;&emsp;skuId|||false|integer(int64)||
|&emsp;&emsp;&emsp;&emsp;attributeId|||false|integer(int64)||
|&emsp;&emsp;&emsp;&emsp;attributeValue|||false|string||
|&emsp;&emsp;&emsp;&emsp;createTime|||false|string(date-time)||
|&emsp;&emsp;&emsp;&emsp;updateTime|||false|string(date-time)||
|&emsp;&emsp;&emsp;&emsp;deleted|||false|integer(int32)||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|ResultObject|
|400|Bad Request|ResultObject|
|500|Internal Server Error|ResultObject|


**响应状态码-200**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


**响应状态码-400**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


**响应状态码-500**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


## 获取SPU下的SKU列表


**接口地址**:`/api/api/v1/admin/skus/spu/{spuId}`


**请求方式**:`GET`


**请求数据类型**:`application/x-www-form-urlencoded`


**响应数据类型**:`*/*`


**接口描述**:<p>根据SPU ID获取SKU列表</p>



**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|spuId||path|true|integer(int64)||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|ResultListSku|
|400|Bad Request|ResultObject|
|500|Internal Server Error|ResultObject|


**响应状态码-200**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||array|Sku|
|&emsp;&emsp;id||integer(int64)||
|&emsp;&emsp;spuId||integer(int64)||
|&emsp;&emsp;skuCode||string||
|&emsp;&emsp;name||string||
|&emsp;&emsp;specs||string||
|&emsp;&emsp;price||number||
|&emsp;&emsp;originalPrice||number||
|&emsp;&emsp;stock||integer(int32)||
|&emsp;&emsp;lowStock||integer(int32)||
|&emsp;&emsp;pic||string||
|&emsp;&emsp;status||integer(int32)||
|&emsp;&emsp;sort||integer(int32)||
|&emsp;&emsp;createTime||string(date-time)||
|&emsp;&emsp;updateTime||string(date-time)||
|&emsp;&emsp;deleted||integer(int32)||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": [
		{
			"id": 0,
			"spuId": 0,
			"skuCode": "",
			"name": "",
			"specs": "",
			"price": 0,
			"originalPrice": 0,
			"stock": 0,
			"lowStock": 0,
			"pic": "",
			"status": 0,
			"sort": 0,
			"createTime": "",
			"updateTime": "",
			"deleted": 0
		}
	]
}
```


**响应状态码-400**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


**响应状态码-500**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


## 添加SKU及其属性


**接口地址**:`/api/api/v1/admin/skus/with-attributes`


**请求方式**:`POST`


**请求数据类型**:`application/x-www-form-urlencoded,application/json`


**响应数据类型**:`*/*`


**接口描述**:<p>添加新的商品SKU及其属性</p>



**请求示例**:


```javascript
{
  "sku": {
    "id": 0,
    "spuId": 0,
    "skuCode": "",
    "name": "",
    "specs": "",
    "price": 0,
    "originalPrice": 0,
    "stock": 0,
    "lowStock": 0,
    "pic": "",
    "status": 0,
    "sort": 0,
    "createTime": "",
    "updateTime": "",
    "deleted": 0
  },
  "attributes": [
    {
      "id": 0,
      "skuId": 0,
      "attributeId": 0,
      "attributeValue": "",
      "createTime": "",
      "updateTime": "",
      "deleted": 0
    }
  ]
}
```


**请求参数**:


| 参数名称 | 参数说明 | 请求类型    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|skuWithAttributesDTO|SkuWithAttributesDTO|body|true|SkuWithAttributesDTO|SkuWithAttributesDTO|
|&emsp;&emsp;sku|||false|Sku|Sku|
|&emsp;&emsp;&emsp;&emsp;id|||false|integer(int64)||
|&emsp;&emsp;&emsp;&emsp;spuId|||false|integer(int64)||
|&emsp;&emsp;&emsp;&emsp;skuCode|||false|string||
|&emsp;&emsp;&emsp;&emsp;name|||false|string||
|&emsp;&emsp;&emsp;&emsp;specs|||false|string||
|&emsp;&emsp;&emsp;&emsp;price|||false|number||
|&emsp;&emsp;&emsp;&emsp;originalPrice|||false|number||
|&emsp;&emsp;&emsp;&emsp;stock|||false|integer(int32)||
|&emsp;&emsp;&emsp;&emsp;lowStock|||false|integer(int32)||
|&emsp;&emsp;&emsp;&emsp;pic|||false|string||
|&emsp;&emsp;&emsp;&emsp;status|||false|integer(int32)||
|&emsp;&emsp;&emsp;&emsp;sort|||false|integer(int32)||
|&emsp;&emsp;&emsp;&emsp;createTime|||false|string(date-time)||
|&emsp;&emsp;&emsp;&emsp;updateTime|||false|string(date-time)||
|&emsp;&emsp;&emsp;&emsp;deleted|||false|integer(int32)||
|&emsp;&emsp;attributes|||false|array|SkuAttribute|
|&emsp;&emsp;&emsp;&emsp;id|||false|integer(int64)||
|&emsp;&emsp;&emsp;&emsp;skuId|||false|integer(int64)||
|&emsp;&emsp;&emsp;&emsp;attributeId|||false|integer(int64)||
|&emsp;&emsp;&emsp;&emsp;attributeValue|||false|string||
|&emsp;&emsp;&emsp;&emsp;createTime|||false|string(date-time)||
|&emsp;&emsp;&emsp;&emsp;updateTime|||false|string(date-time)||
|&emsp;&emsp;&emsp;&emsp;deleted|||false|integer(int32)||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- | 
|200|OK|ResultObject|
|400|Bad Request|ResultObject|
|500|Internal Server Error|ResultObject|


**响应状态码-200**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


**响应状态码-400**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```


**响应状态码-500**:


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- | 
|success||boolean||
|code||integer(int32)|integer(int32)|
|message||string||
|data||object||


**响应示例**:
```javascript
{
	"success": true,
	"code": 0,
	"message": "",
	"data": {}
}
```