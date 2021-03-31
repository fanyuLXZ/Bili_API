# tag表相关

# Ancestor 祖先ID
# Descendant 自身ID

#查询MAD节点下的所有标签
select tagName as `标签名称` from tag
where TagID in (
	select Descendant
    from treepaths
    where Ancestor = 2
    # 根据祖先ID查询出所有子孙节点
);

# 增加和删除使用java中的递归实现
# 参考https://www.jianshu.com/p/ae2eac1069a9?tdsourcetag=s_pcqq