# 猫の家系统后端接口API设计说明文档  
![](https://github.com/MrPuDaDa/CAT-Home-v2.0/blob/img/undraw_welcome_cats_thqn.png?raw=true)

> 总体描述：猫の家系统旨在为广大爱猫人士和需要宠物陪伴的人提供一个方便快捷的购买和出售猫咪及其相关产品的安全可靠交易平台，以及一个养猫交流互助平台。猫の家系统后端接口API设计说明文档是基于Stoplight的后台接口API功能、路由、参数、响应体、请求体等的说明。

## 规约
* 英文文件命名方式： 大驼峰<br> 
    例如： User.v2.0.yaml 
* 路由命名方式： 路径单词使用大驼峰、参数单词小驼峰<br>
    例如： /Goods/UsedGoods/{page}/{size}
* 文件夹命名方式： 全小写、单词间使用下划线连接<br>
    例如： cat_home
* 实体类字段/请求体字段命名方式：小驼峰<br> 
    例如： createDate
* OPERATION ID 命名方式<br>
    例如：<br> 
    路由: /Users/OwnShoppingCart/{userId}<br>
    OPERATION ID: get-Users-OwnShoppingCart-userId  
### 请求方式对应的响应代码 
|  | Put | Get| Post | Delete | Patch| PatchOne | DeleteOne| GetOne| PutOne
---------|----------|---------|---------|---------|---------|---------|---------|---------|---------
200 |Y | Y|Y | Y|Y |Y | Y|Y |Y 
201 |N | N|Y | N|N |N | N|N |N 
400 |Y | Y|Y | Y|Y |Y | Y|Y |Y 
401 |Y | Y|Y | Y|Y |Y | Y|Y |Y 
402 |Y | Y|Y | Y|Y |Y | Y|Y |Y 
403 |Y | Y|Y | Y|Y |Y | Y|Y |Y 
404 |N | N|N | N|N |Y | Y|Y |Y 
500 |Y | Y|Y | Y|Y |Y | Y|Y |Y 
503 |Y | Y|Y | Y|Y |Y | Y|Y |Y 



