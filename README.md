# ge
A website for showing and selling painting, deploy by SSM framework.

# API
/tag/{tagId}/commodity?offset={offset}&limit={limit}      
查询某标签下的商品，按上架时间倒序排序

参数
{tagId}     标签id 
{offset}    查询的起始位置
{limit}     查询条数

返回JSON
success     是否成功标识
data        查询出来的数据
errorMsg    错误信息，null值表示无错误
      